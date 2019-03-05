package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2018/1/14.
 */

public class AnswerActionEntity {

    /**
     * type : string
     * time_stamp : string
     * nickname : string
     * action_txt : string
     * uid : string
     * action_type : string
     * is_me : 0
     */

    private String type;
    private String time_stamp;
    private String nickname;
    private String action_txt;
    private String uid;
    private String action_type;
    private String is_me;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getNickname() {
        if (null == nickname)
            return "";
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAction_txt() {
        if (action_txt == null)
            return "";
        return action_txt;
    }

    public void setAction_txt(String action_txt) {
        this.action_txt = action_txt;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAction_type() {
        if(null==action_type)
            return "";
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getIs_me() {
        return is_me;
    }

    public boolean isMe() {
        return "1".equals(is_me);
    }

    public void setIs_me(String is_me) {
        this.is_me = is_me;
    }
}
