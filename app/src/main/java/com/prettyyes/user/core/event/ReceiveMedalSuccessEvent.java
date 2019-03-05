package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/8/11.
 */

public class ReceiveMedalSuccessEvent {
    public String medal_id;

    public ReceiveMedalSuccessEvent(String medal_id) {
        this.medal_id = medal_id;
    }
}
