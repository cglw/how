package com.prettyyes.user.app.ui.appview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.core.LikeDisLikeHandler;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.model.Suit.AnswerListBySku;
import com.prettyyes.user.model.Suit.ReplyGoods;

/**
 * Created by chengang on 2017/5/3.
 */

public class SuitListQuestionView extends AbsLinearlayoutView {
    public SuitListQuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_suitlist_question;
    }


    private LinearLayout lin_zeze;
    private ImageView img_bottomv2_zeze;
    private TextView tv_bottomv2_zeze;
    private LinearLayout lin_pei;
    private ImageView img_bottomv2_pei;
    private TextView tv_bottomv2_pei;
    private LinearLayout lin_share;
    private LinearLayout lin_comment;

    private int res_unselect_like;
    private int res_unselect_dislike;
    private int res_select_like;
    private int res_select_dislike;
    LikeDisLikeHandler likeDisLikeHandler_like;
    LikeDisLikeHandler likeDisLikeHandler_dislike;

    private QuestionTextView tv_question;
    private TextView tv_question_reply_desc;
    private View view_suit_question_bg;


    public void setBg(int id) {
        view_suit_question_bg.setBackgroundResource(id);
    }


    @Override
    public void initViews() {

        view_suit_question_bg = getView(R.id.view_suit_question_bg);
        tv_question = (QuestionTextView) getView(R.id.tv_question_question);
        tv_question_reply_desc = (TextView) getView(R.id.tv_question_replydesc);
        lin_zeze = (LinearLayout) getView(R.id.lin_zeze);
        img_bottomv2_zeze = (ImageView) getView(R.id.img_bottomv2_zeze);
        tv_bottomv2_zeze = (TextView) getView(R.id.tv_bottomv2_zeze);
        lin_pei = (LinearLayout) getView(R.id.lin_pei);
        img_bottomv2_pei = (ImageView) getView(R.id.img_bottomv2_pei);
        tv_bottomv2_pei = (TextView) getView(R.id.tv_bottomv2_pei);
        lin_share = (LinearLayout) getView(R.id.lin_share);
        lin_comment = (LinearLayout) getView(R.id.lin_comment);
        res_select_like = R.mipmap.how_zan_icon_highlighted;
        res_select_dislike = R.mipmap.how_pei_icon_highlighted;
        res_unselect_like = R.mipmap.how_zan_icon;
        res_unselect_dislike = R.mipmap.how_pei_icon;
        likeDisLikeHandler_like = new LikeDisLikeHandler(getContext(), res_select_like, res_unselect_like);
        likeDisLikeHandler_dislike = new LikeDisLikeHandler(getContext(), res_select_dislike, res_unselect_dislike);

    }

    public void setDataByAnswerList(final AnswerListBySku.DataBean data, final int layoutid, final String img) {
//        ClickUtils.setClick(tv_question, data.getShare_content(), data.getHash_tag(), new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KolActivityActivity.goKolActActivityByout(getContext(), data.getTask_act_id());
//            }
//        });
        tv_question.setTextWithData(new QuestionTextView.QuestionInnerModel()
                .setDesc(data.getDesc())
                .setHash_tag(data.getHash_tag())
                .setTopic_hash_tag(data.getTopic_hash_tag())
                .setTask_act_id(data.getTask_act_id() + "")
                .setTopic_id(data.getTopic_id() + "")
        );


        tv_question_reply_desc.setText(data.getTs_reason());
        setLikeTv(data.getAnswer_like_num(), tv_bottomv2_zeze, data.getLike() == 1, img_bottomv2_zeze);
        setDisLikeTv(data.getAnswer_dislike_num(), tv_bottomv2_pei, data.getDislike() == 1, img_bottomv2_pei);

        lin_share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(getActivity());

                    return;
                }
                new ShareHandler((Activity) getContext()).setRes(data.getShare_model(), new ShareHandler.ShareCallback() {
                    @Override
                    public void share(boolean issuccess) {

                    }
                }).shareAtWindow(layoutid);

            }
        });
        lin_pei.setTag(data);
        lin_pei.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(getActivity());

                    return;
                }
                AnswerListBySku.DataBean data = (AnswerListBySku.DataBean) v.getTag();
                data.setDislike(data.getDislike() == 1 ? 0 : 1);
                if (data.getDislike() == 1)
                    data.setAnswer_dislike_num(data.getAnswer_dislike_num() + 1);
                else
                    data.setAnswer_dislike_num(data.getAnswer_dislike_num() - 1);
                setDisLikeTv(data.getAnswer_dislike_num(), (TextView) ((LinearLayout) v).getChildAt(1),
                        data.getDislike() == 1, (ImageView) ((LinearLayout) v).getChildAt(0));

                likeDisLikeHandler_dislike.viewSelectShow(((LinearLayout) v).getChildAt(0), data.getDislike() == 1);
                if (!ClickUtils.isFastDoubleClick()) {
                    likeDisLikeHandler_dislike.LikeDisLikeAnswerReply(((BaseActivity) getActivity()).getUUId(), "dislike", data.getDislike(), data.getAnswer_id());

                }

            }
        });
        lin_zeze.setTag(data);
        lin_zeze.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(getActivity());

                    return;
                }
                AnswerListBySku.DataBean data = (AnswerListBySku.DataBean) v.getTag();
                data.setLike(data.getLike() == 1 ? 0 : 1);
                if (data.getLike() == 1)
                    data.setAnswer_like_num(data.getAnswer_like_num() + 1);
                else
                    data.setAnswer_like_num(data.getAnswer_like_num() - 1);
                setLikeTv(data.getAnswer_like_num(), (TextView) ((LinearLayout) v).getChildAt(1), data.getLike() == 1, (ImageView) ((LinearLayout) v).getChildAt(0));

                likeDisLikeHandler_like.viewSelectShow(((LinearLayout) v).getChildAt(0), data.getLike() == 1);
                if (!ClickUtils.isFastDoubleClick()) {
                    likeDisLikeHandler_like.LikeDisLikeAnswerReply(((BaseActivity) getActivity()).getUUId(), "like", data.getLike(), data.getAnswer_id());

                }

            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goCommentActivity(getActivity(), data.getAnswer_id() + "",data.getTask_id()+"");
            }
        });
        lin_comment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goCommentActivity(getActivity(), data.getAnswer_id() + "",data.getTask_id()+"");
            }
        });

    }

    public void setDataReplayGoods(final ReplyGoods.TaskListBean data, final int layoutid, final String img) {

        ClickUtils.setClick(tv_question, data.getDesc(), data.getHash_tag(), new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolActActivity(getContext(),data.getTask_act_id());
//                KolActivityActivity.goKolActActivityByout(getContext(), data.getTask_act_id());
            }
        });
        tv_question_reply_desc.setText(data.getTs_reason());

        setLikeTv(data.getAnswer_like_num(), tv_bottomv2_zeze, data.getLike() == 1, img_bottomv2_zeze);
        setDisLikeTv(data.getAnswer_dislike_num(), tv_bottomv2_pei, data.getDislike() == 1, img_bottomv2_pei);

        lin_share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(getActivity());

                    return;
                }
                new ShareHandler((Activity) getContext()).setRes(data.getShare_model(), new ShareHandler.ShareCallback() {
                    @Override
                    public void share(boolean issuccess) {

                    }
                }).shareAtWindow(layoutid);

            }
        });
        lin_pei.setTag(data);
        lin_pei.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(getActivity());

                    return;
                }
                ReplyGoods.TaskListBean data = (ReplyGoods.TaskListBean) v.getTag();
                data.setDislike(data.getDislike() == 1 ? 0 : 1);
                if (data.getDislike() == 1)
                    data.setAnswer_dislike_num(data.getAnswer_dislike_num() + 1);
                else
                    data.setAnswer_dislike_num(data.getAnswer_dislike_num() - 1);
                setDisLikeTv(data.getAnswer_dislike_num(), (TextView) ((LinearLayout) v).getChildAt(1),
                        data.getDislike() == 1, (ImageView) ((LinearLayout) v).getChildAt(0));

                likeDisLikeHandler_dislike.viewSelectShow(((LinearLayout) v).getChildAt(0), data.getDislike() == 1);
                if (!ClickUtils.isFastDoubleClick()) {
                    likeDisLikeHandler_dislike.LikeDisLikeAnswerReply(((BaseActivity) getActivity()).getUUId(), "dislike", data.getDislike(), data.getAnswer_id());

                }

            }
        });
        lin_zeze.setTag(data);
        lin_zeze.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(getActivity());

                    return;
                }
                ReplyGoods.TaskListBean data = (ReplyGoods.TaskListBean) v.getTag();
                data.setLike(data.getLike() == 1 ? 0 : 1);
                if (data.getLike() == 1)
                    data.setAnswer_like_num(data.getAnswer_like_num() + 1);
                else
                    data.setAnswer_like_num(data.getAnswer_like_num() - 1);
                setLikeTv(data.getAnswer_like_num(), (TextView) ((LinearLayout) v).getChildAt(1), data.getLike() == 1, (ImageView) ((LinearLayout) v).getChildAt(0));

                likeDisLikeHandler_like.viewSelectShow(((LinearLayout) v).getChildAt(0), data.getLike() == 1);
                if (!ClickUtils.isFastDoubleClick()) {
                    likeDisLikeHandler_like.LikeDisLikeAnswerReply(((BaseActivity) getActivity()).getUUId(), "like", data.getLike(), data.getAnswer_id());

                }

            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goReplyDetailActivity(getActivity(), data.getTask_id() + "", data.getAnswer_id() + "");
