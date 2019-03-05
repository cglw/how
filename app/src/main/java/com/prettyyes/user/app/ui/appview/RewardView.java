package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.type.RewardListEntity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public class RewardView extends AbsLinearlayoutView<RewardListEntity.InfoEntity.RewardEntity> {
    private TextView tv_paper_title;
    private TextView tv_paper_desc;
    private ImageView img_paper_covery;

    public RewardView(Context context) {
        super(context);
    }


    @Override
    public int bindLayout() {
        return R.layout.item_mianlayout_typepaper;
    }

    @Override
    public void initViews() {
        tv_paper_title = (TextView) getView(R.id.tv_paper_title);
        tv_paper_desc = (TextView) getView(R.id.tv_paper_desc);
        img_paper_covery = (ImageView) getView(R.id.img_covery);
    }

    @Override
    public void setDataByModel(RewardListEntity.InfoEntity.RewardEntity data) {
        tv_paper_title.setText(data.getPaper_name());
        tv_paper_desc.setText(data.getSimple_desc());
        ImageLoadUtils.loadListimg(getActivity(), data.getPaper_image(), img_paper_covery);
    }
}
