package com.prettyyes.user.app.ui.spu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.PhotoAdapter;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.rv.FullyGridLayoutManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

public class UnitInfoActivity extends GoosInfoActivity {

    @ViewInject(R.id.rv)
    RecyclerView rv;


    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_unit_info);

    }

    @Override
    public int setLayout() {
        return R.layout.activity_unit_info;
    }

    @Override
    public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
        super.apiRequestSuccess(spuInfoEntity, method);

        String imgs = spuInfoEntity.getSmall_img();
        if (StringUtils.strIsEmpty(imgs))
            imgs = spuInfoEntity.getMain_img();


        LogUtil.i(TAG, imgs + "--->" + spuInfoEntity.getSmall_img());
        List<String> splitArray = StringUtils.getSplitArray(imgs);
        PhotoAdapter photoAdapter = new PhotoAdapter(this, null);
        photoAdapter.setNeedBigImg(true);
        photoAdapter.setNeedDelete(false);
        int coloum = 1;
        int with = BaseApplication.ScreenWidth - AppUtil.dip2px(16) * 2;
        if (splitArray.size() > 1) {
            coloum = 2;
            with = (BaseApplication.ScreenWidth - AppUtil.dip2px(16) * 2 - (coloum - 1) * AppUtil.dip2px(8)) / coloum;
        }
        FullyGridLayoutManager fullyGridLayoutManager = new FullyGridLayoutManager(this, coloum);
        fullyGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(fullyGridLayoutManager);
        rv.setAdapter(photoAdapter);
        photoAdapter.setColoum(coloum);
        photoAdapter.setImg_width(with);
        photoAdapter.addAll(splitArray);
        //设置推荐
        setRecommend(spuInfoEntity);
        rv.setNestedScrollingEnabled(false);

    }
}