//                CommentActivity.goCommentActivity((BaseActivity) getActivity(), data.getAnswer_id(), false);
            }
        });
        lin_comment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goCommentActivity(getActivity(), data.getAnswer_id() + "",data.getTask_id()+"");

//                CommentActivity.goCommentActivity((BaseActivity) getActivity(), data.getAnswer_id(), true);
            }
        });


    }

    private void setLikeTv(int num, TextView tv, boolean islike, ImageView imageView) {
        tv.setText(new StringBuffer().append("啧啧 ").append(Constant.CENTER_POINT).append(" ").append(num));

        if (islike) {
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.theme_red));

            imageView.setImageResource(R.mipmap.how_zan_icon_highlighted);
        } else {
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.grey_555555));

            imageView.setImageResource(R.mipmap.how_zan_icon);
        }

    }

    private void setDisLikeTv(int num, TextView tv, boolean isdislike, ImageView imageView) {
        tv.setText(new StringBuffer().append("呸 ").append(Constant.CENTER_POINT).append(" ").append(num));
        if (isdislike) {
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.theme_red));
            imageView.setImageResource(R.mipmap.how_pei_icon_highlighted);
        } else {
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.grey_555555));
            imageView.setImageResource(R.mipmap.how_pei_icon);
        }
    }

    @Override
    public void setDataByModel(Object data) {

    }
}
