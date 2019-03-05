package com.prettyyes.user.app.mvpView;

import android.support.design.widget.CoordinatorLayout;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/30
 * Description: Nothing
 */
public interface PersonInfoView extends BaseView {
    void setHeadImg(String url);

    void setName(String name);

    void setSex(String sex);

    void setAce(String ace);

    void setInfo(String info);

    String getName();

    String getSex();

    String getHeadimg();

    String getAce();

    String getInfo();

    CoordinatorLayout getCoordinatorLayout();

    void setTag(ArrayList<String> tags);
}
