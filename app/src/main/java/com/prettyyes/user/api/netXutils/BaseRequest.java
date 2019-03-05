package com.prettyyes.user.api.netXutils;

import com.alibaba.fastjson.JSON;
import com.hornen.logger.Logger;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.app.account.ACache;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.umeng.socialize.sina.message.BaseResponse;

import org.xutils.common.util.MD5;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import static com.prettyyes.user.AppConfig.getUrl;

/**
 * Created by Hornen on 15/9/21.
 */
public abstract class BaseRequest<T> {
    public final String TAG = getClass().getName();
    private final static String SECRET_KEY = "UKOWEB802N";

    private ACache mACache;

    public BaseRequest() {
        mACache = ACache.get(BaseApplication.getAppContext());
    }

    private String generalCacheKey() {

        Map<String, String> newMap = new HashMap<>();
        newMap.putAll(getStringParams());
        newMap.put("a", getAction());
        newMap.put("g", getGroup());
        newMap.put("m", getModule());

        return MD5.md5(JSON.toJSONString(newMap));
    }

    protected boolean needCache() {
        return false;
    }

    public String getCache(String key) {
        return mACache.getAsString(key);
    }

    public void removeCache(String key) {
        mACache.remove(key);
    }

    public void putCache(String key, String data) {
        mACache.put(key, data, 30 * ACache.TIME_DAY);
    }

    private String generalSignature(Map<String, String> params) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>(params);

        StringBuilder builder = new StringBuilder(SECRET_KEY);

        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            builder.append(entry.getKey());
            builder.append(entry.getValue());
        }

        builder.append(SECRET_KEY);

        return MD5.md5(builder.toString()).toUpperCase(Locale.US);
    }


    /**
     * do post request
     *
     * @param callback
     */
    public void post(NetReqCallback<T> callback) {
//        NetReqCallback netReqCallback = setReqCallback(callback);
//        if (netReqCallback == null)
//            return;
        try {
            HttpAccess.getInstance().postAsyn(getPostUrl(), setParams(), setType(), callback, needCache());
        } catch (Exception e) {
            e.printStackTrace();
            callback.apiRequestFail(e.getMessage(), getMethod());
        }

    }

    public void postFile(ProgressCallback progressCallback) {
        try {
            HttpAccess.getInstance().upLoadFileAsyn(getPostUrl(), setParams(),file_path , progressCallback);
        } catch (Exception e) {
            e.printStackTrace();
            progressCallback.onFail(e.toString(), e.toString());
        }

    }

    public void get(final NetReqCallback<T> callback) {
        try {
            HttpAccess.getInstance().getAsyn(getPostUrl(), setType(), callback);
        } catch (Exception e) {
            e.printStackTrace();
            callback.apiRequestFail(e.getMessage(), getMethod());


        }

    }

    protected String method;

    public BaseRequest<T> setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getMethod() {
        return method;
    }

    protected String getAction() {
        return "";
    }


    protected String getGroup() {
        Logger.d("getGroup not override");
        return "interface";
    }

    protected String getModule() {
        Logger.d("getModule not override");
        return "meifou";
    }

    protected Map<String, String> getStringParams() {
        Logger.d("getStringParams not override");
        return new HashMap<String, String>();
    }

    protected Map<String, File> getFileParams() {
        Logger.d("getFileParams not override");
        return null;
    }

    protected Class<? extends BaseResponse> getResponseClass() {
        Logger.d("getResponseClass not override");
        return BaseResponse.class;
    }


    public abstract String setExtraUrl();

    public String getPostUrl() {
        return getUrl() + setExtraUrl();
    }

    public HashMap<String, Object> arrayMap = new HashMap<>();

    public HashMap<String, Object> setParams() {
        arrayMap.put("method", getMethod());
        arrayMap.put("idfa", AppUtil.getIdfa());
        arrayMap.put("channel", AppUtil.getAppChannel());
        arrayMap.put("uuid", BaseApplication.UUID);
        arrayMap.put("version", AppUtil.getVersionName());
        arrayMap.put("api_url", getPostUrl());
        return arrayMap;
    }


    public Type setType() {
        Type genType = this.getClass().getGenericSuperclass();
        LogUtil.i(TAG, "genType" + genType);
        final Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type resultType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{params[0]};
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type getRawType() {
                return ApiResContent.class;
            }
        };
        LogUtil.i(TAG, "resultType" + resultType);

        return resultType;

    }
    public String file_path;



}
