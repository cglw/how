package com.prettyyes.user.core.order;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.order
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public interface OrderCoreApi {
    void createOrder(String uuid, String user_remark, int address_id, int coupon_id, int buy_now, int pay_type);

    void payOrder(String uuid, String order_number, int pay_type);

    void rewardOrder(String uuid, int pay_type, String reward_type, int reward_type_id,int task_id,float price,String ...params);


}
