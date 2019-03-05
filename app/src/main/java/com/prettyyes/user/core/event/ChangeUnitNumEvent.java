package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/5/22.
 */

public class ChangeUnitNumEvent {

    private double price;

    public ChangeUnitNumEvent(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
