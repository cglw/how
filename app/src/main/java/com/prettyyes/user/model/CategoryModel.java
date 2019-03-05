package com.prettyyes.user.model;

import java.io.Serializable;

/**
 * Created by chengang on 2017/5/8.
 */

public class CategoryModel implements Serializable {

    /**
     * id : 1
     * category_id : 1
     * category : 绘画
     * name : 艺术
     * order : 1
     * icon : https://image.prettyyes.com/art.png
     */

    private boolean isSelect = false;

    /**
     * cat_id :
     * parent_id :
     * cat_name :
     * order_num : Unknown Type: float
     */

    private String cat_id;
    private String parent_id;
    private String cat_name;
    private String order_num;
    private String spu_id;
    private String category;
    private String name;

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }
}
