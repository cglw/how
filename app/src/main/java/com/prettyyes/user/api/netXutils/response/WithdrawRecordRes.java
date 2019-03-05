package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.WithdrawEntity;

import java.util.List;

/**
 * Created by chengang on 2017/10/17.
 */

public class WithdrawRecordRes extends BaseRes {
    private List<WithdrawEntity>data;

    public List<WithdrawEntity> getData() {
        return data;
    }

    public void setData(List<WithdrawEntity> data) {
        this.data = data;
    }
}
