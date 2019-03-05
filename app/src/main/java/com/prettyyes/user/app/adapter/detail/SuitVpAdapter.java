package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.ui.common.ViewPagerActivity;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/13
 * Description: Nothing
 */
public class SuitVpAdapter extends AbsVpAdapter<String> {
    public SuitVpAdapter(Context context) {
        super(context, new ArrayList<String>(), R.layout.item_suitvp_showsuit);
    }

    @Override
    public void showData(ViewHolder vHolder, String data, final int position) {
        ImageLoadUtils.loadListimgBySet(context, data, 2, img_suit);
        final ArrayList a = new ArrayList();
        a.clear();
        a.addAll(SuitVpAdapter.this.getData());
        img_suit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.goVpActivity(context, position, a, "");
            }
        });


    }

    public void bindView(ViewHolder viewHolder) {
        img_suit = (RoundImageView) viewHolder.getView(R.id.img_suitVp_showimgr);

    }

    private RoundImageView img_suit;

}
