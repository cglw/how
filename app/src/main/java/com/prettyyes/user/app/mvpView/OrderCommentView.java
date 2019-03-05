package com.prettyyes.user.app.mvpView;

import com.prettyyes.user.app.adapter.order.OrderCommentAdapter;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/11/18
 * Description: Nothing
 */
public interface OrderCommentView extends BaseView {
    String getOrderNumber();

    OrderCommentAdapter getAdapter();
}
