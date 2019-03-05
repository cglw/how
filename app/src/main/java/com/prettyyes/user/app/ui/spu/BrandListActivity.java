package com.prettyyes.user.app.ui.spu;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.BrandListRequest;
import com.prettyyes.user.api.netXutils.response.BrandListRes;
import com.prettyyes.user.app.adapter.BrandListAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;

import java.util.List;

public class BrandListActivity extends SingleListActivity<BrandListRes> {



    @Override
    protected void requestData(int page) {
        new BrandListRequest(inputText).post(this);
    }

    @Override
    public void needSearch(boolean needsearch) {
        super.needSearch(true);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_brand_list);
        searchView.setQueryHint("搜索你想要的品牌");
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new BrandListAdapter(this);
    }

    @Override
    public List getListData(BrandListRes brandListRes) {
        return brandListRes.getList();
    }
}
