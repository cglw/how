package com.prettyyes.user.app.adapter.comment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.view.app.TriangleView;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.model.v8.CommentChildBaseEntity;

/**
 * Created by chengang on 2018/1/12.
 */

public class CommentListAdapter extends AbsRecycleAdapter<CommentChildBaseEntity> {
    UserInfo account;
    public CommentListAdapter(Context context) {
        super(context, R.layout.item_rv_comment_list);
        account = AccountDataRepo.getAccountManager().getAccount();
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final CommentChildBaseEntity commentList, final int position) {
        ImageLoadUtils.loadHeadImg(context, commentList.getHeadimg(), img_head);
        tv_time.setText(commentList.getCreated_at());
        tv_username.setText(commentList.getUsername());
        tv_comment.setText(commentList.getComment());
        boolean is_me = false;

        if (account != null && account.getUid() != null && account.getUid().equals(commentList.getUid() + "")) {
            img_del.setVisibility(View.VISIBLE);
            is_me = true;
        } else {
            img_del.setVisibility(View.GONE);

        }
        refreshCommengChild(commentList,position);

    }


    public void refreshCommengChild(final CommentChildBaseEntity commentList,final int position)
    {
        boolean is_me = false;
        UserInfo account = AccountDataRepo.getAccountManager().getAccount();
        if (account != null && account.getUid() != null && account.getUid().equals(commentList.getUid() + "")) {
            is_me = true;
        }

        ll_comment_list.setVisibility(View.GONE);
        triangleView.setVisibility(View.GONE);
        view_more.setVisibility(View.GONE);

        if (commentList.getChildren() != null && commentList.getChildren().size() > 0) {
            ll_comment_list.setVisibility(View.VISIBLE);
            triangleView.setVisibility(View.VISIBLE);


            for (int i = 0; i < ll_comment_list.getChildCount(); i++) {
                ll_comment_list.getChildAt(i).setVisibility(View.GONE);
            }
            if (commentList.getChildCount() > 2 || commentList.getChildren().size() > 2) {
                view_more.setVisibility(View.VISIBLE);
            }
            ll_comment_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemViewClickCallBack != null) {
                        itemViewClickCallBack.goCommentInfoList(position, commentList);
                    }
                }
            });

            for (int i = 0; i < commentList.getChildren().size(); i++) {
                CommentChildBaseEntity childrenBean = commentList.getChildren().get(i);
                if (i == 2)
                    break;
                LinearLayout childAt = (LinearLayout) ll_comment_list.getChildAt(i);
                childAt.setVisibility(View.VISIBLE);
                ImageView img_head = (ImageView) childAt.getChildAt(0);
                TextView nickname = (TextView) childAt.getChildAt(1);
                TextView comment = (TextView) childAt.getChildAt(2);
                ImageLoadUtils.loadHeadImg(context, childrenBean.getHeadimg(), img_head);

                nickname.setText(is_me ? "我： " : childrenBean.getUsername()+"： ");
                comment.setText(childrenBean.getComment());
                ImageLoadUtils.loadHeadImg(context, childrenBean.getHeadimg(), img_head);
            }


        }

        img_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemViewClickCallBack != null) {
                    itemViewClickCallBack.comment(position, commentList);
                }

            }
        });
        img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemViewClickCallBack != null) {
                    itemViewClickCallBack.del(position, commentList);
                }

            }
        });
    }

    public ItemViewClickCallBack itemViewClickCallBack;

    public void setItemViewClickCallBack(ItemViewClickCallBack itemViewClickCallBack) {
        this.itemViewClickCallBack = itemViewClickCallBack;
    }

    public interface ItemViewClickCallBack {
        void del(int position, CommentChildBaseEntity commentList);

        void comment(int position, CommentChildBaseEntity commentList);

        void goCommentInfoList(int position, CommentChildBaseEntity commentList);
    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        img_head = holder.getView(R.id.img_head);
        tv_time = holder.getView(R.id.tv_time);
        tv_username = holder.getView(R.id.tv_username);
        tv_comment = holder.getView(R.id.tv_comment);
        triangleView = holder.getView(R.id.triangleView);
        img_comment = holder.getView(R.id.img_comment);
        img_del = holder.getView(R.id.img_del);
        ll_comment_list = holder.getView(R.id.ll_comment_list);
        view_more = holder.getView(R.id.view_more);

    }


    private ImageView img_head;
    private TextView tv_time;
    private TextView tv_username;
    private TextView tv_comment;
    private TriangleView triangleView;
    private ImageView img_comment;
    private ImageView img_del;
    private LinearLayout ll_comment_list;
    private LinearLayout view_more;


}
