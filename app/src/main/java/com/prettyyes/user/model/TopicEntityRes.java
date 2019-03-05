package com.prettyyes.user.model;

import com.prettyyes.user.app.ui.appview.ShareModel;

/**
 * Created by chengang on 2017/5/25.
 */

public class TopicEntityRes {


    /**
     * info : {"topic_id":8,"topic_name":"kkkkkkkkkkkkkkk","topic_content":null,"create_time":"2017-05-25 10:52:00","topic_image":null,"share_url":"http://test.prettyyes.com/wx/share/superhot/8","topic_hash_tag":"","topic_desc":"这是How的第8个话题","asker_desc":"2个同问者","topic_bg_color":"","book_slogan":"","reply_desc":"回答字数达0","book_desc":"相当于0本0"}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * topic_id : 8
         * topic_name : kkkkkkkkkkkkkkk
         * topic_content : null
         * create_time : 2017-05-25 10:52:00
         * topic_image : null
         * share_url : http://test.prettyyes.com/wx/share/superhot/8
         * topic_hash_tag :
         * topic_desc : 这是How的第8个话题
         * asker_desc : 2个同问者
         * topic_bg_color :
         * book_slogan :
         * reply_desc : 回答字数达0
         * book_desc : 相当于0本0
         */

        private int topic_id;
        private String topic_name;
        private String topic_content;
        private String create_time;
        private String topic_image;
        private String share_url;
        private String topic_hash_tag;
        private String topic_desc;
        private String asker_desc;
        private String topic_bg_color;
        private String book_slogan;
        private String reply_desc;
        private String book_desc;
        private String is_follow;
        private ShareModel share_model;

        private String covery_img;

        public String getCovery_img() {
            return covery_img;
        }

        public void setCovery_img(String covery_img) {
            this.covery_img = covery_img;
        }

        public ShareModel getShareModel() {
            return share_model;
        }

        public void setShareModel(ShareModel share_model) {
            this.share_model = share_model;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public String getTopic_name() {
            return topic_name;
        }

        public void setTopic_name(String topic_name) {
            this.topic_name = topic_name;
        }

        public String getTopic_content() {
            return topic_content;
        }

        public void setTopic_content(String topic_content) {
            this.topic_content = topic_content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTopic_image() {
            return topic_image;
        }

        public void setTopic_image(String topic_image) {
            this.topic_image = topic_image;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getTopic_hash_tag() {
            return topic_hash_tag;
        }

        public void setTopic_hash_tag(String topic_hash_tag) {
            this.topic_hash_tag = topic_hash_tag;
        }

        public String getTopic_desc() {
            return topic_desc;
        }

        public void setTopic_desc(String topic_desc) {
            this.topic_desc = topic_desc;
        }

        public String getAsker_desc() {
            return asker_desc;
        }

        public void setAsker_desc(String asker_desc) {
            this.asker_desc = asker_desc;
        }

        public String getTopic_bg_color() {
            return topic_bg_color;
        }

        public void setTopic_bg_color(String topic_bg_color) {
            this.topic_bg_color = topic_bg_color;
        }

        public String getBook_slogan() {
            return book_slogan;
        }

        public void setBook_slogan(String book_slogan) {
            this.book_slogan = book_slogan;
        }

        public String getReply_desc() {
            return reply_desc;
        }

        public void setReply_desc(String reply_desc) {
            this.reply_desc = reply_desc;
        }

        public String getBook_desc() {
            return book_desc;
        }

        public void setBook_desc(String book_desc) {
            this.book_desc = book_desc;
        }
    }
}
