package com.prettyyes.user.app.ui.order;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.adapter.order.CheckOrderAdapter;
import com.prettyyes.user.app.adapter.order.CouponVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.mvpPresenter.OrderCheckPresenter;
import com.prettyyes.user.app.mvpView.OrderCheckView;
import com.prettyyes.user.app.view.ClipViewPager;
import com.prettyyes.user.app.view.app.SelectView;
import com.prettyyes.user.app.view.pagetransformer.ScaleVpTransformer;
import com.prettyyes.user.app.view.recycle.HeaderAndFooterRecyclerViewAdapter;
import com.prettyyes.user.app.view.recycle.RecyclerViewUtils;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.coupon.CouponInfoEntity;
import com.prettyyes.user.model.order.OrderCheck;
import com.prettyyes.user.model.v8.OrderCheckItemModel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class CheckOrderActivity extends BaseActivity implements OrderCheckView {
    private static final String TAG = "CheckOrderActivity";

    @ViewInject(R.id.rv_checkorderAct_order)
    private RecyclerView rv_order;
    @ViewInject(R.id.btn_checkorderAct_buy)
    private Button btn_pay;
    @ViewInject(R.id.tv_checkorderAct_payprice)
    private TextView tv_payprice;
    @ViewInject(R.id.tv_checkorderAct_payprice_bottom)
    private TextView tv_payprice_bottom;


    @ViewInject(R.id.tv_checkorederAct_default)
    private TextView tv_default;
    @ViewInject(R.id.lin_checkorederAct_edit)
    private LinearLayout lin_editAddress;
    @ViewInject(R.id.vp_checkorderAct_coupon)
    private ClipViewPager vp_coupon;
    @ViewInject(R.id.tv_checkorderAct_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_checkorderAct_address)
    private TextView tv_address;
    @ViewInject(R.id.tv_checkorderAct_phone)
    private TextView tv_phone;
    @ViewInject(R.id.tv_checkorderAct_subprice)
    private TextView tv_subprice;
    @ViewInject(R.id.tv_checkorderAct_discountprice)
    private TextView tv_discount;
    @ViewInject(R.id.tv_checkorderAct_isHaveCoupon)
    private TextView tv_ishaveCoupon;


    private CouponVpAdapter couponVpAdapter;
    private CheckOrderAdapter checkOrderOneAdapter;
    private OrderCheck orderCheck;

    public static final String BUY_NOW = "buy_now";
    private OrderCheckPresenter orderCheckPresenter = new OrderCheckPresenter(this);
    private int coupon_id = 0;
    private int buy_now;
    private String orderRemark = "";


    @Override
    protected int bindLayout() {
        return R.layout.activity_check_order;
    }

    @Override
    protected void initVariables() {
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null)
            buy_now = intentParmas.getBuy_now();
    }

    View head;
    View foot;
    HeaderAndFooterRecyclerViewAdapter andFooterRecyclerViewAdapter;

    @Override
    protected void initViews() {
        setActivtytitle("填写订单");
        checkOrderOneAdapter = new CheckOrderAdapter(this);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv_order.setLayoutManager(layout);
        couponVpAdapter = new CouponVpAdapter(this);

        head = LayoutInflater.from(this).inflate(R.layout.layout_input_address, (ViewGroup) getRootView(), false);
        foot = LayoutInflater.from(this).inflate(R.layout.layout_checkoreder_bottom, (ViewGroup) getRootView(), false);
        x.view().inject(this, head);
        x.view().inject(this, foot);
        vp_coupon.setAdapter(couponVpAdapter);
        vp_coupon.setPageMargin(DensityUtil.dip2px(16));
        andFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(checkOrderOneAdapter);


        rv_order.setAdapter(andFooterRecyclerViewAdapter);
        RecyclerViewUtils.setHeaderView(rv_order, head);
        RecyclerViewUtils.setFooterView(rv_order, foot);

        getRootView().setFocusable(true);
        getRootView().setFocusableInTouchMode(true);
        getRootView().requestFocus();
        head.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void setSoftModel() {
        super.setSoftModel();
    }

    @Override
    protected void loadData() {
        new OrderApiImpl().orderCheckInfo(SpMananger.getUUID(), buy_now, new NetReqCallback<OrderCheck>() {
            @Override
            public void apiRequestFail(String message, String method) {
                loadFail();

            }

            @Override
            public void apiRequestSuccess(OrderCheck orderCheck, String method) {
                orderCheckPresenter.initData(orderCheck.getInfo());
                loadSuccess();
                head.getLayoutParams().width = BaseApplication.ScreenWidth - AppUtil.dip2px(16) * 2;
                head.requestLayout();


            }
        });

    }

    @Override
    protected void setListener() {


        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCheckPresenter.pay();
            }
        });
        getRootView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vp_coupon.dispatchTouchEvent(event);
            }
        });
        lin_editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCheckPresenter.goSelectAddress();
            }
        });
        vp_coupon.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (isfirst_click) {
                    isfirst_click = false;
                    return;
                }
                for (int i = 0; i < vp_coupon.getChildCount(); i++) {
                    View v = vp_coupon.getChildAt(i);
                    SelectView sv = (SelectView) v.findViewById(R.id.view_coupon_select);
                    if (i != position)
                        sv.getTickPlusDrawable().toggle(false);
                    else
                        sv.getTickPlusDrawable().toggle(true);

                }
                couponVpAdapter.get(position).setSelected(true);


                coupon_id = couponVpAdapter.get(position).getCode_id();
                float price = Float.parseFloat(couponVpAdapter.get(position).getCoupon_price());
                setDiscount_price(price);
                setPayPrice(orderCheckPresenter.getSubprice() - price);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rv_order.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if (vp_coupon.getChildCount() > 0 && isfirst) {
                    isfirst = false;
                    vp_coupon.setCurrentItem(select_index);

                    final View v = vp_coupon.getChildAt(select_index);
                    vp_coupon.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            v.performClick();

                        }
                    }, 200);
