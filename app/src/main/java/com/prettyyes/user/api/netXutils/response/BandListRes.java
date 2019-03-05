package com.prettyyes.user.api.netXutils.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/7/24.
 */

public class BandListRes extends BaseRes {

    private List<ArrayList<Object>>list;

    public List<ArrayList<Object>> getList() {
        return list;
    }

    public void setList(List<ArrayList<Object>> list) {
        this.list = list;
    }
}
