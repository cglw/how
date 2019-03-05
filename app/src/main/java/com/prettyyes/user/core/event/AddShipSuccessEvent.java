package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/6/14.
 */

public class AddShipSuccessEvent {
    private String order_number;

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public AddShipSuccessEvent(String order_number) {
        this.order_number = order_number;
    }
}
