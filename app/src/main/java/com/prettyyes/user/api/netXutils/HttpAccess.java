package com.prettyyes.user.api.netXutils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.hornen.common.MD5;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.prettyyes.user.AppConfig;
import com.prettyyes.user.api.ApiResponse;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.app.account.ACache;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.prettyyes.user.AppConfig.CONNECT_TIMEOUT;
import static com.prettyyes.user.AppConfig.CONNECT_TIMEOUT_FIEL;
import static com.prettyyes.user.AppConfig.CONNECT_TIMEOUT_FIEL_DOWN;
import static com.prettyyes.user.AppConfig.CONNECT_TIMEOUT_VIDEO_FIEL;
import static com.prettyyes.user.api.netXutils.urls.UserUrl.Url_userUploadimg;

/**
 * Created by Cg on 2016/7/1.
 * 网络请求的核心类，包含Get,Post（提交Map或提交Json）
 * 带进度的文件上传和文件下载
 * 加载图片
 */
public class HttpAccess<T> {
    private static final String TAG = "HttpAccess";
    private static Map<String, Callback.Cancelable> cancelList = new HashMap();
    private static Map<String, Callback.ProgressCallback.Cancelable> imgcancelList = new HashMap();
    private static HttpAccess httpAccess;//加载设置

    public static HttpAccess getInstance() {
        if (httpAccess == null) {
            httpAccess = new HttpAccess();
            mACache = ACache.get(BaseApplication.getAppContext());

        }
        return httpAccess;
    }

    private static ACache mACache;//用于缓存处理

    private String getCache(String key) {
        return mACache.getAsString(key);
    }

    private void putCache(String key, String data) {
        mACache.remove(key);
        mACache.put(key, data, 2 * ACache.TIME_HOUR);
    }


