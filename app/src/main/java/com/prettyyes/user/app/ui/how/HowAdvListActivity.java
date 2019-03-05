package com.prettyyes.user.app.ui.how;

import android.widget.ListView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.detail.ActivityListAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.ActivityListPresenter;
import com.prettyyes.user.app.mvpView.ActivityListView;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.model.ActivityListModel;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * app 的内置广告页
 */
public class HowAdvListActivity extends BaseActivity implements ActivityListView {


    @ViewInject(R.id.lv_howadvlistAct)
    private ListView lv;
    private ActivityListAdapter activityListAdapter;
    private ActivityListPresenter activityListPresenter = new ActivityListPresenter(this);
    private ArrayList<ActivityListModel.ListEntity> datas = new ArrayList<>();

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_how_adv_list;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        datas = (ArrayList<ActivityListModel.ListEntity>) getIntent().getSerializableExtra("data");
    }

    @Override
    protected void initViews() {

        setActivtytitle("官方活动");
        activityListAdapter = new ActivityListAdapter(this, new ActivityListAdapter.Callback() {
            @Override
            public void click(String url) {
                new PushHandler(getThis()).handReceiveData(url);
//                activityListPresenter.handlerUrl(url);
            }
        });
        lv.setAdapter(activityListAdapter);
        activityListAdapter.addAll(datas);

    }



    @Override
    protected void setListener() {

    }

    @Override
    protected void loadData() {
        // activityListPresenter.getListData();
    }

    @Override
    public void setLvData(ArrayList<ActivityListModel.ListEntity> data) {
        // activityListAdapter.addAll(data);
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }
}
