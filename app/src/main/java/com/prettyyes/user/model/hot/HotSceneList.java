package com.prettyyes.user.model.hot;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.hot
 * Author: SmileChen
 * Created on: 2016/7/21
 * Description: Nothing
 */
public class HotSceneList extends BaseModel{

    private List<SceneInfo> list;

    public void setList(List<SceneInfo> list) {
        this.list = list;
    }

    public List<SceneInfo> getList() {
        return list;
    }

}
