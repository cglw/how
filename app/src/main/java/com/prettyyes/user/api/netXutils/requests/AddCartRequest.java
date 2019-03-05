package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.AddCartRes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengang on 2017/7/3.
 */

public class AddCartRequest extends BaseRequest<AddCartRes> {

    private String sku_id;
    private int num;
    private String cart_type;
    private String module_id;
    private String suit_unit;
    //是否购买购买状态, 0 保存在wish list 、 1购买 、 2 立即购买
    private String cart_status;
    private String source_info;

    public AddCartRequest setSku_id(String sku_id) {
        this.sku_id = sku_id;
        return this;
    }

    public AddCartRequest setNum(int num) {
        this.num = num;
        return this;
    }

    public AddCartRequest setCart_type(String cart_type) {
        this.cart_type = cart_type;
        return this;
    }

    public AddCartRequest setModule_id(String module_id) {
        this.module_id = module_id;
        return this;
    }

    public AddCartRequest setSuit_unit(String suit_unit) {
        this.suit_unit = suit_unit;
        return this;
    }

    public AddCartRequest setCart_status(String cart_status) {
        this.cart_status = cart_status;
        return this;
    }

    public AddCartRequest setSource_info(String source_info) {
        this.source_info = source_info;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("sku_id",sku_id);
        arrayMap.put("num",num);
        arrayMap.put("cart_type",cart_type);
        arrayMap.put("module_id",module_id);
        arrayMap.put("suit_unit",suit_unit);
        arrayMap.put("cart_status",cart_status);
        Map arrayMap=new HashMap();

        arrayMap.put("source_info",source_info);
        return super.setParams();
    }

    @Override
    public String setExtraUrl() {
        return "/app/cart/addCart";
    }
}
