package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/6/29.
 */

public class CommonRequest<T> extends BaseRequest<T> {

    @Override
    public String setExtraUrl() {
        return null;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("module_id", module_id);
        arrayMap.put("spu_name", spu_name);
        arrayMap.put("spu_desc", spu_desc);
        arrayMap.put("main_img", main_img);
        arrayMap.put("spu_price", spu_price);
        if (spu_status == null || spu_status.length() <= 0)
            spu_status = "1";
        arrayMap.put("spu_status", spu_status);
        arrayMap.put("share_status", share_status);
        arrayMap.put("attr_json", attr_json);
        arrayMap.put("express_price", express_price);
        arrayMap.put("small_img", small_img);
        arrayMap.put("category_ids", category_ids);
        return super.setParams();
    }

    private String module_id;
    private String spu_name;
    private String spu_desc;
    private String main_img;
    public String spu_price;
    private String spu_status = "1";
    private String share_status = "1";
    public String attr_json;
    private String express_price;
    private String small_img;
    private String category_ids;

    public void setCategory_ids(String category_ids) {
        this.category_ids = category_ids;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public void setSpu_desc(String spu_desc) {
        this.spu_desc = spu_desc;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public void setSmall_img(String small_img) {
        this.small_img = small_img;
    }

    public void setSpu_price(String spu_price) {
        this.spu_price = spu_price;
    }

    public void setSpu_status(String spu_status) {
        if (spu_status == null || spu_status.equals(""))
            this.spu_status = "1";
        this.spu_status = spu_status;
    }

    public void setShare_status(String share_status) {
        this.share_status = share_status;
    }

    public void setAttr_json(String attr_json) {
        this.attr_json = attr_json;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

}
