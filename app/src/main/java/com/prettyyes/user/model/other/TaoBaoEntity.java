package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

import java.io.Serializable;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.Suit
 * Author: SmileChen
 * Created on: 2016/10/14
 * Description: Nothing
 */
public class TaoBaoEntity extends BaseModel {

    /**
     * taobao_id : 22
     * taobao_status : 1
     * main_image : http://img.alicdn.com/imgextra/i2/2676772095/TB2TEWla16yQeBjy0FfXXcWvXXa_!!2676772095.jpg
     * small_image : http://img.alicdn.com/imgextra/i2/2676772095/TB2TEWla16yQeBjy0FfXXcWvXXa_!!2676772095.jpg;http://img.alicdn.com/imgextra/i2/2676772095/TB2iNiBa3_xQeBjy0FoXXX1vpXa_!!2676772095.jpg;http://img.alicdn.com/imgextra/i2/2676772095/TB20RSra4fxQeBjSsppXXXeoFXa_!!2676772095.jpg;http://img.alicdn.com/imgextra/i4/2676772095/TB2T9Sqa6nyQeBjSspnXXbZipXa_!!2676772095.jpg;http://img.alicdn.com/imgextra/i4/2676772095/TB25MaoaWzyQeBjSszfXXX7OVXa_!!2676772095.jpg
     * shop_name : 杭州象鼻子科技
     * taobao_url : http://a.m.taobao.com/i537728473318.htm
     * title : 象鼻子 超小迷你隐形无线蓝牙耳机商务4.1耳塞挂耳入耳式运动通用
     * price : 59.00
     * taobao_desc :
     * taobao_desc_link :
     * create_time : 2016-09-13 10:15:19
     * update_time : null
     * like_num : 0
     * view_num : 4
     * buy_count : 0
     * cart : false
     * collect : false
     * user_info : {"id":32472,"nickname":"yin臻","gender":2,"grade":1,"headimg":"http://img.prettyyes.com/32472-7674-1473088446.jpeg"}
     */

    private int taobao_id;
    private int taobao_status;
    private String main_image;
    private String small_image;
    private String shop_name;
    private String taobao_url;
    private String title;
    private String price;
    private String taobao_desc;
    private String taobao_desc_link;
    private String create_time;
    private String update_time;
    private int like_num;
    private int view_num;
    private int buy_count;
    private boolean cart;
    private boolean collect;
    private String wish_list;
    private String share_url;
    private String  is_like;

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public boolean isAddwish_lish() {
        if (wish_list.equals("yes")) {
            return true;
        }
        return false;
    }

    /**
     * id : 32472
     * nickname : yin臻
     * gender : 2
     * grade : 1
     * headimg : http://img.prettyyes.com/32472-7674-1473088446.jpeg
     */

    private UserInfoEntity user_info;

    public int getTaobao_id() {
        return taobao_id;
    }

    public void setTaobao_id(int taobao_id) {
        this.taobao_id = taobao_id;
    }

    public int getTaobao_status() {
        return taobao_status;
    }

    public void setTaobao_status(int taobao_status) {
        this.taobao_status = taobao_status;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getTaobao_url() {
        return taobao_url;
    }

    public void setTaobao_url(String taobao_url) {
        this.taobao_url = taobao_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTaobao_desc() {
        return taobao_desc;
    }

    public void setTaobao_desc(String taobao_desc) {
        this.taobao_desc = taobao_desc;
    }

    public String getTaobao_desc_link() {
        return taobao_desc_link;
    }

    public void setTaobao_desc_link(String taobao_desc_link) {
        this.taobao_desc_link = taobao_desc_link;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
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

    public boolean isCart() {
        return cart;
    }

    public void setCart(boolean cart) {
        this.cart = cart;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public UserInfoEntity getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoEntity user_info) {
        this.user_info = user_info;
    }



    public static class UserInfoEntity implements Serializable {
        private int id;
        private String nickname;
        private int gender;
        private int grade;
        private String headimg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }
    }
}
