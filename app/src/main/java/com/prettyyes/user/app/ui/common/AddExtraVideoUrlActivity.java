package com.prettyyes.user.app.ui.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.HttpAccess;
import com.prettyyes.user.api.netXutils.HttpUploadManager;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.TopShowViewPorxy;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.AppUploadListener;
import com.prettyyes.user.core.SelectMediaHandler;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.SelectExtraVideoEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.ImageHelper;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;

import org.xutils.view.annotation.ViewInject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AddExtraVideoUrlActivity extends BaseActivity {


    @ViewInject(R.id.edit_video_url)
    EditTextDel edt_url;
    @ViewInject(R.id.img_video_covery)
    ImageView img_covery;
    @ViewInject(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @ViewInject(R.id.tv_change_img)
    TextView tv_change_img;

    @ViewInject(R.id.view_change_img)
    View view_change_img;
    @ViewInject(R.id.view_add_img)
    View view_add_img;


    @ViewInject(R.id.btn_confirm)
    Button btn_confirm;
    private String video_path;
    private String video_covery;
    MediaMetadataRetriever media;
    private String upload_path = "";
    private SelectMediaHandler selectMediaHandler;
    private TopShowViewPorxy topShowViewPorxy;


    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_add_extra;
    }


    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            video_covery = intentParmas.getVideo_covery();
            video_path = intentParmas.getVideo_path();
        }
    }

    @Override
    protected void initViews() {

        setActivtytitle("添加外部视频链接");
        edt_url.setText(video_path);
        showAdd();
        if (video_covery != null && video_covery.length() > 0) {
            ImageLoadUtils.loadListimg(this, video_covery, img_covery);
            showChange();
        }
        media = new MediaMetadataRetriever();
        selectMediaHandler = SelectMediaHandler.create(this).setMax(1).setChooseMode(PictureMimeType.ofImage());
        topShowViewPorxy = new TopShowViewPorxy(coordinatorLayout);


    }

    public void showAdd() {
        view_add_img.setVisibility(View.VISIBLE);
        tv_change_img.setVisibility(View.GONE);

    }

    public void showChange() {
        view_add_img.setVisibility(View.GONE);
        tv_change_img.setVisibility(View.VISIBLE);


    }

    @Override
    protected void setListener() {
        super.setListener();

        edt_url.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (StringUtils.isVideo(s.toString())) {
//                    getCovery();
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_url.setOnTextContextChange(new EditTextDel.onTextContextChange() {
            @Override
            public void paste() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (edt_url.getText().toString().length() > 0)
                            getCovery();
                    }
                }, 200);
                SoftKeyboardUtil.closeInputMethod(getThis(), edt_url);
            }
        });


        img_covery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video_covery != null)
                    JumpPageManager.getManager().goBigImgActivity(getThis(), video_covery);
            }
        });
        view_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg();
            }
        });
        tv_change_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video_path = edt_url.getText().toString();
                if (video_path.length() <= 0) {
                    showToastShort("视频链接不可以为空");
                    return;
                }
                if (!(video_path.startsWith("http"))) {
                    showToastShort("请输入正确的视频链接");
                    return;
                }
                if (video_covery == null || video_covery.length() <= 0) {
                    showToastShort("视频封面不可以为空");
                    return;
                }
                AppRxBus.getInstance().post(new SelectExtraVideoEvent(video_path, video_covery));
                finish();

            }
        });
    }

    private void selectImg() {
        selectMediaHandler.config(null); // 裁剪是否可旋转图片
        selectMediaHandler.start();
    }

    private void getCovery() {


        showProgressDialog("正在获取封面图");
        final String path = edt_url.getText().toString();

        mSubscription = Observable
                .just(path)
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        String save_path = null;
                        try {
                            media.setDataSource(path, new HashMap<String, String>());
                            Bitmap bitmap = media.getFrameAtTime(0, MediaMetadataRetriever.OPTION_NEXT_SYNC);
                            try {
                                save_path = ImageHelper.saveBitmapToFile(ImageHelper.comp(bitmap), "video_covery.jpg");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception ee) {
                            showToastShort("获取视频链接封面图失败");
                            dismissProgressDialog();
                        }

                        return save_path;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        ImageLoadUtils.loadListimg(getThis(), s, img_covery);
                        dismissProgressDialog();

                        showChange();
                        return s;
                    }
                });
        ((Observable) mSubscription).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                showProgressDialog(R.string.upload_ing);
                upload_path = s;
                uploadImg(s);
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onComplete() {
                dismissProgressDialog();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> selectList = selectMediaHandler.onActivityResult(requestCode, resultCode, data);
        if (selectList.size() > 0) {
            showProgressDialog(R.string.upload_ing);
            upload_path = selectList.get(0).getPath();
            ImageLoadUtils.loadListimg(getThis(), upload_path, img_covery);
            showChange();
            uploadImg(upload_path);


        }

    }

    private void uploadImg(String path) {
        new HttpUploadManager().startUpload(path, "video_covery", new AppUploadListener() {
            @Override
            public void onProgress(long currentBytesCount, long totalBytesCount) {

            }

            @Override
            public void onNext(Object resulte, String url) {
                dismissProgressDialog();
                video_covery = url;
                topShowViewPorxy.setProgressImageText(1, 1);

            }

            @Override
            public void onError(String erroe) {
                dismissProgressDialog();
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpAccess.getInstance().cancelUpLoad(upload_path);

    }
}
