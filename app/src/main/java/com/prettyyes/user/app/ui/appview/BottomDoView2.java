package com.prettyyes.user.app.ui.appview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.AnswerInfoEntity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/12/16
 * Description: Nothing
 */
public class BottomDoView2 extends BottomInfoView {
    private static final String TAG = "BottomDoView2";

    public BottomDoView2(Context context) {
        super(context);
    }

    public BottomDoView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_bottom_do_v2;
    }


    @Override
    public void initViews() {
        super.initViews();
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setClipChildren(false);
        lin_share = (LinearLayout) getView(R.id.lin_share);
        lin_comment = (LinearLayout) getView(R.id.lin_comment);
        view_share_right = getView(R.id.view_share_right);
    }

    public void hideShare() {
        lin_share.setVisibility(GONE);
        view_share_right.setVisibility(GONE);
    }



    public LinearLayout lin_share;
    private LinearLayout lin_comment;
    private View view_share_right;

    @Override
    public void setReplay(final AnswerInfoEntity data) {
        super.setReplay(data);
        lin_comment.setTag(data);
        lin_share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(getActivity());
                    return;
                }
                new ShareHandler((Activity) getContext()).setRes(data.getShare_model(), null).shareAtWindow(getRootView());

            }
        });


        if (((TextView) lin_comment.getChildAt(1)).getText().toString().contains("评论")) {
            lin_comment.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {

                    if (BaseApplication.UUID == null) {
                        RegisterActivity.getRegister(getContext());
                        return;
                    }
                    AnswerInfoEntity tag = (AnswerInfoEntity) v.getTag();
                    JumpPageManager.getManager().goCommentActivity(getContext(), tag.getAnswer_id(),tag.getTask_id());

                }
            });

            setCommentNum(data.getComment_count());

        } else {
            lin_comment.getChildAt(0).setVisibility(GONE);


        }


    }

    public LinearLayout getLin_comment() {
        return lin_comment;
    }

    @Override
    public void setListener() {
        super.setListener();


    }

    public void setCommentNum(int num) {
        TextView childAt = (TextView) lin_comment.getChildAt(1);
        String showtext = "评论";
        childAt.setText(num > 0 ? showtext + Constant.CENTER_POINT + num : showtext);

    }


    public View getCommentView() {

        return lin_comment;
    }


}
