package com.prettyyes.user.api.netXutils.requests;

import com.google.gson.reflect.TypeToken;
import com.hornen.storage.StorageProxy;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.HomeListRes;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.SpMananger;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by chengang on 2017/7/11.
 */

public class HomeTaskRequest extends BaseRequest<HomeListRes> {
    @Override
    protected boolean needCache() {
        return true;
    }

    @Override
    public String setExtraUrl() {
        return "/app/task/homeTask";
    }

    @Override
    public Type setType() {
        return new TypeToken<ApiResContent<HomeListRes>>() {
        }.getType();
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        arrayMap.put("ad", 1);
        arrayMap.put("cid", SpMananger.getClien_id());
        String last_time = new StorageProxy(BaseApplication.getAppContext()).resolve("last_time", String.class);
        arrayMap.put("last_time", last_time);

        return super.setParams();
    }

    public HomeTaskRequest setPage(int page) {
        this.page = page;
        return this;
    }

    private int page;
    private String last_time;
    private String vendor;
    private String cid;
    private String screening_type;
}
