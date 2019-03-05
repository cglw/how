package com.prettyyes.user.app.view.imageview;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.imageview
 * Author: SmileChen
 * Created on: 2016/9/9
 * Description: Nothing
 */
public class GlideImageViewTarget extends BitmapImageViewTarget {
    public GlideImageViewTarget(ImageView view) {
        super(view);
    }

//    @Override
//    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//        if (view != null && resource != null && view.getScaleType() != ImageView.ScaleType.CENTER_CROP) {
//            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            view.setImageBitmap(resource);
//        }
//        super.onResourceReady(resource, glideAnimation);
//    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        if (placeholder != null && placeholder != null && view != null && view.getScaleType() != ImageView.ScaleType.CENTER_CROP) {
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageDrawable(placeholder);

        }
        super.onLoadStarted(placeholder);
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {
        if (placeholder != null && placeholder != null && view != null && view.getScaleType() != ImageView.ScaleType.CENTER_CROP) {
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageDrawable(placeholder);
        }
        super.onLoadCleared(placeholder);
    }
}
