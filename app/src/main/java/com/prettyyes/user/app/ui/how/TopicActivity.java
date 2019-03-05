package com.prettyyes.user.app.ui.how;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.api.netXutils.response.HomeListRes;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.QuestionEntity;
import com.prettyyes.user.app.ui.appview.TopicHeadView;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.app.view.recycle.RecyclerViewUtils;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.event.TopicAskEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StatusBarUtil;
import com.prettyyes.user.model.TopicEntityRes;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;
import static com.prettyyes.user.R.id.ll_questionAct_head;


public class TopicActivity extends BaseActivity {
    private static final String TAG = "QuestionActivity";

    @ViewInject(R.id.swipeRecycle_questionAct)
    private SwipeRecycleView swipeRecycle_question;
    @ViewInject(ll_questionAct_head)
    private LinearLayout ll_head;
    @ViewInject(R.id.tv_questionAct_title)
    private TextView tv_title;
    @ViewInject(R.id.img_questionAct_back)
    private ImageView img_back;
    @ViewInject(R.id.ll_ask)
    private View view_ask;
    @ViewInject(R.id.tv_topic_name)
    private TextView tv_hash_tag;
    @ViewInject(R.id.img_share)
    private ImageView img_share;
    private AbsMutiRvAdapter adapter;
    private int topic_id = 0;
    private TopicHeadView headview;


    public static void goQuestionActivity(Context context, int topic_id) {
        Intent intent = new Intent(context, TopicActivity.class);
        intent.putExtra("topic_id", topic_id);
        if (context instanceof BaseActivity)
            ((BaseActivity) context).nextActivity(intent);
        else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        topic_id = getIntent().getIntExtra("topic_id", 0);

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_question;
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, AppConfig.STARTBAR_ALPHA, null);
    }
//
//    protected void initViews() {
//

//    }


    @Override
    protected void initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            maxDistance = DensityUtil.dip2px(340 - 24 - 50);
        else
            maxDistance = DensityUtil.dip2px(340 - 50);


        headviewHeight = AppUtil.dip2px(340);
        hideHeader();
        ll_head.setAlpha(0);
        adapter = new AbsMutiRvAdapter<>(this);
        headview = new TopicHeadView(this);
        swipeRecycle_question.setFootviewShow(false);
        swipeRecycle_question.setAdapter(adapter);
        RecyclerViewUtils.setHeaderView(swipeRecycle_question.getRecycleView(), headview);

        swipeRecycle_question.getRecycleView().addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST, AppUtil.dip2px(16), R.color.backgroundWhit).setHaveFootView());


    }

    @Override
    protected void loadData() {
        getTopicInfo();

    }

    @Override
    protected void setListener() {
        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        view_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topic == null) {
                    AppUtil.showToastShort("还未获取到专题信息");
                    return;
                }
                QuestionEntity questionEntity = new QuestionEntity();
                questionEntity.setTopic_id(topic.getTopic_id() + "");
                questionEntity.setTopic_hash_tag(topic.getTopic_hash_tag());
                AskActivity.goAskActivity(TopicActivity.this, questionEntity, new ArrayList<SellerInfoEntity>());
                AppStatistics.onEvent(getThis(),"topic_ask_before","topic_id",topic_id+"");

            }
        });

