package com.prettyyes.user.app.fragments.mianpage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.hornen.storage.StorageProxy;
import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.AbListByCategoryRequest;
import com.prettyyes.user.api.netXutils.requests.HomeTaskRequest;
import com.prettyyes.user.api.netXutils.requests.TaskMatchListRequest;
import com.prettyyes.user.api.netXutils.response.HomeListRes;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.adapter.mainpage.HomeBannerVpAdapter;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.fragments.SingleListFragment;
import com.prettyyes.user.app.mvpPresenter.OtherQuePresenter;
import com.prettyyes.user.app.mvpView.OtherQueView;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.appview.ApplyKolView;
import com.prettyyes.user.app.ui.appview.HomeActivityEnterView;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.TopShowViewPorxy;
import com.prettyyes.user.app.view.component.GuideJumpComponent;
import com.prettyyes.user.app.view.component.GuideThreeBottomComponent;
import com.prettyyes.user.app.view.component.GuideThreeTopComponent;
import com.prettyyes.user.app.view.component.GuideTwoComponent;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.app.view.lvandgrid.EmptyListener;
import com.prettyyes.user.core.AppCommonHandler;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.event.AppTimeEvent;
import com.prettyyes.user.core.event.ChangeKolCategoryEvent;
import com.prettyyes.user.core.event.GuideOneDismissEvent;
import com.prettyyes.user.core.event.HomePgaeRefreshedEvent;
import com.prettyyes.user.core.event.HomeTabSelectAgainEvent;
import com.prettyyes.user.core.event.LoginChangeEvent;
import com.prettyyes.user.core.event.RefreshHomeEvent;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.HomeTaskEntity;
import com.prettyyes.user.model.v8.TaskTimeEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by chengang on 2017/7/10.
 */

public class QuestionVpFragment extends SingleListFragment<HomeListRes> implements OtherQueView, EmptyListener {

    public String type;
    private String category_id;
    public static final String PAGE_HOME = "home_task";
    public static final String PAGE_MATCH = "new_task";
    public static final String PAGE_CATEGORY = "reply";
    public static final String PAGE_REWARD = "reward_task";

    @ViewInject(R.id.autoVp)
    private AutoViewPager autoViewPager;
    @ViewInject(R.id.applykol_view)
    private ApplyKolView applykol_view;
    @ViewInject(R.id.homeEnterView)
    private HomeActivityEnterView homeActivityEnterView;
    @ViewInject(R.id.coordinatorLayout)
    private CoordinatorLayout coordinatorLayout;
    public OtherQuePresenter otherQuePresenter;


    public static boolean CATEGORY_ENABLE=false;

    public static QuestionVpFragment newInstance(String type, String category_id, String status) {
        QuestionVpFragment simple = new QuestionVpFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putString("category_id", category_id);
        args.putString("status", status);
        simple.setArguments(args);
        return simple;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home_list;
    }

    @Override
    public void initParms(Bundle parms) {
        super.initParms(parms);
        type = parms.getString("type");
        category_id = parms.getString("category_id");
        if (type.equals(PAGE_HOME))
            otherQuePresenter = new OtherQuePresenter(this);

    }

