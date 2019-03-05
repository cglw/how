package com.prettyyes.user.app.ui.order;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.order.OrderCommentAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.OrderCommentPresenter;
import com.prettyyes.user.app.mvpView.OrderCommentView;

import org.xutils.view.annotation.ViewInject;

public class OrdercommentActivity extends BaseActivity implements OrderCommentView {

    @ViewInject(R.id.lin_orderCommentAct_post)
    private LinearLayout lin_post;
    @ViewInject(R.id.lv_orderCommentAct)
    private ListView lv;
    private String ordernumber;
    private OrderCommentAdapter orderCommentAdapter;
    public OrderCommentPresenter orderCommentPresenter = new OrderCommentPresenter(this);

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_ordercomment;
    }

    @Override
    protected void initVariables() {
        ordernumber = getIntent().getStringExtra("ordernumber");
    }

    @Override
    protected void initViews() {
        setActivtytitle("评论");
        orderCommentAdapter = new OrderCommentAdapter(this);
        lv.setAdapter(orderCommentAdapter);

    }

    @Override
    protected void setListener() {
        super.setListener();
        lin_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCommentPresenter.postCommentData();
            }
        });
    }

    @Override
    protected void loadData() {
        orderCommentPresenter.getListData();
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public String getOrderNumber() {
        return ordernumber;
    }

    @Override
    public OrderCommentAdapter getAdapter() {
        return orderCommentAdapter;
    }
}
