package com.prettyyes.user.app.mvpPresenter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.mvpView.ChangeBindView;
import com.prettyyes.user.app.ui.setting.BindPhoneActivity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/10/24
 * Description: Nothing
 */
public class ChangeBindPresenter {
    private ChangeBindView changeBindView;
    private OtherApiImpl otherApi;
    private UserApiImpl userApi;

    public ChangeBindPresenter(ChangeBindView changeBindView) {
        this.changeBindView = changeBindView;
        otherApi = new OtherApiImpl();
        userApi = new UserApiImpl();
    }

    private int times = 30;//倒计时
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                if (times >= 0) {
                    changeBindView.getTv_getcode().setText(times + "s");
                    times--;
                    sendEmptyMessageDelayed(1, 1000);
                } else {
                    changeBindView.getTv_getcode().setText(changeBindView.getThis().getString(R.string.verify_getcode));
                    changeBindView.getTv_getcode().setEnabled(true);
                    times = 30;
                }
            }
        }
    };

    public void release_res() {
        if (handler != null) {
            handler.removeMessages(1);
        }
    }

    private void getCode() {
        userApi.userTelephoneVerifyToEveryBody(changeBindView.getPhone(), "common", new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {
                changeBindView.showFailedError(message);
                changeBindView.getTv_getcode().setEnabled(true);
                changeBindView.getTv_getcode().setText(changeBindView.getThis().getString(R.string.verify_getcode));

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                changeBindView.getTv_getcode().setEnabled(false);
                handler.sendEmptyMessage(1);
            }
        });
    }

    public void postGetcode() {
        changeBindView.getTv_getcode().setEnabled(false);
        changeBindView.getTv_getcode().setText("获取中...");
        getCode();
    }

    public void changeTelephoneVerifyOldTelephone() {
        userApi.usechangeTelephoneVerifyOldTelephone(changeBindView.getThis().getUUId(),
                changeBindView.getPhone(), changeBindView.getCode(), new NetReqCallback() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        changeBindView.showFailedError(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        Intent intent = new Intent(changeBindView.getThis(), BindPhoneActivity.class);
                        intent.putExtra("type", BindPhoneActivity.TYPE_CHANGE);
                        changeBindView.getThis().nextActivity(intent);
                    }
                }
        );
    }


}
