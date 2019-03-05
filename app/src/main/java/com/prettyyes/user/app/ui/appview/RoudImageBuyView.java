package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.app.view.imageview.RoundImageView;

/**
 * Created by chengang on 2017/8/3.
 */

public class RoudImageBuyView extends AbsRelativelayoutView {


    public RoudImageBuyView(Context context) {
        super(context);
    }

    public RoudImageBuyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_img_bug_view;
    }

    @Override
    public void initViews() {
        bindViews();

    }

    @Override
    public void setDataByModel(Object data) {

    }

    public void hideBuy() {
        price.setVisibility(GONE);
        desc.setVisibility(GONE);
        mView_viewimg_translate.setVisibility(GONE);
        showbg = false;

    }


    public void showBuy() {
        mView_viewimg_translate.setVisibility(VISIBLE);
        price.setVisibility(VISIBLE);
        desc.setVisibility(VISIBLE);
        showbg = true;
    }

    public boolean showbg = true;

    public RelativeLayout mView_viewimg_translate;
    public RoundImageView img;
    public TextView price;
    public TextView desc;

    // End Of Content View Elements

    private void bindViews() {

        mView_viewimg_translate = (RelativeLayout) findViewById(R.id.view_viewimg_translate);
        img = (RoundImageView) findViewById(R.id.img_viewimg_src);
        price = (TextView) findViewById(R.id.tv_viewimg_price);
        desc = (TextView) findViewById(R.id.tv_viewimg_desc);
    }

}
