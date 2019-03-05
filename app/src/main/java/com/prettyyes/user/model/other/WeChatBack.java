package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/8/10
 * Description: Nothing
 */
public class WeChatBack extends BaseModel{
    // {sex=1, nickname=陈刚, unionid=oCr9huDewAbZofQ_BygcWjFp6o_o, province=安徽, openid=ombCqt5U7E5WY13uu_u6KdgciSfI, language=zh_CN, headimgurl=http://wx.qlogo.cn/mmopen/xyqibIllkBMwdniaDzWrswh161pxcByTYqu85ecDxvqccvVRibRmicXbFqphQCxVT1nwcXb4ic3VkC3Nc198JfMaD0UVVaYicjttC9/0, country=中国, city=滁州}
    private String access_token;
    private String openid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
