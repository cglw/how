package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.system.TaskActPre;

/**
 * Created by chengang on 2017/8/11.
 */

public class TaskActPreRequest extends BaseRequest<TaskActPre> {
    @Override
    public String setExtraUrl() {
        return "/app/act/taskActPre";
    }
}
