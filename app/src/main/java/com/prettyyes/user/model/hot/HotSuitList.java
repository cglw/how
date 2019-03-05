package com.prettyyes.user.model.hot;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.hot
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class HotSuitList extends BaseModel{
    private List<SuitInfo> list;

    public void setList(List<SuitInfo> list) {
        this.list = list;
    }

    public List<SuitInfo> getList() {
        return list;
    }
}
