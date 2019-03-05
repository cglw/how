package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

/**
 * Created by chengang on 2017/7/24.
 */

public class NewTaobaoView extends AbsRelativelayoutView<SpuInfoEntity> {
    public NewTaobaoView(Context context) {
        super(context);
    }

    public NewTaobaoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_taobao;
    }

    @Override
    public void initViews() {
        bindViews();
    }

    @Override
    public void setListener() {
        super.setListener();
        ((View) mImg_covey.getParent()).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null)
                    JumpPageManager.getManager().goSkuActivity(getContext(), data.getSpu_type(), data.getModule_id());
            }
        });
    }

    @Override
    public void setDataByModel(SpuInfoEntity data) {

    }

    @Override
    public void setData(SpuInfoEntity data) {
        super.setData(data);
        ImageLoadUtils.loadListimg(getContext(), data.getMain_img(), mImg_covey);
        mTv_name.setText(data.getSpu_name());
        mTv_price.setText(data.getSpu_price());
    }

    public ImageView mImg_covey;
    public TextView mTv_name;
    public TypefaceTextView mTv_price;


    private void bindViews() {

        mImg_covey = (ImageView) findViewById(R.id.img_taobao_covey);
        mTv_name = (TextView) findViewById(R.id.tv_taobao_name);
        mTv_price = (TypefaceTextView) findViewById(R.id.tv_taobao_price);
    }


}
