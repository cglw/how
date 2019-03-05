package com.prettyyes.user.core;

import com.hornen.storage.StorageProxy;
import com.prettyyes.user.AppConfig;
import com.prettyyes.user.api.netXutils.urls.BaseUrl;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.user.UserInfo;

import static com.prettyyes.user.app.account.AccountDataRepo.ACCOUNT_ZONENAME;
import static com.prettyyes.user.app.account.Constant.RELEASE_OPEN_STATE;
import static com.prettyyes.user.app.account.Constant.SP_USER_CHAT;
import static com.prettyyes.user.app.account.Constant.SP_USER_INFO;

/**
 * Created by chengang on 2017/5/8.
 * 获取存储的个人信息
 */

public class SpMananger {
    private static final String TAG = "SpMananger";

    public SpMananger() {

    }

    private static StorageProxy storageProxy;

    public static StorageProxy getStorageProxy() {
        if (null == storageProxy) {
            synchronized (SpMananger.class) {
                if (null == storageProxy) {
                    storageProxy = new StorageProxy(BaseApplication.getAppContext(), ACCOUNT_ZONENAME);
                }
            }
        }
        return storageProxy;
    }

    public static String getUUID() {
        if (!StringUtils.strIsEmpty(BaseApplication.UUID))
            return BaseApplication.UUID;
        String uuid = null;
        UserInfo resolve = SpMananger.getStorageProxy().resolve(SP_USER_INFO, UserInfo.class);

        if (resolve != null) {
            uuid = resolve.getUuid();
            BaseApplication.UUID = uuid;

        }
        LogUtil.i(TAG, "uuid-->" + uuid);
        return uuid;
    }

    public static String getChat_id() {
        if (!StringUtils.strIsEmpty(BaseApplication.USER_CHAT_ID))
            return BaseApplication.USER_CHAT_ID;
        String chat_id = SpMananger.getStorageProxy().resolve(SP_USER_CHAT, String.class);
        return chat_id;
    }

    public static void saveChat_id(String chat_id) {
        getStorageProxy().save(Constant.SP_USER_CHAT, chat_id);
    }


    public static void loginSuccess() {
        getUUID();
    }

    public static void saveClient_id(String cid) {
        AppConfig.CLIENT_ID = cid;
        getStorageProxy().save(Constant.onReceiveClientId, cid);
    }

    public static String getClien_id() {
        if (StringUtils.strIsEmpty(AppConfig.CLIENT_ID)) {
            String resolve = getStorageProxy().resolve(Constant.onReceiveClientId, String.class);
            AppConfig.CLIENT_ID = resolve;
        }
        return AppConfig.CLIENT_ID;
    }


    public static UserInfo getUserInfo() {

        UserInfo resolve = null;
        if (BaseApplication.userInfo != null) {
            resolve = BaseApplication.userInfo;
            return resolve;
        }

        resolve = SpMananger.getStorageProxy().resolve(SP_USER_INFO, UserInfo.class);
        if (resolve != null) {
            BaseApplication.userInfo = resolve;
            BaseApplication.UUID = resolve.getUuid();
        }
        return resolve;
    }

    public static String getUid() {
        String uid = "";
        UserInfo userInfo = SpMananger.getUserInfo();
        if (userInfo != null)
            uid = userInfo.getUid();
        return uid;
    }

    public static boolean getIsRelease() {
        Boolean release_open_state = SpMananger.getStorageProxy().resolve(RELEASE_OPEN_STATE, Boolean.class);
        AppConfig.isTest = !release_open_state;
        BaseUrl.url = AppConfig.getUrl();
        return release_open_state;
    }

    public static boolean changeBaseUrl() {
        Boolean release_open_state = SpMananger.getStorageProxy().resolve(RELEASE_OPEN_STATE, Boolean.class);
        boolean need = !release_open_state;
        SpMananger.getStorageProxy().save(RELEASE_OPEN_STATE, need);
        AppConfig.isTest = need;
        BaseUrl.url = AppConfig.getUrl();
        AppUtil.showToastShort(String.format("当前切换至%s", need ? "测试服" : "正式服"));
        return need;
    }


}
