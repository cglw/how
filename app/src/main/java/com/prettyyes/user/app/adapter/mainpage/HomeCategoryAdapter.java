package com.prettyyes.user.app.adapter.mainpage;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.model.QueCategoryEntity;

/**
 * Created by chengang on 2017/7/10.
 */

public class HomeCategoryAdapter extends AbsRecycleAdapter<QueCategoryEntity> {


    public HomeCategoryAdapter(Context context) {
        super(context, R.layout.item_rv_home_category);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final QueCategoryEntity data, int position) {
        tv_name.setText(data.getCategory());
        holder.getRootView().getLayoutParams().width = BaseApplication.ScreenWidth / 4;
        img_covery.setImageResource(data.getIc());
    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        tv_name = holder.getView(R.id.tv_name);
        img_covery = holder.getView(R.id.img_covery);

    }

    private TextView tv_name;
    private ImageView img_covery;
}
