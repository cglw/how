package com.prettyyes.user.model;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model
 * Author: SmileChen
 * Created on: 2016/11/30
 * Description: Nothing
 */
public class Banner  {


    /**
     * id : 4
     * name : 测试
     * start_time : 1480403533
     * end_time : 1483017715
     * is_open : 1
     * banner_url : http://www.baidu.com
     * create_time : 2016-11-29 15:12:23
     * img_url : http://img.prettyyes.com/seller-laravel--7141-1480403541.jpeg
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
        private String name;
        private int start_time;
        private int end_time;
        private String is_open;
        private String banner_url;
        private String create_time;
        private String img_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
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
    }
}
