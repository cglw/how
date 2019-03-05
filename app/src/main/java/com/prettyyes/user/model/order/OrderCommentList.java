package com.prettyyes.user.model.order;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.order
 * Author: SmileChen
 * Created on: 2016/11/18
 * Description: Nothing
 */
public class OrderCommentList {

    /**
     * order_unit_id : 5475
     * unit_id : 5282
     * unit_name : 羊绒呢子大衣
     * price : 500.00
     * unit_img : http://img.prettyyes.com/1302-8879-1448332527.jpeg
     * unit_nums : 1
     * unit_type : suit
     */

    private List<ListEntity> list;

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity {
        private int order_unit_id;
        private int unit_id;
        private String unit_name;
        private String price;
        private String unit_img;
        private int unit_nums;
        private String unit_type;
        private float pretty_level;
        private float wish_level;
        private float server_level;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public float getPretty_level() {
            return pretty_level;
        }

        public void setPretty_level(float pretty_level) {
            this.pretty_level = pretty_level;
        }

        public float getWish_level() {
            return wish_level;
        }

        public void setWish_level(float wish_level) {
            this.wish_level = wish_level;
        }

        public float getServer_level() {
            return server_level;
        }

        public void setServer_level(float server_level) {
            this.server_level = server_level;
        }

        public int getOrder_unit_id() {
            return order_unit_id;
        }

        public void setOrder_unit_id(int order_unit_id) {
            this.order_unit_id = order_unit_id;
        }

        public int getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(int unit_id) {
            this.unit_id = unit_id;
        }

        public String getUnit_name() {
            return unit_name;
        }

        public void setUnit_name(String unit_name) {
            this.unit_name = unit_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getUnit_img() {
            return unit_img;
        }

        public void setUnit_img(String unit_img) {
            this.unit_img = unit_img;
        }

        public int getUnit_nums() {
            return unit_nums;
        }

        public void setUnit_nums(int unit_nums) {
            this.unit_nums = unit_nums;
        }

        public String getUnit_type() {
            return unit_type;
        }

        public void setUnit_type(String unit_type) {
            this.unit_type = unit_type;
        }
    }
}
