package com.prettyyes.user.app.ui.setting;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;

public class ForgetPwdActivity extends BaseActivity {

    private String telephone;
    private String verify;
    private String password;
    private EditTextDel edit_forgetPwd_telephone;
    private EditTextDel edit_forgetPwd_password;
    private EditTextDel edit_forgetPwd_verify;
    private TextView btn_forgetPwd_getVerifyCode;
    private Button btn_reset;
    private int times = 30;//倒计时

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                if (times >= 0) {
                    btn_forgetPwd_getVerifyCode.setText(times + "s");
                    times--;
                    sendEmptyMessageDelayed(1, 1000);
                } else {
                    btn_forgetPwd_getVerifyCode.setText(getString(R.string.verify_getcode));
                    btn_forgetPwd_getVerifyCode.setEnabled(true);
                    times = 30;
                }
            }
        }
    };

    private void bindViews() {
        edit_forgetPwd_telephone = (EditTextDel) findViewById(R.id.edit_forgetPwd_telephone);
        edit_forgetPwd_password = (EditTextDel) findViewById(R.id.edit_forgetPwd_password);
        edit_forgetPwd_verify = (EditTextDel) findViewById(R.id.edit_forgetPwd_verify);
        btn_forgetPwd_getVerifyCode = (TextView) findViewById(R.id.btn_forgetPwd_getVerifyCode);
        btn_reset = (Button) findViewById(R.id.btn_forgetPwd_reset);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initViews() {
        setActivtytitle("忘记密码");
        bindViews();
    }

    @Override
    protected void setListener() {
        btn_forgetPwd_getVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // telephone = getViewTxt(edit_forgetPwd_telephone);
                btn_forgetPwd_getVerifyCode.setText(getString(R.string.verify_getcode_geting));
                getCode(edit_forgetPwd_telephone.getText().toString());
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telephone = edit_forgetPwd_telephone.getText().toString();
                password = edit_forgetPwd_password.getText().toString();
                verify = edit_forgetPwd_verify.getText().toString();
                forgetPwdUpd(telephone, verify, password);
            }
        });
    }

    private void getCode(String telephone) {
        new UserApiImpl().userForget_getcode(telephone, new NetWorkCallback() {
            @Override
            public void apiRequestFail(String code, String message) {
                showToastShort(message);
                btn_forgetPwd_getVerifyCode.setText(getString(R.string.verify_getcode));
                btn_forgetPwd_getVerifyCode.setEnabled(true);
            }

            @Override
            public void apiRequestSuccess(ApiResContent apiResponse) {
                showToastShort(apiResponse.getMsg());
                if (apiResponse.isSuccess()) {
                    btn_forgetPwd_getVerifyCode.setEnabled(false);
                    handler.sendEmptyMessage(1);
                } else {
                    btn_forgetPwd_getVerifyCode.setText(getString(R.string.verify_getcode));
                    btn_forgetPwd_getVerifyCode.setEnabled(true);
                }
            }
        });
    }

    private void forgetPwdUpd(String telephone, String verify, String password) {
        new UserApiImpl().userForgetUpd(telephone, verify, password, new NetWorkCallback() {
            @Override
            public void apiRequestFail(String code, String message) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(ApiResContent apiResponse) {
                showToastShort(apiResponse.getMsg());
                if (apiResponse.isSuccess())
                    finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null)
        handler.removeMessages(1);
    }
}
