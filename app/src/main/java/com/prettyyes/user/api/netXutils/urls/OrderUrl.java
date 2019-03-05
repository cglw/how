package com.prettyyes.user.api.netXutils.urls;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.urls
 * Author: SmileChen
 * Created on: 2016/7/21
 * Description: 订单Url
 */
public class OrderUrl extends BaseUrl{
    //订单列表
    public static final String Url_orderGetList= url+"/app/order/getList";


    //订单详情
    public static final String Url_orderGetInfo= url+"/app/order/getInfo";
    //订单去支付
    public static final String Url_orderGoPay= url+"/app/order/goPay";
    //订单取消
    public static final String Url_orderCancel= url+"/app/order/cancel";
    //查看物流
    public static final String Url_orderGetShipInfo= url+"/app/order/getShipInfo";
    //确认收货
    public static final String Url_orderTaskOver= url+"/app/order/takeOver";
    //订单删除
    public static final String Url_orderDel= url+"/app/order/del";
    //提醒发货
    public static final String Url_orderRemind= url+"/app/order/remind";
    //买家订单评论
    public static final String Url_orderOrderComment= url+"/app/order/ordercomment";
    //获取订单角标数量
    public static final String Url_orderGetmessagecount= url+"/app/order/getmessagecount";
  //获取订单角标数量
    public static final String Url_cartNum= url+"/app/cart/cartNum";
    //订单确认页
    public static final String Url_orderCheck= url+"/app/order/check";
    //生成订单
    public static final String Url_orderCreate= url+"/app/order/create";
    //获得wishlist
    public static final String Url_wishList= url+"/app/cart/cartList";
    //删除购物车单品
    public static final String Url_cartdel = url+"/app/cart/delCart";
    //添加购物车单品
    public static final String Url_cartAdd= url+"/app/cart/addCart";
    //订单确认信息
    public static final String Url_orderCheckInfo= url+"/app/order/check";
    //订单确认信息
    public static final String Url_orderPay= url+"/app/order/goPay";
    //打赏
    public static final String Url_orderReward= url+"/app/order/reward";
    //購物車編輯
    public static final String Url_cartEdit= url+"/app/cart/editCart";
    //子订单商品评论列表
    public static final String Url_orderUnitList= url+"/app/order/orderUnitList";
    //購物車編輯
    public static final String Url_ordercomment= url+"/app/order/ordercomment";
    //获取paper详情
    public static final String Url_paperinfo= url+"/app/paper/getPaper";
    //获取rewardlist
    public static final String Url_rewradlist= url+"/app/reward/getList";
}
