package com.prettyyes.user.app.base;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by chengang on 2017/7/13.
 */

public class AbsMutiRvAdapter<T extends BaseType> extends AbsRecycleAdapter<T> {

    private BaseViewTypeFactory mFactory;

    public AbsMutiRvAdapter(Context context) {
        super(context);
        mFactory = new ViewTypeFactory();
    }

    @Override
    public MutiTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mFactory.createViewHolder(parent, viewType);

    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getTypeId(mFactory);
    }


    @Override
    protected void bindData(AbsRecycleViewHolder holder, T t, int position) {
        ((MutiTypeViewHolder) holder).setAbsMutiRvAdapter(this);
        ((MutiTypeViewHolder) holder).bindData(t, position, this);

    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        ((MutiTypeViewHolder) holder).bindView();

    }


}
