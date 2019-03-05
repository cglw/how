package com.prettyyes.user.model.comment;

import java.util.List;

/**
 * Created by chengang on 2017/5/16.
 */

public class CommentList {

    /**
     * id : 0
     * created_at : string
     * updated_at : string
     * comment : string
     * task_suit_id : 0
     * uid : 0
     * form_uid : 0
     * username : string
     * headimg : string
     * childCount : 0
     * children : [{"id":0,"created_at":"string","updated_at":"string","comment":"string","task_suit_id":0,"uid":0,"form_uid":0,"username":"string","headimg":"string"}]
     */

    private String type;
    private int comment_id;
    private String created_at;
    private String updated_at;
    private String comment;
    private int task_suit_id;
    private int uid;
    private int form_uid;
    private String username;
    private String headimg;
    private int childCount;
    private int answer_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    private List<ChildrenBean> children;

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getComment_id() {
        return comment_id;
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

    public int getTask_suit_id() {
        return task_suit_id;
    }

    public void setTask_suit_id(int task_suit_id) {
        this.task_suit_id = task_suit_id;
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

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * id : 0
         * created_at : string
         * updated_at : string
         * comment : string
         * task_suit_id : 0
         * uid : 0
         * form_uid : 0
         * username : string
         * headimg : string
         */

        private int id;
        private String created_at;
        private String updated_at;
        private String comment;
        private int task_suit_id;
        private int uid;
        private int form_uid;
        private String username;
        private String headimg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getTask_suit_id() {
            return task_suit_id;
        }

        public void setTask_suit_id(int task_suit_id) {
            this.task_suit_id = task_suit_id;
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
