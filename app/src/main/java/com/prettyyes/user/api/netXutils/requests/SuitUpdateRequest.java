package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.response.AddTempleteRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/6/29.
 */

public class SuitUpdateRequest extends CommonRequest<AddTempleteRes> {


    @Override
    public String setExtraUrl() {
        return "/app/suit/suitUpdate";
    }

    private String suit_id;
    private String bright_point;
    private String rubbish;
    private String unit_detail;

    public void setSuit_id(String suit_id) {
        this.suit_id = suit_id;
    }

    public void setBright_point(String bright_point) {
        this.bright_point = bright_point;
    }

    public void setRubbish(String rubbish) {
        this.rubbish = rubbish;
    }

    public void setUnit_detail(String unit_detail) {
        this.unit_detail = unit_detail;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("suit_id",suit_id);
        arrayMap.put("bright_point",bright_point);
        arrayMap.put("unit_detail",unit_detail);
        arrayMap.put("rubbish",rubbish);
        return super.setParams();
    }
}
