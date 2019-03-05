package com.prettyyes.user.model.user;

import com.prettyyes.user.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.user
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: 用户头像
 */
public class UserInfolist extends BaseModel{

    /**
     * user_id : 1085
     * headimg : http://img.prettyyes.com/seller-laravel-1085-3826-1471499757.jpeg
     * nickname : 小新
     * gender : 1
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity implements Serializable{
        private int user_id;
        private String headimg;
        private String nickname;
        private int gender;

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getUser_id() {
            return user_id;
        }

        public String getHeadimg() {
            return headimg;
        }

        public String getNickname() {
            return nickname;
        }

        public int getGender() {
            return gender;
        }
    }
}
