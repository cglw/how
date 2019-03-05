package com.prettyyes.user.core;

import android.app.Activity;
import android.content.Intent;

import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.HttpAccess;
import com.prettyyes.user.api.netXutils.HttpUploadManager;
import com.prettyyes.user.app.ui.appview.model.HowLocalMedia;
import com.prettyyes.user.app.view.TopShowViewPorxy;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by chengang on 2017/12/18.
 */

public class SelectMediaHandler {
    private static final String TAG = "SelectMediaHandler";

    private int chooseMode = PictureMimeType.ofImage();
    private int max = 1;
    private PictureSelectionModel pictureSelectionModel;

    public SelectMediaHandler setMax(int max) {
        this.max = max;
        return this;
    }

    public SelectMediaHandler setChooseMode(int chooseMode) {
        this.chooseMode = chooseMode;
        return this;
    }

    private Activity activity;

    public SelectMediaHandler(Activity activity) {
        this.activity = activity;
    }

    public static SelectMediaHandler create(Activity activity) {
        return new SelectMediaHandler(activity);
    }


    public PictureSelectionModel config(List<LocalMedia> selectList) {
        pictureSelectionModel = PictureSelector.create(activity)
                .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.pictureqq)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(max)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .enablePreviewAudio(true) // 是否可播放音频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(false)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                .isGif(true)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                .videoMaxSecond(10).recordVideoSecond(10);//显示多少秒以内的视频or音频也可适用

        if(chooseMode==PictureMimeType.ofVideo())
            pictureSelectionModel.compress(true);// 是否压缩

        return pictureSelectionModel;
    }

    public void start() {
        if (pictureSelectionModel != null)
            pictureSelectionModel.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }

    public List<LocalMedia> onActivityResult(int requestCode, int resultCode, Intent data) {
        List<LocalMedia> selectList = new ArrayList<>();
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    break;
            }
        } else
            selectList.clear();
        if (selectList == null)
            selectList = new ArrayList<>();
        return selectList;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, SelectCallback selectCallback) {
        List<LocalMedia> selectList = new ArrayList<>();
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    if (selectCallback != null) {
                        // 图片选择结果回调
                        selectList = PictureSelector.obtainMultipleResult(data);
                        List<HowLocalMedia> howLocalMedias = new ArrayList<>();
                        for (LocalMedia media : selectList) {
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
                        }


                    }
                    break;
            }
        }
    }


    public interface SelectCallback {
        void success(List<HowLocalMedia> selectList, List<LocalMedia> from, List<HowLocalMedia> last, List<String> paths);
    }

    private List<HowLocalMedia> lasthowLocalselectList;


    public void onActivityResultAutoUpload(int requestCode, int resultCode, Intent data, SelectCallback selectCallback) {
        List<LocalMedia> selectList = null;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList == null)
                        return;
                    lasthowLocalselectList = new ArrayList<>();

                    for (int i = 0; i < getSelectList().size(); i++) {
                        lasthowLocalselectList.add(getSelectList().get(i));
                    }
                    getSelectList().clear();
                    List<String> paths = new ArrayList<>();
                    for (LocalMedia media : selectList) {
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
                        getSelectList().add(howLocalMedia);
                        paths.add(media.getPath());

                    }

                    for (int i = 0; i < getSelectList().size(); i++) {
                        final String path = getSelectList().get(i).getPath();

                        for (int j = 0; j < lasthowLocalselectList.size(); j++) {
                            if (path.equals(lasthowLocalselectList.get(j).getPath())
                                    && lasthowLocalselectList.get(j).getRemote_url() != null
                                    && lasthowLocalselectList.get(j).getRemote_url().length() > 0) {
                                getSelectList().get(i).setRemote_url(lasthowLocalselectList.get(j).getRemote_url());
                                break;
                            }
                        }

                        if (getSelectList().get(i).getRemote_url().contains("http"))
                            continue;
                        HttpUploadManager.create().startUpload(path, "common", new AppUploadListener() {
                            @Override
                            public void onProgress(long currentBytesCount, long totalBytesCount) {

                            }

                            @Override
                            public void onNext(Object resulte, String url) {
                                updateRemoteUrl(path, url, null);
                                if (topShowViewPorxy != null)
                                    topShowViewPorxy.setProgressImageText(getUploadSuccessCount(), getSelectList().size());

                            }

                            @Override
                            public void onError(String error) {
                                if (topShowViewPorxy != null)
                                    topShowViewPorxy.showText(error);


                            }
                        });
                    }
                    if (selectCallback != null)
                        selectCallback.success(getSelectList(), selectList, lasthowLocalselectList, paths);

                    break;
            }
        }
    }

    public void release() {
        cancelAllUpload();
    }

    public SelectMediaHandler setTopShowViewPorxy(TopShowViewPorxy topShowViewPorxy) {
        this.topShowViewPorxy = topShowViewPorxy;
        return this;
    }

    private TopShowViewPorxy topShowViewPorxy;

    private void cancelAllUpload() {
        for (int i = 0; i < getSelectList().size(); i++) {
            LogUtil.i(TAG, "cancelupload_start" + getSelectList().get(i).getPath());
            HttpAccess.getInstance().cancelUpLoad(getSelectList().get(i).getPath());
        }
    }

    public List<HowLocalMedia> getSelectList() {
        if (howLocalselectList == null)
            howLocalselectList = new ArrayList<>();
        return howLocalselectList;
    }

    public void reSet() {
        getSelectList().clear();
        if (lasthowLocalselectList != null)
            lasthowLocalselectList.clear();

    }

    private int getUploadSuccessCount() {
        int count = 0;
        for (int i = 0; i < getSelectList().size(); i++) {
            if (getSelectList().get(i).getRemote_url() != null && getSelectList().get(i).getRemote_url().length() > 0) {
                count++;
            }

        }
        return count;
    }

    List<HowLocalMedia> howLocalselectList;

    private void updateRemoteUrl(String path, String url, Object o) {
        for (int i = 0; i < getSelectList().size(); i++) {
            if (getSelectList().get(i).getPath().equals(path)) {
                getSelectList().get(i).setRemote_url(url);
                break;
            }
        }
    }

    public String getImages() throws Exception {
        check();
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < getSelectList().size(); i++) {
            imgs.add(getSelectList().get(i).getRemote_url());
        }
        return StringUtils.getSplitImgs((ArrayList<String>) imgs);
    }

    private void check() throws Exception {
        if (getUploadSuccessCount() < getSelectList().size()) {
            if (getSelectList().get(0).getMimeType() == PictureMimeType.ofVideo())
                throw new Exception("视频还未上传成功");
            else if (getSelectList().get(0).getMimeType() == PictureMimeType.ofImage())
                throw new Exception("图片还未上传成功");
            else
                throw new Exception("图片还未上传成功");

        }
    }


}
