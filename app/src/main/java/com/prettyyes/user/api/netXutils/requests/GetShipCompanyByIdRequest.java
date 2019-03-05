package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.ShipEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chengang on 2017/7/27.
 */

public class GetShipCompanyByIdRequest extends BaseRequest<List<ShipEntity>> {

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/getexpressfornumber";
    }

    public GetShipCompanyByIdRequest setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }

    public String order_number;

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("order_number", order_number);
        return super.setParams();
    }
}
