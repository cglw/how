package com.prettyyes.user.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.igexin.sdk.PushConsts;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.order.MyOrderListActivity;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.containter.ZBundleCore;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.receiver
 * Author: SmileChen
 * Created on: 2016/7/21
 * Description: Nothing
 */
public class PrettyyesPushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_CLIENTID:
                String cid = bundle.getString("clientid");
                // TODO:处理cid返回
                break;
            case PushConsts.GET_MSG_DATA:
                String taskid = bundle.getString("taskid");
                String messageid = bundle.getString("messageid");
                byte[] payload = bundle.getByteArray("payload");
                if (payload != null) {
                    String data = new String(payload);
                    handReceiver(context, bundle);
                    // TODO:接收处理透传（payload）数据
                }
                break;
            default:
                break;
        }
    }

    private void handReceiver(Context context, Bundle bundle) {
        byte[] payload = bundle.getByteArray("payload");
        if (payload != null) {
            String data = new String(payload);
            new PushHandler(context).handReceiveData(data);
        }
    }

    private void handReceiver1(Context context, Bundle bundle) {
        if (ZBundleCore.getInstance().isExist(MainActivity.class)) {
            byte[] payload = bundle.getByteArray("payload");
            if (payload != null) {
                String data = new String(payload);
                switch (data) {
                    case "2":
                        startMainAlive(context, bundle);
                        break;
                    case "3":
                        startOrderList(context);
                        break;
                    case "4":
                        startOrderList(context);
                        break;
                    case "5":
                        startOrderList(context);
                        break;
                    case "10":
                        startOrderList(context);
                        break;
                }
            }

        } else {
            startMain(context, bundle);
        }
    }

    private void startMain(Context context, Bundle bundle) {
        Intent i = new Intent();
        i.setClass(context, MainActivity.class);
        i.putExtras(bundle);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

    private void startMainAlive(Context context, Bundle bundle) {
        Intent i = new Intent();
        i.setClass(context, MainActivity.class);
        i.putExtras(bundle);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    private void startOrderList(Context context) {
        context.startActivity(new Intent(context, MyOrderListActivity.class));
    }

}
