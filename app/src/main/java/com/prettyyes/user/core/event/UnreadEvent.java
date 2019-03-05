package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/5/18.
 */

public class UnreadEvent {
    private int count;

    public UnreadEvent(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
