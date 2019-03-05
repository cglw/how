package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.comment.CommonInfoListActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.comment.CommentMyList;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;
import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;

/**
 * Created by chengang on 2017/5/18.
 */

public class CommentNotifyAdapter extends AbsRecycleAdapter<CommentMyList> {

    public CommentNotifyAdapter(Context context
    ) {
        super(context, R.layout.item_rv_comment_notify);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final CommentMyList commentMyList, int position) {

        holder.getRootView().setTag(commentMyList);
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentMyList tag = (CommentMyList) v.getTag();
                CommonInfoListActivity.goCommonInfoListActivity((BaseActivity) context, tag.getComment_id(), tag.getAnswer_id());
            }
        });

        kolSimpleInfoView_commentNotify.setDataByCommentMyList(commentMyList);
        if (commentMyList.getAnswer() != null && commentMyList.getAnswer().size() > 0) {
            CommentMyList.AnswerBean answerBean = commentMyList.getAnswer().get(0);
            String covery_img = "";
            if (answerBean != null) {
                img_commentNotify_covery.setVisibility(View.GONE);
                switch (commentMyList.getAnswer_type()) {
                    case TYPE_TAOBAO:
                        if (answerBean.getImg_arr() != null || answerBean.getImg_arr().size() > 0)
                            covery_img = answerBean.getImg_arr().get(0);
                        break;
                    case TYPE_PAPER:
                        covery_img = answerBean.getPaper_image();
                        break;
                    case TYPE_SUIT:
                        if (answerBean.getSuit_img_arr() != null || answerBean.getSuit_img_arr().size() > 0)
                            covery_img = answerBean.getSuit_img_arr().get(0);
                        break;
                }
                if (!StringUtils.strIsEmpty(covery_img)) {
                    img_commentNotify_covery.setVisibility(View.VISIBLE);
                    ImageLoadUtils.loadListimg(context, covery_img, img_commentNotify_covery);
                }
            }


            tv_commentNotify_reason.setText(commentMyList.getTs_reason());
            if (commentMyList.getParent() == null || commentMyList.getParent().getComment() == null) {
                view_red_line.setVisibility(View.GONE);
                tv_commentNotify_other_reply.setVisibility(View.GONE);
                tv_commentNotify_my_reply.setText(commentMyList.getComment());
            } else {
                view_red_line.setVisibility(View.VISIBLE);
                tv_commentNotify_other_reply.setVisibility(View.VISIBLE);


                String other_name = commentMyList.getParent().getUsername();
                String total = "回复了" + other_name + ": " + commentMyList.getComment();
                setText(tv_commentNotify_my_reply, total, 3, 3 + other_name.length());
                String total_other = other_name + ": " + commentMyList.getParent().getComment();
                setText(tv_commentNotify_other_reply, total_other, 0, other_name.length());
            }

            ((View) tv_commentNotify_other_reply.getParent()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    CommentActivity.goCommentActivity((BaseActivity) context, commentMyList.getAnswer_id(), false);
                    JumpPageManager.getManager().goCommentActivity(context, commentMyList.getAnswer_id() + "");
                }
            });
        }
//        ClickUtils.setText(tv_commentNotify_my_reply,total,my_name,);

    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        kolSimpleInfoView_commentNotify = holder.getView(R.id.KolSimpleInfoView_commentNotify);
        tv_commentNotify_other_reply = holder.getView(R.id.tv_commentNotify_other_reply);
        tv_commentNotify_my_reply = holder.getView(R.id.tv_commentNotify_my_reply);
        img_commentNotify_covery = holder.getView(R.id.img_commentNotify_covery);
        tv_commentNotify_reason = holder.getView(R.id.tv_commentNotify_reason);
        view_red_line = holder.getView(R.id.view100);

    }

    public static void setText(TextView tv, String total, int start, int end) {
        if (!TextUtils.isEmpty(total)) {
            SpannableString spanableInfo = new SpannableString(total);
//            spanableInfo.setSpan(new AbsoluteSizeSpan(DensityUtil.dip2px(15)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanableInfo.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanableInfo.setSpan(new ForegroundColorSpan(Color.BLACK), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(spanableInfo);
            tv.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            tv.setText(total);
        }
    }


    private View view_red_line;
    private com.prettyyes.user.app.ui.appview.KolSimpleInfoView kolSimpleInfoView_commentNotify;
    private TextView tv_commentNotify_other_reply;
    private TextView tv_commentNotify_my_reply;
    private com.prettyyes.user.app.view.imageview.RoundImageView img_commentNotify_covery;
    private TextView tv_commentNotify_reason;


}
