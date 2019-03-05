package com.prettyyes.user.app.adapter.holder_presenter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.CommentChildrenBean;

/**
 * Created by chengang on 2017/8/29.
 */

public class ReplyDelCommentHolderViewImpl extends BaseHolderViewImpl<AnswerInfoEntity> implements CommentHolderView {


    public LinearLayout ll_comment_list;
    public LinearLayout ll_comment1;
    public LinearLayout ll_comment2;
    public TextView look_more;
    public String answer_id;

    public LinearLayout ll_comment;

    public void setLl_comment(LinearLayout ll_comment) {
        this.ll_comment = ll_comment;
    }

    public ReplyDelCommentHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder) {
        super(mutiTypeViewHolder);
    }


    public void set_ll_comment() {
        if (ll_comment != null) {
            ll_comment.setTag(data);

            ll_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (JumpPageManager.getManager().checkUnlogin(context)) {
                        return;
                    }
                    final Object itemData = v.getTag();
                    final TextView tv = (TextView) ((LinearLayout) v).getChildAt(0);

                    JumpPageManager.getManager().goCommentActivity(context,data.getAnswer_id(),data.getTask_id());
//                    InputDialog inputDialog = new InputDialog((Activity) context);
//                    inputDialog.setInputCallback(new InputCallback() {
//                        @Override
//                        public void inputString(final String text) {
//
//
//                            if (itemData instanceof AnswerInfoEntity) {
//                                final AnswerInfoEntity tag = (AnswerInfoEntity) itemData;
//                                new SystemApiImpl().addComment(SpMananger.getUUID(), text, Integer.parseInt(tag.getAnswer_id()), "task", 0, new NetReqCallback() {
//                                    @Override
//                                    public void apiRequestFail(String message, String method) {
//                                        AppUtil.showToastShort(message);
//
//                                    }
//
//                                    @Override
//                                    public void apiRequestSuccess(Object o, String method) {
//                                        AppRxBus.getInstance().post(new BottomDoEvent().setTask_id(data.getTask_id()).setAnswer_id(data.getAnswer_id()));
//
//                                        tag.setComment_count(tag.getComment_count() + 1);
//                                        CommentChildrenBean commentChildrenBean = new CommentChildrenBean();
//                                        commentChildrenBean.setUsername(((BaseActivity) context).getAccount().getNickname());
//                                        commentChildrenBean.setHeadimg(SpMananger.getUserInfo().getHeadimg());
//                                        commentChildrenBean.setCreate_time(FormatUtils.getDate(TimeManager.getManager().getServer_time() * 1000));
//                                        commentChildrenBean.setComment(text);
//                                        tag.getComment_children().add(0, commentChildrenBean);
//                                        setCommentNum(tv, tag.getComment_count());
//
//                                        bindCommentData();
//
//
////                                        refreshItem(index);
//
//                                    }
//                                });
//                            }
//
//                        }
//                    });
//                    inputDialog.show(((BaseActivity) context).getRootView());
                }
            });
        }

    }

    @Override
    public CommentHolderView bindComment() {
        ll_comment_list = getView(R.id.ll_comment_list);
        ll_comment1 = (LinearLayout) ll_comment_list.getChildAt(0);
        ll_comment2 = (LinearLayout) ll_comment_list.getChildAt(1);
        look_more = (TextView) ll_comment_list.getChildAt(2);
        bindCommentData();
        set_ll_comment();

        return this;
    }

    @Override
    public void bindCommentData() {

        ll_comment_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpPageManager.getManager().goCommentActivity(context, data.getAnswer_id(),data.getTask_id());
            }
        });
        if (data == null || data.getComment_children().size() < 0) {
            ll_comment_list.setVisibility(View.GONE);
            return;
        } else
            ll_comment_list.setVisibility(View.VISIBLE);
        ll_comment1.setVisibility(View.GONE);
        ll_comment2.setVisibility(View.GONE);
        look_more.setVisibility(View.GONE);


        for (int i = 0; i < data.getComment_children().size(); i++) {
            if (i == 2)
                return;
            CommentChildrenBean c = data.getComment_children().get(i);
            if (i == 0) {
                ll_comment1.setVisibility(View.VISIBLE);
                KolSimpleInfoView kolSimpleInfoView = (KolSimpleInfoView) ll_comment1.getChildAt(0);
                TextView comment = (TextView) ll_comment1.getChildAt(1);
                kolSimpleInfoView.setDataByCommentChildrenBean(c);
                comment.setText(c.getComment());
            } else if (i == 1) {
                ll_comment2.setVisibility(View.VISIBLE);
                KolSimpleInfoView kolSimpleInfoView = (KolSimpleInfoView) ll_comment2.getChildAt(0);
                TextView comment = (TextView) ll_comment2.getChildAt(1);
                kolSimpleInfoView.setDataByCommentChildrenBean(c);
                comment.setText(c.getComment());
            }
            if(data.getComment_children()!=null&&data.getComment_children().size()>2)
                look_more.setVisibility(View.VISIBLE);



        }

    }

    public void setCommentNum(TextView tv, int num) {

        String showtext = "评论";
        tv.setText(num > 0 ? showtext + String.format("(%d)", num) : showtext);

    }

}
