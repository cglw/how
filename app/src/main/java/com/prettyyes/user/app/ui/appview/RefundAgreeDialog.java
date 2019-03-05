package com.prettyyes.user.app.ui.appview;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.AgreeCancelVerifyCodeRquest;
import com.prettyyes.user.api.netXutils.requests.AgreeOrderRefundRequest;
import com.prettyyes.user.core.event.RefreshRefundChangeEvent;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.AppRxBus;

/**
 * Created by chengang on 2017/6/2.
 */

public class RefundAgreeDialog extends Dialog {


    private VeriCodeView vericodeview;
    private TextView tv_refund_cancel;
    private TextView tv_refund_agree;


    public RefundAgreeDialog(final Activity context, final String order_number) {
        super(context, R.style.simple_dialog);
        setContentView(R.layout.dialog_refund_agree);
        setCancelable(false);
        vericodeview = (VeriCodeView) findViewById(R.id.vericodeview);
        tv_refund_cancel = (TextView) findViewById(R.id.tv_refund_cancel);
        tv_refund_agree = (TextView) findViewById(R.id.tv_refund_agree);
        tv_refund_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
        vericodeview.setClickCallback(new VeriCodeView.ClickCallback() {
            @Override
            public void success() {
                new AgreeCancelVerifyCodeRquest().post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        vericodeview.sendVeriCodeSuccess();
                    }
                });
            }
        });
        tv_refund_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vericodeview.getVerifyCode().length() <= 0) {
                    AppUtil.showToastShort("验证码不能为空");
                    return;
                }
                new AgreeOrderRefundRequest().setOrder_number(order_number).setVerify(vericodeview.getVerifyCode()).post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        AppUtil.showToastShort(message);

                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        dismiss();
                        AppUtil.showToastShort("同意退款成功");
                        context.finish();
                        AppRxBus.getInstance().post(new RefreshRefundChangeEvent());
                    }
                });

            }
        });


    }


}
