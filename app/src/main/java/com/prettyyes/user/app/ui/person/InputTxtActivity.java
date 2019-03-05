package com.prettyyes.user.app.ui.person;

import android.text.InputFilter;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.UserEditRequest;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.AlertMessageResponse;
import com.prettyyes.user.model.user.UserInfo;

import org.xutils.view.annotation.ViewInject;

public class InputTxtActivity extends BaseActivity {
    private String title;
    private String hint;
    private String getcode;
    private String content;

    @ViewInject(R.id.edit_InputTxt_edit)
    private EditTextDel editTextDel;

    public static String CodeName = "0";
    public static String CodeAce = "1";
    public static String CodeInfo = "2";

    @Override
    protected int bindLayout() {
        return R.layout.activity_input_txt;
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas == null) {
            return;
        }
        getcode = intentParmas.getGetcode();
        content = intentParmas.getContent();
        if (content == null)
            content = "";
        if (CodeName.equals(getcode)) {
            title = "修改姓名";
            hint = "姓名";


        } else if (CodeAce.equals(getcode)) {
            title = "修改介绍";
            hint = "一句话介绍";

        } else if (CodeInfo.equals(getcode)) {
            title = "修改详情";
            hint = "个人详情介绍";

        }

    }

    @Override
    protected void initViews() {
        setActivtytitle(title);
        setRightTvListener("完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CodeName.equals(getcode)) {
                    if (editTextDel.getText().toString().trim().length() < 1) {
                        showToastShort("昵称不允许为空");
                        return;
                    }
                    UserInfo userInfo = getAccount();
                    userInfo.setNickname(editTextDel.getText().toString());
                    uploadPersonInfo(userInfo);
                } else if (CodeAce.equals(getcode)) {
                    if (editTextDel.getText().toString().trim().length() < 1) {
                        showToastShort("一句话介绍不允许为空");
                        return;
                    }
                    UserInfo userInfo = getAccount();
                    userInfo.setAce_txt(editTextDel.getText().toString());
                    uploadPersonInfo(userInfo);
                } else if (CodeInfo.equals(getcode)) {
                    if (editTextDel.getText().toString().trim().length() < 1) {
                        showToastShort("个人详情不允许为空");
                        return;
                    }
                    UserInfo userInfo = getAccount();
                    userInfo.setInformation(editTextDel.getText().toString());
                    uploadPersonInfo(userInfo);
                }

            }
        });
        int max = 12;
        if (CodeName.equals(getcode))
            max = 12;
        else if (CodeAce.equals(getcode)) {
            max = 30;
        } else if (CodeInfo.equals(getcode)) {
            max = 10000;
        }
        editTextDel.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});


        if (content.length() > max) {
            content = content.substring(0, max);
        }
        editTextDel.setText(content);
        editTextDel.setSelection(content.length());

    }


    @Override
    protected void loadData() {
    }

    private void uploadPersonInfo(final UserInfo account) {
//        String tags = "";
//        for (int i = 0; i < account.getTags_info().size(); i++) {
//            tags += account.getTags_info().get(i).getTag_id() + ";";
//        }

        new UserEditRequest().setNickname(account.getNickname())
                .setAce_txt(account.getAce_txt())
                .setInformation(account.getInformation()).post(new NetReqCallback<AlertMessageResponse>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(AlertMessageResponse o, String method) {
                if (o.isCompleteNewBie())
                    AppRxBus.getInstance().post(new TaskCompleteEvent());
                UserInfo userInfo = new UserInfo();
                userInfo.setNickname(account.getNickname());
                userInfo.setAce_txt(account.getAce_txt());
                userInfo.setInformation(account.getInformation());
                AppRxBus.getInstance().post(userInfo);
                AccountDataRepo.getAccountManager().remove();
                AccountDataRepo.getAccountManager().save(account);
                finish();
            }
        });

    }
}
