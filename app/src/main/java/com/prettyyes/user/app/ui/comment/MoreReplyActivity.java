package com.prettyyes.user.app.ui.comment;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.TaskInfoRequest;
import com.prettyyes.user.app.adapter.holder_presenter.QuestionHolderViewImpl;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.ui.order.WishListActivity;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.app.view.lvandgrid.EmptyListener;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.CartNumEvent;
import com.prettyyes.user.core.event.LookReplyDelBackEvent;
import com.prettyyes.user.core.event.MoreReplyAlphaEvent;
import com.prettyyes.user.core.event.ReplyQuestionSuccessEvent;
import com.prettyyes.user.core.order.OrderManager;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.task.TaskInfo;

import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 更多回复页面
 */
public class MoreReplyActivity extends SingleListActivity<TaskInfo> implements EmptyListener {
    private String task_id;
    private String answer_id;
    private String desc;
    @ViewInject(R.id.rel_question)
    View rel_question;
    @ViewInject(R.id.ll_want_reply)
    LinearLayout ll_want_reply;
    @ViewInject(R.id.view_question)
    View view_question;
    @ViewInject(R.id.coordinatorLayout_more_reply)
    CoordinatorLayout coordinatorLayout_more_reply;
    @ViewInject(R.id.appbarlayout_more_reply)
    AppBarLayout appbarlayout_more_reply;

    QuestionHolderViewImpl questionHolderView;
    private BadgeView badgeView;

    @Override
    protected void initVariables() {
        super.initVariables();
        task_id = JumpPageManager.getManager().getIntentParmas(this).getTask_id();
        answer_id = JumpPageManager.getManager().getIntentParmas(this).getAnswer_id();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String new_task_id = JumpPageManager.getManager().getIntentParmas(this).getTask_id();
        String answer_id = JumpPageManager.getManager().getIntentParmas(this).getAnswer_id();
        if (answer_id != null)
            this.answer_id = answer_id;

        if (new_task_id.equals(task_id))
            return;
        else {
            task_id = new_task_id;
            swipeRv.loadingFistEnter();
        }


    }

    @Override
    protected void requestData(int page) {
        new TaskInfoRequest().setPage(page).setTask_id(task_id).setFirst_answer_id(answer_id).post(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_more_comment;
    }

    @Override
    protected void initViews() {
        super.initViews();
        showBack();
        badgeView = new BadgeView(this);
        badgeView.setTargetView(getImg_right());
        setActivtytitle("所有回复");
        setApptitleTvColor(ContextCompat.getColor(this, R.color.theme_darkgreen));
        setApptitleColor(ContextCompat.getColor(this, R.color.white));
        setStateBarColor(ContextCompat.getColor(this, R.color.white));
        setReightIconListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUUId() == null) {
                    RegisterActivity.getRegister(MoreReplyActivity.this);
                    return;
                }
                nextActivity(WishListActivity.class);
            }
        }, R.mipmap.how_shoopingcart_icon_black);
        swipeRv.setEmpty_listener(this);
        swipeRv.setMin_end_show(1);
        rel_question.setEnabled(false);


        questionHolderView = new QuestionHolderViewImpl(new MutiTypeViewHolder(getRootView()) {
            @Override
            public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

            }

            @Override
            public void bindView() {

            }
        }, 0, null);

    }


    @Override
    public DividerItemDecoration setDrividHeightPx(int height, int color) {
        return super.setDrividHeightPx(16, R.color.transparent).setFootview_num(1);
    }

    public boolean isfirst = true;

    public TaskInfo taskInfo;

    private void setHeadData(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
        questionHolderView.setData(taskInfo);
        questionHolderView.bindQuestion().bindQueOther();
        if (taskInfo.getUid().equals(SpMananger.getUid())) {
            questionHolderView.getView_question_root().setBackgroundResource(R.drawable.bg_gradient_tasklist);
        } else {
            questionHolderView.getView_question_root().setBackgroundColor(ContextCompat.getColor(this, R.color.pink_more_reply));
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return super.onTouchEvent(event);
    }

    public int getPage() {
        return page;
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ReplyQuestionSuccessEvent>() {
            @Override
            protected void back(ReplyQuestionSuccessEvent obj) {
                swipeRv.loadingFistEnter();
            }
        }, new RxCallback<CartNumEvent>() {
            @Override
            protected void back(CartNumEvent cartNumEvent) {
                badgeView.setBadgeCount(cartNumEvent.getNum());
            }
        }, new RxCallback<MoreReplyAlphaEvent>() {
            @Override
            protected void back(MoreReplyAlphaEvent moreReplyAlphaEvent) {
                if (moreReplyAlphaEvent.alpha > 0.9 && !StringUtils.strIsEmpty(desc)) {
                    setActivtytitle(desc);
                } else {
                    setActivtytitle("所有回复");

                }
            }
        }, new RxCallback<LookReplyDelBackEvent>() {
            @Override
            protected void back(LookReplyDelBackEvent lookReplyDelBackEvent) {

                LogUtil.i(TAG, "LookReplyDelBackEvent" + lookReplyDelBackEvent.task_id);
                if (lookReplyDelBackEvent.task_id != null && lookReplyDelBackEvent.task_id.equals(task_id)) {


                    if (adapter.getItems().size() == lookReplyDelBackEvent.datas.size()) {
                        adapter.getItems().set(lookReplyDelBackEvent.position, lookReplyDelBackEvent.datas.get(lookReplyDelBackEvent.position));
                        adapter.notifyItemChanged(lookReplyDelBackEvent.position);
                    }

                    for (int i = adapter.getItems().size(); i < lookReplyDelBackEvent.datas.size(); i++) {
                        adapter.add(lookReplyDelBackEvent.datas.get(i));
                    }


                    MoreReplyActivity.this.page = lookReplyDelBackEvent.page;
                    swipeRv.getRecycleView().smoothScrollToPosition(lookReplyDelBackEvent.position);

                }

            }
        });

        getTv_base_title().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeRv.loadingFistEnter();
                swipeRv.getRecycleView().smoothScrollToPosition(0);
                android.support.design.widget.CoordinatorLayout.Behavior behavior = ((android.support.design.widget.CoordinatorLayout.LayoutParams) appbarlayout_more_reply.getLayoutParams()).getBehavior();
                behavior.onNestedPreScroll(coordinatorLayout_more_reply, appbarlayout_more_reply, swipeRv, 0, -AppUtil.dip2px(155), new int[]{0, 0});

                Class clazz = behavior.getClass();
                try {
                    Method scroll=clazz.getDeclaredMethod("scroll");
                    scroll.setAccessible(true);
//                    scroll.invoke(behavior,)
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    @Override
    public void apiRequestFail(String message, String method) {
        super.apiRequestFail(message, method);
    }

    @Override
    public void apiRequestSuccess(TaskInfo taskInfo, String method) {
        desc = taskInfo.getDesc();
        if (page == 1) {
            setHeadData(taskInfo);
        }

        super.apiRequestSuccess(taskInfo, method);

    }


    @Override
    protected void loadData() {
        super.loadData();
        OrderManager.getInstance().refreshWishListNum();

    }

    @Override
    public List getListData(TaskInfo taskInfo) {
        return taskInfo.getSuit_list();
    }

    @Override
    public void setEmpty(LinearLayout ll, Button button) {
        ((TextView) ll.getChildAt(2)).setText("暂无回复");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SoftKeyboardUtil.observeSoftKeyboardDestopry(this);

    }

}
