package com.prettyyes.user.app.ui.setting;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.ChangeBindPresenter;
import com.prettyyes.user.app.mvpView.ChangeBindView;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.model.user.UserInfo;

import org.xutils.view.annotation.ViewInject;

public class ChangeBindPhoneActivity extends BaseActivity implements ChangeBindView {

    @ViewInject(R.id.tv_changebindphoneAct_phone)
    private TextView tv_phone;
    @ViewInject(R.id.edit_changebindphoneAct_code)
    private EditTextDel editTextDel_code;
    @ViewInject(R.id.btn_changebindphoneAct_confirm)
    private Button btn_confirm;
    @ViewInject(R.id.btn_changebindphoneAct_getcode)
    private Button btn_getcode;
    public ChangeBindPresenter changeBindPresenter = new ChangeBindPresenter(this);

    @Override
    protected int bindLayout() {
        return R.layout.activity_change_bind_phone;
    }

    String phone = "";

    @Override
    protected void initViews() {
        setActivtytitle("更改绑定手机");
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void loadData() {
        if (getAccount() != null)
            phone = getAccount().getTelephone();
        if (phone == null) {
            new UserApiImpl().userInfo(getUUId(), "", new NetReqCallback<UserInfo>() {
                @Override
                public void apiRequestFail(String message, String method) {

                }

                @Override
                public void apiRequestSuccess(UserInfo userInfo, String method) {
                    phone = userInfo.getTelephone();
                    if (phone.length() >= 11)
                        tv_phone.setText(phone.substring(0, 3) + "****" + phone.substring(7, 11));


                }
            });
        } else {
            if (phone.length() >= 11)
                tv_phone.setText(phone.substring(0, 3) + "****" + phone.substring(7, 11));

        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBindPresenter.changeTelephoneVerifyOldTelephone();
            }
        });
        btn_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBindPresenter.postGetcode();
            }
        });
    }

    @Override
    public String getPhone() {
        return getAccount().getTelephone();
    }

    @Override
    public String getCode() {
        return editTextDel_code.getText().toString();
    }

    @Override
    public TextView getTv_getcode() {
        return btn_getcode;
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
    protected void onDestroy() {
        super.onDestroy();
        changeBindPresenter.release_res();
    }
}
