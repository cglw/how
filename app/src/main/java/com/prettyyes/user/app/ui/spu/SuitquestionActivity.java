package com.prettyyes.user.app.ui.spu;

import android.content.Context;
import android.content.Intent;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SuitApiImpl;
import com.prettyyes.user.app.adapter.SuitAnswerAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.model.Suit.AnswerListBySku;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

public class SuitquestionActivity extends BaseActivity {


    @ViewInject(R.id.swipelist)
    private SwipeRecycleView swipeRecycleView;
    private SuitAnswerAdapter adapter;
    private int suit_id;
    private String suit_img;
    private String type;
    private String suit_name;

    @Override
    protected int bindLayout() {
        return R.layout.activity_suit_to_quesion;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        suit_id = getIntent().getIntExtra("sku_id", 0);
        suit_img = getIntent().getStringExtra("suit_img");
        type = getIntent().getStringExtra("type");
        suit_name = getIntent().getStringExtra("suit_name");
    }


    public static void goSuitToQuestion(Context c, int sku_id, String suit_img, String type, String suit_name) {
        Intent i = new Intent(c, SuitquestionActivity.class);
        i.putExtra("sku_id", sku_id);
        i.putExtra("suit_img", suit_img);
        i.putExtra("type", type);
        i.putExtra("suit_name", suit_name);
        if (c instanceof BaseActivity)
            ((BaseActivity) c).nextActivity(i);
        else
            c.startActivity(i);

    }

    @Override
    protected void initViews() {

        setActivtytitle(suit_name);
        adapter = new SuitAnswerAdapter(this);
        swipeRecycleView.setAdapter(adapter);
        adapter.setImg(suit_img);

    }

    @Override
    protected void setListener() {
        super.setListener();
        swipeRecycleView.setListener(new SwipeRecycleView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getList(page);
            }
        });
    }

    private void getList(final int page) {

        new SuitApiImpl().answerListBySku(getUUId(), suit_id + "", page, type, new NetReqCallback<AnswerListBySku>() {
            @Override
            public void apiRequestFail(String message,String method) {
                if(page==1)
                loadFail();
                swipeRecycleView.loadingfail();

            }

            @Override
            public void apiRequestSuccess(AnswerListBySku data,String method) {
                loadSuccess();
                if (data.getData().size() < MIN_PAGE_SIZE) {
                    swipeRecycleView.loadingEnd();
                } else {
                    swipeRecycleView.loadingSuccessHavedata();
                }
                adapter.addAll(data.getData());
                if (data.getData().size() < MIN_PAGE_SIZE)
                    swipeRecycleView.loadingEndWithout();
            }
        });
    }


    @Override
    protected void loadData() {
        swipeRecycleView.loadingFistEnter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null)
            adapter.clear();
        if (swipeRecycleView != null)
            swipeRecycleView.clearSelf();

    }


}
