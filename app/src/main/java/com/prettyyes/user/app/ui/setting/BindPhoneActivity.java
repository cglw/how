package com.prettyyes.user.app.ui.setting;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.BindPhonePresenter;
import com.prettyyes.user.app.mvpView.BindPhoneView;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;

import org.xutils.view.annotation.ViewInject;

public class BindPhoneActivity extends BaseActivity implements BindPhoneView, View.OnClickListener {

    @ViewInject(R.id.edit_bindPhoneAct_telephone)
    private EditTextDel etl_phone;
    @ViewInject(R.id.edit_bindPhoneAct_pwd)
    private EditTextDel etl_pwd;
    @ViewInject(R.id.edit_bindPhoneAct_verycode)
    private EditTextDel etl_code;
    @ViewInject(R.id.tv_bindPhoneAct_getcode)
    private TextView tv_getcode;
    @ViewInject(R.id.btn_bindPhoneAct_bind)
    private Button btn_bind;
    private BindPhonePresenter bindPhonePresenter = new BindPhonePresenter(this);
    public static final int TYPE_WECHAT = 0;
    public static final int TYPE_WEIBO = 1;
    public static final int TYPE_CHANGE = 2;
    public static final int TYPE_QQ = 3;
    private int type;
    private String id = "";
    private String accesstoken = "";

    @Override
    protected int bindLayout() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initVariables() {
        type = getIntent().getIntExtra("type", TYPE_WECHAT);
        id = getIntent().getStringExtra("id");
        accesstoken = getIntent().getStringExtra("accesstoken");

    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void initViews() {
        setActivtytitle("手机绑定");
        showBack();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setListener() {
        tv_getcode.setOnClickListener(this);
        btn_bind.setOnClickListener(this);
    }

    @Override
    public String getPhone() {
        return etl_phone.getText().toString();
    }

    @Override
    public String getPwd() {
        return etl_pwd.getText().toString();
    }

    @Override
    public String getCode() {
        return etl_code.getText().toString();
    }

    @Override
    public TextView getTv_getcode() {
        return tv_getcode;
    }

    @Override
    public void setVeryCodeString(String tv) {
        tv_getcode.setText(tv);
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bindPhoneAct_getcode:
                bindPhonePresenter.postGetcode();
                break;
            case R.id.btn_bindPhoneAct_bind:
                switch (type) {
                    case TYPE_WECHAT:
                        bindPhonePresenter.postLogin(id, accesstoken, type);
                        break;
                    case TYPE_WEIBO:
                        bindPhonePresenter.postLogin(id, accesstoken, type);
                        break;
                    case TYPE_CHANGE:
                        bindPhonePresenter.changeTelephone();
                        break;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
