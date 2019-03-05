package com.prettyyes.user.app.ui.comment;

import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.CommentAddReq;
import com.prettyyes.user.api.netXutils.requests.CommentChildReq;
import com.prettyyes.user.api.netXutils.requests.CommentDelReq;
import com.prettyyes.user.api.netXutils.response.CommentAddRes;
import com.prettyyes.user.api.netXutils.response.CommentChildRes;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.adapter.comment.CommentInfoListAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.appview.CommentInputView;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.model.v8.CommentChildBaseEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.core.utils.ClickUtils.isContain;

/**
 * Created by chengang on 2018/1/12.
 * 评论详情 新的 页面
 */

public class CommentNewInfoListActivity extends SingleListActivity<CommentChildRes> {
    private String comment_id;
    private String answer_id;
    @ViewInject(R.id.commentInput)
    CommentInputView commentInput;
    private CommentChildBaseEntity parent;
    private String current_comment_id;
    private boolean delete_status = false;
    private boolean clickItemComment;
    private int currenClickPotion;
    private int item_y;

    @Override
    protected int bindLayout() {
        return R.layout.layout_comment_info_list_new;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            comment_id = intentParmas.comment_id;
            answer_id = intentParmas.getAnswer_id();
        }
        current_comment_id = comment_id;
    }


    View head;

    @Override
    protected void initViews() {
        super.initViews();
        swipeRv.needWrapHeight();
        setActivtytitle("评论详情");
        setHint("回复楼主");


    }

    @Override
    protected void requestData(int page) {
        new CommentChildReq().setComment_id(comment_id).setPage(page).post(this);
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new CommentInfoListAdapter(this);
    }

    public CommentInfoListAdapter commentInfoListAdapter;

    @Override
    protected void setListener() {
        super.setListener();

        commentInfoListAdapter = (CommentInfoListAdapter) this.adapter;
        commentInfoListAdapter.setItemViewClickCallBack(new CommentInfoListAdapter.ItemViewClickCallBack() {
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


                                    LogUtil.i(TAG, "commentList.getComment_id()" + commentList.getComment_id() + "-->" + commentList.getDepth());
                                    if ("2".equals(commentList.getDepth())) {
                                        int count = 1;
                                        for (int i = position + 1; i < adapter.getItemCount(); i++) {
                                            CommentChildBaseEntity itemData = (CommentChildBaseEntity) adapter.getItemData(i);
                                            if (!commentList.getComment_id().equals(itemData.getParent_id())) {
                                                break;
                                            }
                                            count++;
                                        }
                                        adapter.remove(position, count);

                                    } else if ("3".equals(commentList.getDepth())) {
                                        adapter.remove(position);
                                    }

                                }
                            });
                        }
                    }
                }).show();

            }

            @Override
            public void comment(int position, CommentChildBaseEntity commentList) {
                setHint("回复" + commentList.getUsername());
                current_comment_id = commentList.getComment_id() + "";
                clickItemComment = true;

                commentChildBaseEntity = new CommentChildBaseEntity();
                commentChildBaseEntity.setForm_uid(commentList.getUid());
                commentChildBaseEntity.setPosition(position);
                commentChildBaseEntity.setTo_comment_id(commentList.getComment_id());
                UserInfo account = AccountDataRepo.getAccountManager().getAccount();
                if (account != null) {
                    commentChildBaseEntity.setUid(account.getUid());
                    commentChildBaseEntity.setUsername(account.getNickname());
                }
                commentChildBaseEntity.setForm_nickname(commentList.getUsername());
                commentChildBaseEntity.setDepth("3");


                if (commentList.isDepth2())
                    commentChildBaseEntity.setParent_id(commentList.getComment_id());
                else
                    commentChildBaseEntity.setParent_id(commentList.getParent_id());

                commentChildBaseEntity.setCreated_at(FormatUtils.getNowDate());

                currenClickPotion = position;
                item_y = SoftKeyboardUtil.getRvItemY(position, swipeRv.getRecycleView());
                SoftKeyboardUtil.openInputMethod(commentInput.getEditText());
            }

            @Override
            public void commentParent(int position, CommentChildBaseEntity commentList) {
                SoftKeyboardUtil.openInputMethod(commentInput.getEditText());
            }
        });

        SoftKeyboardUtil.observeSoftKeyboard(this, new SoftKeyboardUtil.OnSoftKeyboardChangeListener() {
            @Override
            public void onSoftKeyBoardChange(int softKeybardHeight, int top, boolean visible) {
                scorllRvToLocation();
                if (visible)
                    clickItemComment = false;
            }
        });
        commentInput.setInputCallback(new CommentInputView.InputCallback() {
            @Override
            public void send(final String text) {

                if (comment_id.equals(current_comment_id)) {

                    commentChildBaseEntity = new CommentChildBaseEntity();
                    commentChildBaseEntity.setForm_uid("");
                    commentChildBaseEntity.setTo_comment_id(parent.getComment_id());
                    UserInfo account = AccountDataRepo.getAccountManager().getAccount();
                    if (account != null) {
                        commentChildBaseEntity.setUid(account.getUid());
                        commentChildBaseEntity.setUsername(account.getNickname());
                    }
                    commentChildBaseEntity.setForm_nickname(parent.getUsername());
                    commentChildBaseEntity.setDepth("2");
                    commentChildBaseEntity.setParent_id(parent.getComment_id());
                    commentChildBaseEntity.setCreated_at(FormatUtils.getNowDate());
                }

                new CommentAddReq()
                        .setAnswer_id(answer_id)
                        .setComment_type("task")
                        .setComment(text)
                        .setComment_id(current_comment_id)
                        .post(new NetReqCallback<CommentAddRes>() {
                            @Override
                            public void apiRequestFail(String message, String method) {

                            }

                            @Override
                            public void apiRequestSuccess(CommentAddRes commentAddRes, String method) {
                                if (current_comment_id != null && commentChildBaseEntity != null &&
                                        current_comment_id.equals(commentChildBaseEntity.getTo_comment_id())) {
                                    commentChildBaseEntity.setComment_id(commentAddRes.getComment_id());
                                    commentChildBaseEntity.setComment(text);
                                    if (commentChildBaseEntity.isDepth2()) {
                                        adapter.add(commentChildBaseEntity);

                                        swipeRv.getRecycleView().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //因为有headview

                                                swipeRv.getRecycleView().scrollToPosition(adapter.getDataCount() - 1);
                                            }
                                        }, 500);

                                    } else
                                        adapter.add(commentChildBaseEntity.getPosition() + 1, commentChildBaseEntity);

                                }

                            }
                        });
            }
        });

    }

    private CommentChildBaseEntity commentChildBaseEntity;


    @Override
    public void setSoftModel() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoftKeyboardUtil.observeSoftKeyboardDestopry(this);
    }

    @Override
    public void apiRequestSuccess(CommentChildRes commentChildRes, String method) {
        super.apiRequestSuccess(commentChildRes, method);

    }


    @Override
    public void handSoft(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            if (isContain(commentInput, ev.getRawX(), ev.getRawY()))
                return;
            View currentFocus = getCurrentFocus();
            if (!clickItemComment)
                setHint("回复楼主");
            clickItemComment = false;

            if (isHideInput(currentFocus, ev)) {
                HideSoftInput(currentFocus.getWindowToken());
            }


        }
    }


    public void setHint(String hint) {
        currenClickPotion = -1;
        commentChildBaseEntity = null;
        current_comment_id = comment_id;
        commentInput.setHint(hint);


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
    public List getListData(CommentChildRes o) {


        ArrayList<CommentChildBaseEntity> arrayList = new ArrayList();

        if (page == 1) {
            o.setDepth("1");
            arrayList.add(o);
            parent = o;
            LogUtil.i(TAG, "page" + page);
            setHint("回复楼主");


        }
        if (o.getChildren() != null) {
            for (int i = 0; i < o.getChildren().size(); i++) {

                CommentChildBaseEntity commentChildTwoRes = o.getChildren().get(i);
                List<CommentChildBaseEntity> children = new ArrayList<>();
                children.addAll(commentChildTwoRes.getChildren());
                commentChildTwoRes.setDepth("2");

                arrayList.add(commentChildTwoRes);
                //清空数据
                commentChildTwoRes.setChildren(null);

                for (int j = 0; j < children.size(); j++) {
                    CommentChildBaseEntity e = children.get(j);
                    e.setDepth("3");
                    arrayList.add(e);
                }

            }
        }
        o.setChildren(null);

        return arrayList;
    }
}
