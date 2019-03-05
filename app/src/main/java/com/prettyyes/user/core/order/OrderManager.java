package com.prettyyes.user.core.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.alipay.PayResult;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.order.OrdercommentActivity;
import com.prettyyes.user.app.ui.order.ShipStateActivity;
import com.prettyyes.user.core.event.CartNumEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.AddCartResponse;
import com.prettyyes.user.model.order.WechatPayEntity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.order
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public class OrderManager {


    private static final int TYPE_ALI = 1;
    private static final int TYPE_WEIXIN = 2;
    private OrderApiImpl orderApi;

    public OrderManager(Activity activity) {
        this.activity = activity;
        orderApi = new OrderApiImpl();
    }

    private static OrderManager orderManager;

    public OrderManager() {

    }

    public static OrderManager getInstance() {
        if (orderManager == null) {
            synchronized (OrderManager.class) {
                if (orderManager == null) {
                    orderManager = new OrderManager();
                }
            }
        }
        return orderManager;

    }

    public void refreshWishListNum() {
        new OrderApiImpl().cartNum(BaseApplication.UUID, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                AppRxBus.getInstance().post(new CartNumEvent(AddCartResponse.getNum(o)));
            }
        });
    }


    public AlertView OrderCreatAndPay(final String uuid, final String user_remark, final int address_id, final int coupon_id, final int buy_now) {
        DataCenter.CURRENT_PAY_TYPE = DataCenter.PayType.Order;
        AlertView alertView = new AlertView("选择支付方式", null, "取消", null,
                new String[]{"支付宝", "微信支付"},
                activity, AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                switch (position) {
                    case 0:
                        showToastShort("正在生成订单");
                        createAndPay(uuid, user_remark, address_id, coupon_id, buy_now, TYPE_ALI);
                        break;
                    case 1:
                        if (isWXAppInstalledAndSupported(activity)) {
                            createAndPay(uuid, user_remark, address_id, coupon_id, buy_now, TYPE_WEIXIN);
                            showToastShort("正在生成订单");
                        } else {
                            showToastShort("你还没有安装微信");
                        }
                        break;
                }
            }
        }).setCancelable(true);
        alertView.show();
        return alertView;
    }

    public AlertView OrderReward(final String uuid, final String reward_type, final int reward_type_id, final int task_id, final float price, final String... params) {
        DataCenter.CURRENT_PAY_TYPE = DataCenter.PayType.Paper;
        DataCenter.CURRENT_PAPER_ID = reward_type_id;
        AlertView alertView = new AlertView("选择支付方式", null, "取消", null,
                new String[]{"支付宝", "微信支付"},
                activity, AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                switch (position) {
                    case 0:
                        showToastShort("正在生成订单");
                        rewardorder(uuid, reward_type, reward_type_id, task_id, TYPE_ALI, price, params);
                        break;
                    case 1:
                        if (isWXAppInstalledAndSupported(activity)) {
                            rewardorder(uuid, reward_type, reward_type_id, task_id, TYPE_WEIXIN, price, params);
                            showToastShort("正在生成订单");
                        } else {
                            showToastShort("你还没有安装微信");
                        }
                        break;
                }
            }
        }).setCancelable(true);
        alertView.show();
        return alertView;
    }

    public AlertView OrderPay(final String uuid, final String ordernum) {
        DataCenter.CURRENT_PAY_TYPE = DataCenter.PayType.Order;
        AlertView alertView = new AlertView("选择支付方式", null, "取消", null,
                new String[]{"支付宝", "微信支付"},
                activity, AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                switch (position) {
                    case 0:
                        pay(uuid, ordernum, TYPE_ALI);
                        showToastShort("正在生成订单");
                        break;
                    case 1:
                        if (isWXAppInstalledAndSupported(activity)) {
                            pay(uuid, ordernum, TYPE_WEIXIN);
                            showToastShort("正在生成订单");
                        } else {
                            showToastShort("你还没有安装微信");
                        }
                        break;
                }
            }
        }).setCancelable(true);
        alertView.show();
        return alertView;
    }

    public void sendNotify(String uuid, String ordernum) {
        orderApi.OrderRemind(uuid, ordernum, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                showToastShort("提醒成功");
            }
        });
    }

    public void commentOrderComment(String ordernumber) {
        Intent intent = new Intent(activity, OrdercommentActivity.class);
        intent.putExtra(Constant.OrderNumber, ordernumber);
        activity.startActivity(intent);

    }

    public AlertView backOrderMoney(final String seller_id, final String order_id) {
        AlertView alertView = new AlertView("提醒", "退款需要和商家商量？", "先缓缓", new String[]{"现在就去"}, null, activity, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, final int position) {
                if (position == 0) {
                    AccountDataRepo.getAccountManager().chatWithSeller(activity, seller_id, order_id);
                }
            }
        }).setCancelable(true);
        alertView.show();
        return alertView;
    }

    public void lookOrderShip(String ordernumber) {
        Intent intent = new Intent(activity, ShipStateActivity.class);
        intent.putExtra("ordernum", ordernumber);
        activity.startActivity(intent);
    }

    public AlertView confirmReceiveOrder(final String ordernumber, final String uuid) {
        AlertView alertView = new AlertView("提醒", "确认收货？", "取消", new String[]{"确定"}, null, activity, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, final int position) {
                if (position == 0) {

                    new OrderApiImpl().OrderTaskOver(uuid, ordernumber, new NetReqCallback() {
                        @Override
                        public void apiRequestFail(String message, String method) {
                            showToastShort(message);
                        }

                        @Override
                        public void apiRequestSuccess(Object o, String method) {

                            ((BaseActivity) activity).sendBrodcast(Constant.OrderConfirmReceive);
                            commentOrderComment(ordernumber);
                        }

                    });
                }
            }
        }).setCancelable(true);
        alertView.show();
        return alertView;
    }

    private void rewardorder(String uuid, String reward_type, int reward_type_id, int task_id, final int paytype, final float price, String... params) {
        new OrderCoreHandler(new OrderCallback() {
            @Override
            public void alipayData(String alipay) {
                if (paytype == TYPE_ALI) {
                    payAli(alipay, activity);
                }
            }

            @Override
            public void weixinData(WechatPayEntity data) {
                if (paytype == TYPE_WEIXIN) {
                    openWeiXinActivity(activity, data);
                }
            }

            @Override
            public void alipaySuccess(String resultInfo, String resultStatus) {

            }
        }).rewardOrder(uuid, paytype, reward_type, reward_type_id, task_id, price, params);
    }

    private void createAndPay(String uuid, String user_remark, int address_id, int coupon_id, int buy_now, final int paytype) {
        new OrderCoreHandler(new OrderCallback() {
            @Override
            public void alipayData(String alipay) {

                if (paytype == TYPE_ALI) {
                    payAli(alipay, activity);
                }
            }

            @Override
            public void weixinData(WechatPayEntity data) {
                if (paytype == TYPE_WEIXIN) {
                    openWeiXinActivity(activity, data);
                }
            }

            @Override
            public void alipaySuccess(String resultInfo, String resultStatus) {

            }
        }).createOrder(uuid, user_remark, address_id, coupon_id, buy_now, paytype);
    }


    private void pay(String uuid, String ordernum, final int paytype) {
        new OrderCoreHandler(new OrderCallback() {
            @Override
            public void alipayData(String alipay) {
                Intent intent = new Intent();
                intent.setAction(Constant.OrderCreate);
                activity.sendBroadcast(intent);
                if (paytype == TYPE_ALI) {
                    payAli(alipay, activity);
                }
            }

            @Override
            public void weixinData(WechatPayEntity data) {
                Intent intent = new Intent();
                intent.setAction(Constant.OrderCreate);
                activity.sendBroadcast(intent);
                if (paytype == TYPE_WEIXIN) {
                    openWeiXinActivity(activity, data);
                }
            }

            @Override
            public void alipaySuccess(String resultInfo, String resultStatus) {

            }
        }).payOrder(uuid, ordernum, paytype);
    }


    private Activity activity;
    private static final int SDK_PAY_FLAG = 1;

    private void payAli(final String s, final Activity activity) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(activity);
                // 调用支付接口，获取支付结果
                LogUtil.i("OrderManager", "pay-->" + s);
                String result = alipay.pay(s, true);
                LogUtil.i("OrderManager", "result-->" + result);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void showToastShort(String content) {
        Toast.makeText(activity, content, Toast.LENGTH_SHORT).show();
    }


    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        showToastShort("支付成功");
                        Intent intent = new Intent();
                        intent.setAction(Constant.OrderPaySuccess);
                        (OrderManager.this.activity).sendBroadcast(intent);
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            showToastShort("支付结果确认中");
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            showToastShort("取消支付");

                        } else {
                            showToastShort("支付失败");

                        }
                        Intent intent = new Intent();
                        intent.setAction(Constant.OrderPayCancel);
                        (OrderManager.this.activity).sendBroadcast(intent);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    private void openWeiXinActivity(Context context, WechatPayEntity data) {
        LogUtil.e("openWeiXinActivity",data.toString());
        IWXAPI api = WXAPIFactory.createWXAPI(context, AppConfig.APPID_WeiXin);
        PayReq request = new PayReq();
        request.appId = AppConfig.APPID_WeiXin;
        request.partnerId = "1289672701";
        request.prepayId = data.getPrepay_id();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = data.getNonce_str();
        request.timeStamp = data.getTimestamp();
        request.sign = data.getSign();
        api.sendReq(request);
//        openWeiXinActivityTest(context, data);
    }

    private void openWeiXinActivityTest(Context context) {

        IWXAPI api = WXAPIFactory.createWXAPI(context, "wxe06ac0cd98607498");
        api.registerApp("wxe06ac0cd98607498");
        PayReq request = new PayReq();
        request.appId = "wxe06ac0cd98607498";
        request.partnerId = "1486916752";
        request.prepayId = "wx2017100922003739e47bbc340059666027";
        request.packageValue = "Sign=WXPay";
        request.nonceStr = "15f9801eacfd11e7bc27784f43638f7d";
        request.timeStamp = "1507558865";
        request.sign = "10C0AA154612C6570CB70A809B1198E6";
        api.sendReq(request);
    }

    private boolean isWXAppInstalledAndSupported(Activity activity) {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(activity, null);
        msgApi.registerApp(AppConfig.APPID_WeiXin);
        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
                && msgApi.isWXAppSupportAPI();
        return sIsWXAppInstalledAndSupported;
    }

    /**
     * 添加购物车，套系或者淘宝
     *
     * @param uuid
     * @param cart_type
     * @param id
     */
    public void addCartByDefault(String uuid, String cart_type, int id) {
        if (cart_type.equals(TYPE_TAOBAO)) {
            orderApi.cartaddgoods(uuid, cart_type, 0, "", id, 1, 0, new NetReqCallback() {
                @Override
                public void apiRequestFail(String message, String method) {
                    showToastShort(message);
                }

                @Override
                public void apiRequestSuccess(Object o, String method) {
                    showToastShort(activity.getString(R.string.addCartSuccess));
                }
            });
        } else if (cart_type.equals(TYPE_SUIT)) {
            orderApi.cartaddgoods(uuid, cart_type, id, "", 0, 1, 0, new NetReqCallback() {
                @Override
                public void apiRequestFail(String message, String method) {
                    showToastShort(message);
                }

                @Override
                public void apiRequestSuccess(Object o, String method) {
                    showToastShort(activity.getString(R.string.addCartSuccess));
                }
            });
        }
    }

    private String test = "input_charset=\"utf-8\"&body=\"How 订单170609292804305\"&notify_url=\"http://test.prettyyes.com/app/pay/alipayNotify\"&out_trade_no=\"170609292804305\"&partner=\"2088421550730483\"&payment_type=\"1\"&seller_id=\"dev@prettyyes.com\"&service=\"mobile.securitypay.pay\"&subject=\"How订单170609292804305\"&total_fee=\"327.99\"&sign=\"LbsCS%2BHby30wWppcREE7AcMM6hp3wz843n2eKRBX7dPPHfWSygQooZPnZ%2FrTjQPXKEn1nB7mtyCurlGrHrPoxnCpuFIQCjExXhtPs%2BhRIuaviC4ITfsJvJRrl7gIKcIy1bzT4wEwgSMnuvV%2FUhw25fmWG%2FY1CLcrLZBekKo5KWs%3D\"&sign_type=\"RSA\"";
}
