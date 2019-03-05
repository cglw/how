package com.prettyyes.user.app.mvpPresenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.prettyyes.user.AppConfig;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.api.netXutils.DesUtils;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.api.netXutils.requests.TaskAddRequest;
import com.prettyyes.user.app.adapter.LiveSellerInfoAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.mvpView.LiveActView;
import com.prettyyes.user.app.ui.appview.model.HowLocalMedia;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.SelectMediaHandler;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AppTimeEvent;
import com.prettyyes.user.core.event.PostAskSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.AlertMessageResponse;
import com.prettyyes.user.model.v8.LiveBarrageEntity;
import com.prettyyes.user.model.v8.LiveTaskInfo;
import com.prettyyes.user.model.v8.QuestionEntity;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;
import static com.prettyyes.user.app.account.Constant.SOCKET_TYPE_ANSWER_TASK_VOTE;
import static com.prettyyes.user.app.account.Constant.SOCKET_TYPE_ANSWER_VOTE;
import static com.prettyyes.user.app.account.Constant.SOCKET_TYPE_KOL_LOGIN;
import static com.prettyyes.user.app.account.Constant.SOCKET_TYPE_MY_ANSWER_TASK;

/**
 * Created by chengang on 2017/2/27.
 */

public class LiveActPresenter {
    private static final String TAG = "LiveActPresenter";
    private WebSocketClient client;// 连接客户端
    private boolean CONNECT_SUCCESS = false;
    private int CONNECT_COUNT = 0;
    private boolean isConnecting = false;
    private LiveActView liveActView;
    private TaskImpl task;
    private BaseActivity activity;
    private int act_id = 0;
    private String atUsers = "";
    private RecyclerView recyclerView;
    private ArrayList<String> datas_enter = new ArrayList<>();
    private LiveBarrage liveBarrage;

    public String getAtUsers() {

        return atUsers;
    }

    private long enter_time = 0;
    private SelectMediaHandler selectMediaHandler;


    public LiveActPresenter(LiveActView liveActView) {
        this.liveActView = liveActView;
        this.activity = liveActView.getThis();
        task = new TaskImpl();
        enter_time = TimeManager.getManager().getServer_time();


    }

    public void initliveBarrage() {
        liveBarrage = new LiveBarrage(activity, liveActView.getEnterViewGroup());
        liveBarrage.setMove_view(liveActView.getReplyMe());
    }

    private void onPause() {
        if (liveBarrage != null)
            liveBarrage.onPause();


    }

    private void onResume() {
        if (liveBarrage != null)
            liveBarrage.onResume();

    }

    boolean isfirst = true;

    public void loadHistory(final int page, final boolean dir) {
        task.tasktaskActHistory(activity.getUUId(), liveActView.getAct().getAct_id(), enter_time + "", page, new NetReqCallback<List<LiveTaskInfo>>() {
            @Override
            public void apiRequestFail(String message, String method) {
                liveActView.showFailedError(message);
                liveActView.getLv().loadingfail();
            }

            @Override
            public void apiRequestSuccess(List<LiveTaskInfo> taskHistory, String method) {
                liveActView.getLv().loadingSuccessHavedata();

                if (dir) {
                    if (taskHistory.size() < MIN_PAGE_SIZE) {
                        if (!isfirst) {
                            liveActView.showFailedError("已无更多");
                            liveActView.getLv().loadingEnd();
                            liveActView.getLv().getSwpie().setEnabled(false);
                        }
                    }
                    for (int i = 0; i < taskHistory.size(); i++) {
                        liveActView.getAdapter().add(0, taskHistory.get(i));
                    }

                    if (page == 1) {
                        //第一次进来的时候滚动到底部
                        recyclerView.scrollToPosition(taskHistory.size() - 1);
                    } else {
                        //恢复数据位置，因为倒着加数据的
                        (recyclerView.getLayoutManager()).scrollToPosition(taskHistory.size());
                    }
                    isfirst = false;


                } else {
                    LogUtil.i(TAG, "liveActView.getAdapter()" + taskHistory.size());
                    liveActView.getAdapter().addAll(taskHistory);
                    if (taskHistory.size() < MIN_PAGE_SIZE) {
                        liveActView.getLv().loadingEnd();
                    }

                }
            }
        });
    }


