package com.prettyyes.user.app.adapter.order;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.order.OrderList;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.order
 * Author: SmileChen
 * Created on: 2016/11/3
 * Description: Nothing
 */
public class OrderListChildAdapter extends SpecilAbsAdapter<OrderList.ListEntity.OrderChildEntity> {

    private int height = 0;
    private OrderCallback ordercallback;
    private int index;
    private static final String TAG = "OrderListChildAdapter";

    public OrderListChildAdapter(int index, ArrayList datas, Context context, OrderCallback ordercallback, int height) {
        super(R.layout.item_lv_orderlist_child, datas, context);

        //外层listview 左右margin 10*2  内部padding  左右10  然后内部margin 8
        //height = (BaseApplication.ScreenWidth - DensityUtil.dip2px(40 + 8 * 3)) / 4 + DensityUtil.dip2px(20);
        this.ordercallback = ordercallback;
        this.index = index;
        this.height = height;
    }

    @Override
    public void bindView(ViewHolder vHolder) {
        tv_ordernum = (TextView) vHolder.getView(R.id.tv_orderlistchildAdp_ordernum);
        rel_oneImg = (RelativeLayout) vHolder.getView(R.id.rel_orderlistchildAdp_oneImg);
        img_uintimg = (ImageView) vHolder.getView(R.id.img_orderlistchildAdp_uintimg);
        tv_uintname = (TextView) vHolder.getView(R.id.tv_orderlistchildAdp_uintname);
        tv_uintprice = (TextView) vHolder.getView(R.id.tv_orderlistchildAdp_price);
        tv_ordertotalnum = (TextView) vHolder.getView(R.id.tv_orderlistchildAdp_ordertotalnum);
        tv_totalprice = (TextView) vHolder.getView(R.id.tv_orderlistchildAdp_totalprice);
        tv_oneuintnum = (TextView) vHolder.getView(R.id.tv_orderlistchildAdp_oneuintnum);
        tv_orderState = (TextView) vHolder.getView(R.id.tv_orderlistchildAdp_orderState);
        lin_common_fourImgs = (LinearLayout) vHolder.getView(R.id.lin_common_fourImgs);
        btn_left = (Button) vHolder.getView(R.id.btn_orderlistchildAdp_left);
        btn_right = (Button) vHolder.getView(R.id.btn_orderlistchildAdp_right);
    }

    @Override
    public void showData(ViewHolder vHolder, OrderList.ListEntity.OrderChildEntity data, int position) {
        AppUtil.loadTypaeFace(context, tv_totalprice);
        AppUtil.loadTypaeFace(context, tv_uintprice);

        tv_ordernum.setText(data.getOrder_number());
        tv_totalprice.setText(Constant.RMB + data.getTotal_price());
        tv_ordertotalnum.setText("共" + data.getUnit_num() + "件商品");
        handlerOrderState(data, position);

        if (data.getUnit_list() == null) {
            vHolder.getRootView().setVisibility(View.GONE);
            return;
        } else {
            vHolder.getRootView().setVisibility(View.VISIBLE);

        }
        if (data.getUnit_list() != null && data.getUnit_list().size() > 1) {
            lin_common_fourImgs.setVisibility(View.VISIBLE);
            rel_oneImg.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) lin_common_fourImgs.getLayoutParams();
            layoutParams.height = height;
            lin_common_fourImgs.setPadding(DensityUtil.dip2px(14), DensityUtil.dip2px(10), DensityUtil.dip2px(14), DensityUtil.dip2px(10));
            for (int j = 0; j < lin_common_fourImgs.getChildCount(); j++) {
                ImageView roundImageView = (ImageView) lin_common_fourImgs.getChildAt(j);
                roundImageView.setVisibility(View.GONE);
            }
            for (int i = 0; i < data.getUnit_list().size(); i++) {
                if (i == 4) {
                    return;
                }
                ImageView iamge = (ImageView) lin_common_fourImgs.getChildAt(i);
                iamge.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams la = (LinearLayout.LayoutParams) iamge.getLayoutParams();
                la.height = height - DensityUtil.dip2px(20);
                la.width = la.height;
                if (i == 3) {
                    la.setMargins(0, 0, 0, 0);
                } else {
                    la.setMargins(0, 0, DensityUtil.dip2px(8), 0);
                }
                ImageLoadUtils.loadListimg(context, data.getUnit_list().get(i).getUnit_img(), iamge);
            }
        } else if (data.getUnit_list().size() == 1) {
            lin_common_fourImgs.setVisibility(View.GONE);
            rel_oneImg.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rel_oneImg.getLayoutParams();
            layoutParams.height = height;
            RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) img_uintimg.getLayoutParams();
            layoutParams1.height = height - DensityUtil.dip2px(20);
            layoutParams1.width = layoutParams1.height;
            ImageLoadUtils.loadListimg(context, data.getUnit_list().get(0).getUnit_img(), img_uintimg);

