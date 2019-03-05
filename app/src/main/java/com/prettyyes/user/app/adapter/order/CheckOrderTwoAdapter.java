package com.prettyyes.user.app.adapter.order;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.mvpPresenter.OrderCheckPresenter;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.order
 * Author: SmileChen
 * Created on: 2016/10/27
 * Description: Nothing
 */
public class CheckOrderTwoAdapter extends SpecilAbsAdapter<OrderCheckPresenter.ListOne.ListTwo> {

    public CheckOrderTwoAdapter(ArrayList datas, Context context) {
        super(R.layout.item_lv_checkorder_two, datas, context);
    }


    @Override
    public void showData(ViewHolder vHolder, final OrderCheckPresenter.ListOne.ListTwo data, int position) {
        AppUtil.loadTypaeFace(context, tv_checkorderAdpthree_price);

        if (data.getType().equals("title")) {
            tv_checkorderAdptwo_title.setText(data.getSuit_name());
            rel_checkorderAdptwo_bottom.setVisibility(View.GONE);
            tv_checkorderAdptwo_title.setVisibility(View.VISIBLE);
            line_checkorderAdpthree.setVisibility(View.GONE);
        } else {
            rel_checkorderAdptwo_bottom.setVisibility(View.VISIBLE);
            tv_checkorderAdptwo_title.setVisibility(View.GONE);
            tv_checkorderAdpthree_price.setText(data.getPrice());
            ImageLoadUtils.loadListimg(context, data.getImg(), img_checkorderAdpthree_uintimg);
            tv_checkorderAdpthree_num.setText("X" + data.getNum() + "");
            tv_checkorderAdpthree_price.setText(Constant.RMB + data.getPrice());
            tv_checkorderAdpthree_uintname.setText(data.getUnit_name());
            line_checkorderAdpthree.setVisibility(View.VISIBLE);
            rel_checkorderAdptwo_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpPageManager.getManager().goSkuActivity((Activity) context, data.getType(), data.getId() + "");

                }
            });
        }


    }

    // Content View Elements

    private TextView tv_checkorderAdptwo_title;
    private ImageView img_checkorderAdpthree_uintimg;
    private TextView tv_checkorderAdpthree_uintname;
    private TextView tv_checkorderAdpthree_price;
    private TextView tv_checkorderAdpthree_num;
    private RelativeLayout rel_checkorderAdptwo_bottom;
    private View line_checkorderAdpthree;

    // End Of Content View Elements

    public void bindView(ViewHolder vHolder) {

        tv_checkorderAdptwo_title = (TextView) vHolder.getView(R.id.tv_checkorderAdptwo_title);
        img_checkorderAdpthree_uintimg = (ImageView) vHolder.getView(R.id.img_checkorderAdpthree_uintimg);
        tv_checkorderAdpthree_uintname = (TextView) vHolder.getView(R.id.tv_checkorderAdpthree_uintname);
        tv_checkorderAdpthree_price = (TextView) vHolder.getView(R.id.tv_checkorderAdpthree_price);
        tv_checkorderAdpthree_num = (TextView) vHolder.getView(R.id.tv_checkorderAdpthree_num);
        rel_checkorderAdptwo_bottom = (RelativeLayout) vHolder.getView(R.id.rel_checkorderAdptwo_bottom);
        line_checkorderAdpthree = vHolder.getView(R.id.line_checkorderAdpthree);
    }
}
