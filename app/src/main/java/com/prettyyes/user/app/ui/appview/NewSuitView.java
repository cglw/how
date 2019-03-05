package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/24.
 */

public class NewSuitView extends AbsRelativelayoutView<SpuInfoEntity> {
    public NewSuitView(Context context) {
        super(context);
    }

    public NewSuitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_suit;
    }

    @Override
    public void initViews() {
        bindViews();

    }

    @Override
    public void setListener() {
        super.setListener();
        rel_look_detail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null) {
                    JumpPageManager.getManager().goSkuActivity(getContext(), data.getSpu_type(), data.getModule_id());
                }

            }
        });
        View parent = (View) mTv_name.getParent();
        parent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null) {
                    JumpPageManager.getManager().goSkuActivity(getContext(), data.getSpu_type(), data.getModule_id());
                }
            }
        });


        img_one.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBigImgActivity(getContext(), data.getUnit_list().get(0).getMain_img());

            }
        });
        img_two.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBigImgActivity(getContext(), data.getUnit_list().get(1).getMain_img());

            }
        });

    }

    @Override
    public void setData(SpuInfoEntity data) {
        super.setData(data);
        ImageLoadUtils.loadListimg(getContext(), data.getMain_img(), mImg_covery);
        mTv_name.setText(data.getSpu_name());
        mTv_price.setText(String.format("搭配购买价：¥%s", data.getSpu_price()));

        if (data.getUnit_list() == null || data.getUnit_list().size() == 0) {
            mLl_units.setVisibility(GONE);
            return;
        } else {
            mLl_units.setVisibility(VISIBLE);
        }

        List<SpuInfoEntity> unit_list = data.getUnit_list();
        tv_one_name.setText(unit_list.get(0).getSpu_name());
        tv_one_price.setText(unit_list.get(0).getSpu_price());
        ImageLoadUtils.loadListimg(getContext(), unit_list.get(0).getMain_img(), img_one);
        tv_two_name.setText(unit_list.get(1).getSpu_price());
        tv_two_price.setText(unit_list.get(1).getSpu_price());
        ImageLoadUtils.loadListimg(getContext(), unit_list.get(1).getMain_img(), img_two);


    }


    @Override
    public void setDataByModel(SpuInfoEntity data) {
    }


    public LinearLayout mLl_units;
    public ImageView mImg_covery;
    public TextView mTv_name;
    public TextView mTv_price;
    public View rel_look_detail;


    public TextView tv_one_price;
    public TextView tv_two_price;
    public TextView tv_one_name;
    public TextView tv_two_name;
    public ImageView img_one;
    public ImageView img_two;


    private void bindViews() {

        mLl_units = (LinearLayout) getView(R.id.ll_units);
        mImg_covery = (ImageView) getView(R.id.img_suit_covery);
        mTv_name = (TextView) getView(R.id.tv_suit_name);
        mTv_price = (TextView) getView(R.id.tv_suit_price);
        rel_look_detail = getView(R.id.rel_look_detail);

        tv_one_price = (TextView) getView(R.id.tv_one_price);
        tv_two_name = (TextView) getView(R.id.tv_two_name);
        tv_one_name = (TextView) getView(R.id.tv_one_name);
        tv_two_price = (TextView) getView(R.id.tv_two_price);
        img_one = (ImageView) getView(R.id.img_one);
        img_two = (ImageView) getView(R.id.img_two);

    }


}
