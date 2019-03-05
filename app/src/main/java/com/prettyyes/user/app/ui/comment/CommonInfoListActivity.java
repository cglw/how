package com.prettyyes.user.app.ui.comment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SystemApiImpl;
import com.prettyyes.user.app.adapter.CommentInfoAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.LoadMoreCommentEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.comment.CommentInfo;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

public class CommonInfoListActivity extends BaseActivity {


    @ViewInject(R.id.swipe_commentInfo)
    SwipeRecycleView swipeRecycleView;

    @ViewInject(R.id.edit_commentInfo)
    EditText editText;
    private int comment_id = 0;
    private int answer_id = 0;
    private CommentInfoAdapter commentInfoAdapter;
    private boolean showMore = false;
    private String uuid = "";
    private boolean isfromNotify = false;


    @Override
    protected int bindLayout() {
        return R.layout.activity_common_list;
    }

    public static void goCommonInfoListActivity(BaseActivity activity, int comment_id, int answer_id) {
        Intent intent = new Intent(activity, CommonInfoListActivity.class);
        intent.putExtra("comment_id", comment_id);
        intent.putExtra("answer_id", answer_id);
        activity.nextActivity(intent);
    }

    public static void goCommonInfoListActivity(BaseActivity activity, int comment_id, int answer_id, boolean fromNotify) {
        Intent intent = new Intent(activity, CommonInfoListActivity.class);
        intent.putExtra("comment_id", comment_id);
        intent.putExtra("answer_id", answer_id);
        intent.putExtra("fromNotify", fromNotify);
        activity.nextActivity(intent);
    }

    @Override
    public void setSoftModel() {

    }

    @Override
    protected void initVariables() {
        super.initVariables();
        comment_id = getIntent().getIntExtra("comment_id", 0);
        answer_id = getIntent().getIntExtra("answer_id", 0);
        load_comment_id = comment_id;
        showMore = isfromNotify;
        if (ZBundleCore.getInstance().isLastSecond(CommentNotifyActivity.class)) {
            uuid = getUUId();
            isfromNotify = true;
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == EditorInfo.IME_ACTION_SEND) && event != null) {


                    if (StringUtils.strIsEmpty(getUUId())) {
                        AppUtil.showToastShort(getString(R.string.login_first));
                        RegisterActivity.getRegister(CommonInfoListActivity.this);
                        return true;
                    }
                    //点击搜索要做的操作
                    if (editText.getText().toString().trim().length() <= 0) {
                        AppUtil.showToastShort("评论不能为空");
                        return true;
                    } else {

                        postComment();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        editText.setText("");
                    }
                }
                return false;
            }
        });
        swipeRecycleView.setListener(new SwipeRecycleView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getListData(page);
            }
        });

//        mSubscription = AppRxBus.getInstance().toObservable(LoadMoreCommentEvent.class).subscribe(new RxAction1<LoadMoreCommentEvent>() {
//
//            @Override
//            public void callback(LoadMoreCommentEvent loadMoreCommentEvent) {
//
//                uuid = "";
//                swipeRecycleView.loadingFistEnter();
//
//
//            }
//        });

        mSubscription=AppRxBus.getInstance().subscribeEvent(new RxCallback<LoadMoreCommentEvent>() {
            @Override
            protected void back(LoadMoreCommentEvent obj) {
                uuid = "";
                swipeRecycleView.loadingFistEnter();


            }
        });
    }

    private void postComment() {
        if (editText.getText().toString().length() <= 0)
            return;
        new SystemApiImpl().addComment(getUUId(), editText.getText().toString(), answer_id, "task", comment_id, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {
                AppUtil.showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(Object o, String method) {

                swipeRecycleView.loadingFistEnter();
            }
        });

    }


    private int load_comment_id = 0;

    private void getListData(final int page) {

        new SystemApiImpl().CommentInfo(getUUId(), load_comment_id, page, new NetReqCallback<CommentInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                loadFail();
                swipeRecycleView.loadingfail();
                dismissProgressDialog();
            }

            @Override
            public void apiRequestSuccess(CommentInfo commentInfo, String method) {
                loadSuccess();
                dismissProgressDialog();

                swipeRecycleView.loadingSuccessHavedata();
                load_comment_id = commentInfo.getComment_id();

                if (commentInfoAdapter.getItemCount() == 0) {
                    CommentInfo.ChildrenBean childrenBean = new CommentInfo.ChildrenBean();
                    childrenBean.setHeadimg(commentInfo.getHeadimg());
                    childrenBean.setUsername(commentInfo.getUsername());
                    childrenBean.setCreated_at(commentInfo.getCreated_at());
                    childrenBean.setComment(commentInfo.getComment());
                    commentInfo.getChildren().add(0, childrenBean);
                }

                commentInfoAdapter.addAll(commentInfo.getChildren());
                if (commentInfo.getChildren().size() < MIN_PAGE_SIZE)
                    swipeRecycleView.loadingEnd();

                if (isFirst) {
                    for (int i = 0; i < commentInfoAdapter.getItemCount(); i++) {
                        if (comment_id == commentInfoAdapter.getItemData(i).getComment_id()) {
                            LinearLayoutManager layoutManager = (LinearLayoutManager) swipeRecycleView.getRecycleView().getLayoutManager();
                            layoutManager.scrollToPosition(i);
                        }
                    }
                }

            }
        });
        uuid = "";
    }


    private boolean isFirst = true;

    @Override
    protected void initViews() {
        setActivtytitle(getString(R.string.title_activity_commentInfoList));
        commentInfoAdapter = new CommentInfoAdapter(this);
        commentInfoAdapter.setComment_id(comment_id);
        swipeRecycleView.setAdapter(commentInfoAdapter);
    }

    @Override
    protected void loadData() {
        swipeRecycleView.loadPageData();
    }
}
