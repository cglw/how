package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/27.
 */

public class SetOrderShipRequest extends BaseRequest<Object> {

    public String order_id;
    public String ship_company;
    public String ship_number;

    public SetOrderShipRequest setOrder_id(String order_id) {
        this.order_id = order_id;
        return this;
    }

    public SetOrderShipRequest setShip_number(String ship_number) {
        this.ship_number = ship_number;
        return this;
    }

    public SetOrderShipRequest setShip_company(String ship_company) {
        this.ship_company = ship_company;
        return this;
    }

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/setsellerordership";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("order_id", order_id);
        arrayMap.put("ship_company", ship_company);
        arrayMap.put("ship_number", ship_number);
        return super.setParams();
    }
}
