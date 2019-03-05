package com.prettyyes.user.app.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.base
 * Author: SmileChen
 * Created on: 2016/12/20
 * Description: Nothing
 */
public class AbsRecycleViewHolder extends RecyclerView.ViewHolder {
    public SparseArray<View> views;

    public AbsRecycleViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = null;
        try {
            view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
        } catch (Exception ee) {
        }

        return (T) view;
    }

    public <T extends View> T findViewById(int viewId) {
        return getView(viewId);
    }

    public View getRootView() {
        return itemView;
    }


}
