package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.model.v8.AnswerInfoEntity;

/**
 * Created by chengang on 2017/7/12.
 */

public class MoreReplyAdapter extends CommonReplyAdapter<AnswerInfoEntity> {


    private static final String TAG = "MoreReplyAdapter";

    public MoreReplyAdapter(Context context) {
        super(context, R.layout.item_rv_mcmt);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, AnswerInfoEntity data, int position) {
        super.bindData(holder, data, position);
//        LogUtil.i(TAG, "bindData");
        setCenterData(holder, data, position);
//        questionView2.setVisibility(View.GONE);
        bottomView2_mocmtAdp.hideShare();
        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomView2_mocmtAdp.lin_share.performClick();
            }
        });


    }

    @Override
    protected void bindView(AbsRecycleViewHolder vHolder) {
        super.bindView(vHolder);
        img_share = vHolder.getView(R.id.img_share);
    }

    private ImageView img_share;
}
