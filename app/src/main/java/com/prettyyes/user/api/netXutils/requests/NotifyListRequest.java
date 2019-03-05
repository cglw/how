package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.other.NotifyContentList;

import java.util.HashMap;

/**
 * Created by chengang on 2017/11/15.
 */

public class NotifyListRequest extends BaseRequest<NotifyContentList> {
    private int page;

    @Override
    public String setExtraUrl() {
        return "/app/notice/noticeList";
    }

    public NotifyListRequest setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        return super.setParams();
    }
}
