package com.prettyyes.user.app.ui.common;

import android.view.View;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.containter.JumpPageManager;

import org.xutils.view.annotation.ViewInject;

public class ShareActivity extends BaseActivity {


    @ViewInject(R.id.img_friends)
    ImageView img_friend;
    @ViewInject(R.id.img_wechat)
    ImageView img_wechat;
    @ViewInject(R.id.img_weibo)
    ImageView img_weibo;
    @ViewInject(R.id.img_qq)
    ImageView img_qq;
    private ShareModel share_model;
    private ShareHandler shareHandler;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_share;
    }

    @Override
    protected void initViews() {
        setActivtytitle("邀请回复");

        setRightTvListener("邀请How好手", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (share_model != null)
                    JumpPageManager.getManager().goInvateKolActivity(getThis(), share_model.getTask_id());
            }
        });
        shareHandler = new ShareHandler(this);
        share_model = JumpPageManager.getManager().getIntentParmas(this).getShareModel();
        if (share_model == null)
            return;
        shareHandler.setRes(share_model, new ShareHandler.ShareCallback() {
            @Override
            public void share(boolean issuccess) {
                if (issuccess)
                    ShareHandler.postShare(share_model.getType(), share_model.getType_id() + "", "0");
            }
        });

    }

    @Override
    protected void setListener() {
        super.setListener();
        img_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shareHandler != null)
                    shareHandler.share_friends();
            }
        });
        img_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shareHandler != null)
                    shareHandler.share_wechat();
            }
        });
//        img_prettyyes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (share_model != null)
//                    JumpPageManager.getManager().goInvateKolActivity(getThis(), share_model.getTask_id());
//
//            }
//        });
        img_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shareHandler != null)
                    shareHandler.share_weibo();
            }
        });
        img_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shareHandler != null)
                    shareHandler.share_qq();
            }
        });

    }

    @Override
    protected void loadData() {

    }


}
