package com.prettyyes.user.app.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.LoginByNormalReq;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.setting.BindPhoneActivity;
import com.prettyyes.user.app.ui.setting.ForgetPwdActivity;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.LoginHandler;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.user.UserInfo;

import org.xutils.view.annotation.ViewInject;
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditTextDel edit_login_username;
    private EditTextDel edit_login_password;
    private TextView btn_login_register;
    private Button btn_login_login;
    private TextView btn_login_forgetpwd;
    private String client_id = "";
    @ViewInject(R.id.img_login_weibo)
    private RoundImageView sina;
    @ViewInject(R.id.img_login_weixin)
    private RoundImageView wechat;
    @ViewInject(R.id.img_login_qq)
    private RoundImageView qq;
    private LoginHandler loginHandler;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void loadData() {
    }

    private void bindViews() {
        edit_login_username = (EditTextDel) findViewById(R.id.edit_login_username);
        edit_login_password = (EditTextDel) findViewById(R.id.edit_login_password);
        btn_login_register = (TextView) findViewById(R.id.btn_login_register);
        btn_login_login = (Button) findViewById(R.id.btn_login_login);
        btn_login_forgetpwd = (TextView) findViewById(R.id.btn_login_forgetpwd);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppStatistics.onPageStart(this, "login");
        AppStatistics.onEvent(this, "login_page");

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppStatistics.onPageEnd(this, "login");
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_login_new;
    }

    @Override
    protected void initViews() {
        hideHeader();
        bindViews();
        btn_login_forgetpwd.setFocusable(true);
        btn_login_forgetpwd.requestFocus();
        loginHandler = LoginHandler.create().setActivity(this);
    }

    @Override
    public void setStatusBar() {
        setTranslucenBar();

    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        inentFliter.addAction(Constant.LOGIN_SUCCESSS);
    }

    @Override
    protected void setListener() {
        super.setListener();
        btn_login_login.setOnClickListener(this);
        btn_login_register.setOnClickListener(this);
        btn_login_forgetpwd.setOnClickListener(this);
        sina.setOnClickListener(this);
        wechat.setOnClickListener(this);
        qq.setOnClickListener(this);
        edit_login_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (event != null) {
                    //点击登录

                    login();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_login_password.getWindowToken(), 0);
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                login();
                btn_login_login.setEnabled(false);
                break;
            case R.id.btn_login_register:
                RegisterActivity.getRegister(this);
                break;
            case R.id.btn_login_forgetpwd:
                nextActivity(ForgetPwdActivity.class);
                break;
            case R.id.img_login_weixin:
                loginHandler.loginWechat();
                break;
            case R.id.img_login_weibo:
                loginHandler.loginSina();
                break;
            case R.id.img_login_qq:
                loginHandler.loginQQ();
                break;

        }
    }

    private void login() {

        String username = edit_login_username.getText().toString();
        if (StringUtils.strIsEmpty(username)) {
            AppUtil.showToastShort(getString(R.string.empty_phone));
            return;
        }
        String password = edit_login_password.getText().toString();
        if (StringUtils.strIsEmpty(password)) {
            AppUtil.showToastShort(getString(R.string.empty_pwd));
            return;
        }
        new LoginByNormalReq().setUsername(username).setPassword(password).post(new NetReqCallback<UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                btn_login_login.setEnabled(true);
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(UserInfo userInfo, String method) {
                btn_login_login.setEnabled(true);
                loginsuccess(userInfo, false);
            }
        });

    }



    public void loginsuccess(UserInfo userInfo, boolean isother, int... type) {
        if (userInfo.isRedirect_bind_page() && isother) {
            showToastShort("请绑定手机");
            Intent intent = new Intent(this, BindPhoneActivity.class);
            if (type != null && type.length > 0) {
                intent.putExtra("type", type[0]);
                intent.putExtra("id", "");
                intent.putExtra("accesstoken", "");
            }
            nextActivity(intent);
            return;
        }
        showToastShort("登陆成功");
        AccountDataRepo.getAccountManager().login(userInfo);
        sendBrodcast(Constant.LOGIN_REFRESH);
        closeLogin();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginHandler.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {

    }

    private void closeLogin() {
        back();
        sendBrodcast(Constant.LOGIN_CLOSE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeLogin();
    }
}
