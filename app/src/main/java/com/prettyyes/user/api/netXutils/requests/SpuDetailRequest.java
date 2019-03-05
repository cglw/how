package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.common.util.MD5;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/1.
 */

public class SpuDetailRequest extends BaseRequest<SpuInfoEntity> {


    @Override
    public String setExtraUrl() {
        return "/app/spu/detail";
    }

    private String module_id = "0";
    private String spu_type;

    public SpuDetailRequest setModule_id(String module_id) {
        if (module_id == null || module_id.equals(""))
            module_id = "0";
        this.module_id = module_id;
        return this;
    }

    public SpuDetailRequest setSpu_type(String spu_type) {
        this.spu_type = spu_type;
        return this;
    }

    @Override
    protected boolean needCache() {
        return false;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("module_id", module_id);
        arrayMap.put("spu_type", spu_type);
        return super.setParams();
    }

    //保存数据用的，不是缓存
    public void saveData(SpuInfoEntity spuInfoEntity) {
        if (spuInfoEntity == null)
            return;
        LogUtil.i(TAG, "key" + getCacheKey() + "save" + GsonHelper.getGson().toJson(spuInfoEntity));
        removeCache(getCacheKey());
        putCache(getCacheKey(), GsonHelper.getGson().toJson(spuInfoEntity));
    }

    public boolean isHaveLocal() {
        cacheKey = getCacheKey();
        LogUtil.i(TAG, "isHaveLocal" + getCacheKey());
        String cache = getCache(cacheKey);
        LogUtil.i(TAG, "cache" + cache);

        if (cache != null) {
            return true;
        } else
            return false;

    }

    String cacheKey;

    @Override
    public void post(NetReqCallback<SpuInfoEntity> callback) {

        cacheKey = getCacheKey();
        String cache = getCache(cacheKey);
        removeCache(cacheKey);

        LogUtil.i(TAG, "getData-->" + cacheKey + "->" + cache);
        if (cache != null) {
            SpuInfoEntity spuInfoEntity = GsonHelper.getGson().fromJson(cache, SpuInfoEntity.class);
            callback.apiRequestSuccess(spuInfoEntity, getMethod());
        } else {
            if (module_id == null || module_id.equals("0") || module_id.length() <= 0) {
                callback.apiRequestSuccess(null, getMethod());
                return;
            }
            super.post(callback);
        }

    }

    public String getReqCache() {
        return getCache(getCacheKey());
    }

    public String getCacheKey() {
        LogUtil.i(TAG, "getCacheKey" + setParams().toString());
        return MD5.md5(GsonHelper.getGson().toJson(setParams()));
    }

}
