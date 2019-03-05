package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;

import java.util.List;

/**
 * Created by chengang on 2017/7/11.
 */

public class HomeTaskEntity extends HomeQueEntity implements BaseType {

    //继承的type task
    private String type = "task";
    private List<ActInfoEntity> data;
    private String new_task;
    private String home_task_count;
    private String ask_text;
    private String ask_color;
    private String activity_type;
    public int que_color;

    private BannerItemEntity ad;


    public int getQue_color() {
        return que_color;
    }

    public BannerItemEntity getAd() {
        return ad;
    }

    public void setAd(BannerItemEntity ad) {
        this.ad = ad;
    }

    public void setQue_color(int que_color) {
        this.que_color = que_color;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ActInfoEntity> getData() {
        return data;
    }

    public void setData(List<ActInfoEntity> data) {
        this.data = data;
    }


    public String getNew_task() {
        return new_task;
    }

    public void setNew_task(String new_task) {
        this.new_task = new_task;
    }

    public String getHome_task_count() {
        return home_task_count;
    }

    public void setHome_task_count(String home_task_count) {
        this.home_task_count = home_task_count;
    }


    public String getAsk_text() {
        return ask_text;
    }

    public void setAsk_text(String ask_text) {
        this.ask_text = ask_text;
    }

    public String getAsk_color() {
        return ask_color;
    }

    public void setAsk_color(String ask_color) {
        this.ask_color = ask_color;
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
