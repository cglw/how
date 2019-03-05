package com.prettyyes.user.app.view.imageview;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.prettyyes.user.app.base.BaseApplication;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.imageview
 * Author: SmileChen
 * Created on: 2016/9/9
 * Description: Nothing
 */
public class GlideBigImageViewTarget extends BitmapImageViewTarget {


    public GlideBigImageViewTarget(ImageView view) {
        super(view);
    }

    @Override
    public void onResourceReady(Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
        super.onResourceReady(resource, transition);
        int imageHeight = resource.getHeight();
        if (imageHeight > BaseApplication.ScreenHeight) {
//            imageHeight = 4096;
            ViewGroup.LayoutParams para = view.getLayoutParams();
            para.height = imageHeight;
            view.setLayoutParams(para);
        }
    }
}
//    @Override
//    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//        int imageHeight = resource.getHeight();
//        if (imageHeight > 4096) {
//            imageHeight = 4096;
//            ViewGroup.LayoutParams para = view.getLayoutParams();
//            para.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            para.height = imageHeight;
//            view.setLayoutParams(para);
//        }
//
//        super.onResourceReady(resource, glideAnimation);
//    }


//}
