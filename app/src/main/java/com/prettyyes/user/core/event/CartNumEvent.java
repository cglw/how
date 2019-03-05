package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/5/22.
 */

public class CartNumEvent {
    private int num;

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public CartNumEvent(int num) {
        this.num = num;
    }
}
