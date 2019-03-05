package com.prettyyes.user.app.adapter.detail;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SystemApiImpl;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.appview.BottomDoView2;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.app.ui.appview.MutiSpuView;
import com.prettyyes.user.app.ui.appview.QuestionView2;
import com.prettyyes.user.app.view.pupopwindow.InputDialog;
import com.prettyyes.user.app.view.tvbtnetv.ExpandableTextView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.InputCallback;
import com.prettyyes.user.core.event.ReplyQuestionSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.CommentChildrenBean;
import com.prettyyes.user.model.v8.QuestionEntity;
import com.prettyyes.user.model.v8.SellerInfoEntity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/9
 * Description: Nothing
 */
public class CommonReplyAdapter<T> extends AbsRecycleAdapter<T> implements ExpandableTextView.OnExpandListener {

    private static final String TAG = "MCmtAdapter";

    private int etvWidth;
    private SparseArray<Integer> mPositionsAndStates = new SparseArray<>();


    public KolSimpleInfoView kolinfoView_mocmtAdp;
    public ExpandableTextView tv_mocmtAdp_reason;
    public MutiSpuView mutiSpuView;
    public BottomDoView2 bottomView2_mocmtAdp;
    public LinearLayout lin_mocmtAdp_layout;
    public LinearLayout ll_comment;
    public View view_line;
    public QuestionView2 questionView2;

    public CommonReplyAdapter(Context context) {
        super(context, R.layout.item_rv_reply_common);
    }

    public CommonReplyAdapter(Context context, int layout) {
        super(context, layout);
        initRrfreshItem();
    }

    private void initRrfreshItem() {

//        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().toObservable(ReplyQuestionSuccessEvent.class).subscribe(new RxAction1<ReplyQuestionSuccessEvent>() {
//            @Override
//            public void callback(ReplyQuestionSuccessEvent replyQuestionSuccessEvent) {
//                for (int i = 0; i < CommonReplyAdapter.this.getItemCount(); i++) {
//                    T itemData = CommonReplyAdapter.this.getItemData(i);
//                    if (itemData instanceof QuestionEntity) {
//                        ((QuestionEntity) itemData).setMy_answer_replyed("1");
//                        CommonReplyAdapter.this.notifyItemChanged(i);
//                    } else {
//                        break;
//                    }
//
//                }
//            }
//        });

        ((BaseActivity)context).mSubscription=  AppRxBus.getInstance().subscribeEvent(new RxCallback<ReplyQuestionSuccessEvent>() {
            @Override
            protected void back(ReplyQuestionSuccessEvent replyQuestionSuccessEvent) {
                for (int i = 0; i < CommonReplyAdapter.this.getItemCount(); i++) {
                    T itemData = CommonReplyAdapter.this.getItemData(i);
                    if (itemData instanceof QuestionEntity) {
                        ((QuestionEntity) itemData).setMy_answer_replyed();
                        CommonReplyAdapter.this.notifyItemChanged(i);
                    } else {
                        break;
                    }

                }
            }
        });
    }


    public void goDetail(AnswerInfoEntity replyModel) {
        DataCenter.SELLRT_ID_CURRENT = Integer.parseInt(replyModel.getSeller_id());
        DataCenter.ANSWER_ID_CURRENT = Integer.parseInt(replyModel.getAnswer_id());
        JumpPageManager.getManager().goSkuActivity((BaseActivity) context, replyModel.getAnswer_type(), String.valueOf(replyModel.getModule_id()));

    }

    private void setReasonByEtv(final AnswerInfoEntity data, final int position) {
        tv_mocmtAdp_reason.setTvCallBac(new ExpandableTextView.TvCallBack() {
            @Override
            public void onclick(View view) {
                goDetail(data);
            }
        });
        //设置扩展textview
        final String content = data.getTs_reason();
        if (etvWidth == 0) {
            tv_mocmtAdp_reason.post(new Runnable() {
                @Override
                public void run() {
                    etvWidth = tv_mocmtAdp_reason.getWidth();
                }
            });
        }
        tv_mocmtAdp_reason.setTag(position);
        tv_mocmtAdp_reason.setExpandListener(this);
        Integer state = mPositionsAndStates.get(position);
        if (state == null) {
            state = 0;
        }
        tv_mocmtAdp_reason.updateForRecyclerView(content, etvWidth, state);
    }


    @Override
    public void onExpand(ExpandableTextView view) {
        Object obj = view.getTag();
        if (obj != null && obj instanceof Integer) {
            mPositionsAndStates.put((Integer) obj, view.getExpandState());
        }
    }

    @Override
    public void onShrink(ExpandableTextView view) {
        Object obj = view.getTag();
        if (obj != null && obj instanceof Integer) {
            mPositionsAndStates.put((Integer) obj, view.getExpandState());
        }
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final T data, final int position) {


    }

    public void setQuestionData(QuestionEntity questionData) {
        if (questionView2 != null)
            questionView2.setData(questionData);
    }

    public void setCenterData(AbsRecycleViewHolder holder, final AnswerInfoEntity data, int position) {
        mutiSpuView.setDataByModel(data.getAnswer_data());

        SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
        sellerInfoEntity.setSeller_id(data.getSeller_id());
        sellerInfoEntity.setHeadimg(data.getHeadimg());
        sellerInfoEntity.setNickname(data.getNickname());
        sellerInfoEntity.setFamous_type(data.getFamous_type());
        sellerInfoEntity.setAce_txt(data.getAce_txt());
        sellerInfoEntity.setGrade_title(data.getGrade_title());
        kolinfoView_mocmtAdp.setSellerInfo(sellerInfoEntity);
        bottomView2_mocmtAdp.setData(data);
        setReasonByEtv(data, position);
        holder.getRootView().setTag(data);
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDetail((AnswerInfoEntity) v.getTag());


            }
        });

        ll_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goCommentActivity(context, data.getAnswer_id(),data.getTask_id());
