package com.prettyyes.user.core;

import android.content.Intent;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.api.netXutils.requests.LoginByQQReq;
import com.prettyyes.user.api.netXutils.requests.LoginBySinaReq;
import com.prettyyes.user.api.netXutils.requests.LoginByWechatReq;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.setting.BindPhoneActivity;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.other.WeChatBack;
import com.prettyyes.user.model.other.WeiBoBack;
import com.prettyyes.user.model.user.UserInfo;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.util.Map;

import static com.prettyyes.user.core.utils.AppUtil.showToastShort;

/**
 * Created by chengang on 2018/1/19.
 * 登录的逻辑处理类
 */

public class LoginHandler {

    public static LoginHandler create() {
        return new LoginHandler();
    }

    private static final String TAG = "LoginHandler";

    public BaseActivity activity;

    public LoginHandler setActivity(BaseActivity activity) {
        this.activity = activity;
        return this;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//      mShareAPI.onActivityResult(requestCode, resultCode, data);

    }

    public void loginWechat() {
        AppUtil.showToastShort("获取授权");
        mShareAPI = UMShareAPI.get(activity);
        SHARE_MEDIA platform = SHARE_MEDIA.WEIXIN;
        mShareAPI.isInstall(activity, SHARE_MEDIA.WEIXIN);
        mShareAPI.doOauthVerify(activity, platform, umAuthListener);
    }

    public void loginQQ() {
        LogUtil.i(TAG, "loginQQ");
        AppUtil.showToastShort("获取授权");
        mShareAPI = UMShareAPI.get(activity);
        SHARE_MEDIA platform = SHARE_MEDIA.QQ;
        mShareAPI.isInstall(activity, SHARE_MEDIA.QQ);
        mShareAPI.doOauthVerify(activity, platform, umAuthListener);

    }


    public void loginSina() {
        AppUtil.showToastShort("获取授权");
        mShareAPI = UMShareAPI.get(activity);
        SHARE_MEDIA platform = SHARE_MEDIA.SINA;
        mShareAPI.isInstall(activity, SHARE_MEDIA.SINA);
        mShareAPI.doOauthVerify(activity, platform, umAuthListener);
    }

    private UMShareAPI mShareAPI;
    private UMAuthListener umAuthListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            AppUtil.showToastShort("正在获取信息");
            getPlatInfo(platform, data);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            AppUtil.showToastShort("授权失败");


        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            AppUtil.showToastShort("授权取消");
        }
    };

    private void getPlatInfo(SHARE_MEDIA platform, Map<String, String> data) {
        LogUtil.i("getPlatInfo", data.toString());
        JSONObject jsonObject = new JSONObject(data);
        if (platform == SHARE_MEDIA.SINA) {
            WeiBoBack weiBoBack = GsonHelper.getGson().fromJson(jsonObject + "", WeiBoBack.class);
            back_id = weiBoBack.getUid();
            token = weiBoBack.getAccessToken();
            loginBySina(back_id, token);

        } else if (platform == SHARE_MEDIA.WEIXIN) {
            WeChatBack weChatBack = GsonHelper.getGson().fromJson(jsonObject + "", WeChatBack.class);
            back_id = weChatBack.getOpenid();
            token = weChatBack.getAccess_token();
            loginByWechat(back_id, token);

        } else if (platform == SHARE_MEDIA.QQ) {
            WeChatBack weChatBack = GsonHelper.getGson().fromJson(jsonObject + "", WeChatBack.class);
            back_id = weChatBack.getOpenid();
            token = weChatBack.getAccess_token();
            loginByQQ(back_id, token);

        }
    }

    private void loginByQQ(String back_id, String token) {
        new LoginByQQReq().setOpenId(back_id).setAccess_token(token).post(new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(UserInfo userInfo, String method) {
                loginsuccessByOther(userInfo, true, BindPhoneActivity.TYPE_QQ);


            }
        });
    }


    private void loginByWechat(String pid, final String token) {
        new LoginByWechatReq().setOpenId(pid).setAccess_token(token).post(new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(UserInfo userInfo, String method) {
                loginsuccessByOther(userInfo, true, BindPhoneActivity.TYPE_WECHAT);

            }
        });
    }

    private void loginBySina(String uid, String access_token) {
        new LoginBySinaReq().setUid(uid).setAccess_token(access_token).post(new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);

            }

            @Override
            public void apiRequestSuccess(UserInfo userInfo, String method) {
                loginsuccessByOther(userInfo, true, BindPhoneActivity.TYPE_WEIBO);

            }
        });

    }

    //    返回的用户id
    private String back_id;
    //    返回的访问token
    private String token;

    public void loginsuccessByOther(UserInfo userInfo, boolean isother, int... type) {
        if (userInfo.isRedirect_bind_page() && isother) {
            showToastShort("请绑定手机");
            Intent intent = new Intent(activity, BindPhoneActivity.class);
            if (type != null && type.length > 0) {
                intent.putExtra("type", type[0]);
                intent.putExtra("id", back_id);
                intent.putExtra("accesstoken", token);
            }
            activity.nextActivity(intent);
            return;
        }
        showToastShort("登录成功");
        AccountDataRepo.getAccountManager().login(userInfo);
        activity.sendBrodcast(Constant.LOGIN_REFRESH);
        activity.finish();
    }
}
