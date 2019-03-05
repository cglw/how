package com.prettyyes.user.app.ui.comment;

import android.app.Activity;
import android.content.Intent;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SystemApiImpl;
import com.prettyyes.user.app.adapter.CommentNotifyAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.model.comment.CommentMyList;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

public class CommentNotifyActivity extends BaseActivity {


    @ViewInject(R.id.swipe_commentNotify)
    private SwipeRecycleView swipeRecycleView;
    private CommentNotifyAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_comment_notify;
    }


    @Override
    protected void initViews() {
        setActivtytitle("回复列表");
        adapter = new CommentNotifyAdapter(this);
        swipeRecycleView.setAdapter(adapter);
    }

    public static void goCommentNotifyActivity() {
        Activity activity = ZBundleCore.getInstance().getTopActivity();
        Intent intent = new Intent(activity, CommentNotifyActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void loadData() {
        swipeRecycleView.loadPageData();
    }

    @Override
    protected void setListener() {
        super.setListener();
        swipeRecycleView.setListener(new SwipeRecycleView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getListData(page);
            }
        });
    }

    private void getListData(final int page) {

        new SystemApiImpl().myReplyCommentList(getUUId(), page, new NetReqCallback<List<CommentMyList>>() {
            @Override
            public void apiRequestFail(String message, String method) {
                if (page == 1)
                    loadFail();
                swipeRecycleView.loadingfail();
            }

            @Override
            public void apiRequestSuccess(List<CommentMyList> commentMyLists, String method) {
                loadSuccess();
                swipeRecycleView.loadingSuccessHavedata();
                adapter.addAll(commentMyLists);
                if (commentMyLists == null || commentMyLists.size() <= 0)
                    swipeRecycleView.loadingEnd();


            }
        });
    }
}
