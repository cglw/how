package com.prettyyes.user.react_native;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.prettyyes.user.core.SpMananger;

import javax.annotation.Nullable;

/**
 * Created by chengang on 2017/8/3.
 *
 */

public class HowNativeEventManager {


    public static final String ResetRouteHome = "Home";
    public static final String ResetRouteMorePage = "morePage";

    public HowNativeEventManager() {
    }


    public void sendReplySuccess(ReactContext reactContext, String task_id) {
        WritableMap writableMap = Arguments.createMap();
        writableMap.putString("task_id", task_id);
        sendEvent(reactContext, "TaskReplySuccess", writableMap);

    }

    public void sendLoginStateChange(ReactContext reactContext) {
        WritableMap writableMap = Arguments.createMap();

        String uuid = SpMananger.getUUID();
        if (uuid == null)
            uuid = "";
        writableMap.putString("uuid", uuid);
        sendEvent(reactContext, "LoginStateChange", writableMap);

    }

    public void changeToHome(ReactContext reactContext) {
//        sendResetRoute(reactContext, ResetRouteHome);
    }

    public void changeToMorePage(ReactContext reactContext) {
//        sendResetRoute(reactContext, ResetRouteMorePage);

    }

    public void sendResetRoute(ReactContext reactContext, String routeName) {
        WritableMap writableMap = Arguments.createMap();
        writableMap.putString("routeName", routeName);
        sendEvent(reactContext, "ResetRoute", writableMap);
    }

    public static void sendEvent(ReactContext reactContext,
                                 String eventName,
                                 @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

}
