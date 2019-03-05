package com.prettyyes.user.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.holder_presenter.QuestionHolderViewImpl;
import com.prettyyes.user.app.adapter.home.CommonSpuV8Holder;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.core.event.AskUnreadEvent;
import com.prettyyes.user.core.event.ReplyQuestionSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.MineDynamicEntity;
import com.prettyyes.user.model.v8.MyAskEntity;
import com.prettyyes.user.model.v8.QuestionEntity;

import java.util.List;


/**
 * Created by chengang on 2017/7/26.
 */

public class InvateMeHolder extends CommonSpuV8Holder<MineDynamicEntity> {


    public InvateMeHolder(ViewGroup parent) {
        super(parent, R.layout.item_rv_invate_me_v8);
    }

    @Override
    public void bindData(final MineDynamicEntity datas, int position, RecyclerView.Adapter adpter) {
        super.bindData(datas, position, adpter);
        img_unread.setVisibility(View.GONE);
        if (datas.isMyAsk()) {
            if (datas.isMe()) {
                try {
                    int tip = Integer.parseInt(datas.getTask().getTip());
                    if (tip > 0)
                        img_unread.setVisibility(View.VISIBLE);
                } catch (Exception ee) {

                }
            }
        }


        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AskUnreadEvent>() {
            @Override
            protected void back(AskUnreadEvent askUnreadEvent) {
                List<MineDynamicEntity> items = absMutiRvAdapter.getItems();
                if (askUnreadEvent.getPosition() < items.size()) {
                    if (items.get(askUnreadEvent.getPosition()).getTask().getTask_id().equals(askUnreadEvent.getTask_id())) {
                        MineDynamicEntity dynamicEntity = (MineDynamicEntity) absMutiRvAdapter.getItemData(askUnreadEvent.getPosition());
                        dynamicEntity.getTask().setTip("0");
                        absMutiRvAdapter.notifyItemChanged(askUnreadEvent.getPosition());
                    }

                }
            }
        });


        new QuestionHolderViewImpl(this, position, datas.getTask()) {
            @Override
            public void itemClick(QuestionEntity questionEntity, View view) {
                super.itemClick(questionEntity, view);
                try {
                    int tip = Integer.parseInt(questionEntity.getTip());

                    if (datas.isMyAsk() && tip > 0) {
                        DataCenter.clearTaskUnread(questionEntity.getTask_id(), position);
                    }
                } catch (Exception ee) {

                }

            }

            @Override
            public void reply(QuestionEntity tag, String s, View v) {
                super.reply(tag, s, v);

            }
        }.bindQueOther().bindQuestion();


    }

    @Override
    public void setRxbusListener() {
        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ReplyQuestionSuccessEvent>() {
            @Override
            protected void back(ReplyQuestionSuccessEvent replyQuestionSuccessEvent) {
                String task_id = replyQuestionSuccessEvent.getTask_id();
                if (getAbsMutiRvAdapter() != null) {
                    for (int i = 0; i < getAbsMutiRvAdapter().getItemCount(); i++) {
                        MyAskEntity itemData = (MyAskEntity) getAbsMutiRvAdapter().getItemData(i);
                        if (itemData.getTask().getTask_id() != null && itemData.getTask().getTask_id().equals(task_id)) {
                            itemData.getTask().setMy_answer_replyed();
                            getAbsMutiRvAdapter().notifyItemChanged(i);
                            break;
                        }
                    }
                }
            }
        });

    }

    @Override
    public void bindView() {
        img_unread = getView(R.id.img_unread);

    }

    private ImageView img_unread;

}
