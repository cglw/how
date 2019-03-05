package com.prettyyes.user.app.base;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;

/**
 * Created by chengang on 2017/7/27.
 */

public class MainApplication extends BaseApplication implements ReactApplication {
    private static ReactInstanceManager mReactInstanceManager;


    public static ReactInstanceManager getReactInstanceManager() {
        if (mReactInstanceManager == null) {
            if (reactApplication != null)
                mReactInstanceManager = reactApplication.getReactNativeHost().getReactInstanceManager();
        }
        return mReactInstanceManager;
    }


    public static ReactApplication reactApplication;


//    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
//
//        @Override
//        protected String getJSBundleFile() {
//            return CodePush.getJSBundleFile();
//        }
//
//        @Override
//        public boolean getUseDeveloperSupport() {
//            return BuildConfig.DEBUG;
//        }
//
//        @Override
//        protected List<ReactPackage> getPackages() {
//
//
//            String key = getResources().getString(R.string.reactNativeCodePush_androidDeploymentKey);
//            if (!AppConfig.isTest)
//                key = getResources().getString(R.string.reactNativeCodePush_androidDeploymentKey);
//
//            return Arrays.<ReactPackage>asList(
//                    new MainReactPackage(),
//                    new JsReactPackage(),
//                    new CodePush(key, getApplicationContext(), BuildConfig.DEBUG)
//            );
//        }
//    };


    @Override
    public ReactNativeHost getReactNativeHost() {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        SoLoader.init(this, /* native exopackage */ false);
        reactApplication = this;
    }
}

