package com.prettyyes.user.core;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.AlertMessageResponse;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/11/2
 * Description: Nothing
 */
public class LikeDisLikeHandler {
    AnimationSet animationSet;
    private int color_select;
    private int res_select;
    private int color_unselect;
    private int res_unselect;
    private Context context;

    public LikeDisLikeHandler() {
    }

    public LikeDisLikeHandler(Context context, int selectRes, int unselectRes) {
        animationSet = new AnimationSet(true);
        this.context = context;
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_big);
        Animation loadAnimation1 = AnimationUtils.loadAnimation(context, R.anim.scale_small);
        animationSet.addAnimation(loadAnimation);
        animationSet.addAnimation(loadAnimation1);
        this.res_select = selectRes;
        this.res_unselect = unselectRes;
    }

    public void viewSelectShow(View view, boolean isselect) {
        if (view instanceof ImageView) {
            if (isselect)
                ((ImageView) view).setImageResource(res_select);
            else
                ((ImageView) view).setImageResource(res_unselect);

            startAnimation(view);
        }
    }

    public void tvSelectShow(TextView view, boolean isselect) {
        if (isselect)
            view.setTextColor(ContextCompat.getColor(context, R.color.theme_red));
        else
            view.setTextColor(ContextCompat.getColor(context, R.color.grey_555555));
    }

    public void viewSelectShowNoAnim(View view, boolean isselect) {
        if (view instanceof ImageView) {
            if (isselect)
                ((ImageView) view).setImageResource(res_select);
            else
                ((ImageView) view).setImageResource(res_unselect);

        }
    }

    private void startAnimation(View v) {
        v.clearAnimation();
        v.startAnimation(animationSet);
    }

    public void LikeDisLikeQuestion(String uuid, String vote_type, int type_value, int task_id) {
        new TaskImpl().taskTaskVote(uuid, vote_type, type_value, task_id + "", new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                AlertMessageResponse.isNeedShow(o, DataCenter.CouponGetType.ZEZE);

            }


        });
    }

    public void LikeDisLikeQuestion(String uuid, String vote_type, int type_value, String task_id) {
        new TaskImpl().taskTaskVote(uuid, vote_type, type_value, task_id, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                AlertMessageResponse.isNeedShow(o, DataCenter.CouponGetType.ZEZE);

            }


        });
    }

    public void LikeDisLikeAnswerReply(String uuid, String vote_type, int type_value, int answer_id) {
        new TaskImpl().taskTaskAnswerVote(uuid, vote_type, type_value, answer_id + "", new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                AlertMessageResponse.isNeedShow(o, DataCenter.CouponGetType.ZEZE);

            }


        });
    }

    public void LikeDisLikeAnswerReply(String uuid, String vote_type, int type_value, String answer_id) {
        new TaskImpl().taskTaskAnswerVote(uuid, vote_type, type_value, answer_id, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                AlertMessageResponse.isNeedShow(o, DataCenter.CouponGetType.ZEZE);

            }


        });
    }
}
