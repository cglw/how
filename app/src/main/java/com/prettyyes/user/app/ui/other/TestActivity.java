package com.prettyyes.user.app.ui.other;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.prettyyes.user.R;
import com.prettyyes.user.app.view.component.GuideOneComponent;
import com.prettyyes.user.core.utils.AppUtil;

import jp.wasabeef.richeditor.TestWebView;

public class TestActivity  extends Activity{
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView();
//    }


    private TestWebView testWebView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.img_ask).post(new Runnable() {
            @Override
            public void run() {
                showGuideView(findViewById(R.id.img_ask));

            }
        });

    }



    Guide guide;

    public void showGuideView(View targetView) {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(targetView)
                .setFullingViewId(R.id.ll)
                .setAlpha(150)
                .setHighTargetCorner(AppUtil.dip2px(40))
                .setHighTargetPadding(0)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
            }
        });


       builder.addComponent(new GuideOneComponent());
        guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(true);
        guide.show(this);

    }

}