    public void setLiveState() {

        act_id = liveActView.getAct().getAct_id();

        this.recyclerView = liveActView.getLv().getRecycleView();
        if (TimeManager.getManager().getServer_time() <= liveActView.getEndTime()) {
            initRecycleView();
            connect();


        } else {
            liveActView.getLv().getSwpie().setEnabled(true);
            initRecycleView();
        }
        startEnter();

    }

    private void startEnter() {
        handler.sendEmptyMessage(3);
    }

    public void setSellerData() {
        setVp(liveActView.getSellers(), liveActView.getAct().getHash_tag());
    }

    AutoViewPager vp;

    private void setVp(ArrayList<SellerInfoEntity> data, String act_name) {
        atUsers = "";
        for (int i = 0; i < data.size(); i++) {
            atUsers += data.get(i).getId() + ";";
        }

        LiveSellerInfoAdapter liveSellerInfoAdapter = new LiveSellerInfoAdapter(activity);
        liveSellerInfoAdapter.setActInfo(act_id, act_name, TimeManager.getManager().getServer_time() <= liveActView.getEndTime());
        vp = liveActView.getSellerVp();
        vp.setIsneedIndict(false);
        liveSellerInfoAdapter.addAll(data);
        vp.setAbsVpAdapter(liveSellerInfoAdapter);
    }


    public void reconnect() {
        if (!close && !isConnecting && !CONNECT_SUCCESS && AppUtil.isNetworkAvailable(BaseApplication.getAppContext())) {
            handler.sendEmptyMessage(1);
            isConnecting = true;
        }
    }

