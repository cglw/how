package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

/**
 * Created by chengang on 2017/7/24.
 */

public class NewPaperView extends AbsRelativelayoutView<SpuInfoEntity> {
    public NewPaperView(Context context) {
        super(context);
    }

    public NewPaperView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_newpaper;
    }

    @Override
    public void initViews() {
        bindViews();
    }

    @Override
    public void setDataByModel(SpuInfoEntity data) {

    }

    @Override
    public void setListener() {
        super.setListener();
        ((View)img_covery.getParent()).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data!=null)
                {
                    JumpPageManager.getManager().goSkuActivity(getContext(),data.getSpu_type(),data.getModule_id());
                }
            }
        });

    }

    @Override
    public void setData(SpuInfoEntity data) {
        super.setData(data);
        ImageLoadUtils.loadListimg(getContext(),data.getMain_img(),img_covery);
        mTv_name.setText(data.getSpu_name());
        mTv_desc.setText(data.getSpu_desc());
        this.data=data;
    }

    public TextView mTv_name;
    public TextView mTv_desc;
    public ImageView img_covery;


    private void bindViews() {
        mTv_name = (TextView) getView(R.id.tv_paper_name);
        mTv_desc = (TextView) getView(R.id.tv_paper_desc);
        img_covery = (ImageView) getView(R.id.img_paper_covery);
    }
}
