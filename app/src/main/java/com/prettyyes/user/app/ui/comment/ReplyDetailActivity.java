package com.prettyyes.user.app.ui.comment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.AnswerViewAddReq;
import com.prettyyes.user.api.netXutils.requests.TaskInfoRequest;
import com.prettyyes.user.app.adapter.QuestionHeadHolderImpl;
import com.prettyyes.user.app.adapter.holder_presenter.MutiSpuHolderViewImpl;
import com.prettyyes.user.app.adapter.holder_presenter.ReplyDelCommentHolderViewImpl;
import com.prettyyes.user.app.adapter.holder_presenter.ReplyHolderViewImpl;
import com.prettyyes.user.app.adapter.holder_presenter.SellerHolderViewImpl;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.BottomDoEvent;
import com.prettyyes.user.core.event.LookReplyDelBackEvent;
import com.prettyyes.user.core.event.ReplyQuestionSuccessEvent;
import com.prettyyes.user.core.utils.AnimotionUtils;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.task.TaskInfo;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 回复详情
 */
public class ReplyDetailActivity extends BaseActivity {

    private String task_id;

    @ViewInject(R.id.pull_scroll)
    PullToRefreshScrollView pull_scroll;
    @ViewInject(R.id.rel_pull_parent)
    LinearLayout rel_pull_parent;
    @ViewInject(R.id.tv_question)
    TextView tv_question;
    @ViewInject(R.id.view_share_line)
    View view_line;
    @ViewInject(R.id.ll_share)
    View ll_share;
    @ViewInject(R.id.ll_comment)
    LinearLayout ll_comment;
    @ViewInject(R.id.bottom)
    View bottom;
    @ViewInject(R.id.img_click)
    View img_click;

    public TaskInfo taskInfo;
    public String answer_id;
    public List<AnswerInfoEntity> datas;
    private int page = 1;
    private ReplyDelCommentHolderViewImpl moreReplyHolderView;
    private View view_content;
    private MutiSpuHolderViewImpl mutiSpuHolderView;
    private ReplyHolderViewImpl replyHolderView;
    private QuestionHeadHolderImpl questionHeadHolder;


    @Override
    public void needLoading(boolean need) {
        super.needLoading(true);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_reply_detail;
    }

    @Override
    protected void initViews() {
        datas = new ArrayList<>();
        setActivtytitle(R.string.title_activity_more_comment);

        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        task_id = intentParmas.getTask_id();
        answer_id = intentParmas.getAnswer_id();
        LogUtil.i(TAG, "answer_id" + answer_id);

        if (task_id == null) {
            page = intentParmas.getPage();
            data_index = intentParmas.getPosition();
            LogUtil.i(TAG, "data_index" + data_index);
            taskInfo = (TaskInfo) intentParmas.getQuestionEntity();
            datas = intentParmas.getAnswerInfoEntities();
        }
        view_line.setVisibility(View.VISIBLE);
        ll_share.setVisibility(View.VISIBLE);
        view_content = pull_scroll.getChildAt(0);

        //增加阅读
        new AnswerViewAddReq().setAnswer_id(answer_id).post(null);


    }


    @Override
    protected void loadData() {
        if (task_id != null) {
            getPageData(pull_scroll);
        } else {
            task_id = taskInfo.getTask_id();
            setData(data_index, pull_scroll);
            loadSuccess();

        }
    }

    private int data_index;


    public void postBackInfo() {
        AppRxBus.getInstance().post(new LookReplyDelBackEvent().setPage(page).setPosition(data_index).setDatas(datas).setTask_id(task_id));

    }

