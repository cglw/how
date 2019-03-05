package com.prettyyes.user.app.mvpPresenter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.mvpView.BindPhoneView;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.setting.BindPhoneActivity;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.ronyun.activity.RongyunUtils;

import static com.prettyyes.user.app.account.AccountDataRepo.getAccountManager;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/10/24
 * Description: Nothing
 */
public class BindPhonePresenter {
    private BindPhoneView bindPhoneView;
    private OtherApiImpl otherApi;

    public BindPhonePresenter(BindPhoneView bindPhoneView) {
        this.bindPhoneView = bindPhoneView;
        otherApi = new OtherApiImpl();
    }

    private int times = 30;//倒计时
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                if (times >= 0) {
                    bindPhoneView.getTv_getcode().setText(times + "s");
                    times--;
                    sendEmptyMessageDelayed(1, 1000);
                } else {
                    bindPhoneView.getTv_getcode().setText(bindPhoneView.getThis().getString(R.string.verify_getcode));
                    bindPhoneView.getTv_getcode().setEnabled(true);
                    times = 30;
                }
            }
        }
    };

    private void getCode() {
        new UserApiImpl().userTelephoneVerifyToEveryBody(bindPhoneView.getPhone(), "reg", new NetReqCallback() {
            @Override
            public void apiRequestFail(String message,String method) {
                bindPhoneView.showFailedError(message);
                bindPhoneView.getTv_getcode().setEnabled(true);
                bindPhoneView.getTv_getcode().setText(bindPhoneView.getThis().getString(R.string.verify_getcode));

            }

            @Override
            public void apiRequestSuccess(Object o,String method) {
                bindPhoneView.getTv_getcode().setEnabled(false);
                handler.sendEmptyMessage(1);
            }
        });
    }

    public void postGetcode() {
        if (bindPhoneView.getPhone().length() <= 0) {
            bindPhoneView.showFailedError("请输入手机号");
            return;
        }
        bindPhoneView.getTv_getcode().setEnabled(false);
        bindPhoneView.getTv_getcode().setText("获取中...");
        getCode();
    }

    private void postLoginByWeibo(String uid, String access_token) {
        otherApi.WeiboBind(uid, access_token, "", bindPhoneView.getPhone(), bindPhoneView.getPwd(), bindPhoneView.getCode(), new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message,String method) {
                bindPhoneView.showFailedError(message);
            }

            @Override
            public void apiRequestSuccess(UserInfo userInfo,String method) {
                loginsuccess(userInfo);

            }
        });
    }

    public void postLogin(String id, String access_token, int tyep) {
        if (id == null || id.length() <= 0) {
            bindPhoneView.showFailedError("出现错误");
            return;
        }
        if (tyep == BindPhoneActivity.TYPE_WECHAT) {
            postLoginByWeChat(id, access_token);
        } else if (tyep == BindPhoneActivity.TYPE_WEIBO) {
            postLoginByWeibo(id, access_token);
        }
    }

    private void postLoginByWeChat(String openid, String access_token) {

        otherApi.WechatBind(openid, access_token, "", bindPhoneView.getPhone(), bindPhoneView.getPwd(), bindPhoneView.getCode(), new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message,String method) {
                bindPhoneView.showFailedError(message);
            }

            @Override
            public void apiRequestSuccess(UserInfo userLoginInfo,String method) {
                loginsuccess(userLoginInfo);
            }
        });
    }


    private void postLogin() {
        new UserApiImpl().userLogin(bindPhoneView.getPhone(), bindPhoneView.getPwd(), "", new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message,String method) {
                bindPhoneView.showFailedError(message);
            }

            @Override
            public void apiRequestSuccess(UserInfo userLoginInfo,String method) {
                loginsuccess(userLoginInfo);
            }
        });
    }

    public void changeTelephone() {
        new UserApiImpl().usechangeTelephone(bindPhoneView.getThis().getUUId(),
                bindPhoneView.getPhone(), bindPhoneView.getPwd(), bindPhoneView.getCode(), new NetReqCallback() {
                    @Override
                    public void apiRequestFail(String message,String method) {
                        bindPhoneView.showFailedError(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object o,String method) {
                        bindPhoneView.showFailedError("绑定成功,正在自动登录");
                        postLogin();
                    }
                });
    }

    private void loginsuccess(UserInfo userInfo) {
        getAccountManager().login(userInfo);
        RongyunUtils.connect(bindPhoneView.getThis().getAccount().rongyun_token.getRongyun_buyer(), bindPhoneView.getThis().getBaseContext(), new RongyunUtils.CallBackUid() {
            @Override
            public void back(String userid) {
                UserInfo userInfo = bindPhoneView.getThis().getAccount();
                SpMananger.saveChat_id(userid);
                AccountDataRepo.getAccountManager().remove();
                AccountDataRepo.getAccountManager().save(userInfo);
            }
        });

        Intent intent = new Intent();
        bindPhoneView.getThis().sendBroadcast(intent);
        intent.setAction(Constant.LOGIN_REFRESH);

        bindPhoneView.getThis().back();
        bindPhoneView.getThis().nextActivity(MainActivity.class);
        bindPhoneView.showFailedError("绑定成功,正在自动登录");

    }

}
