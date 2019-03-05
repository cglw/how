package com.prettyyes.user.app.adapter.searchholder;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.model.search.SearchBaseEntity;
import com.prettyyes.user.model.v8.SellerInfoEntity;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchSellerModel extends SearchBaseEntity implements BaseType {
    private SellerInfoEntity seller;

    @Override
    public void setType(String type) {
        super.setType("seller");
    }

    public SellerInfoEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerInfoEntity seller) {
        this.seller = seller;
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
