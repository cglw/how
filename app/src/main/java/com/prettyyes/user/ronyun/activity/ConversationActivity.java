package com.prettyyes.user.ronyun.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.app.base.BaseRongyunActivity;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.databaseModel.RongyunUserInfo;
import com.prettyyes.user.model.user.UserInfolist;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;

import java.util.Locale;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

/**
 * Created by Bob on 15/8/18.
 * 会话页面
 */
public class ConversationActivity extends BaseRongyunActivity {

    private static final String TAG = "ConversationActivity";

    private String mTargetId;

    @Override
    public void setSoftModel() {

    }

    /**
     * 刚刚创建完讨论组后获得讨论组的id 为targetIds，需要根据 为targetIds 获取 targetId
     */
    private String mTargetIds;

    @ViewInject(R.id.view_conversation_sku)
    private LinearLayout view_conversation_sku;
    @ViewInject(R.id.img_conversation_covery)
    private ImageView img_covery;
    @ViewInject(R.id.tv_conversation_price)
    private TextView tv_price;
    @ViewInject(R.id.tv_conversation_uintname)
    private TextView tv_uitname;
    @ViewInject(R.id.tv_conversation_send)
    private TextView tv_send;

    @ViewInject(R.id.view_conversation_order_layout)
    private View view_order_layout;
    @ViewInject(R.id.tv_conversation_order)
    private TextView tv_ordernumber;
    @ViewInject(R.id.tv_conversation_send_ordernumber)
    private TextView tv_ordernumber_send;


    public SpuInfoEntity spuInfoEntity;
    private String order_id;

    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;


    @Override
    protected int bindLayout() {
        return R.layout.rongyun_conversation;
    }

    @Override
    protected void initViews() {
        setActivtytitle("聊天");
        showBack();

    }


