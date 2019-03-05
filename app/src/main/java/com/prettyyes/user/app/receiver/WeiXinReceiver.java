package com.prettyyes.user.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.receiver
 * Author: SmileChen
 * Created on: 2016/8/1
 * Description: Nothing
 */
public class WeiXinReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

    }
    public void onResp(BaseResp resp){
        if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
        }
    }
}
