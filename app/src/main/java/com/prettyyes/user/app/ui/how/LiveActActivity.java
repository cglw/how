package com.prettyyes.user.app.ui.how;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.adapter.holder_presenter.KolActEndHoldeViewImpl;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.mvpPresenter.LiveActPresenter;
import com.prettyyes.user.app.mvpPresenter.QuestionEntity;
import com.prettyyes.user.app.mvpView.LiveActView;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.TopShowViewPorxy;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AppTimeEvent;
import com.prettyyes.user.core.event.PostAskSuccessEvent;
import com.prettyyes.user.core.event.ReplyQuestionSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;
import com.prettyyes.user.model.common.ActInfo;
import com.prettyyes.user.model.v8.LiveTaskInfo;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.R.id.tv_liveAct_actname;
import static com.prettyyes.user.core.utils.ClickUtils.isContain;

public class LiveActActivity extends BaseActivity implements LiveActView {
    private static final String TAG = "LiveActActivity";

    @ViewInject(R.id.autoVp_liveAct_sellerinfo)
    private AutoViewPager autoVp_sellerInfo;
    @ViewInject(R.id.swipeRecycleView_liveAct_listInfo)
    private SwipeRecycleView swipeRecycleView;
    @ViewInject(tv_liveAct_actname)
    private TypefaceTextView tv_actname;
    @ViewInject(R.id.tv_liveAct_lefttime)
    private TextView tv_lefttime;
    @ViewInject(R.id.view_liveAct_sellerinfo)
    private View view_seller;
    @ViewInject(R.id.img_liveAct_newmessage)
    private ImageView img_liveAct_newmessage;
    @ViewInject(R.id.rel_ask)
    private View rel_ask;
    @ViewInject(R.id.img_bottom_ic)
    private ImageView img_bottom_ic;
    @ViewInject(R.id.tv_bottom_text)
    private TextView tv_bottom_text;

    @ViewInject(R.id.lin_title)
    private View lin_title;

    @ViewInject(R.id.framelayout_enter)
    private FrameLayout frame_enter;
    @ViewInject(R.id.tv_people_num)
    private TextView tv_people_num;
    @ViewInject(R.id.tv_reply_move)
    private TextView tv_reply_move;

    @ViewInject(R.id.img_share)
    private View img_share;
    @ViewInject(R.id.ll_bottom_do)
    private View ll_bottom_do;
    @ViewInject(R.id.edit_que)
    private EditText edit_que;
    @ViewInject(R.id.img_select_img)
    private ImageView img_select_img;
    @ViewInject(R.id.btn_ask)
    private Button btn_ask;
    @ViewInject(R.id.coordinatorLayout)
    private CoordinatorLayout coordinatorLayout;
    @ViewInject(R.id.ll_act_root)
    private View ll_act_root;

    private long end_time = 0;
    private AbsMutiRvAdapter adapter;

