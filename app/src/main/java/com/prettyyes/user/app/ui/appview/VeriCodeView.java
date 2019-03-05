package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.event.SendVerifyCodeEvent;
import com.prettyyes.user.core.utils.AppRxBus;

import org.xutils.view.annotation.ViewInject;


/**
 * Created by chengang on 2017/6/14.
 */

public class VeriCodeView extends AbsLinearlayoutView {

    @ViewInject(R.id.btn_getcode)
    Button btn_getcode;
    @ViewInject(R.id.etv_code)
    EditTextDel etv_code;

    int left_time = 30;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (left_time >= 0) {
                    btn_getcode.setText(String.format("%d s", left_time));
                    left_time--;
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    btn_getcode.setText(getContext().getString(R.string.verify_code_get));
                    btn_getcode.setEnabled(true);
                    left_time = 30;
                }
            }
        }
    };

    public VeriCodeView(Context context) {
        super(context);
    }

    public VeriCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public String getVerifyCode() {
        return etv_code.getText().toString();
    }

    @Override
    public int bindLayout() {
        return R.layout.view_get_code;
    }

    @Override
    public void initViews() {
        btn_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCallback != null) {
                    clickCallback.success();
                }

            }
        });
    }

    public void sendVeriCodeSuccess() {
        btn_getcode.setText(getContext().getString(R.string.verify_code_geting));
        handler.sendEmptyMessageDelayed(1, 1000);
        btn_getcode.setEnabled(false);
        AppRxBus.getInstance().post(new SendVerifyCodeEvent(btn_getcode));

    }

    private ClickCallback clickCallback;

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    public interface ClickCallback {
        void success();
    }

    @Override
    public void setDataByModel(Object data) {

    }

}
