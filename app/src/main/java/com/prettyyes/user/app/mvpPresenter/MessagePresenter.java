package com.prettyyes.user.app.mvpPresenter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.prettyyes.user.app.mvpView.MessageView;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.databaseModel.RongyunUserInfo;

import org.xutils.ex.DbException;

import java.util.ArrayList;

import io.rong.imkit.RongIM;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/10/31
 * Description: Nothing
 */
public class MessagePresenter {
    private MessageView messageView;

    public MessagePresenter(MessageView messageView) {
        this.messageView = messageView;
    }

    private ArrayList<RongyunUserInfo> data = new ArrayList<>();

    public void initHeadimg() {
        try {
            data = new RongyunUserInfo().getRongyunUserInfo();
        } catch (DbException e) {
            e.printStackTrace();
        }
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {
                try {
                    return getUserInfoFromDb(userId);
                } catch (DbException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }, true);

    }

    private UserInfo getUserInfoFromDb(String user_id) throws DbException {
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getUser_id().equals(user_id)) {
                    return new UserInfo(user_id, data.get(i).getNickname(), Uri.parse(data.get(i).getPortraitUri()));
                }
            }
        }
        return null;
    }

    /**
     * 判断消息是否是 push 消息
     */
    public void isReconnect(ConversationListAdapter adapter, Callback callback) {

        Intent intent = messageView.getActivity().getIntent();
        String token = null;

        if (messageView.getActivity().getUserInfo() != null) {

            token = messageView.getActivity().getUserInfo().rongyun_token.getRongyun_buyer();
        }

        //push，通知或新消息过来
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {

            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (intent.getData().getQueryParameter("push") != null
                    && intent.getData().getQueryParameter("push").equals("true")) {

                reconnect(token, adapter, callback);
            } else {
                //程序切到后台，收到消息后点击进入,会执行这里
                if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null) {

                    reconnect(token, adapter, callback);
                } else {
                    enterFragment(adapter, callback);
                }
            }
        }
    }

    /**
     * 重连
     *
     * @param token
     */
    private void reconnect(String token, final ConversationListAdapter adapter, final Callback callback) {

        if (messageView.getActivity().getApplicationInfo().packageName.equals(AppUtil.getCurProcessName(messageView.getActivity().getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String s) {

                    enterFragment(adapter, callback);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }


    private void enterFragment(ConversationListAdapter adapter, Callback callback) {

    }

    public interface Callback {
        public void back(Fragment fragment);

    }
}
