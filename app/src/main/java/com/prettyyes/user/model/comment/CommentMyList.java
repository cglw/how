package com.prettyyes.user.model.comment;

import java.util.List;

/**
 * Created by chengang on 2017/5/16.
 */

public class CommentMyList {


    /**
     * comment_id : 0
     * created_at : string
     * updated_at : string
     * comment : string
     * answer_id : 0
     * uid : 0
     * form_uid : 0
     * username : string
     * headimg : string
     * comment_type : string
     * ts_reason : string
     * answer_type : string
     * answer : [{"suit_id":"","seller_id":"string","suit_img_arr":["sdfsdfsd"]}]
     * parent : [{"comment_id":0,"created_at":"string","updated_at":"string","comment":"string","answer_id":0,"uid":0,"form_uid":0,"username":"string","headimg":"string"}]
     */

    private int comment_id;
    private String created_at;
    private String updated_at;
    private String comment;
    private int answer_id;
    private int uid;
    private int form_uid;
    private String username;
    private String headimg;
    private String comment_type;
    private String ts_reason;
    private String answer_type;
    private List<AnswerBean> answer;
    private ParentBean parent;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getForm_uid() {
        return form_uid;
    }

    public void setForm_uid(int form_uid) {
        this.form_uid = form_uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getComment_type() {
        return comment_type;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    public String getTs_reason() {
        return ts_reason;
    }

    public void setTs_reason(String ts_reason) {
        this.ts_reason = ts_reason;
    }

    public String getAnswer_type() {
        return answer_type;
    }

    public void setAnswer_type(String answer_type) {
        this.answer_type = answer_type;
    }

    public List<AnswerBean> getAnswer() {
        return answer;
    }

    public void setAnswer(List<AnswerBean> answer) {
        this.answer = answer;
    }

    public ParentBean getParent() {
        return parent;
    }

    public void setParent(ParentBean parent) {
        this.parent = parent;
    }

    public static class AnswerBean {
        /**
         * suit_id :
         * seller_id : string
         * suit_img_arr : ["sdfsdfsd"]
         */

        private int suit_id;
        private int paper_id;
        private int taobao_id;

        private String seller_id;

        private List<String> img_arr;
        private List<String> suit_img_arr;
        private String paper_image;

        public List<String> getSuit_img_arr() {
            return suit_img_arr;
        }

        public void setSuit_img_arr(List<String> suit_img_arr) {
            this.suit_img_arr = suit_img_arr;
        }

        public String getPaper_image() {
            return paper_image;
        }

        public void setPaper_image(String paper_image) {
            this.paper_image = paper_image;
        }

        public List<String> getImg_arr() {
            return img_arr;
        }

        public void setImg_arr(List<String> img_arr) {
            this.img_arr = img_arr;
        }

        public int getPaper_id() {
            return paper_id;
        }

        public void setPaper_id(int paper_id) {
            this.paper_id = paper_id;
        }

        public int getTaobao_id() {
            return taobao_id;
        }

        public void setTaobao_id(int taobao_id) {
            this.taobao_id = taobao_id;
        }

        public int getSuit_id() {
            return suit_id;
        }

        public void setSuit_id(int suit_id) {
            this.suit_id = suit_id;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }


    }

    public static class ParentBean {
        /**
         * comment_id : 0
         * created_at : string
         * updated_at : string
         * comment : string
         * answer_id : 0
         * uid : 0
         * form_uid : 0
         * username : string
         * headimg : string
         */

        private int comment_id;
        private String created_at;
        private String updated_at;
        private String comment;
        private int answer_id;
        private int uid;
        private int form_uid;
        private String username;
        private String headimg;

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getAnswer_id() {
            return answer_id;
        }

        public void setAnswer_id(int answer_id) {
            this.answer_id = answer_id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getForm_uid() {
            return form_uid;
        }

        public void setForm_uid(int form_uid) {
            this.form_uid = form_uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }
    }
}
