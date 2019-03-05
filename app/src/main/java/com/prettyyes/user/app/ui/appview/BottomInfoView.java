package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
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
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.model.v8.AnswerInfoEntity;

/**
 * Created by chengang on 2017/7/10.
 */

public class BottomInfoView extends AbsLinearlayoutView {
    private static final String TAG = "BottomInfoView";

    public BottomInfoView(Context context) {
        super(context);
    }

    public BottomInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_bottom_info;
    }

    private int res_unselect_like;
    private int res_unselect_dislike;
    private int res_select_like;
    private int res_select_dislike;

    @Override
    public void initViews() {

        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setClipChildren(false);

        lin_zeze = (LinearLayout) getView(R.id.lin_zeze);
        img_bottomv2_zeze = (ImageView) getView(R.id.img_bottomv2_zeze);
        tv_bottomv2_zeze = (TextView) getView(R.id.tv_bottomv2_zeze);
        lin_pei = (LinearLayout) getView(R.id.lin_pei);
        img_bottomv2_pei = (ImageView) getView(R.id.img_bottomv2_pei);
        tv_bottomv2_pei = (TextView) getView(R.id.tv_bottomv2_pei);
        lin_comment = (LinearLayout) getView(R.id.lin_comment);
        tv_comment = (TextView) getView(R.id.tv_comment);
        res_select_like = R.mipmap.how_zan_icon_highlighted;
        res_select_dislike = R.mipmap.how_pei_icon_highlighted;
        res_unselect_like = R.mipmap.how_zan_icon;
        res_unselect_dislike = R.mipmap.how_pei_icon;
        likeDisLikeHandler_like = new LikeDisLikeHandler(getContext(), res_select_like, res_unselect_like);
        likeDisLikeHandler_dislike = new LikeDisLikeHandler(getContext(), res_select_dislike, res_unselect_dislike);

    }

    @Override
    public void setDataByModel(Object data) {

    }

    LikeDisLikeHandler likeDisLikeHandler_like;
    LikeDisLikeHandler likeDisLikeHandler_dislike;
    private LinearLayout lin_zeze;
    private ImageView img_bottomv2_zeze;
    private TextView tv_bottomv2_zeze;
    private LinearLayout lin_pei;
    private ImageView img_bottomv2_pei;
    private TextView tv_bottomv2_pei;
    private LinearLayout lin_comment;
    private TextView tv_comment;

    public void setData(final AnswerInfoEntity data) {
        if (data == null) {
            this.setVisibility(GONE);
            return;
        }
        this.setVisibility(VISIBLE);
        setLikeTv(data.getAnswer_like_num(), tv_bottomv2_zeze, data.getAnswer_like() == 1, img_bottomv2_zeze);
        setDisLikeTv(data.getAnswer_dislike_num(), tv_bottomv2_pei, data.getAnswer_dislike() == 1, img_bottomv2_pei);
        setTipNum(data.getComment_count());

        lin_pei.setTag(data);
        lin_pei.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(getActivity());
                    return;
                }
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
                    likeDisLikeHandler_dislike.LikeDisLikeAnswerReply(((BaseActivity) getActivity()).getUUId(), "dislike", data.getAnswer_dislike(), data.getAnswer_id());

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
                AnswerInfoEntity data = (AnswerInfoEntity) v.getTag();


                data.setAnswer_like(data.getAnswer_like() == 1 ? 0 : 1);
                if (data.getAnswer_like() == 1)
                    data.setAnswer_like_num(data.getAnswer_like_num() + 1);
                else
                    data.setAnswer_like_num(data.getAnswer_like_num() - 1);
                setLikeTv(data.getAnswer_like_num(), (TextView) ((LinearLayout) v).getChildAt(1), data.getAnswer_like() == 1, (ImageView) ((LinearLayout) v).getChildAt(0));

                likeDisLikeHandler_like.viewSelectShow(((LinearLayout) v).getChildAt(0), data.getAnswer_like() == 1);
                if (!ClickUtils.isFastDoubleClick()) {
                    likeDisLikeHandler_like.LikeDisLikeAnswerReply(((BaseActivity) getActivity()).getUUId(), "like", data.getAnswer_like(), data.getAnswer_id());

                }

            }
        });

        setReplay(data);


    }

    public void setReplay(AnswerInfoEntity data) {
        if (data == null)
            return;
        lin_comment.setTag(data);
        lin_comment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerInfoEntity tag = (AnswerInfoEntity) v.getTag();
                JumpPageManager.getManager().goCommentActivity(getContext(), tag.getAnswer_id(),tag.getTask_id());
            }
        });


    }

    private void setLikeTv(int num, TextView tv, boolean islike, ImageView imageView) {
        tv.setText(new StringBuffer().append("啧啧 ").append(Constant.CENTER_POINT).append(" ").append(num));

        if (islike) {
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.theme_red));
            imageView.setImageResource(R.mipmap.how_zan_icon_highlighted);
        } else {
            imageView.setImageResource(R.mipmap.how_zan_icon);
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.grey_555555));

        }

    }

    private void setDisLikeTv(int num, TextView tv, boolean isdislike, ImageView imageView) {
        tv.setText(new StringBuffer().append("呸 ").append(Constant.CENTER_POINT).append(" ").append(num));
        if (isdislike) {
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.theme_red));

            imageView.setImageResource(R.mipmap.how_pei_icon_highlighted);
        } else {
            imageView.setImageResource(R.mipmap.how_pei_icon);
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.grey_555555));

        }
    }

    public void setTipNum(int num) {

        String showtext = "评论";
        tv_comment.setText(num > 0 ? showtext + String.format("(%d)", num) : showtext);

    }

}
