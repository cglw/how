package com.prettyyes.user.core.order;

import com.prettyyes.user.model.order.WechatPayEntity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.order
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public interface OrderCallback {
    public void alipayData(String alipay);

    public void weixinData(WechatPayEntity data);

    public void alipaySuccess(String resultInfo, String resultStatus);
}
