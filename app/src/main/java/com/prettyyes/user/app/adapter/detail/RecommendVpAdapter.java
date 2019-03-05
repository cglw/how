package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/14
 * Description: Nothing
 */
public class RecommendVpAdapter extends AbsVpAdapter<SpuInfoEntity> {

    public RecommendVpAdapter(Context context) {
        super(context, new ArrayList<SpuInfoEntity>(), R.layout.item_vp_suitdel_recommend);
    }

    @Override
    public void showData(ViewHolder vHolder, final SpuInfoEntity data, int position) {
        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_showimg);
        vHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PushHandler(context).handReceiveData(data.getRule());
//                WebviewActivity.goWebActivity((Activity) context,data.getGoods_link());
            }
        });
        tv_title.setText(data.getSpu_name());
        tv_price.setText(StringUtils.getPrice(data.getSpu_price()));
    }

    private ImageView img_showimg;
    private TextView tv_title;
    private TextView tv_price;

    public void bindView(ViewHolder viewHolder) {
        img_showimg = (ImageView) viewHolder.getView(R.id.img_recommendVpAdp_showimg);
        tv_title = (TextView) viewHolder.getView(R.id.tv_title);
        tv_price = (TextView) viewHolder.getView(R.id.tv_price);

    }
}
