package com.prettyyes.user.app.adapter.moments_myList;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.MineDynamicEntity;

/**
 * Created by chengang on 2017/7/13.
 */

public class CommentMeViewHolder extends CommentOtherViewHolder {


    @Override
    public void clickItem(MineDynamicEntity modle) {
            JumpPageManager.getManager().goCommentActivity(context, modle.getAnswer_id(), modle.getTask_id());

    }

    public CommentMeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setCommentInfo(MineDynamicEntity modle) {
        tv_title.setText(context.getString(R.string.item_minetype_comment_me));
        StringUtils.addIndexStyleSpan(tv_content, "我:" + modle.getParent().getComment(), 0, 1);
        String comment = "回复了@我:" + modle.getComment();
        StringUtils.addIndexStyleSpan(tv_comment, comment, 3, 2, ContextCompat.getColor(context, R.color.theme_red));
    }
}