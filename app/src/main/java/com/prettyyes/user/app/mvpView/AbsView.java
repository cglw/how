package com.prettyyes.user.app.mvpView;

import android.widget.Toast;

import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public abstract class AbsView {
    void showFailedError(String tv) {
        Toast.makeText(BaseApplication.getAppContext(), tv, Toast.LENGTH_SHORT);
    }

    String getUUid() {
        return BaseApplication.UUID;
    }

    public abstract BaseActivity getActivity();
}
