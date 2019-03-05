package com.prettyyes.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model
 * Author: SmileChen
 * Created on: 2016/11/5
 * Description: Nothing
 */
public class ActivityListModel implements Serializable {

    /**
     * id : 5
     * activity_title : 活动五
     * main_img : http://img.prettyyes.com/seller-laravel-1-4948-1478152435.jpeg
     * activity_link : http://www.taobao.com
     * activity_describe : 活动五活动五活动五
     * create_time : 2016-11-03 13:53:56
     */

    private List<ListEntity> list;

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity implements Serializable{
        private int id;
        private String activity_title;
        private String main_img;
        private String activity_link;
        private String activity_describe;
        private String create_time;
        private String activity_rule;

        public String getActivity_rule() {
            return activity_rule;
        }

        public void setActivity_rule(String rule) {
            this.activity_rule = rule;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getActivity_title() {
            return activity_title;
        }

        public void setActivity_title(String activity_title) {
            this.activity_title = activity_title;
        }

        public String getMain_img() {
            return main_img;
        }

        public void setMain_img(String main_img) {
            this.main_img = main_img;
        }

        public String getActivity_link() {

            return activity_link;
        }

        public void setActivity_link(String activity_link) {
            this.activity_link = activity_link;
        }

        public String getActivity_describe() {
            return activity_describe;
        }

        public void setActivity_describe(String activity_describe) {
            this.activity_describe = activity_describe;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
