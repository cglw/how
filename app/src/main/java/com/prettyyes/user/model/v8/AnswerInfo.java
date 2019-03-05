package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/6/18.
 */

public class AnswerInfo extends BaseModel {

    /**
     * seller_id : 1085
     * task_id : 16715
     * answer_type : sku
     * module_id : 7
     * ts_reason : test
     * nickname : 嘻嘻嘻
     * headimg : http://img.prettyyes.com/1085-4583-1451650313.jpeg
     * information :
     * ace_txt : ddddd
     * famous_type : 1
     * answer_id : 142
     * answer_like_num : 0
     * answer_dislike_num : 0
     * answer_data : {"sku_id":7,"seller_id":1085,"price":"1000.00","sku_name":"我无所事事时lv","share_status":1,"brand_id":3,"brand_name":"嘻嘻嘻","brand_image":"http://img.prettyyes.com/1085-4583-1451650313.jpeg","uid":1085,"category_name":"xixixi","img_arr":["https://si.geilicdn.com/vshop1147006992-1482837603102-3C930-s1.jpg?w=480&h=480","https://si.geilicdn.com/vshop1147006992-1482837603965-F58C3-s1.jpg?w=480&h=480","https://si.geilicdn.com/vshop1147006992-1482837604800-FB937-s1.jpg?w=480&h=480"]}
     */

    private String seller_id;
    private String task_id;
    private String answer_type;
    private String module_id;
    private String ts_reason;
    private String nickname;
    private String headimg;
    private String information;
    private String ace_txt;
    private String famous_type;
    private String answer_id;
    private String answer_like_num;
    private String answer_dislike_num;
    private AnswerDataBean answer_data;


    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getAnswer_type() {
        return answer_type;
    }

    public void setAnswer_type(String answer_type) {
        this.answer_type = answer_type;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getFamous_type() {
        return famous_type;
    }

    public void setFamous_type(String famous_type) {
        this.famous_type = famous_type;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_like_num() {
        return answer_like_num;
    }

    public void setAnswer_like_num(String answer_like_num) {
        this.answer_like_num = answer_like_num;
    }

    public String getAnswer_dislike_num() {
        return answer_dislike_num;
    }

    public void setAnswer_dislike_num(String answer_dislike_num) {
        this.answer_dislike_num = answer_dislike_num;
    }

    public String getTs_reason() {
        return ts_reason;
    }

    public void setTs_reason(String ts_reason) {
        this.ts_reason = ts_reason;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getAce_txt() {
        return ace_txt;
    }

    public void setAce_txt(String ace_txt) {
        this.ace_txt = ace_txt;
    }


    public AnswerDataBean getAnswer_data() {
        return answer_data;
    }

    public void setAnswer_data(AnswerDataBean answer_data) {
        this.answer_data = answer_data;
    }


    @Override
    public String toString() {
        return "AnswerInfo{" +
                "seller_id='" + seller_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", answer_type='" + answer_type + '\'' +
                ", module_id='" + module_id + '\'' +
                ", ts_reason='" + ts_reason + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headimg='" + headimg + '\'' +
                ", information='" + information + '\'' +
                ", ace_txt='" + ace_txt + '\'' +
                ", famous_type='" + famous_type + '\'' +
                ", answer_id='" + answer_id + '\'' +
                ", answer_like_num='" + answer_like_num + '\'' +
                ", answer_dislike_num='" + answer_dislike_num + '\'' +
                ", answer_data=" + answer_data +
                '}';
    }
}