    @Override
    public void initView(View view) {
        super.initView(view);
        CATEGORY_ENABLE=false;
        swipeRv.getRecycleView().addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST)
                .setmDividerHeight(AppUtil.dip2px(16))
                .setUse_margin(true)
                .setNeed_top_margin(true));
        showApply();
        if (type.equals(PAGE_HOME))
            autoViewPager.setVisibility(View.INVISIBLE);
        else {
            autoViewPager.setVisibility(View.GONE);
        }


        autoViewPager.setIscanClickOther(false);
        autoViewPager.setLayoutParams(new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (BaseApplication.ScreenWidth * 0.3)));

    }

    public void showApply() {

        if (type.equals(PAGE_MATCH)) {
            applykol_view.best_new();
            applykol_view.setVisibility(View.VISIBLE);
            checkTaskMatch();
        } else if (type.equals(PAGE_CATEGORY)) {
            applykol_view.category();
            applykol_view.setVisibility(View.VISIBLE);
            checkUserNewTask();
        } else if (type.equals(PAGE_REWARD)) {
            applykol_view.reward();
            applykol_view.setVisibility(View.VISIBLE);
            checkUserNewTask();

        }


    }

    @Override
    public void requestPageData(int page) {
        switch (type) {
            case PAGE_HOME:
                new HomeTaskRequest().setPage(page).setMethod(PAGE_HOME).post(this);
                break;
            case PAGE_MATCH:

                swipeRv.setEmpty_listener(this);
                String status = "2";
                if (!MainActivity.class.getCanonicalName().equals(getActivity().getClass().getCanonicalName()))
                    status = "5";
                new TaskMatchListRequest().setStatus(status).setPage(page).setMethod(PAGE_MATCH).post(this);
                break;
            case PAGE_REWARD:

                new TaskMatchListRequest().setPage(page).setStatus("7").setMethod(PAGE_MATCH).post(this);
                break;
            case PAGE_CATEGORY:
                new AbListByCategoryRequest().setPage(page).setCategory_id(category_id).setMethod(PAGE_CATEGORY).post(this);
                break;
        }
    }

    private void checkUserNewTask() {
        mSubscription = AppCommonHandler.create().checkCompleteNewUserTask().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isCompletet) throws Exception {
                if (applykol_view == null)
                    return;

                if (isCompletet) {
                    CATEGORY_ENABLE=true;
                    applykol_view.setVisibility(View.GONE);
                }
                else {
                    CATEGORY_ENABLE=false;
                    applykol_view.setVisibility(View.VISIBLE);
                }

            }

        });
    }

    @Override
    public void setListener() {
        super.setListener();

        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<RefreshHomeEvent>() {
            @Override
            protected void back(RefreshHomeEvent o) {
                if (((RefreshHomeEvent) o).getMethod().equals(type))
                    swipeRv.loadingFistEnter();
            }
        }, new RxCallback<ChangeKolCategoryEvent>() {
            @Override
            protected void back(ChangeKolCategoryEvent o) {
                if (type.equals(PAGE_CATEGORY)) {
                    swipeRv.loadingSuccessHavedata();
                    category_id = o.getQueCategoryEntity().getCategory_id();
                    swipeRv.loadingFistEnter();
                }
            }
        }, new RxCallback<AppTimeEvent>() {
            @Override
            protected void back(AppTimeEvent o) {
                if (otherQuePresenter != null)
                    otherQuePresenter.checkKolActivity((AppTimeEvent) o);
            }
        }, new RxCallback<HomePgaeRefreshedEvent>() {
            @Override
            protected void back(HomePgaeRefreshedEvent o) {
                if (type.equals(PAGE_HOME)) {
                    otherQuePresenter.getBanner();
                    otherQuePresenter.getActivity();
                }
            }
        }, new RxCallback<HomeTabSelectAgainEvent>() {
            @Override
            protected void back(HomeTabSelectAgainEvent o) {
                if (o.index == 0 && isVisible) {
                    swipeRv.loadingFistEnter();
                }
            }
        }, new RxCallback<LoginChangeEvent>() {
            @Override
            protected void back(LoginChangeEvent o) {
                swipeRv.loadingFistEnter();
                showApply();
            }
        }, new RxCallback<GuideOneDismissEvent>() {
            @Override
            protected void back(GuideOneDismissEvent obj) {
                if (type.equals(PAGE_HOME)) {
                    handler.sendEmptyMessage(1);
                }
            }
        });

        if (type.equals(PAGE_CATEGORY) || type.equals(PAGE_REWARD)) {
            mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<TaskCompleteEvent>() {
                @Override
                protected void back(TaskCompleteEvent obj) {
                    checkUserNewTask();
                }
            });
        }

    }

    private int top;


    @Override
    public void apiRequestSuccess(final HomeListRes homeTask, String method) {
        if (page == 1) {
//            if (method.equals(PAGE_HOME))
//                RxBus.getInstance().post(new HomeVpCanScrollEvent(homeTask.getSeller_type() == null ? false : homeTask.getSeller_type().equals("1")));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppRxBus.getInstance().post(new HomePgaeRefreshedEvent());
                }
            }, 500);

            if (homeTask.getNotice() != null) {
                new TopShowViewPorxy(coordinatorLayout, "").showHome(homeTask.getNotice().getNew_task_text());
            }
        }
        if (homeTask != null && homeTask.getNotice() != null && type.equals(PAGE_HOME)) {
            new StorageProxy(BaseApplication.getAppContext()).save("last_time", homeTask.getNotice().getLast_time());
        }
        super.apiRequestSuccess(homeTask, method);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null)
            handler.removeMessages(1);
    }


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (swipeRv.getRecycleView().getChildCount() <= 0)
                    handler.sendEmptyMessageDelayed(1, 200);
                else if (AccountDataRepo.getAccountManager().isFirstUse())
                    notifyRv();
            }

            super.handleMessage(msg);
        }
    };


    @Override
    public List getListData(HomeListRes homeListRes) {

        if (PAGE_MATCH.equals(type)) {
            for (int i = 0; i < homeListRes.getList().size(); i++) {
                List<HomeTaskEntity> list = homeListRes.getList();
                list.get(i).setTask_describe_text("");
                list.get(i).setAnswer_info(null);
            }
        } else if (PAGE_REWARD.equals(type)) {
            for (int i = 0; i < homeListRes.getList().size(); i++) {
                homeListRes.getList().get(i).setType("reward");
            }
        }
        return homeListRes.getList();
    }

    @Override
    public void showFailedError(String tv) {

    }

    @Override
    public BaseActivity getThis() {
        return (BaseActivity) getActivity();
    }

    @Override
    public AutoViewPager getBannerVp() {
        return autoViewPager;
    }

    private HomeBannerVpAdapter advVpAdapter;

    @Override
    public HomeBannerVpAdapter getVpAdapter() {
        if (advVpAdapter == null)
            advVpAdapter = new HomeBannerVpAdapter(getThis());
        return advVpAdapter;
    }


    @Override
    public HomeActivityEnterView getHomeEnter() {
        return homeActivityEnterView;
    }

    @Override
    public BadgeView getBadgeView() {
        return null;
    }

    @Override
    public View getBannerView() {
        return autoViewPager;
    }


    @Override
    public void notifyRv() {
        if (PAGE_HOME.equals(type)) {
            for (int i = 0; i < swipeRv.getRecycleView().getChildCount(); i++) {
                View viewById = swipeRv.getRecycleView().getChildAt(i).findViewById(R.id.tv_want_reply);
                if (viewById != null) {
                    if (guide == null)
                        showGuideView(viewById);
                    break;
                }
            }

        }
    }

    @Override
    public void setEmpty(LinearLayout ll, Button button) {

    }

    Guide guide;

    public void showGuideView(View targetView) {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(targetView)
                .setFullingViewId(R.id.rel_main_base)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                for (int i = 0; i < swipeRv.getRecycleView().getChildCount(); i++) {
                    View view_question_root = swipeRv.getRecycleView().getChildAt(i).findViewById(R.id.view_question_root);

                    if (view_question_root != null) {
                        showGuideView2(view_question_root);
                        break;
                    }


                }
            }
        });


        builder.addComponent(new GuideTwoComponent());
        builder.addComponent(new GuideJumpComponent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide.dismissNoCallback();
                AccountDataRepo.getAccountManager().UseApp();

            }
        }));
        guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(true);
        guide.show(getActivity());


    }

    private List<TaskTimeEntity> taskTimeEntities = new ArrayList<>();

    public List<TaskTimeEntity> getTaskTimeEntities() {

        if (null == taskTimeEntities)
            taskTimeEntities = new ArrayList<>();
        return taskTimeEntities;
    }

    public void removeTaskAndPostDurtion(String task_id) {
        if (task_id == null)
            return;
        List<TaskTimeEntity> taskTimeEntities = getTaskTimeEntities();
        for (int i = 0; i < taskTimeEntities.size(); i++) {
            if (task_id.equals(taskTimeEntities.get(i).getTask_id())) {
                long durtion = (System.currentTimeMillis() - taskTimeEntities.get(i).getTime_enter()) / 1000;
//                在这里提交时间
                if (durtion > 0)
                    AppStatistics.onEventDurtion(getContext(), String.format("%s_time", type), taskTimeEntities.get(i).getTask_id(), durtion + "");
                getTaskTimeEntities().remove(i);
                break;


            }
        }
    }

    public void resetStart() {
        List<TaskTimeEntity> taskTimeEntities = getTaskTimeEntities();
        for (int i = 0; i < taskTimeEntities.size(); i++) {
            taskTimeEntities.get(i).setTime_enter(System.currentTimeMillis());
        }
    }

    public void addTaskTimeEntity(TaskTimeEntity taskTimeEntity) {
        List<TaskTimeEntity> taskTimeEntities = getTaskTimeEntities();
        for (int i = 0; i < taskTimeEntities.size(); i++) {
            if (taskTimeEntity.getTask_id().equals(taskTimeEntities.get(i).getTask_id())) {
                return;
            }
        }
        taskTimeEntities.add(taskTimeEntity);

    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new AbsMutiRvAdapter(getContext()) {
            @Override
            public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
                super.onViewAttachedToWindow(holder);
                if (holder.getAdapterPosition() >= 0 && holder.getAdapterPosition() < adapter.getItemCount()) {
                    HomeTaskEntity itemData = (HomeTaskEntity) adapter.getItemData(holder.getAdapterPosition());

                    if (!StringUtils.strIsEmpty(itemData.getTask_id())) {
                        TaskTimeEntity taskTimeEntity = new TaskTimeEntity();
                        taskTimeEntity.setTime_enter(System.currentTimeMillis());
                        taskTimeEntity.setTask_id(itemData.getTask_id());
                        addTaskTimeEntity(taskTimeEntity);


                    }
                }

            }

            @Override
            public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
                if (holder.getAdapterPosition() >= 0 && holder.getAdapterPosition() < adapter.getItemCount()) {
                    HomeTaskEntity itemData = (HomeTaskEntity) adapter.getItemData(holder.getAdapterPosition());
                    if (!StringUtils.strIsEmpty(itemData.getTask_id()))
                        removeTaskAndPostDurtion(itemData.getTask_id());
                }

            }
        };
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (applykol_view != null) {
                checkRewardAndCategory();
                checkTaskMatch();
            }
            resetStart();
        } else {
            postResumeDurtion();
        }


    }

    private void checkTaskMatch() {
        if (PAGE_MATCH.equals(type))
            mSubscription = AppCommonHandler.create().checkHowScore().subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean isCompletet) throws Exception {
                    if (applykol_view == null)
                        return;
                    if (isCompletet)
                        applykol_view.setVisibility(View.GONE);
                    else
                        applykol_view.setVisibility(View.VISIBLE);

                }

            });
    }

    private void checkRewardAndCategory() {
        if (PAGE_CATEGORY.equals(type) || PAGE_REWARD.equals(type)) {
            checkUserNewTask();
        }
    }

    private void postResumeDurtion() {
        List<TaskTimeEntity> taskTimeEntities = getTaskTimeEntities();
        for (int i = 0; i < taskTimeEntities.size(); i++) {
            long durtion = (System.currentTimeMillis() - taskTimeEntities.get(i).getTime_enter()) / 1000;
//                在这里提交时间
            if (durtion > 0)
                AppStatistics.onEventDurtion(getContext(), String.format("%s_time", type), taskTimeEntities.get(i).getTask_id(), durtion + "");


        }
    }

    public void showGuideView2(View targetView) {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(targetView)
                .setFullingViewId(R.id.rel_main_base)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
                AccountDataRepo.getAccountManager().UseApp();

            }

            @Override
            public void onDismiss() {

            }
        });


        builder.addComponent(new GuideThreeTopComponent());
        builder.addComponent(new GuideThreeBottomComponent());

        Guide guide2 = builder.createGuide();
        guide2.setShouldCheckLocInWindow(true);
        guide2.show(getActivity());


    }
}
