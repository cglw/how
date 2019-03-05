package com.prettyyes.user.app.adapter.order;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.SellerOrderInfo;

/**
 * Created by chengang on 2017/7/27.
 */

public class SellerInnerOrderAdapter extends AbsRecycleAdapter<SellerOrderInfo.UnitListBean> {
    public SellerInnerOrderAdapter(Context context) {
        super(context, R.layout.item_rv_seller_orderinfo);

    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final SellerOrderInfo.UnitListBean unitListBean, int position) {
        rel_orderinfo_top.setVisibility(View.GONE);
        rel_orderinfo_bottom.setVisibility(View.GONE);

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSkuActivity(context,unitListBean.getSpu_type(),unitListBean.getModule_id()+"");
            }
        });

        View parent = (View) rel_orderinfo_bottom.getParent();
        parent.setPadding(0, 0, 0, 0);

        ImageLoadUtils.loadListimg(context, unitListBean.getMain_img(), img_orderinfo_uintimg);
        tv_orderinfo_number.setText(String.format("X%d", unitListBean.getNum()));
        tv_orderinfo_price.setText(String.format(context.getString(R.string.format_rmb), unitListBean.getPrice()));
        tv_orderinfo_uintname.setText(unitListBean.getSpu_name());

        btn_orderinfo.setVisibility(View.VISIBLE);
    }


    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        rel_orderinfo_top = holder.getView(R.id.rel_orderinfo_top);
        tv_left = holder.getView(R.id.tv_left);
        line_orderinfochildAdp = holder.getView(R.id.line_orderinfochildAdp);
        rel_orderinfo_center = holder.getView(R.id.rel_orderinfo_center);
        img_orderinfo_uintimg = holder.getView(R.id.img_orderinfo_uintimg);
        tv_orderinfo_uintname = holder.getView(R.id.tv_orderinfo_uintname);
        tv_orderinfo_price = holder.getView(R.id.tv_orderinfo_price);
        tv_orderinfo_number = holder.getView(R.id.tv_orderinfo_number);
        rel_orderinfo_bottom = holder.getView(R.id.rel_orderinfo_bottom);
        btn_orderinfo = holder.getView(R.id.btn_orderinfo);
        tv_orderinfo_ordernumber = holder.getView(R.id.tv_orderinfo_ordernumber);
        tv_orderinfo_orderstatus = holder.getView(R.id.tv_orderinfo_orderstatus);

    }


    private RelativeLayout rel_orderinfo_top;
    private TextView tv_left;
    private View line_orderinfochildAdp;
    private RelativeLayout rel_orderinfo_center;
    private ImageView img_orderinfo_uintimg;
    private TextView tv_orderinfo_uintname;
    private TextView tv_orderinfo_price;
    private TextView tv_orderinfo_number;
    private TextView tv_orderinfo_ordernumber;
    private TextView tv_orderinfo_orderstatus;
    private RelativeLayout rel_orderinfo_bottom;
    private Button btn_orderinfo;
}
