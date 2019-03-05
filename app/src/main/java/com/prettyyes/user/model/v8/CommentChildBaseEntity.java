package com.prettyyes.user.model.v8;

import java.util.List;

/**
 * Created by chengang on 2018/1/14.
 */

public class CommentChildBaseEntity {

    /**
     * comment_id : 866
     * parent_id : 849
     * lft : 782
     * rgt : 783
     * depth : 3
     * created_at : 2018-01-11 15:28:15
     * updated_at : 2018-01-11 15:28:16
     * comment : 回复再次回复子评论2/////
     * answer_id : 7167
     * uid : 1085
     * comment_type : task
     * deleted_at : null
     * uuid : acee7320ee974cad52c5ae2c84fe5a4a
     * form_uid : 1085
     * comment_status : 1
     * username : xx
     * famous_type : 1
     * headimg : https://image.prettyyes.com/1085-08650-1514532898.jpeg
     * form_nickname : xx
     */

    private String comment_id;
    private String to_comment_id;
    private String parent_id;
    private String lft;
    private String rgt;
    private String depth;
    private String created_at;
    private String updated_at;
    private String comment;
    private String answer_id;
    private String uid;
    private String comment_type;
    private String deleted_at;
    private String uuid;
    private String form_uid;
    private String comment_status;
    private String username;
    private String famous_type;
    private String headimg;
    private String form_nickname;
    private int childCount;
    private int position;
    private List<CommentChildBaseEntity> children;

    public boolean isDepth2() {
        return "2".equals(depth);
    }

    public boolean isDepth1() {
        return "1".equals(depth);
    }

    public boolean isDepth3() {
        return "3".equals(depth);
    }

    public String getTo_comment_id() {
        return to_comment_id;
    }

    public void setTo_comment_id(String to_comment_id) {
        this.to_comment_id = to_comment_id;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public List<CommentChildBaseEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CommentChildBaseEntity> children) {
        this.children = children;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getLft() {
        return lft;
    }

    public void setLft(String lft) {
        this.lft = lft;
    }

    public String getRgt() {
        return rgt;
    }

    public void setRgt(String rgt) {
        this.rgt = rgt;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
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

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getComment_type() {
        return comment_type;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getForm_uid() {
        return form_uid;
    }

    public void setForm_uid(String form_uid) {
        this.form_uid = form_uid;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFamous_type() {
        return famous_type;
    }

    public void setFamous_type(String famous_type) {
        this.famous_type = famous_type;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getForm_nickname() {
        if (form_nickname == null)
            return "";
        return form_nickname;
    }

    public void setForm_nickname(String form_nickname) {
        this.form_nickname = form_nickname;
    }
}
