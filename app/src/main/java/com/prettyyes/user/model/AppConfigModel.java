package com.prettyyes.user.model;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model
 * Author: SmileChen
 * Created on: 2016/9/19
 * Description: Nothing
 */
public class AppConfigModel {
    //是否第一次启动
    private boolean isfirstStart=true;
    private String defaulturl="";

    public boolean isfirstStart() {
        return isfirstStart;
    }

    public void setIsfirstStart(boolean isfirstStart) {
        this.isfirstStart = isfirstStart;
    }
}
