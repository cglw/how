package com.prettyyes.user.core.event;

import android.view.View;

/**
 * Created by chengang on 2017/7/27.
 */

public class SendVerifyCodeEvent {
    View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public SendVerifyCodeEvent(View view) {
        this.view = view;
    }
}
