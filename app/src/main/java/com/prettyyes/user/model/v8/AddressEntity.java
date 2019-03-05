package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/7/28.
 */

public class AddressEntity extends BaseModel{
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
    private boolean isselect=false;

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getGet_uname() {
        return get_uname;
    }

    public void setGet_uname(String get_uname) {
        this.get_uname = get_uname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getPua_createtime() {
        return pua_createtime;
    }

    public void setPua_createtime(String pua_createtime) {
        this.pua_createtime = pua_createtime;
    }

    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }
}
