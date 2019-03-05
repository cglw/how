package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.HomeNotifyEntity;
import com.prettyyes.user.model.v8.HomeTaskEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/11.
 */

public class HomeListRes extends BaseRes{
    private List<HomeTaskEntity> list;
    private HomeNotifyEntity notice;
    private String seller_type;//1 为seller


    public String getSeller_type() {
        return seller_type;
    }

    public void setSeller_type(String seller_type) {
        this.seller_type = seller_type;
    }

    public HomeNotifyEntity getNotice() {
        return notice;
    }

    public void setNotice(HomeNotifyEntity notice) {
        this.notice = notice;
    }

    public List<HomeTaskEntity> getList() {
        return list;
    }

    public void setList(List<HomeTaskEntity> list) {
        this.list = list;
    }
}
