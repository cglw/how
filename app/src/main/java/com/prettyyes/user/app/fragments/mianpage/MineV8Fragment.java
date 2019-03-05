package com.prettyyes.user.app.fragments.mianpage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.api.netXutils.requests.RichRequest;
import com.prettyyes.user.api.netXutils.response.RichesRes;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.ui.order.MyOrderListActivity;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.event.HowScoreChangeEvent;
import com.prettyyes.user.core.event.LoginChangeEvent;
import com.prettyyes.user.core.event.MineDynamicUnreadEvent;
import com.prettyyes.user.core.event.MorePageUnreadEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.user.UserInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chengang on 2017/7/24.
 */

public class MineV8Fragment extends BaseFragment implements View.OnClickListener {


    ScrollView scrollView;

    @Override
    public int bindLayout() {
        return R.layout.fragment_mine_v8;
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
        bindViews(view);
        settingItem_go_ask.setUnread(DataCenter.TASK_UNREAD_NUM);
        settingItem_go_invate.setUnread(DataCenter.INVATE_ME_UNREAD_NUM);

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {
        if (SpMananger.getUUID() == null)
            return;
        getPersonInfo();
        getMoney();
    }

    @Override
    public void setListener() {
        super.setListener();


        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AddTemplateOrSelectSuccessEvent>() {
            @Override
            protected void back(AddTemplateOrSelectSuccessEvent obj) {
                getPersonInfo();

            }
        }, new RxCallback<LoginChangeEvent>() {
            @Override
            protected void back(LoginChangeEvent obj) {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                loading();
                lazyInitBusiness(getContext());
            }
        }, new RxCallback<UserInfo>() {
            @Override
            protected void back(UserInfo obj) {
                if (!StringUtils.strIsEmpty(obj.getNickname())) {
                    tv_nickname.setText(obj.getNickname());
                }
                if (!StringUtils.strIsEmpty(obj.getHeadimg())) {
                    ImageLoadUtils.loadHeadImg(getActivity(),obj.getHeadimg(), img_head);

                }
            }
        }, new RxCallback<HowScoreChangeEvent>() {
            @Override
            protected void back(HowScoreChangeEvent obj) {
                tv_how.setText(obj.getHow_score());
            }
        }, new RxCallback<MineDynamicUnreadEvent>() {
            @Override
            protected void back(MineDynamicUnreadEvent obj) {
                settingItem_go_ask.setUnread(DataCenter.TASK_UNREAD_NUM);

            }
        }, new RxCallback<MorePageUnreadEvent>() {
            @Override
            protected void back(MorePageUnreadEvent obj) {
                settingItem_go_invate.setUnread(DataCenter.INVATE_ME_UNREAD_NUM);

            }
        });


        ll_go_info_detail.setOnClickListener(this);
        ll_go_score.setOnClickListener(this);
        ll_order_buy.setOnClickListener(this);
        ll_order_sell.setOnClickListener(this);
        ll_go_cart.setOnClickListener(this);
        ll_go_account.setOnClickListener(this);
        ll_aply_kol.setOnClickListener(this);
        btn_withdraw.setOnClickListener(this);
        ll_go_spulist.setOnClickListener(this);
        settingItem_go_collect.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goCollectActivity(getContext());
            }
        });

        settingItem_go_ask.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMyAskListActivity(getContext());

            }
        });
        settingItem_go_chatkf.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDataRepo.getAccountManager().chatWithkf();
            }
        });
        settingItem_go_invate.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goInvateMeActivity(getContext());

            }
        });

        settingItem_go_reply.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToastShort("codeing");
                JumpPageManager.getManager().goMyReplyQueActivity(getContext());


            }
        });
        settingItem_go_setting.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSettingActivity(getContext());
            }
        });
        settingItem_go_coupon.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goCouponListActivity(getContext());
            }
        });
    }


    public LinearLayout ll_go_info_detail;
    public ImageView img_head;
    public TextView tv_nickname;
    public TextView tv_money;
    public Button btn_withdraw;
    public View ll_aply_kol;
    public LinearLayout ll_go_score;
    public TextView tv_type;
    public TextView tv_how;
    public TextView tv_score;
    public LinearLayout ll_imgs;
    public LinearLayout ll_go_spulist;
    public TextView tv_goods_num;
    public com.prettyyes.user.app.view.app.SettingItemView settingItem_go_ask;
    public com.prettyyes.user.app.view.app.SettingItemView settingItem_go_reply;
    public com.prettyyes.user.app.view.app.SettingItemView settingItem_go_collect;
    public com.prettyyes.user.app.view.app.SettingItemView settingItem_go_invate;
    public com.prettyyes.user.app.view.app.SettingItemView settingItem_go_setting;
    public com.prettyyes.user.app.view.app.SettingItemView settingItem_go_chatkf;
    public com.prettyyes.user.app.view.app.SettingItemView settingItem_go_coupon;
    public LinearLayout ll_order_buy;
    public LinearLayout ll_order_sell;
    public LinearLayout ll_go_cart;
    public LinearLayout ll_go_account;

    public View view_line_order_seller;
    public View ll_order_seller;
    // End Of Content View Elements

    private void bindViews(View view) {

        ll_go_info_detail = (LinearLayout) view.findViewById(R.id.ll_go_info_detail);
        img_head = (ImageView) view.findViewById(R.id.img_head);
        tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
        tv_money = (TextView) view.findViewById(R.id.tv_money);
        btn_withdraw = (Button) view.findViewById(R.id.btn_withdraw);
        ll_aply_kol = view.findViewById(R.id.ll_aply_kol);
        ll_go_score = (LinearLayout) view.findViewById(R.id.ll_go_score);
        tv_type = (TextView) view.findViewById(R.id.tv_type);
        tv_how = (TextView) view.findViewById(R.id.tv_how);
        tv_score = (TextView) view.findViewById(R.id.tv_score);
        ll_imgs = (LinearLayout) view.findViewById(R.id.ll_imgs);
        ll_go_spulist = (LinearLayout) view.findViewById(R.id.ll_go_spulist);
        tv_goods_num = (TextView) view.findViewById(R.id.tv_goods_num);
        settingItem_go_ask = (com.prettyyes.user.app.view.app.SettingItemView) view.findViewById(R.id.settingItem_go_ask);
        settingItem_go_reply = (com.prettyyes.user.app.view.app.SettingItemView) view.findViewById(R.id.settingItem_go_reply);
        settingItem_go_collect = (com.prettyyes.user.app.view.app.SettingItemView) view.findViewById(R.id.settingItem_go_collect);
        settingItem_go_invate = (com.prettyyes.user.app.view.app.SettingItemView) view.findViewById(R.id.settingItem_go_invate);
        settingItem_go_setting = (com.prettyyes.user.app.view.app.SettingItemView) view.findViewById(R.id.settingItem_go_setting);
        settingItem_go_chatkf = (com.prettyyes.user.app.view.app.SettingItemView) view.findViewById(R.id.settingItem_go_chatkf);
        settingItem_go_coupon = (com.prettyyes.user.app.view.app.SettingItemView) view.findViewById(R.id.settingItem_coupon);
        ll_order_buy = (LinearLayout) view.findViewById(R.id.ll_order_buy);
        ll_order_sell = (LinearLayout) view.findViewById(R.id.ll_order_sell);
        ll_go_cart = (LinearLayout) view.findViewById(R.id.ll_go_cart);
        ll_go_account = (LinearLayout) view.findViewById(R.id.ll_go_account);
        ll_order_seller = view.findViewById(R.id.ll_order_seller);
        view_line_order_seller = view.findViewById(R.id.view_line_order_seller);

    }


    public void getPersonInfo() {

        if (getUUId() == null)
            return;
        new UserApiImpl().userInfo(getUUId(), "", new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                if (message != null && message.contains("登录")) {
                    JumpPageManager.getManager().goUnLoginActivity(getActivity());
                }
                loadFail();

            }

            @Override
            public void apiRequestSuccess(UserInfo userInfo, String method) {
                loadSuccess();
                setData(userInfo);
                AccountDataRepo.getAccountManager().save(userInfo);

            }
        });
    }

    private void setData(UserInfo userInfo) {
        if (userInfo == null)
            return;
        ImageLoadUtils.loadHeadImg(getActivity(),userInfo.getHeadimg(), img_head);
        tv_nickname.setText(userInfo.getNickname());
        View parent = (View) btn_withdraw.getParent();

        if (userInfo.isSeller()) {
            ((View) (ll_imgs.getParent())).setVisibility(View.VISIBLE);
            parent.setVisibility(View.VISIBLE);
            ll_aply_kol.setVisibility(View.GONE);
            ll_order_seller.setVisibility(View.VISIBLE);
            view_line_order_seller.setVisibility(View.VISIBLE);
            settingItem_go_invate.setVisibility(View.VISIBLE);
        } else {
            ((View) (ll_imgs.getParent())).setVisibility(View.VISIBLE);
            parent.setVisibility(View.VISIBLE);
            ll_aply_kol.setVisibility(View.GONE);
            ll_order_seller.setVisibility(View.VISIBLE);
            view_line_order_seller.setVisibility(View.VISIBLE);
            settingItem_go_invate.setVisibility(View.VISIBLE);

        }
        tv_goods_num.setText(String.format("%s+", userInfo.getSpu_count()));
        final List<String> spu_img = userInfo.getSpu_img();
        if (spu_img != null && spu_img.size() > 0) {
            for (int i = 0; i < spu_img.size(); i++) {
                if (i > 2)
                    return;
                RoundImageView childAt = (RoundImageView) ll_imgs.getChildAt(i);
                ImageLoadUtils.loadListimg(getActivity(), spu_img.get(i), childAt);
                final int j = i;
                childAt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JumpPageManager.getManager().goBigImgActivity(getContext(), (ArrayList<String>) spu_img, j);
                    }
                });
            }

        }
        tv_type.setText(userInfo.grade_title);
        tv_how.setText(userInfo.score);
        tv_score.setText(userInfo.getSeller_score());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_go_info_detail:
