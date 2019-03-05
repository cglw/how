package com.prettyyes.user.app.adapter.moments_myList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.model.v8.MineDynamicEntity;

/**
 * Created by chengang on 2017/7/13.
 */

public class MineCommonViewHolder extends MutiTypeViewHolder<MineDynamicEntity> {

    public String uid;

    public MineCommonViewHolder(View itemView) {
        super(itemView);
        uid = SpMananger.getUid();
    }

//
//    public MineCommonViewHolder(Context context, int layout_id) {
//        super(context, R.layout.layout_mineitem_top);
//    }

    @Override
    public void bindData(MineDynamicEntity modle, int position, RecyclerView.Adapter adpter) {

        tv_date.setText(FormatUtils.getTimeBefore(modle.getCreated_at_timestamp(), TimeManager.getManager().getServer_time()));
        String moment_type = modle.getMoment_type();
        img_unread.setVisibility(View.GONE);
        if (modle.getMoment_type().equals("like_task")) {
        } else if (moment_type.equals("comment")) {
            if (modle.getComment_status().equals("0")) {
                img_unread.setVisibility(View.VISIBLE);
            }
        } else if (moment_type.equals("ask")) {

            try {
                int tip = Integer.parseInt(modle.getTask().getTip());
                if (tip > 0 && isMe(modle))
                    img_unread.setVisibility(View.VISIBLE);

            } catch (Exception ee) {

            }

        }
    }

    public boolean isMe(MineDynamicEntity modle) {
        if (uid == null)
            return false;
        return uid.equals(modle.getTask().getUid());
    }

    @Override
    public void bindView() {
        tv_title = getView(R.id.tv_title);
        tv_date = getView(R.id.tv_date);
        img_unread = getView(R.id.img_unread);
//        img_title_bg = getView(R.id.img_title_bg);


    }

    public TextView tv_title;
    //    public ImageView img_title_bg;
    public TextView tv_date;
    public ImageView img_unread;
}
