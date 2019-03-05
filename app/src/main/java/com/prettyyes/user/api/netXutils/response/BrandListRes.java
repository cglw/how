package com.prettyyes.user.api.netXutils.response;


import com.prettyyes.user.model.v8.BrandModel;

import java.util.List;

/**
 * Created by chengang on 2017/5/23.
 */

public class BrandListRes extends BaseRes{
    public List<BrandModel> getList() {
        return list;
    }

    public void setList(List<BrandModel> list) {
        this.list = list;
    }

    private List<BrandModel> list;

}
