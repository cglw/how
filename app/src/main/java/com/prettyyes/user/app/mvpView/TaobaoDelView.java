package com.prettyyes.user.app.mvpView;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.api
 * Author: SmileChen
 * Created on: 2016/10/14
 * Description: Nothing
 */
public interface TaobaoDelView extends BaseView {
    void setVpData(List<String> data);

    void setCoverImg(String url);

    void setPrice(String price);

    void setTitle(String title);

    void setLink(String link);

    void share();

    void showProgressDialog(String msg);

    int getTaobaoId();

    void dismisProgreDialog();

    int getActivityBaseView();
}
