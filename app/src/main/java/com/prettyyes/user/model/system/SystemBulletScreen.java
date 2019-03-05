package com.prettyyes.user.model.system;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.system
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class SystemBulletScreen extends BaseModel{
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
