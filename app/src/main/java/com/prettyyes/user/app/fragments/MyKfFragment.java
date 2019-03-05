package com.prettyyes.user.app.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.app.adapter.detail.HowActivtyAdapter;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.ActivityListModel;
import com.prettyyes.user.model.HowActivityModel;
import com.prettyyes.user.model.other.NotifyCount;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.UriFragment;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.model.Event;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ConnectionStatusListener.ConnectionStatus;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.MessageContent;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;
import io.rong.push.RongPushClient;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.fragments
 * Author: SmileChen
 * Created on: 2016/11/2
 * Description: Nothing
 */
public class MyKfFragment extends UriFragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final String TAG = "MyKfFragment";
    private boolean isShowWithoutConnected = false;
    private LinearLayout mNotificationBar;
    private ImageView mNotificationBarImage;
    private TextView mNotificationBarText;
    private ListView mList;
    private ArrayList<HowActivityModel> datas = new ArrayList<>();
    private ArrayList<ConversationType> mSupportConversationList = new ArrayList();

    private HowActivtyAdapter howActivtyAdapter;
    private RongIMClient.ResultCallback<List<Conversation>> mCallback = new RongIMClient.ResultCallback<List<Conversation>>() {
        public void onSuccess(List<Conversation> conversations) {
            HowActivityModel howActivityModel = new HowActivityModel();
            if (conversations != null) {
                for (int i = 0; i < conversations.size(); i++) {
                    if (conversations.get(i).getConversationType().equals(ConversationType.CUSTOMER_SERVICE)) {
                        howActivityModel = createModel(conversations.get(i).getLatestMessage(), conversations.get(i).getReceivedTime(), "客服");
                        break;
                    }
                }
            }
            getListData(howActivityModel);


        }

        public void onError(RongIMClient.ErrorCode e) {

        }
    };

