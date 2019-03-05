package com.prettyyes.user.model;

import java.util.List;

/**
 * Created by chengang on 2017/5/25.
 */

public class TopicCollectRes {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * topic_id : 1
         * topic_name : 测试专题 小王子
         * topic_content : topic_content
         * topic_image : http://img.prettyyes.com/seller-laravel-1085-6637-1471839612.jpeg
         * create_time : 2017-05-25 16:25:10
         * topic_hash_tag : topic_hash_tag
         * task_answer_char_num : 0
         * asker_desc : 17个同问者
         * reply_desc : 回答字数达0
         */

        private int topic_id;
        private String topic_name;
        private String topic_content;
        private String topic_image;
        private String create_time;
        private String topic_hash_tag;
        private int task_answer_char_num;
        private String asker_desc;
        private String reply_desc;
        private String topic_bg_color;

        public String getTopic_bg_color() {
            return topic_bg_color;
        }

        public void setTopic_bg_color(String topic_bg_color) {
            this.topic_bg_color = topic_bg_color;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public String getTopic_name() {
            return topic_name;
        }

        public void setTopic_name(String topic_name) {
            this.topic_name = topic_name;
        }

        public String getTopic_content() {
            return topic_content;
        }

        public void setTopic_content(String topic_content) {
            this.topic_content = topic_content;
        }

        public String getTopic_image() {
            return topic_image;
        }

        public void setTopic_image(String topic_image) {
            this.topic_image = topic_image;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTopic_hash_tag() {
            return topic_hash_tag;
        }

        public void setTopic_hash_tag(String topic_hash_tag) {
            this.topic_hash_tag = topic_hash_tag;
        }

        public int getTask_answer_char_num() {
            return task_answer_char_num;
        }

        public void setTask_answer_char_num(int task_answer_char_num) {
            this.task_answer_char_num = task_answer_char_num;
        }

        public String getAsker_desc() {
            return asker_desc;
        }

        public void setAsker_desc(String asker_desc) {
            this.asker_desc = asker_desc;
        }

        public String getReply_desc() {
            return reply_desc;
        }

        public void setReply_desc(String reply_desc) {
            this.reply_desc = reply_desc;
        }
    }
}
