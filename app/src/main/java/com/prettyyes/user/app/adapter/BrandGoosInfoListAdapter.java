package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.view.dialog.MyBottomDialog;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/9/15.
 */

public class BrandGoosInfoListAdapter extends SpuListAdapter {
    public BrandGoosInfoListAdapter(Context context) {
        super(context, R.layout.item_rv_brand_goods_list);
        bottom_item = new ArrayList<>();
        bottom_item.add("选择");
        bottom_item.add("查看");
        bottom_item.add("取消");
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, SpuInfoEntity data, final int data_position) {
        super.bindData(holder, data, data_position);


        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.getRootView().getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);

        tv_brand_name.setText(data.getBrand_name());
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottom_item == null || bottom_item.size() <= 0)
                    return;
                final SpuInfoEntity data = (SpuInfoEntity) v.getTag();
                final MyBottomDialog myBottomDialog = new MyBottomDialog(context);
                myBottomDialog.show();
                myBottomDialog.getBottomDialogAdapter().addAll(bottom_item);

                myBottomDialog.getBottomDialogAdapter().setMyOnItemClickListener(new OnMyItemClickListener<String>() {
                    @Override
                    public void clickItem(AbsRecycleViewHolder holder, View view, String o, int position) {
                        myBottomDialog.dismiss();
                        if (o.equals("查看")) {
                            JumpPageManager.getManager().goSkuActivity(context, data.getSpu_type(), data.getModule_id());
                        } else if (o.equals("选择")) {
                            AppRxBus.getInstance().post(new AddTemplateOrSelectSuccessEvent(data.getModule_id(), data.getSpu_type()));

                            JumpPageManager.getManager().goKolReplyActivity(context);

                        }


                    }
                });

            }
        });

    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        super.bindView(holder);
        tv_brand_name = holder.getView(R.id.tv_brand_name);

    }

    private TextView tv_brand_name;
}
