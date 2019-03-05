package com.prettyyes.user.app.mvpView;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/12/14
 * Description: Nothing
 */
public interface SpecialPerfomView extends BaseView {
     AutoViewPager getVp();
     int getHowActivityId();
     void setActivtyInfo(String coveryimg,String title,String desc,String activitytime);
     RecyclerView getRecycle();
     SwipeRecycleView getSwipeRecycle();
     void setViewVisiableByType(String type);
     void setNewMessageVisiable(boolean visiable);
     ImageView getFAb();

}
