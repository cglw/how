package com.prettyyes.user.model;

/**
 * Created by chengang on 2017/5/17.
 */

public class FirstModel extends BaseModel{

    /**
     * first_login : 1
     * first_task_ask : 1
     * first_task_zeze : 1
     * first_share : 1
     */

    private int first_login;
    private int first_task_ask;
    private int first_task_zeze;
    private int first_share;

    public int getFirst_login() {
        return first_login;
    }

    public void setFirst_login(int first_login) {
        this.first_login = first_login;
    }

    public int getFirst_task_ask() {
        return first_task_ask;
    }

    public void setFirst_task_ask(int first_task_ask) {
        this.first_task_ask = first_task_ask;
    }

    public int getFirst_task_zeze() {
        return first_task_zeze;
    }

    public void setFirst_task_zeze(int first_task_zeze) {
        this.first_task_zeze = first_task_zeze;
    }

    public int getFirst_share() {
        return first_share;
    }

    public void setFirst_share(int first_share) {
        this.first_share = first_share;
    }
}
