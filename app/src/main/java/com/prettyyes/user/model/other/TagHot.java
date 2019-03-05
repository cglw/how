package com.prettyyes.user.model.other;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/8/29
 * Description: Nothing
 */
public class TagHot {

    /**
     * tag_id : 72
     * tag_name : 清新
     * hot_num : 1355
     */

    private List<TagsEntity> tags;

    public void setTags(List<TagsEntity> tags) {
        this.tags = tags;
    }

    public List<TagsEntity> getTags() {
        return tags;
    }

    public static class TagsEntity {
        private int tag_id;
        private String tag_name;
        private int hot_num;

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public void setHot_num(int hot_num) {
            this.hot_num = hot_num;
        }

        public int getTag_id() {
            return tag_id;
        }

        public String getTag_name() {
            return tag_name;
        }

        public int getHot_num() {
            return hot_num;
        }
    }
}
