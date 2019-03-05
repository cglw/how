package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/4/19.
 */

public class NotifyCount extends BaseModel {

    private int count;
    private String content;
    private String create_time;
    private String headimg;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
