package com.prettyyes.user.react_native;

import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by chengang on 2017/8/4.
 */

public class RCTDeviceEventEmitter implements DefaultHardwareBackBtnHandler {
    @Override
    public void invokeDefaultOnBackPressed() {

    }
    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        if (reactContext==null) {
        }else{
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }

    }
}
