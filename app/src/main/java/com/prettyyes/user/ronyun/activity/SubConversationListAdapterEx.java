package com.prettyyes.user.ronyun.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.SubConversationListAdapter;
import io.rong.imlib.model.Conversation;

/**
 * Created by chengang on 2017/12/11.
 */

public class SubConversationListAdapterEx  extends SubConversationListAdapter {

    public SubConversationListAdapterEx(Context context) {
        super(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup group) {
        return super.newView(context, position, group);
    }

    @Override
    protected void bindView(View v, int position, UIConversation data) {
        if (data.getConversationType().equals(Conversation.ConversationType.DISCUSSION))
            data.setUnreadType(UIConversation.UnreadRemindType.REMIND_ONLY);
        super.bindView(v, position, data);
    }
}
