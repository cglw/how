package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.core.event.LoadMoreCommentEvent;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.comment.CommentInfo;

/**
 * Created by chengang on 2017/5/16.
 */

public class CommentInfoAdapter extends AbsRecycleAdapter<CommentInfo.ChildrenBean> {
    public CommentInfoAdapter(Context context) {
        super(context, R.layout.item_rv_comment_info);
//        user_id = ((BaseActivity) context).getAccount().getUser_id();
    }

    private int comment_id;

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

//    private String user_id = "";

    @Override
    protected void bindData(AbsRecycleViewHolder holder, CommentInfo.ChildrenBean commentInfo, int position) {

        View parent = (View) mKolSimpleInfoView.getParent();
        mKolSimpleInfoView.setDataByCommentChildrenBean(commentInfo);
        mKolSimpleInfoView.setNeedClickHead(false);
        tv_content.setText(commentInfo.getComment());
        rel_morereply.setVisibility(View.GONE);

        if (commentInfo.getSeparated() != null && commentInfo.getSeparated().equals("true")) {
            rel_morereply.setVisibility(View.VISIBLE);


        }
        if (comment_id == commentInfo.getComment_id()) {
            parent.setBackgroundColor(Color.argb(55, 255, 171, 191));
        } else {
            if (position == 0) {
                line_top.setVisibility(View.GONE);
                parent.setBackgroundColor(Color.WHITE);
            } else {
                line_top.setVisibility(View.VISIBLE);
                parent.setBackgroundColor(Color.rgb(250, 250, 250));

            }

        }


        rel_morereply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppRxBus.getInstance().post(new LoadMoreCommentEvent());
            }
        });


    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        mKolSimpleInfoView = holder.getView(R.id.KolSimpleInfoView_commentInfoAdp);
        tv_content = holder.getView(R.id.tv_commentInfoAdp_content);
        rel_morereply = holder.getView(R.id.rel_commentInfoAdp_morereply);
        line_top = holder.getView(R.id.line_top);
    }

    private KolSimpleInfoView mKolSimpleInfoView;
    private TextView tv_content;
    private RelativeLayout rel_morereply;
    private View line_top;

}
