package com.prettyyes.user.app.ui.person;

import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.BindAlipayRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ChangeAccountSuccess;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.v8.AliAccountEntity;
import com.ta.utdid2.android.utils.StringUtils;

public class AliAccountActivity extends BaseActivity {

    private EditTextDel etv_alipay_account;
    private EditTextDel etv_alipay_phone;
    private EditTextDel etv_alipay_name;

    private void bindViews() {
        etv_alipay_account = (EditTextDel) findViewById(R.id.etv_alipay_account);
        etv_alipay_phone = (EditTextDel) findViewById(R.id.etv_alipay_phone);
        etv_alipay_name = (EditTextDel) findViewById(R.id.etv_alipay_name);
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_ali_account;
    }

    @Override
    protected void initViews() {
        bindViews();
        setActivtytitle(R.string.title_activity_aliaccount);
        IntentParams intentParams = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParams != null) {
            AliAccountEntity aliAccountModel = intentParams.getAlipayEntity();
            setData(aliAccountModel);
        }
    }

    @Override
    protected void setListener() {
        super.setListener();

        setRightTvListener(R.string.submit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etv_alipay_account.getText().toString())) {
                    showSnack("账户不允许为空");
                    return;
                }
                if (StringUtils.isEmpty(etv_alipay_name.getText().toString())) {
                    showSnack("用户名不允许为空");
                    return;
                }
                if (StringUtils.isEmpty(etv_alipay_phone.getText().toString())) {
                    showSnack("绑定手机号不允许为空");
                    return;
                }

                showProgressDialog(R.string.upload_ing);
                BindAlipayRequest api = new BindAlipayRequest();
                api.setAlipay_account(etv_alipay_account.getText().toString());
                api.setAlipay_telephone(etv_alipay_phone.getText().toString());
                api.setAlipay_username(etv_alipay_name.getText().toString());
                api.post(new NetReqCallback() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        dismissProgressDialog();
                        showToastShort(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        dismissProgressDialog();
                        finish();
                        showToastShort("操作成功");
                        AppRxBus.getInstance().post(new ChangeAccountSuccess());

                    }
                });

            }
        });
    }

    @Override
    protected void loadData() {

    }

    public void setData(AliAccountEntity data) {
        if (data == null)
            return;
        getRight_tv().setText("修改");
        etv_alipay_account.setText(data.getAlipay_account());
        etv_alipay_name.setText(data.getAlipay_username());
        etv_alipay_phone.setText(data.getAlipay_telephone());

    }
}
