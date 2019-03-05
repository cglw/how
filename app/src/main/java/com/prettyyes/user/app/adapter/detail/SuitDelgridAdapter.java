package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.core.event.ChangeUnitNumEvent;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.Suit.SuitDetailEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter
 * Author: SmileChen
 * Created on: 2016/8/17
 * Description: Nothing
 */
public class SuitDelgridAdapter extends SpecilAbsAdapter<SuitDetailEntity.SuitEntity.UnitListEntity> {


    public SuitDelgridAdapter(Context context, ArrayList<SuitDetailEntity.SuitEntity.UnitListEntity> datas) {
        super(R.layout.item_rv_suitdel_suit, datas, context);


    }


    private View view_line;
    private ImageView img_uintimg;
    private LinearLayout lin_select_num;
    private TextView tv_uintname;
    private TextView tv_price;

    @Override
    public void bindView(ViewHolder vHolder) {
        view_line = vHolder.getView(R.id.view_line);
        img_uintimg = (ImageView) vHolder.getView(R.id.img_uintimg);
        lin_select_num = (LinearLayout) vHolder.getView(R.id.lin_select_num);
        tv_uintname = (TextView) vHolder.getView(R.id.tv_uintname);
        tv_price = (TextView) vHolder.getView(R.id.tv_price);

    }

    @Override
    public void showData(ViewHolder vHolder, final SuitDetailEntity.SuitEntity.UnitListEntity data, int position) {

        if (position == 0)
            view_line.setVisibility(View.GONE);
        else
            view_line.setVisibility(View.VISIBLE);

        tv_price.setText(Constant.RMB + data.getPrice());
        tv_uintname.setText(data.getUnit_name());

        ImageLoadUtils.loadListimg(context, data.getImg(), img_uintimg);
        TextView tv_remove = (TextView) lin_select_num.getChildAt(0);
        TextView tv_num = (TextView) lin_select_num.getChildAt(1);
        TextView tv_add = (TextView) lin_select_num.getChildAt(2);
        tv_num.setText(data.getNum() + "");
        tv_remove.setTag(data);
        tv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) v.getParent();
                TextView childAt = (TextView) linearLayout.getChildAt(1);
                SuitDetailEntity.SuitEntity.UnitListEntity tag = (SuitDetailEntity.SuitEntity.UnitListEntity) v.getTag();
                if (tag.getNum() >= 1) {
                    tag.setNum(tag.getNum() - 1);
                    childAt.setText(tag.getNum() + "");
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
                SuitDetailEntity.SuitEntity.UnitListEntity tag = (SuitDetailEntity.SuitEntity.UnitListEntity) v.getTag();
                tag.setNum(tag.getNum() + 1);
                childAt.setText(tag.getNum() + "");
                AppRxBus.getInstance().post(new ChangeUnitNumEvent(getTotalPrice()));
            }
        });
    }

    private double getTotalPrice() {
        double price = 0;
        for (int i = 0; i < SuitDelgridAdapter.this.datas.size(); i++) {
            price += SuitDelgridAdapter.this.get(i).getNum() * Double.parseDouble(SuitDelgridAdapter.this.get(i).getPrice());

        }
        return price;
    }

}