    public void checkKolActivity(AppTimeEvent appTimeEvent) {
        reconnect();
        long left_time = liveActView.getEndTime() - appTimeEvent.getServer_time();
        liveActView.showBottomStart();
        if (left_time > 300) {
            liveActView.getLefttimeShow().setText("LIVE");
            if (liveActView.getSellerView().getVisibility() == View.GONE) {
                liveActView.getSellerView().setVisibility(View.VISIBLE);
            }
        } else if (left_time >= 0 && left_time <= 300) {
            int min = (int) (left_time / 60);
            int s = (int) (left_time % 60);
            if (s >= 10)
                liveActView.getLefttimeShow().setText("活动倒计时： " + min + ":" + s);
            else
                liveActView.getLefttimeShow().setText("活动倒计时： " + min + ":0" + s);
        } else if (left_time < 0) {
            liveActView.getLefttimeShow().setText("活动已结束");
            release_res();
            if (liveActView.getSellerView().getVisibility() == View.VISIBLE) {
                liveActView.getSellerView().setVisibility(View.GONE);
            }
            liveActView.showBottomEnd();

        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (CONNECT_COUNT <= 5 && !CONNECT_SUCCESS) {
                    CONNECT_COUNT++;
                    connect();
                }
            } else if (msg.what == 3) {
                //进入的人
                handlerEnterPerson();
            }

        }
    };

    public void connect() {
        try {
            client = new WebSocketClient(new URI(AppConfig.SOCKET_ADDRESS), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CONNECT_COUNT = 0;
                            CONNECT_SUCCESS = true;
                            isConnecting = false;
                            postLogin();
                            LogUtil.i(TAG, "WebSocketClient-->Success");

                        }
                    });
                }

                @Override
                public void onMessage(final String message) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.i(TAG, "handlerReceiveMessage" + message);

                            handlerReceiveMessage(message);

                        }
                    });
                }

                @Override
                public void onClose(final int code, final String reason, boolean remote) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isConnecting = false;
                            CONNECT_SUCCESS = false;
                            //1000是自己主动断开
                            if (code != 1000) {
                                reconnect();
                            }
                            LogUtil.i(TAG, "--?onClose" + reason + "-->" + code);


                        }
                    });
                }

                @Override
                public void onError(final Exception ex) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isConnecting = false;
                            CONNECT_SUCCESS = false;
                            LogUtil.i(TAG, "onError" + ex.getMessage());

                        }
                    });
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        client.connect();
    }

    private void postLogin() {
        if (client != null && CONNECT_SUCCESS) {
            Map<String, String> a = new HashMap();
            a.put("type", "login");
            a.put("act_id", act_id + "");
            a.put("uuid", activity.getUUId());
            JSONObject js = new JSONObject(a);
            try {
                client.send(DesUtils.encryptionByDes(js.toString()));
            } catch (Exception ee) {

            }
        }
    }

    private void handlerReceiveMessage(String message) {
        try {
            JSONObject jsonobject = new JSONObject(message);
            switch (jsonobject.optString("type")) {
                case "login":
                    break;
                case "kol_answer":
                    String json = jsonobject.optString("extra");
                    getAnswer(json);
                    break;
                case "kol_login":
                    JSONObject json_kol_login = jsonobject.optJSONObject("extra");
                    String nickname = json_kol_login.optString("nick_name");
                    String group_count = json_kol_login.optString("group_count");
                    datas_enter.add(nickname + "进入专场");
                    liveActView.setCurrentCount(group_count);
                    liveBarrage.addLiveBarrageEntity(createLiveBarrage(SOCKET_TYPE_KOL_LOGIN, nickname));

                    break;
                case "answer_vote":

                    json_to_que(jsonobject, SOCKET_TYPE_ANSWER_VOTE);

                    break;
                case "answer_task_vote":
                    json_to_que(jsonobject, SOCKET_TYPE_ANSWER_TASK_VOTE);
                    break;
                case "my_answer_task":
                    json_to_que(jsonobject, SOCKET_TYPE_MY_ANSWER_TASK);

                    break;
                case "ping":
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private QuestionEntity json_to_que(JSONObject jsonObject, String type) {
        String json = jsonObject.optString("extra");
        QuestionEntity questionEntity = GsonHelper.getGson().fromJson(json, QuestionEntity.class);
        LiveBarrageEntity liveBarrage_entity = createLiveBarrage(type, questionEntity.getAnswer_user_nickname());
        liveBarrage_entity.setTask_id(questionEntity.getTask_id());
        liveBarrage_entity.setQue(questionEntity.getDesc());
        liveBarrage_entity.setAnswer_id(questionEntity.getAnswer_id());

        if (type.equals(SOCKET_TYPE_MY_ANSWER_TASK)) {

            this.liveBarrage.addReplyMeData(liveBarrage_entity);
        } else
            this.liveBarrage.addLiveBarrageEntity(liveBarrage_entity);
        return questionEntity;

    }


    private LiveBarrageEntity createLiveBarrage(String type, String show_txt) {
        LiveBarrageEntity liveBarrageEntity = new LiveBarrageEntity();
        liveBarrageEntity.setShow_txt(show_txt);
        liveBarrageEntity.setType(type);
        return liveBarrageEntity;
    }

    private int lastPosition = 0;
    private int lastOffset = 0;

    private void getAnswer(String json) {


        if (recyclerView.getLayoutManager() != null) {
            View topView = recyclerView.getLayoutManager().getChildAt(0);
            if (topView != null) {
                lastOffset = topView.getTop();                                   //获取与该view的顶部的偏移量
                lastPosition = recyclerView.getLayoutManager().getPosition(topView);  //得到该View的数组位置
            }
        }


        LiveTaskInfo datas = GsonHelper.getGson().fromJson(json, LiveTaskInfo.class);
        datas.setPush_time(TimeManager.getManager().getServer_time());


        if (recyclerView.canScrollVertically(1) || isEnterOtherActivity) {
            liveActView.getNewMessageView().setVisibility(View.VISIBLE);
            liveActView.getAdapter().add(datas);

        } else {
            liveActView.getNewMessageView().setVisibility(View.GONE);
            liveActView.getAdapter().add(datas);
            recyclerView.smoothScrollToPosition(liveActView.getAdapter().getDataCount() - 1);

            recyclerView.smoothScrollBy(0, 20);

//            do {
//            }
//            while (recyclerView.canScrollVertically(1));

        }
    }

    public void goBottom() {
        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPosition(liveActView.getAdapter().getDataCount() - 1);
        liveActView.getNewMessageView().setVisibility(View.GONE);

    }


    public void initRecycleView() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    liveActView.getNewMessageView().setVisibility(View.GONE);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }


    public void backtoTop() {
        recyclerView.scrollToPosition(0);
    }


    private void handlerEnterPerson() {

    }

    public void release_res() {

        if (vp != null)
            vp.stop();

        if (handler != null) {
            handler.removeMessages(1);
            handler.removeMessages(3);
        }
        if (client != null) {
            client.getConnection().close();
            client.close();
            close = true;
        }
        client = null;
        if (selectMediaHandler != null)
            selectMediaHandler.release();


    }

    private boolean close = false;

    private boolean isEnterOtherActivity = false;

    public void stop() {
        if (vp != null)
            vp.stop();
        isEnterOtherActivity = true;
        onPause();


    }

    public void start() {
        vp.start();
        isEnterOtherActivity = false;
        onResume();
    }


    public void ask() {
        if (JumpPageManager.getManager().checkUnlogin(activity))
            return;
        if (liveActView.getAskQue() == null || liveActView.getAskQue().length() <= 0) {
            AppUtil.showToastShort("发问不允许为空");
            return;
        }


        try {
            new TaskAddRequest().setDesc(liveActView.getAskQue()).setAct_id(act_id + "")
                    .setPic_list(getSelectMediaHandler()
                            .getImages())
                    .setAt_users(getAtUsers()).post(new NetReqCallback<Object>() {
                @Override
                public void apiRequestFail(String message, String method) {
                    AppUtil.showToastShort(message);
                }

                @Override
                public void apiRequestSuccess(Object s, String method) {

                    AlertMessageResponse.isNeedShow(s, DataCenter.CouponGetType.ASK);
                    AppUtil.showToastShort("发问上传成功");
                    AppRxBus.getInstance().postDely(new PostAskSuccessEvent());
                    selectMediaHandler.reSet();
                    getSelectList().clear();
                    liveActView.setAskSelectImgs(null);
                    liveActView.setAskQue("");
                    SoftKeyboardUtil.closeInputMethod(activity, activity.getRootView());
                }
            });
        } catch (Exception e) {
            AppUtil.showToastShort(e.getMessage());
            e.printStackTrace();
        }


    }

    List<LocalMedia> selectList;

    public List<LocalMedia> getSelectList() {
        if (selectList == null)
            selectList = new ArrayList<>();
        return selectList;
    }

    public SelectMediaHandler getSelectMediaHandler() {
        if (selectMediaHandler == null)
            selectMediaHandler = SelectMediaHandler.create(activity).setMax(4).setChooseMode(PictureMimeType.ofImage()).setTopShowViewPorxy(liveActView.getTopShowView());
        return selectMediaHandler;
    }

    public void selectImgs() {
        getSelectMediaHandler().config(getSelectList());
        getSelectMediaHandler().start();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        getSelectMediaHandler().onActivityResultAutoUpload(requestCode, resultCode, data, new SelectMediaHandler.SelectCallback() {
            @Override
            public void success(List<HowLocalMedia> selectList, List<LocalMedia> from, List<HowLocalMedia> last, List<String> paths) {
                LiveActPresenter.this.selectList = from;
                int count = 0;
                for (int i = 0; i < selectList.size(); i++) {
                    for (int j = 0; j < last.size(); j++) {
                        if (selectList.get(i).getPath().contains(last.get(j).getPath())) {
                            count++;
                            break;
                        }
                    }

                }
                if (count < selectList.size()) {
                    liveActView.getTopShowView().showTextNoHide("正在上传图片...");
                }

                liveActView.setAskSelectImgs(paths);


            }
        });
    }

    public void deleteImgs() {
        if (getSelectList().size() > 0)
            DialogHelper.getInstance().getDialogNoCancel("确认删除图片", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getSelectList().clear();
                    getSelectMediaHandler().release();
                    getSelectMediaHandler().reSet();
                    liveActView.setAskSelectImgs(null);
                    liveActView.getTopShowView().showText("删除成功");

                }
            }).show();
    }


}
