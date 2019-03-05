package com.prettyyes.user.app.mvpView;

import android.view.View;
import android.widget.RadioGroup;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public interface MainActivityView extends BaseView {

    View getTipsView();

    View getTipsMoreView();

    RadioGroup getRadioGroup();
}
