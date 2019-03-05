package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.mainpage.HomeCategoryAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.event.ChangeKolCategoryEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.QueCategoryEntity;

/**
 * Created by chengang on 2017/7/10.
 */

public class HomeCategoryPupopWindow extends AbsPupopWindow {

    public HomeCategoryPupopWindow(Activity context) {
        super(context);
    }

    private HomeCategoryAdapter homeCategoryAdapter;

    @Override
    public void bindView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        homeCategoryAdapter = new HomeCategoryAdapter(activity);

//        new KolCategoryRequest().post(new NetReqCallback<KolCategoryRes>() {
//            @Override
//            public void apiRequestFail(String message, String method) {
//
//            }
//
//            @Override
//            public void apiRequestSuccess(KolCategoryRes queCategoryEntities, String method) {
//                homeCategoryAdapter.addAll(queCategoryEntities.getList());
//            }
//        });

        QueCategoryEntity queCategoryEntity = new QueCategoryEntity();
        queCategoryEntity.setCategory("生活方式");
        queCategoryEntity.setCategory_id("6");
        queCategoryEntity.setIc(R.mipmap.how_home_category1);

        QueCategoryEntity queCategoryEntity1 = new QueCategoryEntity();

        queCategoryEntity1.setCategory_id("3");
        queCategoryEntity1.setCategory("心情");
        queCategoryEntity1.setIc(R.mipmap.how_home_category2);

        QueCategoryEntity queCategoryEntity2 = new QueCategoryEntity();

        queCategoryEntity2.setCategory_id("10");
        queCategoryEntity2.setCategory("书影音");
        queCategoryEntity2.setIc(R.mipmap.how_home_category3);

        QueCategoryEntity queCategoryEntity3 = new QueCategoryEntity();

        queCategoryEntity3.setCategory_id("13");
        queCategoryEntity3.setCategory("美食");
        queCategoryEntity3.setIc(R.mipmap.how_home_category4);

        homeCategoryAdapter.add(queCategoryEntity);
        homeCategoryAdapter.add(queCategoryEntity1);
        homeCategoryAdapter.add(queCategoryEntity2);
        homeCategoryAdapter.add(queCategoryEntity3);
        LinearLayoutManager layout = new LinearLayoutManager(activity);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(homeCategoryAdapter);

        homeCategoryAdapter.setMyOnItemClickListener(new AbsRecycleAdapter.OnMyItemClickListener<QueCategoryEntity>() {
            @Override
            public void clickItem(AbsRecycleViewHolder holder, View view, QueCategoryEntity o, int position) {
                AppRxBus.getInstance().post(new ChangeKolCategoryEvent(o));
                dismiss();
            }
        });
    }

    @Override
    public int setAnimation() {
        return R.style.animon_alpha;
    }

    RecyclerView recyclerView;


    @Override
    public int getLayout() {
        return R.layout.pupop_home_category;
    }

    @Override
    public int getLayoutHeight() {
        return 107;
    }
}
