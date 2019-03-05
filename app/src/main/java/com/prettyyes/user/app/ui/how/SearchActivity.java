package com.prettyyes.user.app.ui.how;

import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.SearchCommonRequest;
import com.prettyyes.user.api.netXutils.response.SearchCommonRes;
import com.prettyyes.user.api.netXutils.response.SearchItemModel;
import com.prettyyes.user.app.adapter.searchholder.SearchLookMoreModel;
import com.prettyyes.user.app.adapter.searchholder.SearchQueModel;
import com.prettyyes.user.app.adapter.searchholder.SearchSellerModel;
import com.prettyyes.user.app.adapter.searchholder.SearchTitltModel;
import com.prettyyes.user.app.adapter.searchholder.SearchTopicModel;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.app.view.lvandgrid.EmptyListener;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.PostAskSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;

import java.util.List;

public class SearchActivity extends SingleListActivity<List<SearchCommonRes>> implements EmptyListener {


    private String search_type = "hot";

    @Override
    protected void initViews() {
        super.initViews();
        hideHeader();
        searchView.setVisibility(View.VISIBLE);
        swipeRv.setEmpty_listener(this);
        swipeRv.getRecycleView().addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        searchView.setFocusable(true);
        searchView.setFocusableInTouchMode(true);
        searchView.requestFocus();


    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppStatistics.onEvent(this, "search_page");

    }

    public void ask(View view) {
        JumpPageManager.getManager().goAskActivity(this);
    }

    @Override
    public void needSearch(boolean needsearch) {
        super.needSearch(true);
    }

    @Override
    public void setSoftModel() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }

    @Override
    protected void setListener() {
        super.setListener();
//        mSubscription = AppRxBus.getInstance().toObservable(Object.class).subscribe(new RxAction1<Object>() {
//            @Override
//            public void callback(Object o) {
//                if (o instanceof PostAskSuccessEvent) {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            swipeRv.loadingFistEnter();
//                        }
//                    }, 2000);
//                }
//            }
//        });

        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<PostAskSuccessEvent>() {
            @Override
            protected void back(PostAskSuccessEvent obj) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRv.loadingFistEnter();
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void requestData(int page) {
        if (inputText.length() <= 0)
            search_type = "hot";
        AppStatistics.onEvent(this, "search");

        new SearchCommonRequest().setPage(page).setSearch_type(search_type).setKey_word(inputText).post(this);
        search_type = "all";
    }


    @Override
    public void setAdapter() {
        super.setAdapter();
    }


    @Override
    public void apiRequestSuccess(List<SearchCommonRes> searchCommonResList, String method) {
        super.apiRequestSuccess(searchCommonResList, method);
        AppStatistics.onEvent(this, "search_result_page");

        adapter.clear();
        for (int i = 0; i < searchCommonResList.size(); i++) {
            SearchCommonRes searchCommonRes = searchCommonResList.get(i);
            String key_word = searchCommonRes.getKey_word();
            if (searchCommonRes.getSearch_type().equals("task")) {
                if (searchCommonRes.getList().size() > 0) {
                    adapter.add(new SearchTitltModel().setTitle("问答").setKey_word(key_word));
                }
                for (int j = 0; j < searchCommonRes.getList().size(); j++) {

                    SearchItemModel searchItemModel = searchCommonRes.getList().get(j);
                    if (j >= 2) {
                        adapter.add(new SearchLookMoreModel().setSearch_type("task").setKey_word(key_word));
                        break;
                    }
                    SearchQueModel data = new SearchQueModel();
                    data.setAnswer_list(searchItemModel.getAnswer_list());
                    data.setTask(searchItemModel.getTask());
                    data.setKey_word(key_word);
                    adapter.add(data);
                }


            } else if (searchCommonRes.getSearch_type().equals("seller")) {
                if (searchCommonRes.getList().size() > 0)
                    adapter.add(new SearchTitltModel().setTitle("用户").setKey_word(key_word));
                for (int j = 0; j < searchCommonRes.getList().size(); j++) {
                    SearchItemModel searchItemModel = searchCommonRes.getList().get(j);

                    if (j >= 2) {
                        adapter.add(new SearchLookMoreModel().setSearch_type("seller").setKey_word(key_word));
                        break;
                    }
                    SearchSellerModel searchSellerModel = new SearchSellerModel();
                    searchSellerModel.setSeller(searchItemModel.getSeller());
                    searchSellerModel.setKey_word(key_word);
                    adapter.add(searchSellerModel);

                }


            } else if (searchCommonRes.getSearch_type().equals("topic")) {
                if (searchCommonRes.getList().size() > 0)
                    adapter.add(new SearchTitltModel().setTitle("话题").setKey_word(key_word));
                for (int j = 0; j < searchCommonRes.getList().size(); j++) {
                    SearchItemModel searchItemModel = searchCommonRes.getList().get(j);
                    if (j >= 2) {
                        adapter.add(new SearchLookMoreModel().setSearch_type("topic").setKey_word(key_word));
                        break;
                    }
                    SearchTopicModel data = new SearchTopicModel();
                    data.setId(searchItemModel.getTopic().getId());
                    data.setTopic_name(searchItemModel.getTopic().getTopic_name());
                    data.setTopic_content(searchItemModel.getTopic().getTopic_content());
                    data.setKey_word(searchCommonRes.getKey_word());
                    adapter.add(data);
                }

            }
        }

        swipeRv.loadingEnd();


    }

    @Override
    public List getListData(List<SearchCommonRes> searchCommonResList) {
        return null;
    }

    @Override
    public void apiRequestFail(String message, String method) {
        super.apiRequestFail(message, method);
        LogUtil.i(TAG, message + "");
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new AbsMutiRvAdapter(this);
    }

    @Override
    public void setEmpty(LinearLayout ll, Button button) {
        ll.setGravity(Gravity.CENTER_HORIZONTAL);
        ll.setPadding(AppUtil.dip2px(60), AppUtil.dip2px(32), AppUtil.dip2px(60), 0);

        for (int i = 0; i < ll.getChildCount(); i++) {
            ll.getChildAt(i).setVisibility(View.GONE);
        }
        button.setVisibility(View.VISIBLE);
        button.setText("去发问");
        ((TextView) ll.getChildAt(0)).setText("我是一个过于理智的傻瓜。");
        ((TextView) ll.getChildAt(1)).setText("——《诺丁山》");
        ((TextView) ll.getChildAt(2)).setText("未搜到相关内容");

        button.setVisibility(View.GONE);
//        ImageView img = new ImageView(this);
//        img.setImageResource(R.mipmap.ask_button);
//        ll.addView(img);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AskActivity.goAskActivity(getThis());
//            }
//        });

    }
}
