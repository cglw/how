package com.prettyyes.user.api.netXutils.response;

/**
 * Created by chengang on 2017/8/21.
 */

public class BrandCountRes {

    /**
     * brand_count : 0
     * brand_sku_count : 0
     * task_count : 0
     * task_desc_count : 0
     */

    private int brand_count;
    private int brand_sku_count;
    private String task_count;
    private String task_desc_count;

    public int getBrand_count() {
        return brand_count;
    }

    public void setBrand_count(int brand_count) {
        this.brand_count = brand_count;
    }

    public int getBrand_sku_count() {
        return brand_sku_count;
    }

    public void setBrand_sku_count(int brand_sku_count) {
        this.brand_sku_count = brand_sku_count;
    }


}
