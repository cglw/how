package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;

/**
 * Created by chengang on 2017/10/17.
 */

public class WithdrawEntity implements BaseType {
    private String apply_money;
    private String apply_time;
    private int is_get_apply;

    public String getApply_money() {
        return apply_money;
    }

    public String getApply_time() {
        return apply_time;
    }

    public int getIs_get_apply() {
        return is_get_apply;
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
