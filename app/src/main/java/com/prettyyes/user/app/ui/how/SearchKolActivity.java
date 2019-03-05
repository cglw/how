package com.prettyyes.user.app.ui.how;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.detail.SearchSellerListAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.mvpPresenter.SearchKolPresenter;
import com.prettyyes.user.app.mvpView.SearchKolView;
import com.prettyyes.user.app.view.lvandgrid.SwipeListView;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class SearchKolActivity extends BaseActivity implements SearchKolView {

    @ViewInject(R.id.tv_searchkolAct_search)
    private TextView tv_search;
    @ViewInject(R.id.edit_searchkolAct_input)
    private EditTextDel edit_input;
    @ViewInject(R.id.lv_searchkolAct_list)
    private SwipeListView lv_list;
    private SearchSellerListAdapter searchSellerListAdapter;
    public SearchKolPresenter searchKolPresenter = new SearchKolPresenter(this);
    private ArrayList<SellerInfoEntity> seller_array = new ArrayList<>();


    @Override
    protected void initVariables() {
        super.initVariables();
        seller_array = (ArrayList<SellerInfoEntity>) getIntent().getSerializableExtra("seller_info");
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_search_kol;
    }

    @Override
    protected void initViews() {
        setActivtytitle("提醒谁回答");
        setStateBarColor(ContextCompat.getColor(this, R.color.theme_coffee));
        setApptitleColor(ContextCompat.getColor(this, R.color.theme_coffee));
        setApptitleTvColor(ContextCompat.getColor(this, R.color.white));
        searchSellerListAdapter = new SearchSellerListAdapter(this);
        getLeft_tv().setTextColor(ContextCompat.getColor(this, R.color.white));
        getRight_tv().setTextColor(ContextCompat.getColor(this, R.color.white));
        lv_list.setAdapter(searchSellerListAdapter);
        lv_list.setDelyTime(0);
        //  lv_list.getListView().setDivider(getDrawable(R.drawable.dotted_line));
        lv_list.getListView().setDividerHeight(DensityUtil.dip2px(2));
    }

    @Override
    protected void loadData() {
        lv_list.loadPageData();
    }

    @Override
    protected void setListener() {
        showLeft();
        setRightTvListener("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("seller_info", seller_array);
                setResult(RESULT_OK, i);
                back();


            }
        });

        lv_list.setListener(new SwipeListView.LoadCallback() {
            @Override
            public void loadList(int page) {
                searchKolPresenter.getSearchList(page, seller_array);
            }
        });
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edit_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                LogUtil.i(TAG,"afterTextChanged");
                lv_list.loadingFistEnter();
            }
        });
    }

    @Override
    public String getSearchString() {
        return edit_input.getText().toString() + "";
    }

    @Override
    public SearchSellerListAdapter getAdapter() {
        return searchSellerListAdapter;
    }

    @Override
    public SwipeListView getLv() {
        return lv_list;
    }

    @Override
    public void showFailedError(String tv) {

    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        super.setInentFliter(inentFliter);
        inentFliter.addAction(Constant.SELLER_SELECT);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
        super.handlerIntenter(context, intent);
        if (intent.getAction().equals(Constant.SELLER_SELECT)) {
            SellerInfoEntity seller_info = (SellerInfoEntity) intent.getSerializableExtra("seller_info");
            for (int i = 0; i < seller_array.size(); i++) {
                if (seller_info.getId() == seller_array.get(i).getId()) {
                    if (!seller_info.isselect())
                        seller_array.remove(i);
                    return;
                }
            }
            seller_array.add(seller_info);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lv_list != null)
            lv_list.clearSelf();
    }
}
