package com.prettyyes.user.app.ui.spu;

import android.support.v7.widget.SearchView;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.SpuListRequest;
import com.prettyyes.user.api.netXutils.response.SpuListRes;
import com.prettyyes.user.app.adapter.UnitListAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddunitSuccess;
import com.prettyyes.user.core.event.UnitSelectEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;
import static com.prettyyes.user.app.account.Constant.TYPE_UNIT;

public class UnitListActivity extends SingleListActivity<SpuListRes> {


    private int page = 1;

    @Override
    public void apiRequestSuccess(SpuListRes spuListRes, String method) {
        super.apiRequestSuccess(spuListRes, method);
        if (this.page == 1) {
            adapter.clear();
            adapter.addAll(spuInfoEntities_from);
            List<SpuInfoEntity> data = spuListRes.getData();

            for (int i = data.size() - 1; i >= 0; i--) {
                for (int j = 0; j < spuInfoEntities_from.size(); j++) {
                    if (data.get(i).getModule_id().equals(spuInfoEntities_from.get(j).getModule_id())) {

                        data.remove(i);

                        count++;
                        break;
                    }

                }

            }
        }
        adapter.addAll(spuListRes.getData());
        if (spuListRes.getData().size() < MIN_PAGE_SIZE - count)
            swipeRv.loadingEnd();

    }

    @Override
    public List getListData(SpuListRes spuListRes) {
        return null;
    }

    private int count = 0;

    private List<SpuInfoEntity> spuInfoEntities_from = new ArrayList<>();

    private String search_txt = "";

    @Override
    protected void requestData(int page) {
        this.page = page;

        new SpuListRequest().setPage(page).setSpu_type(TYPE_UNIT).setSpu_value(search_txt).post(this);

    }

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null)
            spuInfoEntities_from = intentParmas.getSpuInfoEntities();
    }

    @Override
    public void needSearch(boolean needsearch) {
        super.needSearch(true);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("选择单品");
        setRightTvListener(getString(R.string.confirm), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<SpuInfoEntity> select = ((UnitListAdapter) adapter).getSelect();
                if (select == null || select.size() <= 0) {
                    showToastShort(R.string.template_empty_unitlist);
                }
                AppRxBus.getInstance().post(new UnitSelectEvent(select));
                finish();
            }
        });
        swipeRv.getRecycleView().addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search_txt = query;
                loadData();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void setListener() {
        super.setListener();
//        mSubscription = AppRxBus.getInstance().toObservable(Object.class).subscribe(new RxAction1<
//                Object>() {
//            @Override
//            public void callback(Object o) {
//                if (o instanceof AddunitSuccess) {
//                    swipeRv.loadingFistEnter();
//                }
//            }
//        });

        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AddunitSuccess>() {
            @Override
            protected void back(AddunitSuccess obj) {
                swipeRv.loadingFistEnter();

            }
        });
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new UnitListAdapter(this);
    }

}
