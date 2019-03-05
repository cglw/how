package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.WishListPresenter;
import com.prettyyes.user.app.ui.kol.KolInfoActivity;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/26
 * Description: Nothing
 */
public class WishListVpAdapter extends AbsVpAdapter<WishListPresenter.WishVpEntity> {
    public WishListVpAdapter(Context context, WishListLvExtralAdapter.CartCallback cartCallback) {
        super(context, new ArrayList<WishListPresenter.WishVpEntity>(), R.layout.item_vp_wishlist);
        this.cartCallback = cartCallback;
    }

    private WishListLvExtralAdapter.CartCallback cartCallback;


    private ListView lv_wishlistAdp;
    private RoundImageView img_head;

    @Override
    public void bindView(ViewHolder vHolder) {
        lv_wishlistAdp = (ListView) vHolder.getView(R.id.lv_wishlistVpAdp);
        img_head = (RoundImageView) vHolder.getView(R.id.img_wishlistVpAdp_head);
    }

    @Override
    public void showData(ViewHolder vHolder, final WishListPresenter.WishVpEntity data, int position) {
        int height = 0;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) lv_wishlistAdp.getLayoutParams();
        for (int i = 0; i < data.getSuitlist().size(); i++) {
            if (data.getSuitlist().get(i).getType().equals("title")) {
                height += 30;
            } else {
                height += 102;
            }
        }
        height += (data.getSuitlist().size() - 1) * 3;
        layoutParams.height = DensityUtil.dip2px(height);
        WishListLvExtralAdapter adapter = new WishListLvExtralAdapter((ArrayList<WishListPresenter.WishVpEntity.WishVpListEntity>) data.getSuitlist(), context);
        adapter.setCartCallback(cartCallback);
        lv_wishlistAdp.setAdapter(adapter);
        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KolInfoActivity.class);
                intent.putExtra("seller_id", data.getSeller_id());
                ((BaseActivity)context).nextActivity(intent);
            }
        });

        BadgeView badgeView2 = new BadgeView(context);
        badgeView2.initNopadding(50);
        badgeView2.setTargetView(img_head);
        badgeView2.setImageTag(AppUtil.isFamous(data.getFamous_type()),50);


        ImageLoadUtils.loadHeadImg(context,data.getHeadimg(), img_head);
    }

}
