package com.prettyyes.user.app.ui;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.model.task.TaskHomeTask;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

public class TestRecycle extends BaseActivity {

    private SwipeRecycleView swipeRecycleView;

//    HomepageRecycleAdapter homepageRecycleAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_test_recycle;
    }

    @Override
    protected void initViews() {
//        homepageRecycleAdapter = new HomepageRecycleAdapter(this);
//        swipeRecycleView.setAdapter(homepageRecycleAdapter);

    }
    @Override
    protected void setListener() {
        super.setListener();
        swipeRecycleView.setListener(new SwipeRecycleView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getdata(page);
            }
        });
    }
    private void getdata(int page) {
        new TaskImpl().taskHomeTask(getUUId(), page, new NetReqCallback<List<TaskHomeTask>>() {
            @Override
            public void apiRequestFail(String message,String method) {
                swipeRecycleView.loadingfail();
            }

            @Override
            public void apiRequestSuccess(List<TaskHomeTask> taskHomeTasks,String method) {
                if (taskHomeTasks.size() < 5) {
                    swipeRecycleView.loadingEnd();
                } else {
                    swipeRecycleView.loadingSuccessHavedata();
                }

            }
        });
    }
    @Override
    protected void loadData() {
        swipeRecycleView.loadingFistEnter();
    }
}
