package com.prettyyes.user.app.ui.appview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.MedalOrderRequest;
import com.prettyyes.user.app.adapter.InputAddressHolder;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.order.MyOrderListActivity;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ReceiveMedalSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import static com.prettyyes.user.app.ui.order.MyOrderListActivity.ORDER_PAY_SUCCESS;

/**
 * Created by chengang on 2017/6/2.
 */

public class ReceiveOrderDialog extends Dialog {


    private final InputAddressHolder inputAddressHolder;
    private RoundImageView img_covery;
    private TextView tv_title;
    private TextView tv_price;
    private Button btn_get;
    private View lin_checkorederAct_edit;

    // End Of Content View Elements

    private void bindViews() {

        img_covery = (RoundImageView) findViewById(R.id.img_covery);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        btn_get = (Button) findViewById(R.id.btn_get);
        lin_checkorederAct_edit = findViewById(R.id.lin_checkorederAct_edit);
    }


    public ReceiveOrderDialog(final Context context, final SpuInfoEntity data, final String medal_id, boolean canReceive) {
        super(context, R.style.simple_dialog);
        setContentView(R.layout.dialog_get_medal);
        bindViews();
        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_covery);
        tv_price.setText(StringUtils.getPrice(data.getSpu_price()));
        tv_title.setText(data.getSpu_name());
        img_covery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSkuActivity(context,data.getSpu_type(),data.getModule_id());
            }
        });
        btn_get.setEnabled(canReceive);
        inputAddressHolder = new InputAddressHolder(lin_checkorederAct_edit, (BaseActivity) context);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                v.setEnabled(false);
                if (inputAddressHolder.addressid == 0) {
                    AppUtil.showToastShort("请输入你的地址");
                    v.setEnabled(true);
                    return;
                }
                new MedalOrderRequest().setMedal_id(medal_id)
                        .setAddress_id(inputAddressHolder.addressid + "")
                        .post(new NetReqCallback<Object>() {
                            @Override
                            public void apiRequestFail(String message, String method) {
                                AppUtil.showToastShort(message);
                                v.setEnabled(true);

                            }

                            @Override
                            public void apiRequestSuccess(Object o, String method) {
                                ((BaseActivity) context).showSnack("领取成功");
                                AppRxBus.getInstance().post(new ReceiveMedalSuccessEvent(medal_id));
                                dismiss();
                                MyOrderListActivity.goOrderListActivity(getContext(), ORDER_PAY_SUCCESS);
                                v.setEnabled(true);
//                                JumpPageManager.getManager().goOrderListActivity(context);
//                                v.setEnabled(true);
//                                RxBus.getInstance().post(new OrderPaySuccessEvent());


                            }
                        });

            }
        });


    }


}
