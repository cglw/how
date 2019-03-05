package com.prettyyes.user.api.netXutils.requests;

import com.google.gson.reflect.TypeToken;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.HomeListRes;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by chengang on 2017/7/12.
 */

public class AbListByCategoryRequest extends BaseRequest<HomeListRes> {

    @Override
    public Type setType() {
        return new TypeToken<ApiResContent<HomeListRes>>() {
        }.getType();
    }

    @Override
    protected boolean needCache() {
        return true;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page",page);
        arrayMap.put("category_id",category_id);
        arrayMap.put("screening_type",screening_type);
        return super.setParams();
    }

    private String category_id;
    private String screening_type;
    private int page;

    public AbListByCategoryRequest setPage(int page) {
        this.page = page;
        return this;
    }

    public AbListByCategoryRequest setCategory_id(String category_id) {
        this.category_id = category_id;
        return this;
    }

    public AbListByCategoryRequest setScreening_type(String screening_type) {
        this.screening_type = screening_type;
        return this;
    }

    @Override
    public String setExtraUrl() {
        return "/app/task/availableListByCategory";
    }
}
