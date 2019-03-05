package com.prettyyes.user.ronyun.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.adapter.detail.ConversationListAdapterEx;
import com.prettyyes.user.app.adapter.detail.VpfragmentAdapter;
import com.prettyyes.user.app.base.BaseRongyunActivity;
import com.prettyyes.user.app.fragments.MessageListFragment;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.app.SegmentControlView;
import com.prettyyes.user.core.event.UnreadEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.databaseModel.RongyunUserInfo;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Bob on 15/8/18.
 * 会话列表
 */
public class ConversationListActivity extends BaseRongyunActivity {

    @ViewInject(R.id.vp_conversationList_pager)
    private ViewPager vp_pager;
    @ViewInject(R.id.segment_conversationList)
    private SegmentControlView segmentControlView;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<RongyunUserInfo> data = new ArrayList<>();

    BadgeView mMyDotView;

    @Override
    protected int bindLayout() {
        return R.layout.rongyun_conversationlist;
    }

    @Override
    protected void initViews() {
        showBack();
        setActivtytitle("会话列表");
        try {
            data = new RongyunUserInfo().getRongyunUserInfo();
        } catch (DbException e) {
            e.printStackTrace();
        }
        initListData();
        mMyDotView = new BadgeView(this);
        mMyDotView.setTargetView(segmentControlView);//就是这里
        mMyDotView.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        mMyDotView.setHideOnNull(true);
        mMyDotView.setTextSize(7);
        mMyDotView.setBadgeCount(DataCenter.getNotifyTotal());


    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        super.setInentFliter(inentFliter);
        inentFliter.addAction(Constant.Activity_num_Unread);

    }

    private void initListData() {
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

    @Override
    protected void loadData() {
        isReconnect();
    }

    @Override
    protected void setListener() {
        segmentControlView.setOnSegmentChangedListener(new SegmentControlView.OnSegmentChangedListener() {
            @Override
            public void onSegmentChanged(int newSelectedIndex) {
                if (vp_pager != null)
                    vp_pager.setCurrentItem(newSelectedIndex);
            }
        });
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<UnreadEvent>() {
            @Override
            protected void back(final UnreadEvent unreadEvent) {
                mMyDotView.setBadgeCount(unreadEvent.getCount() + DataCenter.Chat_UNREAD_NUM);

                RongIM.getInstance().getUnreadCount(Conversation.ConversationType.CUSTOMER_SERVICE, AppConfig.KF, new RongIMClient.ResultCallback<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        mMyDotView.setBadgeCount(unreadEvent.getCount() + integer - DataCenter.Chat_UNREAD_NUM);

                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });

            }
        });
    }

    private ConversationListFragment mConversationListFragment = null;
    private Conversation.ConversationType[] mConversationsTypes = null;

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;

            uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.CUSTOMER_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//系统
                    .build();
            mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
            };


            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }


    /**
     * 判断消息是否是 push 消息
     */
    private void isReconnect() {

        Intent intent = getIntent();
        String token = null;

        if (getUserInfo() != null && getUserInfo().rongyun_token != null) {

            token = getUserInfo().rongyun_token.getRongyun_buyer();
        }

        //push，通知或新消息过来
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {

            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (intent.getData().getQueryParameter("push") != null
                    && intent.getData().getQueryParameter("push").equals("true")) {

                reconnect(token);
            } else {
                //程序切到后台，收到消息后点击进入,会执行这里
                if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null) {

                    reconnect(token);
                } else {
                    loadFragment();
                }
            }
        }
    }


    private void loadFragment() {
        fragments.clear();
        fragments.add(initConversationList());
        fragments.add(new MessageListFragment());
        vp_pager.setAdapter(new VpfragmentAdapter(getSupportFragmentManager(), fragments));
        segmentControlView.setViewPager(vp_pager);
        if (DataCenter.getNotifyTotal() > 0 && DataCenter.Chat_UNREAD_NUM == 0 && DataCenter.KF_UNREAD_NUM == 0) {
            vp_pager.setCurrentItem(1);
        }
    }

    /**
     * 重连
     *
     * @param token
     */
    private void reconnect(String token) {

        if (getApplicationInfo().packageName.equals(AppUtil.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String s) {
                    loadFragment();
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }
}
