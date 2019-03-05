package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.model.coupon.CouponInfoEntity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter
 * Author: SmileChen
 * Created on: 2016/8/29
 * Description: Nothing
 */
public class CouponAdapter extends AbsRecycleAdapter<CouponInfoEntity> {
    public CouponAdapter(Context context) {
        super(context,R.layout.item_lv_coupon);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, CouponInfoEntity data, int position) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "Athelas_W01_Italic.ttf");
        tv_couponAdp_price.setTypeface(type);
        tv_couponAdp_price.setText(Constant.RMB + data.getCoupon_price());
        tv_couponAdp_name.setText(data.getContent());
        tv_couponAdp_coupon_txt.setText(Constant.CENTER_POINT + " " + data.getCoupon_txt());
        tv_couponAdp_end_time.setText(Constant.CENTER_POINT + " " + "截止" + data.getEnd_time());
        if (data.getCoupon_type() == null) {
            return;
        }
        if (data.getCoupon_type().equals("1")) {
            img_coupon_bg.setImageResource(R.mipmap.bg_coupon_blue);
        } else if (data.getCoupon_type().equals("2")) {
            img_coupon_bg.setImageResource(R.mipmap.coupon_list_paper_coupon);

        }
    }

    @Override
    protected void bindView(AbsRecycleViewHolder vHolder) {
        tv_couponAdp_price = (TextView) vHolder.getView(R.id.tv_coupon_price);
        tv_couponAdp_name = (TextView) vHolder.getView(R.id.tv_coupon_name);
        tv_couponAdp_coupon_txt = (TextView) vHolder.getView(R.id.tv_coupon_content);
        tv_couponAdp_end_time = (TextView) vHolder.getView(R.id.tv_coupon_endtime);
        img_coupon_bg = (ImageView) vHolder.getView(R.id.img_coupon_bg);
    }

    private TextView tv_couponAdp_price;
    private TextView tv_couponAdp_name;
    private TextView tv_couponAdp_coupon_txt;
    private TextView tv_couponAdp_end_time;
    private ImageView img_coupon_bg;

}
