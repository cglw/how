package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.ShipEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/27.
 */

public class ShipCompangRequest extends BaseRequest<List<ShipEntity>>{
    @Override
    protected boolean needCache() {
        return true;
    }


    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/getexpress";
    }
}
