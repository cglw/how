package com.prettyyes.user.app.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.prettyyes.user.app.adapter.BottomDialogAdapter;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;

/**
 * Created by chengang on 2017/5/31.
 */

public class MyBottomDialog extends BottomSheetDialog {
    private BottomDialogAdapter bottomDialogAdapter;

    public MyBottomDialog(Context context) {
        super(context);
        init(context);
    }

    private void init(Context c) {
        RecyclerView recyclerView=new RecyclerView(c);
        setContentView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        bottomDialogAdapter = new BottomDialogAdapter(c);
        recyclerView.addItemDecoration(new DividerItemDecoration(c,DividerItemDecoration.HORIZONTAL_LIST));
        recyclerView.setAdapter(bottomDialogAdapter);
    }

    public BottomDialogAdapter getBottomDialogAdapter() {
        return bottomDialogAdapter;
    }

    public MyBottomDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
        init(context);
    }


}
