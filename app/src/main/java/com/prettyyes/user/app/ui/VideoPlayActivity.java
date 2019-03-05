package com.prettyyes.user.app.ui;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;

import com.luck.picture.lib.PictureBaseActivity;
import com.prettyyes.user.R;
import com.prettyyes.user.app.view.app.MyVideoView;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;


public class VideoPlayActivity extends PictureBaseActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, View.OnClickListener {
    private String video_path = "";
    private String video_covery = "";
    private ImageView picture_left_back;
    private MediaController mMediaController;
    private MyVideoView mVideoView;
    private ImageView iv_play;
    private ImageView img_covery;
    private int mPositionWhenPaused = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            video_path = intentParmas.getVideo_path();
            video_covery = intentParmas.getVideo_covery();
        }

        picture_left_back = (ImageView) findViewById(com.luck.picture.lib.R.id.picture_left_back);
        img_covery = (ImageView) findViewById(R.id.img_covery);
//        jc = (JCVideoPlayerStandard) findViewById(R.id.jc);
        mVideoView = (MyVideoView) findViewById(com.luck.picture.lib.R.id.video_view);
        mVideoView.setBackgroundColor(Color.BLACK);
        iv_play = (ImageView) findViewById(com.luck.picture.lib.R.id.iv_play);
        mMediaController = new MediaController(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setMediaController(mMediaController);
        picture_left_back.setOnClickListener(this);
        iv_play.setOnClickListener(this);
        ImageLoadUtils.loadListimg(this, video_covery, img_covery);
        mVideoView.setPlayListener(new MyVideoView.PlayListener() {
            @Override
            public void onPause() {
                iv_play.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStart() {

            }
        });

//        jc.coverImageView
//        jc.setUp(video_path, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
//
//        ImageLoadUtils.loadBigImg(this, video_covery, jc.thumbImageView);

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                            mVideoView.setBackgroundColor(Color.TRANSPARENT);
                        iv_play.setVisibility(View.INVISIBLE);
                        img_covery.setVisibility(View.INVISIBLE);

                        return true;
                    }
                });
            }
        });
    }


    public void onStart() {
        // Play Video
        if (video_path.startsWith("http")) {
            mVideoView.setVideoURI(Uri.parse(video_path));
        } else {
            mVideoView.setVideoPath(video_path);
        }
        mVideoView.start();

////        jz.playOnThisJzvd();
//       jc.play();


        super.onStart();
    }

    public void onPause() {
        // Stop video when the activity is pause.
        mPositionWhenPaused = mVideoView.getCurrentPosition();
        mVideoView.stopPlayback();
//        JCVideoPlayer.releaseAllVideos();

        super.onPause();
    }

//    @Override
//    public void onBackPressed() {
////        if (JCVideoPlayer.backPress()) {
////            return;
//        }
//        super.onBackPressed();
//    }


    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0, R.anim.a3);
    }

    @Override
    protected void onDestroy() {
        mMediaController = null;
        mVideoView = null;
        iv_play = null;
        super.onDestroy();
    }

    public void onResume() {
        // Resume video player
        if (mPositionWhenPaused >= 0) {
            mVideoView.seekTo(mPositionWhenPaused);
            mPositionWhenPaused = -1;
        }

        super.onResume();
    }

    @Override
    public boolean onError(MediaPlayer player, int arg1, int arg2) {
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (null != iv_play) {
            iv_play.setVisibility(View.VISIBLE);
            img_covery.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == com.luck.picture.lib.R.id.picture_left_back) {
            finish();
        } else if (id == com.luck.picture.lib.R.id.iv_play) {
            mVideoView.start();
            iv_play.setVisibility(View.INVISIBLE);
            img_covery.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ContextWrapper(newBase) {
            @Override
            public Object getSystemService(String name) {
                if (Context.AUDIO_SERVICE.equals(name))
                    return getApplicationContext().getSystemService(name);
                return super.getSystemService(name);
            }
        });
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    // video started
                    mVideoView.setBackgroundColor(Color.TRANSPARENT);
                    iv_play.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });
    }
}
