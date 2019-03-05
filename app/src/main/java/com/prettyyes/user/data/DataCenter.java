package com.prettyyes.user.data;

import android.graphics.Bitmap;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.SystemApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.api.netXutils.requests.InvateMeClearReq;
import com.prettyyes.user.api.netXutils.requests.InvateMeUnreadReq;
import com.prettyyes.user.api.netXutils.requests.ReadCommentRequest;
import com.prettyyes.user.api.netXutils.requests.TaskClearUnreadReq;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.event.AskUnreadEvent;
import com.prettyyes.user.core.event.CommentUnreadEvent;
import com.prettyyes.user.core.event.MineDynamicUnreadEvent;
import com.prettyyes.user.core.event.MorePageUnreadEvent;
import com.prettyyes.user.core.event.UnreadEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.UnreadModel;
import com.prettyyes.user.model.other.NotifyCount;
import com.prettyyes.user.model.system.ActivtyUnreadModel;
import com.prettyyes.user.model.task.TaskTotalTips;
import com.prettyyes.user.model.v8.CountCommonEntity;

import java.io.Serializable;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.data
 * Author: SmileChen
 * Created on: 2016/12/22
 * Description: Nothing
 */
public class DataCenter implements Serializable {

    //官方获得未读
    public static int ACTIVITY_UNREAD_NUM = 0;
    //推送消息未读
    public static int NOTIFY_UNREAD_NUM = 0;
    //聊天未读
    public static int Chat_UNREAD_NUM = 0;
    //客服聊天未读
    public static int KF_UNREAD_NUM = 0;
    //评论未读
    public static int COMMENT_UNREAD_NUM = 0;
    //回复问题未读
    public static int TASK_UNREAD_NUM = 0;
    //邀请我未读
    public static int INVATE_ME_UNREAD_NUM = 0;


    public static int CURRENT_PAPER_ID = 0;
    public static PayType CURRENT_PAY_TYPE = PayType.Order;
    public static long SERVER_TIME = 0;
    public static long LIVE_ACTIVITY_END_TIME = 0;
    public static ChatFromType CURRENT_ChatFromType = ChatFromType.NORMAL;
    public static Bitmap bgBitmap = null;
    public static int SELLRT_ID_CURRENT = 0;
    public static int ANSWER_ID_CURRENT = 0;

    private long time_unlogin;
    private String ishaveShowUnregister;

    public String ishaveShowUnregister() {
        return ishaveShowUnregister;
    }

    public void setIshaveShowUnregister(String ishaveShowUnregister) {
        this.ishaveShowUnregister = ishaveShowUnregister;
    }

    public long getTime_unlogin() {
        return time_unlogin;
    }

    public void setTime_unlogin(long time_unlogin) {
        this.time_unlogin = time_unlogin;
    }

    public enum PayType implements Serializable {
        Order, Paper
    }

    public enum ChatFromType implements Serializable {
        SKU, NORMAL
    }

    public enum CouponGetType implements Serializable {
        UNREGISTER, REGISTER, SHARE, ASK, ZEZE
    }

    public enum UnreadType implements Serializable {
        ACTIVITY, COMMENT, PRETTYYES_NOTIFY, CHAT, TASK, ALL, MORE
    }

    // 首页右上角的
    public static int getUnreadTotal() {
        return ACTIVITY_UNREAD_NUM + NOTIFY_UNREAD_NUM + Chat_UNREAD_NUM + KF_UNREAD_NUM;
    }

    //首页右上角进去  通知的
    public static int getNotifyTotal() {
        return ACTIVITY_UNREAD_NUM + NOTIFY_UNREAD_NUM;
    }

