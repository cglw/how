package com.prettyyes.user.app.adapter.mainpage;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.common.AppBanner;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/14
 * Description: Nothing
 */
public class HomeBannerVpAdapter extends AbsVpAdapter<AppBanner.ListEntity> {

    private int img_height = 0;

    public HomeBannerVpAdapter(Context context) {
        super(context, new ArrayList<AppBanner.ListEntity>(), R.layout.item_vp_home);
    }

    public void setHeight(int height) {
        this.img_height = height;
    }

    @Override
    public void showData(ViewHolder vHolder, final AppBanner.ListEntity data, int position) {

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) img_showimg.getLayoutParams();
        layoutParams.height = (int) (BaseApplication.ScreenWidth * 0.3);

        ImageLoadUtils.loadListimg(context, data.getImg_url(), img_showimg);
        img_showimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppStatistics.onEvent(context, "banner_" + data.getBanner_type() + ";" + data.getId());
                AppStatistics.onEvent(context, "banner", "banner_id", data.getId() + "");
                new PushHandler(context).handReceiveData(data.getBanner_rule());
            }
        });
    }

    private ImageView img_showimg;

    public void bindView(ViewHolder viewHolder) {
        img_showimg = (ImageView) viewHolder.getView(R.id.img_recommendVpAdp_showimg);

    }
}
