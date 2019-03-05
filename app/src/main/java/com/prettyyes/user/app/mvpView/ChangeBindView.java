package com.prettyyes.user.app.mvpView;

import android.widget.TextView;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/11/10
 * Description: Nothing
 */
public interface ChangeBindView extends BaseView{
    String getPhone();


    String getCode();

    TextView getTv_getcode();
}
