package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ChangeUnitNumEvent;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

/**
 * Created by chengang on 2017/7/3.
 */

public class UnitListInSuitAdapter extends AbsRecycleAdapter<SpuInfoEntity> {

    public UnitListInSuitAdapter(Context context) {
        super(context, R.layout.item_rv_suitdel_suit);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final SpuInfoEntity data, int position) {
        if (position == 0)
            view_line.setVisibility(View.GONE);
        else
            view_line.setVisibility(View.VISIBLE);

        tv_price.setText(StringUtils.getPrice(data.getSpu_price()));
        tv_uintname.setText(data.getSpu_name());

        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_uintimg);
        TextView tv_remove = (TextView) lin_select_num.getChildAt(0);
        TextView tv_num = (TextView) lin_select_num.getChildAt(1);
        TextView tv_add = (TextView) lin_select_num.getChildAt(2);
        setNum(tv_num, data.getNum());
        tv_remove.setTag(data);
        tv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) v.getParent();
                TextView childAt = (TextView) linearLayout.getChildAt(1);
                SpuInfoEntity tag = (SpuInfoEntity) v.getTag();
                if (tag.getNum() >= 1) {
                    tag.setNum(tag.getNum() - 1);
                    setNum(childAt, tag.getNum());
                }

                AppRxBus.getInstance().post(new ChangeUnitNumEvent(getTotalPrice()));

            }
        });
        tv_add.setTag(data);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) v.getParent();
                TextView childAt = (TextView) linearLayout.getChildAt(1);
                SpuInfoEntity tag = (SpuInfoEntity) v.getTag();
                tag.setNum(tag.getNum() + 1);
                setNum(childAt, tag.getNum());
                AppRxBus.getInstance().post(new ChangeUnitNumEvent(getTotalPrice()));
            }
        });

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpPageManager.getManager().goSkuActivity(context, data.getSpu_type(), data.getModule_id());
            }
        });
    }

    private double getTotalPrice() {
        double price = 0;
        for (int i = 0; i < UnitListInSuitAdapter.this.getItems().size(); i++) {
            price += UnitListInSuitAdapter.this.getItems().get(i).getNum() * Double.parseDouble(UnitListInSuitAdapter.this.getItems().get(i).getSpu_price());

        }
        return price;
    }


    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        view_line = holder.getView(R.id.view_line);
        img_uintimg = holder.getView(R.id.img_uintimg);
        lin_select_num = holder.getView(R.id.lin_select_num);
        tv_uintname = holder.getView(R.id.tv_uintname);
        tv_price = holder.getView(R.id.tv_price);
    }

    private void setNum(TextView tv, int num) {
        tv.setText(String.format("%d", num));

    }

    private View view_line;
    private ImageView img_uintimg;
    private LinearLayout lin_select_num;
    private TextView tv_uintname;
    private TextView tv_price;

}
