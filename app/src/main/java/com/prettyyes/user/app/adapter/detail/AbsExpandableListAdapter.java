package com.prettyyes.user.app.adapter.detail;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public class AbsExpandableListAdapter<T, X> extends BaseExpandableListAdapter {
    private ArrayList<T> datasParent;
    private ArrayList<T> datasChidren;

    @Override
    public int getGroupCount() {
        return datasParent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
