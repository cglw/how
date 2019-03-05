package com.prettyyes.user.model.coupon;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.coupon
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class ExistShareCoupon extends BaseModel{

    /**
     * flag : 0
     * slogan : null
     */

    private int flag;
    private String slogan;
    private String share_url;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
}
