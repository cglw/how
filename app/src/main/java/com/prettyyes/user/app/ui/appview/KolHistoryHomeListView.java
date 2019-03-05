package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.mainpage.KolHistotyVpAdapter;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.v8.ActInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/5/9.
 */

public class KolHistoryHomeListView extends AbsRelativelayoutView {


//    private KolHistotyHomeAdapter kolHistotyHomeAdapter;
//    private RecyclerView rv;

    private static final String TAG = "KolHistoryHomeListView";

    private ViewPager vp;
    private LinearLayout ll_index;
    private KolHistotyVpAdapter kolHistotyVpAdapter;

    public KolHistoryHomeListView(Context context) {
        super(context);
    }

    public KolHistoryHomeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_kolhistory;
    }


    public void setData(List<ActInfoEntity> datas) {
//        kolHistotyHomeAdapter.clear();
//        kolHistotyHomeAdapter.addAll(datas);
        if (datas == null)
            return;
        kolHistotyVpAdapter.clear();
        kolHistotyVpAdapter.addAll((ArrayList<ActInfoEntity>) datas);
        if (datas.size() == ll_index.getChildCount()) {
            return;
        } else if (datas.size() < ll_index.getChildCount()) {
            for (int i = ll_index.getChildCount() - 1; i > datas.size() - 1; i--) {
                ll_index.removeViewAt(i);
            }

        } else if (datas.size() > ll_index.getChildCount()) {
            for (int i = ll_index.getChildCount(); i < datas.size(); i++) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_history_act_index, ll_index, false);
                ll_index.addView(inflate);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) inflate.getLayoutParams();
                layoutParams.setMargins(AppUtil.dip2px(5), 0, 0, 0);
            }
        }
        for (int i = 0; i < ll_index.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) ll_index.getChildAt(i);

            if (i == vp.getCurrentItem()) {
                childAt.getChildAt(0).setVisibility(VISIBLE);
            } else {
                childAt.getChildAt(0).setVisibility(GONE);

            }
            childAt.setTag(i);
            childAt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (int) v.getTag();
                    vp.setCurrentItem(index);

                }
            });
        }

    }

    private void refreshIndex() {
        for (int i = 0; i < ll_index.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) ll_index.getChildAt(i);

            if (i == vp.getCurrentItem()) {
                childAt.getChildAt(0).setVisibility(VISIBLE);
            } else {
                childAt.getChildAt(0).setVisibility(GONE);

            }

        }
    }

    @Override
    public void initViews() {
        this.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
//        setBackgroundColor(Color.WHITE);
        setFocusable(false);

        kolHistotyVpAdapter = new KolHistotyVpAdapter(getContext());
        ll_index = (LinearLayout) getView(R.id.ll_index);
        vp = (ViewPager) getView(R.id.vp);
        vp.setAdapter(kolHistotyVpAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                refreshIndex();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        rv = (RecyclerView) getView(R.id.rv);
//        kolHistotyHomeAdapter = new KolHistotyHomeAdapter(getActivity());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rv.setLayoutManager(linearLayoutManager);
//        rv.setAdapter(kolHistotyHomeAdapter);

    }

    @Override
    public void setDataByModel(Object data) {

    }

}
