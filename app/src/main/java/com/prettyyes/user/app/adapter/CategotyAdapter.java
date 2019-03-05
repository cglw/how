package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.model.CategoryModel;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/6/6.
 */

public class CategotyAdapter extends AbsRecycleAdapter<CategoryModel> {
    public CategotyAdapter(Context context) {
        super(context, R.layout.adapter_category);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final CategoryModel categoryModel, final int position) {
//        ImageLoadUtils.loadListimg(context, categoryModel.getIcon(), img_ic);
        tv_name.setText(categoryModel.getCat_name());
        holder.getRootView().setTag(position);
        if (categoryModel.isSelect())
            img_select.setVisibility(View.VISIBLE);
        else
            img_select.setVisibility(View.GONE);

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                CategotyAdapter.this.getItems().get(tag).setSelect(!CategotyAdapter.this.getItems().get(tag).isSelect());
                CategotyAdapter.this.notifyItemChanged(tag);
            }
        });

    }

    private ImageView img_ic;
    private TextView tv_name;
    private ImageView img_select;

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        img_ic = holder.getView(R.id.img_category_ic);
        tv_name = holder.getView(R.id.tv_category_name);
        img_select = holder.getView(R.id.img_select);
    }

    public ArrayList<CategoryModel> getSelect() {
        ArrayList<CategoryModel> datas = new ArrayList<>();
        for (int i = 0; i < CategotyAdapter.this.getItems().size(); i++) {
            if (CategotyAdapter.this.getItems().get(i).isSelect())
                datas.add(CategotyAdapter.this.getItems().get(i));
        }
        return datas;

    }
}
