package com.prettyyes.user.app.ui.spu;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.BrandSpuListRequest;
import com.prettyyes.user.app.adapter.BrandGoosInfoListAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/10.
 */

public class BrandSpuListActivity extends SingleListActivity<List<SpuInfoEntity>> {

    private String brand_id;

    @Override
    protected void requestData(int page) {
        new BrandSpuListRequest(brand_id).setPage(page).post(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_brand_list_spuinfo);
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            brand_id = intentParmas.getBrand_id();
        }
    }

//    @Override
//    public void setAdapter() {
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//
//        swipeRv.setAdapter(adapter, gridLayoutManager);
//        swipeRv.getRecycleView().addItemDecoration(new DividerItemDecoration(this, BOTH_SET, AppUtil.dip2px(8), R.color.backgroundWhit));
//        gridLayoutManager.setSpanSizeLookup(new HeaderSpanSizeLookup(swipeRv.getHeaderAndFooterRecyclerViewAdapter(), gridLayoutManager.getSpanCount()));
//
//    }



    @Override
    public DividerItemDecoration setDrividHeightPx(int height, int color) {
        return super.setDrividHeightPx(AppUtil.dip2px(0.3), R.color.backgroundWhit);
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new BrandGoosInfoListAdapter(this);
    }


    @Override
    public List getListData(List<SpuInfoEntity> spuInfoEntities) {
        return spuInfoEntities;
    }
}
