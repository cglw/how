package com.prettyyes.user.api.netXutils.Apis;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.NetWorkCallback;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.Apis
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description:
 */
public interface OrderApi extends BaseApi {
    public abstract void OrderCheck(String uuid, int suit_id, String suit_unit_id_str, String coupon_code, NetWorkCallback paramNetWorkCallback);

    public abstract void OrderCreate(String uuid, String address_id, String suit_id, String suit_unit_id_str, String pay_type, String coupon_code, String user_remark, NetWorkCallback paramNetWorkCallback);


    public abstract void OrderGetList(String uuid, int status, int page, NetReqCallback netReqCallback);

    public abstract void OrderGetInfo(String uuid, String order_number, NetReqCallback netReqCallback);

    public abstract void OrderGoPay(String uuid, String order_number, String pay_type, NetWorkCallback paramNetWorkCallback);

    public abstract void OrderCancel(String uuid, String order_number, NetWorkCallback paramNetWorkCallback);

    public abstract void OrderGetShipInfo(String uuid, String order_number, NetWorkCallback paramNetWorkCallback);

    public abstract void OrderTaskOver(String uuid, String order_number, NetReqCallback netReqCallback);

    public abstract void OrderDel(String uuid, String order_number, NetWorkCallback paramNetWorkCallback);

    public abstract void OrderRemind(String uuid, String order_number, NetReqCallback netReqCallback);

    public abstract void OrderOrderComment(String uuid, String order_number, String content, int suit_id, String images, float pretty_level, float wish_level, float server_level, NetWorkCallback paramNetWorkCallback);

    public abstract void OrderGetMessageCount(String uuid, NetWorkCallback paramNetWorkCallback);

    public abstract void cartList(String uuid,int page, NetReqCallback netReqCallback);
    public abstract void cartNum(String uuid, NetReqCallback netReqCallback);

    public abstract void cartdelgoods(String uuid, String cart_id, NetReqCallback netReqCallback);

    public abstract void cartaddgoods(String uuid, String cart_type, int suit_id, String suit_unit, int suit_taobao_id, int num, int cart_status, NetReqCallback netReqCallback);

    public abstract void orderCheckInfo(String uuid, int buy_now, NetReqCallback netReqCallback);

    public abstract void orderCreate(String uuid, String user_remark, int pay_type, int coupon_id, int buy_now, int address_id, NetReqCallback netReqCallback);

    public abstract void orderGopay(String uuid, String order_number, int pay_type, NetReqCallback netReqCallback);

    public abstract void orderReward(String uuid, int pay_type, String reward_type, int reward_type_id, int task_id, float price, NetReqCallback netReqCallback,String...params);

    public abstract void cartEdit(String uuid, String cart_id, int num, int cart_status, NetReqCallback netReqCallback);

    public abstract void orderUintCommentList(String uuid, String order_number, NetReqCallback netReqCallback);

    public abstract void ordercomment(String uuid, String order_number, String json, NetReqCallback netReqCallback);

    public abstract void getPaper(String uuid, String paper_id, NetReqCallback netReqCallback);

    public abstract void getRewardList(String uuid, int page, NetReqCallback netReqCallback);

}
