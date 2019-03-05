package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.BrandModel;


/**
 * Created by chengang on 2017/5/23.
 */

public class BrandListAdapter extends AbsRecycleAdapter<BrandModel> {


    private RelativeLayout rel_simpleInfoView_header;
    private ImageView img_brand;
    private TextView tv_brand_name;
    private TextView tv_brand_category;
    private TextView tv_brand_desc;
    private View view_line;

    public BrandListAdapter(Context context) {
        super(context, R.layout.adapter_brandlist);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final BrandModel brandModel, int position) {

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBrandSpuListActivty(context, brandModel.getBrand_id());
            }
        });
        tv_brand_name.setText(brandModel.getBrand_name());
        tv_brand_desc.setText(brandModel.getBrand_description());
        ImageLoadUtils.loadHeadImg(context, brandModel.getBrand_image(), img_brand);
        tv_brand_category.setText(brandModel.getBrand_cate());
        if (position == 0)
            view_line.setVisibility(View.GONE);
        else
            view_line.setVisibility(View.VISIBLE);
    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        rel_simpleInfoView_header = holder.getView(R.id.rel_simpleInfoView_header);
        img_brand = holder.getView(R.id.img_brand);
        tv_brand_name = holder.getView(R.id.tv_brand_name);
        tv_brand_category = holder.getView(R.id.tv_brand_category);
        tv_brand_desc = holder.getView(R.id.tv_brand_desc);
        view_line = holder.getView(R.id.view_line);
    }


}
