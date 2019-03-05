package com.prettyyes.user.api.netXutils.ApiImpls;

import com.google.gson.reflect.TypeToken;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.Apis.OrderApi;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.api.netXutils.urls.OrderUrl;
import com.prettyyes.user.model.order.OrderCheck;
import com.prettyyes.user.model.order.OrderCommentList;
import com.prettyyes.user.model.order.OrderCreate;
import com.prettyyes.user.model.order.OrderGoPay;
import com.prettyyes.user.model.order.OrderInfo;
import com.prettyyes.user.model.order.OrderList;
import com.prettyyes.user.model.order.OrderMessageCount;
import com.prettyyes.user.model.order.OrderShipInfo;
import com.prettyyes.user.model.order.WishListEntity;
import com.prettyyes.user.model.type.PaperEntity;
import com.prettyyes.user.model.type.RewardListEntity;
import com.prettyyes.user.model.v8.CartListRes;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static com.prettyyes.user.data.DataCenter.ANSWER_ID_CURRENT;
import static com.prettyyes.user.data.DataCenter.SELLRT_ID_CURRENT;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.ApiImpls
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class OrderApiImpl implements OrderApi {
    /**
     * 订单确认页
     *
     * @param uuid
     * @param suit_id
     * @param suit_unit_id_str
     * @param coupon_code
     * @param paramNetWorkCallback
     */

    @Override
    public void OrderCheck(String uuid, int suit_id, String suit_unit_id_str, String coupon_code, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("suit_id", suit_id);
        localHashMap.put("suit_unit_id_str", suit_unit_id_str);
        localHashMap.put("coupon_code", coupon_code);
        Type localType = new TypeToken<ApiResContent<OrderCheck>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderCheck, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 生成订单
     *
     * @param uuid
     * @param address_id
     * @param suit_id
     * @param suit_unit_id_str
     * @param pay_type
     * @param coupon_code
     * @param user_remark
     * @param paramNetWorkCallback
     */
    @Override
    public void OrderCreate(String uuid, String address_id, String suit_id, String suit_unit_id_str, String pay_type, String coupon_code, String user_remark, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("address_id", address_id);
        localHashMap.put("suit_unit_id_str", suit_unit_id_str);
        localHashMap.put("suit_id", suit_id);
        localHashMap.put("pay_type", pay_type);
        localHashMap.put("coupon_code", coupon_code);
        localHashMap.put("user_remark", user_remark);
        Type localType = new TypeToken<ApiResContent<OrderCreate>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderCreate, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 订单列表
     *
     * @param uuid
     * @param status         (选填)订单状态 1所有订单  2待付款  3退款 4已付款
     * @param page
     * @param netReqCallback
     */
    @Override
    public void OrderGetList(String uuid, int status, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("status", status);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<OrderList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderGetList, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getLocalizedMessage(),"OrderGetList");
        }
    }

    /**
     * 订单详情
     *
     * @param uuid
     * @param order_number
     * @param netReqCallback
     */
    @Override
    public void OrderGetInfo(String uuid, String order_number, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        Type localType = new TypeToken<ApiResContent<OrderInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderGetInfo, localHashMap, localType, netReqCallback, false);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"OrderGetList");
        }
    }


    /**
     * 订单去支付
     *
     * @param uuid
     * @param order_number
     * @param paramNetWorkCallback
     */
    @Override
    public void OrderGoPay(String uuid, String order_number, String pay_type, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        localHashMap.put("pay_type", pay_type);
        Type localType = new TypeToken<ApiResContent<OrderGoPay>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderGoPay, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 订单取消 根据res判断
     *
     * @param uuid
     * @param order_number
     * @param paramNetWorkCallback
     */
    @Override
    public void OrderCancel(String uuid, String order_number, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderCancel, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 查看物流信息
     *
     * @param uuid
     * @param order_number
     * @param paramNetWorkCallback
     */
    @Override
    public void OrderGetShipInfo(String uuid, String order_number, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        Type localType = new TypeToken<ApiResContent<OrderShipInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderGetShipInfo, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }


    /**
     * 确认收货 根据res 判断
     *
     * @param uuid
     * @param order_number
     * @param netReqCallback
     */
    @Override
    public void OrderTaskOver(String uuid, String order_number, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderTaskOver, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"OrderGetList");
        }
    }

    /**
     * 订单删除 根据res判断
     *
     * @param uuid
     * @param order_number
     * @param paramNetWorkCallback
     */
    @Override
    public void OrderDel(String uuid, String order_number, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderDel, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 提醒发货
     *
     * @param uuid
     * @param order_number
     * @param netReqCallback
     */
    @Override
    public void OrderRemind(String uuid, String order_number, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderRemind, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"OrderRemind");
        }
    }

    /**
     * 卖家订单评论 根据res判断
     *
     * @param uuid
     * @param order_number
     * @param content
     * @param suit_id
     * @param images
     * @param pretty_level
     * @param wish_level
     * @param server_level
     * @param paramNetWorkCallback
     */
    @Override
    public void OrderOrderComment(String uuid, String order_number, String content, int suit_id, String images, float pretty_level, float wish_level, float server_level, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        localHashMap.put("content", content);
        localHashMap.put("suit_id", suit_id);
        localHashMap.put("images", images);
        localHashMap.put("pretty_level", pretty_level);
        localHashMap.put("wish_level", wish_level);
        localHashMap.put("server_level", server_level);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderOrderComment, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 获取订单角标数量
     *
     * @param uuid
     * @param paramNetWorkCallback
     */
    @Override
    public void OrderGetMessageCount(String uuid, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<OrderMessageCount>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderGetmessagecount, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 购物车列表
     *
     * @param uuid
     * @param netReqCallback
     */
    @Override
    public void cartList(String uuid,int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<CartListRes>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_wishList, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"cartList");
        }
    }

    @Override
    public void cartNum(String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_cartNum, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"cartNum");
        }
    }

    /**
     * 删除购物车单品
     *
     * @param uuid
     * @param cart_id
     * @param netReqCallback
     */
    @Override
    public void cartdelgoods(String uuid, String cart_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("cart_id", cart_id);
        Type localType = new TypeToken<ApiResContent<WishListEntity>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_cartdel, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"cartdelgoods");
        }
    }

    /**
     * 添加购物车
     *
     * @param uuid
     * @param cart_type
     * @param suit_id
     * @param suit_unit
     * @param suit_taobao_id
     * @param num
     * @param cart_status
     * @param netReqCallback
     */
    @Override
    public void cartaddgoods(String uuid, String cart_type, int suit_id, String suit_unit, int suit_taobao_id, int num, int cart_status, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("cart_type", cart_type);
        localHashMap.put("suit_id", suit_id);
        localHashMap.put("suit_taobao_id", suit_taobao_id);
        localHashMap.put("suit_unit", suit_unit);
        localHashMap.put("num", num);
        localHashMap.put("cart_status", cart_status);
        Map<String, Integer> map = new HashMap<>();
        map.put("seller_id", SELLRT_ID_CURRENT);
        map.put("answer_id", ANSWER_ID_CURRENT);
        localHashMap.put("source_info", GsonHelper.getGson().toJson(map));
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_cartAdd, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"cartaddgoods");
        }
    }

    /**
     * 订单确认信息
     *
     * @param uuid
     * @param buy_now
     * @param netReqCallback
     */
    @Override
    public void orderCheckInfo(String uuid, int buy_now, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("buy_now", buy_now);
        Type localType = new TypeToken<ApiResContent<OrderCheck>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderCheckInfo, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"orderCheckInfo");
        }
    }

    /**
     * 生成订单
     *
     * @param uuid
     * @param user_remark
     * @param pay_type
     * @param coupon_id
     * @param buy_now
     * @param netReqCallback
     */
    @Override
    public void orderCreate(String uuid, String user_remark, int pay_type, int coupon_id, int buy_now, int address_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("user_remark", user_remark);
        localHashMap.put("pay_type", pay_type);
        localHashMap.put("coupon_id", coupon_id);
        localHashMap.put("buy_now", buy_now);
        localHashMap.put("address_id", address_id);
        Type localType = new TypeToken<ApiResContent<OrderCreate>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderCreate, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"orderCreate");
        }
    }

    @Override
    public void orderGopay(String uuid, String order_number, int pay_type, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number_parent", order_number);
        localHashMap.put("pay_type", pay_type);

        Type localType = new TypeToken<ApiResContent<OrderGoPay>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderPay, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"orderGopay");
        }
    }

    @Override
    public void orderReward(String uuid, int pay_type, String reward_type, int reward_type_id, int task_id, float price, NetReqCallback netReqCallback, String... params) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("pay_type", pay_type);
        localHashMap.put("reward_type", reward_type);
        localHashMap.put("reward_type_id", reward_type_id);
        localHashMap.put("task_id", task_id);
        localHashMap.put("price", price);
        if (params.length > 0)
            localHashMap.put("coupon_id", params[0]);

        Type localType = new TypeToken<ApiResContent<OrderCreate>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderReward, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"orderReward");
        }
    }

    /**
     * 修改wish list商品数量
     *
     * @param uuid
     * @param cart_id
     * @param num
     * @param cart_status    是否购买购买状态 0 保存在wish list  1购买
     * @param netReqCallback
     */
    @Override
    public void cartEdit(String uuid, String cart_id, int num, int cart_status, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("cart_id", cart_id);
        localHashMap.put("num", num);
        localHashMap.put("cart_status", cart_status);

        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_cartEdit, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"cartEdit");
        }
    }

    /**
     * /**
     * 子订单商品评论列表
     *
     * @param uuid
     * @param order_number
     * @param netReqCallback
     */
    @Override
    public void orderUintCommentList(String uuid, String order_number, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        Type localType = new TypeToken<ApiResContent<OrderCommentList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_orderUnitList, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"orderUintCommentList");
        }
    }

    /**
     * 买家订单评论
     *
     * @param uuid
     * @param order_number
     * @param json
     * @param netReqCallback json-->{
     *                       "image":""//多个用 , 隔开 String
     *                       "content":"" String
     *                       "order_unit_id":"" int
     *                       "pretty_level":"" float
     *                       "wish_level":"" float
     *                       "server_level":"" float
     *                       }
     */
    @Override
    public void ordercomment(String uuid, String order_number, String json, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("order_number", order_number);
        localHashMap.put("comment_data", json);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_ordercomment, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"ordercomment");
        }
    }

    @Override
    public void getPaper(String uuid, String paper_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("paper_id", paper_id);
        Type localType = new TypeToken<ApiResContent<PaperEntity>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_paperinfo, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"getPaper");
        }
    }

    @Override
    public void getRewardList(String uuid, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<RewardListEntity>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OrderUrl.Url_rewradlist, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"getRewardList");
        }
    }
}
