package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.scene
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: 添加场景
 */
public class SceneAdd extends BaseModel{
    private int scene_id;
    private String scene_name;

    public int getScene_id() {
        return scene_id;
    }

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public String getScene_name() {
        return scene_name;
    }

    public void setScene_name(String scene_name) {
        this.scene_name = scene_name;
    }
}
