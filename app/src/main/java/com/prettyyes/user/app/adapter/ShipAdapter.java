package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.model.order.OrderShipInfo;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter
 * Author: SmileChen
 * Created on: 2016/8/23
 * Description: Nothing
 */
public class ShipAdapter extends SpecilAbsAdapter<OrderShipInfo.InfoEntity> {
    public ShipAdapter(int layoutRes, ArrayList<OrderShipInfo.InfoEntity> datas, Context context) {
        super(R.layout.item_lv_shiplist, datas);
        this.context = context;
    }

    @Override
    public void bindView(ViewHolder vHolder) {

    }

    @Override
    public void showData(ViewHolder vHolder, OrderShipInfo.InfoEntity data, int position) {
        bindViews(vHolder);
        if (position == 0) {
            lin_shipAdp_typelast.setVisibility(View.VISIBLE);
            lin_shipAdp_typenormal.setVisibility(View.GONE);
            tv_shipAdp_last_info.setText(Html.fromHtml(data.getContext()));
            tv_shipAdp_last_time.setText(data.getTime());
        } else {
            lin_shipAdp_typelast.setVisibility(View.GONE);
            lin_shipAdp_typenormal.setVisibility(View.VISIBLE);
            tv_shipAdp_normal_info.setText(Html.fromHtml(data.getContext()));
            tv_shipAdp_normal_time.setText(data.getTime());
            //StringUtils.getPhone(data.getContext());
            //tv_shipAdp_normal_info.setText(getClickableSpan());

            //此行必须有
            // tv_shipAdp_normal_info.setMovementMethod(LinkMovementMethod.getInstance());

        }

    }

    private LinearLayout lin_shipAdp_typelast;
    private RelativeLayout rel_ship_last;
    private TextView tv_shipAdp_last_info;
    private TextView tv_shipAdp_last_time;
    private LinearLayout lin_shipAdp_typenormal;
    private TextView tv_shipAdp_normal_info;
    private TextView tv_shipAdp_normal_time;

    // End Of Content View Elements

    private void bindViews(ViewHolder viewHolder) {

        lin_shipAdp_typelast = (LinearLayout) viewHolder.getView(R.id.lin_shipAdp_typelast);
        rel_ship_last = (RelativeLayout) viewHolder.getView(R.id.rel_ship_last);
        tv_shipAdp_last_info = (TextView) viewHolder.getView(R.id.tv_shipAdp_last_info);
        tv_shipAdp_last_time = (TextView) viewHolder.getView(R.id.tv_shipAdp_last_time);
        lin_shipAdp_typenormal = (LinearLayout) viewHolder.getView(R.id.lin_shipAdp_typenormal);
        tv_shipAdp_normal_info = (TextView) viewHolder.getView(R.id.tv_shipAdp_normal_info);
        tv_shipAdp_normal_time = (TextView) viewHolder.getView(R.id.tv_shipAdp_normal_time);
    }

    private SpannableString getClickableSpan(String tv) {

        //监听器
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseApplication.getAppContext(), "Click Success", Toast.LENGTH_SHORT).show();
            }
        };

        SpannableString spanableInfo = new SpannableString(tv);
        int start = 2;  //超链接起始位置
        int end = 11;   //超链接结束位置

        //可以为多部分设置超链接
        spanableInfo.setSpan(new Clickable(listener), start, end, Spanned.SPAN_MARK_MARK);
        spanableInfo.setSpan(new Clickable(listener), 14, 18, Spanned.SPAN_MARK_MARK);

        return spanableInfo;
    }
}

class Clickable extends ClickableSpan implements View.OnClickListener {
    private final View.OnClickListener mListener;

    public Clickable(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view);
    }

}
