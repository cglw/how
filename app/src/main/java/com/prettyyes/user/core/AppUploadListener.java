package com.prettyyes.user.core;


/**
 * Created by chengang on 2017/6/6.
 */

public interface AppUploadListener<T> {




    void onProgress(long currentBytesCount, long totalBytesCount);

    /**
     * 成功后回调方法
     *
     * @param resulte
     * @param
     */
    void onNext(T resulte, String url);

    /**
     * 失败
     * 失败或者错误方法
     * 自定义异常处理
     *
     * @param e
     */
    void onError(String error);

}
