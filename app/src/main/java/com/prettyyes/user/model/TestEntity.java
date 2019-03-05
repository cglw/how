package com.prettyyes.user.model;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;

/**
 * Created by chengang on 2017/8/10.
 */

public class TestEntity implements BaseType {
    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
