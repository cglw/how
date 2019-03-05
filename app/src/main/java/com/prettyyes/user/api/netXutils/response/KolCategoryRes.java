package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.QueCategoryEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/12.
 */

public class KolCategoryRes extends BaseRes{
    private List<QueCategoryEntity>list;

    public List<QueCategoryEntity> getList() {
        return list;
    }

    public void setList(List<QueCategoryEntity> list) {
        this.list = list;
    }
}
