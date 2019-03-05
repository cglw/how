package com.prettyyes.user.app.adapter.order;

import android.content.Context;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.view.lvandgrid.SwipeListView;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.order
 * Author: SmileChen
 * Created on: 2016/11/3
 * Description: Nothing
 */
public class OrderListVpAdapter extends AbsVpAdapter {
    public OrderListVpAdapter(Context context, int layoutRes) {
        super(context, new ArrayList(), layoutRes);
    }

    @Override
    public void bindView(ViewHolder vHolder) {
        swipeList_orderlistVpAdp = (SwipeListView) vHolder.getView(R.id.swipeList_orderlistVpAdp);
    }

    private SwipeListView swipeList_orderlistVpAdp;

    @Override
    public void showData(ViewHolder vHolder, Object data, int position) {

    }
}
