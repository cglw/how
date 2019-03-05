package com.prettyyes.user.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SystemApiImpl;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.app.view.pupopwindow.InputDialog;
import com.prettyyes.user.core.event.CommentChildEvent;
import com.prettyyes.user.core.event.InputCallback;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.comment.CommentList;

import static com.prettyyes.user.R.id.tv_inner_comment_totalnum;

/**
 * Created by chengang on 2017/5/16.
 */

public class CommentSimpleAdapter extends SpecilAbsAdapter<CommentList> {
    public CommentSimpleAdapter(Context context) {
        super(R.layout.item_rv_simple_comment, context);
    }

    private final static int tag_2 = 2 << 24;

    @Override
    public void showData(ViewHolder holder, final CommentList commentList, final int position) {


        mKolSimpleInfoView.setDataByCommentList(commentList);
        mRel_inner_comment.setVisibility(View.GONE);
        mTv_extra_comment_content.setText(commentList.getComment());
        mRel_inner_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonInfoListActivity.goCommonInfoListActivity((BaseActivity) context, commentList.getComment_id(), commentList.getAnswer_id());
            }
        });
        int count = commentList.getChildCount();
        if (count > 0) {
            mRel_inner_comment.setVisibility(View.VISIBLE);
//            mTv_inner_name.setText();
            mTv_inner_comment_content.setText(commentList.getChildren().get(0).getComment());
            String target = commentList.getChildren().get(0).getUsername() + ":";
            String total = target + commentList.getChildren().get(0).getComment();

            ClickUtils.setText(mTv_inner_comment_content, total, target, DensityUtil.dip2px(13), null);


            mTv_inner_comment_totalnum.setText(count > 1 ? String.format("共%d个回复>", count) : "");
            if (count <= 1)
                mTv_inner_comment_totalnum.setVisibility(View.GONE);
            else
                mTv_inner_comment_totalnum.setVisibility(View.VISIBLE);


        }

        mImg_chat.setTag(commentList);
        mImg_chat.setTag(tag_2, holder.getRootView());

        mImg_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                v.setFocusable(true);
                v.requestFocus();
                v.setFocusableInTouchMode(true);
                if (StringUtils.strIsEmpty(((BaseActivity) context).getUUId())) {
                    AppUtil.showToastShort(context.getString(R.string.login_first));
                    RegisterActivity.getRegister(context);
                    return;
                }


                final CommentList tag = (CommentList) v.getTag();
//                if(commentCallbak!=null) {
//                    commentCallbak.comment(tag);
//                    return;
//                }
                InputDialog inputDialog = new InputDialog((Activity) context);
                inputDialog.setHint(String.format("回复 %s", tag.getUsername()));
                inputDialog.setInputCallback(new InputCallback() {
                    @Override
                    public void inputString(final String text) {
                        new SystemApiImpl().addComment(((BaseActivity) context).getUUId(), text, tag.getAnswer_id(), "task", tag.getComment_id(), new NetReqCallback() {
                            @Override
                            public void apiRequestFail(String message, String method) {
                                AppUtil.showToastShort(message);
                            }

                            @Override
                            public void apiRequestSuccess(Object o, String method) {
                                tag.setChildCount(tag.getChildCount() + 1);
                                CommentList.ChildrenBean childrenBean = new CommentList.ChildrenBean();
                                childrenBean.setComment(text);
                                childrenBean.setUsername(((BaseActivity) context).getAccount().getNickname());
                                tag.getChildren().add(0, childrenBean);
                                AppRxBus.getInstance().post(new CommentChildEvent());

                                View root = (View) v.getTag(tag_2);
                                View mRel_inner_comment = root.findViewById(R.id.ll_inner_comment);
                                TextView mTv_inner_comment_content = (TextView) root.findViewById(R.id.tv_inner_comment_content);
                                TextView mTv_inner_comment_totalnum = (TextView) root.findViewById(tv_inner_comment_totalnum);

                                if (tag.getChildCount() > 0) {
                                    mRel_inner_comment.setVisibility(View.VISIBLE);
                                    mTv_inner_comment_content.setText(tag.getChildren().get(0).getComment());
                                    String target = tag.getChildren().get(0).getUsername() + ":";
                                    String total = target + tag.getChildren().get(0).getComment();
                                    ClickUtils.setText(mTv_inner_comment_content, total, target, DensityUtil.dip2px(13), null);
                                    mTv_inner_comment_totalnum.setText(tag.getChildCount() > 1 ? String.format("共%d个回复>", tag.getChildCount()) : "");
                                    if (tag.getChildCount() <= 1)
                                        mTv_inner_comment_totalnum.setVisibility(View.GONE);
                                    else
                                        mTv_inner_comment_totalnum.setVisibility(View.VISIBLE);

                                }


//                                CommentSimpleAdapter.this.notifyDataSetChanged();

//                                CommentSimpleAdapter.this.notifyItemChanged(position);

//                                String target=commentList.getChildren().get(0).getUsername() + ":";
//                                String total=target+commentList.getChildren().get(0).getComment();
//
//                                ClickUtils.setText( mTv_inner_comment_content,total,target,13,null);


                            }
                        });


                    }
                });
                inputDialog.show(R.id.activity_comment);
            }
        });


    }

    public void setCommentCallback(CommentCallbak commentCallbak) {
        this.commentCallbak = commentCallbak;
    }

    private CommentCallbak commentCallbak;

    public interface CommentCallbak {

        void comment(CommentList commentList);
    }


    @Override
    public void bindView(ViewHolder holder) {
        mImg_chat = (ImageView) holder.getView(R.id.img_chat);
        mKolSimpleInfoView = (KolSimpleInfoView) holder.getView(R.id.KolSimpleInfoView);
        mTv_extra_comment_content = (TextView) holder.getView(R.id.tv_extra_comment_content);
        mRel_inner_comment = holder.getView(R.id.ll_inner_comment);
        mTv_inner_comment_content = (TextView) holder.getView(R.id.tv_inner_comment_content);
        mTv_inner_comment_totalnum = (TextView) holder.getView(tv_inner_comment_totalnum);

    }

    private ImageView mImg_chat;
    private KolSimpleInfoView mKolSimpleInfoView;
    private TextView mTv_extra_comment_content;
    private View mRel_inner_comment;
    //    private TextView mTv_inner_name;
    private TextView mTv_inner_comment_content;
    private TextView mTv_inner_comment_totalnum;


}
