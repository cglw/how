package com.prettyyes.user.app.base;

import android.view.ViewGroup;

import com.prettyyes.user.app.adapter.invate.InvateSellerModel;
import com.prettyyes.user.app.adapter.searchholder.SearchLookMoreModel;
import com.prettyyes.user.app.adapter.searchholder.SearchQueModel;
import com.prettyyes.user.app.adapter.searchholder.SearchSellerModel;
import com.prettyyes.user.app.adapter.searchholder.SearchTitltModel;
import com.prettyyes.user.app.adapter.searchholder.SearchTopicModel;
import com.prettyyes.user.model.TestEntity;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.HomeTaskEntity;
import com.prettyyes.user.model.v8.HowScoreGoods;
import com.prettyyes.user.model.v8.ItemTextModel;
import com.prettyyes.user.model.v8.LiveTaskInfo;
import com.prettyyes.user.model.v8.MedalEntity;
import com.prettyyes.user.model.v8.MineDynamicEntity;
import com.prettyyes.user.model.v8.MyAskEntity;
import com.prettyyes.user.model.v8.ScoreEntity;
import com.prettyyes.user.model.v8.ScoreGetEntity;
import com.prettyyes.user.model.v8.TaskNewUserItemEntity;
import com.prettyyes.user.model.v8.WithdrawEntity;

/**
 * Created by chengang on 2017/7/13.
 */

public interface BaseViewTypeFactory {
    MutiTypeViewHolder createViewHolder(ViewGroup parent, int viewType);

    int type(DemoType demoType);

    int type(MineDynamicEntity mineCommentEntity);

    int type(ItemTextModel item);

    int type(SearchLookMoreModel item);

    int type(SearchSellerModel item);

    int type(SearchTopicModel item);

    int type(SearchTitltModel item);

    int type(SearchQueModel item);

    int type(InvateSellerModel item);

    int type(HomeTaskEntity item);

    int type(MedalEntity item);

    int type(MyAskEntity item);

    int type(TestEntity item);

    int type(AnswerInfoEntity item);

    int type(ScoreEntity item);

    int type(LiveTaskInfo item);

    int type(WithdrawEntity item);

    int type(ScoreGetEntity item);

    int type(TaskNewUserItemEntity item);

    int type(HowScoreGoods item);


}
