package com.prettyyes.user.app.mvpView;

import android.widget.TextView;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/10/24
 * Description: Nothing
 */
public interface BindPhoneView extends BaseView{

    String getPhone();

    String getPwd();

    String getCode();

    TextView getTv_getcode();

    void setVeryCodeString(String tv);


}
