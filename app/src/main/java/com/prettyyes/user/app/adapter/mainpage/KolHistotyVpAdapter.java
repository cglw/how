package com.prettyyes.user.app.adapter.mainpage;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.model.v8.ActInfoEntity;

/**
 * Created by chengang on 2017/5/9.
 */

public class KolHistotyVpAdapter extends AbsVpAdapter<ActInfoEntity> {
    public KolHistotyVpAdapter(Context context) {
        super(context, R.layout.item_vp_history_act);
    }

    private TextView tv_act_name;
    private TextView tv_act_time;
    private TextView tv_act_content;


    @Override
    public void bindView(ViewHolder holder) {
        tv_act_name = holder.getView(R.id.tv_act_name);
        tv_act_time = holder.getView(R.id.tv_act_time);
        tv_act_content = holder.getView(R.id.tv_act_content);
    }

    @Override
    public void showData(ViewHolder holder, final ActInfoEntity data, int position) {
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppStatistics.onEvent(context, "topic", "topic_id", data.getAct_id() + "");

                JumpPageManager.getManager().goKolActActivity(context, Integer.parseInt(data.getAct_id()));
            }
        });
        tv_act_name.setText(data.getAct_name());
        tv_act_content.setText(data.getAct_content());
        tv_act_time.setText(FormatUtils.getDateMMddyy(FormatUtils.StringToDateMs(data.getStart_time())));

    }
}
