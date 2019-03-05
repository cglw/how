package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.core.utils.AppUtil;

/**
 * Created by chengang on 2017/7/10.
 */

public class AddTempleteRes extends BaseRes {
    private String module_id;
    private String module_type;
    private String newbie_task;

    public String getNewbie_task() {
        return newbie_task;
    }

    public void setNewbie_task(String newbie_task) {
        this.newbie_task = newbie_task;
    }

    public boolean isCompletetNewBie() {
        AppUtil.showToastShort(newbie_task);
        return newbie_task != null && newbie_task.length() > 0;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getModule_type() {
        return module_type;
    }

    public void setModule_type(String module_type) {
        this.module_type = module_type;
    }
}
