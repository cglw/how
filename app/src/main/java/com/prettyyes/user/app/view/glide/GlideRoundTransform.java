package com.prettyyes.user.app.view.glide;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.glide
 * Author: SmileChen
 * Created on: 2016/9/19
 * Description: glide 圆脚
 */
public class GlideRoundTransform extends BitmapTransformation {

    private static float radius = 12f;


    public GlideRoundTransform(int dp) {
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);

        //float r = size / 2f;
       // canvas.drawCircle(r, r, r, paint);
        return result;
    }


    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update((getClass().getName() + Math.round(radius)).getBytes());
    }
}