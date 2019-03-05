package com.prettyyes.user.app.fragments.mianpage;

import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.KolListRequest;
import com.prettyyes.user.app.adapter.mainpage.KolListRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.fragments.SingleListFragment;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.event.HomeTabSelectAgainEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.other.AceGetList;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

public class KolListFragment extends SingleListFragment<AceGetList> {

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @Override
    public void onResume() {
        super.onResume();
        AppStatistics.onPageStart(getContext(), "kol"); //统计页面，"MainScreen"为页面名称，可自定义

    }

    @Override
    public void onPause() {
        super.onPause();
        AppStatistics.onPageEnd(getContext(), "kol"); //统计页面，"MainScreen"为页面名称，可自定义

    }

    @Override
    public void setListener() {
        super.setListener();
//        mSubscription = AppRxBus.getInstance().toObservable(HomeTabSelectAgainEvent.class).subscribe(new Action1<HomeTabSelectAgainEvent>() {
//            @Override
//            public void call(HomeTabSelectAgainEvent o) {
//
//                if (o.index == 1 && isVisible) {
//                    swipeRv.getRecycleView().scrollToPosition(0);
//                    swipeRv.loadingFistEnter();
//                }
//
//
//            }
//        });

//        tv_title.setOnClickListener(new View.OnClickListener() {
//            long[] mHints = new long[5];//初始全部为0
//
//            @Override
//            public void onClick(View v) {
//                //将mHints数组内的所有元素左移一个位置
////                System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
////                //获得当前系统已经启动的时间
////                mHints[mHints.length - 1] = System.currentTimeMillis();
////                if (System.currentTimeMillis() - mHints[0] <= 500) {
////                    AppConfig.isDebug = !AppConfig.isDebug;
////                    Toast.makeText(getActivity(), "is debug" + AppConfig.isDebug, Toast.LENGTH_SHORT).show();
////                }
//
//                AppConfig.isDebug = !AppConfig.isDebug;
//                Toast.makeText(getActivity(), "is debug" + AppConfig.isDebug, Toast.LENGTH_SHORT).show();
//
//            }
//        });
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<HomeTabSelectAgainEvent>() {
            @Override
            protected void back(HomeTabSelectAgainEvent obj) {
                if (obj.index == 1 && isVisible) {
                    swipeRv.getRecycleView().scrollToPosition(0);
                    swipeRv.loadingFistEnter();
                }

            }
        });

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_kol_list;
    }


    @Override
    public AbsRecycleAdapter createAdapter() {
        return new KolListRecycleAdapter(getContext());
    }


    @Override
    public void requestPageData(int page) {
        new KolListRequest().setNickname("").setPage(page).post(this);
    }

    @Override
    public List getListData(AceGetList o) {
        return o.getList();
    }
}
