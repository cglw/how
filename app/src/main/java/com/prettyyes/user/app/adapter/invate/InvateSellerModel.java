package com.prettyyes.user.app.adapter.invate;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.model.v8.SellerInfoEntity;

/**
 * Created by chengang on 2017/7/17.
 */

public class InvateSellerModel  implements BaseType {
    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }

    private SellerInfoEntity seller;

    public SellerInfoEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerInfoEntity seller) {
        this.seller = seller;
    }

    private String task_id;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }
}
