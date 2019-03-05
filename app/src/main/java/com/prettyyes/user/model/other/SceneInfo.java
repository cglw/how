package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/12/20
 * Description: Nothing
 */
public class SceneInfo extends BaseModel{
    private int scene_id;
    private String background;

    public int getScene_id() {
        return scene_id;
    }

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
