package com.prettyyes.user.model.Suit;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.Suit
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class SuitUserGetSuitListBySellerId extends BaseModel{

    /**
     * suit_id : 9965
     * name : 美妆香水包B
     * cover_img : http://img.prettyyes.com/22517-3434-1462467341.jpeg
     * price : 218.00
     * like_num : 0
     * view_num : 8
     * buy_count : 0
     * comment_count : 0
     */

    private List<SuitsEntity> suits;

    public void setSuits(List<SuitsEntity> suits) {
        this.suits = suits;
    }

    public List<SuitsEntity> getSuits() {
        return suits;
    }

    public static class SuitsEntity {
        private int suit_id;
        private String name;
        private String cover_img;
        private String price;
        private int like_num;
        private int view_num;
        private int buy_count;
        private int comment_count;


        public void setSuit_id(int suit_id) {
            this.suit_id = suit_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public void setView_num(int view_num) {
            this.view_num = view_num;
        }

        public void setBuy_count(int buy_count) {
            this.buy_count = buy_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getSuit_id() {
            return suit_id;
        }

        public String getName() {
            return name;
        }

        public String getCover_img() {
            return cover_img;
        }

        public String getPrice() {
            return price;
        }

        public int getLike_num() {
            return like_num;
        }

        public int getView_num() {
            return view_num;
        }

        public int getBuy_count() {
            return buy_count;
        }

        public int getComment_count() {
            return comment_count;
        }
    }
}
