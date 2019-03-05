package com.prettyyes.user.app.mvpPresenter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.LiveBarrageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/9/20.
 */

public class LiveBarrage {
    private static final String TAG = "LiveBarrage";
    private List<View> data_view;
    private Context context;
    private final static int tag_view = 2 << 24;
    private final static int tag_data = 3 << 24;
    View target;
    FrameLayout danmakuContainer;
    private List<LiveBarrageEntity> data_str;
    private List<LiveBarrageEntity> data_reply_me;
    private TextView move_view;

    public void setMove_view(TextView move_view) {
        this.move_view = move_view;
    }

    public LiveBarrage(Context context, FrameLayout danmakuContainer) {
        this.context = context;
        this.danmakuContainer = danmakuContainer;
        data_str = new ArrayList<>();
        data_view = new ArrayList<>();
        data_reply_me = new ArrayList<>();
    }

    private boolean isStop=true;

    public void onPause() {
        isStop=true;
        data_str.clear();

    }

    public void onResume() {
        isStop=false;
    }

    public void addLiveBarrageEntity(LiveBarrageEntity liveBarrageEntity) {
        if(isStop)
            return;
        data_str.add(liveBarrageEntity);
        boolean show = false;
        for (int i = 0; i < data_view.size(); i++) {
            if (data_view.get(i).getVisibility() == View.VISIBLE) {
                show = true;
                break;
            }
        }
        if (!show) {
            addContentToDanmaku(data_str.get(0));
        }
    }


