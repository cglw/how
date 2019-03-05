package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.BandListRes;

/**
 * Created by chengang on 2017/7/24.
 */

public class BankListRequest extends BaseRequest<BandListRes> {
      public NetReqCallback setReqCallback(NetReqCallback callback) {
        return callback;
    }

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/banklist";
    }
}
