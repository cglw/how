package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.scene
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: 获取场景任务
 */
public class SceneGetHot extends BaseModel {

    /**
     * scene_id : 462
     * scene_name : 显瘦还要遮肉
     * hot_num : 1033
     */

    public SceneGetHot() {
    }

    private List<SceneEntity> scene;

    public void setScene(List<SceneEntity> scene) {
        this.scene = scene;
    }

    public List<SceneEntity> getScene() {
        return scene;
    }

    public static class SceneEntity {
        private int scene_id;
        private String scene_name;
        private int hot_num;

        public void setScene_id(int scene_id) {
            this.scene_id = scene_id;
        }

        public void setScene_name(String scene_name) {
            this.scene_name = scene_name;
        }

        public void setHot_num(int hot_num) {
            this.hot_num = hot_num;
        }

        public int getScene_id() {
            return scene_id;
        }

        public String getScene_name() {
            return scene_name;
        }

        public int getHot_num() {
            return hot_num;
        }
    }
}