    private void end(final View view) {
        /**
         * view 消失时，view自身的动画效果
         */
        PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f);
        PropertyValuesHolder pvhY1 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f);
        PropertyValuesHolder pvhZ1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        ObjectAnimator multAnim1 = ObjectAnimator.ofPropertyValuesHolder(view, pvhX1, pvhY1, pvhZ1)
                .setDuration(500);
        multAnim1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setPivotX(0f);
                view.setPivotY(0f);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        multAnim1.start();
    }

    public void startEnter() {
        if (handler != null)
            handler.sendEmptyMessage(3);
    }

    private void start(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        ObjectAnimator multAnim = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000);
        multAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                View view = (View) ((ObjectAnimator) animation).getTarget();  //使用动画监听器，主要是为了处理缩放的中心点修改到自己想要的位置
                view.setPivotX(0f);
                view.setPivotY(AppUtil.dip2px(22));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (data_str.size() > 0) {
                    data_str.remove(0);
                }
                handlerEnterPerson();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });

        multAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                for (int j = 0; j < danmakuContainer.getChildCount(); j++) {
                    View childAt = danmakuContainer.getChildAt(j);
                    if (childAt.getTag(tag_view) != null) {
                        childAt.setTag(tag_view, null);
                        target = childAt;
                        continue;
                    }
                    if (childAt != target && childAt != null) {
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                        if (value == 0) {
                            childAt.setTag(layoutParams.bottomMargin);
                        }
                        int bottom_margin = 0;
                        if (childAt.getTag() != null)
                            bottom_margin = (int) childAt.getTag();
                        layoutParams.setMargins(0, layoutParams.topMargin, 0, (int) ((AppUtil.dip2px(22 + 4) * (value)) + bottom_margin));
                        childAt.setLayoutParams(layoutParams);
                    }
                }


            }
        });
        multAnim.start();


    }


    /**
     * 发送弹幕内容到界面上
     *
     * @param
     */
    private void addContentToDanmaku(LiveBarrageEntity liveBarrageEntity) {

        if (data_view.size() < 3) {
            TextView inputView2 = (TextView) createDanmakuView();               //创建弹幕的背景及内容
            inputView2.setTag(tag_view, true);
            setViewTextShow(inputView2, liveBarrageEntity);
            inputView2.setVisibility(View.VISIBLE);
            FrameLayout.LayoutParams inputLP2 = createDanmakuLP();      //自定义每一条弹幕的margin值
            danmakuContainer.addView(inputView2, inputLP2);
            data_view.add(inputView2);
            start(inputView2);
        } else {
            TextView inputView2 = (TextView) data_view.get(0);
            setViewTextShow(inputView2, liveBarrageEntity);
            inputView2.setVisibility(View.VISIBLE);
            inputView2.setTag(tag_view, true);
            inputView2.setLayoutParams(createDanmakuLP());
            data_view.remove(0);
            data_view.add(inputView2);
            start(inputView2);
        }
        handler.sendEmptyMessageDelayed(1, 2000);


        //5秒后自动删除自己这条弹幕

    }

    private void handlerEnterPerson() {
        if (data_str.size() > 0) {
            addContentToDanmaku(data_str.get(0));
        }
    }

    public void setViewTextShow(TextView tv, LiveBarrageEntity liveBarrageEntity) {
        String show_txt = liveBarrageEntity.getShow_txt();
        Drawable drawable = null;
        tv.setTag(tag_data, liveBarrageEntity);
        switch (liveBarrageEntity.getType()) {
            case Constant.SOCKET_TYPE_KOL_LOGIN:
                StringUtils.addIndexStyleSpan(tv, String.format("用户 %s 进入专场", show_txt), 3, 3 + show_txt.length());
                drawable = context.getResources().getDrawable(R.mipmap
                        .avatar_ask_status);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                break;
            case Constant.SOCKET_TYPE_ANSWER_TASK_VOTE:
                StringUtils.addIndexStyleSpan(tv, String.format("%s 赞同了你的问题", show_txt), 0, show_txt.length());
                drawable = context.getResources().getDrawable(R.mipmap
                        .zan);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LiveBarrageEntity tag = (LiveBarrageEntity) v.getTag(tag_data);
                        if (tag == null)
                            return;


                        JumpPageManager.getManager().goMoreSpuReply(context, tag.getTask_id());
                    }
                });

                break;
            case Constant.SOCKET_TYPE_ANSWER_VOTE:
                StringUtils.addIndexStyleSpan(tv, String.format("%s 赞同了你的回复", show_txt), 0, show_txt.length());
                drawable = context.getResources().getDrawable(R.mipmap
                        .zan);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LiveBarrageEntity tag = (LiveBarrageEntity) v.getTag(tag_data);
                        if (tag == null)
                            return;
                        JumpPageManager.getManager().goReplyDetailActivity(context, tag.getTask_id(), tag.getAnswer_id());
                    }
                });
                break;
        }
        if (drawable != null)
            drawable.setBounds(0, 0, AppUtil.dip2px(8), AppUtil.dip2px(8));

        tv.setCompoundDrawables(drawable, null, null, null);
        tv.setCompoundDrawablePadding(AppUtil.dip2px(4));


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                for (int i = 0; i < data_view.size(); i++) {
                    if (data_view.get(i).getVisibility() == View.VISIBLE) {
                        end(data_view.get(i));
                        break;
                    }
                }
            } else if (msg.what == 3) {
                handlerEnterPerson();

            }

        }
    };


    private View createDanmakuView() {
        TextView textView = new TextView(context);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(12);
        textView.setClickable(true);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(AppUtil.dip2px(8), 0, AppUtil.dip2px(8), 0);
        textView.setBackgroundResource(getBgResId());
        return textView;
    }

    private int getBgResId() {
        return R.drawable.bg_round_black_live_2;
    }

    private FrameLayout.LayoutParams createDanmakuLP() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, AppUtil.dip2px(22));
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.setMargins(0, 0, 0, 0);
        return layoutParams;
    }

    public void clear() {
        if (handler != null) {
            handler.removeMessages(1);
            handler.removeMessages(3);
        }
    }

    public void addReplyMeData(LiveBarrageEntity liveBarrageEntity) {
        data_reply_me.add(liveBarrageEntity);
        if (data_reply_me.size() == 1)
            move(data_reply_me.get(0));
        else if ((data_reply_me.size() > 1))
            alpha_out(move_view);

    }

    long remove_time = 30 * 1000;
    Runnable action = new Runnable() {
        @Override
        public void run() {
            alpha_out(move_view);

        }
    };

    public void move(final LiveBarrageEntity liveBarrageEntity) {


        move_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alpha_out(move_view);
                JumpPageManager.getManager().goMoreSpuReply(context, liveBarrageEntity.getTask_id());

            }
        });
        remove_time = 30 * 1000;
        String que = liveBarrageEntity.getQue();
        if (que.length() > 5)
            que = que.substring(0, 5) + "...";
        move_view.setText(String.format(context.getString(R.string.fmt_reply), que));
        move_view.setVisibility(View.VISIBLE);

        move_view.removeCallbacks(action);
        move_view.postDelayed(action, remove_time);

//        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("tra", 0f, 1f);


        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
        loadAnimation.setDuration(500);
        loadAnimation.setFillAfter(true);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                move_view.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        move_view.startAnimation(loadAnimation);

    }

    public void alpha_out(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.alpha_out);
        loadAnimation.setDuration(500);
        loadAnimation.setFillAfter(true);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (data_reply_me.size() > 0)
                    data_reply_me.remove(0);
                if (data_reply_me.size() > 0)
                    move(data_reply_me.get(0));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(loadAnimation);


    }


}
