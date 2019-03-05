package com.prettyyes.user.core.utils;

import android.os.Handler;

import com.luck.picture.lib.rxbus2.RxBus;

import io.reactivex.disposables.Disposable;

/**
 * Created by Eric on 2017/1/20.
 */

public class AppRxBus {

    public AppRxBus() {
    }

    private static AppRxBus appRxBus;

    public static AppRxBus getInstance() {
        if (appRxBus == null) {
            synchronized (RxBus.class) {
                if (appRxBus == null) {
                    appRxBus = new AppRxBus();
                }
            }
        }
        return appRxBus;
    }

    public void post(Object obj) {
        RxBus.getDefault().post(obj);
    }

    public void postDely(final Object obj) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RxBus.getDefault().post(obj);

            }
        }, 1000);
    }

    public Disposable subscribeEvent(final RxCallback... callback) {
        Disposable mSubscription = null;

        for (int i = 0; i < callback.length; i++) {
            final int j = i;
            mSubscription = RxBus.getDefault().toObservable((Class<Object>) callback[i].getT()).subscribe(new RxAction1<Object>() {

                @Override
                public void callback(Object o) {
                    try {

                        callback[j].back(o);

                    } catch (Exception e) {
                    }
                }

            });
        }
        return mSubscription;
    }

    public void register(Object subscribe) {
        if (!RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().register(this);
        }
    }



    public void unregister(Object subscribe) {
        if (subscribe instanceof Disposable) {
//            if (((Disposable) subscribe).isDisposed())
                ((Disposable) subscribe).dispose();
        }

    }


}
