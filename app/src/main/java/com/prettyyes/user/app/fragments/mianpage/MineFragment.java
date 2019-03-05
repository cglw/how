package com.prettyyes.user.app.fragments.mianpage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.ui.how.CollectionActivity;
import com.prettyyes.user.app.ui.order.CouponListActivity;
import com.prettyyes.user.app.ui.order.MyOrderListActivity;
import com.prettyyes.user.app.ui.order.WishListActivity;
import com.prettyyes.user.app.ui.setting.SettingActivity;
import com.prettyyes.user.app.view.app.MineItemView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.user.UserInfo;

import org.xutils.view.annotation.ViewInject;


public class MineFragment extends BaseFragment implements View.OnClickListener {

    @ViewInject(R.id.view_mine_lookinfo)
    private LinearLayout lin_myinfo;
    @ViewInject(R.id.img_mine_headimg)
    private ImageView img_head;
    @ViewInject(R.id.tv_mine_nickname)
    private TextView tv_name;
    @ViewInject(R.id.tv_tobe_kol)
    private TextView tv_tobe_kol;
    @ViewInject(R.id.view_mine_collect)
    private LinearLayout mineItemView_kol;
    @ViewInject(R.id.view_mine_wishlist)
    private LinearLayout mineItemView_wishlist;

    @ViewInject(R.id.view_mine_coupon)
    private LinearLayout mineItemView_coupon;
    @ViewInject(R.id.view_mine_order)
    private LinearLayout mineItemView_order;
    @ViewInject(R.id.view_mine_setting)
    private RelativeLayout mineItemView_setting;
    @ViewInject(R.id.view_mine_callkf)
    private RelativeLayout mineItemView_kefu;

    @ViewInject(R.id.view_mine_tobekol)
    private View mineItemView_tobekol;
    @ViewInject(R.id.view_mine_content)
    private MineItemView view_mine_content;
    @ViewInject(R.id.view_line_content)
    private View view_line_content;


    @Override
    public void onResume() {
        super.onResume();
        AppStatistics.onPageStart(getActivity(),"PersonalPage"); //统计页面，"MainScreen"为页面名称，可自定义

    }

    @Override
    public void onPause() {
        super.onPause();
        AppStatistics.onPageEnd(getActivity(),"PersonalPage"); //统计页面，"MainScreen"为页面名称，可自定义

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_mine_new;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {


    }

    @Override
    public void setListener() {

        tv_tobe_kol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMyAccountActivity(getContext());
            }
        });
        lin_myinfo.setOnClickListener(this);
        mineItemView_kol.setOnClickListener(this);
        mineItemView_wishlist.setOnClickListener(this);
        mineItemView_coupon.setOnClickListener(this);
        mineItemView_order.setOnClickListener(this);
        mineItemView_setting.setOnClickListener(this);
        mineItemView_kefu.setOnClickListener(this);
        mineItemView_tobekol.setOnClickListener(this);
        view_mine_content.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSpuListActivity(getActivity());
            }
        });
//        mSubscription = AppRxBus.getInstance().toObservable(UserInfo.class).subscribe(new RxAction1<UserInfo>() {
//            @Override
//            public void callback(UserInfo userInfo) {
//
//                if (!StringUtils.strIsEmpty(userInfo.getNickname())) {
//                    tv_name.setText(userInfo.getNickname());
//                }
//                if (!StringUtils.strIsEmpty(userInfo.getHeadimg())) {
//                    ImageLoadUtils.loadHeadImg(userInfo.getHeadimg(), img_head);
//
//                }
//            }
//        });
     mSubscription=   AppRxBus.getInstance().subscribeEvent(new RxCallback<UserInfo>() {
            @Override
            protected void back(UserInfo userInfo) {
                if (!StringUtils.strIsEmpty(userInfo.getNickname())) {
                    tv_name.setText(userInfo.getNickname());
                }
                if (!StringUtils.strIsEmpty(userInfo.getHeadimg())) {
                    ImageLoadUtils.loadHeadImg(getActivity(),userInfo.getHeadimg(), img_head);

                }
            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {
        getPersonInfo();
    }


    public void getPersonInfo() {
        new UserApiImpl().userInfo(getUUId(), "", new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(UserInfo userInfo, String method) {
                setData(userInfo);
            }
        });
    }

    private void setData(UserInfo userInfo) {
        ImageLoadUtils.loadHeadImg(getActivity(),userInfo.getHeadimg(), img_head);
        tv_name.setText(userInfo.getNickname());
        if (userInfo.isSeller()) {
            view_line_content.setVisibility(View.VISIBLE);
            view_mine_content.setVisibility(View.VISIBLE);
        } else {
            view_line_content.setVisibility(View.GONE);
            view_mine_content.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_mine_collect:
                next(CollectionActivity.class);
                break;
            case R.id.view_mine_wishlist:
                WishListActivity.goWishListActivity(getActivity());
                break;
            case R.id.view_mine_coupon:
                next(CouponListActivity.class);
                break;
            case R.id.view_mine_order:
                next(MyOrderListActivity.class);
                break;
            case R.id.view_mine_setting:
                next(SettingActivity.class);
                break;
            case R.id.view_mine_callkf:
                AccountDataRepo.getAccountManager().chatWithkf();
                break;
            case R.id.view_mine_tobekol:
                JumpPageManager.getManager().goApplayKolDetailActivity(getContext());
                break;
            case R.id.view_mine_lookinfo:
                JumpPageManager.getManager().goPersonInfoActivity(getContext());
                break;


        }
    }


}
