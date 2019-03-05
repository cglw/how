package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/6/29.
 */

public class UnitListAdapter extends AbsRecycleAdapter<SpuInfoEntity> {
    public UnitListAdapter(Context context) {
        super(context, R.layout.adapter_add_suit);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final SpuInfoEntity data, int position) {
        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_uintimg);
        tv_uintname.setText(data.getSpu_name());
        tv_price.setText(String.format(context.getString(R.string.format_rmb), data.getSpu_price()));
        checkbox_select.setTag(position);
        checkbox_select.setChecked(data.isSelect());
//        checkbox_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                int tag = (int) buttonView.getTag();
//                UnitListAdapter.this.getItemData(tag).setSelect(!UnitListAdapter.this.getItemData(tag).isSelect());
//
//            }
//        });

        img_uintimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSkuActivity(context, data.getSpu_type(), data.getModule_id());
            }
        });
        holder.getRootView().setTag(checkbox_select);
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox tag = (CheckBox) v.getTag();

                if (getSelect().size() >= 10 && !tag.isChecked()) {
                    AppUtil.showToastShort("最多选择10个");
                    return;
                }
                tag.setChecked(!tag.isChecked());
                data.setSelect(tag.isChecked());

            }
        });
    }


    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        img_uintimg = holder.getView(R.id.img_uintimg);
        checkbox_select = holder.getView(R.id.checkbox);
        img_more_do = holder.getView(R.id.img_more_do);
        tv_uintname = holder.getView(R.id.tv_uintname);
        tv_price = holder.getView(R.id.tv_price);
    }

    private ImageView img_uintimg;
    private CheckBox checkbox_select;
    private ImageView img_more_do;
    private TextView tv_uintname;
    private TextView tv_price;
    private CheckBox checkbox;


    public ArrayList<SpuInfoEntity> getSelect() {
        ArrayList<SpuInfoEntity> datas = new ArrayList<>();
        for (int i = 0; i < UnitListAdapter.this.getItems().size(); i++) {
            if (UnitListAdapter.this.getItems().get(i).isSelect())
                datas.add(UnitListAdapter.this.getItems().get(i));
        }
        return datas;

    }
}
