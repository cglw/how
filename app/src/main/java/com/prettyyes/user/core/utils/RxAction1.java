package com.prettyyes.user.core.utils;


import android.util.Log;

import io.reactivex.functions.Consumer;

/**
 * Created by chengang on 2017/8/4.
 */

public abstract class RxAction1<T> implements Consumer<T> {
    private static final String TAG ="RxAction1" ;

    @Override
    public void accept(T t) throws Exception {
        try {
            callback(t);
        } catch (Exception ee) {
            Log.e(TAG, "eeeee" + ee);
        }
    }
    public abstract void callback(T t);

}
