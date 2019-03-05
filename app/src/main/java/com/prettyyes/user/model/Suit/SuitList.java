package com.prettyyes.user.model.Suit;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.Suit
 * Author: SmileChen
 * Created on: 2016/11/17
 * Description: Nothing
 */
public class SuitList {

    /**
     * sku_id : 86
     * type : taobao
     * name : MIUCO女装2016秋季新款通勤OL小西服拼接条纹衬衫裙假两件连衣裙
     * cover_img : http://img.alicdn.com/imgextra/i1/671012022/TB2s1FrXrDD11BjSszfXXbwoFXa_!!671012022.jpg
     * status : 1
     * price : 268.00
     * like_num : 0
     * view_num : 431
     * buy_count : 0
     * create_time : 2016-10-12 19:11:49
     * comment_count : 0
     */

    private List<SuitsEntity> suits;

    public List<SuitsEntity> getSuits() {
        return suits;
    }

    public void setSuits(List<SuitsEntity> suits) {
        this.suits = suits;
    }

    public static class SuitsEntity {
        private String spu_id;
        private String module_id;
        private String type;
        private String name;
        private String cover_img;
        private int status;
        private String price;
        private int like_num;
        private int view_num;
        private int buy_count;
        private String create_time;
        private int comment_count;

        public String getSpu_id() {
            return spu_id;
        }

        public void setSpu_id(String spu_id) {
            this.spu_id = spu_id;
        }

        public String getMoudle_id() {
            return module_id;
        }

        public void setMoudle_id(String moudle_id) {
            this.module_id = moudle_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getLike_num() {
            return like_num;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public int getView_num() {
            return view_num;
        }

        public void setView_num(int view_num) {
            this.view_num = view_num;
        }

        public int getBuy_count() {
            return buy_count;
        }

        public void setBuy_count(int buy_count) {
            this.buy_count = buy_count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }
    }
}
