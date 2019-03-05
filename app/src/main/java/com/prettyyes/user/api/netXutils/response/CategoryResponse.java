package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.CategoryModel;

import java.util.List;

/**
 * Created by chengang on 2017/6/23.
 */

public class CategoryResponse extends BaseRes{
    private List<CategoryModel> list;

    public List<CategoryModel> getList() {
        return list;
    }

    public void setList(List<CategoryModel> list) {
        this.list = list;
    }
}
