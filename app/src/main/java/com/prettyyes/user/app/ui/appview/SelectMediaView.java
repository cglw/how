package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.api.netXutils.HttpAccess;
import com.prettyyes.user.api.netXutils.HttpUploadManager;
import com.prettyyes.user.api.netXutils.ProgressCallback;
import com.prettyyes.user.api.netXutils.requests.UploadVedioReq;
import com.prettyyes.user.api.netXutils.response.UploadVedioRes;
import com.prettyyes.user.app.adapter.GridImageAdapter;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.app.ui.appview.model.HowLocalMedia;
import com.prettyyes.user.app.view.TopShowViewPorxy;
import com.prettyyes.user.app.view.dialog.SelectMediaDialog;
import com.prettyyes.user.app.view.lvandgrid.MoveRecycleview;
import com.prettyyes.user.app.view.progress.CirclePercentView;
import com.prettyyes.user.core.AppUploadListener;
import com.prettyyes.user.core.SelectMediaHandler;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.UploadMediaEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by chengang on 2017/3/31.
 */

public class SelectMediaView extends AbsLinearlayoutView {
    private static final String TAG = "SelectMediaView";

    private TopShowViewPorxy topShowViewPorxy;

    public SelectMediaView(Context context) {
        super(context);
    }

    public SelectMediaView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private GridImageAdapter adapter;

    public GridImageAdapter getAdapter() {
        return adapter;
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_moveview;
    }

    private boolean onSelectImage = false;
    private int max = 3;
    List<LocalMedia> selectList = new ArrayList<>();
    private SelectMediaHandler selectMediaHandler;
    MoveRecycleview viewById;

    public void setOnSelectImage(boolean onSelectImage) {
        this.onSelectImage = onSelectImage;
    }

    public void setMax(int max) {
        this.max = max;
        adapter.setSelectMax(max);
    }

    public void setTopShowViewPorxyRoot(View root) {
        topShowViewPorxy = new TopShowViewPorxy(root);
    }

