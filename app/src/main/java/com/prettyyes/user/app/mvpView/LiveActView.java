package com.prettyyes.user.app.mvpView;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.TopShowViewPorxy;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.model.common.ActInfo;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/2/27.
 */

public interface LiveActView extends BaseView {
    ActInfo getAct();

    TextView getLefttimeShow();

    ArrayList<SellerInfoEntity> getSellers();

    AutoViewPager getSellerVp();

    SwipeRecycleView getLv();

    AbsMutiRvAdapter getAdapter();

    //    View getBottom();
    View getSellerView();

    FrameLayout getEnterViewGroup();

    TextView getReplyMe();

    View getNewMessageView();

    void showBottomEnd();

    void showBottomStart();

    long getEndTime();

    void setCurrentCount(String tv);

    String getAskQue();

    void setAskSelectImgs(List<String> imgs);

    TopShowViewPorxy getTopShowView();

    void setAskQue(String text);


}
