package com.prettyyes.user.model.search;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/7/13.
 */

public class SearchBaseEntity extends BaseModel {

    private String key_word;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getKey_word() {
        return key_word;
    }

    public SearchBaseEntity setKey_word(String key_word) {
        this.key_word = key_word;
        return this;
    }


}
