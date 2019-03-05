package com.prettyyes.user.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.LoginHandler;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.user.UserInfo;

import org.xutils.view.annotation.ViewInject;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private UserApiImpl userApi;
    private EditTextDel edit_register_telephone;
    private EditTextDel edit_register_password;
    private EditTextDel edit_register_verify;
    private TextView tv_register_getVerifyCode;
    private Button tv_register_register;
    private int times = 30;//倒计时
    @ViewInject(R.id.img_register_sina)
    private ImageView sina;
    @ViewInject(R.id.img_register_wechat)
    private ImageView wechat;
    @ViewInject(R.id.img_register_qq)
    private ImageView qq;
    @ViewInject(R.id.btn_register_login)
    private TextView back;
    @ViewInject(R.id.check_protocol)
    private CheckBox check_protocol;
    private LoginHandler loginHandler;

    @Override
    protected void onStart() {
        super.onStart();
        AppStatistics.onPageStart(this, "register");
        AppStatistics.onEvent(this, "register");

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppStatistics.onPageEnd(this, "register");

    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    public void protocol(View view) {
        JumpPageManager.getManager().goWebActivity(this, AppConfig.PARTNER_AGREE);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                if (times >= 0) {
                    tv_register_getVerifyCode.setText(times + "s");
                    times--;
                    sendEmptyMessageDelayed(1, 1000);
                } else {
                    tv_register_getVerifyCode.setText(getString(R.string.verify_getcode));
                    tv_register_getVerifyCode.setEnabled(true);
                    times = 30;
                }
            }
        }
    };

    public static void getRegister(Activity activity) {
        if (activity instanceof BaseActivity)
            ((BaseActivity) activity).nextActivity(RegisterActivity.class);
        else
            activity.startActivity(new Intent(activity, RegisterActivity.class));
    }

    public static void getRegister(Context activity) {
        if (activity instanceof BaseActivity)
            ((BaseActivity) activity).nextActivity(RegisterActivity.class);
        else
            activity.startActivity(new Intent(activity, RegisterActivity.class));
    }


    private void bindViews() {
        edit_register_telephone = (EditTextDel) findViewById(R.id.edit_register_telephone);
        edit_register_password = (EditTextDel) findViewById(R.id.edit_register_password);
        edit_register_verify = (EditTextDel) findViewById(R.id.edit_register_verify);
        tv_register_getVerifyCode = (TextView) findViewById(R.id.btn_register_getVerifyCode);
        tv_register_register = (Button) findViewById(R.id.btn_register_register);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_register_new;
    }

    @Override
    protected void initViews() {

        hideHeader();
        bindViews();
        tv_register_register.setFocusable(true);
        tv_register_register.requestFocus();
        loginHandler = LoginHandler.create().setActivity(this);
    }

    @Override
    public void setStatusBar() {
        setTranslucenBar();
    }

    @Override
    protected void setListener() {
        tv_register_getVerifyCode.setOnClickListener(this);
        tv_register_register.setOnClickListener(this);
        sina.setOnClickListener(this);
        wechat.setOnClickListener(this);
        qq.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        userApi = new UserApiImpl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_register_wechat:
                if (check_protocol.isChecked()) {
                    loginHandler.loginWechat();
                } else {
                    AppUtil.showToastShort("你需要同意《用户协议》");
                }
                break;
            case R.id.img_register_sina:
                if (check_protocol.isChecked()) {
                    loginHandler.loginSina();
                } else {
                    AppUtil.showToastShort("你需要同意《用户协议》");
                }
                break;
            case R.id.img_register_qq:
                if (check_protocol.isChecked()) {
                    loginHandler.loginQQ();
                } else {
                    AppUtil.showToastShort("你需要同意《用户协议》");
                }
                break;
            case R.id.btn_register_register:
                if (check_protocol.isChecked()) {
                    register(edit_register_telephone.getText().toString(), edit_register_telephone.getText().toString(), edit_register_password.getText().toString(),
                            "1", edit_register_verify.getText().toString(), "", 2);
                } else {
                    AppUtil.showToastShort("你需要同意《用户协议》");
                }

                break;
            case R.id.btn_register_getVerifyCode:
                tv_register_getVerifyCode.setText("获取中...");
                getRegisterCode(edit_register_telephone.getText().toString(), "reg");
                break;
            case R.id.btn_register_login:
                nextActivity(LoginActivity.class);
                break;
        }
    }


    private void getRegisterCode(String telephone, String template_type) {
        userApi.userTelephoneVerify(telephone, template_type, new NetWorkCallback() {
            @Override
            public void apiRequestFail(String code, String message) {
                showToastShort(message);
                tv_register_getVerifyCode.setEnabled(true);
                tv_register_getVerifyCode.setText(getString(R.string.verify_getcode));
            }

            @Override
            public void apiRequestSuccess(ApiResContent apiResponse) {
                showToastShort(apiResponse.getMsg());
                if (apiResponse.isSuccess()) {
                    tv_register_getVerifyCode.setEnabled(false);
                    handler.sendEmptyMessage(1);
                } else {
                    tv_register_getVerifyCode.setEnabled(true);
                    tv_register_getVerifyCode.setText(getString(R.string.verify_getcode));
                }
            }
        });
    }

    private void register(final String username, final String telephone, final String password, String type, String vreify, String slogan, int gender) {

        if (StringUtils.strIsEmpty(telephone)) {
            AppUtil.showToastShort(getString(R.string.empty_phone));
            return;
        }
        if (StringUtils.strIsEmpty(password)) {
            AppUtil.showToastShort(getString(R.string.empty_pwd));
            return;
        }
        if (StringUtils.strIsEmpty(vreify)) {
            AppUtil.showToastShort(getString(R.string.empty_code));
            return;
        }

        userApi.userRegister(username, telephone, password, type, vreify, slogan, gender, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                showToastShort("注册成功");
                login(telephone, password);

            }


        });
    }


    private void login(String username, String password) {
        userApi.userLogin(username, password, "", new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(UserInfo userLoginInfo, String method) {
                loginsuccessbyRegister(userLoginInfo);

            }
        });
    }



    private void loginsuccessbyRegister(UserInfo userInfo) {
        LogUtil.i(TAG, "loginsuccessbyRegister");
        AccountDataRepo.getAccountManager().login(userInfo);
        sendBrodcast(Constant.LOGIN_REFRESH);
        sendBrodcast(Constant.LOGIN_SUCCESSS);
        nextActivity(MainActivity.class);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginHandler.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        super.setInentFliter(inentFliter);
        inentFliter.addAction(Constant.LOGIN_CLOSE);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
        super.handlerIntenter(context, intent);
        if (intent.getAction().equals(Constant.LOGIN_CLOSE))
            finish();
    }

}
