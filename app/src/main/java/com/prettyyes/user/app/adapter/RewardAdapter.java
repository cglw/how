package com.prettyyes.user.app.adapter;

import android.app.Activity;

import com.prettyyes.user.app.base.BaseViewAdapter;
import com.prettyyes.user.app.ui.appview.RewardView;
import com.prettyyes.user.model.type.RewardListEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public class RewardAdapter extends BaseViewAdapter<RewardListEntity.InfoEntity, RewardView> {
    public RewardAdapter(Activity activity) {
        super(activity, new ArrayList());
    }

    @Override
    public RewardView getLayoutView(Activity context) {
        return new RewardView(context);
    }

    @Override
    public void setData(RewardView view, int i, RewardListEntity.InfoEntity data) {
        view.setDataByModel(data.getReward());
    }
}
