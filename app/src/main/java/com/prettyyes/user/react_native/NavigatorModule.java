package com.prettyyes.user.react_native;

import android.app.Activity;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.containter.ZBundleCore;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chengang on 2017/7/27.
 */

public class NavigatorModule extends ReactContextBaseJavaModule {

    private static final String TAG = "NavigatorModule";



    public NavigatorModule(final ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "NavigatorModule";
    }

    @ReactMethod
    public void show(String msg) {
        Toast.makeText(getReactApplicationContext(), "Js调用显示原生传递的参数是:" + msg, Toast.LENGTH_LONG).show();
    }

    @ReactMethod
    public void navigateTo(String msg) {
        Activity topActivity = ZBundleCore.getInstance().getTopActivity();
        if (topActivity != null) {
            try {
                JSONObject jsonObject = new JSONObject(msg);
                String rule = jsonObject.optString("rule");
                if (rule != null)
                    new PushHandler(topActivity).handReceiveData(rule);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }





}
