package com.prettyyes.user.model;

/**
 * Created by chengang on 2017/7/12.
 */

public class QueCategoryEntity {
    private String category_id;
    private String category;
    private String name;
    private String icon;
    private int ic;

    public int getIc() {
        return ic;
    }

    public void setIc(int ic) {
        this.ic = ic;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
