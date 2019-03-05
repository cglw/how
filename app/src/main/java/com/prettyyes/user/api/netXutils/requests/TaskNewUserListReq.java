package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.common.ListCommon;
import com.prettyyes.user.model.v8.TaskNewUserItemEntity;

import java.util.List;

/**
 * Created by chengang on 2017/12/27.
 */

public class TaskNewUserListReq extends BaseRequest<ListCommon<List<TaskNewUserItemEntity>>> {
    @Override
    public String setExtraUrl() {
        return "/app/newbietask/getList";
    }

    @Override
    protected boolean needCache() {
        return true;
    }


    @Override
    public void post(NetReqCallback<ListCommon<List<TaskNewUserItemEntity>>> callback) {
        super.post(callback);
    }
}