    @Override
    public void initViews() {
        if (selectMediaHandler == null)
            selectMediaHandler = SelectMediaHandler.create(getActivity());
        viewById = (MoveRecycleview) findViewById(R.id.moverv);
        adapter = new GridImageAdapter(getActivity(), new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {

                if (onSelectImage) {
                    adapter.setSelectMax(max);

                    selectMediaHandler.setChooseMode(PictureMimeType.ofImage()).setMax(max).config(getSelectList());
                    selectMediaHandler.start();

                } else
                    new SelectMediaDialog(getActivity(), new SelectMediaDialog.DialogCallback() {
                        @Override
                        public void picture() {
                            adapter.setSelectMax(max);
                            selectMediaHandler.setChooseMode(PictureMimeType.ofImage()).setMax(max).config(getSelectList());
                            selectMediaHandler.start();

                        }

                        @Override
                        public void vedio() {
                            adapter.setSelectMax(1);
                            selectMediaHandler.setChooseMode(PictureMimeType.ofVideo());
                            selectMediaHandler.setChooseMode(PictureMimeType.ofVideo()).setMax(1).config(getSelectList());
                            selectMediaHandler.start();
                        }
                    }).show();

            }
        });
        viewById.setThisAdapter(adapter);
        viewById.setNestedScrollingEnabled(false);
        adapter.setOnItemDeleteListener(new GridImageAdapter.OnItemDeleteListener() {
            @Override
            public void onItemClick(String path, int position, View v) {
                HttpAccess.getInstance().cancelUpLoad(path);
                topShowViewPorxy.setProgressImageText(adapter.getUploadSuccessCount(), adapter.items.size());

            }
        });
        adapter.setOnItemReloadListener(new GridImageAdapter.OnItemReloadListener() {
            @Override
            public void onItemClick(HowLocalMedia howlocalmedia, int position, View v) {


                if (((CirclePercentView) v).isError()) {
                    ((CirclePercentView) v).setError(false);
                    ((CirclePercentView) v).setPercentNoAnimi(0);
                    postData();
                } else {
                    preview(position);

                }
            }
        });
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                preview(position);
            }
        });



    }

    private void preview(int position) {
        if (getSelectList().size() > 0) {
            LocalMedia media = getSelectList().get(position);
            String pictureType = media.getPictureType();
            int mediaType = PictureMimeType.pictureToVideo(pictureType);
            switch (mediaType) {
                case 1:
                    // 预览图片 可自定长按保存路径
                    //PictureSelector.config(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                    PictureSelector.create(getActivity()).externalPicturePreview(position, getSelectList());
                    break;
                case 2:
                    // 预览视频
                    PictureSelector.create(getActivity()).externalPictureVideo(media.getPath());
                    break;
                case 3:
                    // 预览音频
                    PictureSelector.create(getActivity()).externalPictureAudio(media.getPath());
                    break;
            }


        }
    }


    @Override
    public void setListener() {


    }

    public List<LocalMedia> getSelectList() {
        selectList = new ArrayList<>();
        for (int i = 0; i < adapter.items.size(); i++) {
            HowLocalMedia howLocalMedia = adapter.items.get(i);
            selectList.add(howLocalMedia);
        }
        return selectList;
    }

    public List<HowLocalMedia> getData() {
        return adapter.getItems();
    }

    @Override
    public void setDataByModel(Object data) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    List<HowLocalMedia> howLocalMedias = new ArrayList<>();
                    for (LocalMedia media : selectList) {
                        if (media.getMimeType() == PictureMimeType.ofImage()) {
                            adapter.setSelectMax(max);
                        }
                        HowLocalMedia howLocalMedia = new HowLocalMedia();
                        howLocalMedia.setPath(media.getPath());
                        howLocalMedia.setCompressPath(media.getCompressPath());
                        howLocalMedia.setCutPath(media.getCutPath());
                        howLocalMedia.setDuration(media.getDuration());
                        howLocalMedia.setChecked(media.isChecked());
                        howLocalMedia.setCut(media.isCut());
                        howLocalMedia.setNum(media.getNum());
                        howLocalMedia.setMimeType(media.getMimeType());
                        howLocalMedia.setPictureType(media.getPictureType());
                        howLocalMedia.setCompressed(media.isCompressed());
                        howLocalMedia.setWidth(media.getWidth());
                        howLocalMedia.setHeight(media.getHeight());
                        howLocalMedias.add(howLocalMedia);
                        for (int i = 0; i < adapter.items.size(); i++) {
                            HowLocalMedia howLocalMedia1 = adapter.items.get(i);
                            if (howLocalMedia1.getPath().equals(media.getPath())) {
                                howLocalMedia.setProgress(howLocalMedia1.getProgress());
                                howLocalMedia.setRemote_url(howLocalMedia1.getRemote_url());
                                howLocalMedia.setCover_img(howLocalMedia1.getCover_img());

                            }
                        }
                    }
                    adapter.setList(howLocalMedias);
                    adapter.notifyDataSetChanged();
                    postData();

                    break;
            }
        }


        // 例如 LocalMedia 里面返回三种path
        // 1.media.getPath(); 为原图path
        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
        // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的


    }

    //上传资源
    private void postData() {
        for (int i = 0; i < adapter.items.size(); i++) {
            postDataNet(adapter.items.get(i).getPath(), adapter.items.get(i).getMimeType(), adapter.items.get(i).getRemote_url());
        }
    }

    //上传到服务器
    private void postDataNet(final String path, int mime_type, String remote_url) {
        if (remote_url.startsWith("http"))
            return;
        if (mime_type == PictureMimeType.ofVideo()) {
            final UploadVedioReq uploadVedioReq = new UploadVedioReq();
            uploadVedioReq.setBinary_video(new File(path)).postFile(new ProgressCallback() {
                @Override
                public void onFail(String paramString1, String paramString2) {

                    topShowViewPorxy.showText("上传失败");
                    updateProgress(path, 0, -1);

                }

                @Override
                public void onFinish() {

                }

                @Override
                public void onLoading(long total, long current, boolean isDownloading) {
                    updateProgress(path, (long) (current * 0.99), total);
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(String result) {
                    UploadVedioRes uploadVedioRes = GsonHelper.getGson().fromJson(result, UploadVedioRes.class);
                    if (uploadVedioRes.isSuccess()) {
                        updateRemoteUrl(path, uploadVedioRes.getExtra().getVideo_url(), uploadVedioRes.getExtra().getVideo_cover_img());
                        topShowViewPorxy.setProgressVideoText(adapter.getUploadSuccessCount(), adapter.items.size());
                        updateProgress(path, 1, 1);

                    }
                }
            });

        } else {

            final HttpUploadManager httpUploadManager = new HttpUploadManager();
            httpUploadManager.startUpload(path, "reply", new AppUploadListener() {
                @Override
                public void onProgress(long currentBytesCount, long totalBytesCount) {
                    updateProgress(path, (long) (currentBytesCount * 0.99), totalBytesCount);
                }

                @Override
                public void onNext(Object resulte, String url) {
                    updateRemoteUrl(path, url, null);
                    updateProgress(path, 1, 1);
                    topShowViewPorxy.setProgressImageText(adapter.getUploadSuccessCount(), adapter.items.size());

                }

                @Override
                public void onError(String error) {
                    updateProgress(path, 0, -1);
                    topShowViewPorxy.showText(error);

                }
            });


        }


    }

    private void updateProgress(String tag, long currentBytesCount, long totalBytesCount) {
        HowLocalMedia howLocalMedia = null;
        //        更新数据源
        for (int i = 0; i < adapter.items.size(); i++) {
            if (adapter.items.get(i).getPath() != null && adapter.items.get(i).getPath().equals(tag)) {
                howLocalMedia = adapter.items.get(i);
                howLocalMedia.setLast_progress(adapter.items.get(i).getProgress());
                if (totalBytesCount == -1) {
                    adapter.items.get(i).setProgress(0);
                } else if (currentBytesCount == totalBytesCount) {
                    adapter.items.get(i).setProgress(100);
                } else {
                    adapter.items.get(i).setProgress((int) (currentBytesCount * 1.0 * 100 / totalBytesCount));
                }
                break;
            }
        }
        //        更新数据源

        for (int i = 0; i < viewById.getChildCount(); i++) {

            CirclePercentView cp = (CirclePercentView) viewById.getChildAt(i).findViewById(R.id.cprogress);
            cp.setError(false);
            if (cp.getTag() != null && cp.getTag().equals(tag)) {
                if (totalBytesCount == -1) {
                    cp.setError(true);
                } else if (currentBytesCount == totalBytesCount) {
                    cp.setPercentNoAnimi(100);
                    ((View) cp.getParent()).setVisibility(View.GONE);

                } else {
                    int last_progress = 0;
                    if (howLocalMedia != null) {
                        last_progress = howLocalMedia.getLast_progress();
                    }
                    cp.setPercent(last_progress, (int) (currentBytesCount * 1.0 * 100 / totalBytesCount));

                }
                break;
            }
        }


    }

    private void updateRemoteUrl(String tag, String remote_url, String cover_img) {
        for (int i = 0; i < adapter.items.size(); i++) {
            if (adapter.items.get(i).getPath() != null && adapter.items.get(i).getPath().equals(tag)) {
                adapter.items.get(i).setCover_img(cover_img);
                adapter.items.get(i).setRemote_url(remote_url);
                break;
            }
        }
    }


    public void check() throws Exception {
        if (adapter.getUploadSuccessCount() < adapter.items.size()) {
            if (adapter.items.get(0).getMimeType() == PictureMimeType.ofVideo())
                throw new Exception("视频还未上传成功");
            else if (adapter.items.get(0).getMimeType() == PictureMimeType.ofImage())
                throw new Exception("图片还未上传成功");
            else
                throw new Exception("图片还未上传成功");

        }

    }

    public UploadMediaEntity getUploadMediaEntity() throws Exception {
        check();
        if (uploadMediaEntity == null)
            uploadMediaEntity = new UploadMediaEntity();

        uploadMediaEntity.getUrl().clear();
        for (int i = 0; i < adapter.items.size(); i++) {
            HowLocalMedia howLocalMedia = adapter.items.get(i);
            if (howLocalMedia.getMimeType() == PictureMimeType.ofVideo()) {
                uploadMediaEntity.setVideo(howLocalMedia.getRemote_url());
                uploadMediaEntity.setCover_image(howLocalMedia.getCover_img());
            } else if (howLocalMedia.getMimeType() == PictureMimeType.ofImage()) {
                uploadMediaEntity.addImg(howLocalMedia.getRemote_url());

            }
        }
        return uploadMediaEntity;
    }

    public String getImages() throws Exception {
        check();
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < adapter.items.size(); i++) {
            imgs.add(adapter.items.get(i).getRemote_url());
        }
        return StringUtils.getSplitImgs((ArrayList<String>) imgs);
    }

    public void setImages(List<String> imgs) {
        List<HowLocalMedia> datas = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            HowLocalMedia how = new HowLocalMedia();
            how.setPath(imgs.get(i));
            how.setMimeType(PictureMimeType.ofImage());
//            默认pictype是 图片
            if (imgs.get(i).startsWith("http")) {
                how.setRemote_url(imgs.get(i));
            }
            how.setProgress(100);
            how.setRemote_url(imgs.get(i));
            datas.add(how);
        }
        adapter.setList(datas);
        adapter.notifyDataSetChanged();
    }

    public void releseResource() {
        for (int i = 0; i < adapter.items.size(); i++) {
            HttpAccess.getInstance().cancelUpLoad(adapter.items.get(i).getPath());
        }
        topShowViewPorxy.release();

    }

    public String getMain() throws Exception {
        check();
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < adapter.items.size(); i++) {
            imgs.add(adapter.items.get(i).getRemote_url());
            return imgs.get(0);

        }
        return "";
    }

    private UploadMediaEntity uploadMediaEntity;
}
