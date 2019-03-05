package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.WithdrawRecordRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/10/17.
 */

public class WithDrawRecordRequest extends BaseRequest<WithdrawRecordRes> {
    private int page;

    public WithDrawRecordRequest setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        return super.setParams();
    }

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/richesList";
    }
}
