package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class AceGetList extends BaseModel{

    /**
     * seller_id : 1085
     * ace_name : 雪梨php^_^3
     * ace_img : null
     * ace_txt : xiao xin coolllllll!!!
     * headimg : http://img.prettyyes.com/seller-laravel-1085-6637-1471839612.jpeg
     * like_num : 2
     * share_num : 18
     * is_like : 0
     * pei_num : 0
     * zeze_num : 1
     */

    private List<ListEntity> list;

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity {
        private int seller_id;
        private String ace_name;
        private Object ace_img;
        private String ace_txt;
        private String headimg;
        private String recommend;
        private int like_num;
        private int share_num;
        private int is_like;
        private int pei_num;
        private int zeze_num;
        private int famous_type=0;
        private String template_count;
        private String char_count;
        private String answer_task_count;
        private String reading_min;
        public String grade_title;

        public String getGrade_title() {
            return grade_title;
        }

        public String getReading_min() {
            return reading_min;
        }

        public void setReading_min(String reading_min) {
            this.reading_min = reading_min;
        }

        public String getTemplate_count() {
            return template_count;
        }

        public void setTemplate_count(String template_count) {
            this.template_count = template_count;
        }

        public String getChar_count() {
            return char_count;
        }

        public void setChar_count(String char_count) {
            this.char_count = char_count;
        }

        public String getAnswer_task_count() {
            return answer_task_count;
        }

        public void setAnswer_task_count(String answer_task_count) {
            this.answer_task_count = answer_task_count;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public int getFamous_type() {
            return famous_type;
        }

        public void setFamous_type(int famous_type) {
            this.famous_type = famous_type;
        }
        private List<String>ingredient;

        public List<String> getIngredient() {
            return ingredient;
        }

        public void setIngredient(List<String> ingredient) {
            this.ingredient = ingredient;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public String getAce_name() {
            return ace_name;
        }

        public void setAce_name(String ace_name) {
            this.ace_name = ace_name;
        }

        public Object getAce_img() {
            return ace_img;
        }

        public void setAce_img(Object ace_img) {
            this.ace_img = ace_img;
        }

        public String getAce_txt() {
            return ace_txt;
        }

        public void setAce_txt(String ace_txt) {
            this.ace_txt = ace_txt;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public int getLike_num() {
            return like_num;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public int getShare_num() {
            return share_num;
        }

        public void setShare_num(int share_num) {
            this.share_num = share_num;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }

        public int getPei_num() {
            return pei_num;
        }

        public void setPei_num(int pei_num) {
            this.pei_num = pei_num;
        }

        public int getZeze_num() {
            return zeze_num;
        }

        public void setZeze_num(int zeze_num) {
            this.zeze_num = zeze_num;
        }
    }
}