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

public class TaskMatchListRequest extends BaseRequest<HomeListRes> {
    @Override
    public Type setType() {
        return new TypeToken<ApiResContent<HomeListRes>>() {
        }.getType();
    }

    @Override
    public String setExtraUrl() {
        return "/app/task/taskMatchList";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        arrayMap.put("screening_type", screening_type);
        arrayMap.put("status", status);
        return super.setParams();
    }

    private int page;
    private String screening_type;

    public TaskMatchListRequest setScreening_type(String screening_type) {
        this.screening_type = screening_type;
        return this;

    }

    //    状态 0 全部问答 1 已回答的问答 2 未回答的问答 3 at的列表 4 想答的问题 5 热门问题 6 所有问题 7 悬赏的问题
    private String status;

    public TaskMatchListRequest setStatus(String status) {
        this.status = status;
        return this;
    }

    public TaskMatchListRequest setPage(int page) {
        this.page = page;
        return this;
    }
}
