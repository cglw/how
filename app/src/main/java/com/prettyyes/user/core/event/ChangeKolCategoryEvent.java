package com.prettyyes.user.core.event;

import com.prettyyes.user.model.QueCategoryEntity;

/**
 * Created by chengang on 2017/7/10.
 */

public class ChangeKolCategoryEvent {
    private QueCategoryEntity queCategoryEntity;

    public QueCategoryEntity getQueCategoryEntity() {
        return queCategoryEntity;
    }

    public void setQueCategoryEntity(QueCategoryEntity queCategoryEntity) {
        this.queCategoryEntity = queCategoryEntity;
    }

    public ChangeKolCategoryEvent(QueCategoryEntity queCategoryEntity) {
        this.queCategoryEntity = queCategoryEntity;
    }
}
