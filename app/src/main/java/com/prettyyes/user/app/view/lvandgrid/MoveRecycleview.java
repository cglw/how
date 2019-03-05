package com.prettyyes.user.app.view.lvandgrid;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;

import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.view.recycle.HeaderAndFooterRecyclerViewAdapter;
import com.prettyyes.user.core.rv.ItemTouchHelperAdapter;
import com.prettyyes.user.core.rv.OnRecyclerItemClickListener;
import com.prettyyes.user.core.rv.SimpleItemTouchHelperCallback;

import java.util.Collections;

/**
 * Created by chengang on 2017/6/30.
 */

public class MoveRecycleview extends RecyclerView implements ItemTouchHelperAdapter {


    private AbsRecycleAdapter absRecycleAdapter;

    public MoveRecycleview(Context context) {
        super(context);
        init();
    }

    public MoveRecycleview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MoveRecycleview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();

    }

    public void setThisAdapter(AbsRecycleAdapter adapter) {
        this.absRecycleAdapter=adapter;
        setAdapter(adapter);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(getContext(), 4);
        setLayoutManager(manager);

    }

    public void setThisAdapter(AbsRecycleAdapter adapter, int coloum) {
        this.absRecycleAdapter=adapter;
        setAdapter(adapter);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(getContext(), coloum);
        setLayoutManager(manager);


    }

    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;

    public void setHeadFootAdapter(AbsRecycleAdapter adapter) {
        this.absRecycleAdapter=adapter;
        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        this.headerAndFooterRecyclerViewAdapter = headerAndFooterRecyclerViewAdapter;
        setAdapter(headerAndFooterRecyclerViewAdapter);
    }

    private void init() {
        addOnItemTouchListener(new OnRecyclerItemClickListener(this) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                if (vh.getAdapterPosition() >= absRecycleAdapter.getDataCount()) {
                    return;
                }
                //获取系统震动服务
                Vibrator vib = (Vibrator) getContext().getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                vib.vibrate(70);
            }
        });


        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(this) {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.getAdapterPosition() >= absRecycleAdapter.getDataCount()) {
                    return makeMovementFlags(0, 0);
                }
                return super.getMovementFlags(recyclerView, viewHolder);
            }
        };
        final ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(this);

    }

    public void setSimpleItemTouchHelperCallback(ItemTouchHelper.Callback itemTouchHelperCallback) {
        final ItemTouchHelper touchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(this);
    }

    @Override
    public boolean onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        if (source.getAdapterPosition() >= absRecycleAdapter.getDataCount() || target.getAdapterPosition() >= absRecycleAdapter.getDataCount())
            return false;
        Collections.swap(absRecycleAdapter.getItems(), source.getAdapterPosition(), target.getAdapterPosition());
        //刷新位置交换

        if (headerAndFooterRecyclerViewAdapter != null)
            headerAndFooterRecyclerViewAdapter.notifyItemMoved(source.getAdapterPosition(), target.getAdapterPosition());
        else
            absRecycleAdapter.notifyItemMoved(source.getAdapterPosition(), target.getAdapterPosition());
        onItemClear(source);
        return true;
    }

    @Override
    public void onItemDissmiss(RecyclerView.ViewHolder source) {

    }

    @Override
    public void onItemSelect(RecyclerView.ViewHolder source) {
        source.itemView.setScaleX(1.2f);
        source.itemView.setScaleY(1.2f);
    }

    @Override
    public void onItemClear(RecyclerView.ViewHolder source) {
        source.itemView.setScaleX(1.0f);
        source.itemView.setScaleY(1.0f);
    }

}
