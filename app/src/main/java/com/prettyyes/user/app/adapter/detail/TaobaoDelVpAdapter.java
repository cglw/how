package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/14
 * Description: Nothing
 */
public class TaobaoDelVpAdapter extends AbsVpAdapter<String> {
    public TaobaoDelVpAdapter(Context context) {
        super(context, new ArrayList<String>(), R.layout.item_oneimg);
    }

    @Override
    public void showData(ViewHolder vHolder, final String data, int position) {
        if (data.startsWith("http"))
            ImageLoadUtils.loadListimg(context, data, img_pageadapter);
        else
            ImageLoadUtils.loadListimg(context, "file://" + data, img_pageadapter);
        vHolder.getRootView().setTag(position);
        vHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                JumpPageManager.getManager().goBigImgActivity(context, TaobaoDelVpAdapter.this.getData(), tag);
            }
        });

//        ImageLoadUtils.loadListimgDef(context, data, img_pageadapter);
    }

    public void bindView(ViewHolder vHolder) {
        img_pageadapter = (RoundImageView) vHolder.getView(R.id.img_pageadapter);
    }

    RoundImageView img_pageadapter;

}
