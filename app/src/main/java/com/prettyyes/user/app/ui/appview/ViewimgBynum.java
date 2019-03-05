package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/3/27.
 */

public class ViewimgBynum extends AbsLinearlayoutView<SpuInfoEntity> {
    private static final String TAG = "ViewimgBynum";

    public ViewimgBynum(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewimgBynum(Context context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_img_by_num;
    }

    private LinearLayout ll_imgs;


    @Override
    public void initViews() {
        ll_imgs =this;


    }


    @Override
    public void setData(SpuInfoEntity data) {
        super.setData(data);
        setDataByModel(data);
    }

    List<String> imgs;
    int max_size = 0;

    @Override
    public void setDataByModel(final SpuInfoEntity data) {
        if (imgs == null)
            imgs = new ArrayList<>();
        if (data.getImg_arr() == null || data.getImg_arr().size() <= 0) {
            imgs.add(data.getMain_img());
        } else
            imgs.addAll(data.getImg_arr());
        max_size = imgs.size();
        if (max_size > 3)
            max_size = 3;

        for (int i = 0; i < ll_imgs.getChildCount(); i++) {
            RoudImageBuyView childAt = (RoudImageBuyView) ll_imgs.getChildAt(i);

            if (i == max_size - 1) {
                childAt.showBuy();
            } else {
                childAt.hideBuy();
            }

            if (i > max_size - 1) {
                childAt.setVisibility(GONE);
            } else {
                childAt.setVisibility(VISIBLE);
                childAt.desc.setText(data.getSpu_name());
                childAt.price.setText(StringUtils.getPrice(data.getSpu_price()));
                ImageLoadUtils.loadListimg(getContext(), imgs.get(i), childAt.img);
            }


            childAt.setTag(i);
            childAt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!((RoudImageBuyView) v).showbg) {
                        JumpPageManager.getManager().goBigImgActivity(getContext(), (ArrayList<String>) imgs, (Integer) v.getTag());
                    } else
                        JumpPageManager.getManager().goSkuActivity(getContext(), data.getSpu_type(), data.getModule_id());
                }
            });

        }

        for (int i = 0; i < max_size - 1; i++) {
            LinearLayout.LayoutParams layoutParams = (LayoutParams) ll_imgs.getChildAt(i).getLayoutParams();
            layoutParams.setMargins(0, 0, AppUtil.dip2px(8), 0);
        }


    }


}
