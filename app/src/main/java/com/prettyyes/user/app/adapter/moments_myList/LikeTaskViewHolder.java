package com.prettyyes.user.app.adapter.moments_myList;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.v8.MineDynamicEntity;

/**
 * Created by chengang on 2017/7/13.
 */

public class LikeTaskViewHolder extends MineCommonViewHolder {
    public LikeTaskViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(final MineDynamicEntity modle, int position, RecyclerView.Adapter adpter) {
        super.bindData(modle, position, adpter);

        tv_title.setText(context.getString(R.string.item_minetype_collect));
        LogUtil.i(TAG, "modle.getTask()" + modle.getTask());


        String task = modle.getTask().getDesc();

        if (task.length() > 20) {
            task = task.substring(0, 20) + "...";
        }
        String format = String.format("你关注的“%s”问题收到了来自用户", task);
        String nickname = modle.getNickname();
        if (nickname == null)
            nickname = "";
        String format1 = String.format("%s%s的回复", format, nickname);
        SpannableString spanString = new SpannableString(format1);
        StyleSpan span = new StyleSpan(Typeface.BOLD);

        spanString.setSpan(span, format.length(), format.length() + nickname.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.theme_red)), format.length(), format.length() + nickname.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_que.setText(spanString);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goReplyDetailActivity(context,modle.getTask_id(), modle.getAnswer_id());

//                JumpPageManager.getManager().goMoreSpuReply(context, modle.getTask_id());
            }
        });
    }

    @Override
    public void bindView() {
        super.bindView();
        tv_que = getView(R.id.tv_que);
    }

    private TextView tv_que;
}