package com.prettyyes.user.model.common;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/2/22.
 */

public class ListStringCommon<T> extends BaseModel{
    private T list;

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
