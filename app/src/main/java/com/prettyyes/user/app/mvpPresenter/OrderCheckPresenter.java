package com.prettyyes.user.app.mvpPresenter;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.adapter.order.CheckOrderAdapter;
import com.prettyyes.user.app.mvpView.OrderCheckView;
import com.prettyyes.user.app.ui.order.CheckOrderActivity;
import com.prettyyes.user.app.ui.order.MyOrderListActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.order.OrderManager;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.order.OrderCheck;
import com.prettyyes.user.model.v8.AddressEntity;
import com.prettyyes.user.model.v8.CartInfoEntity;
import com.prettyyes.user.model.v8.OrderCheckItemModel;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.prettyyes.user.app.ui.order.MyOrderListActivity.ORDER_PAY_SUCCESS;
import static com.prettyyes.user.model.v8.OrderCheckItemModel.TYPE_BOTTOM;
import static com.prettyyes.user.model.v8.OrderCheckItemModel.TYPE_CENTER;
import static com.prettyyes.user.model.v8.OrderCheckItemModel.TYPE_TOP;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public class OrderCheckPresenter {
    private static final String TAG = "OrderCheckPresenter";
    private OrderCheckView orderCheckView;
    private OrderApiImpl orderApi;

    public OrderCheckPresenter(OrderCheckView orderCheckView) {
        this.orderCheckView = orderCheckView;
        orderApi = new OrderApiImpl();
    }



    public void goSelectAddress() {
        JumpPageManager.getManager().goAddressListActivity(orderCheckView.getThis(), JumpPageManager.TYPE_SELECT, orderCheckView.getAddress_id() + "");
    }

    public void registerIntent(IntentFilter intentFilter) {
        intentFilter.addAction(Constant.AddressSelect);
        intentFilter.addAction(Constant.AddressDelete);
        intentFilter.addAction(Constant.CouponUse);
        intentFilter.addAction(Constant.OrderPaySuccess);
        intentFilter.addAction(Constant.OrderPayCancel);
    }

    public void handlerIntent(Intent intent) {
        if (intent.getAction().equals(Constant.AddressDelete)) {
            orderCheckView.setAddress("", 0);
            orderCheckView.setPhone("");
            orderCheckView.setName("");
            orderCheckView.setTvDefaultVisiable(View.GONE);
        } else if (intent.getAction().equals(Constant.AddressSelect)) {
            AddressEntity data = (AddressEntity) intent.getSerializableExtra("Address");
            if (data == null) {
                return;
            }
            setAddressData(data);
        } else if (intent.getAction().equals(Constant.OrderPaySuccess)) {
            if (DataCenter.CURRENT_PAPER_ID == 0) {
                MyOrderListActivity.goOrderListActivity(orderCheckView.getThis(), ORDER_PAY_SUCCESS);
            }
        } else if (intent.getAction().equals(Constant.OrderPayCancel)) {
            if (DataCenter.CURRENT_PAPER_ID == 0) {
                orderCheckView.getThis().nextActivity(MyOrderListActivity.class);
            }
        }
    }

    private void setAddressData(AddressEntity data) {
        orderCheckView.setAddress(data.getDetail(), data.getA_id());
        orderCheckView.setPhone(data.getMobile());
        orderCheckView.setName(data.getGet_uname());
        if (data.getIs_default().equals("1")) {
            orderCheckView.setTvDefaultVisiable(View.VISIBLE);
        } else {
            orderCheckView.setTvDefaultVisiable(View.GONE);
        }
    }

    private float totalprice = 0;

    public float getSubprice() {
        return totalprice;
    }

    public void initData(OrderCheck.InfoEntity infoEntity) {
        totalprice = infoEntity.getTotal_price();
        orderCheckView.setName(infoEntity.getDefault_address().getGet_uname());
        if (infoEntity.getDefault_address() != null) {
            if (infoEntity.getDefault_address().getA_id() != 0) {
                orderCheckView.setTvDefaultVisiable(View.VISIBLE);
                orderCheckView.setAddress(infoEntity.getDefault_address().getDetail(), infoEntity.getDefault_address().getA_id());
            } else {
                orderCheckView.setTvDefaultVisiable(View.GONE);
            }
        }
        handlerToListData(infoEntity);

        orderCheckView.setPhone(infoEntity.getDefault_address().getMobile());
        orderCheckView.setSubprice(infoEntity.getTotal_price());
        orderCheckView.setDiscount_price(infoEntity.getTotal_price() - infoEntity.getDiscount_price());
        orderCheckView.setPayPrice(infoEntity.getDiscount_price());
        if (infoEntity.getCoupon_list() != null && infoEntity.getCoupon_list().size() > 0) {
            orderCheckView.setHaveCoupon(true);


            for (int i = 0; i < infoEntity.getCoupon_list().size(); i++) {
                if ((infoEntity.getCoupon_list().get(i).getCode_id() + "").equals(infoEntity.getChose_coupon())) {
                    orderCheckView.setCoupon(infoEntity.getCoupon_list(),i);
                    break;

                }
            }
        } else {
            orderCheckView.setHaveCoupon(false);
        }
    }


    private void handlerToListData(OrderCheck.InfoEntity infoEntity) {

        List<OrderCheckItemModel> datas = new ArrayList<>();
        List<OrderCheck.InfoEntity.ListEntity> list = infoEntity.getList();
        for (int i = 0; i < list.size(); i++) {
            OrderCheck.InfoEntity.ListEntity listEntity = list.get(i);
            OrderCheckItemModel orderCheckItemModel_top = new OrderCheckItemModel();
            orderCheckItemModel_top.setItem_type(TYPE_TOP);
            SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
            sellerInfoEntity.setHeadimg(listEntity.getSeller_headimg());
            sellerInfoEntity.setAce_txt(listEntity.getSeller_ace_txt());
            sellerInfoEntity.setSeller_id(String.valueOf(listEntity.getSeller_id()));
            sellerInfoEntity.setFamous_type(listEntity.getFamous_type() + "");
            sellerInfoEntity.setNickname(listEntity.getSeller_name());
            orderCheckItemModel_top.setSellerInfoEntity(sellerInfoEntity);
            datas.add(orderCheckItemModel_top);


            for (int j = 0; j < listEntity.getCartList().size(); j++) {
                CartInfoEntity cartInfo = listEntity.getCartList().get(j);
                OrderCheckItemModel orderCheckItemModel_center = new OrderCheckItemModel();
                orderCheckItemModel_center.setItem_type(TYPE_CENTER);
                orderCheckItemModel_center.setModule_id(cartInfo.getModule_id());
                orderCheckItemModel_center.setSpu_type(cartInfo.getSpu_type());
                orderCheckItemModel_center.setSpu_price(cartInfo.getSku_price());
                orderCheckItemModel_center.setMain_img(cartInfo.getMain_img());
                orderCheckItemModel_center.setSpu_name(cartInfo.getSku_name());
                orderCheckItemModel_center.setNum(cartInfo.getNum());
                orderCheckItemModel_center.setCart_id(cartInfo.getCart_id());
                orderCheckItemModel_center.setCart_status(cartInfo.getCart_status() + "");
                orderCheckItemModel_center.setAttr_value_text(cartInfo.getAttr_value_text() + "");
                datas.add(orderCheckItemModel_center);
            }


            OrderCheckItemModel orderCheckItemModel_bottom = new OrderCheckItemModel();
            orderCheckItemModel_bottom.setItem_type(TYPE_BOTTOM);
            orderCheckItemModel_bottom.setTotalprice(listEntity.getSeller_total_price() + "");
            orderCheckItemModel_bottom.setTotalnumber(listEntity.getSeller_total_num() + "");
            orderCheckItemModel_bottom.setExpress_price(listEntity.getSeller_express_price());
            orderCheckItemModel_bottom.setSeller_id(listEntity.getSeller_id() + "");
            orderCheckItemModel_bottom.setRemark("");
            datas.add(orderCheckItemModel_bottom);

        }


        orderCheckView.setLvData(datas);
    }

    public void pay() {

        CheckOrderAdapter orderAdapter = ((CheckOrderActivity) orderCheckView.getThis()).getCheckOrderOneAdapter();
        ArrayList<Map> a = new ArrayList();
        for (int i = 0; i < orderAdapter.getDataCount(); i++) {

            if (orderAdapter.getItemData(i).getItem_type().equals(TYPE_BOTTOM)) {
                Map<String, Object> m = new HashMap<>();
                m.put("seller_id", orderAdapter.getItemData(i).getSeller_id());
                m.put("remark", orderAdapter.getItemData(i).getRemark());
                a.add(m);
            }
        }
        String remark = JSONArray.toJSONString(a);
        orderCheckView.getThis().alertView = new OrderManager(orderCheckView.getThis()).OrderCreatAndPay(
                orderCheckView.getThis().getUUId(),
                remark
                , orderCheckView.getAddress_id(), orderCheckView.getCoupon_id(), orderCheckView.getBuy_now());
    }


    public static class ListOne {
        private String seller_name;
        private String seller_id;
        private String seller_headimg;
        private String totalprice;
        private String remark = "";
        private String act;
        private String totalnumber;
        private int famous_type;
        private ArrayList<ListTwo> suit;

        public int getFamous_type() {
            return famous_type;
        }

        public void setFamous_type(int famous_type) {
            this.famous_type = famous_type;
        }

        public String getTotalnumber() {
            return totalnumber;
        }

        public void setTotalnumber(String totalnumber) {
            this.totalnumber = totalnumber;
        }

        public String getAct() {
            return act;
        }

        public void setAct(String act) {
            this.act = act;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public static class ListTwo {
            private String type;
            private String suit_name;
            private String cover_img;
            private String suit_id;

            private int unit_id;
            private String unit_name;
            private String price;
            private String img;
            private int num;
            private int cart_id;
            private int cart_status;
            private int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSuit_name() {
                return suit_name;
            }

            public void setSuit_name(String suit_name) {
                this.suit_name = suit_name;
            }

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public String getSuit_id() {
                return suit_id;
            }

            public void setSuit_id(String suit_id) {
                this.suit_id = suit_id;
            }

            public int getUnit_id() {
                return unit_id;
            }

            public void setUnit_id(int unit_id) {
                this.unit_id = unit_id;
            }

            public String getUnit_name() {
                return unit_name;
            }

            public void setUnit_name(String unit_name) {
                this.unit_name = unit_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
            }

            public int getCart_status() {
                return cart_status;
            }

            public void setCart_status(int cart_status) {
                this.cart_status = cart_status;
            }
        }

        public String getSeller_name() {
            return seller_name;
        }

        public void setSeller_name(String seller_name) {
            this.seller_name = seller_name;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getSeller_headimg() {
            return seller_headimg;
        }

        public void setSeller_headimg(String seller_headimg) {
            this.seller_headimg = seller_headimg;
        }

        public ArrayList<ListTwo> getSuit() {
            return suit;
        }

        public void setSuit(ArrayList<ListTwo> suit) {
            this.suit = suit;
        }
    }

}
