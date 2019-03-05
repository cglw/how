package com.prettyyes.user.app.adapter.order;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.model.order.OrderList;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.order
 * Author: SmileChen
 * Created on: 2016/11/3
 * Description: Nothing
 */
public class OrderListAdapter extends SpecilAbsAdapter<OrderList.ListEntity> {


    private OrderListChildAdapter.OrderCallback ordercallback;
    private int innerHeight = 0;//子listview中间图片布局的高度

    public OrderListAdapter(Context context, OrderListChildAdapter.OrderCallback ordercallback) {
        super(R.layout.item_lv_orderlist_parent, new ArrayList<OrderList.ListEntity>(), context);
        this.ordercallback = ordercallback;
        //10是左右的margin  14内部左右padding  4是阴影 8是每个图片之间的margin
        innerHeight = (BaseApplication.ScreenWidth - DensityUtil.dip2px(10 * 2 + 14 * 2 + 6 * 2 + 8 * 3)) / 4 + DensityUtil.dip2px(20);
    }

    @Override
    public void bindView(ViewHolder vHolder) {
        tv_orderParentAdp_ordernum = (TextView) vHolder.getView(R.id.tv_orderParentAdp_ordernum);
        lv_orderParentAdp_orderchild = (ListView) vHolder.getView(R.id.lv_orderParentAdp_orderchild);
        btn_do = (Button) vHolder.getView(R.id.btn_orderParentAdp_dosomething);
    }

    private TextView tv_orderParentAdp_ordernum;
    private ListView lv_orderParentAdp_orderchild;
    private Button btn_do;
    @Override
    public void showData(ViewHolder vHolder, final OrderList.ListEntity data, final int position) {
        tv_orderParentAdp_ordernum.setText("订单:" + data.getOrder_number_parent());
        lv_orderParentAdp_orderchild.setAdapter(new OrderListChildAdapter(position, (ArrayList) data.getOrder_child(), context, ordercallback, innerHeight));

        if (data.getOrder_status().equals("1")) {
            btn_do.setVisibility(View.VISIBLE);
        } else {
            btn_do.setVisibility(View.GONE);
        }

        lv_orderParentAdp_orderchild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String order_number = data.getOrder_child().get(position).getOrder_number();

                if (data.getOrder_child().get(position).getAgree_cancel().equals("3")) {
                    JumpPageManager.getManager().goRefundActivityActivity(context, order_number);

                } else
                    JumpPageManager.getManager().goMyOrderInfoActivity(context, order_number);
            }
        });
        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordercallback != null) {
                    ordercallback.pay(data.getOrder_number_parent());
                }
            }
        });

    }

}
