package com.prettyyes.user.app.adapter;

import android.content.Context;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsAdapter;
import com.prettyyes.user.model.v8.AnswerInfoEntity;

/**
 * Created by chengang on 2017/8/25.
 */

public class ReplyDetailLvAdapter extends AbsAdapter<AnswerInfoEntity> {
    public ReplyDetailLvAdapter( Context context) {
        super(R.layout.view_reply_detail, context);
    }
//
////    public ReplyDetailLvAdapter(Context context) {
////        super(context, new ArrayList<AnswerInfoEntity>(), R.layout.view_reply_detail);
////    }
////
////    @Override
////    public void bindView(ViewHolder vHolder) {
//
//    }

//    @Override
//    public void showData(ViewHolder vHolder, AnswerInfoEntity data, int position) {
//
//        CommentHeadHolderImpl commentHeadHolder = new CommentHeadHolderImpl(vHolder, 0, data);
//        commentHeadHolder.bindRoot();
//        new MutiSpuHolderViewImpl(vHolder, 0, data.getAnswer_data()).bindMutiSpu();
//
//    }

    @Override
    public void showData(ViewHolder vHolder, AnswerInfoEntity data) {

    }
}
