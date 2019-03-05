package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.TaskSearchRes;

/**
 * Created by chengang on 2017/7/14.
 */

public class TaskSearchRequest extends BaseRequest<TaskSearchRes> {

    @Override
    public String setExtraUrl() {
        return "/app/search/taskSearch";
    }

}
