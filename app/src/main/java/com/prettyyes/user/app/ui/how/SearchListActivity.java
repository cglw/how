package com.prettyyes.user.app.ui.how;

import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.SearchCommonRequest;
import com.prettyyes.user.api.netXutils.response.SearchCommonRes;
import com.prettyyes.user.api.netXutils.response.SearchItemModel;
import com.prettyyes.user.app.adapter.searchholder.SearchQueModel;
import com.prettyyes.user.app.adapter.searchholder.SearchSellerModel;
import com.prettyyes.user.app.adapter.searchholder.SearchTopicModel;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;

import java.util.List;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

/**
 * Created by chengang on 2017/7/17.
 */

public class SearchListActivity extends SingleListActivity<List<SearchCommonRes>> {

    private String search_type;
    private String key_word;

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            search_type = intentParmas.getSearch_type();
            key_word = intentParmas.getKey_word();
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_search_list;
    }

    public void ask(View view) {
        AskActivity.goAskActivity(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        switch (search_type) {
            case "task":
                setActivtytitle("问题列表");
                break;
            case "seller":
                setActivtytitle("商家列表");
                break;
            case "topic":
                setActivtytitle("话题列表");
                break;
        }
        swipeRv.getRecycleView().addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST).setHaveFootView());

    }

    @Override
    protected void requestData(int page) {

        new SearchCommonRequest().setPage(page).setSearch_type(search_type).setKey_word(key_word).post(this);

    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new AbsMutiRvAdapter(this);
    }

    @Override
    public void apiRequestSuccess(List<SearchCommonRes> searchCommonResList, String method) {
        super.apiRequestSuccess(searchCommonResList, method);
        for (int i = 0; i < searchCommonResList.size(); i++) {
            SearchCommonRes searchCommonRes = searchCommonResList.get(i);
            String key_word = searchCommonRes.getKey_word();
            if (searchCommonRes.getSearch_type().equals("task")) {

                for (int j = 0; j < searchCommonRes.getList().size(); j++) {
                    SearchItemModel searchItemModel = searchCommonRes.getList().get(j);
                    SearchQueModel data = new SearchQueModel();
                    data.setAnswer_list(searchItemModel.getAnswer_list());
                    data.setTask(searchItemModel.getTask());
                    data.setKey_word(key_word);
                    adapter.add(data);
                }


            } else if (searchCommonRes.getSearch_type().equals("seller")) {

                for (int j = 0; j < searchCommonRes.getList().size(); j++) {
                    SearchItemModel searchItemModel = searchCommonRes.getList().get(j);
                    SearchSellerModel searchSellerModel = new SearchSellerModel();
                    searchSellerModel.setSeller(searchItemModel.getSeller());
                    searchSellerModel.setKey_word(key_word);
                    adapter.add(searchSellerModel);

                }


            } else if (searchCommonRes.getSearch_type().equals("topic")) {

                for (int j = 0; j < searchCommonRes.getList().size(); j++) {
                    SearchItemModel searchItemModel = searchCommonRes.getList().get(j);
                    SearchTopicModel data = new SearchTopicModel();
                    data.setId(searchItemModel.getTopic().getId());
                    data.setTopic_name(searchItemModel.getTopic().getTopic_name());
                    data.setTopic_content(searchItemModel.getTopic().getTopic_content());
                    data.setKey_word(searchCommonRes.getKey_word());
                    adapter.add(data);
                }

            }
        }


        if (searchCommonResList.get(0).getList().size() < MIN_PAGE_SIZE) {
            swipeRv.loadingEnd();
        }
    }

    @Override
    public List getListData(List<SearchCommonRes> searchCommonResList) {
        return null;
    }
}
