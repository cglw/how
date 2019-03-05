package com.prettyyes.user.app.adapter.comment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.view.app.TriangleView;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.model.v8.CommentChildBaseEntity;

/**
 * Created by chengang on 2018/1/12.
 */

public class CommentInfoListAdapter extends AbsRecycleAdapter<CommentChildBaseEntity> {

    private void setCommentHeadData(final CommentChildBaseEntity commentList, AbsRecycleViewHolder holder, final int position) {

        ImageView img_head = holder.findViewById(R.id.img_head);
        TextView tv_time = holder.findViewById(R.id.tv_time);
        TextView tv_username = holder.findViewById(R.id.tv_username);
        TextView tv_comment = holder.findViewById(R.id.tv_comment);
        ImageView img_del = holder.findViewById(R.id.img_del);
        ImageView img_comment = holder.findViewById(R.id.img_comment);
        TriangleView triangleView = holder.findViewById(R.id.triangleView);
        ImageLoadUtils.loadHeadImg(context, commentList.getHeadimg(), img_head);
        tv_time.setText(commentList.getCreated_at());
        tv_username.setText(commentList.getUsername());
        tv_comment.setText(commentList.getComment());
        img_del.setVisibility(View.GONE);
        img_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemViewClickCallBack != null) {
                    itemViewClickCallBack.comment(position, commentList);
                }
            }
        });
        if (getItemCount() > 1) {
            triangleView.setVisibility(View.VISIBLE);
        } else {
            triangleView.setVisibility(View.GONE);
        }


    }
    UserInfo account;


    public CommentInfoListAdapter(Context context) {
        super(context, R.layout.item_rv_comment_info_new);
        account = AccountDataRepo.getAccountManager().getAccount();
    }


    @Override
    protected void bindData(AbsRecycleViewHolder holder, final CommentChildBaseEntity commentList, final int position) {
        if (commentList.isDepth1()) {
            ll_root.setVisibility(View.GONE);
            view_commnet_head.setVisibility(View.VISIBLE);
            setCommentHeadData(commentList, holder, position);

        } else {
            ll_root.setVisibility(View.VISIBLE);
            view_commnet_head.setVisibility(View.GONE);
            String showNickname = commentList.getUsername();
            tv_del.setVisibility(View.GONE);
            ll_root.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_bg_lookmore));
            if ("2".equals(commentList.getDepth())) {
                showNickname = commentList.getUsername();
                LogUtil.i(TAG,"commentList.getUid()"+commentList.getUid()+"-->"+account.getUid());
                if (account != null && commentList.getUid().equals(account.getUid())) {
                    tv_del.setVisibility(View.VISIBLE);
                    showNickname = "我";
                }

            } else {


                String reply_user_name = commentList.getUsername();
                String bereply_user_name = commentList.getForm_nickname();
                if (account != null && commentList.getUid().equals(account.getUid())) {
                    tv_del.setVisibility(View.VISIBLE);
                    reply_user_name = "我";
                }
                if (account != null && commentList.getForm_uid().equals(account.getUid())) {
                    tv_del.setVisibility(View.VISIBLE);
                    bereply_user_name = "我";
                }
                showNickname = reply_user_name + " 回复 " + bereply_user_name;

                ll_root.setBackgroundColor(ContextCompat.getColor(context, R.color.white_grey));


            }
            tv_comment.setText(String.format("%s : %s", showNickname, commentList.getComment()));

            tv_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemViewClickCallBack != null) {
                        itemViewClickCallBack.comment(position, commentList);
                    }

                }
            });
            tv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemViewClickCallBack != null) {
                        itemViewClickCallBack.del(position, commentList);
                    }
                }
            });
        }


    }

    private CommentInfoListAdapter.ItemViewClickCallBack itemViewClickCallBack;

    public void setItemViewClickCallBack(CommentInfoListAdapter.ItemViewClickCallBack itemViewClickCallBack) {
        this.itemViewClickCallBack = itemViewClickCallBack;
    }

    public interface ItemViewClickCallBack {
        void del(int position, CommentChildBaseEntity commentList);

        void comment(int position, CommentChildBaseEntity commentList);

        void commentParent(int position, CommentChildBaseEntity commentList);
    }


    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        tv_comment = holder.getView(R.id.tv_info_comment);
        tv_del = holder.getView(R.id.tv_info_del);
        ll_root = holder.getView(R.id.ll_root);
        view_commnet_head = holder.getView(R.id.view_commnet_head);
    }

    private View view_commnet_head;
    private TextView tv_comment;
    private View ll_root;
    private TextView tv_del;
}
