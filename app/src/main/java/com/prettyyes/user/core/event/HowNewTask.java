package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/5/19.
 */

public class HowNewTask {

    private boolean showRefresh;

    public boolean isShowRefresh() {
        return showRefresh;
    }

    public void setShowRefresh(boolean showRefresh) {
        this.showRefresh = showRefresh;
    }

    public HowNewTask(boolean showRefresh) {
        this.showRefresh = showRefresh;
    }
}
