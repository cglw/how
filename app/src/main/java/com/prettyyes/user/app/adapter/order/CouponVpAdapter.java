package com.prettyyes.user.app.adapter.order;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.view.app.SelectView;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.coupon.CouponInfoEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.order
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public class CouponVpAdapter extends AbsVpAdapter<CouponInfoEntity> {

    private static final String TAG = "CouponVpAdapter";

    public CouponVpAdapter(Context context) {
        super(context, new ArrayList(), R.layout.item_vp_coupon);
    }

    private final static int tag_1 = 3 << 24;
    private final static int tag_2 = 4 << 24;
    private final static int tag_3 = 5 << 24;

    @Override
    public void bindView(ViewHolder vHolder) {
        tv_couponAdp_price = (TextView) vHolder.getView(R.id.tv_coupon_price);
        tv_couponAdp_name = (TextView) vHolder.getView(R.id.tv_coupon_name);
        tv_couponAdp_coupon_txt = (TextView) vHolder.getView(R.id.tv_coupon_content);
        tv_couponAdp_end_time = (TextView) vHolder.getView(R.id.tv_coupon_endtime);
        img_coupon_bg = (ImageView) vHolder.getView(R.id.img_coupon_bg);
        view_coupon_select = (SelectView) vHolder.getView(R.id.view_coupon_select);
    }

    public void selectItem(int index) {
        int tag = (int) view_coupon_select.getTag();
        if (tag == index)
            view_coupon_select.getTickPlusDrawable().setSelect();

    }


    boolean isfirst = true;


    @Override
    public void showData(ViewHolder vHolder, final CouponInfoEntity data, int position) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "Athelas_W01_Italic.ttf");
        tv_couponAdp_price.setTypeface(type);
        tv_couponAdp_price.setText(Constant.RMB + data.getCoupon_price());
        tv_couponAdp_name.setText(data.getCoupon_name());
        tv_couponAdp_coupon_txt.setText(Constant.CENTER_POINT + " " + data.getCoupon_txt());
        view_coupon_select.setTag(position);

//        if (isfirst && data.isSelected()) {
//            view_coupon_select.getTickPlusDrawable().setSelect();
//            isfirst = false;
//        }

        vHolder.getRootView().setTag(tag_2, view_coupon_select);
        vHolder.getRootView().setTag(tag_3, data);
        vHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectView view = (SelectView) v.getTag(tag_2);
                CouponInfoEntity data_tag = (CouponInfoEntity) v.getTag(tag_3);
                boolean select = data_tag.isSelected();

                for (int i = 0; i < CouponVpAdapter.this.getCount(); i++) {
                    CouponVpAdapter.this.get(i).setSelected(false);
                }
                data_tag.setSelected(!select);

                if (data_tag.isSelected())
                    view.getTickPlusDrawable().toggle(true);
                else
                    view.getTickPlusDrawable().toggle(false);

                AppRxBus.getInstance().post(data_tag);
            }
        });
    }


    private TextView tv_couponAdp_price;
    private TextView tv_couponAdp_name;
    private TextView tv_couponAdp_coupon_txt;
    private TextView tv_couponAdp_end_time;
    private ImageView img_coupon_bg;
    private SelectView view_coupon_select;

}
