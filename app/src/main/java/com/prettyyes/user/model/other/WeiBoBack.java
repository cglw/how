package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/8/10
 * Description: Nothing
 */
public class WeiBoBack extends BaseModel {

    String uid;
    String access_token;
    String accessToken;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccess_token() {

        return access_token;
    }

    public String getAccessToken() {
        if (accessToken == null)
            return access_token;
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
