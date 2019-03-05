package com.prettyyes.user.app.adapter.moments_myList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.appview.NewQuestionView;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AskUnreadEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.MineDynamicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/7/13.
 *
 */

public class AskViewHolder extends MineCommonViewHolder {


    public AskViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(final MineDynamicEntity modle, final int position, final RecyclerView.Adapter adpter) {
        super.bindData(modle, position, adpter);
        tv_title.setText(context.getString(R.string.item_minetype_que));
        newQuestionView.setData(modle.getTask(), (ArrayList<String>) StringUtils.getSplitArray(modle.getTask().getPic_list()));
        tv_score.setText(modle.getTask().getPscore());
        if (tv_score != null) {
            View parent = (View) tv_score.getParent();
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpPageManager.getManager().goHowToAsk(context, true);
                }
            });
        }
        if (Integer.parseInt(modle.getTask().getTip()) > 0 && isMe(modle)) {
            tv_reply_num.setVisibility(View.VISIBLE);
            tv_reply_num.setText(String.format("收到%s条新回复", modle.getTask().getTip()));
        } else {
            tv_reply_num.setVisibility(View.GONE);

        }
        if (modle.getTask().getTask_count() > 0) {
            ll_reply_num_info.getChildAt(2).setVisibility(View.GONE);
            ll_reply_num_info.getChildAt(0).setVisibility(View.VISIBLE);
            ll_reply_num_info.getChildAt(1).setVisibility(View.VISIBLE);
            tv_reply_info_num.setText(modle.getTask().getTask_describe_text());

        } else {
            ll_reply_num_info.getChildAt(2).setVisibility(View.VISIBLE);
            ll_reply_num_info.getChildAt(0).setVisibility(View.GONE);
            ll_reply_num_info.getChildAt(1).setVisibility(View.GONE);
        }
        view_invate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task_id =  modle.getTask().getTask_id();
                ShareModel share_model = modle.getTask().getShare_model();
                share_model.setType("task");
                share_model.setType_id(task_id);
                share_model.setTask_id(task_id);
                JumpPageManager.getManager().goShareActivity(context, share_model);

            }
        });

        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AskUnreadEvent>() {
            @Override
            protected void back(AskUnreadEvent askUnreadEvent) {
                LogUtil.i(TAG, "refresh need" + askUnreadEvent.getTask_id() + "-->" + askUnreadEvent.getPosition());

                List<MineDynamicEntity> items = absMutiRvAdapter.getItems();
                if (askUnreadEvent.getPosition() < items.size()) {
                    if (items.get(askUnreadEvent.getPosition()).getTask().getTask_id().equals(askUnreadEvent.getTask_id())) {
                        MineDynamicEntity dynamicEntity = (MineDynamicEntity) absMutiRvAdapter.getItemData(askUnreadEvent.getPosition());
                        dynamicEntity.getTask().setTip("0");
                        absMutiRvAdapter.notifyItemChanged(askUnreadEvent.getPosition());
                        LogUtil.i(TAG, "test" + askUnreadEvent.getPosition());
                    }

                }
            }
        });


        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modle.getTask().getTask_count() <= 1) {
                    JumpPageManager.getManager().goReplyDetailActivity(context, modle.getTask().getTask_id(), modle.getAnswer_id());
                } else {
                    JumpPageManager.getManager().goMoreSpuReply(context, modle.getTask().getTask_id(), modle.getAnswer_id());

                }
                DataCenter.clearTaskUnread(modle.getTask().getTask_id(), position);

            }
        });
//        tv_reply_info_num.setText(String.format("共收到%s个回复 | 有%s个优秀内容", modle.getTask()., modle.getTask().getExcellent()));


    }

    @Override
    public void bindView() {
        super.bindView();
        view_invate = getView(R.id.tv_invate);
        newQuestionView = getView(R.id.newqueview);
        tv_score = getView(R.id.tv_score);
        tv_reply_num = getView(R.id.tv_reply_num);
        tv_reply_info_num = getView(R.id.tv_reply_info_num);
        ll_reply_num_info = getView(R.id.ll_reply_num_info);
    }

    private View view_invate;
    private NewQuestionView newQuestionView;
    private TextView tv_score;
    private TextView tv_reply_num;
    private TextView tv_reply_info_num;
    private LinearLayout ll_reply_num_info;
}