    public static void getUnread(UnreadType type) {
        if (SpMananger.getUUID() == null) {
            ACTIVITY_UNREAD_NUM = 0;
            NOTIFY_UNREAD_NUM = 0;
            Chat_UNREAD_NUM = 0;
            KF_UNREAD_NUM = 0;
            COMMENT_UNREAD_NUM = 0;
            TASK_UNREAD_NUM = 0;
            INVATE_ME_UNREAD_NUM=0;
            AppRxBus.getInstance().post(new UnreadEvent(getUnreadTotal()));
            AppRxBus.getInstance().post(new MineDynamicUnreadEvent(getMineDynamicTotal()));
            AppRxBus.getInstance().post(new MorePageUnreadEvent(getMoreUnreadTotal()));

            return;
        }
        switch (type) {
            case MORE:
                getInvateMeUnread();
                break;
            case ACTIVITY:
                getActivityUnread();
                break;
            case PRETTYYES_NOTIFY:
                getPrettyyesNotify();
                break;
            case COMMENT:
                getCommentUnread();
                break;
            case TASK:
                getTaskUnread();
            case ALL:
                setRonyuListener();
                getActivityUnread();
                getPrettyyesNotify();
                getCommentUnread();
                getTaskUnread();
                getInvateMeUnread();
                break;

        }


    }

    // tab收到答案的
    public static int getMineDynamicTotal() {
        return COMMENT_UNREAD_NUM + TASK_UNREAD_NUM;
    }
    // tab 更多的的
    public static int getMoreUnreadTotal() {
        return INVATE_ME_UNREAD_NUM + TASK_UNREAD_NUM;
    }

    public static int MineDynamicTotal = 0;

    private static void getTaskUnread() {
        new TaskImpl().taskTaskTotalTips(SpMananger.getUUID(), new NetReqCallback<TaskTotalTips>() {

            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(TaskTotalTips taskTotalTips, String method) {
                TASK_UNREAD_NUM = taskTotalTips.getTotal_tips();
                AppRxBus.getInstance().post(new MineDynamicUnreadEvent(getMineDynamicTotal()));
                AppRxBus.getInstance().post(new MorePageUnreadEvent(getMoreUnreadTotal()));

            }
        });
    }

