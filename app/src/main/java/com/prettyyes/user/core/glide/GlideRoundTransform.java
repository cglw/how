package com.prettyyes.user.core.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.prettyyes.user.core.utils.AppUtil;

import java.security.MessageDigest;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.glide
 * Author: SmileChen
 * Created on: 2016/9/19
 * Description: Nothing
 */
public class GlideRoundTransform extends BitmapTransformation {

    private static float radius = 12f;

    public GlideRoundTransform(Context context) {
        this(context, 12);
    }



    public GlideRoundTransform(Context context, int dp) {
        super(context);
        this.radius = AppUtil.dip2px(dp);
    }




    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }


    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {

        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);


        return result;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }


//    @Override
//    public String getId() {
//        return getClass().getName() + Math.round(radius);
//    }
}