    public void getPageData(final PullToRefreshScrollView pullToRefreshScrollView) {

        new TaskInfoRequest().setPage(page).setTask_id(task_id).setFirst_answer_id(answer_id).post(new NetReqCallback<TaskInfo>() {
            private QuestionHeadHolderImpl questionHeadHolder;

            @Override
            public void apiRequestFail(String message, String method) {
                showSnack(message);
                loadFail();
            }

            @Override
            public void apiRequestSuccess(TaskInfo taskInfo, String method) {

                loadSuccess();

                ReplyDetailActivity.this.taskInfo = taskInfo;
                answer_id = null;
                if (page == 1) {
                    datas.clear();

                    if (questionHeadHolder == null)
                        questionHeadHolder = new QuestionHeadHolderImpl(new MutiTypeViewHolder(getRootView()) {
                            @Override
                            public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

                            }

                            @Override
                            public void bindView() {

                            }
                        });
                    questionHeadHolder.setData(taskInfo);
                    questionHeadHolder.bindQuestion();

                }
                datas.addAll(taskInfo.getSuit_list());
                if (datas.size() > 0 && !datas.get(0).getAnswer_id().equals("0")) {
                    pullToRefreshScrollView.setVisibility(View.VISIBLE);
                    bottom.setVisibility(View.VISIBLE);
                } else {
                    pullToRefreshScrollView.setVisibility(View.INVISIBLE);
                    bottom.setVisibility(View.INVISIBLE);
                    return;

                }

                ReplyDetailActivity.this.taskInfo = taskInfo;
                if (isfirst) {
                    setData(data_index, pullToRefreshScrollView);
                    isfirst = false;

                }
                page++;

                if (taskInfo.getSuit_list().size() <= 0) {
                    isloadend = true;
                    return;
                }


            }
        });

    }

    private boolean isfirst = true;


    public boolean isloadend = false;

    private int view_height;


    public int last_index = 0;

    public void setData(int index, PullToRefreshScrollView pullToRefreshScrollView) {

        if (datas.size() <= 0)
            return;


        AnswerInfoEntity answerInfoEntity = datas.get(index);
        if (StringUtils.strIsEmpty(answerInfoEntity.getAnswer_id()) || answerInfoEntity.getAnswer_id().equals("0")) {
            pullToRefreshScrollView.setVisibility(View.GONE);
            bottom.setVisibility(View.GONE);
        } else {
            pullToRefreshScrollView.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.VISIBLE);
        }


        if (index == 0) {
            // 上拉加载更多时的提示文本设置
            setTop("上面没有了哦", pullToRefreshScrollView, true);
        } else {
            setTop("上一个", pullToRefreshScrollView, false);

        }
        if (index == taskInfo.getTask_count() - 1) {
            setBottom("没有更多了", pullToRefreshScrollView, true);
        } else {
            setBottom("下一个", pullToRefreshScrollView, false);
        }

        if (pullToRefreshScrollView == null)
            return;
        if (index > datas.size() - 1)
            return;


        if (questionHeadHolder == null)
            questionHeadHolder = new QuestionHeadHolderImpl(new MutiTypeViewHolder(getRootView()) {
                @Override
                public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

                }

                @Override
                public void bindView() {

                }
            });
        questionHeadHolder.setData(taskInfo);
        questionHeadHolder.bindQuestion();

        replyHolderView = new ReplyHolderViewImpl(new MutiTypeViewHolder(getRootView()) {
            @Override
            public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

            }

            @Override
            public void bindView() {

            }
        });
        replyHolderView.setData(datas.get(data_index));
        replyHolderView.bindBottom();
        replyHolderView.bindNormalText(pullToRefreshScrollView);
        replyHolderView.bindReadNumber();

        SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
        sellerInfoEntity.setSeller_id(answerInfoEntity.getSeller_id());
        sellerInfoEntity.setHeadimg(answerInfoEntity.getHeadimg());
        sellerInfoEntity.setNickname(answerInfoEntity.getNickname());
        sellerInfoEntity.setFamous_type(answerInfoEntity.getFamous_type());
        sellerInfoEntity.setAce_txt(answerInfoEntity.getAce_txt());
        sellerInfoEntity.setGrade_title(answerInfoEntity.getGrade_title());
        new SellerHolderViewImpl(new MutiTypeViewHolder(pullToRefreshScrollView) {
            @Override
            public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

            }

            @Override
            public void bindView() {

            }
        }, 0, sellerInfoEntity).bindSellerViews();


        moreReplyHolderView = new ReplyDelCommentHolderViewImpl(new MutiTypeViewHolder(pullToRefreshScrollView) {
            @Override
            public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

            }

            @Override
            public void bindView() {

            }
        });
        datas.get(index).ishaveLoad = true;
        moreReplyHolderView.setData(datas.get(index));
        moreReplyHolderView.setLl_comment(ll_comment);
        moreReplyHolderView.bindComment();

        mutiSpuHolderView = new MutiSpuHolderViewImpl(new MutiTypeViewHolder(pullToRefreshScrollView) {
            @Override
            public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

            }

            @Override
            public void bindView() {

            }
        });
        mutiSpuHolderView.setData(datas.get(data_index).getAnswer_data());
        mutiSpuHolderView.bindMutiSpu();


        ReplyHolderViewImpl replyHolderView2 = new ReplyHolderViewImpl(new MutiTypeViewHolder(pullToRefreshScrollView) {
            @Override
            public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

            }

            @Override
            public void bindView() {

            }
        });
        replyHolderView2.setData(datas.get(data_index));
        replyHolderView2.bindVedioImages();

    }


    public void setTop(String txt, PullToRefreshScrollView pullToRefreshScrollView, boolean hide) {
        pullToRefreshScrollView.getLoadingLayoutProxy(true, false).setPullLabel(txt);
        pullToRefreshScrollView.getLoadingLayoutProxy(true, false).setRefreshingLabel(txt);
        pullToRefreshScrollView.getLoadingLayoutProxy(true, false).setReleaseLabel(txt);

    }

    public void setBottom(String txt, PullToRefreshScrollView pullToRefreshScrollView, boolean hide) {
        pullToRefreshScrollView.getLoadingLayoutProxy(false, true).setPullLabel(txt);
        pullToRefreshScrollView.getLoadingLayoutProxy(false, true).setRefreshingLabel(txt);
        pullToRefreshScrollView.getLoadingLayoutProxy(false, true).setReleaseLabel(txt);

    }


    public void setPull_scrollListener(final PullToRefreshScrollView pull_scroll) {

        pull_scroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener3<ScrollView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ScrollView> refreshView) {
                if (data_index > 0) {
                    data_index--;
                    postBackInfo();
                } else {
                    refreshView.setState_pub(PullToRefreshBase.State.RESET);
                    return;
                }
                img_click.setVisibility(View.VISIBLE);

                final PullToRefreshScrollView inflate = (PullToRefreshScrollView) LayoutInflater.from(getThis()).inflate(R.layout.view_reply_detail, null);
                setPull_scrollListener(inflate);
                rel_pull_parent.addView(inflate, 0);
                final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) inflate.getLayoutParams();
                layoutParams.height = view_height;
                layoutParams.setMargins(0, -view_height, 0, 0);
                setData(data_index, inflate);

                AnimotionUtils.animateTrans_noremove(inflate, -view_height, 0, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                rel_pull_parent.removeView(refreshView);
                                img_click.setVisibility(View.GONE);
                            }
                        });

                    }
                });
                LogUtil.i(TAG, "data_index" + data_index + "data-counbt" + getData_count());
            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<ScrollView> refreshView) {


                if (data_index >= getData_count() / 2) {
                    if (!isloadend)
                        getPageData((PullToRefreshScrollView) refreshView);
                }

                if (data_index < getData_count() - 1) {
                    data_index++;
                    postBackInfo();
                } else {
                    refreshView.setState_pub(PullToRefreshBase.State.RESET);
                    return;
                }
                img_click.setVisibility(View.VISIBLE);


                final PullToRefreshScrollView inflate = (PullToRefreshScrollView) LayoutInflater.from(getThis()).inflate(R.layout.view_reply_detail, null);
                setPull_scrollListener(inflate);
                rel_pull_parent.addView(inflate);
                setData(data_index, inflate);
                inflate.getLayoutParams().height = view_height;
                AnimotionUtils.animateTrans(refreshView, -view_height, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        img_click.setVisibility(View.GONE);

                    }
                });
                LogUtil.i(TAG, "data_index" + data_index + "data-counbt" + getData_count());

            }
        });
    }

    @Override
    protected void setListener() {
        super.setListener();
        img_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        pull_scroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view_height = pull_scroll.getHeight();
                if (view_height > 0) {
                    pull_scroll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    pull_scroll.getLayoutParams().height = view_height;

                }
            }
        });

        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ReplyQuestionSuccessEvent>() {
            @Override
            protected void back(ReplyQuestionSuccessEvent replyQuestionSuccessEvent) {
                if (replyQuestionSuccessEvent.getTask_id().equals(task_id)) {
                    if (datas.size() == 0 || (datas.size() == 1 && datas.get(0).getAnswer_id().equals("0"))) {
                        getPageData(pull_scroll);
                    } else {
                        taskInfo.setTask_count(taskInfo.getTask_count() + 1);
                        taskInfo.setMy_answer_replyed();
                        questionHeadHolder.setQuestion();
                    }
                }
            }
        }, new RxCallback<BottomDoEvent>() {
            @Override
            protected void back(BottomDoEvent obj) {
                postBackInfo();
            }
        });

        setPull_scrollListener(pull_scroll);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public int getData_count() {
        if (datas != null)
            return datas.size();
        return 0;
    }

}
