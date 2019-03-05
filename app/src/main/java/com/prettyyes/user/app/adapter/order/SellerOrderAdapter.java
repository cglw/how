package com.prettyyes.user.app.adapter.order;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.RemindshipRequest;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddShipSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.SellerOrderInfo;

import java.util.List;

import static com.prettyyes.user.core.containter.JumpPageManager.SELLER;

/**
 * Created by chengang on 2017/7/27.
 */

public class SellerOrderAdapter extends AbsRecycleAdapter<SellerOrderInfo> {
    public SellerOrderAdapter(Context context) {
        super(context, R.layout.item_rv_seller_orderinfo);
//        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().toObservable(AddShipSuccessEvent.class).subscribe(new RxAction1<AddShipSuccessEvent>() {
//            @Override
//            public void callback(AddShipSuccessEvent addShipSuccessEvent) {
//                for (int i = 0; i < SellerOrderAdapter.this.getDataCount(); i++) {
//                    if (SellerOrderAdapter.this.getItemData(i).getOrder_id().equals(addShipSuccessEvent.getOrder_number())) {
//                        SellerOrderAdapter.this.getItemData(i).setShip_status(1);
//                        SellerOrderAdapter.this.notifyItemChanged(i);
//                        break;
//
//                    }
//                }
//            }
//        });
        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AddShipSuccessEvent>() {
            @Override
            protected void back(AddShipSuccessEvent addShipSuccessEvent) {
                for (int i = 0; i < SellerOrderAdapter.this.getDataCount(); i++) {
                    if (SellerOrderAdapter.this.getItemData(i).getOrder_id().equals(addShipSuccessEvent.getOrder_number())) {
                        SellerOrderAdapter.this.getItemData(i).setShip_status(1);
                        SellerOrderAdapter.this.notifyItemChanged(i);
                        break;

                    }
                }
            }
        });
    }


    private String getOrderState(int order_status, int ship_status, int agree_cancel) {
        String status = "";
        if (order_status == 1 && ship_status == 0 && agree_cancel == 0) {
            status = "未付款";
        } else if (order_status == 2 && ship_status == 0 && agree_cancel == 0) {
            status = "已付款";
        } else if (order_status == 2 && ship_status == 1 && agree_cancel == 0) {
            status = "发货中";
        } else if (order_status == 3 && ship_status == 2 && agree_cancel == 0) {
            status = "已完成";
        } else if (order_status == 2 && ship_status == 0 && agree_cancel == 3) {
            status = "用户申请退款中";
        } else if (order_status == 2 && ship_status == 0 && agree_cancel == 1) {
            status = "退款中";
        } else if (order_status == 2 && ship_status == 0 && agree_cancel == 2) {
            status = "已退款";
        }
        return status;
    }

    private String getOrderBtn(int order_status, int ship_status, int agree_cancel) {
        String status = "";
        if (order_status == 1 && ship_status == 0 && agree_cancel == 0) {
            status = "提醒付款";
        } else if (order_status == 2 && ship_status == 0 && agree_cancel == 0) {
            status = "输入运单";
        } else if (order_status == 2 && ship_status == 1 && agree_cancel == 0) {
            status = "修改运单";
        } else if (order_status == 2 && ship_status == 0 && agree_cancel == 3) {
            status = "操作退款";
        }
        return status;
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final SellerOrderInfo orderListEntity, int position) {
        tv_orderinfo_ordernumber.setText(orderListEntity.getOrder_number());


        tv_orderinfo_orderstatus.setText(getOrderState(orderListEntity.getOrder_status(), orderListEntity.getShip_status(), orderListEntity.getAgree_cancel()));


        List<SellerOrderInfo.UnitListBean> unit_list = orderListEntity.getUnit_list();
        String unit_img = "";
        String price = "";
        String unit_name = "";
        if (unit_list != null && unit_list.size() > 0) {

            unit_img = unit_list.get(0).getMain_img();
            price = orderListEntity.getUnit_list().get(0).getPrice();
            unit_name = orderListEntity.getUnit_list().get(0).getSpu_name();
        }

        ImageLoadUtils.loadListimg(context, unit_img, img_orderinfo_uintimg);
        tv_orderinfo_number.setText(String.format("X%d", orderListEntity.getUnit_num()));
        tv_orderinfo_price.setText(String.format(context.getString(R.string.format_rmb), price));
        tv_orderinfo_uintname.setText(unit_name);
        btn_orderinfo.setVisibility(View.GONE);


        btn_orderinfo.setText(getOrderBtn(orderListEntity.getOrder_status(), orderListEntity.getShip_status(), orderListEntity.getAgree_cancel()));

        if (btn_orderinfo.getText().toString().length() < 1)
            btn_orderinfo.setVisibility(View.GONE);
        else
            btn_orderinfo.setVisibility(View.VISIBLE);

        switch (btn_orderinfo.getText().toString()) {

            case "提醒付款":
                btn_orderinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new RemindshipRequest().setOrder_number(orderListEntity.getOrder_number()).post(new NetReqCallback<Object>() {
                            @Override
                            public void apiRequestFail(String message, String method) {
                                AppUtil.showToastShort(message);
                            }

                            @Override
                            public void apiRequestSuccess(Object o, String method) {
                                AppUtil.showToastShort("提醒成功");

                            }
                        });
                    }
                });
                break;
            case "输入运单":
                btn_orderinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JumpPageManager.getManager().goAddShipActivity(context, orderListEntity.getOrder_id());
                    }
                });
                break;
            case "修改运单":
                btn_orderinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JumpPageManager.getManager().goAddShipActivity(context, orderListEntity.getOrder_id());
                    }
                });
                break;
            case "操作退款":
                btn_orderinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JumpPageManager.getManager().goRefundActivityActivity(context, orderListEntity.getOrder_number(), SELLER);

                    }
                });
                break;
        }


        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSellerOrderInfoActivity(context, orderListEntity.getOrder_number());
            }
        });

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
