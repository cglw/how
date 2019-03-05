package com.prettyyes.user.api;


/**
 * Created by Cg on 2016/7/1.
 */
public abstract interface NetReqCallback<T> {
    public abstract void apiRequestFail(String message, String method);

    public abstract void apiRequestSuccess(T t, String method);

}
