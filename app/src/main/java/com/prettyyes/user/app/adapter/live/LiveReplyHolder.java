package com.prettyyes.user.app.adapter.live;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.holder_presenter.ReplyHolderViewImpl;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.app.view.tvbtnetv.ExpandableTextView;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.AnswerDataEntity;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.LiveTaskInfo;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;

/**
 * Created by chengang on 2017/9/18.
 */

public class LiveReplyHolder extends LiveQuestionHolder {

    public LiveReplyHolder(ViewGroup parent) {
        super(parent.getContext(), parent, R.layout.item_rv_live_reply);
    }

    @Override
    public void bindData(final LiveTaskInfo modle, int position, RecyclerView.Adapter adpter) {
        super.bindData(modle,position,adpter);
        final AnswerInfoEntity answerInfoEntity = modle.getSuit_list().get(0);
        tv_reply_que.setText("问题：" + modle.getDesc());
        ImageLoadUtils.loadHeadImg(context, answerInfoEntity.getHeadimg(), img_head);
        tv_nickname.setText(answerInfoEntity.getNickname());
        tv_tsReason.setText(answerInfoEntity.getTs_reason());
        tv_tsReason.setText(answerInfoEntity.getTs_reason());

        String create_time = answerInfoEntity.getTs_create_time();

        if (TimeManager.getManager().getServer_time() - modle.getPush_time() < 5 * 60 ||
                (TimeManager.getManager().getServer_time() - FormatUtils.StringToDate(create_time)) < time) {
            tv_time.setVisibility(View.GONE);
        } else {
            tv_time.setVisibility(View.VISIBLE);
            tv_time.setText(getLiveShowTime(create_time));

        }

        view_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerInfoEntity != null)
                    JumpPageManager.getManager().goReplyDetailActivity(context, modle.getTask_id(), answerInfoEntity.getAnswer_id());
            }
        });

        tv_tsReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerInfoEntity != null)
                    JumpPageManager.getManager().goReplyDetailActivity(context, modle.getTask_id(), answerInfoEntity.getAnswer_id());

            }
        });
//
//        tv_look_more_reply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (answerInfoEntity != null)
//                    JumpPageManager.getManager().goReplyDetailActivity(context, modle.getTask_id(), answerInfoEntity.getAnswer_id());
//
//            }
//        });

//        if (tv_tsReason.isShowNotAll()) {
//            tv_look_more_reply.setVisibility(View.VISIBLE);
//        } else {
//            tv_look_more_reply.setVisibility(View.GONE);
//
//        }

        getLikeDisLikeHandler_like().viewSelectShowNoAnim(img_zan, answerInfoEntity.getAnswer_like() == 1);


        img_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answerInfoEntity.setAnswer_like(answerInfoEntity.getAnswer_like() == 1 ? 0 : 1);
                getLikeDisLikeHandler_like().viewSelectShow(v, answerInfoEntity.getAnswer_like() == 1);
                if (!ClickUtils.isFastDoubleClick()) {
                    getLikeDisLikeHandler_like().LikeDisLikeAnswerReply(SpMananger.getUUID(), "like", answerInfoEntity.getAnswer_like(), answerInfoEntity.getAnswer_id());

                }

            }
        });
        view_kol_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerInfoEntity.getSeller_id() != null)
                    JumpPageManager.getManager().goKolInfoActivity(context, answerInfoEntity.getSeller_id());
            }
        });

        ReplyHolderViewImpl replyHolderView = new ReplyHolderViewImpl(new MutiTypeViewHolder<AnswerInfoEntity>(this.getRootView()) {
            @Override
            public void bindData(AnswerInfoEntity modle, int position, RecyclerView.Adapter adpter) {
            }

            @Override
            public void bindView() {

            }
        }, position, answerInfoEntity);
        replyHolderView.bindVedioImages();

        final AnswerDataEntity answer_data = answerInfoEntity.getAnswer_data();
        if (answer_data != null && answer_data.getModule_id() != null) {
            view_spu.setVisibility(View.VISIBLE);
            replyHolderView.ll_reply_content_imgs_root.setVisibility(View.GONE);
            replyHolderView.view_vedio_root.setVisibility(View.GONE);
            ImageLoadUtils.loadListimg(context, answerInfoEntity.getAnswer_data().getMain_img(), img_main);
            if (answer_data.getSpu_type().equals(TYPE_PAPER)) {
                tv_paper_title.setText(answer_data.getSpu_name());
                view_paper.setVisibility(View.VISIBLE);
                view_goods.setVisibility(View.GONE);
            } else {
                view_paper.setVisibility(View.GONE);
                view_goods.setVisibility(View.VISIBLE);
                tv_goods_name.setText(answer_data.getSpu_name());
                tv_goods_price.setText(StringUtils.getPrice(answer_data.getSpu_price()));
            }
            view_spu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpPageManager.getManager().goSkuActivity(context, answer_data.getSpu_type(), answer_data.getModule_id());
                }
            });

        } else {
            view_spu.setVisibility(View.GONE);

        }


    }

    @Override
    public void bindView() {
        super.bindView();
        tv_time = findViewById(R.id.tv_time);
        view_reply = findViewById(R.id.view_reply);
        img_main = findViewById(R.id.img_main);
        tv_tsReason = findViewById(R.id.tv_tsReason);
        img_zan = findViewById(R.id.img_zan);
        img_head = findViewById(R.id.img_head);
        tv_nickname = findViewById(R.id.tv_nickname);
        view_kol_info = findViewById(R.id.view_kol_info);
        view_spu = findViewById(R.id.view_spu);
        view_goods = findViewById(R.id.view_goods);
        tv_reply_que = findViewById(R.id.tv_reply_que);
        view_paper = findViewById(R.id.view_paper);
        tv_paper_title = findViewById(R.id.tv_paper_title);
        tv_goods_name = findViewById(R.id.tv_goods_name);
        tv_goods_price = findViewById(R.id.tv_goods_price);

    }

    private TextView tv_time;
    private LinearLayout view_reply;
    private RoundImageView img_main;
    private ExpandableTextView tv_tsReason;
    private ImageView img_zan;
    private ImageView img_head;
    private TextView tv_nickname;
    private View view_kol_info;
    private TextView tv_reply_que;
    private View view_spu;
    private View view_goods;
    private View view_paper;
    private TextView tv_paper_title;
    private TextView tv_goods_name;
    private TextView tv_goods_price;


}
