package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/17.
 */

public class CommentUnreadEvent {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CommentUnreadEvent(int count) {
        this.count = count;
    }
}
