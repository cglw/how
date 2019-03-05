package com.prettyyes.user.api.netXutils.response;

/**
 * Created by chengang on 2017/12/28.
 */

public class SearchStatusRes {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isShow() {
        return "1".equals(status);
    }
}
