package com.prettyyes.user.ronyun.activity;

import android.content.Context;

import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.ronyun.activity
 * Author: SmileChen
 * Created on: 2016/8/31
 * Description: Nothing
 */
public class RongyunUtils {
    private static final String TAG = "RongyunUtils";

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    public static void connect(final String token, final Context context, final CallBackUid callBackUid) {
        RongIM.setOnReceiveMessageListener(new MyReceivePushMessageListener());
        if (context.getApplicationInfo().packageName.equals(AppUtil.getCurProcessName((context.getApplicationContext())))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    LogUtil.i(TAG, "onTokenIncorrect");

//                    connect(token,context,callBackUid);

                }

                @Override
                public void onSuccess(String s) {
                    LogUtil.i(TAG, "onSuccess" + s);
                    SpMananger.saveChat_id(s);

                    if (callBackUid != null) {
                        callBackUid.back(s);
                    }
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtil.i(TAG, "ErrorCode" + errorCode);
                }
            });

        }
    }

    public interface CallBackUid {
        public void back(String userid);
    }

    public static class MyReceivePushMessageListener implements RongIMClient.OnReceiveMessageListener {

        @Override
        public boolean onReceived(Message message, int i) {
            return false;
        }
    }

}
