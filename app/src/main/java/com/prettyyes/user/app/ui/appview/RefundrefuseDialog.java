package com.prettyyes.user.app.ui.appview;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.DisAgreeOrderRefundRequest;
import com.prettyyes.user.core.event.RefreshRefundChangeEvent;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.AppRxBus;

/**
 * Created by chengang on 2017/6/2.
 */

public class RefundrefuseDialog extends Dialog {


    private EditText edit_reason;
    private TextView tv_refund_refuse;


    public RefundrefuseDialog(final Activity context, final String order_number) {
        super(context, R.style.simple_dialog);
        setContentView(R.layout.dialog_refund_refuse);
        edit_reason = (EditText) findViewById(R.id.edit_reason);
        tv_refund_refuse = (TextView) findViewById(R.id.tv_refund_refuse);
        tv_refund_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getReason().length() <= 0) {
                    AppUtil.showToastShort("拒绝理由不可以为空");
                    return;
                } else {
                    new DisAgreeOrderRefundRequest().setOrder_number(order_number).setDisagree_reason(getReason()).post(new NetReqCallback<Object>() {
                        @Override
                        public void apiRequestFail(String message, String method) {
                            AppUtil.showToastShort(message);

                        }

                        @Override
                        public void apiRequestSuccess(Object o, String method) {
                            AppUtil.showToastShort("拒绝退款成功");
                            dismiss();
                            if (context != null)
                                context.finish();
                            AppRxBus.getInstance().post(new RefreshRefundChangeEvent());

                        }
                    });

                }
            }
        });

    }

    public String getReason() {
        return edit_reason.getText().toString();
    }


}