//                next(PersonInfoActivity.class);
                JumpPageManager.getManager().goPersonInfoActivity(getContext());
                break;
            case R.id.ll_go_score:
                JumpPageManager.getManager().goMyMedalActivity(getContext());
                break;
            case R.id.ll_order_buy:
                next(MyOrderListActivity.class);
                break;
            case R.id.ll_order_sell:
                JumpPageManager.getManager().goSellerOrderListActivity(getContext());
                break;
            case R.id.ll_go_cart:
                JumpPageManager.getManager().goCartActivity(getContext());
                break;
            case R.id.ll_go_account:
                JumpPageManager.getManager().goMyAccountActivity(getContext());
                break;
            case R.id.ll_aply_kol:
                JumpPageManager.getManager().goApplayKolDetailActivity(getContext());
                break;
            case R.id.ll_go_spulist:
                JumpPageManager.getManager().goSpuListActivity(getContext());
                break;
            case R.id.btn_withdraw:

                JumpPageManager.getManager().goWithdrawRecordActivity(getActivity());

                break;
        }

    }

    public void getMoney() {
        new RichRequest().post(new NetReqCallback<RichesRes>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(RichesRes richesRes, String method) {
                tv_money.setText(String.format("可提现金额 ¥:%s", richesRes.getInfo().getTotal_money()));
            }
        });

    }
}
