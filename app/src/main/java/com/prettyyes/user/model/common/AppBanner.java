package com.prettyyes.user.model.common;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.common
 * Author: SmileChen
 * Created on: 2016/12/15
 * Description: Nothing
 */
public class AppBanner {

    /**
     * id : 4
     * banner_name : 测试
     * banner_desc : 这是一个帅气的描述
     * start_time : 1480403533
     * end_time : 1483017715
     * is_open : 1
     * create_time : 2016-11-29 15:12:23
     * img_url : http://img.prettyyes.com/seller-laravel-4-7698-1481689643.jpeg
     * banner_type : home_page
     * banner_rule : {"type":"howKolSession","session_id":"5"}
     */

    private List<ListEntity> list;

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity {
        private int id;
        private String banner_name;
        private String banner_desc;
        private String start_time;
        private String end_time;
        private String is_open;
        private String create_time;
        private String img_url;
        private String banner_type;
        private String banner_rule;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBanner_name() {
            return banner_name;
        }

        public void setBanner_name(String banner_name) {
            this.banner_name = banner_name;
        }

        public String getBanner_desc() {
            return banner_desc;
        }

        public void setBanner_desc(String banner_desc) {
            this.banner_desc = banner_desc;
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

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getBanner_type() {
            return banner_type;
        }

        public void setBanner_type(String banner_type) {
            this.banner_type = banner_type;
        }

        public String getBanner_rule() {
            return banner_rule;
        }

        public void setBanner_rule(String banner_rule) {
            this.banner_rule = banner_rule;
        }
    }
}
