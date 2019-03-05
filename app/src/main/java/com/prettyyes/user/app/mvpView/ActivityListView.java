package com.prettyyes.user.app.mvpView;

import com.prettyyes.user.model.ActivityListModel;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/11/5
 * Description: Nothing
 */
public interface ActivityListView extends BaseView {
    public void setLvData(ArrayList<ActivityListModel.ListEntity>data);

}
