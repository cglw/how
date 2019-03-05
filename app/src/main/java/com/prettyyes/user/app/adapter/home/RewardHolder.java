package com.prettyyes.user.app.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.holder_presenter.QuestionHolderViewImpl;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.v8.HomeTaskEntity;

/**
 * Created by chengang on 2017/12/7.
 */

public class RewardHolder extends CommonSpuV8Holder<HomeTaskEntity> {


    private TextView tv_reward;
    private TextView tv_want_reply;
    private ImageView img_complete;

    public RewardHolder(ViewGroup parent) {
        super(parent, R.layout.item_rv_reward);
    }

    @Override
    public void bindData(final HomeTaskEntity datas, int position, RecyclerView.Adapter adpter) {
        super.bindData(datas, position, adpter);
        new QuestionHolderViewImpl(this, position, datas).bindQueOther().bindQuestion().setLikeDisLikeHandler(likeDisLikeHandler);
//        new ReplyHolderViewImpl(this, position, datas.getAnswer_info()).setQuestionEntity(datas).bindRoot();
        tv_reward = getView(R.id.tv_reward);
        tv_want_reply = getView(R.id.tv_want_reply);
        img_complete = getView(R.id.img_complete);
        tv_reward.setText(String.format("正在悬赏,还有%d个回复名额", datas.getReward_count()));

        LogUtil.i(TAG, "datas" + datas.getMy_answer() + "datas.haveReply()" + datas.haveReply());
        if (datas.haveReply()) {
            tv_reward.setEnabled(false);
            tv_reward.setBackgroundResource(R.drawable.bg_round_grey_100);
            tv_reward.setText("已回复");

        } else {
            tv_reward.setEnabled(true);
            tv_reward.setBackgroundResource(R.drawable.bg_round_red_round100);
        }

        if (datas.getReward_count() == 0) {
            img_complete.setVisibility(View.VISIBLE);
        } else {
            img_complete.setVisibility(View.GONE);
        }
        tv_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolReplyActivity(context, datas.getTask_id(), datas.getDesc());
            }
        });
        tv_want_reply.setVisibility(View.GONE);
//        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ReplyQuestionSuccessEvent>() {
//            @Override
//            protected void back(ReplyQuestionSuccessEvent replyQuestionSuccessEvent) {
//                String task_id = replyQuestionSuccessEvent.getTask_id();
//                if (getAbsMutiRvAdapter() != null) {
//                    for (int i = 0; i < getAbsMutiRvAdapter().getItemCount(); i++) {
//                        QuestionEntity itemData = (QuestionEntity) getAbsMutiRvAdapter().getItemData(i);
//                        if (itemData.getTask_id() != null && itemData.getTask_id().equals(task_id)) {
//                            if (itemData.getReward_count() > 0) {
//                                itemData.setReward_count(itemData.getReward_count() - 1);
//                                getAbsMutiRvAdapter().notifyItemChanged(i);
//                            }
//                            break;
//                        }
//                    }
//                }
//            }
//        });


    }
}