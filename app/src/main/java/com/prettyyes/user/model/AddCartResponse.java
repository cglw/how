package com.prettyyes.user.model;

import com.prettyyes.user.api.netXutils.GsonHelper;

/**
 * Created by chengang on 2017/5/22.
 */

public class AddCartResponse {
    private int cart_num;

    public static int getNum(Object o) {
        AddCartResponse addCartResponse = GsonHelper.getGson().fromJson(GsonHelper.getGson().toJson(o), AddCartResponse.class);
        return addCartResponse.getCart_num();
    }

    public int getCart_num() {
        return cart_num;
    }

    public void setCart_num(int cart_num) {
        this.cart_num = cart_num;
    }
}
