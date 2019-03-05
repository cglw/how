package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.event.ClickItemTextEvent;
import com.prettyyes.user.core.event.ItemTextPupopDismissEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.v8.ItemTextModel;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/7/14.
 */

public class ItemTextPupop extends AbsPupopWindow {

    public ItemTextPupop(Activity context, ArrayList<ItemTextModel> data) {
        super(context);
        AbsMutiRvAdapter adapter = new AbsMutiRvAdapter(activity);
        adapter.addAll(data);
        recyclerView = (RecyclerView) mMenuView.findViewById(R.id.rv);
        recyclerView.setBackgroundColor(Color.WHITE);
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL_LIST));

        recyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(activity, android.support.v7.widget.DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        adapter.setMyOnItemClickListener(new AbsRecycleAdapter.OnMyItemClickListener<ItemTextModel>() {
            @Override
            public void clickItem(AbsRecycleViewHolder holder, View view, ItemTextModel o, int position) {
                AppRxBus.getInstance().post(new ClickItemTextEvent(o.getText()));
                dismiss();

            }
        });

    }

    @Override
    public void bindView(View view) {


    }

    @Override
    public int setAnimation() {
        return R.style.animon_alpha;
    }

    RecyclerView recyclerView;


    @Override
    public int getLayout() {
        return R.layout.recycleview;
    }

    @Override
    public int getLayoutHeight() {
        return 0;
    }

    @Override
    public void setHeightBy() {
        super.setHeightBy();
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        AppRxBus.getInstance().post(new ItemTextPupopDismissEvent());
    }
}
