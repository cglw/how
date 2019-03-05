package com.prettyyes.user.app.ui.appview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.HowScoreGoodsExChangeReq;
import com.prettyyes.user.app.adapter.InputAddressHolder;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.order.MyOrderListActivity;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ReceiveHowScoreGoodsSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.HowScoreGoods;

import static com.prettyyes.user.app.ui.order.MyOrderListActivity.ORDER_PAY_SUCCESS;

/**
 * Created by chengang on 2017/6/2.
 */

public class ReceiveHowScoreGoodsDialog extends Dialog {


    private final InputAddressHolder inputAddressHolder;
    private RoundImageView img_covery;
    private TextView tv_title;
    private TextView tv_score;
    private Button btn_get;
    private View lin_checkorederAct_edit;

    // End Of Content View Elements

    private void bindViews() {

        img_covery = (RoundImageView) findViewById(R.id.img_covery);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_score = (TextView) findViewById(R.id.tv_score);
        btn_get = (Button) findViewById(R.id.btn_get);
        lin_checkorederAct_edit = findViewById(R.id.lin_checkorederAct_edit);
    }


    public ReceiveHowScoreGoodsDialog(final Context context, final HowScoreGoods data) {
        super(context, R.style.simple_dialog);
        setContentView(R.layout.dialog_howscore_to_goods);
        bindViews();

        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_covery);
        tv_score.setText(String.format("%s分", data.getHow_score()));
        tv_title.setText(data.getSpu_name());

        img_covery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSkuActivity(context,data.getSpu_type(),data.getModule_id());
            }
        });
        btn_get.setEnabled(data.canReceive());
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
                new HowScoreGoodsExChangeReq()
                        .setRechange_id(data.getId())
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
                                dismiss();
                                AppRxBus.getInstance().post(new ReceiveHowScoreGoodsSuccessEvent(data.getId()));
                                MyOrderListActivity.goOrderListActivity(getContext(), ORDER_PAY_SUCCESS);
                                v.setEnabled(true);
                            }
                        });

            }
        });


    }


}
