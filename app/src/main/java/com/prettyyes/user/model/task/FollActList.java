package com.prettyyes.user.model.task;

import java.util.List;

/**
 * Created by chengang on 2017/2/15.
 */

public class FollActList {

    private List<FollAct> list;

    public List<FollAct> getList() {
        return list;
    }

    public void setList(List<FollAct> list) {
        this.list = list;
    }

    public static class FollAct {
        /**
         * id : 5
         * act_name : 配饰
         * act_content : 一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情一件美丽配饰，一天美丽心情件美丽配饰，一天美丽心情
         * create_time : 2016-04-12 19:24:04
         * start_time : 2017-02-13 14:13:00
         * end_time : 2017-02-13 14:16:00
         * hash_tag : #配饰#
         */

        private int id;
        private int act_id;
        private String act_name;
        private String act_content;
        private String create_time;
        private String start_time;
        private String end_time;
        private String hash_tag;

        public int getAct_id() {
            return act_id;
        }

        public void setAct_id(int act_id) {
            this.act_id = act_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAct_name() {
            return act_name;
        }

        public void setAct_name(String act_name) {
            this.act_name = act_name;
        }

        public String getAct_content() {
            return act_content;
        }

        public void setAct_content(String act_content) {
            this.act_content = act_content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getHash_tag() {
            return hash_tag;
        }

        public void setHash_tag(String hash_tag) {
            this.hash_tag = hash_tag;
        }
    }
}
