package com.prettyyes.user.core.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by chengang on 2017/10/9.
 */

public abstract class RxCallback<T> {
    private static final String TAG = "TestCallback";

    protected abstract void back(T obj);

    public T getT() {
        Type genType = this.getClass().getGenericSuperclass();
        final Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (T) params[0];

    }

}
