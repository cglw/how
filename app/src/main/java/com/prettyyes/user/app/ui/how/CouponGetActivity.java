package com.prettyyes.user.app.ui.how;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.FirstModel;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.AppConfig.REGISTER_DIALOG_OPEN;
import static com.prettyyes.user.data.DataCenter.CouponGetType;

public class CouponGetActivity extends BaseActivity {

    private static final String TAG = "CouponGetActivity";

    @Override
    public boolean isRemove_loadingLayout() {
        return true;
    }

    @ViewInject(R.id.img_couponGet_covery)
    ImageView img_covery;
    @ViewInject(R.id.ll_couponGet_type)
    LinearLayout ll_type;
    @ViewInject(R.id.ll_couponGet_four_check)
    LinearLayout ll_check;
    @ViewInject(R.id.tv_couponGet_title)
    TextView tv_title;
    @ViewInject(R.id.tv_couponGet_price)
    TextView tv_price;
    @ViewInject(R.id.tv_couponGet_type)
    TextView tv_type;
    @ViewInject(R.id.btn_couponGet_do)
    Button btn_coupon_do;
    @ViewInject(R.id.img_couponGet_close)
    ImageView img_close;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }


    public static void goComponGetActivity(Activity activity) {

        activity.startActivity(new Intent(activity, CouponGetActivity.class));
        activity.overridePendingTransition(R.anim.alpha_in, 0);
    }

    public static void goComponGetActivity(Activity activity, CouponGetType couponGetType, FirstModel firstModel) {
        if (REGISTER_DIALOG_OPEN) {
            LogUtil.i(TAG, "goComponGetActivity" + SpMananger.getUUID());
            if (StringUtils.strIsEmpty(SpMananger.getUUID()))
                return;
            Intent intent = new Intent(activity, CouponGetActivity.class);
            intent.putExtra("type", couponGetType);
            intent.putExtra("firstModel", firstModel);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.alpha_in, 0);
        }
    }

    private FirstModel firstModel;

    @Override
    protected int bindLayout() {
        return R.layout.activity_compon_get;
    }

    @Override
    public void setStatusBar() {
        setTranslucenBar();
    }

    private CouponGetType couponGetType;

    @Override
    protected void initVariables() {
        super.initVariables();
        couponGetType = (CouponGetType) getIntent().getSerializableExtra("type");

        firstModel = (FirstModel) getIntent().getSerializableExtra("firstModel");

    }

    @Override
    protected void setListener() {
        super.setListener();
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void setTextByData() {
        btn_coupon_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goCouponListActivity(getThis());
            }
        });
        switch (couponGetType) {
            case UNREGISTER:

                tv_title.setText("注册成为我们的用户享受");
                tv_price.setText("注册");
                tv_type.setText("新人大礼包");
                btn_coupon_do.setText("注册");
                img_covery.setImageResource(R.mipmap.background5);
                ll_check.setVisibility(View.GONE);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img_covery.getLayoutParams();
                layoutParams.weight = 2;
                btn_coupon_do.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RegisterActivity.getRegister(CouponGetActivity.this);
                    }
                });

                break;
            case REGISTER:
                tv_title.setText("你已经成功注册！How 送你");
                tv_price.setText("注册");
                tv_type.setText("新人大礼包");
                btn_coupon_do.setText("查看优惠券");
                img_covery.setImageResource(R.mipmap.background2);


                break;
            case SHARE:
                tv_title.setText("恭喜你获得首次分享奖励！");
                tv_price.setText("5元");
                tv_type.setText("无门槛优惠券");
                btn_coupon_do.setText("查看优惠券");
                img_covery.setImageResource(R.mipmap.background1);

                break;
            case ZEZE:
                tv_title.setText("恭喜你获得首次啧啧奖励！");
                tv_price.setText("5元");
                tv_type.setText("无门槛优惠券");
                btn_coupon_do.setText("查看优惠券");
                img_covery.setImageResource(R.mipmap.background4);

                break;
            case ASK:
                tv_title.setText("恭喜你获得首次提问奖励奖励！");
                tv_price.setText("5元");
                tv_type.setText("无门槛优惠券");
                btn_coupon_do.setText("查看优惠券");
                img_covery.setImageResource(R.mipmap.background3);

                break;
        }
    }

    @Override
    protected void initViews() {
        hideHeader();
        for (int i = 0; i < ll_check.getChildCount(); i++) {
            CheckBox childAt = (CheckBox) ll_check.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox v1 = (CheckBox) v;
                    v1.setChecked(!v1.isChecked());
                }
            });
            childAt.setChecked(false);
        }

        if (firstModel != null) {
            if (firstModel.getFirst_login() == 1) {
                ((CheckBox) ll_check.getChildAt(0)).setChecked(true);
            }
            if (firstModel.getFirst_task_ask() == 1) {
                ((CheckBox) ll_check.getChildAt(1)).setChecked(true);
            }
            if (firstModel.getFirst_share() == 1) {
                ((CheckBox) ll_check.getChildAt(2)).setChecked(true);
            }
            if (firstModel.getFirst_task_zeze() == 1) {
                ((CheckBox) ll_check.getChildAt(3)).setChecked(true);

            }
        }


        setTextByData();
    }

    @Override
    protected void loadData() {
        new UserApiImpl().userFirstActivity(getUUId(), new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {

            }
        });

    }

    @Override
    public void back() {
        super.back();
        overridePendingTransition(0, R.anim.alpha_out);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.alpha_out);

    }
}
