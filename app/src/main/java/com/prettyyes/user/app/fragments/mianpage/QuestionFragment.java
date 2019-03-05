package com.prettyyes.user.app.fragments.mianpage;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.OpenSpecialRequest;
import com.prettyyes.user.api.netXutils.requests.SearchStatusReq;
import com.prettyyes.user.api.netXutils.response.OpenSpecialRes;
import com.prettyyes.user.api.netXutils.response.SearchStatusRes;
import com.prettyyes.user.app.adapter.MyFragmentPagerAdapter;
import com.prettyyes.user.app.adapter.mainpage.HomeBannerVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.mvpView.OtherQueView;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.appview.HomeActivityEnterView;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.NoScrollViewPager;
import com.prettyyes.user.app.view.app.TriangleView;
import com.prettyyes.user.app.view.pupopwindow.HomeCategoryPupopWindow;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ChangeKolCategoryEvent;
import com.prettyyes.user.core.event.HowNewTask;
import com.prettyyes.user.core.event.LoginChangeEvent;
import com.prettyyes.user.core.event.RefreshHomeEvent;
import com.prettyyes.user.core.event.RewardSelectEvent;
import com.prettyyes.user.core.event.UnreadEvent;
import com.prettyyes.user.core.utils.AnimotionUtils;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.OpenSpecialEntity;
import com.prettyyes.user.ronyun.activity.RongyunUtils;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import io.rong.imkit.RongIM;

import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.CATEGORY_ENABLE;
import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_CATEGORY;
import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_HOME;
import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_MATCH;
import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_REWARD;

public class QuestionFragment extends BaseFragment implements OtherQueView {


    @ViewInject(R.id.img_otherqueFmt_chat)
    private ImageView img_chat;
    @ViewInject(R.id.tv_otherqueFmt_title)
    private TextView tv_title;
    @ViewInject(R.id.img_refresh)
    private ImageView img_refresh;
    @ViewInject(R.id.ll_tablayout)
    RelativeLayout ll_tablayout;

    @ViewInject(R.id.tablayout_home)
    TabLayout tablayout_home;

    @ViewInject(R.id.triangleView)
    TriangleView triangleView;

    @ViewInject(R.id.vp_home)
    NoScrollViewPager vp_home;


    @ViewInject(R.id.view_search)
    View view_search;


    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<Fragment> fragments;
    private MyFragmentPagerAdapter adapter;


    @Override
    public void onResume() {
        super.onResume();
        AppStatistics.onPageStart(getThis(), "wenda"); //统计页面，"MainScreen"为页面名称，可自定义

    }

