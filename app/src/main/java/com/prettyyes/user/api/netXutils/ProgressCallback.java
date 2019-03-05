package com.prettyyes.user.api.netXutils;

/**
 * Created by Administrator on 2016/7/1.
 */
public abstract interface ProgressCallback {

    public abstract void onFail(String paramString1, String paramString2);

    public abstract void onFinish();

    public abstract void onLoading(long total, long current, boolean isDownloading);

    public abstract void onStart();

    public abstract void onSuccess(String result);
}
