package com.prettyyes.user.app.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.holder_presenter.QuestionHolderViewImpl;
import com.prettyyes.user.app.adapter.holder_presenter.ReplyHolderViewImpl;
import com.prettyyes.user.model.v8.HomeTaskEntity;

/**
 * Created by chengang on 2017/8/10.
 */

public class HomeV8Holder extends CommonSpuV8Holder<HomeTaskEntity> {


    public HomeV8Holder(ViewGroup parent) {
        super(parent, R.layout.item_rv_home_v8);
    }

    @Override
    public void bindData(HomeTaskEntity datas, int position, RecyclerView.Adapter adpter) {
        super.bindData(datas, position, adpter);
        new QuestionHolderViewImpl(this, position, datas).bindQueOther().bindQuestion().setLikeDisLikeHandler(likeDisLikeHandler);
        new ReplyHolderViewImpl(this, position, datas.getAnswer_info()).setQuestionEntity(datas).bindRoot();
    }


}