    @Override
    protected void loadData() {
        if (getUserInfo() == null) {
            showToastShort("未获取到聊天账号，请尝试重新登录");
            finish();
            return;
        } else {
            if (SpMananger.getChat_id() == null) {
                showToastShort("未获取到聊天账号，请尝试重新登录");
                finish();
                return;
            }
        }
        Intent intent = getIntent();
        getIntentDate(intent);
        isReconnect(intent);
        initReceiveView();
        initMe();
        getData();
        RongyunUserInfo rongyunUserInfo = new RongyunUserInfo();

        rongyunUserInfo.setUser_id(SpMananger.getChat_id());
        rongyunUserInfo.setNickname(getUserInfo().getNickname());
        rongyunUserInfo.setPortraitUri(getUserInfo().getHeadimg());
        try {
            rongyunUserInfo.save(rongyunUserInfo);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void initReceiveView() {
        if (spuInfoEntity == null) {
            view_conversation_sku.setVisibility(View.GONE);
        } else {
            view_conversation_sku.setVisibility(View.VISIBLE);
            ImageLoadUtils.loadListimg(this, spuInfoEntity.getMain_img(), img_covery);
            tv_price.setText(StringUtils.getPrice(spuInfoEntity.getSpu_price()));
            tv_uitname.setText(spuInfoEntity.getSpu_name());

            if (!StringUtils.strIsEmpty(order_id)) {
                view_order_layout.setVisibility(View.VISIBLE);
                tv_ordernumber.setText(String.format("我订单号码为%s，这个订单我想要退款。", order_id));
            }
        }

    }

    private void initMe() {
        if (SpMananger.getChat_id() == null) {
            showToastShort("未获取到用户聊天账号");
            this.finish();
        }
        userInfo_Me = new UserInfo(SpMananger.getChat_id(), SpMananger.getUserInfo().getNickname(), Uri.parse(StringUtils.getHeadImgByServer(SpMananger.getUserInfo().getHeadimg())));
        RongIM.getInstance().refreshUserInfoCache(userInfo_Me);
    }


    private void getData() {
        findUserById(mTargetId);
    }

    private UserInfo userInfo_Me;
    private UserInfo userInfo_He;


    @Override
    protected void setListener() {
        super.setListener();
        RongIM.setConversationBehaviorListener(new MyConversationBehaviorListener());
        view_conversation_sku.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spuInfoEntity != null) {
                    JumpPageManager.getManager().goSkuActivity(ConversationActivity.this, spuInfoEntity.getSpu_type(), spuInfoEntity.getModule_id());
                }

            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtils.isFastDoubleClick()) {
                    return;
                }
                if (spuInfoEntity != null && spuInfoEntity.getShare_model() != null) {
                    String targetUrl = spuInfoEntity.getShare_model().getTargetUrl();

                    if (StringUtils.strIsEmpty(targetUrl)) {
                        targetUrl = AppConfig.BaseUrl;
                    }
                    sendMessage(String.format("%s?&spu_type=%s&module_id=%s", targetUrl, spuInfoEntity.getSpu_type(), spuInfoEntity.getModule_id()));


                }

            }
        });
        tv_ordernumber_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtils.isFastDoubleClick()) {
                    return;
                }
                sendMessage(String.format("我订单号码为%s，这个订单我想要退款。", order_id));

            }
        });
    }

    public void sendMessage(String msg) {
        TextMessage myTextMessage = TextMessage.obtain(msg);
        Message myMessage = Message.obtain(mTargetId, Conversation.ConversationType.PRIVATE, myTextMessage);
        RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMediaMessageCallback() {
            @Override
            public void onProgress(Message message, int i) {

            }

            @Override
            public void onCanceled(Message message) {

            }

            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {

            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    private void findUserById(final String userId) {
        new UserApiImpl().UserInfoList(SpMananger.getUserInfo().getUuid(), userId, new NetReqCallback<UserInfolist>() {
            @Override
            public void apiRequestFail(String message, String method) {
            }

            @Override
            public void apiRequestSuccess(UserInfolist userInfolist, String method) {
                String headimg = userInfolist.getList().get(0).getHeadimg();
                StringUtils.getHeadImgByServer(headimg);
                userInfo_He = new UserInfo(userId, userInfolist.getList().get(0).getNickname(), Uri.parse(headimg));
                RongIM.getInstance().refreshUserInfoCache(userInfo_He);

                setActivtytitle(userInfolist.getList().get(0).getNickname());
                try {

                    RongyunUserInfo rongyunUserInfo = new RongyunUserInfo();
                    rongyunUserInfo.setUser_id(userId);
                    rongyunUserInfo.setNickname(userInfolist.getList().get(0).getNickname());
                    rongyunUserInfo.setPortraitUri(headimg);
                    rongyunUserInfo.save(rongyunUserInfo);
                } catch (DbException e) {
                    e.printStackTrace();
                }


                //    initHeader();


            }
        });

    }


    /**
     * 展示如何从 Intent 中得到 融云会话页面传递的 Uri
     */
    private void getIntentDate(Intent intent) {
        mTargetId = intent.getData().getQueryParameter("targetId");
        if (mTargetId.equals(AppConfig.KF)) {
            setActivtytitle(getString(R.string.kf));
            UserInfo kf = new UserInfo(AppConfig.KF, "How客服", Uri.parse(AppConfig.KF_HEAD_IMG));
            RongIM.getInstance().refreshUserInfoCache(kf);
        }

        mTargetIds = intent.getData().getQueryParameter("targetIds");
        order_id = intent.getData().getQueryParameter("order_id");
        String data = intent.getData().getQueryParameter("data");
        if (data != null)
            spuInfoEntity = GsonHelper.getGson().fromJson(data, SpuInfoEntity.class);
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
        enterFragment(mConversationType, mTargetId);


    }


    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType
     * @param mTargetId
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {


        ConversationFragment fragment = new ConversationFragmentEx();

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId)
                .build();

        fragment.setUri(uri);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //xxx 为你要加载的 id
        transaction.add(R.id.conversation, fragment);
        transaction.commitAllowingStateLoss();

    }


    /**
     * 判断消息是否是 push 消息
     */
    private void isReconnect(Intent intent) {
        String token = null;

        if (getUserInfo() != null && getUserInfo().rongyun_token != null) {
            token = getUserInfo().rongyun_token.getRongyun_buyer();
        } else {
            showToastShort("未获取到用户Token");
            this.finish();
            return;
        }

        //push或通知过来
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
                    enterFragment(mConversationType, mTargetId);
                }
            }
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

                    enterFragment(mConversationType, mTargetId);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

    private class MyConversationBehaviorListener implements RongIM.ConversationBehaviorListener {

        @Override
        public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
            return false;
        }

        @Override
        public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
            return false;
        }

        @Override
        public boolean onMessageClick(Context context, View view, Message message) {
            return false;
        }

        @Override
        public boolean onMessageLinkClick(Context context, String s) {
            LogUtil.i(TAG, s + "-->");


            Map<String, String> result = StringUtils.URLRequest(s);

            String module_id = result.get("module_id");
            String spu_type = result.get("spu_type");
            JumpPageManager.getManager().goSkuActivity(ConversationActivity.this, spu_type, module_id);
            return true;


        }

        @Override
        public boolean onMessageLongClick(Context context, View view, Message message) {
            return false;
        }
    }

}
