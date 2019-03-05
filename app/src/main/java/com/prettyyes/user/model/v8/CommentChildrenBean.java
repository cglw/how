package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/7/11.
 */

public class CommentChildrenBean extends BaseModel{
    /**
     * comment_id : 0
     * comment : string
     * answer_id : 0
     * username : string
     */

    private int comment_id;
    private String comment;
    private int answer_id;
    private String username;
    public String headimg;
    public String create_time;

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
