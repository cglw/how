package com.prettyyes.user.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.core.event.OrderPayCancelEvent;
import com.prettyyes.user.core.event.OrderPaySuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, AppConfig.APPID_WeiXin);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Intent intent = new Intent();
        LogUtil.i(TAG,resp.errStr+"");
        LogUtil.i(TAG,resp.errCode+"");
        LogUtil.i(TAG,resp.openId+"");
        LogUtil.i(TAG,resp.transaction+"");

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:
                    Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                    intent.setAction(Constant.OrderPaySuccess);
                    sendBroadcast(intent);
                    AppRxBus.getInstance().post(new OrderPaySuccessEvent());
                    finish();
                    break;
                case -1:
                    Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                    intent.setAction(Constant.OrderPayCancel);
                    sendBroadcast(intent);
                    AppRxBus.getInstance().post(new OrderPayCancelEvent());

                    finish();
                    break;
                case -2:
                    Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
                    intent.setAction(Constant.OrderPayCancel);
                    sendBroadcast(intent);
                    AppRxBus.getInstance().post(new OrderPayCancelEvent());
                    finish();
                    break;
            }

        }


    }
}