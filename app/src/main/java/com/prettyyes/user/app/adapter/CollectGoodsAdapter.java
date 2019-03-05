package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.Suit.SkuCollectEntity;

/**
 * Created by chengang on 2017/5/4.
 */

public class CollectGoodsAdapter extends AbsRecycleAdapter<SkuCollectEntity> {


    private TextView tv_goodsname;
    private TextView tv_goodsdesc;
    private TextView tv_goodsprice;
    private ImageView img_goodsimg;


    public CollectGoodsAdapter(Context context) {
        super(context, R.layout.item_rv_simple_list);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final SkuCollectEntity data, int position) {

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSkuActivity(context, data.getSpu_type(), data.getModule_id());
            }
        });

        img_goodsimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBigImgActivity(context, data.getMain_img());
            }
        });
        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_goodsimg);

        tv_goodsname.setText(data.getSpu_name());
        tv_goodsdesc.setText("");
        tv_goodsprice.setText(StringUtils.getPrice(data.getSpu_price()));


    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {

        tv_goodsname = holder.getView(R.id.tv_suitlistAdp_goodsname);
        tv_goodsdesc = holder.getView(R.id.tv_suitlistAdp_goodsdesc);
        tv_goodsprice = holder.getView(R.id.tv_suitlistAdp_goodsprice);
        img_goodsimg = holder.getView(R.id.img_suitlistAdp_goodsimg);

    }
}
