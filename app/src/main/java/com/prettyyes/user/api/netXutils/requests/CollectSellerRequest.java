package com.prettyyes.user.api.netXutils.requests;


import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/19.
 */

public class CollectSellerRequest extends BaseRequest<Object> {


    @Override
    public String setExtraUrl() {
        return "/app/ace/like";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("is_like",is_like);
        arrayMap.put("ace_id",ace_id);
        return super.setParams();
    }
    //      localHashMap.put("is_like", is_like);
//        localHashMap.put("ace_id", ace_id);
//        localHashMap.put("uuid", uuid);


    private int is_like;
    private String ace_id;

    public CollectSellerRequest setIs_like(int is_like) {
        this.is_like = is_like;
        return this;
    }

    public CollectSellerRequest setAce_id(String ace_id) {
        this.ace_id = ace_id;
        return this;
    }
}
