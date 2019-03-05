package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.base.BaseRongyunActivity;
import com.prettyyes.user.app.ui.comment.CommentNotifyActivity;
import com.prettyyes.user.app.ui.how.HowAdvListActivity;
import com.prettyyes.user.app.ui.how.NotifyActivity;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.TimeUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.ActivityListModel;
import com.prettyyes.user.model.HowActivityModel;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/11/4
 * Description: Nothing
 */
public class HowActivtyAdapter extends AbsRecycleAdapter<HowActivityModel> {
    public HowActivtyAdapter(Context context) {
        super(context, R.layout.item_lv_howactivity);
    }


    @Override
    protected void bindData(AbsRecycleViewHolder holder, final HowActivityModel data, int position) {
        tv_howactivityAdp_tag.setBackgroundResource(R.drawable.bg_round_red_round5);
        if (data.getType().equals("客服")) {
            ImageLoadUtils.loadHeadImg(context, "http://img.prettyyes.com/Kefu.png", img_howactivityAdp_head);
            tv_howactivityAdp_name.setText("How");
            tv_howactivityAdp_tag.setText("客服");
            tv_howactivityAdp_message.setText(data.getLastMeseage());
            tv_howactivityAdp_time.setText(TimeUtils.calRelativeTimestap(data.getReceivetime() / 1000));
        } else if (data.getType().equals("官方")) {
            ImageLoadUtils.loadHeadImg(context, data.getHeadimg(), img_howactivityAdp_head);
            tv_howactivityAdp_name.setText(data.getTitle());
            tv_howactivityAdp_tag.setText("官方");
            tv_howactivityAdp_message.setText(data.getLastMeseage());
            tv_howactivityAdp_time.setText(TimeUtils.calRelativeTimestap(data.getReceivetime()));
        } else if (data.getType().equals("通知")) {
            img_howactivityAdp_head.setImageResource(R.mipmap.myhead);
            tv_howactivityAdp_name.setText(data.getTitle());
            tv_howactivityAdp_tag.setText("通知");
            tv_howactivityAdp_time.setText(TimeUtils.calRelativeTimestap(data.getReceivetime()));
            tv_howactivityAdp_message.setText(data.getLastMeseage());


        } else if (data.getType().equals("评论")) {
            img_howactivityAdp_head.setImageResource(R.mipmap.myhead);
            tv_howactivityAdp_name.setText(data.getTitle());
            tv_howactivityAdp_tag.setText("评论");
            tv_howactivityAdp_time.setText("");
            tv_howactivityAdp_message.setText(data.getLastMeseage());

        }

        if (data.getUnread() > 0)
            view_unread.setVisibility(View.VISIBLE);
        else
            view_unread.setVisibility(View.GONE);

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = data.getType();
                if (type.equals("客服")) {
                    AccountDataRepo.getAccountManager().chatWithkf();

                } else if (type.equals("官方")) {
                    DataCenter.clearUnread(DataCenter.UnreadType.ACTIVITY);
                    Intent intent = new Intent(context, HowAdvListActivity.class);
                    if (activityLists != null) {
                        intent.putExtra("data", activityLists);
                        ((BaseRongyunActivity) context).nextActivity(intent);
                    }

                } else if (type.equals("通知")) {
                    DataCenter.clearUnread(DataCenter.UnreadType.PRETTYYES_NOTIFY);
                    Intent intent = new Intent(context, NotifyActivity.class);
                    ((BaseRongyunActivity) context).nextActivity(intent);


                } else if (type.equals("评论")) {
                    CommentNotifyActivity.goCommentNotifyActivity();
                    DataCenter.clearUnread(DataCenter.UnreadType.COMMENT);


                }
                data.setUnread(0);
                HowActivtyAdapter.this.notifyDataSetChanged();

            }
        });


    }

    private ArrayList<ActivityListModel.ListEntity> activityLists = new ArrayList<>();

    public void setActivityLists(ArrayList<ActivityListModel.ListEntity> activityLists) {
        this.activityLists = activityLists;
    }

    @Override
    protected void bindView(AbsRecycleViewHolder vHolder) {
        img_howactivityAdp_head = (ImageView) vHolder.getView(R.id.img_howactivityAdp_head);
        tv_howactivityAdp_name = (TextView) vHolder.getView(R.id.tv_howactivityAdp_name);
        tv_howactivityAdp_tag = (TextView) vHolder.getView(R.id.tv_howactivityAdp_tag);
        tv_howactivityAdp_message = (TextView) vHolder.getView(R.id.tv_howactivityAdp_message);
        tv_howactivityAdp_time = (TextView) vHolder.getView(R.id.tv_howactivityAdp_time);
        view_unread = vHolder.getView(R.id.view_unread);
    }

    private ImageView img_howactivityAdp_head;
    private TextView tv_howactivityAdp_name;
    private TextView tv_howactivityAdp_tag;
    private TextView tv_howactivityAdp_message;
    private TextView tv_howactivityAdp_time;
    private View view_unread;


}
