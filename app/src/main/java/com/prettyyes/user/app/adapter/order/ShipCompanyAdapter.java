package com.prettyyes.user.app.adapter.order;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.event.SelectShipCompanyEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.v8.ShipEntity;

/**
 * Created by chengang on 2017/6/13.
 */

public class ShipCompanyAdapter extends AbsRecycleAdapter<ShipEntity> {

    public ShipCompanyAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final ShipEntity shipCompany, int position) {
        tv.setText(shipCompany.getExpress_value());
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppRxBus.getInstance().post(new SelectShipCompanyEvent(shipCompany.getExpress_value(),shipCompany.getExpress_key()));
                ((Activity) context).finish();

            }
        });
    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        tv = holder.getView(android.R.id.text1);
    }

    private TextView tv;
}
