package com.prettyyes.user.app.ui.setting;

import android.view.View;
import android.widget.Button;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;

public class ResetPwdActivity extends BaseActivity {

    private String pwd_old;
    private String pwd_now;
    private String pwd_repeat;
    private EditTextDel edit_resetPwd_password_old;
    private EditTextDel edit_resetPwd_password_new;
    private EditTextDel edit_resetPwd_passwordreperat;
    private Button btn_repeatPwd_post;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    private void bindViews() {
        edit_resetPwd_password_old = (EditTextDel) findViewById(R.id.edit_resetPwd_password_old);
        edit_resetPwd_password_new = (EditTextDel) findViewById(R.id.edit_resetPwd_password_new);
        edit_resetPwd_passwordreperat = (EditTextDel) findViewById(R.id.edit_resetPwd_passwordreperat);
        btn_repeatPwd_post = (Button) findViewById(R.id.btn_repeatPwd_post);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_reset_pwd;
    }

    @Override
    protected void initViews() {
        setActivtytitle(getString(R.string.title_activity_resetPwd));
        bindViews();
    }

    @Override
    protected void setListener() {
        btn_repeatPwd_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd_old =edit_resetPwd_password_old.getText().toString();
                pwd_now = edit_resetPwd_password_new.getText().toString();
                pwd_repeat =edit_resetPwd_passwordreperat.getText().toString();
                getUUid(0);
            }
        });
    }

    @Override
    public void doSomethingByUUid(int type) {
        super.doSomethingByUUid(type);
        resetPwd(uuid_act,pwd_old, pwd_now, pwd_repeat);
    }

    private void resetPwd(String uuid, String password, String password_now, String password_now_repeat) {
        new UserApiImpl().userResetPwd(uuid, password, password_now, password_now_repeat, new NetWorkCallback() {
            @Override
            public void apiRequestFail(String code, String message) {
                showToastShort(message);
            }
            @Override
            public void apiRequestSuccess(ApiResContent apiResponse) {
                showToastShort(apiResponse.getMsg());
                if(apiResponse.isSuccess())
                finish();
            }
        });
    }
}
