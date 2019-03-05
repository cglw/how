package com.prettyyes.user.model.v8;

import java.io.Serializable;

/**
 * Created by chengang on 2017/6/30.
 */

public class UnitUpload implements Serializable{
    private String order_num;
    private String unit_id;
    private int num;
    private String sku_id;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }


}