    @Override
    public void onPause() {
        super.onPause();
        AppStatistics.onPageEnd(getThis(), "wenda"); //统计页面，"MainScreen"为页面名称，可自定义

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_other_question;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    BadgeView mMyDotView;

    @Override
    public void initView(View view) {
        mMyDotView = new BadgeView(getActivity());
        mMyDotView.setTargetView(img_chat);//就是这里
        mMyDotView.setTextSize(6);
        mMyDotView.setBadgeCount(0);
        vp_home.setScroll(true);
    }


    @Override
    public void setListener() {
        super.setListener();
        view_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UpdateService.installApk(null,getActivity());
                JumpPageManager.getManager().goSearchActivity(getActivity());

            }
        });
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        img_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUUId() == null) {
                    RegisterActivity.getRegister(getActivity());
                    return;
                }
                if (RongIM.getInstance() != null)
                    RongIM.getInstance().startConversationList(getActivity());
            }
        });
        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppRxBus.getInstance().post(new RefreshHomeEvent(PAGE_HOME));
                img_refresh.setVisibility(View.GONE);
            }
        });


        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<UnreadEvent>() {
            @Override
            protected void back(UnreadEvent obj) {
                mMyDotView.setBadgeCount(((UnreadEvent) obj).getCount());
            }
        }, new RxCallback<HowNewTask>() {
            @Override
            protected void back(HowNewTask obj) {
                if (((HowNewTask) obj).isShowRefresh())
                    img_refresh.setVisibility(View.VISIBLE);
                else
                    img_refresh.setVisibility(View.GONE);
            }
        }, new RxCallback<ChangeKolCategoryEvent>() {
            @Override
            protected void back(ChangeKolCategoryEvent o) {
                view4.setText(((ChangeKolCategoryEvent) o).getQueCategoryEntity().getCategory());
            }
        }, new RxCallback<LoginChangeEvent>() {
            @Override
            protected void back(LoginChangeEvent o) {
                controlSearch();
                vp_home.setCurrentItem(0);
                String token = "";
                if (getAccount() != null && getAccount().rongyun_token != null) {
                    token = getAccount().rongyun_token.getRongyun_buyer();
                }
                RongyunUtils.connect(token, getActivity(), new RongyunUtils.CallBackUid() {
                    @Override
                    public void back(String userid) {

                    }
                });
            }
        }, new RxCallback<RewardSelectEvent>() {
            @Override
            protected void back(RewardSelectEvent obj) {
                vp_home.setCurrentItem(2);
            }
        });

        tablayout_home.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 3) {
                    triangleView.setColor(ContextCompat.getColor(getContext(), R.color.theme_red));
                    if (view4 != null)
                        view4.setTextColor(ContextCompat.getColor(getContext(), R.color.textBlack));
                } else {
                    triangleView.setColor(ContextCompat.getColor(getContext(), R.color.hintBlack));
                    if (view4 != null)
                        view4.setTextColor(ContextCompat.getColor(getContext(), R.color.secondaryTextBlack));

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vp_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                last_position = position;

                switch (position) {
                    case 0:
                        AppStatistics.onEvent(getContext(), "wenda_selected");
                        break;
                    case 1:
                        AppStatistics.onEvent(getContext(), "wenda_new");
                        break;
                    case 2:
                        AppStatistics.onEvent(getContext(), "wenda_reward");
                        break;
                    case 3:
                        AppStatistics.onEvent(getContext(), "wenda_choose");
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    TextView view4;


    public void controlSearch() {
        new SearchStatusReq().post(new NetReqCallback<SearchStatusRes>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(SearchStatusRes searchStatusRes, String method) {
                if (searchStatusRes.isShow()) {
                    tv_title.setVisibility(View.GONE);
                    if (view_search.getVisibility() == View.GONE)
                        AnimotionUtils.animateAlphaIn(view_search, 1000);
                } else {
//                    view_search.setVisibility(View.GONE);
//                    tv_title.setVisibility(View.VISIBLE);


                    tv_title.setVisibility(View.GONE);
                    if (view_search.getVisibility() == View.GONE)
                        AnimotionUtils.animateAlphaIn(view_search, 1000);


                }


            }
        });
    }


    @Override
    public void doBusiness(Context mContext) {
        controlSearch();
        titles.add("编辑精选");
        titles.add("最新回答");
        titles.add("悬赏令");
        titles.add("筛选");
        fragments = new ArrayList<>();
        fragments.add(QuestionVpFragment.newInstance(PAGE_HOME, "", ""));
        fragments.add(QuestionVpFragment.newInstance(PAGE_MATCH, "", ""));
        fragments.add(QuestionVpFragment.newInstance(PAGE_REWARD, "", ""));
        fragments.add(QuestionVpFragment.newInstance(PAGE_CATEGORY, "1", ""));

        vp_home.setOffscreenPageLimit(fragments.size());
        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments, titles) {
        };
        vp_home.setAdapter(adapter);
        tablayout_home.setTabMode(TabLayout.GRAVITY_CENTER);
        tablayout_home.setupWithViewPager(vp_home);
        setTabFour("筛选", false);

        new OpenSpecialRequest().post(new NetReqCallback<OpenSpecialRes>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(OpenSpecialRes openSpecialRes, String method) {
                TabLayout.Tab tabAt = tablayout_home.getTabAt(2);
                OpenSpecialEntity list = openSpecialRes.getList();

                String reward_name = list.getReward_name();
                if (reward_name != null && reward_name.length() > 0)
                    tabAt.setText(reward_name);

            }
        });
    }

    private void setTabFour(String tv, boolean select) {
        TabLayout.Tab tabAt = tablayout_home.getTabAt(3);
        view4 = new TextView(getContext());
        view4.setText(tv);
        view4.setGravity(Gravity.CENTER);
        if (select) {
            view4.setTextColor(ContextCompat.getColor(getContext(), R.color.textBlack));

        } else {
            view4.setTextColor(ContextCompat.getColor(getContext(), R.color.hintBlack));
        }
        tabAt.setCustomView(view4);

        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CATEGORY_ENABLE)
                    new HomeCategoryPupopWindow(getThis()).showAsdropdown(tablayout_home);
                if (last_position == 3) {

                } else {
                    vp_home.setCurrentItem(3);
                }
            }
        });

    }

    private int last_position = 0;


    @Override
    public void lazyInitBusiness(Context mContext) {


    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return (BaseActivity) getActivity();
    }


    @Override
    public AutoViewPager getBannerVp() {
        return null;
    }

    @Override
    public HomeBannerVpAdapter getVpAdapter() {
        return null;
    }

    @Override
    public HomeActivityEnterView getHomeEnter() {
        return null;
    }

    @Override
    public BadgeView getBadgeView() {
        return mMyDotView;
    }

    @Override
    public View getBannerView() {
        return null;
    }

    @Override
    public void notifyRv() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public Fragment getCurrentFragment() {
        return adapter.getmCurrentFragment();
    }

}
