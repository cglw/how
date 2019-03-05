package com.prettyyes.user.app.base;

import android.os.Bundle;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

/**
 * Created by chengang on 2017/7/31.
 */

public abstract class BaseReactActivity extends BaseActivity
        implements DefaultHardwareBackBtnHandler {

    private static final String TAG = "BaseReactActivity";


    /*
       * Get the ReactInstanceManager, AKA the bridge between JS and Android
       * We use a singleton here so we can reuse the appacountinstance throughout our app
       * instead of constantly re-instantiating and re-downloading the bundle
       */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Get the reference to the ReactInstanceManager
         */
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    /*
     * Any activity that uses the ReactFragment or ReactActivty
     * Needs to call onHostPause() on the ReactInstanceManager
     */
    @Override
    protected void onPause() {
        super.onPause();

        if (MainApplication.getReactInstanceManager() != null) {
            MainApplication.getReactInstanceManager().onHostPause();
        }
    }

    /*
     * Same as onPause - need to call onHostResume
     * on our ReactInstanceManager
     */
    @Override
    protected void onResume() {
        super.onResume();

        if (MainApplication.getReactInstanceManager() != null) {
            MainApplication.getReactInstanceManager().onHostResume(this, this);
        }
    }

}
