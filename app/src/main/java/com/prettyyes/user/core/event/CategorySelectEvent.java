package com.prettyyes.user.core.event;


import com.prettyyes.user.model.CategoryModel;

import java.util.List;

/**
 * Created by chengang on 2017/6/6.
 */

public class CategorySelectEvent {
    private List<CategoryModel>categoryModelList;

    public List<CategoryModel> getCategoryModelList() {
        return categoryModelList;
    }

    public void setCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    public CategorySelectEvent(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    public String getCategoryString() {
        String category = "";
        for (int i = 0; i < categoryModelList.size(); i++) {
            category += categoryModelList.get(i).getCat_name();
            if (i < categoryModelList.size() - 1)
                category += ";";
        }
        return category;
    }
}
