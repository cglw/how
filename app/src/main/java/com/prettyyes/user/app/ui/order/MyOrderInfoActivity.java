package com.prettyyes.user.app.ui.order;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.adapter.order.OrderInfoChildAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.OrderInfoPresenter;
import com.prettyyes.user.app.mvpView.OrderInfoView;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.app.view.app.UPointStepsView;
import com.prettyyes.user.app.view.lvandgrid.MyListView;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.order.OrderInfo;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class MyOrderInfoActivity extends BaseActivity implements OrderInfoView {

    @ViewInject(R.id.lv_orderinfoAct)
    private MyListView lv;
    @ViewInject(R.id.tv_orderinfoAct_totalprice)//商品总价
    private TextView tv_totalprice;
    @ViewInject(R.id.KolSimpleInfoView)
    KolSimpleInfoView kolSimpleInfoView;
    @ViewInject(R.id.tv_orderinfoAct_totalnum)
    private TextView tv_totalnum;
    @ViewInject(R.id.tv_orderinfoAct_remark)
    private TextView tv_remark;
    @ViewInject(R.id.tv_orderinfoAct_yourname)
    private TextView tv_yourname;
    @ViewInject(R.id.tv_orderinfoAct_address)
    private TextView tv_address;
    @ViewInject(R.id.tv_orderinfoAct_phone)
    private TextView tv_phone;
    @ViewInject(R.id.tv_orderinfoAct_payprice)//实际支付价格
    private TextView tv_payprice;
    @ViewInject(R.id.tv_orderinfoAct_subprice)//商品总价
    private TextView tv_subprice;
    @ViewInject(R.id.tv_orderinfoAct_discountprice)//折扣的价格
    private TextView tv_discountprice;
    @ViewInject(R.id.tv_orderinfoAct_isHaveCoupon)
    private TextView tv_isHaveCoupon;
    @ViewInject(R.id.btn_orderInfoAct_left)
    private Button btn_left;
    @ViewInject(R.id.btn_orderInfoAct_right)
    private Button btn_right;

    @ViewInject(R.id.rel_coupon_base)
    private View rel_coupon_base;
    @ViewInject(R.id.tv_coupon_name)
    private TextView tv_coupon_name;
    @ViewInject(R.id.tv_coupon_content)
    private TextView tv_coupon_content;
    @ViewInject(R.id.tv_coupon_endtime)
    private TextView getTv_coupon_endtime;
    @ViewInject(R.id.tv_coupon_price)
    private TextView tv_coupon_price;
    @ViewInject(R.id.stepView_orderinfoAct_step)
    private UPointStepsView stepsView_step;
    @ViewInject(R.id.tv_orderinfoAct_creattime)
    private TextView tv_createtime;
    @ViewInject(R.id.tv_orderinfoAct_paytime)
    private TextView tv_paytime;

    private String ordernum = "";
    private OrderInfoPresenter orderInfoPresenter = new OrderInfoPresenter(this);
    private OrderInfoChildAdapter orderInfoChildAdapter;
    private int sellerid = 0;


    @Override
    protected void initVariables() {
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null)
            ordernum = intentParmas.getOrder_number();
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_my_order_info;
    }

    @Override
    protected void initViews() {
        setActivtytitle("订单详情");
        orderInfoChildAdapter = new OrderInfoChildAdapter(this);
        lv.setAdapter(orderInfoChildAdapter);
        //获取焦点，避免滑动
        tv_createtime.setFocusable(true);
        tv_createtime.setFocusableInTouchMode(true);
        tv_createtime.requestFocus();
        rel_coupon_base.setVisibility(View.GONE);


    }

    @Override
    protected void loadData() {
        orderInfoPresenter.getOrderInfo(ordernum);
    }

    @Override
    protected void setListener() {
        super.setListener();

    }

    @Override
    public void setLvData(ArrayList<OrderInfo.InfoEntity.MyListInfo> data) {
        orderInfoChildAdapter.clear();
        orderInfoChildAdapter.addAll(data);
    }

    @Override
    public void setSellerName(String name, String seller_id) {
//        tv_sellername.setText(name);
//        tv_sellername.setTag(seller_id);
    }

    @Override
    public void setSellerRemark(String remark) {
        if (remark != null && remark.length() > 0)
            tv_remark.setText("留言：" + remark);
    }

    @Override
    public void setOrderTotalNum(String totalnum) {
        tv_totalnum.setText("共" + totalnum + "件商品");
    }

    @Override
    public void setOrderPrice(String totalprice) {
        AppUtil.loadTypaeFace(this, tv_totalprice);
        tv_totalprice.setText(Constant.RMB + totalprice);
    }

    @Override
    public void setYourname(String name) {
        tv_yourname.setText("联系人：" + name);
    }

    @Override
    public void setPhone(String phone) {
        tv_phone.setText("手机号：" + phone);
    }

    @Override
    public void setAddress(String address) {
        tv_address.setText("收货地址：" + address);
    }

    @Override
    public void setSubPrice(String subPrice) {
        AppUtil.loadTypaeFace(this, tv_subprice);
        tv_subprice.setText(Constant.RMB + subPrice);
    }

    @Override
    public void setDiscountPrice(String discountPrice) {
        AppUtil.loadTypaeFace(this, tv_discountprice);
        tv_discountprice.setText(Constant.RMB + discountPrice);

    }

    @Override
    public void setPayPrice(String payPrice) {
        AppUtil.loadTypaeFace(this, tv_payprice);
        tv_payprice.setText(Constant.RMB + payPrice);
    }

    @Override
    public ListView getLv() {
        return lv;
    }


    @Override
    public TextView getConponText() {
        return tv_isHaveCoupon;
    }

    @Override
    public Button getBtnOne() {
        return btn_left;
    }

    @Override
    public Button getBtnTwo() {
        return btn_right;
    }

    @Override
    public String getOrdernumber() {
        return ordernum;
    }

    @Override
    public String getSellerId() {
        return kolSimpleInfoView.getTag() + "";
    }

    @Override
    public void setTopTime(String creattime, String nexttime) {
        tv_createtime.setText("创建时间：" + creattime);
        tv_paytime.setText("发货时间：" + nexttime);
    }

    @Override
    public void setStep(int current, ArrayList<String> datas) {
        stepsView_step.setCurrentTv(datas);
        stepsView_step.setCurrentPosition(current);
    }

    @Override
    public View getCouponLayout() {
        return rel_coupon_base;
    }

    @Override
    public void setCouponData(String price, String name, String content, String endtime) {
        AppUtil.loadTypaeFace(this, tv_coupon_price);
        tv_coupon_price.setText(Constant.RMB + price);
        tv_coupon_name.setText(name);
        tv_coupon_content.setText(Constant.CENTER_POINT + content);
        getTv_coupon_endtime.setText("");
    }

    @Override
    public void setSellerInfo(OrderInfo.InfoEntity sellerInfo) {
        SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
        sellerInfoEntity.setGrade_title(sellerInfo.grade_title);
        sellerInfoEntity.setAce_txt(sellerInfo.getSeller_ace_txt());
        sellerInfoEntity.setHeadimg(sellerInfo.getSeller_headimg());
        sellerInfoEntity.setNickname(sellerInfo.getSeller_name());
        sellerInfoEntity.setFamous_type(sellerInfo.getSeller_famous() + "");
        sellerInfoEntity.setSeller_id(sellerInfo.getSeller_id() + "");
        kolSimpleInfoView.setSellerInfo(sellerInfoEntity);
        kolSimpleInfoView.setTag(sellerInfo.getSeller_id());
        LogUtil.i(TAG, sellerInfo.toString());

    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        super.setInentFliter(inentFliter);
        orderInfoPresenter.setIntentFliter(inentFliter);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
        super.handlerIntenter(context, intent);
        orderInfoPresenter.handIntentFliter(intent);
    }
}