    private TopShowViewPorxy topShowViewPorxy;
    private int act_id;


    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    private ActInfo actInfo;//活动id
    private ArrayList<SellerInfoEntity> sellerInfoEntities;//活动id
    private LiveActPresenter liveActPresenter = new LiveActPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        AppStatistics.onEvent(this, "how_live", "act_id", act_id + "");

    }


    @Override
    public void setSoftModel() {

    }

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            actInfo = intentParmas.getActInfo();
            act_id = actInfo.getAct_id();
            sellerInfoEntities = (ArrayList<SellerInfoEntity>) actInfo.getSeller_list();
            end_time = Long.parseLong(actInfo.getEnd_time());
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_live_act;
    }



    PowerManager.WakeLock mWakeLock;

    @Override
    protected void initViews() {
        hideTitle();
        adapter = new AbsMutiRvAdapter(this);
        swipeRecycleView.setAdapter(adapter);
        swipeRecycleView.getRecycleView().setClipChildren(false);
        tv_actname.setText(actInfo.getAct_name());
        swipeRecycleView.setScrollColor();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "how_tag");
        liveActPresenter.initliveBarrage();
        topShowViewPorxy = new TopShowViewPorxy(coordinatorLayout);

        SoftKeyboardUtil.observeSoftKeyboard(this, new SoftKeyboardUtil.OnSoftKeyboardChangeListener() {
            @Override
            public void onSoftKeyBoardChange(int softKeybardHeight, int top, boolean visible) {
                if (visible)
                    btn_ask.setVisibility(View.VISIBLE);
                else
                    btn_ask.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void setStatusBar() {
        setStateBarColor(Color.WHITE);
    }

    @Override
    protected void loadData() {

        liveActPresenter.setLiveState();
        liveActPresenter.setSellerData();
        swipeRecycleView.loadingFistEnter();


        new KolActEndHoldeViewImpl(new MutiTypeViewHolder(getRootView()) {
            @Override
            public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

            }

            @Override
            public void bindView() {

            }
        }).setRecyclerView(swipeRecycleView.getRecycleView()).setData(actInfo);

    }



    @Override
    protected void setListener() {

        tv_actname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolActActivity(getThis(), actInfo.getAct_id());
            }
        });
        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actInfo != null && actInfo.getBegin_share_model() != null) {
                    ShareModel begin_share_model = actInfo.getBegin_share_model();
                    begin_share_model.setShareTypeImage();
                    new ShareHandler(getThis()).setRes(begin_share_model, new ShareHandler.ShareCallback() {
                        @Override
                        public void share(boolean issuccess) {

                        }
                    }).shareAtWindow(getRootView());
                }
            }
        });

        if (TimeManager.getManager().getServer_time() <= end_time) {
            swipeRecycleView.setFootviewShow(false);
            swipeRecycleView.setListener(new SwipeRecycleView.LoadTopCallback() {
                @Override
                public void loadtopList(int page) {
                    liveActPresenter.loadHistory(page, true);
                }
            });

        } else {
            swipeRecycleView.setListener(new SwipeRecycleView.LoadCallback() {
                @Override
                public void loadList(int page) {
                    liveActPresenter.loadHistory(page, false);
                }
            });
        }
        rel_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actInfo.getAct_id() == 0) {
                    showToastShort("未获取到活动信息");
                    return;
                }
                if (TimeManager.getManager().getServer_time() > end_time) {
                    showToastShort("不在活动时段");
                    return;
                }
                QuestionEntity questionEntity = new QuestionEntity();
                questionEntity.setAct_id(actInfo.getAct_id() + "");
                questionEntity.setHash_tag(actInfo.getHash_tag());
                AskActivity.goAskActivity(getThis(), questionEntity, getSellers());

            }
        });

        img_liveAct_newmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveActPresenter.goBottom();
            }
        });

        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AppTimeEvent>() {
            @Override
            protected void back(AppTimeEvent appTimeEvent) {
                liveActPresenter.checkKolActivity(appTimeEvent);
            }
        }, new RxCallback<PostAskSuccessEvent>() {
            @Override
            protected void back(PostAskSuccessEvent obj) {
                liveActPresenter.goBottom();
            }
        }, new RxCallback<ReplyQuestionSuccessEvent>() {
            @Override
            protected void back(ReplyQuestionSuccessEvent replyQuestionSuccessEvent) {
                liveActPresenter.goBottom();
                for (int i = adapter.getItemCount() - 1; i >= 0; i--) {
                    LiveTaskInfo itemData = (LiveTaskInfo) adapter.getItemData(i);
                    if (itemData.getTask_id().equals(replyQuestionSuccessEvent.getTask_id())) {
                        itemData.setMy_answer_replyed();
                        adapter.notifyItemChanged(i);
                    }
                }
                for (int i = adapter.getItemCount() - 1; i >= 0; i--) {
                    LiveTaskInfo itemData = (LiveTaskInfo) adapter.getItemData(i);

                }

            }
        });

        img_select_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveActPresenter.selectImgs();
            }
        });
        img_select_img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                liveActPresenter.deleteImgs();
                return true;
            }
        });
        btn_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                liveActPresenter.ask();
            }
        });


    }


    @Override
    public ActInfo getAct() {
        return actInfo;
    }

    @Override
    public TextView getLefttimeShow() {
        return tv_lefttime;
    }

    @Override
    public ArrayList<SellerInfoEntity> getSellers() {
        for (int i = 0; i < sellerInfoEntities.size(); i++) {
            sellerInfoEntities.get(i).setId(Integer.parseInt(sellerInfoEntities.get(i).getSeller_id()));
        }
        return sellerInfoEntities;
    }

    @Override
    public AutoViewPager getSellerVp() {
        return autoVp_sellerInfo;
    }

    @Override
    public SwipeRecycleView getLv() {
        return swipeRecycleView;
    }

    @Override
    public AbsMutiRvAdapter getAdapter() {
        return adapter;
    }


    @Override
    public View getSellerView() {
        return view_seller;
    }

    @Override
    public FrameLayout getEnterViewGroup() {
        return frame_enter;
    }

    @Override
    public TextView getReplyMe() {
        return tv_reply_move;
    }


    @Override
    public View getNewMessageView() {
        return img_liveAct_newmessage;
    }

    @Override
    public void showBottomEnd() {
        rel_ask.setVisibility(View.VISIBLE);
        rel_ask.setBackgroundResource(R.drawable.bg_round_black_round100);
        img_bottom_ic.setVisibility(View.VISIBLE);
        tv_bottom_text.setText("专场已结束");
        ll_bottom_do.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showBottomStart() {
        rel_ask.setVisibility(View.VISIBLE);

        rel_ask.setBackgroundResource(R.drawable.bg_round_red_round100);
        img_bottom_ic.setVisibility(View.VISIBLE);
        tv_bottom_text.setText("我要问");
        ll_bottom_do.setVisibility(View.INVISIBLE);

    }

    @Override
    public void handSoft(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isContain(btn_ask, ev.getRawX(), ev.getRawY()) || isContain(img_select_img, ev.getRawX(), ev.getRawY()))
                return;
            View currentFocus = getCurrentFocus();
            if (isHideInput(currentFocus, ev)) {
                HideSoftInput(currentFocus.getWindowToken());
            }
        }
    }


    @Override
    public long getEndTime() {
        return end_time;
    }

    @Override
    public void setCurrentCount(String tv) {
        tv_people_num.setText(tv);
    }

    @Override
    public String getAskQue() {
        return edit_que.getText().toString();
    }

    @Override
    public void setAskSelectImgs(List<String> imgs) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img_select_img.getLayoutParams();

        if (imgs != null && imgs.size() > 0) {
            ImageLoadUtils.loadListimg(this, imgs.get(0), img_select_img);
            layoutParams.width = AppUtil.dip2px(25);
            layoutParams.height = AppUtil.dip2px(25);
        } else {
            img_select_img.setImageResource(R.mipmap.image);
            layoutParams.width = AppUtil.dip2px(17);
            layoutParams.height = AppUtil.dip2px(17);
        }
        img_select_img.setLayoutParams(layoutParams);


    }

    @Override
    public TopShowViewPorxy getTopShowView() {
        return topShowViewPorxy;
    }

    @Override
    public void setAskQue(String text) {
        edit_que.setText(text);
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        super.setInentFliter(inentFliter);
        inentFliter.addAction(Constant.OrderPaySuccess);
        inentFliter.addAction(Constant.PUPOPDISMISS);
        inentFliter.addAction(Constant.PUPOPSHOW);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
        super.handlerIntenter(context, intent);
        if (intent.getAction().contains(Constant.PUPOPSHOW)) {
            liveActPresenter.stop();
        } else if (intent.getAction().equals(Constant.PUPOPDISMISS)) {
            liveActPresenter.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (liveActPresenter != null)
            liveActPresenter.release_res();
        if (autoVp_sellerInfo != null)
            autoVp_sellerInfo.clearSelf();
        if (swipeRecycleView != null)
            swipeRecycleView.clearSelf();
        if (topShowViewPorxy != null)
            topShowViewPorxy.release();
        SoftKeyboardUtil.observeSoftKeyboardDestopry(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (liveActPresenter != null) {
            liveActPresenter.stop();
        }
        if (mWakeLock != null) {
            mWakeLock.release();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        TimeManager.getManager().syncServerTime();
        AppStatistics.onPageStart(this, "HowLive_" + act_id);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (liveActPresenter != null) {
            liveActPresenter.start();
        }
        if (mWakeLock != null)
            mWakeLock.acquire();

        if (tv_actname != null) {
            tv_actname.setFocusable(true);
            tv_actname.requestFocus();
            tv_actname.setFocusableInTouchMode(true);
        }
        AppStatistics.onPageEnd(this, "HowLive_" + act_id);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        liveActPresenter.onActivityResult(requestCode, resultCode, data);
    }
}