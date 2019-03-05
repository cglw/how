package com.prettyyes.user.app.fragments.collect;

import com.prettyyes.user.api.netXutils.requests.SuitFavouriteSuitListRequest;
import com.prettyyes.user.api.netXutils.response.SkuCollectRes;
import com.prettyyes.user.app.adapter.CollectGoodsAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.fragments.SingleListFragment;

import java.util.List;

/**
 * Created by chengang on 2017/7/25.
 */

public class CollectGoosFragment extends SingleListFragment<SkuCollectRes> {
    @Override
    public void requestPageData(int page) {
        new SuitFavouriteSuitListRequest().setPage(page).post(this);
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new CollectGoodsAdapter(getContext());
    }

    @Override
    public List getListData(SkuCollectRes skuCollectEntity) {
        return skuCollectEntity.getData();
    }
}
