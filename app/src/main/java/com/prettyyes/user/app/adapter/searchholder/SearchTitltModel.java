package com.prettyyes.user.app.adapter.searchholder;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.model.search.SearchBaseEntity;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchTitltModel  extends SearchBaseEntity implements BaseType{

    @Override
    public void setType(String type) {
        super.setType("title");
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public SearchTitltModel setTitle(String title) {
        this.title = title;
        return this;
    }
    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
