package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/11/7
 * Description: Nothing
 */
public class SearchSellerListAdapter extends SpecilAbsAdapter<SellerInfoEntity> {

    private static final String TAG = "SearchSellerListAdapter";

    public SearchSellerListAdapter(Context context) {
        super(R.layout.item_lv_searchseller, new ArrayList<SellerInfoEntity>(), context);
    }

    @Override
    public void bindView(ViewHolder vHolder) {
        img_head = (RoundImageView) vHolder.getView(R.id.img_head);
        tv_name = (TypefaceTextView) vHolder.getView(R.id.tv_name);
        tv_desc = (TextView) vHolder.getView(R.id.tv_desc);
        view_searcgseller_check = (RelativeLayout) vHolder.getView(R.id.view_searchseller_check);
        img_check = (CheckBox) vHolder.getView(R.id.img_searchseller_select);

    }


    private RoundImageView img_head;
    private TypefaceTextView tv_name;
    private TextView tv_desc;
    private RelativeLayout view_searcgseller_check;
    private CheckBox img_check;


    @Override
    public void showData(ViewHolder vHolder, final SellerInfoEntity data, int position) {
        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolInfoActivity(context, data.getId() + "");
            }
        });

        ImageLoadUtils.loadHeadImg(context, data.getAce_img(), img_head);
        tv_name.setText(data.getNickname());
        tv_desc.setText(data.getAce_txt());
        BadgeView badgeView2 = new BadgeView(context);
        badgeView2.initNopadding(50);
        badgeView2.setTargetView(img_head);
        badgeView2.setImageTag(data.isFamous(), 50);

        if (data.isselect()) {
            img_check.setChecked(true);
        } else {
            img_check.setChecked(false);

        }

        vHolder.getRootView().setTag(data);
        vHolder.getRootView().setTag(R.id.tag, img_check);
        vHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellerInfoEntity data = (SellerInfoEntity) v.getTag();
                data.setIsselect(!data.isselect());
                CheckBox tag = (CheckBox) v.getTag(R.id.tag);
                tag.setChecked(data.isselect());
                Intent intent = new Intent();
                intent.putExtra("seller_info", data);
                intent.setAction(Constant.SELLER_SELECT);
                context.sendBroadcast(intent);

            }
        });
        img_check.setTag(data);
        img_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellerInfoEntity data = (SellerInfoEntity) v.getTag();
                data.setIsselect(!data.isselect());
                ((CheckBox) v).setChecked(data.isselect());


                Intent intent = new Intent();
                intent.putExtra("seller_info", data);
                intent.setAction(Constant.SELLER_SELECT);
                context.sendBroadcast(intent);
            }
        });

    }
}
