package com.prettyyes.user.app.adapter.searchholder;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.model.search.SearchBaseEntity;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchLookMoreModel  extends SearchBaseEntity implements BaseType {


    private String search_type;

    public String getSearch_type() {
        return search_type;
    }



    public SearchLookMoreModel setSearch_type(String search_type) {
        this.search_type = search_type;
        return this;
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