            tv_uintprice.setText(Constant.RMB + data.getUnit_list().get(0).getPrice());
            tv_uintname.setText(data.getUnit_list().get(0).getUnit_name());
            tv_oneuintnum.setText("X" + data.getUnit_list().get(0).getUnit_nums() + "");
        }

    }

    private void handlerOrderState(final OrderList.ListEntity.OrderChildEntity data, final int position) {

        btn_left.setVisibility(View.GONE);
        btn_right.setVisibility(View.GONE);
        if (data.getAgree_cancel().equals("1")) {
            tv_orderState.setText("退款中");
        } else if (data.getAgree_cancel().equals("2")) {
            tv_orderState.setText("已退款");
        } else if (data.getOrder_status().equals("0")) {
            tv_orderState.setText("已完成");
        } else if (data.getOrder_status().equals("3")) {
            tv_orderState.setText("已完成");
            if (data.getIs_comment().equals("0")) {
                tv_orderState.setText("待评价");
                btn_right.setVisibility(View.VISIBLE);
                btn_right.setText("评价");
            }

        } else if (data.getOrder_status().equals("1")) {
            tv_orderState.setText("待支付");
            btn_left.setVisibility(View.GONE);
            btn_right.setVisibility(View.GONE);

        } else if (data.getOrder_status().equals("2") && data.getShip_status().equals("0")&& data.getAgree_cancel().equals("0")) {
            tv_orderState.setText("待发货");
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setText("申请退款");
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setText("提醒发货");

        } else if (data.getOrder_status().equals("2")&& data.getShip_status().equals("0") && data.getAgree_cancel().equals("3")) {
            tv_orderState.setText("退款中");
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setText("查看退款");
            btn_right.setVisibility(View.GONE);
            btn_right.setText("提醒发货");
        } else if (data.getOrder_status().equals("2") && data.getShip_status().equals("1")) {
            tv_orderState.setText("已发货");
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setText("查看物流");
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setText("确认收货");

        } else if (data.getOrder_status().equals("2") && data.getShip_status().equals("2")) {
            tv_orderState.setText("已到货");
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setText("查看物流");
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setText("确认收货");
        } else {
            tv_orderState.setText("");
        }
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordercallback != null) {
                    String tv = ((Button) v).getText().toString();

                    switch (tv) {
                        case "申请退款":
                            ordercallback.backmoney(data.getOrder_number(), data.getSeller_id() + "");
                            break;
                        case "查看物流":
                            ordercallback.lookship(data.getOrder_number());
                            break;
                        case "查看退款":
                            ordercallback.backmoney(data.getOrder_number(), data.getSeller_id() + "");

                            break;

                    }

                }

            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordercallback != null) {
                    if (((Button) v).getText().toString().equals("提醒发货")) {
                        ordercallback.notiySend(data.getOrder_number());
                    } else if (((Button) v).getText().toString().equals("确认收货")) {
                        ordercallback.confirmReceive(data.getOrder_number());
                    } else if (((Button) v).getText().toString().equals("评价")) {
                        ordercallback.commentOrder(data.getOrder_number());
                    }
                }
            }
        });


    }

    private TextView tv_ordernum;
    private TextView tv_oneuintnum;
    private RelativeLayout rel_oneImg;
    private ImageView img_uintimg;
    private TextView tv_uintname;
    private TextView tv_uintprice;
    private TextView tv_ordertotalnum;
    private TextView tv_totalprice;
    private TextView tv_orderState;
    private LinearLayout lin_common_fourImgs;
    private Button btn_left;
    private Button btn_right;

    public interface OrderCallback {
        public void pay(String ordernumber);

        public void cancelPay(String ordernumber);

        public void notiySend(String ordernumber);

        public void backmoney(String ordernumber, String seller_id);

        public void commentOrder(String ordernumber);

        public void lookship(String ordernumber);

        public void confirmReceive(String ordernumber);


    }

}