//        mSubscription = AppRxBus.getInstance().toObservable(TopicAskEvent.class).subscribe(new RxAction1<TopicAskEvent>() {
//            @Override
//            public void callback(TopicAskEvent topicAskEvent) {
//                swipeRecycle_question.loadingFistEnter();
//                getTopicInfo();
//            }
//        });
        mSubscription=AppRxBus.getInstance().subscribeEvent(new RxCallback<TopicAskEvent>() {
            @Override
            protected void back(TopicAskEvent obj) {
                swipeRecycle_question.loadingFistEnter();
                getTopicInfo();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        swipeRecycle_question.setListener(new SwipeRecycleView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getData(page);
            }
        });
//
//        swipeRecycle_question.setLvScrollCallback(new SwipeListView.LvScrollCallback() {
//            @Override
//            public void lvOnScroll(AbsListView view, int o) {
//            }
//
//            @Override
//            public void lvScrollStateChanged(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                int dy = getScrollY();//滚动距离
//
//                mDistance = dy;
//                float percent = mDistance * 1f / maxDistance;//百分比
//                alpha = (int) (percent * 255);//得到当前应当设置给标题栏的透明度
//                ll_head.setAlpha(percent);
//                if (mDistance >= maxDistance)
//                    tv_title.setVisibility(View.VISIBLE);
//                else
//                    tv_title.setVisibility(View.GONE);
//
//            }
//
//            public int getScrollY() {
//                View c = swipeRecycle_question.getListView().getChildAt(0);
//                if (c == null) {
//                    return 0;
//                }
//                int firstVisiblePosition = swipeRecycle_question.getListView().getFirstVisiblePosition();
//                if (firstVisiblePosition > 0)
//                    return maxDistance;
//                int top = c.getTop();
//                return -top + firstVisiblePosition * c.getHeight();
//            }
//
//        });

        swipeRecycle_question.setRvScrollCallback(new SwipeRecycleView.RvScrollCallback() {
            @Override
            public void rvonScrolled(RecyclerView recyclerView, int dx, int dy) {
                mDistance = getScollYDistance();
                float percent = mDistance * 1f / maxDistance;//百分比
                alpha = (int) (percent * 255);//得到当前应当设置给标题栏的透明度
                ll_head.setAlpha(percent);
                if (mDistance >= maxDistance)
                    tv_title.setVisibility(View.VISIBLE);
                else
                    tv_title.setVisibility(View.GONE);

            }

            @Override
            public void rvScrollStateChanged(RecyclerView recyclerView, int newState) {

            }
        });
    }


    private int headviewHeight = 0;

    private int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) swipeRecycle_question.getRecycleView().getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = 0;
        if (position > 0)
            itemHeight = firstVisiableChildView.getHeight();
        int total;
        if (position > 0) {
            total = (position - 1) * itemHeight + headviewHeight;
        } else {
            total = itemHeight;
        }

        return total - firstVisiableChildView.getTop();
    }

    int mDistance = 0;
    int maxDistance;//当距离在[0,255]变化时，透明度在[0,255之间变化]
    int alpha = 0;

    public void share() {
        if (topic == null) {
            showToastShort("还未获取到专题信息");
            return;
        }
        new ShareHandler(this).setRes(topic.getShareModel(), new ShareHandler.ShareCallback() {
            @Override
            public void share(boolean issuccess) {

            }
        }).shareAtWindow(R.id.rel_question_main);
    }

    private void getData(final int page) {
        new TaskImpl().getTopicList(getUUId(), topic_id, page, new NetReqCallback<HomeListRes>() {
            @Override
            public void apiRequestFail(String message, String method) {
                swipeRecycle_question.loadingfail();
                if (page == 1)
                    loadFail();


            }

            @Override
            public void apiRequestSuccess(HomeListRes homeListRes, String method) {
                loadSuccess();
                if (page == 1) {
                    if (!isFirstEnter)
                        getTopicInfo();
                }


                swipeRecycle_question.loadingSuccessHavedata();
                for (int i = 0; i < homeListRes.getList().size(); i++) {
                    homeListRes.getList().get(i).que_color = color;
                    homeListRes.getList().get(i).setTopic_hash_tag("");
                    LogUtil.i(TAG, "color" + color);
                    homeListRes.getList().get(i).setActivity_type("topic");
                }
                adapter.addAll(homeListRes.getList());
                if (homeListRes.getList().size() < MIN_PAGE_SIZE)
                    swipeRecycle_question.loadingEnd();

            }

        });

    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        super.setInentFliter(inentFliter);
        inentFliter.addAction(Constant.OrderPaySuccess);
    }


    private TopicEntityRes.InfoBean topic;
    private boolean isFirstEnter = true;

    public void getTopicInfo() {

        new TaskImpl().getTopicInfo(getUUId(), topic_id, new NetReqCallback<TopicEntityRes>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(TopicEntityRes topicEntityRes, String method) {


                topic = topicEntityRes.getInfo();
                tv_title.setText(topicEntityRes.getInfo().getTopic_name());
                headview.setDataByModel(topicEntityRes.getInfo());

                tv_hash_tag.setText(topicEntityRes.getInfo().getTopic_hash_tag());
                color = Color.parseColor("#FDB4A2");
                try {
                    color = Color.parseColor(topicEntityRes.getInfo().getTopic_bg_color());
                } catch (Exception ee) {

                }
                ll_head.setBackgroundColor(color);


                swipeRecycle_question.loadingFistEnter();


            }
        });
    }


    private int color;


}
