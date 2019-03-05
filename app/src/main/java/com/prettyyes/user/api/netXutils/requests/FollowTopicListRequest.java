package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.TopicCollectRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/25.
 */

public class FollowTopicListRequest extends BaseRequest<TopicCollectRes> {

    @Override
    public String setExtraUrl() {
        return "/app/topic/followTopicList";
    }
    private int page;

    public FollowTopicListRequest setPage(int page) {
        this.page = page;
        return this;

    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page",page);
        return super.setParams();
    }
}
