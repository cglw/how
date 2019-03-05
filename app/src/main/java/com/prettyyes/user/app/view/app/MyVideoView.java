package com.prettyyes.user.app.view.app;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by chengang on 2017/12/21.
 */

public class MyVideoView extends VideoView {
    private PlayListener playListener;
    public interface PlayListener{
        public void onPause();
        public void onStart();

    }
    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPlayListener(PlayListener playListener) {
        this.playListener = playListener;
    }


    @Override
    public void pause() {
        super.pause();
        if(playListener==null){

        }else {
            playListener.onPause();
        }
    }

    @Override
    public void start() {
        super.start();
        if(playListener==null){

        }else {
            playListener.onStart();
        }
    }


}