//                CommentActivity.goCommentActivity((BaseActivity) context, Integer.parseInt(data.getAnswer_id()), false);
            }
        });

        bottomView2_mocmtAdp.getCommentView().setTag(position);
        bottomView2_mocmtAdp.getCommentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(context);
                    return;
                }
                final int index = (int) v.getTag();
                InputDialog inputDialog = new InputDialog((Activity) context);
                inputDialog.setInputCallback(new InputCallback() {
                    @Override
                    public void inputString(final String text) {


                        T itemData = CommonReplyAdapter.this.getItemData(index);

                        if (itemData instanceof AnswerInfoEntity) {
                            final AnswerInfoEntity tag = (AnswerInfoEntity) itemData;
                            new SystemApiImpl().addComment(BaseApplication.UUID, text, Integer.parseInt(tag.getAnswer_id()), "task", 0, new NetReqCallback() {
                                @Override
                                public void apiRequestFail(String message, String method) {
                                    AppUtil.showToastShort(message);

                                }

                                @Override
                                public void apiRequestSuccess(Object o, String method) {
                                    tag.setComment_count(tag.getComment_count() + 1);
                                    CommentChildrenBean commentChildrenBean = new CommentChildrenBean();
                                    commentChildrenBean.setUsername(((BaseActivity) context).getAccount().getNickname());
                                    commentChildrenBean.setComment(text);
                                    tag.getComment_children().add(0, commentChildrenBean);


                                    refreshItem(index);

                                }
                            });
                        }

                    }
                });
                inputDialog.show(((BaseActivity) context).getRootView());
            }
        });
        setCommentData(ll_comment, view_line, bottomView2_mocmtAdp, data);

    }

    private RecyclerView recyclerView;
    private int headViewCount = 0;

    public void setHeadViewCount(int headViewCount) {
        this.headViewCount = headViewCount;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void refreshItem(int position) {
        if (recyclerView == null)
            return;
        int index = position + headViewCount;

        int start = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int end = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

        T itemData = CommonReplyAdapter.this.getItemData(position);
        AnswerInfoEntity data = null;
        if (itemData instanceof AnswerInfoEntity)
            data = (AnswerInfoEntity) itemData;
        if (index >= start && index <= end) {
            /**获取指定位置view对象**/
            View view = recyclerView.getChildAt(index - start);
            try {
                LinearLayout ll_comment = (LinearLayout) view.findViewById(R.id.ll_comment);
                View view_line = view.findViewById(R.id.view_line);
                BottomDoView2 bottomDoView2 = (BottomDoView2) view.findViewById(R.id.bottomView2_mocmtAdp);
                setCommentData(ll_comment, view_line, bottomDoView2, data);
            } catch (Exception ee) {

            }

        }

    }

    public void setCommentData(LinearLayout ll_comment, View view_line, BottomDoView2 bottomDoView2, AnswerInfoEntity data) {
        if (data == null)
            return;
        bottomDoView2.setCommentNum(data.getComment_count());
        if (data.getComment_count() > 0) {
            ll_comment.setVisibility(View.VISIBLE);
            view_line.setVisibility(View.GONE);
        } else {
            ll_comment.setVisibility(View.GONE);
            view_line.setVisibility(View.VISIBLE);

        }
        if (data.getComment_children() == null)
            return;
        for (int i = 1; i < 3; i++) {
            ll_comment.getChildAt(i).setVisibility(View.GONE);
        }

        for (int i = 0; i < data.getComment_children().size(); i++) {
            if (i >= 2)
                break;
            CommentChildrenBean commentChildrenBean = data.getComment_children().get(i);
            TextView childAt = (TextView) ll_comment.getChildAt(i + 1);
            childAt.setVisibility(View.VISIBLE);
            String target = commentChildrenBean.getUsername() + ":";
            String total = target + commentChildrenBean.getComment();
            ClickUtils.setText(childAt, total, target, DensityUtil.dip2px(13), ContextCompat.getColor(context, R.color.comment_user));
        }
        if (data.getComment_count() > 2) {
            ll_comment.getChildAt(3).setVisibility(View.VISIBLE);
        } else {
            ll_comment.getChildAt(3).setVisibility(View.GONE);
        }

    }


    @Override
    protected void bindView(AbsRecycleViewHolder vHolder) {
        kolinfoView_mocmtAdp = vHolder.getView(R.id.kolinfoView_mocmtAdp);
        tv_mocmtAdp_reason = vHolder.getView(R.id.tv_mocmtAdp_reason);
        mutiSpuView = vHolder.getView(R.id.mutispuview);
        bottomView2_mocmtAdp = vHolder.getView(R.id.bottomView2_mocmtAdp);
        ll_comment = vHolder.getView(R.id.ll_comment);//评论布局
        lin_mocmtAdp_layout = vHolder.getView(R.id.lin_mocmtAdp_layout);//背景
        view_line = vHolder.getView(R.id.view_line);
        questionView2 = vHolder.getView(R.id.QuestionView2);
        if (mutiSpuView != null)
            mutiSpuView.getLayoutParams().width = BaseApplication.ScreenWidth - AppUtil.dip2px(4 * 16);

    }
}
