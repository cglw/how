package com.prettyyes.user.app.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.LikeDisLikeHandler;
import com.prettyyes.user.core.event.ReplyQuestionSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.QuestionEntity;

/**
 * Created by chengang on 2017/7/20.
 */

public class CommonSpuV8Holder<T> extends MutiTypeViewHolder<T> {
    public LikeDisLikeHandler likeDisLikeHandler;
    public final static int tag_index = 3 << 24;
    private final static int tag_view = 4 << 24;

    public CommonSpuV8Holder(View itemView) {
        super(itemView);
        initLikeDislike();
    }

    public void initLikeDislike() {
        likeDisLikeHandler = new LikeDisLikeHandler(context, R.mipmap.how_home_liked, R.mipmap.how_home_like);
    }

    public CommonSpuV8Holder(ViewGroup parent, int layout_id) {
        super(parent.getContext(), parent, layout_id);
        initLikeDislike();

    }

    public CommonSpuV8Holder(ViewGroup parent) {
        super(parent.getContext(), parent, R.layout.item_rv_home_v8);
        initLikeDislike();
    }


    @Override
    public void bindData(final T datas, int position, RecyclerView.Adapter adpter) {
        setRxbusListener();

    }

    @Override
    public void bindView() {

    }


    public void setRxbusListener() {
        ((BaseActivity)context).mSubscription=   AppRxBus.getInstance().subscribeEvent(new RxCallback<ReplyQuestionSuccessEvent>() {
            @Override
            protected void back(ReplyQuestionSuccessEvent replyQuestionSuccessEvent) {

                String task_id = replyQuestionSuccessEvent.getTask_id();
                if (getAbsMutiRvAdapter() != null) {
                    for (int i = 0; i < getAbsMutiRvAdapter().getItemCount(); i++) {
                        QuestionEntity itemData = (QuestionEntity) getAbsMutiRvAdapter().getItemData(i);
                        if (itemData.getTask_id() != null && itemData.getTask_id().equals(task_id)) {
                            itemData.setMy_answer_replyed();
                            getAbsMutiRvAdapter().notifyItemChanged(i);
                            break;
                        }
                    }
                }
            }
        });

    }

}
