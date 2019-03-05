package com.prettyyes.user.core.rv;

import android.support.v7.widget.RecyclerView;

/**
 * Created by chengang on 2017/6/26.
 */

public interface ItemTouchHelperAdapter {
    //数据交换
    boolean onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target);

    //数据删除
    void onItemDissmiss(RecyclerView.ViewHolder source);

    //drag或者swipe选中
    void onItemSelect(RecyclerView.ViewHolder source);

    //状态清除
    void onItemClear(RecyclerView.ViewHolder source);
}
