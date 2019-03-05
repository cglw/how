package com.prettyyes.user.app.ui.common;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;

import org.xutils.view.annotation.ViewInject;

public class HowtoAskActivity extends BaseActivity {

    @ViewInject(R.id.btn_ask)
    Button btn_ask;
    @ViewInject(R.id.ll_imgs)
    ImageView imageView;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_howto_ask;
    }

    @Override
    protected void initViews() {
        setActivtytitle(R.string.ask_how_to);
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);


        Glide.with(this).load(R.mipmap.ask_bible).listener(new RequestListener<Drawable>() {
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
                params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                imageView.setLayoutParams(params);

                return false;
            }
        }).into(imageView);



        if (intentParmas != null) {
            if (intentParmas.show_ask)
                btn_ask.setVisibility(View.VISIBLE);
        }
//        Glide.with(this).asBitmap().load( R.mipmap.ask_bible).thumbnail(0.1f).apply(options_big).into(new GlideBigImageViewTarget(img));

        btn_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskActivity.goAskActivity(getThis());
            }
        });

    }

    @Override
    protected void loadData() {

    }
}
