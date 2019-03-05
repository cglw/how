package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/10.
 */

public class RefreshHomeEvent {
    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public RefreshHomeEvent(String method) {
        this.method = method;
    }
}
