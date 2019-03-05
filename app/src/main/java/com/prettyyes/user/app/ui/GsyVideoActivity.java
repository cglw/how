package com.prettyyes.user.app.ui;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.ui.appview.SampleCoverVideo;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StatusBarUtil;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

public class GsyVideoActivity extends AppCompatActivity {
    OrientationUtils orientationUtils;
    SampleCoverVideo videoPlayer;
    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";
    private boolean isTransition;
    private String video_path = "";
    private String video_covery = "";
    private Transition transition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsy_video);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);

        videoPlayer = (SampleCoverVideo) findViewById(R.id.gsyVideoPlayer);
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);

        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            video_path = intentParmas.getVideo_path();
            video_covery = intentParmas.getVideo_covery();
        }

        //增加封面

        videoPlayer.setUp(video_path, true, "");
        videoPlayer.setCoveryImage(video_covery);

        orientationUtils = new OrientationUtils(this, videoPlayer);



        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        videoPlayer.setBackgroundColor(Color.TRANSPARENT);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initTransition();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoPlayer!=null)
        {
            videoPlayer.setStandardVideoAllCallBack(null);
            GSYVideoPlayer.releaseAllVideos();
        }
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setStandardVideoAllCallBack(null);
        GSYVideoPlayer.releaseAllVideos();
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
            LogUtil.d("TAG", "isTransition close");
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }, 500);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    private void initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(videoPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            startVideoAnnimation();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new OnTransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    startVideoAnnimation();

                    transition.removeListener(this);
                }

                @Override
                public void onTransitionStart(Transition transition) {
                    super.onTransitionStart(transition);
//                    startVideoAnnimation();
                }
            });
            return true;
        }
        return false;
    }

    private void startVideoAnnimation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                videoPlayer.startPlayLogic();
            }
        }, 500);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public class OnTransitionListener implements Transition.TransitionListener {


        @Override
        public void onTransitionStart(Transition transition) {

        }

        @Override
        public void onTransitionEnd(Transition transition) {

        }

        @Override
        public void onTransitionCancel(Transition transition) {

        }

        @Override
        public void onTransitionPause(Transition transition) {

        }

        @Override
        public void onTransitionResume(Transition transition) {

        }
    }


}
