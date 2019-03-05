package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.ActivityListModel;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/11/5
 * Description: Nothing
 */
public class ActivityListAdapter extends SpecilAbsAdapter<ActivityListModel.ListEntity> {
    private Callback callback;

    public ActivityListAdapter(Context context, Callback callback) {
        super(R.layout.item_lv_activitylistadp, new ArrayList<ActivityListModel.ListEntity>(), context);
        this.callback = callback;
    }
    // Content View Elements

    private TextView tv_activitylistAdp_time;
    private TextView tv_activitylistAdp_title;
    private ImageView img_activitylistAdp_mainimg;
    private TextView tv_activitylistAdp_desc;
    private LinearLayout lin_activitylistAdp_item;

    @Override
    public void bindView(ViewHolder vHolder) {

        tv_activitylistAdp_time = (TextView) vHolder.getView(R.id.tv_activitylistAdp_time);
        tv_activitylistAdp_title = (TextView) vHolder.getView(R.id.tv_activitylistAdp_title);
        img_activitylistAdp_mainimg = (ImageView) vHolder.getView(R.id.img_activitylistAdp_mainimg);
        tv_activitylistAdp_desc = (TextView) vHolder.getView(R.id.tv_activitylistAdp_desc);
        lin_activitylistAdp_item = (LinearLayout) vHolder.getView(R.id.lin_activitylistAdp_item);
    }

    @Override
    public void showData(ViewHolder vHolder, final ActivityListModel.ListEntity data, int position) {

        tv_activitylistAdp_desc.setText(data.getActivity_describe());
        tv_activitylistAdp_time.setText(data.getCreate_time());
        tv_activitylistAdp_title.setText(data.getActivity_title());
        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_activitylistAdp_mainimg);
        lin_activitylistAdp_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    if (data.getActivity_rule() != null && data.getActivity_rule().length() > 0) {
                        callback.click(data.getActivity_rule());
                    } else {
                        JumpPageManager.getManager().goWebActivity(context, data.getActivity_link());
                    }
                }
            }
        });
    }

    public interface Callback {
        public void click(String url);
    }

}
