package com.prettyyes.user.app.adapter.holder_presenter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.comment.ReplyDetailActivity;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.LikeDisLikeHandler;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.BottomDoEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.AnswerActionEntity;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.QuestionEntity;
import com.prettyyes.user.model.v8.SellerInfoEntity;
import com.prettyyes.user.model.v8.UploadMediaEntity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/8/10.
 */

public class ReplyHolderViewImpl extends BaseHolderViewImpl<AnswerInfoEntity> implements ReplyHolderView {
    public ReplyHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder, int position, AnswerInfoEntity data) {
        super(mutiTypeViewHolder, position, data);

    }

    public ReplyHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder) {
        super(mutiTypeViewHolder);

    }


    private TextView tv_tsReason;
    public View view_reply_answer_root;

    @Override
    public ReplyHolderViewImpl bindNormalText() {
        tv_tsReason = getView(R.id.tv_tsReason);
        tv_tsReason.setText(data.getTs_reason());
        return this;
    }

    public ReplyHolderViewImpl bindNormalText(View view) {
        tv_tsReason = (TextView) view.findViewById(R.id.tv_tsReason);
        tv_tsReason.setText(data.getTs_reason());
        return this;
    }

    @Override
    public ReplyHolderViewImpl bindMoreText() {
        return this;
    }

    public void setAnswerClick() {
        if (questionEntity != null) {
            AppStatistics.onEventCommon(context, "reply_detail;" + data.getAnswer_id());
            JumpPageManager.getManager().goReplyDetailActivity(context, data.getTask_id(), data.getAnswer_id());
        }
    }

    public ReplyHolderViewImpl setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
        return this;
    }

    public QuestionEntity questionEntity;


    private LinearLayout ll_zeze;
    private ImageView img_zeze;
    private TextView tv_zeze;
    private LinearLayout ll_pei;
    private ImageView img_pei;
    private TextView tv_pei;
    public LinearLayout ll_share;
    public LinearLayout ll_comment;
    public TextView tv_comment;
    LikeDisLikeHandler likeDisLikeHandler_like;
    LikeDisLikeHandler likeDisLikeHandler_dislike;

    @Override
    public ReplyHolderViewImpl bindBottom() {
        ll_zeze = (LinearLayout) findViewById(R.id.ll_zeze);
        img_zeze = (ImageView) findViewById(R.id.img_zeze);
        tv_zeze = (TextView) findViewById(R.id.tv_zeze);
        ll_pei = (LinearLayout) findViewById(R.id.ll_pei);
        img_pei = (ImageView) findViewById(R.id.img_pei);
        tv_pei = (TextView) findViewById(R.id.tv_pei);
        ll_share = (LinearLayout) findViewById(R.id.ll_share);
        ll_comment = (LinearLayout) findViewById(R.id.ll_comment);
        tv_comment = (TextView) findViewById(R.id.tv_comment);
        likeDisLikeHandler_like = new LikeDisLikeHandler(getContext(), R.mipmap.how_zan_icon_highlighted, R.mipmap.how_zan_icon);
        likeDisLikeHandler_dislike = new LikeDisLikeHandler(getContext(), R.mipmap.how_pei_icon_highlighted, R.mipmap.how_pei_icon);

        setBottomData();
        return this;
    }


    @Override
    public ReplyHolderViewImpl bindRoot() {

        view_reply_answer_root = getView(R.id.view_reply_answer_root);


        if (view_reply_answer_root != null)
            view_reply_answer_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAnswerClick();

                }
            });
        if (data != null &&
                data.getAnswer_id() != null) {
            if (view_reply_answer_root != null)
                view_reply_answer_root.setVisibility(View.VISIBLE);
            new MutiSpuHolderViewImpl(mutiTypeViewHolder, position, data.getAnswer_data()).bindMutiSpu();
            SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
            AnswerInfoEntity answer_info = data;
            sellerInfoEntity.setSeller_id(answer_info.getSeller_id());
            sellerInfoEntity.setHeadimg(answer_info.getHeadimg());
            sellerInfoEntity.setNickname(answer_info.getNickname());
            sellerInfoEntity.setFamous_type(answer_info.getFamous_type());
            sellerInfoEntity.setAce_txt(answer_info.getAce_txt());
            sellerInfoEntity.setGrade_title(answer_info.getGrade_title());
            new SellerHolderViewImpl(mutiTypeViewHolder, position, sellerInfoEntity).bindSellerViews();

            bindBottom();
            bindNormalText();
            bindVedioImages();
            bindReadNumber();

        } else {
            if (view_reply_answer_root != null)
                view_reply_answer_root.setVisibility(View.GONE);
            bindVedioImages();
        }


        return this;
    }

    public ViewGroup ll_reply_content_imgs;
    public View view_vedio_root;
    public View ll_reply_content_imgs_root;
    private ImageView img_vedio_covery;
    private TextView tv_imgs_num;


    @Override
    public BaseHolderViewImpl bindVedioImages() {
        ll_reply_content_imgs = getView(R.id.ll_reply_content_imgs);
        ll_reply_content_imgs_root = getView(R.id.ll_reply_content_imgs_root);
        tv_imgs_num = getView(R.id.tv_imgs_num);
        view_vedio_root = getView(R.id.view_vedio_root);
        img_vedio_covery = getView(R.id.img_vedio_covery);
        ll_reply_content_imgs_root.setVisibility(View.GONE);
        view_vedio_root.setVisibility(View.GONE);
        tv_imgs_num.setVisibility(View.GONE);

        LogUtil.i(TAG, "BaseHolderViewImpldata" + data);

        if (data == null)
            return this;

        UploadMediaEntity answer_img_vedio = data.getMediaEntity();
        if (answer_img_vedio != null && answer_img_vedio.getType() != null) {

            if (data.getMediaEntity().isImage()) {

                ll_reply_content_imgs_root.setVisibility(View.VISIBLE);
                LogUtil.i(TAG, "BaseHolderViewImplgetReplyImgsParent" + data.getMediaEntity().getType() + data.getMediaEntity().getUrl());
                LogUtil.i(TAG, "BaseHolderViewImplgetReplyImgsParent" + ll_reply_content_imgs.getChildCount());

                if (data.getMediaEntity().getUrl().size() > 3) {
                    tv_imgs_num.setVisibility(View.VISIBLE);
                    tv_imgs_num.setText(data.getMediaEntity().getUrl().size() + "+");
                }

                for (int i = 0; i < ll_reply_content_imgs.getChildCount(); i++) {
                    ImageView childAt = (ImageView) ll_reply_content_imgs.getChildAt(i);

                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();

                    if (i == data.getMediaEntity().getUrl().size() - 1) {
                        layoutParams.setMargins(0, 0, 0, 0);
                    } else {

                        if (i < ll_reply_content_imgs.getChildCount() - 1)
                            layoutParams.setMargins(0, 0, AppUtil.dip2px(8), 0);
                    }
                    childAt.setLayoutParams(layoutParams);
                    if (i <= data.getMediaEntity().getUrl().size() - 1) {
                        final int j = i;
                        childAt.setVisibility(View.VISIBLE);
                        ImageLoadUtils.loadListimg(context, data.getMediaEntity().getUrl().get(i), childAt);
                        childAt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                JumpPageManager.getManager().goBigImgActivity(context, (ArrayList<String>) data.getMediaEntity().getUrl(), j);
                            }
                        });
                    } else {
                        childAt.setVisibility(View.GONE);
                    }

                }

            } else if (answer_img_vedio.isVedio()) {
                view_vedio_root.setVisibility(View.VISIBLE);
                ImageLoadUtils.loadListimg(context, answer_img_vedio.getCover_image(), img_vedio_covery);
                img_vedio_covery.setTag(R.id.cover_image, answer_img_vedio);
                img_vedio_covery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UploadMediaEntity tag = (UploadMediaEntity) v.getTag(R.id.cover_image);
                        JumpPageManager.getManager().goVideoActivity(context, tag.getUrl().get(0), tag.getCover_image(),img_vedio_covery);
//                        PictureSelector.config((Activity) context).externalPictureVideo((String) v.getTag());

                    }
                });
            }
        }


        return this;
    }

    private TextView tv_answer_view_num;
    private TextView tv_action;

    @Override
    public BaseHolderViewImpl bindReadNumber() {
        tv_answer_view_num = getView(R.id.tv_answer_view_num);
        tv_action = getView(R.id.tv_action);
        if (tv_answer_view_num != null)
            tv_answer_view_num.setText(data.getAnswer_view_num());
        AnswerActionEntity action = data.getAction();
        if (action == null) {
            tv_action.setText("");
            return this;
        }
        String timeBefore = "";
        try {
            long time_now = TimeManager.getManager().getServer_time();
            long time_show = Long.parseLong(action.getTime_stamp());

            LogUtil.i(TAG, "time_now" + time_now + "-->time_show" + time_show + "-->" + (time_now - time_show));
            if ((time_now - time_show) > 5 * 60) {
                timeBefore = "";
            } else {
                timeBefore = FormatUtils.getTimeBefore(Long.parseLong(action.getTime_stamp()), TimeManager.getManager().getServer_time());
            }
        } catch (Exception ee) {
            timeBefore = "";
        }

        String show = String.format("%s%s%s", timeBefore, action.getNickname(), action.getAction_type());


        LogUtil.i(TAG, action.getIs_me() + "-->" + action.isMe() + "-->" + timeBefore + "-->" + action.getNickname() + "-->" + action.getAction_type());


        SpannableString spannableString = StringUtils.addColorSpan(show, timeBefore.length(), action.getNickname().length(), ContextCompat.getColor(context, R.color.theme_red));
        tv_action.setText(spannableString);


        return this;
    }

    public void share() {
        AppStatistics.onEventCommon(context, "share;" + data.getTask_id());
        new ShareHandler((Activity) getContext()).setRes(data.getShare_model(), new ShareHandler.ShareCallback() {
            @Override
            public void share(boolean issuccess) {

            }
        }).shareAtWindow(mutiTypeViewHolder.parent);
    }

    public void setBottomData() {
        if (data == null) {
            return;
        }
        setLikeTv(data.getAnswer_like_num(), tv_zeze, data.getAnswer_like() == 1, img_zeze);
        setDisLikeTv(data.getAnswer_dislike_num(), tv_pei, data.getAnswer_dislike() == 1, img_pei);
        setCommentNum(tv_comment, data.getComment_count());

        if (ll_pei != null) {
            ll_pei.setTag(data);
            ll_pei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (BaseApplication.UUID == null) {
                        RegisterActivity.getRegister(context);
                        return;
                    }
                    AppStatistics.onEventCommon(context, "pei;" + data.getTask_id());
                    AppStatistics.onEvent(context, "pei", "reply_id", data.getAnswer_id());

                    AnswerInfoEntity data = (AnswerInfoEntity) v.getTag();

                    data.setAnswer_dislike(data.getAnswer_dislike() == 1 ? 0 : 1);
                    if (data.getAnswer_dislike() == 1)
                        data.setAnswer_dislike_num(data.getAnswer_dislike_num() + 1);
                    else
                        data.setAnswer_dislike_num(data.getAnswer_dislike_num() - 1);
                    setDisLikeTv(data.getAnswer_dislike_num(), (TextView) ((LinearLayout) v).getChildAt(1),
                            data.getAnswer_dislike() == 1, (ImageView) ((LinearLayout) v).getChildAt(0));

                    likeDisLikeHandler_dislike.viewSelectShow(((LinearLayout) v).getChildAt(0), data.getAnswer_dislike() == 1);
                    if (!ClickUtils.isFastDoubleClick()) {
                        likeDisLikeHandler_dislike.LikeDisLikeAnswerReply(SpMananger.getUUID(), "dislike", data.getAnswer_dislike(), data.getAnswer_id());

                    }
                    if (context instanceof ReplyDetailActivity)
                        AppRxBus.getInstance().post(new BottomDoEvent().setTask_id(data.getTask_id()).setAnswer_id(data.getAnswer_id()));

                }
            });
        }

        if (ll_zeze != null) {
            ll_zeze.setTag(data);
            ll_zeze.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (BaseApplication.UUID == null) {
                        RegisterActivity.getRegister(context);
                        return;
                    }
                    AppStatistics.onEventCommon(context, "zeze;" + data.getTask_id());
                    AppStatistics.onEvent(context, "zeze", "reply_id", data.getAnswer_id());

                    AnswerInfoEntity data = (AnswerInfoEntity) v.getTag();


                    data.setAnswer_like(data.getAnswer_like() == 1 ? 0 : 1);
                    if (data.getAnswer_like() == 1)
                        data.setAnswer_like_num(data.getAnswer_like_num() + 1);
                    else
                        data.setAnswer_like_num(data.getAnswer_like_num() - 1);
                    setLikeTv(data.getAnswer_like_num(), (TextView) ((LinearLayout) v).getChildAt(1), data.getAnswer_like() == 1, (ImageView) ((LinearLayout) v).getChildAt(0));

                    likeDisLikeHandler_like.viewSelectShow(((LinearLayout) v).getChildAt(0), data.getAnswer_like() == 1);
                    if (!ClickUtils.isFastDoubleClick()) {
                        likeDisLikeHandler_like.LikeDisLikeAnswerReply(SpMananger.getUUID(), "like", data.getAnswer_like(), data.getAnswer_id());

                    }
                    if (context instanceof ReplyDetailActivity)
                        AppRxBus.getInstance().post(new BottomDoEvent().setTask_id(data.getTask_id()).setAnswer_id(data.getAnswer_id()));


                }
            });
        }
        if (ll_share != null) {
            ll_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data != null)
                        new ShareHandler((Activity) getContext()).setRes(data.getShare_model(), new ShareHandler.ShareCallback() {
                            @Override
                            public void share(boolean issuccess) {

                            }
                        }).shareAtWindow(v);
                }
            });
        }

        setReplay(data);


    }

    public void setReplay(AnswerInfoEntity answerInfoEntity) {
        if (answerInfoEntity == null)
            return;
        if (ll_comment != null) {
            ll_comment.setTag(answerInfoEntity);
            ll_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppStatistics.onEventCommon(context, "comment;" + data.getTask_id());

                    AnswerInfoEntity tag = (AnswerInfoEntity) v.getTag();
                    JumpPageManager.getManager().goCommentActivity(getContext(), tag.getAnswer_id(),tag.getTask_id());
                }
            });
        }


    }

    private void setLikeTv(int num, TextView tv, boolean islike, ImageView imageView) {
        tv.setText(new StringBuffer().append("啧啧 ").append(Constant.CENTER_POINT).append(" ").append(num));

        if (islike) {
            tv.setTextColor(ContextCompat.getColor(context, R.color.theme_red));
            imageView.setImageResource(R.mipmap.how_zan_icon_highlighted);
        } else {
            tv.setTextColor(ContextCompat.getColor(context, R.color.grey_555555));
            imageView.setImageResource(R.mipmap.how_zan_icon);
        }

    }

    private void setDisLikeTv(int num, TextView tv, boolean isdislike, ImageView imageView) {
        tv.setText(new StringBuffer().append("呸 ").append(Constant.CENTER_POINT).append(" ").append(num));
        if (isdislike) {
            tv.setTextColor(ContextCompat.getColor(context, R.color.theme_red));

            imageView.setImageResource(R.mipmap.how_pei_icon_highlighted);
        } else {
            tv.setTextColor(ContextCompat.getColor(context, R.color.grey_555555));
            imageView.setImageResource(R.mipmap.how_pei_icon);
        }
    }

    public void setCommentNum(TextView tv, int num) {

        String showtext = "评论";
        tv.setText(num > 0 ? showtext + String.format("(%d)", num) : showtext);

    }


}
