package com.prettyyes.user.app.account;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.CookieManager;

import com.hornen.storage.StorageProxy;
import com.igexin.sdk.PushManager;
import com.prettyyes.user.AppConfig;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.UserInfoRequest;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.LoginChangeEvent;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.AlertMessageResponse;
import com.prettyyes.user.model.user.UserInfo;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;

import static com.prettyyes.user.app.account.Constant.SP_FIRST_USE;
import static com.prettyyes.user.app.account.Constant.SP_USER_INFO;
import static com.prettyyes.user.app.base.BaseApplication.userInfo;

/**
 * Created by Hornen on 15/11/24.
 */
public class AccountDataRepo {
    public final static String ACCOUNT_ZONENAME = "account_zonename";

    public static final String TAG = "AccountDataRepo";


    private StorageProxy storageProxy;


    public AccountDataRepo() {
    }

    public static AccountDataRepo getAccountManager() {
        return AccountDataRepoManager.instance;
    }

    private static class AccountDataRepoManager {
        static AccountDataRepo instance = new AccountDataRepo(BaseApplication.getAppContext());
    }


    public AccountDataRepo(Context cxt) {
        storageProxy = new StorageProxy(cxt, ACCOUNT_ZONENAME);
    }

    public boolean hasLogined() {
        return storageProxy.exist(SP_USER_INFO);

    }

    public void refreshUseInfoLocal() {
        new UserInfoRequest().post(new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
            }

            @Override
            public void apiRequestSuccess(UserInfo userInfo, String method) {
                save(userInfo);
            }
        });
    }


    public void logout() {
        CookieManager.getInstance().removeAllCookie();
        storageProxy.remove(SP_USER_INFO);
        if (userInfo != null) {
            userInfo = null;
        }
        RongIMClient.getInstance().logout();
        BaseApplication.UUID = null;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppRxBus.getInstance().post(new LoginChangeEvent());


    }

    public void remove() {
        storageProxy.remove(SP_USER_INFO);
        if (userInfo != null) {
            userInfo = null;
        }
    }

    public void save(UserInfo info) {
        userInfo = info;
        BaseApplication.UUID = info.getUuid();
        storageProxy.save(SP_USER_INFO, info);
//        InputProvider.ExtendProvider[] ep = {new CameraInputProvider(RongContext.getInstance()), new ImageInputProvider(RongContext.getInstance())};
//        //我需要让他在什么会话类型中的 bar 展示
//        RongIM.resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, ep);
//        RongIM.resetInputExtensionProvider(Conversation.ConversationType.CUSTOMER_SERVICE, ep);

    }


    public void login(UserInfo userInfo) {
        String newbie_task = userInfo.getNewbie_task();
        if (newbie_task != null && newbie_task.length() > 0) {
            AppUtil.showToastShort(newbie_task);
            AppRxBus.getInstance().post(new TaskCompleteEvent());
        }


        save(userInfo);
        new UserInfoRequest().post(new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(UserInfo userInfo, String method) {
                save(userInfo);
            }
        });
        if (!PushManager.getInstance().isPushTurnedOn(BaseApplication.getAppContext())) {
            PushManager.getInstance().turnOnPush(BaseApplication.getAppContext());
        }


        AlertMessageResponse.isNeedShow(userInfo.getAlert_message(), DataCenter.CouponGetType.REGISTER);
        AppRxBus.getInstance().post(new LoginChangeEvent());

    }


    public void chatWithSeller(final String seller_id, final Context context) {
        if (JumpPageManager.getManager().checkUnlogin(null))
            return;
        if (StringUtils.strIsEmpty(seller_id))
            return;
        if (SpMananger.getChat_id() != null) {
            if (RongIM.getInstance() != null)
                RongIM.getInstance().startPrivateChat(context, seller_id, "title");
        }

    }

    public void chatWithSeller(String targetUserId, String json) {
        if (JumpPageManager.getManager().checkUnlogin(BaseApplication.context))
            return;
        if (!TextUtils.isEmpty(targetUserId)) {
            if (RongContext.getInstance() == null) {
                throw new ExceptionInInitializerError("RongCloud SDK not init");
            } else {

                Uri uri = Uri.parse("rong://" + BaseApplication.context.getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversation").appendPath(Conversation.ConversationType.PRIVATE.getName().toLowerCase())
                        .appendQueryParameter("targetId", targetUserId)
                        .appendQueryParameter("data", json)
                        .build();
                ZBundleCore.getInstance().getTopActivity().startActivity(new Intent("android.intent.action.VIEW", uri));
            }
        } else {
//            throw new IllegalArgumentException();
        }
    }

    public void chatWithSeller(Context context, String targetUserId, String order_id) {
        if (JumpPageManager.getManager().checkUnlogin(null))
            return;
        if (context != null && !TextUtils.isEmpty(targetUserId)) {
            if (RongContext.getInstance() == null) {
                throw new ExceptionInInitializerError("RongCloud SDK not init");
            } else {

                Uri uri = Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversation").appendPath(Conversation.ConversationType.PRIVATE.getName().toLowerCase())
                        .appendQueryParameter("targetId", targetUserId)
                        .appendQueryParameter("order_id", order_id)
                        .build();
                context.startActivity(new Intent("android.intent.action.VIEW", uri));
            }
        } else {
//            throw new IllegalArgumentException();
        }
    }

    public void chatWithkf() {
        if (JumpPageManager.getManager().checkUnlogin(null))
            return;
        if (SpMananger.getUserInfo() == null || SpMananger.getChat_id() == null) {
            AppUtil.showToastShort("未获取到聊天账号");
            return;
        }
        //首先需要构造使用客服者的用户信息
        //首先需要构造使用客服者的用户信息
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
        RongIM.getInstance().startCustomerServiceChat(ZBundleCore.getInstance().getTopActivity(), AppConfig.KF, "在线客服", csInfo);
    }


    public UserInfo getAccount() {
        return storageProxy.resolve(SP_USER_INFO, UserInfo.class);
    }


    public boolean isFirstUse() {
        if (SpMananger.getUUID() != null && SpMananger.getUUID().length() > 0)
            return false;
        Boolean resolve = storageProxy.resolve(SP_FIRST_USE, Boolean.class);
        //第一次默认为false
//        storageProxy.save(SP_FIRST_USE, true);

        return !resolve;
    }

    public void UseApp() {
        storageProxy.save(SP_FIRST_USE, true);
    }

}