//    private void updateData(List<Conversation> conversations) {
//        if (conversations != null) {
//            for (int i = 0; i < conversations.size(); i++) {
//                if (conversations.get(i).getConversationType().equals(ConversationType.CUSTOMER_SERVICE)) {
//                    updateAdapter(conversations.get(i).getLatestMessage(), conversations.get(i).getReceivedTime(), "客服", conversations.get(i).getUnreadMessageCount());
//                    return;
//
//                }
//
//
//            }
//
//        }
//    }

    
    private HowActivityModel createModel(MessageContent messageContent, long time, String type) {
        HowActivityModel howActivityModel = new HowActivityModel();
        howActivityModel.setType(type);
        howActivityModel.setReceivetime(time);
        howActivityModel.setLastMeseage(MessageContentToString(messageContent));
        return howActivityModel;
    }


    private String MessageContentToString(MessageContent messageContent) {
        if (messageContent instanceof ImageMessage) {
            return "[图片]";
        } else if (messageContent instanceof TextMessage) {
            return ((TextMessage) messageContent).getContent().toString();
        } else if (messageContent instanceof VoiceMessage) {
            return "[语言]";

        }

        return "[草稿]";
    }

    private Set<ConversationKey> mConversationKeyList;

    public void onResume() {
        super.onResume();
        if (!RongIMClient.getInstance().getCurrentConnectionStatus().equals(ConnectionStatus.CONNECTED)) {
            this.isShowWithoutConnected = true;
        } else {
            RongPushClient.clearAllPushNotifications(this.getActivity());
            ConnectionStatus status = RongIM.getInstance().getCurrentConnectionStatus();
            this.setNotificationBarVisibility(status);
        }
    }

    public void getListData(final HowActivityModel howActivityModel) {
        new OtherApiImpl().getActivityList(new NetReqCallback<ActivityListModel>() {
            @Override
            public void apiRequestFail(String message, String method) {
            }

            @Override
            public void apiRequestSuccess(ActivityListModel activityListModel, String method) {

                if (activityListModel != null) {
                    HowActivityModel a = new HowActivityModel();
                    a.setType("官方");
                    a.setReceivetime(FormatUtils.StringToDate(activityListModel.getList().get(0).getCreate_time()));
                    a.setLastMeseage(activityListModel.getList().get(0).getActivity_describe());
                    a.setTitle(activityListModel.getList().get(0).getActivity_title());
                    a.setHeadimg(activityListModel.getList().get(0).getMain_img());
                    a.setUnread(DataCenter.ACTIVITY_UNREAD_NUM);
                    if (a != null)
                        howActivtyAdapter.add(a);
                }

                if (howActivityModel.getType() != null) {
                    howActivtyAdapter.add(howActivityModel);
                }
            }
        });
    }

    public void onDestroy() {
        RongContext.getInstance().getEventBus().unregister(this);
        this.getHandler().removeCallbacksAndMessages((Object) null);
        AppRxBus.getInstance().unregister(mSubscription);

        super.onDestroy();
    }

    private Object mSubscription;

    public void onPause() {
        super.onPause();
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        howActivtyAdapter = new HowActivtyAdapter(getActivity());
        AppRxBus.getInstance().register(this);

        RongPushClient.clearAllPushNotifications(this.getActivity());
        this.mSupportConversationList.clear();
        this.mConversationKeyList = new HashSet();
        RongContext.getInstance().getEventBus().register(this);
        getNotify();

    }

    private void getCommentNotify() {
        HowActivityModel a = new HowActivityModel();
        a.setType("评论");
        a.setLastMeseage("查看评论");
        a.setTitle("How");
        howActivtyAdapter.add(a);
    }

    private void getNotify() {
        new OtherApiImpl().getNotityCount(BaseApplication.UUID, new NetReqCallback<NotifyCount>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(NotifyCount notifyCount, String method) {
                if (notifyCount != null) {
                    HowActivityModel a = new HowActivityModel();
                    a.setType("通知");
                    a.setReceivetime(FormatUtils.StringToDate(notifyCount.getCreate_time()));
                    a.setLastMeseage(notifyCount.getContent());
                    a.setTitle("How");
                    a.setHeadimg(notifyCount.getHeadimg());
                    a.setUnread(DataCenter.NOTIFY_UNREAD_NUM);
                    if (a != null)
                        howActivtyAdapter.add(a);
                }

            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_howactivity, container, false);
        this.mNotificationBar = (LinearLayout) this.findViewById(view, R.id.rc_status_bar);
        this.mNotificationBar.setVisibility(View.GONE);
        this.mNotificationBarImage = (ImageView) this.findViewById(view, R.id.rc_status_bar_image);
        this.mNotificationBarText = (TextView) this.findViewById(view, R.id.rc_status_bar_text);
        this.mList = this.findViewById(view, R.id.rc_how_list);
        LinearLayout emptyView = (LinearLayout) this.findViewById(view, R.id.rc_conversation_list_empty_layout);
        ConnectionStatus status = RongIM.getInstance().getCurrentConnectionStatus();
        TextView textView = (TextView) this.findViewById(view, R.id.rc_empty_tv);
        if (status.equals(ConnectionStatus.DISCONNECTED)) {
            textView.setText(RongContext.getInstance().getResources().getString(R.string.rc_conversation_list_not_connected));
        } else {
            textView.setText(RongContext.getInstance().getResources().getString(R.string.rc_conversation_list_empty_prompt));
        }
        this.setNotificationBarVisibility(status);
        this.mList.setEmptyView(emptyView);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // initAdapterFirstData();
        this.mList.setOnItemClickListener(this);
        this.mList.setOnItemLongClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    private void initAdapterFirstData() {
        HowActivityModel how = new HowActivityModel();
        how.setType("官方");
        howActivtyAdapter.add(how);
    }

    private void setNotificationBarVisibility(ConnectionStatus status) {
        if (!this.getResources().getBoolean(R.bool.rc_is_show_warning_notification)) {
        } else {
            String content = null;
            if (status.equals(ConnectionStatus.NETWORK_UNAVAILABLE)) {
                content = this.getResources().getString(R.string.rc_notice_network_unavailable);
            } else if (status.equals(ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT)) {
                content = this.getResources().getString(R.string.rc_notice_tick);
            } else if (status.equals(ConnectionStatus.CONNECTED)) {
                this.mNotificationBar.setVisibility(View.GONE);
            } else if (status.equals(ConnectionStatus.DISCONNECTED)) {
                content = this.getResources().getString(R.string.rc_notice_disconnect);
            } else if (status.equals(ConnectionStatus.CONNECTING)) {
                content = this.getResources().getString(R.string.rc_notice_connecting);
            }

            if (content != null) {
                if (this.mNotificationBar.getVisibility() == View.GONE) {
                    final String finalContent = content;
                    this.getHandler().postDelayed(new Runnable() {
                        public void run() {
                            if (!RongIMClient.getInstance().getCurrentConnectionStatus().equals(ConnectionStatus.CONNECTED)) {
                                MyKfFragment.this.mNotificationBar.setVisibility(View.VISIBLE);
                                MyKfFragment.this.mNotificationBarText.setText(finalContent);
                                if (RongIMClient.getInstance().getCurrentConnectionStatus().equals(ConnectionStatus.CONNECTING)) {
                                    MyKfFragment.this.mNotificationBarImage.setImageDrawable(MyKfFragment.this.getResources().getDrawable(R.drawable.rc_notification_connecting_animated));
                                } else {
                                    MyKfFragment.this.mNotificationBarImage.setImageDrawable(MyKfFragment.this.getResources().getDrawable(R.drawable.rc_notification_network_available));
                                }
                            }

                        }
                    }, 4000L);
                } else {
                    this.mNotificationBarText.setText(content);
                    if (RongIMClient.getInstance().getCurrentConnectionStatus().equals(ConnectionStatus.CONNECTING)) {
                        this.mNotificationBarImage.setImageDrawable(this.getResources().getDrawable(R.drawable.rc_notification_connecting_animated));
                    } else {
                        this.mNotificationBarImage.setImageDrawable(this.getResources().getDrawable(R.drawable.rc_notification_network_available));
                    }
                }
            }

        }
    }

//    public void onEventMainThread(Message message) {
//        if (this.mSupportConversationList.size() != 0 && !this.mSupportConversationList.contains(message.getConversationType()) || this.mSupportConversationList.size() == 0 && (message.getConversationType() == ConversationType.CHATROOM || message.getConversationType() == ConversationType.CUSTOMER_SERVICE)) {
//        } else {
//            if (howActivtyAdapter.get(1) != null) {
//                howActivtyAdapter.get(1).setLastMeseage(MessageContentToString(message.getContent()));
//                howActivtyAdapter.get(1).setReceivetime(System.currentTimeMillis());
//                howActivtyAdapter.notifyDataSetChanged();
//
//            }
//        }
//    }

    public void onEventMainThread(ConnectionStatus status) {
        this.setNotificationBarVisibility(status);
        if (this.isShowWithoutConnected && status.equals(ConnectionStatus.CONNECTED)) {
            this.isShowWithoutConnected = false;
        }

    }

    public void onEventMainThread(final Event.OnReceiveMessageEvent event) {
//        if (RongContext.getInstance().getConversationGatherState(event.getMessage().getConversationType().getName()).booleanValue()) {
//            RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
//                public void onSuccess(List<Conversation> conversations) {
//                    updateData(conversations);
//                }
//
//                public void onError(RongIMClient.ErrorCode e) {
//                }
//            }, new ConversationType[]{event.getMessage().getConversationType()});
//        }

    }

    public void onEventMainThread(final Event.ConversationRemoveEvent removeEvent) {


    }

    protected void initFragment(Uri uri) {
//        RLog.d(this.TAG, "initFragment " + uri);
//        ConversationType[] defConversationType = new ConversationType[]{ConversationType.PRIVATE, ConversationType.GROUP, ConversationType.DISCUSSION, ConversationType.SYSTEM, ConversationType.CUSTOMER_SERVICE, ConversationType.CHATROOM, ConversationType.PUBLIC_SERVICE, ConversationType.APP_PUBLIC_SERVICE};
//        ConversationType[] type = defConversationType;
//        int var4 = defConversationType.length;
//
//        int var5;
//        for(var5 = 0; var5 < var4; ++var5) {
//            ConversationType conversationType = type[var5];
//            if(uri.getQueryParameter(conversationType.getName()) != null) {
//                ConversationListFragment.ConversationConfig conversationType1 = new ConversationListFragment.ConversationConfig(null);
//                conversationType1.conversationType = conversationType;
//                conversationType1.isGathered = uri.getQueryParameter(conversationType.getName()).equals("true");
//                this.mConversationsConfig.add(conversationType1);
//            }
//        }
//
//        if(this.mConversationsConfig.size() == 0) {
//            String var9 = uri.getQueryParameter("type");
//            ConversationType[] var10 = defConversationType;
//            var5 = defConversationType.length;
//
//            for(int var11 = 0; var11 < var5; ++var11) {
//                ConversationType var12 = var10[var11];
//                if(var12.getName().equals(var9)) {
//                    ConversationListFragment.ConversationConfig config = new ConversationListFragment.ConversationConfig(null);
//                    config.conversationType = var12;
//                    config.isGathered = false;
//                    this.mConversationsConfig.add(config);
//                    break;
//                }
//            }
//        }
//
//        this.mAdapter.clear();
//        if(RongIMClient.getInstance().getCurrentConnectionStatus().equals(ConnectionStatus.DISCONNECTED)) {
//            RLog.d(this.TAG, "RongCloud haven\'t been connected yet, so the conversation list display blank !!!");
//            this.isShowWithoutConnected = true;
//        } else {
//            this.getConversationList(this.getConfigConversationTypes());
//        }
    }


//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        howActivtyAdapter.get(position).setUnread(0);
//        howActivtyAdapter.notifyDataSetChanged();
//        String type = howActivtyAdapter.get(position).getType();
//        if (type.equals("客服")) {
//            chatWithkf();
//        } else if (type.equals("官方")) {
//            DataCenter.clearUnread(DataCenter.UnreadType.ACTIVITY);
//            Intent intent = new Intent(getActivity(), HowAdvListActivity.class);
//            intent.putExtra("data", activityLists);
//            ((BaseRongyunActivity) getActivity()).nextActivity(intent);
//        } else if (type.equals("通知")) {
//            DataCenter.clearUnread(DataCenter.UnreadType.PRETTYYES_NOTIFY);
//            Intent intent = new Intent(getActivity(), NotifyActivity.class);
//            ((BaseRongyunActivity) getActivity()).nextActivity(intent);
//        } else if (type.equals("评论")) {
//            CommentNotifyActivity.goCommentNotifyActivity();
//            DataCenter.clearUnread(DataCenter.UnreadType.COMMENT);
//        }
//
//    }


    public void chatWithkf() {
        //首先需要构造使用客服者的用户信息
        //首先需要构造使用客服者的用户信息
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
        RongIM.getInstance().startCustomerServiceChat(getActivity(), "KEFU146718733598596", "在线客服", csInfo);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

//    @Override
//    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        doDelete();
//        return true;
//    }


//    private void doDelete() {
//        AlertView alertView = new AlertView("提醒", "是否删除？", "取消", new String[]{"确定"}, null, getActivity(), AlertView.Style.Alert, new OnItemClickListener() {
//            @Override
//            public void onItemClick(Object o, final int position) {
//                if (position == 0) {
//                    String type = howActivtyAdapter.get(position).getType();
//                    if (type.equals("客服")) {
//                        RongIM.getInstance().removeConversation(ConversationType.CUSTOMER_SERVICE, AppConfig.KF);
//
//                    } else {
//                        if (howActivtyAdapter.get(position) != null) {
//                            howActivtyAdapter.remove(position);
//                        }
//                    }
//                }
//            }
//        }).setCancelable(true);
//        alertView.show();
//    }


}
