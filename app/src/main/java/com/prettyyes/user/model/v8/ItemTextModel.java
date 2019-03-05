package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;

/**
 * Created by chengang on 2017/7/14.
 */

public class ItemTextModel implements BaseType {
    private String text;
    private int unread_num=0;

    public int getUnread_num() {
        return unread_num;
    }

    public ItemTextModel setUnread_num(int unread_num) {
        this.unread_num = unread_num;
        return this;
    }

    public ItemTextModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
