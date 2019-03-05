package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * 带封面
 * Created by guoshuyu on 2017/9/3.
 */

public class SampleCoverVideo extends StandardGSYVideoPlayer {


    private ImageView img_covery;

    public SampleCoverVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
        init();
    }

    public SampleCoverVideo(Context context) {
        super(context);
        init();
    }

    public SampleCoverVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_layout_cover;
    }


    public void setCoveryImage(String video_covery_path) {

        ImageLoadUtils.loadBigImg(getContext(), video_covery_path, img_covery);
        setViewShowState(mLoadingProgressBar, VISIBLE);
        getStartButton().setVisibility(View.GONE);


    }

    @Override
    protected void changeUiToPreparingShow() {
        super.changeUiToPreparingShow();
    }

    private void init() {
        img_covery = (ImageView) findViewById(R.id.img_covery);
        getStartButton().setVisibility(GONE);

        setVideoAllCallBack(new VideoAllCallBack() {
            @Override
            public void onPrepared(String url, Object... objects) {
                getStartButton().setVisibility(GONE);

                Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_out);
                loadAnimation.setDuration(200);
                loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        img_covery.setVisibility(GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                img_covery.startAnimation(loadAnimation);


            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {
                img_covery.setVisibility(GONE);

            }

            @Override
            public void onClickStartError(String url, Object... objects) {

            }

            @Override
            public void onClickStop(String url, Object... objects) {

            }

            @Override
            public void onClickStopFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickResume(String url, Object... objects) {

            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                img_covery.setVisibility(VISIBLE);
            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {

            }

            @Override
            public void onPlayError(String url, Object... objects) {
                AppUtil.showToastShort("该视频资源不可播放");
            }

            @Override
            public void onClickStartThumb(String url, Object... objects) {

            }

            @Override
            public void onClickBlank(String url, Object... objects) {

            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {

            }
        });
    }


}
