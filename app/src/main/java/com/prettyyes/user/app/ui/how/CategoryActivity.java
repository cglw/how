package com.prettyyes.user.app.ui.how;

import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.CategoryRequest;
import com.prettyyes.user.app.adapter.CategotyAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.CategorySelectEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends SingleListActivity<List<CategoryModel>> {

    @Override
    public void setActivtytitle(String title) {
        super.setActivtytitle(getString(R.string.title_activity_category));
        showHeader();
        setRightTvListener(getString(R.string.confirm), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppRxBus.getInstance().post(new CategorySelectEvent(((CategotyAdapter) adapter).getSelect()));
                finish();
            }
        });
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new CategotyAdapter(this);
    }

    @Override
    protected void requestData(int page) {
        new CategoryRequest().post(this);
    }

    @Override
    public void apiRequestSuccess(List<CategoryModel> categoryModels,String method) {

        super.apiRequestSuccess(categoryModels,method);
        ArrayList<CategoryModel> categoryModels_recrive = JumpPageManager.getManager().getIntentParmas(getThis()).getCategoryModels();

        if (categoryModels_recrive != null && categoryModels_recrive.size() > 0) {
            for (int i = 0; i < categoryModels_recrive.size(); i++) {
                for (int j = 0; j < categoryModels.size(); j++) {
                    if (categoryModels_recrive.get(i).getCat_id().equals(categoryModels.get(j).getCat_id()))
                        categoryModels.get(j).setSelect(true);
                }
            }
        }
        adapter.addAll(categoryModels);
        swipeRv.loadingEnd();
    }

    @Override
    public List getListData(List<CategoryModel> categoryModels) {
        return null;
    }

}
