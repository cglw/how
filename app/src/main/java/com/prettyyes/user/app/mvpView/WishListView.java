package com.prettyyes.user.app.mvpView;

import com.prettyyes.user.app.adapter.detail.WishListVpAdapter;
import com.prettyyes.user.app.mvpPresenter.WishListPresenter;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/10/26
 * Description: Nothing
 */
public interface WishListView extends BaseView{
    void setHeadimg(String url);
    void setVpdata(ArrayList<WishListPresenter.WishVpEntity>data);
    WishListVpAdapter getAdapter();
    void showProgressDialog(String msg);

    void dismisProgreDialog();

}
