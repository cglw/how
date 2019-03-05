package com.prettyyes.user.app.receiver;

import android.content.Context;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by chengang on 2017/6/16.
 */

public class MyReceiver extends PushMessageReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {

        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }
}
