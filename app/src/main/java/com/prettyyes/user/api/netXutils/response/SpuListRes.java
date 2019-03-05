package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/6/29.
 */

public class SpuListRes extends BaseRes{
    private int current_page;
    private List<SpuInfoEntity> data;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public List<SpuInfoEntity> getData() {
        return data;
    }

    public void setData(List<SpuInfoEntity> data) {
        this.data = data;
    }
}
