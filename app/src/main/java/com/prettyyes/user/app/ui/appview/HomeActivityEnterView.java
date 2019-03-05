package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.common.ActInfo;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/12/21
 * Description: Nothing
 */
public class HomeActivityEnterView extends AbsRelativelayoutView {

    public HomeActivityEnterView(Context context) {
        super(context);
    }

    public HomeActivityEnterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_homeactivtyenter;
    }

    @Override
    public void initViews() {
        setBackgroundResource(R.mipmap.kol_entry_unstarted_bk);
        tv_title = (TextView) getView(R.id.tv_actView_title);
        tv_time = (TextView) getView(R.id.tv_actView_time);
        tv_click = (TextView) getView(R.id.tv_actView_click);

    }

    private TextView tv_title;
    private TextView tv_time;
    private TextView tv_click;

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setTime(String time) {
        tv_time.setText(time);
    }

    ActInfo actInfo;


    public void setActInfo(ActInfo actInfo) {
        this.actInfo = actInfo;
    }


    public void setTv_click(final String tv) {
        tv_click.setText(tv);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AppStatistics.onEvent(getContext(),"topic");
                if (tv.equals("立即参与")) {
                    JumpPageManager.getManager().goLiveActivity(getContext(), actInfo);
                } else
                    JumpPageManager.getManager().goKolActActivity(getContext(), actInfo.getAct_id());


            }
        });

    }

    public void setImg_bg(boolean isstart) {
        if (isstart) {
            setBackgroundResource(R.mipmap.kol_entry_started_bk);
        } else {
            setBackgroundResource(R.mipmap.kol_entry_unstarted_bk);

        }
    }


    @Override
    public void setDataByModel(Object data) {

    }
}
