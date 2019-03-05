package com.prettyyes.user.model.common;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/2/22.
 */

public class InfoCommon<T> extends BaseModel{
    private T info;

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
