package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter
 * Author: SmileChen
 * Created on: 2016/8/9
 * Description: Nothing
 */
public class ViewPagerLocalAdapter extends AbsVpAdapter<Integer> {


    private ViewPager viewPager;

    public ViewPagerLocalAdapter(Context context, ViewPager viewPager) {
        super(context, R.layout.item_guide);
        this.viewPager = viewPager;
    }

    @Override
    public void bindView(ViewHolder vHolder) {
        img = vHolder.getView(R.id.img);
        view_go_main = vHolder.getView(R.id.view_go_main);
    }

    private ImageView img;
    private View view_go_main;

    @Override
    public void showData(ViewHolder vHolder, Integer data, final int position) {
        ImageLoadUtils.loadGuide(context, data, img);
        vHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < getCount() - 1) {
                    viewPager.setCurrentItem(position + 1, false);
                } else {
//                    AppRxBus.getInstance().post(new GuideCompleteEvent());
                    JumpPageManager.getManager().goMainActivity(context);
                }
            }
        });
        view_go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppRxBus.getInstance().post(new GuideCompleteEvent());

                JumpPageManager.getManager().goMainActivity(context);

            }
        });
    }


}