    /**
     * Get请求
     *
     * @param url
     * @param paramType
     * @param paramNetWorkCallback
     */
    private void get(final String url, final Type paramType, final NetReqCallback paramNetWorkCallback) {
        LogUtil.i(TAG, "get:" + url);
        Callback.Cancelable localCancelable = x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            public void onCancelled(Callback.CancelledException paramAnonymousCancelledException) {
            }

            public void onError(Throwable paramAnonymousThrowable, boolean paramAnonymousBoolean) {
                getErrorToCallback(paramNetWorkCallback, paramAnonymousThrowable, url);
            }

            public void onFinished() {
            }

            public void onSuccess(String result) {
                LogUtil.i(TAG, url + "->" + result);
                try {
                    ApiResContent o = GsonHelper.getGson().fromJson(result, paramType);
                    paramNetWorkCallback.apiRequestSuccess(o.getExtra(), url);
                } catch (Exception localException) {
                    paramNetWorkCallback.apiRequestFail(localException.getMessage(), url);
                }
            }
        });
        cancelList.put(url, localCancelable);
    }


    /**
     * Post提交Map
     *
     * @param url
     * @param paramMap
     * @param paramType
     * @param paramNetWorkCallback
     */
    private void post(final String url, final Map<String, Object> paramMap, final Type paramType, final NetWorkCallback paramNetWorkCallback) throws Exception {
        LogUtil.i(TAG, String.format("post:%s", url));

        RequestParams requestParams = getParams(url, paramMap, null);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            public void onCancelled(Callback.CancelledException paramAnonymousCancelledException) {
            }

            public void onError(Throwable paramAnonymousThrowable, boolean paramAnonymousBoolean) {
                String cache = getCache(MD5.getMD5Str(JSON.toJSONString(paramMap)));
                if (null != cache) {
                    JSONObject jsonObject = null;
                    ApiResContent apiResContent = null;
                    try {
                        jsonObject = new JSONObject(cache);
                        apiResContent = (ApiResContent) GsonHelper.getGson().fromJson(cache, paramType);
                        if (apiResContent.isSuccess()) {
                            paramNetWorkCallback.apiRequestSuccess(apiResContent);
                        } else {
                            paramNetWorkCallback.apiRequestFail("", apiResContent.getMsg());
                        }

                    } catch (Exception ee) {
                        JSONArray jsonArray = jsonObject.optJSONArray("extra");
                        apiResContent.setMsg(jsonObject.optString("msg"));
                        apiResContent.setRes(jsonObject.optString("res"));
                        apiResContent.setExtra(jsonArray);
                        try {
                            if (apiResContent.isSuccess())
                                paramNetWorkCallback.apiRequestSuccess(apiResContent);
                            else {
                                paramNetWorkCallback.apiRequestFail("", apiResContent.getMsg());
                            }
                        } catch (DbException e) {
                            getErrorToCallback(paramNetWorkCallback, paramAnonymousThrowable);
                        }
                    }
                } else
                    getErrorToCallback(paramNetWorkCallback, paramAnonymousThrowable);

            }

            public void onFinished() {

            }

            public void onSuccess(String result) {
                LogUtil.i(TAG, String.format("result:%s", result));

                String msg = "未知错误";
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    msg = jsonObject.optString("msg");
                    ApiResContent apiResContent = (ApiResContent) GsonHelper.getGson().fromJson(result, paramType);
                    if (apiResContent.isSuccess()) {
                        paramNetWorkCallback.apiRequestSuccess(apiResContent);
                        putCache(MD5.getMD5Str(JSON.toJSONString(paramMap)), result);
                    } else {
                        paramNetWorkCallback.apiRequestFail("", apiResContent.getMsg());

                    }
                } catch (Exception Exception) {
                    JSONArray jsonArray = jsonObject.optJSONArray("extra");
                    ApiResContent apiResContent = new ApiResContent();
                    apiResContent.setMsg(jsonObject.optString("msg"));
                    apiResContent.setRes(jsonObject.optString("res"));
                    apiResContent.setExtra(jsonArray);

                    try {
                        if (apiResContent.isSuccess()) {
                            paramNetWorkCallback.apiRequestSuccess(apiResContent);
                            putCache(MD5.getMD5Str(JSON.toJSONString(paramMap)), result);
                        } else {
                            paramNetWorkCallback.apiRequestFail("", apiResContent.getMsg());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    /**
     * Post提交Map
     *
     * @param url
     * @param paramMap
     * @param paramType
     * @param
     */
    private void post(final String url, final Map<String, Object> paramMap, final Type paramType, final NetReqCallback netReqCallback, final Boolean... booleen) throws Exception {
        RequestParams requestParams = getParams(url, paramMap, null);

        Callback.CommonCallback<String> commonCallback = new Callback.CommonCallback<String>() {
            public void onCancelled(CancelledException paramAnonymousCancelledException) {
            }

            public void onError(Throwable paramAnonymousThrowable, boolean paramAnonymousBoolean) {
                LogUtil.i(TAG, "paramAnonymousThrowable" + paramAnonymousThrowable);

                if (booleen != null && booleen.length > 0 && booleen[0]) {
                    String cache = null;

                    cache = getCache(MD5.getMD5Str(JSON.toJSONString(paramMap)));
                    if (cache != null) {
                        parserResponse(cache, url, null, paramType, paramMap, netReqCallback, booleen);
                        return;
                    }
                }
//                LogUtil.i(TAG, url + "error-->" + paramAnonymousThrowable);
//                if (null != finalCache) {
//                    parserResponse(finalCache, url, null, paramType, paramMap, netReqCallback, booleen);
//                } else
                getErrorToCallback(netReqCallback, paramAnonymousThrowable, url);
            }

            public void onFinished() {

            }

            public void onSuccess(String result) {
                LogUtil.i(TAG, String.format("%s->result:%s", url, result));

                parserResponse(null, url, result, paramType, paramMap, netReqCallback, booleen);

            }
        };


        x.http().post(requestParams, commonCallback);

    }


    /**
     * Post提交json
     *
     * @param url
     * @param paramMap
     * @param paramType
     * @param paramNetWorkCallback
     */
    private void postJson(String url, Map<String, String> paramMap, final Type paramType, final NetWorkCallback<T> paramNetWorkCallback) {


        x.http().post(getParamsTojson(url, paramMap), new Callback.CommonCallback<String>() {
            public void onCancelled(Callback.CancelledException paramAnonymousCancelledException) {
            }

            public void onError(Throwable paramAnonymousThrowable, boolean paramAnonymousBoolean) {
                getErrorToCallback(paramNetWorkCallback, paramAnonymousThrowable);
            }

            public void onFinished() {
            }

            public void onSuccess(String result) {
                try {
                    paramNetWorkCallback.apiRequestSuccess((ApiResContent) GsonHelper.getGson().fromJson(result, paramType));
                    return;
                } catch (Exception localException) {
                    paramNetWorkCallback.apiRequestFail(localException.getMessage(), "");
                }
            }


        });
    }

    /**
     * 上传文件,并回调进度
     *
     * @param url
     * @param paramMap
     * @param filepath
     * @param paramProgressCallback
     */
    private void uploadFile(final String url, Map<String, Object> paramMap, final String filepath, final ProgressCallback paramProgressCallback) throws Exception {
        Callback.ProgressCallback<String> callback = new Callback.ProgressCallback<String>() {
            public void onCancelled(CancelledException paramAnonymousCancelledException) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.i(TAG, "uploadFile_onError" + ex);

                paramProgressCallback.onFail(ex.getLocalizedMessage(), ex.getMessage());
            }

            public void onFinished() {
                paramProgressCallback.onFinish();
            }

            public void onStarted() {
                paramProgressCallback.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                LogUtil.i(TAG, "uploadFile_base" + total + "-->" + current + "--->" + isDownloading);

                paramProgressCallback.onLoading(total, current, isDownloading);
            }

            public void onSuccess(String paramAnonymousString) {
                LogUtil.i(TAG, "uploadFile_onSuccess" + paramAnonymousString);
                ApiResContent apiResContent = GsonHelper.getGson().fromJson(paramAnonymousString, ApiResContent.class);
                if (apiResContent.isSuccess())
                    paramProgressCallback.onSuccess(paramAnonymousString);
                else
                    paramProgressCallback.onFail(apiResContent.getMsg(), apiResContent.getMsg());

                removeKey(filepath);

            }

            public void onWaiting() {
            }
        };
        Callback.Cancelable post = x.http().post(getParams(url, paramMap, null), callback);


        String format = String.format("%s?path=%s", url, filepath);
        LogUtil.i(TAG, "save_key" + format);
        cancelList.put(format, post);

    }


    public static Map<String, Callback.Cancelable> getCancelList() {
        return cancelList;
    }

    /**
     * 上传文件,并回调进度
     *
     * @param url
     * @param paramMap
     * @param
     * @param paramProgressCallback
     */
    private void uploadFiles(final String url, Map<String, String> paramMap, final ArrayList<String> filepaths, final ProgressCallback paramProgressCallback) throws Exception {
        Callback.Cancelable post = x.http().post(getFileParams(url, paramMap, filepaths), new Callback.ProgressCallback<String>() {
            public void onCancelled(CancelledException paramAnonymousCancelledException) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                paramProgressCallback.onFail(ex.getLocalizedMessage(), ex.getMessage());
                LogUtil.i(TAG, "uploadFile_onError" + ex.getMessage());
            }

            public void onFinished() {
                paramProgressCallback.onFinish();
            }

            public void onStarted() {
                paramProgressCallback.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                paramProgressCallback.onLoading(total, current, isDownloading);
            }

            public void onSuccess(String paramAnonymousString) {
                ApiResContent apiResContent = GsonHelper.getGson().fromJson(paramAnonymousString, ApiResContent.class);
                if (apiResContent.isSuccess())
                    paramProgressCallback.onSuccess(paramAnonymousString);
                else
                    paramProgressCallback.onFail(apiResContent.getMsg(), apiResContent.getMsg());
                for (String path : filepaths) {
                    removeKey(path);
                }
            }

            public void onWaiting() {
            }
        });
        for (String path : filepaths) {
            String format1 = String.format("%s?path=%s", url, path);
            LogUtil.i(TAG, "save_key" + format1);
            cancelList.put(format1, post);

        }


    }

    /**
     * 下载文件，并回调进度
     *
     * @param url
     * @param filepath
     * @param paramProgressCallback
     */
    private void downloadFile(final String url, String filepath, final ProgressCallback paramProgressCallback) {
        Callback.Cancelable localCancelable = x.http().get(downloadFileParams(url, filepath), new Callback.ProgressCallback<File>() {
            public void onCancelled(Callback.CancelledException paramAnonymousCancelledException) {
            }

            @Override
            public void onSuccess(File file) {
                paramProgressCallback.onSuccess("下载成功");
                removeKey(url);


            }

            public void onError(Throwable paramAnonymousThrowable, boolean paramAnonymousBoolean) {

                paramProgressCallback.onFail(paramAnonymousThrowable.getLocalizedMessage(), paramAnonymousThrowable.getMessage());
            }

            public void onFinished() {
                paramProgressCallback.onFinish();
            }

            public void onStarted() {
                paramProgressCallback.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                paramProgressCallback.onLoading(total, current, isDownloading);

            }

            public void onWaiting() {
            }
        });
        cancelList.put(url, localCancelable);
    }

    /**
     * 下载文件的参数设置
     *
     * @param url
     * @param filepath
     * @return
     */
    private RequestParams downloadFileParams(String url, String filepath) {
        RequestParams localRequestParams = new RequestParams(url);
        localRequestParams.setConnectTimeout(CONNECT_TIMEOUT_FIEL_DOWN);
        localRequestParams.setAutoResume(true);
        localRequestParams.setSaveFilePath(filepath);
        localRequestParams.setCancelFast(true);
        return localRequestParams;
    }

    /**
     * Post请求拼接参数
     *
     * @param url
     * @param paramMap
     * @param filepath
     * @return
     */
    private RequestParams getParams(String url, Map<String, Object> paramMap, String filepath) throws Exception {
        RequestParams localRequestParams = new RequestParams(url);
        localRequestParams.setConnectTimeout(CONNECT_TIMEOUT);
        if(!paramMap.containsKey("uuid")) {
            paramMap.put("idfa", AppUtil.getIdfa());
            paramMap.put("channel", AppUtil.getAppChannel());
            paramMap.put("uuid", BaseApplication.UUID);
            paramMap.put("version", AppUtil.getVersionName());
            paramMap.put("url", url);

        }

        String plainTxt = new Gson().toJson(paramMap);//提交的String
        LogUtil.i(TAG, "RequestParams" +url+"-->"+ paramMap.toString());

        //进行两次加密
        String desResult = DesUtils.encrypt(plainTxt);
        String mytext = java.net.URLEncoder.encode(desResult, "utf-8");
        localRequestParams.addBodyParameter(AppConfig.DesStringKey, mytext);

        LogUtil.i(TAG, "desResult" + mytext);

        for (String key : paramMap.keySet()) {
            if (paramMap.get(key) instanceof File) {
                String toType = PictureMimeType.fileToType((File) paramMap.get(key));
                boolean eqVideo = toType.startsWith(PictureConfig.VIDEO);
                if (eqVideo)
                    localRequestParams.setConnectTimeout(CONNECT_TIMEOUT_VIDEO_FIEL);
                else
                    localRequestParams.setConnectTimeout(CONNECT_TIMEOUT_FIEL);
                localRequestParams.addBodyParameter(key, (File) paramMap.get(key));
            }
        }
        localRequestParams.addHeader("Accept", AppConfig.ServerVersion);
        return localRequestParams;
    }

    /**
     * Post请求拼接参数
     *
     * @param url
     * @param paramMap
     * @param filepath
     * @return
     */
    private RequestParams getFileParams(String url, Map<String, String> paramMap, ArrayList<String> filepath) throws Exception {
        RequestParams localRequestParams = new RequestParams(url);
        localRequestParams.setConnectTimeout(CONNECT_TIMEOUT);
        String plainTxt = new Gson().toJson(paramMap);//提交的String
        //进行两次加密
        String desResult = DesUtils.encrypt(plainTxt);
        String mytext = java.net.URLEncoder.encode(desResult, "utf-8");
        localRequestParams.addBodyParameter(AppConfig.DesStringKey, mytext);
        for (int i = 0; i < filepath.size(); i++) {
            localRequestParams.addBodyParameter("binary_img[]", new File(filepath.get(i)));
        }
        localRequestParams.addHeader("Accept", AppConfig.ServerVersion);
        return localRequestParams;
    }

    /**
     * Map转成json字符串
     *
     * @param url
     * @param paramMap
     * @return
     */
    private RequestParams getParamsTojson(String url, Map<String, String> paramMap) {
        RequestParams localRequestParams = new RequestParams(url);
        localRequestParams.setCharset("utf-8");
        localRequestParams.setBodyContent(new Gson().toJson(paramMap));
        localRequestParams.setAsJsonContent(true);
        localRequestParams.addHeader("Accept", "application/pretty.yes.v3+json");
        return localRequestParams;
    }

    /**
     * 取消下载
     *
     * @param url
     */
    private void cancel(String url) {
        LogUtil.i(TAG, "cancelupload--path" + url + cancelList.size());

        if (url == null)
            return;

        String cancel_key = null;
        for (String key : cancelList.keySet()) {
            LogUtil.i(TAG, "key--" + key);
            if (key != null && key.contains(url)) {
                Callback.Cancelable localCancelable = cancelList.get(key);
                if (localCancelable == null) {
                    key = String.format("%s?path=%s", Url_userUploadimg, url);

                    localCancelable = cancelList.get(key);
                }
                if (localCancelable == null) {
                    key = String.format("%s?path=%s", AppConfig.getUrl() + "/app/user/uploadVideo", url);
                    localCancelable = cancelList.get(key);
                }
                if (localCancelable != null) {
                    localCancelable.cancel();
                    cancel_key = key;

                }
            }

        }
        if (cancel_key != null)
            cancelList.remove(cancel_key);
    }

    private void removeKey(String key) {
        String rm_key1 = String.format("%s?path=%s", Url_userUploadimg, key);
        String rm_key2 = String.format("%s?path=%s", AppConfig.getUrl() + "/app/user/uploadVideo", key);
        cancelList.remove(key);
        cancelList.remove(rm_key1);
        cancelList.remove(rm_key2);
    }


    public void cancelDownLoad(String url) {
        cancel(url);
    }

    public void cancelUpLoad(String url) {
        cancel(url);
    }


    /**
     * 对外暴露的Get请求
     *
     * @param url
     * @param paramType
     * @param
     */
    public void getAsyn(String url, Type paramType, NetReqCallback netReqCallback) {
        getInstance().get(url, paramType, netReqCallback);
    }

    /**
     * 对位暴露的Post请求，提交的是Map
     *
     * @param url
     * @param paramMap
     * @param paramType
     * @param paramNetWorkCallback
     */
    public void postAsyn(String url, Map<String, String> paramMap, Type paramType, NetWorkCallback paramNetWorkCallback) throws Exception {

        getInstance().post(url, paramMap, paramType, paramNetWorkCallback);
    }

    /**
     * @param url            请求url
     * @param paramMap       拼接的参数
     * @param paramType      返回实体类型
     * @param netReqCallback 回调接口
     * @param booleen        //是否需要缓存数据  需要缓存的话需要设置为true
     * @throws Exception
     */
    public void postAsyn(String url, Map<String, String> paramMap, Type paramType, NetReqCallback netReqCallback, Boolean... booleen) throws Exception {
        getInstance().post(url, paramMap, paramType, netReqCallback, booleen);
    }

    /**
     * 对外暴露的Post,提交的是Json，提交的json可以使用Aes加密（待添加）
     *
     * @param url
     * @param paramMap
     * @param paramType
     * @param paramNetWorkCallback
     */
    public void postJsonAsyn(String url, Map<String, String> paramMap, Type paramType, NetWorkCallback<ApiResponse> paramNetWorkCallback) {
        getInstance().postJson(url, paramMap, paramType, paramNetWorkCallback);
    }

    /**
     * 对位暴露的文件上传
     *
     * @param url
     * @param paramMap
     * @param filepath
     * @param paramProgressCallback
     */
    public void upLoadFileAsyn(String url, Map<String, String> paramMap, String filepath, ProgressCallback paramProgressCallback) throws Exception {
        getInstance().uploadFile(url, paramMap, filepath, paramProgressCallback);
    }

    /**
     * 对位暴露的文件上传
     *
     * @param url
     * @param paramMap
     * @param filepath
     * @param paramProgressCallback
     */
    public void upLoadFilesAsyn(String url, Map<String, String> paramMap, ArrayList filepath, ProgressCallback paramProgressCallback) throws Exception {
        getInstance().uploadFiles(url, paramMap, filepath, paramProgressCallback);
    }

    /**
     * 对外暴露的文件下载
     *
     * @param url
     * @param filepath
     * @param paramProgressCallback
     */

    public void downLoadFileAsyn(String url, String filepath, ProgressCallback paramProgressCallback) {
        getInstance().downloadFile(url, filepath, paramProgressCallback);
    }

    /**
     * 网络请求失败 回调的错误 code和Message
     *
     * @param netWorkCallback
     * @param ex
     */
    private void getErrorToCallback(NetWorkCallback netWorkCallback, Throwable ex) {
        if (ex instanceof HttpException) {
            HttpException httpEx = (HttpException) ex;
            int responseCode = httpEx.getCode();
            String responseMsg = httpEx.getMessage();
            netWorkCallback.apiRequestFail(responseCode + "", responseMsg);
        } else {
            netWorkCallback.apiRequestFail("default", "网络异常");
        }
    }

    /**
     * 网络请求失败 回调的错误 code和Message
     *
     * @param netWorkCallback
     * @param ex
     */
    private void getErrorToCallback(NetReqCallback netWorkCallback, Throwable ex, String url) {
        if (ex instanceof HttpException) {
            HttpException httpEx = (HttpException) ex;
            int responseCode = httpEx.getCode();
            String responseMsg = httpEx.getMessage();
            //showToast(responseMsg);
            if (netWorkCallback != null)
                netWorkCallback.apiRequestFail(responseMsg, url);

            LogUtil.i(TAG, ex + ex.getMessage());
        } else {
            // showToast("网络异常");
            if (netWorkCallback != null)
                netWorkCallback.apiRequestFail("您的网络状况不太好哦！", url);
        }
    }

    /**
     * 处理返回的结果
     *
     * @param result
     * @param paramType
     * @param paramMap
     * @param netReqCallback
     * @param booleen
     */
    protected void parserResponse(String cache, String url, String result, final Type paramType, final Map<String, Object> paramMap, NetReqCallback netReqCallback, Boolean... booleen) {
        JSONObject response = null;

        String method = (String) paramMap.get("method");
        if (!StringUtils.strIsEmpty(result)) {
            try {
                response = new JSONObject(result);
                String code = response.optString("res");
                Object message = response.optString("msg");
                if (code.equals("200")) {//请求成功
                    LogUtil.i(TAG, "paramType" + paramType);

                    ApiResContent apiResContent = GsonHelper.getGson().fromJson(result, paramType);
                    if (null != booleen && booleen.length > 0 && booleen[0] == true)// 默认是不缓存数据的
                    {
                        if (cache == null && netReqCallback != null) {

                            netReqCallback.apiRequestSuccess(apiResContent.getExtra(), method);//回调数据

                        }
                        String key = JSON.toJSONString(paramMap);
                        putCache(MD5.getMD5Str(key), result);//缓存数据}
                        LogUtil.i(TAG, "putCache" + MD5.getMD5Str(key) + "\n" + "data-->" + result);

                    } else {
                        if (netReqCallback != null)
                            netReqCallback.apiRequestSuccess(apiResContent.getExtra(), method);//回调数据

                    }
                    return;
                } else {
                    //  showToast(message);
                    if (netReqCallback != null)
                        netReqCallback.apiRequestFail(message + "", method);
                }
            } catch (Exception e) {
                LogUtil.i(TAG, url + "error-->" + e);
                e.printStackTrace();
                if (netReqCallback != null)
                    netReqCallback.apiRequestFail(e + "", method);

            }

        }

        if (null != cache) {
            try {
                JSONObject response_cache = new JSONObject(cache);
                String code = response_cache.optString("res");
                String message = response_cache.optString("msg");
                if (code.equals("200")) {//请求成功
                    ApiResContent apiResContent = GsonHelper.getGson().fromJson(cache, paramType);
                    if (netReqCallback != null) {
                        netReqCallback.apiRequestSuccess(apiResContent.getExtra(), method);//回调数据
                        LogUtil.i(TAG, "cache-->callback" + apiResContent.getExtra());
                    }
                    return;
                } else {
                    if (netReqCallback != null) {
                        netReqCallback.apiRequestFail(message, method);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (netReqCallback != null)
                    netReqCallback.apiRequestFail(e + "", method);

            }
        }


    }


}
