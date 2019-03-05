package com.prettyyes.user.app.ui.kol;

import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.ApplyKolRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.view.EditInScrollText;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.StatusBarUtil;
import com.prettyyes.user.core.utils.StringUtils;

import org.xutils.view.annotation.ViewInject;

public class ApplyToKolDetialActivity extends BaseActivity {
    private static final String TAG = "ApplyToKolActivity";
    private EditInScrollText mEdit_nickname;
    private EditInScrollText mEdit_act;
    private EditInScrollText mEdit_phone;
    private EditInScrollText mEdit_email;
    private EditInScrollText mEdit_share_story;
    private EditInScrollText mEdit_your_question;
    private EditInScrollText mEdit_why;
    private EditInScrollText mEdit_hope;
    private Button mBtn_post;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    private void bindViews() {
        mEdit_nickname = (EditInScrollText) findViewById(R.id.edit_nickname);
        mEdit_act = (EditInScrollText) findViewById(R.id.edit_act);
        mEdit_phone = (EditInScrollText) findViewById(R.id.edit_phone);
        mEdit_email = (EditInScrollText) findViewById(R.id.edit_email);
        mEdit_share_story = (EditInScrollText) findViewById(R.id.edit_share_story);
        mEdit_your_question = (EditInScrollText) findViewById(R.id.edit_your_question);
        mEdit_why = (EditInScrollText) findViewById(R.id.edit_why);
        mEdit_hope = (EditInScrollText) findViewById(R.id.edit_hope);
        mBtn_post = (Button) findViewById(R.id.btn_post);
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, AppConfig.STARTBAR_ALPHA, null);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_apply_to_kol;
    }

    @ViewInject(R.id.ll_bg)
    LinearLayout ll_bg;

    @ViewInject(R.id.img_bg)
    ImageView img_bg;

    @Override
    protected void initViews() {
        bindViews();
        hideHeader();
        ViewGroup.LayoutParams layoutParams = ll_bg.getLayoutParams();
        ViewGroup.LayoutParams img_bgLayoutParams = img_bg.getLayoutParams();
        layoutParams.width = BaseApplication.ScreenWidth;
        img_bgLayoutParams.width = layoutParams.width;
        layoutParams.height = layoutParams.width * 154 / 75;
        img_bgLayoutParams.height = layoutParams.height;


    }

    @Override
    protected void setListener() {
        super.setListener();
        mBtn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (JumpPageManager.getManager().checkUnlogin(getThis())) {
                    return;
                }
                if (checkInput())
                    return;

                new ApplyKolRequest()
                        .setNickname(getNickName())
                        .setIntroduce(getIntroduce())
                        .setTelephone(getPhone())
                        .setExperience(getExperience())
                        .setEmail(getEmail())
                        .setConfused(getConfused())
                        .setWhy(getWhy())
                        .setHope(getHope()).post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        showSnack(message);

                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        DialogHelper.getInstance().getDialogNoCancel(R.string.apply_kol_post_success, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                back();
                            }
                        });

                    }
                });
            }
        });
    }

    public String getNickName() {
        return mEdit_nickname.getText().toString().trim();
    }

    public String getIntroduce() {
        return mEdit_act.getText().toString().trim();
    }

    public String getExperience() {
        return mEdit_share_story.getText().toString().trim();
    }

    public String getEmail() {
        return mEdit_email.getText().toString().trim();
    }

    public String getPhone() {
        return mEdit_phone.getText().toString().trim();
    }

    public String getConfused() {
        return mEdit_your_question.getText().toString().trim();
    }

    public String getWhy() {
        return mEdit_why.getText().toString().trim();
    }

    public String getHope() {
        return mEdit_hope.getText().toString().trim();
    }

    public boolean checkInput() {
        if (StringUtils.strIsEmpty(getNickName())) {
            showToastShort("请输入昵称");
            return true;
        }
        if (StringUtils.strIsEmpty(getIntroduce())) {
            showToastShort("请输入一句话介绍");
            return true;
        }
        if (StringUtils.strIsEmpty(getPhone())) {
            showToastShort("请输入手机号");
            return true;
        }
        if (StringUtils.strIsEmpty(getEmail())) {
            showToastShort("请输入邮箱");
            return true;
        }
        if (StringUtils.strIsEmpty(getExperience())) {
            showToastShort("请输入简介");
            return true;
        }

        return false;
    }

    @Override
    protected void loadData() {

    }
}
