package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chengang on 2017/7/17.
 */

public class SellerSearchListRequest extends BaseRequest<List<SellerInfoEntity>> {


    @Override
    public String setExtraUrl() {
        return "/app/user/sellerSearchList";
    }

    private String nickname;
    private String task_id;
    private int page;

    public SellerSearchListRequest setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public SellerSearchListRequest setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public SellerSearchListRequest setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("nickname",nickname);
        arrayMap.put("task_id",task_id);
        arrayMap.put("page",page);
        return super.setParams();
    }
}
