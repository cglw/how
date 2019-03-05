package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.v8.SpuInfoEntity;

/**
 * Created by chengang on 2017/6/30.
 */

public class UnitSelectAdapter extends SpuListAdapter {


    private int img_width;

    public UnitSelectAdapter(Context context) {
        super(context, R.layout.item_unit_select);
        img_width = (BaseApplication.ScreenWidth - AppUtil.dip2px(16) * 4) / 3;
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, SpuInfoEntity data, int data_position) {
        super.bindData(holder, data, data_position);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.getRootView().getLayoutParams();
        layoutParams.width = img_width;
        if ((data_position + 1) % 3 == 0) {
            layoutParams.setMargins(0, 0, 0, AppUtil.dip2px(8));

        } else {
            layoutParams.setMargins(0, 0, AppUtil.dip2px(8), AppUtil.dip2px(8));

        }
    }
}
