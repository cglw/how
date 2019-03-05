package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.MineDynamicEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/13.
 */

public class MonmentsmyListRes extends BaseRes{
    private List<MineDynamicEntity> data;

    public List<MineDynamicEntity> getData() {
        return data;
    }

    public void setData(List<MineDynamicEntity> data) {
        this.data = data;
    }
}
