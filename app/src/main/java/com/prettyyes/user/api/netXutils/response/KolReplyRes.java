package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.core.utils.AppUtil;

/**
 * Created by chengang on 2017/8/28.
 */

public class KolReplyRes {
    public String show_join_page;
    private String newbie_task;

    public boolean isCompleteNewBie() {
        AppUtil.showToastShort(newbie_task);
        return newbie_task != null && newbie_task.length() > 0;
    }

    public String getNewbie_task() {
        return newbie_task;
    }

    public void setNewbie_task(String newbie_task) {
        this.newbie_task = newbie_task;
    }



    public boolean needShowApply() {
        return show_join_page != null && show_join_page.equals("1");
    }

}
