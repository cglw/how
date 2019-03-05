package com.prettyyes.user.app.adapter.holder_presenter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.CommentChildrenBean;

/**
 * Created by chengang on 2017/8/10.
 */

public class MoreReplyHolderViewImpl extends ReplyHolderViewImpl {
    public MoreReplyHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder, int position, AnswerInfoEntity data) {
        super(mutiTypeViewHolder, position, data);
    }

    public MoreReplyHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder) {
        super(mutiTypeViewHolder);
    }

    private LinearLayout ll_comment_list;
    private ImageView img_top_share;


//    @Override
//    public void setAnswerClick() {
//       JumpPageManager.getManager().goSkuActivity(context,data.getAnswer_type(),data.getModule_id());
//    }

    @Override
    public ReplyHolderViewImpl bindBottom() {
        ll_comment_list = mutiTypeViewHolder.getView(R.id.ll_comment_list);
        img_top_share = getView(R.id.img_top_share);

        return super.bindBottom();
    }

    private final static int tag_data = 10 << 24;
    private final static int tag_ll_comment_list = 11 << 24;
    private final static int tag_tv_comment = 12 << 24;


    @Override
    public void setBottomData() {
        super.setBottomData();
        if (img_top_share != null)
            img_top_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share();
                }
            });


        if (ll_comment != null) {
            ll_comment.setTag(position);
            ll_comment.setTag(tag_data, data);
            ll_comment.setTag(tag_ll_comment_list, ll_comment_list);
            ll_comment.setTag(tag_tv_comment, tv_comment);
            ll_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (JumpPageManager.getManager().checkUnlogin(context)) {
                        return;
                    }
                    final int index = (int) v.getTag();
                    final Object itemData = v.getTag(tag_data);
                    final LinearLayout ll_comment_list = (LinearLayout) v.getTag(tag_ll_comment_list);
                    final TextView tv_comment = (TextView) v.getTag(tag_tv_comment);

                    JumpPageManager.getManager().goCommentActivity(context,data.getAnswer_id(),data.getTask_id());

                }
            });
        }
        if (ll_comment_list != null && tv_comment != null) {
            setCommentData(ll_comment_list, tv_comment, data);


            for (int i = 0; i < ll_comment_list.getChildCount(); i++) {
                View childAt = ll_comment_list.getChildAt(i);
                childAt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JumpPageManager.getManager().goCommentActivity(context, data.getAnswer_id(),data.getTask_id());
                    }
                });

            }
        }


    }

    public RecyclerView recyclerView;
    public int headViewCount = 0;

    public MoreReplyHolderViewImpl setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    private void refreshItem(int position) {
        recyclerView = (RecyclerView) mutiTypeViewHolder.parent;
        int index = position + headViewCount;

        int start = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int end = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

        Object itemData = mutiTypeViewHolder.absMutiRvAdapter.getItemData(index);
        AnswerInfoEntity data = null;
        if (itemData instanceof AnswerInfoEntity)
            data = (AnswerInfoEntity) itemData;
        if (index >= start && index <= end) {
            /**获取指定位置view对象**/
            View view = recyclerView.getChildAt(index - start);
            try {
                LinearLayout ll_comment = (LinearLayout) view.findViewById(R.id.ll_comment_list);
                TextView tv = (TextView) view.findViewById(R.id.tv_comment);
                setCommentData(ll_comment, tv, data);
            } catch (Exception ee) {

            }

        }

    }


    public void setCommentData(LinearLayout ll_comment_list, TextView tv_comment, AnswerInfoEntity data) {
        if (data == null)
            return;
        setCommentNum(tv_comment, data.getComment_count());

        if (data.getComment_children() == null || data.getComment_children().size() == 0) {
            ll_comment_list.setVisibility(View.GONE);
            return;
        } else
            ll_comment_list.setVisibility(View.VISIBLE);


        for (int i = 0; i < 2; i++) {
            ll_comment_list.getChildAt(i).setVisibility(View.GONE);
        }

        for (int i = 0; i < data.getComment_children().size(); i++) {
            if (i >= 2)
                break;
            CommentChildrenBean commentChildrenBean = data.getComment_children().get(i);
            TextView childAt = (TextView) ll_comment_list.getChildAt(i);
            childAt.setVisibility(View.VISIBLE);
            String target = commentChildrenBean.getUsername() + ":";
            String total = target + commentChildrenBean.getComment();
            ClickUtils.setText(childAt, total, target, DensityUtil.dip2px(13), ContextCompat.getColor(context, R.color.comment_user));
        }
        if (data.getComment_children() != null && data.getComment_children().size() > 2) {
            ll_comment_list.getChildAt(2).setVisibility(View.VISIBLE);
        } else {
            ll_comment_list.getChildAt(2).setVisibility(View.GONE);
        }

    }
}
