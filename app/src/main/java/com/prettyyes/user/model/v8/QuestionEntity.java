package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.user.UserInfo;

/**
 * Created by chengang on 2017/7/11.
 */

public class QuestionEntity extends BaseModel {


    /**
     * task_id : 5864
     * update_date : 9/5/2017
     * desc : 周五
     * uid : 29637
     * gender : 2
     * pic_list : 0
     * like_num : 2
     * dislike_num : 3
     * share_num : 0
     * nickname : Kayla
     * is_open : 0
     * task_act_id : 9
     * total_score : null
     * topic_id : 0
     * topic_hash_tag :
     * topic_name :
     * like_task : 1
     * act_name : 3月2日问答活动测试
     * act_content : 问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多问答描述很多
     * hash_tag : #3月2日问答#
     * like : 0
     * dislike : 0
     */

    private String task_id;
    private String update_date;
    private String desc;
    private String uid;
    private int gender;
    private String pic_list;
    private int like_num;
    private int dislike_num;
    private int share_num;
    private String nickname;
    private String is_open;
    private String task_act_id;
    private String total_score;
    private String topic_id;
    private String topic_hash_tag;
    private String topic_name;
    private int like_task;
    private String act_name;
    private String act_content;
    private String hash_tag;
    private int like;
    private int dislike;
    private ShareModel share_model;
    private String tip;
    private String pricecount;//可购买
    private String excellent;//解决方法
    private String share_content;
    private String Pscore;
    private String my_answer;
    private String task_time;
    private UserInfo userinfo;
    private int task_count;//总共的回复
    private String task_describe_text;
    private String answer_user_id;
    private String answer_user_nickname;
    private String answer_id;
    private int reward_count=0;

    public int getReward_count() {
        return reward_count;
    }

    public void setReward_count(int reward_count) {
        this.reward_count = reward_count;
    }

    public boolean is_have_zeze() {
        return like == 1;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public String getAnswer_user_id() {
        return answer_user_id;
    }

    public String getAnswer_user_nickname() {
        return answer_user_nickname;
    }

    public int getTask_count() {
        return task_count;
    }

    public void setTask_count(int task_count) {
        this.task_count = task_count;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getMy_answer() {
        return my_answer;
    }

    public boolean haveReply() {
        return my_answer == null ? false : my_answer.equals("1");
    }

    public void setMy_answer_replyed() {
        this.my_answer = "1";
        if (this.my_answer != null || this.my_answer.equals("0"))
            if (getReward_count() > 0)
                setReward_count(getReward_count() - 1);

    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPic_list() {
        return pic_list;
    }

    public void setPic_list(String pic_list) {
        this.pic_list = pic_list;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getDislike_num() {
        return dislike_num;
    }

    public void setDislike_num(int dislike_num) {
        this.dislike_num = dislike_num;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIs_open() {
        return is_open;
    }

    public void setIs_open(String is_open) {
        this.is_open = is_open;
    }

    public String getTask_act_id() {
        return task_act_id;
    }

    public void setTask_act_id(String task_act_id) {
        this.task_act_id = task_act_id;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_hash_tag() {
        return topic_hash_tag;
    }

    public void setTopic_hash_tag(String topic_hash_tag) {
        this.topic_hash_tag = topic_hash_tag;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public int getLike_task() {
        return like_task;
    }

    public void setLike_task(int like_task) {
        this.like_task = like_task;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAct_content() {
        return act_content;
    }

    public void setAct_content(String act_content) {
        this.act_content = act_content;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getTip() {
        return tip;
    }

    public int getTipCount() {
        if (tip == null)
            return 0;
        try {
            return Integer.parseInt(tip);
        } catch (Exception ee) {

        }
        return 0;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPricecount() {
        return pricecount;
    }

    public void setPricecount(String pricecount) {
        this.pricecount = pricecount;
    }

    public String getExcellent() {
        return excellent;
    }

    public void setExcellent(String excellent) {
        this.excellent = excellent;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public String getTask_describe_text() {
        return task_describe_text;
    }

    public void setTask_describe_text(String task_describe_text) {
        this.task_describe_text = task_describe_text;
    }

    public String getPscore() {
        int value = (int) (Float.parseFloat(Pscore) * 100);
        return value + "";
    }

    public void setPscore(String Pscore) {
        this.Pscore = Pscore;
    }

    public String getTask_time() {
        return task_time;
    }

    public void setTask_time(String task_time) {
        this.task_time = task_time;
    }

    public ShareModel getShare_model() {
        return share_model;
    }

    public void setShare_model(ShareModel share_model) {
        this.share_model = share_model;
    }


    public boolean isMyQue() {
        if(SpMananger.getUid()==null)
            return false;
        return SpMananger.getUid().equals(getUid());
    }
}

