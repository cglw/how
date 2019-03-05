package com.prettyyes.user.model.v8;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengang on 2017/6/18.
 */

public class CartListRes implements Serializable {


    private List<CartListEntity> list;

    public List<CartListEntity> getList() {
        return list;
    }

}
