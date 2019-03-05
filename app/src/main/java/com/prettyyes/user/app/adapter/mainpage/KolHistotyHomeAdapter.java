package com.prettyyes.user.app.adapter.mainpage;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.ActInfoEntity;

/**
 * Created by chengang on 2017/5/9.
 */

public class KolHistotyHomeAdapter extends AbsRecycleAdapter<ActInfoEntity> {
    public KolHistotyHomeAdapter(Context context) {
        super(context, R.layout.item_rv_kolhistory_home);
    }


    @Override
    protected void bindData(AbsRecycleViewHolder holder, final ActInfoEntity data, int position) {

        ImageLoadUtils.loadListimg(context, data.getAct_img(), img_covery);
        tv_actdesc.setText(data.getAct_content());
//        String kols = "";
//        tv_actkols.setText(kols);
        tv_actname.setText(data.getAct_name());
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppStatistics.onEventCommon(context,"act;"+data.getAct_id());
//                KolActivityActivity.goKolActActivity(context, Integer.parseInt(data.getAct_id()));
                JumpPageManager.getManager().goKolActActivity(context,Integer.parseInt(data.getAct_id()));

            }
        });
    }

    private ImageView img_covery;
    private TextView tv_actname;
    private TextView tv_actdesc;
//    private TextView tv_actkols;

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {

        img_covery = holder.getView(R.id.img_act_covery);
        tv_actdesc = holder.getView(R.id.tv_act_desc);
        tv_actname = holder.getView(R.id.tv_actname);
//        tv_actkols = holder.getView(R.id.tv_act_kols);
    }


}
