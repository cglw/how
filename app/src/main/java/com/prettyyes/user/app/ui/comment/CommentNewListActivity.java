package com.prettyyes.user.app.ui.comment;

import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.CommentAddReq;
import com.prettyyes.user.api.netXutils.requests.CommentDelReq;
import com.prettyyes.user.api.netXutils.requests.CommentListReq;
import com.prettyyes.user.api.netXutils.response.CommentAddRes;
import com.prettyyes.user.api.netXutils.response.CommentNewListRes;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.adapter.comment.CommentListAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.appview.CommentInputView;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.GestureLayout;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.model.v8.CommentChildBaseEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static com.prettyyes.user.core.utils.ClickUtils.isContain;

/**
 * Created by chengang on 2018/1/12.
 * 评论列表
 */

public class CommentNewListActivity extends SingleListActivity<CommentNewListRes> {
    private String answer_id;
    private String task_id;
    private String comment_id = "";
    private String commentType = "task";
    private int currenClickPotion = -1;
    private int softKeyboarUnShowRvChildCount = 0;


    @ViewInject(R.id.commentInput)
    CommentInputView commentInput;
    @ViewInject(R.id.view_root)
    GestureLayout view_root;


    private CommentChildBaseEntity commentChildBaseEntity;
    private float x;


    @Override
    protected int bindLayout() {
        return R.layout.layout_comment_list_new;
    }

    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
        return super.setDrividHeightPx(1);
    }

    private boolean delete_status = false;
    CommentListAdapter commentListAdapter;

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("评论列表");
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            if (intentParmas.getAnswer_id() != null) {
                answer_id = intentParmas.getAnswer_id();
                task_id = intentParmas.getTask_id();
                LogUtil.i(TAG, "task_id" + task_id);
            }
        }

        setHint("评论套系回复");

        commentListAdapter = (CommentListAdapter) adapter;
        commentListAdapter.setItemViewClickCallBack(new CommentListAdapter.ItemViewClickCallBack() {
            @Override
            public void del(final int position, final CommentChildBaseEntity commentList) {


                String comment = commentList.getComment();
                if (comment == null)
                    comment = "";
                DialogHelper.getInstance().getDialogNoCancel(String.format("确定删除评论\"%s\"", comment.length() > 20 ? comment.substring(0, 20) : comment), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!delete_status) {
                            delete_status = true;
                            new CommentDelReq().setComment_id(commentList.getComment_id() + "").post(new NetReqCallback<Object>() {
                                @Override
                                public void apiRequestFail(String message, String method) {
                                    delete_status = false;
                                }

                                @Override
                                public void apiRequestSuccess(Object o, String method) {
                                    delete_status = false;
                                    adapter.remove(position);

                                }
                            });
                        }
                    }
                }).show();


            }

            @Override
            public void comment(int position, CommentChildBaseEntity commentList) {
                setHint("回复" + commentList.getUsername());
                currenClickPotion = position;
                comment_id = commentList.getComment_id() + "";
                clickItemComment = true;

                commentChildBaseEntity = new CommentChildBaseEntity();
                commentChildBaseEntity.setPosition(position);
                UserInfo account = AccountDataRepo.getAccountManager().getAccount();
                if (account != null) {
                    commentChildBaseEntity.setUid(account.getUid());
                    commentChildBaseEntity.setUsername(account.getNickname());
                    commentChildBaseEntity.setHeadimg(account.getHeadimg());
                }
                commentChildBaseEntity.setCreated_at(FormatUtils.getNowDate());

                commentChildBaseEntity.setTo_comment_id(commentList.getComment_id());


                currenClickPotion = position;
                item_y = SoftKeyboardUtil.getRvItemY(position, swipeRv.getRecycleView());
                SoftKeyboardUtil.openInputMethod(commentInput.getEditText());


            }

            @Override
            public void goCommentInfoList(int position, CommentChildBaseEntity commentList) {
                JumpPageManager.getManager().goCommentInfoActivity(getThis(), commentList.getComment_id() + "", answer_id);
            }
        });

        setRightTvListener("查看问答", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ZBundleCore.getInstance().isLastSecond(ReplyDetailActivity.class))
                    finish();
                else
                    JumpPageManager.getManager().goReplyDetailActivity(getThis(), task_id, answer_id);

            }
        });

    }

    private int item_y = 0;

    private boolean clickItemComment = false;

    @Override
    public void setSoftModel() {
//        /**/super.setSoftModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoftKeyboardUtil.observeSoftKeyboardDestopry(this);
    }

    @Override
    public void handSoft(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {


            if (isContain(commentInput, ev.getRawX(), ev.getRawY())) {

                return;
            }
            View currentFocus = getCurrentFocus();


            if (!clickItemComment)
                setHint("评论套系回复");
            clickItemComment = false;

            if (isHideInput(currentFocus, ev)) {
                HideSoftInput(currentFocus.getWindowToken());
            }


        }
    }


    public void setHint(String hint) {
        comment_id = "";
        currenClickPotion = -1;
        commentChildBaseEntity = null;
        commentInput.setHint(hint);
    }

    float x1 = 0;
    float x2 = 0;

    @Override
    protected void setListener() {
        super.setListener();

        SoftKeyboardUtil.observeSoftKeyboard(this, new SoftKeyboardUtil.OnSoftKeyboardChangeListener() {
            @Override
            public void onSoftKeyBoardChange(int softKeybardHeight, int top, boolean visible) {
                scorllRvToLocation();
                if (visible) {
                    clickItemComment = false;

                }
            }
        });
        view_root.setSwipeListener(new GestureLayout.SwipListener() {
            @Override
            public void onLeftSwipe() {
                JumpPageManager.getManager().goReplyDetailActivity(getThis(), task_id, answer_id);
            }

            @Override
            public void onRightSwipe() {

            }
        });

        commentInput.setInputCallback(new CommentInputView.InputCallback() {
            @Override
            public void send(final String text) {

                LogUtil.i(TAG, "send");
                new CommentAddReq()
                        .setAnswer_id(answer_id)
                        .setComment_type("task")
                        .setComment(text)
                        .setComment_id(comment_id)
                        .post(new NetReqCallback<CommentAddRes>() {
                            @Override
                            public void apiRequestFail(String message, String method) {

                            }

                            @Override
                            public void apiRequestSuccess(CommentAddRes commentAddRes, String method) {
//                                if (swipeRv != null)
//                                    swipeRv.loadingFistEnter();

                                if (commentChildBaseEntity == null) {
                                    if (swipeRv != null)
                                        swipeRv.loadingFistEnter();
                                } else if (comment_id != null &&
                                        comment_id.equals(commentChildBaseEntity.getTo_comment_id())) {

                                    commentChildBaseEntity.setComment_id(commentAddRes.getComment_id());
                                    commentChildBaseEntity.setComment(text);
                                    CommentChildBaseEntity itemData = (CommentChildBaseEntity) adapter.getItemData(commentChildBaseEntity.getPosition());
                                    itemData.getChildren().add(0, commentChildBaseEntity);
                                    commentListAdapter.notifyItemChanged(commentChildBaseEntity.getPosition());
                                }


                            }
                        });
            }
        });


    }

    private void scorllRvToLocation() {
        if (currenClickPotion == -1)
            return;
        //判断软键盘弹出还是可见的item 被遮挡
        int[] position2 = new int[2];
        commentInput.getLocationOnScreen(position2);
        if (item_y <= position2[1]) {
        } else {
            swipeRv.getRecycleView().scrollBy(0, item_y - position2[1]);

        }
    }


    @Override
    public AbsRecycleAdapter createAdapter() {
        return new CommentListAdapter(this);
    }

    @Override
    protected void requestData(int page) {
        new CommentListReq().setAnswer_id(answer_id).setPage(page).setComment_type(commentType).post(this);
    }

    private boolean isFirst = true;

    @Override
    public List getListData(CommentNewListRes o) {
        //判断是为空
        if (isFirst && adapter.getItemCount() == 0 && o.getList().size() == 0) {
            SoftKeyboardUtil.openInputMethod(commentInput.getEditText());
            commentInput.fouse();
        }
        return o.getList();
    }
}