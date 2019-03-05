package com.prettyyes.user.app.ui.order;

import android.view.View;
import android.widget.Button;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.CouponLIstRequest;
import com.prettyyes.user.app.adapter.CouponAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.MyCouponPresenter;
import com.prettyyes.user.app.mvpView.MyCouponView;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.model.coupon.CouponList;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

public class CouponListActivity extends SingleListActivity<CouponList> implements MyCouponView {
    @ViewInject(R.id.btn_mycouponAct_addcoupon)
    private Button btn_addcoupon;
    @ViewInject(R.id.btn_mycouponAct_invatefriend)
    private Button btn_invate;
    @ViewInject(R.id.edit_mycouponAct_code)
    private EditTextDel editTextDel_code;

    private MyCouponPresenter myCouponPresenter = new MyCouponPresenter(this);

    @Override
    protected int bindLayout() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void requestData(int page) {
        new CouponLIstRequest().setCoupon_type("0").post(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("我的优惠券");
        showBack();
        swipeRv.getRecycleView().setOverScrollMode(View.OVER_SCROLL_NEVER);
        swipeRv.getRecycleView().setVerticalScrollBarEnabled(false);

    }

    @Override
    protected void setListener() {
        super.setListener();
        btn_addcoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCouponPresenter.addCoupoon();
            }
        });
    }


    @Override
    public AbsRecycleAdapter createAdapter() {
        return new CouponAdapter(this);
    }

    @Override
    public List getListData(CouponList o) {
        return o.getList();
    }


    @Override
    public String getCouponTxt() {
        return editTextDel_code.getText().toString() + "";
    }


    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }


}
