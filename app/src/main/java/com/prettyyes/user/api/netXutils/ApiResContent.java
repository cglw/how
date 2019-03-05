package com.prettyyes.user.api.netXutils;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils
 * Author: SmileChen
 * Created on: 2016/7/19
 * Description: Nothing
 */
public class ApiResContent<T> {
    /**
     * 接口均采用POST方式提交
     *返回数据为JSON格式，共有3个值res，msg，extra
     *res为返回值码，200表示请求成功，200以上代表各种错误
     *msg为提示信息
     *extra为额外返回数据
     */
    String msg;
    String res;
    T extra;

    public boolean isSuccess()
    {
        if(res.equals("200"))
        {
            return true;
        }
        return false;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public T getExtra() {
        return extra;
    }

    public void setExtra(T extra) {
        this.extra = extra;
    }
}