    private static void getInvateMeUnread() {
        new InvateMeUnreadReq().post(new NetReqCallback<CountCommonEntity>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(CountCommonEntity o, String method) {
                INVATE_ME_UNREAD_NUM = o.getCount();
                AppRxBus.getInstance().post(new MorePageUnreadEvent(getMoreUnreadTotal()));

            }
        });
    }


    public static void clearUnread(UnreadType type) {

        switch (type) {
            case ACTIVITY:
                clearActivityUnread();
                break;
            case PRETTYYES_NOTIFY:
                clearPrettyyesNotify();
                break;
            case COMMENT:
                clearCommentUnread();
                break;
            case ALL:
                clearActivityUnread();
                clearCommentUnread();
                clearPrettyyesNotify();
                break;
        }


    }

    private static void clearCommentUnread() {
        if (COMMENT_UNREAD_NUM > 0) {
            COMMENT_UNREAD_NUM = 0;
            AppRxBus.getInstance().post(new CommentUnreadEvent(0));
            new SystemApiImpl().updateCommentUnread(BaseApplication.UUID, new NetReqCallback() {
                @Override
                public void apiRequestFail(String message, String method) {

                }

                @Override
                public void apiRequestSuccess(Object o, String method) {

                }
            });
        }


    }

    private static void clearPrettyyesNotify() {
        if (DataCenter.NOTIFY_UNREAD_NUM > 0) {
            DataCenter.NOTIFY_UNREAD_NUM = 0;
            AppRxBus.getInstance().post(new UnreadEvent(getUnreadTotal()));
            new OtherApiImpl().clearNotifyCount(BaseApplication.UUID, new NetReqCallback() {
                @Override
                public void apiRequestFail(String message, String method) {

                }

                @Override
                public void apiRequestSuccess(Object o, String method) {

                }
            });
        }

    }


    private static void clearActivityUnread() {
        if (DataCenter.ACTIVITY_UNREAD_NUM > 0) {
            DataCenter.ACTIVITY_UNREAD_NUM = 0;
            AppRxBus.getInstance().post(new UnreadEvent(getUnreadTotal()));


            new SystemApiImpl().clientActivityRead(SpMananger.getUUID(), new NetReqCallback() {
                @Override
                public void apiRequestFail(String message, String method) {

                }

                @Override
                public void apiRequestSuccess(Object o, String method) {

                }
            });
        }

    }

    private static void getCommentUnread() {
        new SystemApiImpl().commentUnreadCount(SpMananger.getUUID(), new NetReqCallback<UnreadModel>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(UnreadModel o, String method) {
                COMMENT_UNREAD_NUM = o.getComment_unread_count();
                AppRxBus.getInstance().post(new MineDynamicUnreadEvent(getMineDynamicTotal()));

            }
        });
    }

    public static void clearInvateMeUnread() {
        new InvateMeClearReq().post(new NetReqCallback<Object>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                DataCenter.INVATE_ME_UNREAD_NUM = 0;
                AppRxBus.getInstance().post(new MorePageUnreadEvent(0));
            }
        });

    }

    public static void clearTaskUnread(final String task_id, final int position) {
        new TaskClearUnreadReq().setTask_id(task_id).post(new NetReqCallback<Object>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                AppRxBus.getInstance().post(new AskUnreadEvent(position).setTask_id(task_id));

            }
        });
    }


    private static void getPrettyyesNotify() {
        new OtherApiImpl().getNotityCount(SpMananger.getUUID(), new NetReqCallback<NotifyCount>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(NotifyCount notifyCount, String method) {
                DataCenter.NOTIFY_UNREAD_NUM = notifyCount.getCount();
                AppRxBus.getInstance().post(new UnreadEvent(getUnreadTotal()));
            }
        });
    }


    private static void getActivityUnread() {
        new SystemApiImpl().activityUnreadCount(SpMananger.getUUID(), new NetReqCallback<ActivtyUnreadModel>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(ActivtyUnreadModel activtyUnreadModel, String method) {
                DataCenter.ACTIVITY_UNREAD_NUM = activtyUnreadModel.getActivity_unread();
                AppRxBus.getInstance().post(new UnreadEvent(getUnreadTotal()));

            }
        });
    }


    public static void setRonyuListener() {
        if (RongIM.getInstance() != null) {
            /**
             * 设置接收未读消息的监听器。
             *
             * @param listener          接收未读消息消息的监听器。
             * @param conversationTypes 接收指定会话类型的未读消息数。
             */
            RongIM.getInstance().setOnReceiveUnreadCountChangedListener(new MyReceiveUnreadCountChangedListener(), Conversation.ConversationType.PRIVATE);
            RongIM.getInstance().setOnReceiveUnreadCountChangedListener(new CustomerServiceUnreadCountChangedListener(), Conversation.ConversationType.CUSTOMER_SERVICE);

        }
    }


    private static class MyReceiveUnreadCountChangedListener implements RongIM.OnReceiveUnreadCountChangedListener {


        @Override
        public void onMessageIncreased(int i) {
            Chat_UNREAD_NUM = i;
            AppRxBus.getInstance().post(new UnreadEvent(getUnreadTotal()));

        }

    }

    private static class CustomerServiceUnreadCountChangedListener implements RongIM.OnReceiveUnreadCountChangedListener {

        @Override
        public void onMessageIncreased(int i) {
            KF_UNREAD_NUM = i;
            AppRxBus.getInstance().post(new UnreadEvent(getUnreadTotal()));

        }

    }

    @Override
    public String toString() {
        return "DataCenter{" +
                "time_unlogin=" + time_unlogin +
                ", ishaveShowUnregister=" + ishaveShowUnregister +
                '}';
    }

    public static String source_uid;

    public static void setSource_uid(String source_uid) {
        DataCenter.source_uid = source_uid;
        SpMananger.getStorageProxy().save("source_id", source_uid);
    }

    public static String getSource_uid() {
        if (source_uid != null)
            return source_uid;
        return SpMananger.getStorageProxy().resolve("source_id", String.class);

    }

    public static void clearCommentUnread(String comment_id, NetReqCallback netReqCallback) {
        new ReadCommentRequest().setComment_id(comment_id).post(netReqCallback);
    }
}
