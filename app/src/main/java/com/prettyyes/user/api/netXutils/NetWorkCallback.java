package com.prettyyes.user.api.netXutils;


import org.xutils.ex.DbException;

/**
 * Created by Cg on 2016/7/1.
 */
public abstract interface NetWorkCallback<T> {
	public abstract void apiRequestFail(String code, String message);
	public abstract void apiRequestSuccess(ApiResContent<T> apiResponse) throws DbException;

}
