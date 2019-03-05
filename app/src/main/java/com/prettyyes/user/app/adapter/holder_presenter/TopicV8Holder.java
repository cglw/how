package com.prettyyes.user.app.adapter.holder_presenter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.prettyyes.user.app.adapter.home.HomeV8Holder;
import com.prettyyes.user.model.v8.HomeTaskEntity;

/**
 * Created by chengang on 2017/8/11.
 */

public class TopicV8Holder extends HomeV8Holder {
    public TopicV8Holder(ViewGroup parent) {
        super(parent);
    }


    @Override
    public void bindData(HomeTaskEntity datas, int position, RecyclerView.Adapter adpter) {
        super.bindData(datas, position, adpter);
        new QuestionHolderViewImpl(this, position, datas).setQue_color(datas.que_color).bindQueOther().bindQuestion().setLikeDisLikeHandler(likeDisLikeHandler);
        new ReplyHolderViewImpl(this, position, datas.getAnswer_info()).setQuestionEntity(datas).bindRoot();


    }
    //
//    public TopicV8Holder(ViewGroup parent) {
//        super(parent, R.layout.item_rv_topic_v8);
//    }
//
//    @Override
//    public void bindData(HomeTaskEntity datas, int position, RecyclerView.Adapter adpter) {
//        super.bindData(datas, position, adpter);
//        new QuestionHolderViewImpl(this, position, datas).bindQueOther().bindQuestion().setLikeDisLikeHandler(likeDisLikeHandler);
//        new ReplyHolderViewImpl(this, position, datas.getAnswer_info()).setQuestionEntity(datas).bindRoot();
//
//
//    }
}
