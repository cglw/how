package com.prettyyes.user.app.adapter.holder_presenter;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.CollectRequest;
import com.prettyyes.user.api.netXutils.requests.TaskFilterRequest;
import com.prettyyes.user.api.netXutils.response.CollectRes;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.QuestionTextView;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.app.ui.comment.MoreReplyActivity;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.LikeDisLikeHandler;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.QuestionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/8/10.
 */

public class QuestionHolderViewImpl extends BaseHolderViewImpl<QuestionEntity> implements QuestionHolderView {


    public LikeDisLikeHandler likeDisLikeHandler;
    private final static int tag_index = 3 << 24;
    private final static int tag_view = 4 << 24;


    public void setLikeDisLikeHandler(LikeDisLikeHandler likeDisLikeHandler) {
        this.likeDisLikeHandler = likeDisLikeHandler;
    }

    public QuestionHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder) {
        super(mutiTypeViewHolder);
    }

    public QuestionHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder, int position, QuestionEntity data) {
        super(mutiTypeViewHolder, position, data);
    }

    public int que_color = 0;


    public QuestionHolderViewImpl setQue_color(int que_color) {
        this.que_color = que_color;
        return this;
    }


    public TextView tv_que_name;
    private ImageView img_que;
    private TextView tv_more;
    public QuestionTextView tv_que_desc;
    private View view_question_root;
    private TextView tv_reply_right_num;

    @Override
    public QuestionHolderViewImpl bindQuestion() {
        tv_que_name = getView(R.id.tv_que_name);
        img_que = getView(R.id.img_que);
        tv_more = getView(R.id.tv_more);
        tv_que_desc = getView(R.id.tv_que_desc);
        view_question_root = getView(R.id.view_question_root);
        tv_reply_right_num = getView(R.id.tv_reply_right_num);
        setQuestion();

        if (likeDisLikeHandler == null)
            likeDisLikeHandler = new LikeDisLikeHandler(context, R.mipmap.how_home_liked, R.mipmap.how_home_like);

        return this;
    }

    public View getView_question_root() {
        return view_question_root;
    }


    public void itemClick(QuestionEntity questionEntity, View view) {
        if (ZBundleCore.getInstance().getTopActivity().getClass().equals(MoreReplyActivity.class)) {
            return;
        }
        if (questionEntity == null)
            return;
        AppStatistics.onEventCommon(context, "task;" + questionEntity.getTask_id());
        JumpPageManager.getManager().goMoreSpuReply(context, questionEntity.getTask_id());

    }

    public void setQuestion() {

        tv_que_name.setText(data.getNickname());
        if (view_question_root != null) {
            if (que_color != 0)
                view_question_root.setBackgroundColor(que_color);

            view_question_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick(data, v);
                }
            });
        }

        if (tv_que_desc != null) {

            tv_que_desc.setTextWithData(new QuestionTextView.QuestionInnerModel()
                    .setDesc(data.getDesc())
                    .setHash_tag(data.getHash_tag())
                    .setTopic_hash_tag(data.getTopic_hash_tag())
                    .setTask_act_id(data.getTask_act_id())
                    .setTopic_id(data.getTopic_id()));
        }

        if (tv_reply_right_num != null) {
            if (data.getTask_count() > 0) {
                tv_reply_right_num.setText(String.format("%d 回复 >>", data.getTask_count()));
            } else {
                tv_reply_right_num.setText("");
            }
        }


        final List<String> imgs = StringUtils.getSplitArray(data.getPic_list());
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

    }

    public ImageView img_close;
    public TextView tv_score;
    public TextView tv_reply_good_buy;
    public ImageView img_invite;
    public ImageView img_collect;
    public TextView tv_want_reply;


    @Override
    public QuestionHolderViewImpl bindQueOther() {
        LogUtil.i(TAG, mutiTypeViewHolder + "mutiTypeViewHolder");
        img_close = getView(R.id.img_close);
        LogUtil.i(TAG, "img_close" + img_close);

        img_invite = getView(R.id.img_invite);
        img_collect = getView(R.id.img_collect);
        tv_score = getView(R.id.tv_score);
        tv_reply_good_buy = getView(R.id.tv_reply_good_buy);
        tv_want_reply = getView(R.id.tv_want_reply);
        if (tv_score != null) {
            View parent = (View) tv_score.getParent();
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppStatistics.onEventCommon(context, "task_score;" + data.getTask_id());
                    JumpPageManager.getManager().goHowToAsk(context, true);
                }
            });
        }

        if (img_invite != null)
            img_invite.setTag(data.getShare_model());

        if (img_collect != null) {
            if (data.getLike_task() == 1) {
                img_collect.setImageResource(R.mipmap.how_home_liked);

            } else {
                img_collect.setImageResource(R.mipmap.how_home_like);
            }
            img_collect.setTag(data);
        }
        if (img_close != null) {
            img_close.setTag(data);
            img_close.setTag(tag_index, position);
        }
        LogUtil.i(TAG, "tv_want_reply+setdata");

        if (tv_want_reply != null) {
            tv_want_reply.setTag(data);


            if (data.haveReply()) {
//                tv_want_reply.setVisibility(View.GONE);
                tv_want_reply.setText("已回答");
                tv_want_reply.setEnabled(false);
                tv_want_reply.setBackgroundResource(R.drawable.bg_round_grey_100);

            } else {
//                tv_want_reply.setVisibility(View.VISIBLE);
                tv_want_reply.setText("我也要答");
                tv_want_reply.setEnabled(true);
                tv_want_reply.setBackgroundResource(R.drawable.bg_round_red_round100);
                if (data.isMyQue()) {
                    tv_want_reply.setText("邀请回答");
                    tv_want_reply.setEnabled(true);
                    tv_want_reply.setBackgroundResource(R.drawable.bg_round_red_round100);
                }


            }


        }
        if (tv_score != null)
            tv_score.setText(data.getPscore());
        if (tv_reply_good_buy != null)
            tv_reply_good_buy.setText(data.getTask_describe_text());
        setQueListener();
        return this;
    }

    public void reply(QuestionEntity questionEntity, String showText, View v) {

        if ("我也要答".equals(showText)) {
            AppStatistics.onEventCommon(context, "reply;" + questionEntity.getTask_id());
            JumpPageManager.getManager().goKolReplyActivity(context, questionEntity.getTask_id(), questionEntity.getDesc());
        } else if ("邀请回答".equals(showText)) {
            JumpPageManager.getManager().goInvateKolActivity(context, questionEntity.getTask_id());

        }
    }

    private void setQueListener() {
        LogUtil.i(TAG, "tv_want_reply+setQueListener" + tv_want_reply);


        if (tv_want_reply != null) {
            tv_want_reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = ((TextView) v).getText().toString();
                    reply(data, s, v);

                }
            });
        }

        if (img_collect != null)
            img_collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (JumpPageManager.getManager().checkUnlogin(context)) {
                        return;
                    }

                    AppStatistics.onEventCommon(context, "collect;" + data.getTask_id());
                    QuestionEntity tag = (QuestionEntity) v.getTag();
                    int res = (tag.getLike_task() == 0 ? 1 : 0);
                    likeDisLikeHandler.viewSelectShow(v, res == 1);
                    tag.setLike_task(res);
                    new CollectRequest().setIs_like(res + "").setSpu_type("task").setModule_id(tag.getTask_id()).post(new NetReqCallback<CollectRes>() {
                        @Override
                        public void apiRequestFail(String message, String method) {
                            AppUtil.showToastShort(message);
                        }

                        @Override
                        public void apiRequestSuccess(CollectRes collectRes, String method) {
                            if (collectRes.getInfo().equals("1")) {
                                ((BaseActivity) context).showSnack(R.string.collect_tasklike_succes);
                            } else {
                                ((BaseActivity) context).showSnack(R.string.collect_taskdislike_success);
                            }

                        }
                    });


                }
            });


        if (img_close != null)
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (JumpPageManager.getManager().checkUnlogin(context)) return;

                    final QuestionEntity tag = (QuestionEntity) v.getTag();
                    final int index = (int) v.getTag(tag_index);
                    DialogHelper.getInstance().getDialogNoCancel(R.string.task_filter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new TaskFilterRequest().setTask_id(tag.getTask_id()).post(new NetReqCallback<Object>() {
                                @Override
                                public void apiRequestFail(String message, String method) {
                                    AppUtil.showToastShort(message);
                                }

                                @Override
                                public void apiRequestSuccess(Object s, String method) {
                                    mutiTypeViewHolder.absMutiRvAdapter.remove(index);
                                }
                            });
                        }
                    }).show();
                }
            });

        if (img_invite != null) {
            img_invite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShareModel tag = (ShareModel) v.getTag();
                    tag.setType_id(data.getTask_id());
                    tag.setType("task");
                    tag.setTask_id(data.getTask_id());
                    AppStatistics.onEventCommon(context, "share;" + data.getTask_id());
                    JumpPageManager.getManager().goShareActivity(context, tag);
                }
            });
        }

    }
}
