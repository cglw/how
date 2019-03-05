package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/18.
 */

public class HomeVpCanScrollEvent {
    private boolean seller;

    public boolean isSeller() {
        return seller;
    }

    public void setSeller(boolean seller) {
        this.seller = seller;
    }

    public HomeVpCanScrollEvent(boolean seller) {
        this.seller = seller;
    }
}
