package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class AddressList extends BaseModel{

    /**
     * a_id : 498
     * province_id : 2
     * province_name : 北京
     * city_id : 52
     * city_name : 北京
     * area_id : 500
     * area_name : 东城区
     * detail : Add
     * uid : 1478
     * get_uname : Assassins
     * mobile : asdasd
     * is_default : 0
     * pua_createtime : 2016-02-01 10:40:50
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private int a_id;
        private int province_id;
        private String province_name;
        private int city_id;
        private String city_name;
        private int area_id;
        private String area_name;
        private String detail;
        private int uid;
        private String get_uname;
        private String mobile;
        private String is_default;
        private String pua_createtime;

        public void setA_id(int a_id) {
            this.a_id = a_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setGet_uname(String get_uname) {
            this.get_uname = get_uname;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public void setPua_createtime(String pua_createtime) {
            this.pua_createtime = pua_createtime;
        }

        public int getA_id() {
            return a_id;
        }

        public int getProvince_id() {
            return province_id;
        }

        public String getProvince_name() {
            return province_name;
        }

        public int getCity_id() {
            return city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public int getArea_id() {
            return area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public String getDetail() {
            return detail;
        }

        public int getUid() {
            return uid;
        }

        public String getGet_uname() {
            return get_uname;
        }

        public String getMobile() {
            return mobile;
        }

        public String getIs_default() {
            return is_default;
        }

        public String getPua_createtime() {
            return pua_createtime;
        }
    }
}
