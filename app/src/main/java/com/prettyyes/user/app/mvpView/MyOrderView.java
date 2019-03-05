package com.prettyyes.user.app.mvpView;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/11/2
 * Description: Nothing
 */
public interface MyOrderView extends BaseView {
    public void setVpData(ArrayList<Fragment> fragments);
}
