package com.prettyyes.user.core.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.view.glide.CircleTransform;
import com.prettyyes.user.app.view.glide.GlideRoundTransform;
import com.prettyyes.user.app.view.imageview.GlideBigImageViewTarget;

import static com.prettyyes.user.app.base.BaseApplication.ScreenWidth;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.utils
 * Author: SmileChen
 * Created on: 2016/8/17
 * Description: Nothing
 * glide 配置priority 其他的配置会地址gif无法加载 用默认 或者high
 */
public class ImageLoadUtils {
    private static final boolean closeDown = false;
    private static final String TAG = "ImageLoadUtils";
    private static final boolean isOpenMemoryCache = false;
    public static String extral = "";
    public static String extral_head = "";

    public static final RequestOptions options_head = new RequestOptions()
            .centerCrop()
            .priority(Priority.NORMAL)
            .placeholder(R.mipmap.myhead)
            .error(R.mipmap.myhead)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .transform(new CircleTransform(BaseApplication.getAppContext()));
    public static final RequestOptions options_radis = new RequestOptions()
            .centerCrop()
            .priority(Priority.NORMAL)
            .placeholder(R.mipmap.ic_defaultimg)
            .error(R.mipmap.ic_defaultimg)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE);


    public static final RequestOptions options_normal = new RequestOptions()
            .centerCrop()
            .priority(Priority.NORMAL)
            .placeholder(R.mipmap.ic_defaultimg).error(R.mipmap.ic_defaultimg)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public static final RequestOptions options_normal_no_default = new RequestOptions()
            .centerCrop()
            .priority(Priority.NORMAL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();


    public static final RequestOptions options_gif = new RequestOptions()
            .placeholder(R.mipmap.ic_defaultimg)
            .error(R.mipmap.ic_defaultimg)
            .diskCacheStrategy(DiskCacheStrategy.ALL);
    public static final RequestOptions options_big = new RequestOptions()
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.mipmap.ic_defaultimg)
            .error(R.mipmap.ic_defaultimg);


    public static final RequestOptions options_splash = new RequestOptions()
            .centerCrop()
            .error(R.mipmap.bg_app_start)
            .dontAnimate();

    public static final RequestOptions options_guide = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .error(R.mipmap.bg_app_start);


    public static boolean isGif(String url) {
        if (url == null)
            return false;
        return url.endsWith("gif");
    }

    public static void loadHeadImg(Context context, String url, ImageView imageView) {
        if (closeDown || url == null || imageView == null || context == null)
            return;
        if (isGif(url)) {
            Glide.with(context).load(url).apply(options_gif).into(imageView);
            return;
        }
        Glide.with(context).load(getTargetUrl(url, extral)).apply(options_head).into(imageView);
    }


    public static void loadListimg(String url, ImageView imageView) {
        loadListimgBySet(BaseApplication.context, url, 4, imageView);
    }

    public static void loadListimg(Context context, String url, ImageView imageView) {
        loadListimgBySet(context, url, 4, imageView);
    }

    public static void loadListimgNodefault(Context context, String url, ImageView imageView) {
        loadListimgBySetNoDefault(context, url, 4, imageView);
    }

    public static void loadListimgRadis(Context context, String url, ImageView imageView, int radis) {
        loadListimgBySetRound(context, url, 4, imageView, radis);
    }

    public static void loadSplash(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(options_splash).into(imageView);

    }

    public static void loadGuide(Context context, int res, ImageView imageView) {
        Glide.with(context).load(res).apply(options_guide).into(imageView);
    }

    public static void loadBigImage(Context context, int res, ImageView imageView) {
        Glide.with(context).load(res).apply(options_guide).into(imageView);
    }

    public static void loadListimgBySet(Context context, String url, int num, ImageView imageView) {
        if (closeDown || url == null || imageView == null || context == null)
            return;
        if (isGif(url)) {
            Glide.with(context).load(url).apply(options_gif).into(imageView);
            return;
        }
        String extral = String.format("?imageView2/4/w/%d/h/%d", ScreenWidth / num, ScreenWidth / num);
        Glide.with(context).load(getTargetUrl(url, extral)).apply(options_normal).into(imageView);

    }

    public static void loadListimgBySetNoDefault(Context context, String url, int num, ImageView imageView) {
        if (closeDown || url == null || imageView == null || context == null)
            return;
        if (isGif(url)) {
            Glide.with(context).load(url).apply(options_gif).into(imageView);
            return;
        }
        String extral = String.format("?imageView2/4/w/%d/h/%d", ScreenWidth / num, ScreenWidth / num);
        Glide.with(context).load(getTargetUrl(url, extral)).apply(options_normal_no_default).into(imageView);

    }

    private static void loadListimgBySetRound(Context context, String url, int num, ImageView imageView, int radis) {
        if (closeDown || url == null || imageView == null || context == null)
            return;
        if (isGif(url)) {
            Glide.with(context).load(url).apply(options_gif).into(imageView);
            return;
        }
        String extral = String.format("?imageView2/4/w/%d/h/%d", ScreenWidth / num, ScreenWidth / num);
        Glide.with(context).load(getTargetUrl(url, extral)).apply(options_radis.transform(new GlideRoundTransform(radis))).into(imageView);

    }

    private static String getTargetUrl(String url, String extral) {
        String target_url = url;
        if (url.startsWith("http")) {
            target_url = String.format("%s%s", url, extral);
        }
        return target_url;
    }

    public static void loadBigImg(final Context context, String url, final ImageView imageView) {
        if (closeDown || url == null || imageView == null || context == null)
            return;
        if (isGif(url)) {
            Glide.with(context).load(url).apply(options_gif).into(imageView);
            return;
        }
        Glide.with(context).asBitmap().load(url).thumbnail(0.1f).apply(options_big).into(new GlideBigImageViewTarget(imageView));

    }

    public static void loadBigImgNew(final Context context, String url, final ImageView imageView) {
        if (closeDown || url == null || imageView == null || context == null)
            return;
        if (isGif(url)) {
            Glide.with(context).load(url).apply(options_gif).into(imageView);
            return;
        }
        Glide.with(context).load(url).thumbnail(0.1f).apply(options_big).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                float scale = (float) vw / (float) resource.getIntrinsicWidth();
                int vh = Math.round(resource.getIntrinsicHeight() * scale);
                if (vh > BaseApplication.ScreenHeight) {

                    params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                    imageView.setLayoutParams(params);
                }
                return false;
            }
        }).into(imageView);
    }


}
