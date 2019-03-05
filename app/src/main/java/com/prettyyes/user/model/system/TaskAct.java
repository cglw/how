package com.prettyyes.user.model.system;

import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.common.ActInfo;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.system
 * Author: SmileChen
 * Created on: 2016/12/19
 * Description: Nothing
 */
public class TaskAct extends BaseModel {

    /**
     * act_id : 5
     * act_content : 一件美丽配饰，一天美丽心情
     * act_name : 配饰
     * act_img : http://img.prettyyes.com/48531-01896-1481030138.jpeg
     * start_time : 2016-04-12 19:18:03
     * end_time : 2017-12-29 19:19:00
     * hash_tag : #配饰#
     * date_time : 04月12日 19:18 - 19:19
     * seller_list : [{"seller_id":32561,"headimg":"","nickname":"15618692010","ace_txt":"","session_ace_txt":""},{"seller_id":31397,"headimg":"http://img.prettyyes.com/seller-laravel-31397-5313-1478678881.jpeg","nickname":"冯冯","ace_txt":"","session_ace_txt":""},{"seller_id":32562,"headimg":"","nickname":"aaa15618693010","ace_txt":"","session_ace_txt":""},{"seller_id":32563,"headimg":"","nickname":"aaa15628691010bbbb","ace_txt":"","session_ace_txt":""},{"seller_id":1085,"headimg":"http://img.prettyyes.com/seller-laravel-1085-6637-1471839612.jpeg","nickname":"雪梨","ace_txt":"xiao xin coolllllll!!!","session_ace_txt":"sdvsasds1123爱的是撒旦123 为"},{"seller_id":32564,"headimg":"","nickname":"sdasdasdad","ace_txt":"","session_ace_txt":""}]
     */

    private ActInfo info;

    public ActInfo getInfo() {
        return info;
    }

    public void setInfo(ActInfo info) {
        this.info = info;
    }

    public static class InfoEntity implements Serializable {
        private int act_id;
        private String act_content;
        private String act_name;
        private String act_img;
        private long start_time;
        private long create_time;
        private long end_time;
        private String hash_tag;
        private String date_time;
        private int remind_time;
        private int last_task;
        private String share_url;
        private String last_task_id;
        private long server_time;
        private String is_follow;
        private ShareModel share_model;
        private List<SellerInfoEntity> seller_list;

        public ShareModel getShareModel() {
            return share_model;
        }

        public void setShareModel(ShareModel share_model) {
            this.share_model = share_model;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }


        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public int getRemind_time() {
            return remind_time;
        }

        public void setRemind_time(int remind_time) {
            this.remind_time = remind_time;
        }

        public int getLast_task() {
            return last_task;
        }

        public void setLast_task(int last_task) {
            this.last_task = last_task;
        }

        public String getLast_task_id() {
            return last_task_id;
        }

        public void setLast_task_id(String last_task_id) {
            this.last_task_id = last_task_id;
        }

        public long getServer_time() {
            return server_time;
        }

        public void setServer_time(long server_time) {
            this.server_time = server_time;
        }

        /**
         * seller_id : 32561
         * headimg :
         * nickname : 15618692010
         * ace_txt :
         * session_ace_txt :
         */



        public int getAct_id() {
            return act_id;
        }

        public void setAct_id(int act_id) {
            this.act_id = act_id;
        }

        public String getAct_content() {
            return act_content;
        }

        public void setAct_content(String act_content) {
            this.act_content = act_content;
        }

        public String getAct_name() {
            return act_name;
        }

        public void setAct_name(String act_name) {
            this.act_name = act_name;
        }

        public String getAct_img() {
            return act_img;
        }

        public void setAct_img(String act_img) {
            this.act_img = act_img;
        }



        public String getHash_tag() {
            return hash_tag;
        }

        public void setHash_tag(String hash_tag) {
            this.hash_tag = hash_tag;
        }

        public String getDate_time() {
            return date_time;
        }

        public void setDate_time(String date_time) {
            this.date_time = date_time;
        }

        public List<SellerInfoEntity> getSeller_list() {
            return seller_list;
        }

        public void setSeller_list(List<SellerInfoEntity> seller_list) {
            this.seller_list = seller_list;
        }

        public long getStart_time() {
            return start_time;
        }

        public void setStart_time(long start_time) {
            this.start_time = start_time;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }
    }

}
