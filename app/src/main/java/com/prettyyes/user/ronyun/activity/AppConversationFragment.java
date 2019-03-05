package com.prettyyes.user.ronyun.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.detail.ConversationListAdapterEx;
import com.prettyyes.user.app.base.BaseFragment;

import java.util.List;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by chengang on 2017/12/11.
 */

public class AppConversationFragment extends BaseFragment {
    @Override
    public int bindLayout() {
        return R.layout.fragment_conversation_app;
    }

    private ConversationListFragment mConversationListFragment = null;
    private Conversation.ConversationType[] mConversationsTypes = null;

    @Override
    public View bindView() {
        return null;
    }


    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.rong_content, initConversationList());
        transaction.commit();


    }

    @Override
    public void doBusiness(Context mContext) {
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                if (conversations != null && conversations.size() > 0) {
                    for (Conversation c : conversations) {
                        RongIM.getInstance().clearMessagesUnreadStatus(c.getConversationType(), c.getTargetId(), null);
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        }, mConversationsTypes);

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;

            uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .build();
            mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                    Conversation.ConversationType.GROUP,
                    Conversation.ConversationType.PUBLIC_SERVICE,
                    Conversation.ConversationType.APP_PUBLIC_SERVICE,
                    Conversation.ConversationType.SYSTEM};


            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }


}
