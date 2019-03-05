package com.prettyyes.user.app.ui.comment;

import android.content.Context;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SystemApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.app.adapter.CommentHeadHolder;
import com.prettyyes.user.app.adapter.CommentSimpleAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.appview.BottomDoView2;
import com.prettyyes.user.app.ui.appview.CommentNumView;
import com.prettyyes.user.app.view.lvandgrid.SwipeListView;
import com.prettyyes.user.app.view.pupopwindow.InputDialog;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.CommentChildEvent;
import com.prettyyes.user.core.event.InputCallback;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.comment.CommentList;
import com.prettyyes.user.model.comment.CommentListRes;
import com.prettyyes.user.model.task.TaskInfo;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.QuestionEntity;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

public class CommentListActivity extends BaseActivity {



    private AnswerInfoEntity answerInfoEntity;
    private QuestionEntity questionEntity;
    @ViewInject(R.id.bottomView2_comment)
    BottomDoView2 bottomDoView2;
    @ViewInject(R.id.swipelist_comment)
    SwipeListView swipeListView;
    @ViewInject(R.id.CommentNumView)
    CommentNumView commentNumView_visiable;
    @ViewInject(R.id.rel_bottom)
    View rel_bottom;
    @ViewInject(R.id.edit_commentInfo)
    EditText editText;
    private int answer_id = 0;
    private CommentNumView commentNumView;


    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            if (intentParmas.getAnswer_id() != null)
                answer_id = Integer.parseInt(intentParmas.getAnswer_id());
            answerInfoEntity = intentParmas.getAnswerInfoEntity();
            questionEntity = intentParmas.getQuestionEntity();
            scrollToTop = intentParmas.isScrollToTop();
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_comment;
    }

    CommentHeadHolder commentHeadHolder;
    LinearLayout ll_head;
    @Override
    public void setSoftModel() {

    }

    @Override
    protected void initViews() {



        ll_head = new LinearLayout(this);
        commentHeadHolder = new CommentHeadHolder(ll_head);
        setActivtytitle("评论列表");
        commentSimpleAdapter = new CommentSimpleAdapter(this);

        swipeListView.setIsneedEmptyView(false);
        swipeListView.setAdapter(commentSimpleAdapter);
        commentNumView = new CommentNumView(this);
        swipeListView.getListView().addHeaderView(commentNumView);

        if (answerInfoEntity != null && questionEntity != null) {
            getList(1);
        } else {
            new TaskImpl().taskTaskInfoByAnswerId(answer_id, getUUId(), new NetReqCallback<TaskInfo>() {
                @Override
                public void apiRequestFail(String message, String method) {

                }

                @Override
                public void apiRequestSuccess(TaskInfo taskInfo, String method) {
                    swipeListView.loadingFistEnter();
                    CommentListActivity.this.answerInfoEntity = taskInfo.getSuit_list().get(0);
                    CommentListActivity.this.questionEntity = taskInfo;
                }
            });
        }
        commentSimpleAdapter.setCommentCallback(new CommentSimpleAdapter.CommentCallbak() {
            @Override
            public void comment(CommentList tag) {
               String commentHint= String.format("回复 %s", tag.getUsername());
            }
        });
    }


    public void setData() {
        bottomDoView2.setData(answerInfoEntity);
        commentHeadHolder.bindData(answerInfoEntity, questionEntity);
        bottomDoView2.getLin_comment().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getUUId() == null) {
                    AppUtil.showToastShort(getString(R.string.login_first));
                    RegisterActivity.getRegister(CommentListActivity.this);
                    return;
                }
                InputDialog inputDialog = new InputDialog(CommentListActivity.this);
                inputDialog.setInputCallback(new InputCallback() {
                    @Override
                    public void inputString(String text) {
                        new SystemApiImpl().addComment(getUUId(), text, Integer.parseInt(answerInfoEntity.getAnswer_id()), "task", 0, new NetReqCallback() {
                            @Override
                            public void apiRequestFail(String message, String method) {
                                AppUtil.showToastShort(message);

                            }

                            @Override
                            public void apiRequestSuccess(Object o, String method) {

                                isFirst = true;
                                scrollToTop = true;
                                swipeListView.loadingFistEnter();
                            }
                        });

                    }
                });
                inputDialog.show(R.id.activity_comment);
            }
        });
    }


    private CommentSimpleAdapter commentSimpleAdapter;

    @Override
    protected void setListener() {
        super.setListener();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == EditorInfo.IME_ACTION_SEND) && event != null) {


                    if(StringUtils.strIsEmpty(SpMananger.getUid()))
                    {
                        AppUtil.showToastShort(getString(R.string.login_first));
                        RegisterActivity.getRegister(getThis());
                        return true;
                    }
                    //点击搜索要做的操作
                    if (editText.getText().toString().trim().length() <= 0) {
                        AppUtil.showToastShort("评论不能为空");
                        return true;
                    } else {

                        new SystemApiImpl().addComment(getUUId(), editText.getText().toString().trim(), Integer.parseInt(answerInfoEntity.getAnswer_id()), "task", 0, new NetReqCallback() {
                            @Override
                            public void apiRequestFail(String message, String method) {
                                AppUtil.showToastShort(message);

                            }

                            @Override
                            public void apiRequestSuccess(Object o, String method) {

                                isFirst = true;
                                scrollToTop = true;
                                swipeListView.loadingFistEnter();
                            }
                        });
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        editText.setText("");
                    }
                }
                return false;
            }
        });

        mSubscription=AppRxBus.getInstance().subscribeEvent(new RxCallback<CommentChildEvent>() {
            @Override
            protected void back(CommentChildEvent obj) {
                comment_num++;
                setCommentNum(comment_num);
            }
        });

        swipeListView.setListener(new SwipeListView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getList(page);
            }
        });


        swipeListView.setLvScrollCallback(new SwipeListView.LvScrollCallback() {
            @Override
            public void lvOnScroll(AbsListView view, int scrollState) {

            }

            @Override
            public void lvScrollStateChanged(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    commentNumView_visiable.setVisibility(View.VISIBLE);
                } else {
                    commentNumView_visiable.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getList(final int page) {

        new SystemApiImpl().commentList(answer_id, "task", page, new NetReqCallback<CommentListRes>() {
            @Override
            public void apiRequestFail(String message, String method) {
                if (page == 1)
                    loadFail();
                swipeListView.loadingfail();
            }

            @Override
            public void apiRequestSuccess(CommentListRes commentListRes, String method) {
                swipeListView.loadingSuccessHavedata();

//                commentSimpleAdapter.addAll((ArrayList<CommentListEntity>) commentListRes.getList());

                if (commentListRes.getComment_count() > 0)
                    setCommentNum(commentListRes.getComment_count());
                if (commentListRes.getList().size() < MIN_PAGE_SIZE) {
                    swipeListView.loadingEnd();
                }
                if (page == 1 && isFirst && scrollToTop) {
                    swipeListView.getListView().post(new Runnable() {
                        @Override
                        public void run() {
                            swipeListView.getListView().requestFocusFromTouch();
                            swipeListView.getListView().setSelection(1);
                            loadSuccess();
                        }
                    });
                    isFirst = false;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadSuccess();
                    }
                }, 500);


            }
        });
    }

    @Override
    public void loadSuccess() {

        super.loadSuccess();

    }

    private boolean scrollToTop = true;
    private boolean isFirst = true;
    private int comment_num;

    private void setCommentNum(int num) {
        comment_num = num;
        commentNumView.setTotalComment(comment_num);
        commentNumView_visiable.setTotalComment(comment_num);
        bottomDoView2.setCommentNum(num);

    }


    @Override
    protected void loadData() {
        if (answer_id == 0)
            swipeListView.loadingFistEnter();
    }


}