//                    v.performClick();


//
//                    if (v == null)
//                        return;
//                    SelectView sv = (SelectView) v.findViewById(R.id.view_coupon_select);
//                    if (sv != null)
//                        sv.getTickPlusDrawable().setSelect();
                }


            }
        });


        mSubscription=AppRxBus.getInstance().subscribeEvent(new RxCallback<CouponInfoEntity>() {
            @Override
            protected void back(CouponInfoEntity couponInfoEntity) {
                if (couponInfoEntity.isSelected()) {
                    coupon_id = couponInfoEntity.getCode_id();
                    float price = Float.parseFloat(couponInfoEntity.getCoupon_price());
                    setDiscount_price(price);
                    setPayPrice(orderCheckPresenter.getSubprice() - price);
                } else {
                    coupon_id = 0;
                    setDiscount_price(0);
                    setPayPrice(orderCheckPresenter.getSubprice());
                }
            }
        });
    }

    @Override
    public String getUser_remark() {
        return orderRemark;
    }

    @Override
    public int getPay_type() {
        return 0;
    }

    private boolean isfirst = true;
    private boolean isfirst_click = true;

    @Override
    public int getCoupon_id() {
        int id = 0;
        for (int i = 0; i < couponVpAdapter.getCount(); i++) {
            if (couponVpAdapter.get(i).isSelected()) {
                id = couponVpAdapter.get(i).getCode_id();
            }
        }
        return id;
    }

    @Override
    public int getAddress_id() {
        if (tv_address.getTag() == null) {
            return 0;
        }
        return (int) tv_address.getTag();
    }

    @Override
    public int getBuy_now() {
        return buy_now;
    }

    @Override
    public void setName(String name) {
        if (name == null)
            name = "";
        tv_name.setText(name);
    }

    @Override
    public void setPhone(String phone) {
        if (phone == null)
            phone = "";
        tv_phone.setText(phone);
    }

    @Override
    public void setAddress(String addrss, int address_id) {
        if (addrss == null) {
            addrss = "";
        }
        tv_address.setText(addrss);
        tv_address.setTag(address_id);
    }

    public void setSelect_index(int select_index) {
        this.select_index = select_index;
    }

    @Override
    public void setCoupon(final List<CouponInfoEntity> datas, int index) {
        if (datas.size() > 0) {

            vp_coupon.setPageTransformer(true, new ScaleVpTransformer(0.9f, vp_coupon));
            vp_coupon.setOffscreenPageLimit(datas.size());

            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).isSelected()) {
                    break;
                }
            }
            select_index = index;
            couponVpAdapter.addAll((ArrayList<CouponInfoEntity>) datas);
//            vp_coupon.setCurrentItem(index);
//            vp_coupon.setCurrentItem(select_index);


        }


    }


    @Override
    public void setSubprice(float subprice) {
        AppUtil.loadTypaeFace(this, tv_subprice);
        tv_subprice.setText(Constant.RMB + subprice);
    }

    @Override
    public void setPayPrice(float payprice) {
        AppUtil.loadTypaeFace(this, tv_payprice);
        AppUtil.loadTypaeFace(this, tv_payprice_bottom);
        tv_payprice.setText(Constant.RMB + payprice);
        tv_payprice_bottom.setText(Constant.RMB + payprice);
    }

    private int select_index = 0;

    @Override
    public void setDiscount_price(float discount_price) {
        AppUtil.loadTypaeFace(this, tv_discount);

        tv_discount.setText(Constant.RMB + String.format("%.2f", discount_price));
    }


    @Override
    public void setLvData(List<OrderCheckItemModel> datas) {
        checkOrderOneAdapter.addAll(datas);
    }

    @Override
    public void setTvDefaultVisiable(int visiable) {
        tv_default.setVisibility(visiable);
    }

    @Override
    public void setHaveCoupon(boolean have) {
        if (have) {
            vp_coupon.setVisibility(View.VISIBLE);
            tv_ishaveCoupon.setVisibility(View.GONE);
        } else {
            vp_coupon.setVisibility(View.GONE);
            tv_ishaveCoupon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setOrderRemark(String json) {
        orderRemark = json;
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
        orderCheckPresenter.registerIntent(inentFliter);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
        orderCheckPresenter.handlerIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public CheckOrderAdapter getCheckOrderOneAdapter() {
        return checkOrderOneAdapter;
    }
}
