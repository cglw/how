package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.MedalEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/21.
 */

public class MedalListRes extends BaseRes {
    private List<MedalEntity>list;

    public List<MedalEntity> getList() {
        return list;
    }

    public void setList(List<MedalEntity> list) {
        this.list = list;
    }
}
