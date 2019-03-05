package com.prettyyes.user.app.mvpView;

import com.prettyyes.user.app.base.BaseActivity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.api
 * Author: SmileChen
 * Created on: 2016/10/14
 * Description: Nothing
 */
public interface BaseView {
    void showFailedError(String tv);

    BaseActivity getThis();
}
