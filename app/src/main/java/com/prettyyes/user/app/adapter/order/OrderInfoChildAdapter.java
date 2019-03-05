package com.prettyyes.user.app.adapter.order;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.order.OrderInfo;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.order
 * Author: SmileChen
 * Created on: 2016/11/3
 * Description: Nothing
 */
public class OrderInfoChildAdapter extends SpecilAbsAdapter<OrderInfo.InfoEntity.MyListInfo> {
    public OrderInfoChildAdapter(Context context) {
        super(R.layout.item_lv_orderinfo_child, new ArrayList<OrderInfo.InfoEntity.MyListInfo>(), context);
    }

    // Content View Elements

    private ImageView img_uintimg;
    private TextView tv_uintname;
    private TextView tv_price;
    private TextView tv_oneuintnum;
    private TextView tv_title;
    private RelativeLayout rel_unit;
    private View line_orderinfochildAdp;

    @Override
    public void bindView(ViewHolder vHolder) {
        img_uintimg = (ImageView) vHolder.getView(R.id.img_orderinfochildAdp_uintimg);
        tv_uintname = (TextView) vHolder.getView(R.id.tv_orderinfochildAdp_uintname);
        tv_price = (TextView) vHolder.getView(R.id.tv_orderinfochildAdp_price);
        tv_oneuintnum = (TextView) vHolder.getView(R.id.tv_orderinfochildAdp_oneuintnum);
        tv_title = (TextView) vHolder.getView(R.id.tv_orderinfochildAdp_title);
        rel_unit = (RelativeLayout) vHolder.getView(R.id.rel_orderinfochildAdp_oneImg);
        line_orderinfochildAdp = vHolder.getView(R.id.line_orderinfochildAdp);
    }

    @Override
    public void showData(ViewHolder vHolder, final OrderInfo.InfoEntity.MyListInfo data, int position) {
        AppUtil.loadTypaeFace(context, tv_price);
        if (data.getType().equals("title")) {
            tv_title.setVisibility(View.VISIBLE);
            rel_unit.setVisibility(View.GONE);
            tv_title.setText(data.getSuit_name());
            line_orderinfochildAdp.setVisibility(View.GONE);

        } else {
            tv_title.setVisibility(View.GONE);
            rel_unit.setVisibility(View.VISIBLE);
            ImageLoadUtils.loadListimg(context, data.getImg(), img_uintimg);
            tv_uintname.setText(data.getName());
            tv_price.setText(Constant.RMB + data.getPrice());
            tv_oneuintnum.setText("X" + data.getNums());
            line_orderinfochildAdp.setVisibility(View.VISIBLE);
            rel_unit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpPageManager.getManager().goSkuActivity((Activity) context, data.getType(), data.getId() + "");

                }
            });

        }


    }
}
