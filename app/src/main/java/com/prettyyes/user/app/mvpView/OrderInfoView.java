package com.prettyyes.user.app.mvpView;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.prettyyes.user.model.order.OrderInfo;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/11/3
 * Description: Nothing
 */
public interface OrderInfoView extends BaseView {

    public void setLvData(ArrayList<OrderInfo.InfoEntity.MyListInfo> data);

    public void setSellerName(String name, String seller_id);

    public void setSellerRemark(String remark);

    public void setOrderTotalNum(String totalnum);

    public void setOrderPrice(String totalprice);

    public void setYourname(String name);

    public void setPhone(String phone);

    public void setAddress(String address);

    public void setSubPrice(String subPrice);

    public void setDiscountPrice(String discountPrice);

    public void setPayPrice(String payPrice);

    public ListView getLv();

    public TextView getConponText();

    public Button getBtnOne();

    public Button getBtnTwo();

    public String getOrdernumber();

    public String getSellerId();

    void setTopTime(String creattime, String nexttime);

    void setStep(int current, ArrayList<String> datas);

    View getCouponLayout();

    void setCouponData(String price, String name, String content, String endtime);

    void setSellerInfo(OrderInfo.InfoEntity sellerInfo);


}
