package com.prettyyes.user.app.mvpView;

import android.view.View;

import com.prettyyes.user.app.adapter.mainpage.HomeBannerVpAdapter;
import com.prettyyes.user.app.ui.appview.HomeActivityEnterView;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.BadgeView;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/12/14
 * Description: Nothing
 */
public interface OtherQueView extends BaseView {
    public AutoViewPager getBannerVp();

    public HomeBannerVpAdapter getVpAdapter();

    HomeActivityEnterView getHomeEnter();


    BadgeView getBadgeView();

    View getBannerView();

    void notifyRv();


}
