package com.prettyyes.user.app.ui.setting;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.app.SettingItemView;
import com.prettyyes.user.core.UpdateHandler;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.DataCleanManager;
import com.prettyyes.user.model.user.UserInfo;

import org.xutils.view.annotation.ViewInject;

public class SettingActivity extends BaseActivity {

    @ViewInject(R.id.settingItem_settingAct_version)
    private SettingItemView settingItemView_version;
    @ViewInject(R.id.settingItem_settingAct_clearcache)
    private SettingItemView settingItemView_clearcache;
    @ViewInject(R.id.lin_settingAct_loginout)
    private LinearLayout lin_loginout;
    @ViewInject(R.id.settingItem_settingAct_changebindphone)
    private SettingItemView settingItemView_bindphone;
    @ViewInject(R.id.settingItem_settingAct_passwordset)
    private SettingItemView settingItemView_pwdset;
    private BadgeView badgeView;
    private UpdateHandler updateHandler;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {
        showBack();
        setActivtytitle(R.string.title_activity_setting);
        settingItemView_version.hideArrow();
        settingItemView_clearcache.hideArrow();


    }

    @Override
    protected void loadData() {
        initVersion();
        initCacheSize();
    }

    @Override
    protected void setListener() {
        settingItemView_bindphone.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserApiImpl().userInfo(getUUId(), "", new NetReqCallback<UserInfo>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        AppUtil.showToastShort(message);

                    }

                    @Override
                    public void apiRequestSuccess(UserInfo userInfo, String method) {
                        String phone = userInfo.getTelephone();
                        if (phone.equals("00000000000")) {
                            Intent intent = new Intent(SettingActivity.this, BindPhoneActivity.class);
                            intent.putExtra("type", BindPhoneActivity.TYPE_CHANGE);
                            nextActivity(intent);
                        } else {
                            nextActivity(ChangeBindPhoneActivity.class);
                        }


                    }
                });

            }
        });
        settingItemView_pwdset.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(ResetPwdActivity.class);
            }
        });
        settingItemView_version.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVersion();
            }
        });
        settingItemView_clearcache.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearcache();
            }
        });
        lin_loginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginout();
            }
        });
    }

    private void initVersion() {
        settingItemView_version.setRightText(UpdateHandler.getVersion(getBaseContext()));
        if (UpdateHandler.checkNeedUpdateByLocal()) {
            badgeView = new BadgeView(this);
            badgeView.setText("new");
            badgeView.setTextColor(Color.WHITE);
            badgeView.setTargetView(settingItemView_version);
        }
    }

    private void initCacheSize() {
        try {
            String size = DataCleanManager.getCacheSize(getApplicationContext().getCacheDir());
            settingItemView_clearcache.setRightText(size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void checkVersion() {
        updateHandler = new UpdateHandler(this, true);
        updateHandler.checkVersion();
    }

    private void clearcache() {
        DataCleanManager.clearAllCache(getBaseContext());
        initCacheSize();
    }

    private void loginout() {
        alertView = new AlertView("提醒", "确认退出登录？", "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, final int position) {
                if (position == 0) {
                    back();
                    AccountDataRepo.getAccountManager().logout();
                    Intent intent = new Intent();
                    intent.setAction(Constant.LOGIN_REFRESH);
                    sendBroadcast(intent);
                    clearcache();

                }
            }
        }).setCancelable(true);
        alertView.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (updateHandler != null)
            updateHandler.cancle();
    }
}
