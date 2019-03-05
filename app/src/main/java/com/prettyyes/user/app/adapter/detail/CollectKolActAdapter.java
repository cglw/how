package com.prettyyes.user.app.adapter.detail;

import android.app.Activity;
import android.view.View;

import com.prettyyes.user.app.base.BaseViewAdapter;
import com.prettyyes.user.app.ui.appview.HomeActivityEnterView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.task.FollActList;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public class CollectKolActAdapter extends BaseViewAdapter<FollActList.FollAct, HomeActivityEnterView> {

    public CollectKolActAdapter(Activity activity) {
        super(activity, new ArrayList());
    }

    @Override
    public HomeActivityEnterView getLayoutView(Activity context) {
        return new HomeActivityEnterView(context);
    }

    @Override
    public void setData(HomeActivityEnterView view, int i, final FollActList.FollAct data) {

        view.setTitle(data.getAct_name());
        view.setTime(data.getStart_time());
        view.setTv_click("查看");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                KolActivityActivity.goKolActActivity(activity,data.getAct_id());
                JumpPageManager.getManager().goKolActActivity(activity,data.getAct_id());

            }
        });

    }
}
