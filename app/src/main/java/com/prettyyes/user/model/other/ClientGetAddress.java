package com.prettyyes.user.model.other;

import com.prettyyes.user.model.v8.AddressEntity;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/8/12
 * Description: Nothing
 */
public class ClientGetAddress {

    /**
     * a_id : 1121
     * province_id : 2
     * province_name : 北京
     * city_id : 52
     * city_name : 北京
     * area_id : 500
     * area_name : 东城区
     * detail : ces
     * uid : 1085
     * get_uname : test
     * mobile : 15618691010
     * is_default : 0
     * pua_createtime : 2016-04-15 15:15:35
     */

    private List<AddressEntity> list;

    public void setList(List<AddressEntity> list) {
        this.list = list;
    }

    public List<AddressEntity> getList() {
        return list;
    }


}
