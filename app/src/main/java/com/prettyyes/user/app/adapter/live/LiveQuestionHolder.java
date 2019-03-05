package com.prettyyes.user.app.adapter.live;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.view.tvbtnetv.ExpandableTextView;
import com.prettyyes.user.core.LikeDisLikeHandler;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.LiveTaskInfo;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/9/18.
 */

public class LiveQuestionHolder extends MutiTypeViewHolder<LiveTaskInfo> {
    LikeDisLikeHandler likeDisLikeHandler_like;
    public long time = 5 * 60;

    private List<SellerInfoEntity> live_sellers;

    public LiveQuestionHolder(Context context, ViewGroup parent, int layout_id) {
        super(context, parent, layout_id);
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas((Activity) context);
        if (intentParmas != null && intentParmas.getActInfo() != null) {
            live_sellers = intentParmas.getActInfo().getSeller_list();
        }

    }

    public LikeDisLikeHandler getLikeDisLikeHandler_like() {
        if (likeDisLikeHandler_like == null)
            likeDisLikeHandler_like = new LikeDisLikeHandler(context, R.mipmap.ic_zan_live, R.mipmap.ic_zan_live_unselect);
        return likeDisLikeHandler_like;
    }

    public LiveQuestionHolder(ViewGroup parent) {
        super(parent, R.layout.item_rv_live_question);
        this.context = parent.getContext();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas((Activity) context);
        if (intentParmas != null && intentParmas.getActInfo() != null) {
            live_sellers = intentParmas.getActInfo().getSeller_list();
        }
    }

    private boolean contansMe(List<SellerInfoEntity> sellerInfoEntities) {
        if (sellerInfoEntities == null)
            return false;
        for (int i = 0; i < sellerInfoEntities.size(); i++) {
            if (sellerInfoEntities.get(i).getSeller_id().equals(SpMananger.getUid())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void bindData(final LiveTaskInfo modle, int position, RecyclerView.Adapter adpter) {
        String create_time = modle.getCreate_time();
        List<AnswerInfoEntity> suit_list = modle.getSuit_list();
        String show_create_time = create_time;
        if (suit_list != null && suit_list.size() > 0 && suit_list.get(0).getTs_create_time() != null)
            show_create_time = suit_list.get(0).getTs_create_time();

        if ((TimeManager.getManager().getServer_time() - modle.getPush_time() < time) ||
                (TimeManager.getManager().getServer_time() - FormatUtils.StringToDate(show_create_time)) < time) {
            tv_time_que.setVisibility(View.GONE);
        } else {
            tv_time_que.setVisibility(View.VISIBLE);
            tv_time_que.setText(getLiveShowTime(create_time));

        }
        getLikeDisLikeHandler_like().viewSelectShowNoAnim(img_zan_que, modle.is_have_zeze());

        tv_que.setText(modle.getDesc());
        tv_que.setText(modle.getDesc());
        tv_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMoreSpuReply(context, modle.getTask_id());
            }
        });
        tv_nickname_que.setText(modle.getNickname());

        if (modle.isMyQue()) {
            view_que.setBackgroundResource(R.drawable.left_chat_me);
        } else {
            view_que.setBackgroundResource(R.drawable.left_chat);
        }
        view_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMoreSpuReply(context, modle.getTask_id());
            }
        });


        final List<String> imgs = StringUtils.getSplitArray(modle.getPic_list());
        tv_more.setVisibility(View.GONE);
        img_que.setVisibility(View.GONE);
        if (imgs != null && imgs.size() > 0) {
            img_que.setVisibility(View.VISIBLE);
            if (imgs.size() > 1)
                tv_more.setVisibility(View.VISIBLE);
            else
                tv_more.setVisibility(View.GONE);

            ImageLoadUtils.loadListimg(context, imgs.get(0), img_que);
            img_que.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpPageManager.getManager().goBigImgActivity(context, (ArrayList<String>) imgs, 0);
                }
            });
        }
        if (!modle.haveReply() && live_sellers != null && contansMe(live_sellers)) {
            btn_reply.setVisibility(View.VISIBLE);
        } else
            btn_reply.setVisibility(View.GONE);

        btn_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolReplyActivity(context, modle.getTask_id(), modle.getDesc());
            }
        });
        img_zan_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modle.setLike(modle.is_have_zeze() ? 0 : 1);
                getLikeDisLikeHandler_like().viewSelectShow(v, modle.is_have_zeze());
                if (!ClickUtils.isFastDoubleClick()) {
                    getLikeDisLikeHandler_like().LikeDisLikeQuestion(SpMananger.getUUID(), "like", modle.getLike(), modle.getTask_id());

                }

            }
        });


    }

    @Override
    public void bindView() {
        tv_time_que = findViewById(R.id.tv_time_que);
        view_que = findViewById(R.id.view_que);
        img_que = findViewById(R.id.img_que);
        tv_more = findViewById(R.id.tv_more);
        tv_que = findViewById(R.id.tv_que);
        btn_reply = findViewById(R.id.btn_reply);
        img_zan_que = findViewById(R.id.img_zan_que);
        img_left = findViewById(R.id.img_left);
        tv_nickname_que = findViewById(R.id.tv_nickname_que);
        tv_look_more_que = findViewById(R.id.tv_look_more_que);
    }

    private TextView tv_time_que;
    private RelativeLayout view_que;
    private ImageView img_que;
    private TextView tv_more;
    private ExpandableTextView tv_que;
    private ImageView btn_reply;
    private ImageView img_zan_que;
    private ImageView img_left;
    private TextView tv_nickname_que;
    private TextView tv_look_more_que;


    public String getLiveShowTime(String create_time) {
        String res;
        if (create_time == null)
            return "";

        if (TimeManager.getManager().getServer_time() - FormatUtils.StringToDate(create_time) < 60 * 60 * 24) {

            res = FormatUtils.StringToDateAmPm(create_time);
        } else {
            res = create_time;

        }
        return res;

    }
}
