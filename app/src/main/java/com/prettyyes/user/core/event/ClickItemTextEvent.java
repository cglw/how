package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/14.
 */

public class ClickItemTextEvent {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ClickItemTextEvent(String text) {
        this.text = text;
    }
}
