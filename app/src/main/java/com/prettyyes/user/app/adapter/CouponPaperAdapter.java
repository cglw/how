package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.view.app.SelectView;
import com.prettyyes.user.model.coupon.CouponInfoEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.order
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public class CouponPaperAdapter extends SpecilAbsAdapter<CouponInfoEntity> {


    private TextView tv_couponAdp_price;
    private TextView tv_couponAdp_name;
    private TextView tv_couponAdp_coupon_txt;
    private TextView tv_couponAdp_coupon_time;
    private SelectView view_coupon_select;
    private ListView lv;

    public CouponPaperAdapter(Context context, ListView lv) {
        super(R.layout.item_recycle_coupon_paper, new ArrayList<CouponInfoEntity>(), context);
        this.lv = lv;
    }


    @Override
    public void bindView(ViewHolder holder) {

        tv_couponAdp_price = (TextView) holder.getView(R.id.tv_coupon_paper_price);
        tv_couponAdp_name = (TextView) holder.getView(R.id.tv_coupon_paper_name);
        tv_couponAdp_coupon_txt = (TextView) holder.getView(R.id.tv_coupon_paper_content);
        tv_couponAdp_coupon_time = (TextView) holder.getView(R.id.tv_coupon_paper_endtime);
        view_coupon_select = (SelectView) holder.getView(R.id.view_coupon_paper_select);

    }

    @Override
    public void showData(ViewHolder vHolder, final CouponInfoEntity data, final int position) {
//        final Typeface type = Typeface.createFromAsset(context.getAssets(), "Athelas_W01_Italic.ttf");
//        tv_couponAdp_price.setTypeface(type);
        tv_couponAdp_price.setText(Constant.RMB + data.getCoupon_price());
        tv_couponAdp_name.setText(data.getContent());
        tv_couponAdp_coupon_txt.setText(Constant.CENTER_POINT + " " + data.getCoupon_txt());
        tv_couponAdp_coupon_time.setText(Constant.CENTER_POINT + " " + "截止" + data.getEnd_time());


        view_coupon_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setSelected(!data.isSelected());
                //其他的都需要变成没有选择
                if (data.isSelected()) {
                    for (int i = 0; i < getCount(); i++) {
                        if (i != position) {
                            datas.get(i).setSelected(false);
                        }
                    }
                    for (int i = 0; i < lv.getChildCount(); i++) {
                        View item =  lv.getChildAt(i);
                        SelectView select = (SelectView) item.findViewById(R.id.view_coupon_paper_select);
                        select.getTickPlusDrawable().toggle(false);
                    }
                }
                ((SelectView) v).getTickPlusDrawable().toggle(data.isSelected());

            }
        });

        view_coupon_select.getTickPlusDrawable().toggleNotime(data.isSelected());

    }

}
