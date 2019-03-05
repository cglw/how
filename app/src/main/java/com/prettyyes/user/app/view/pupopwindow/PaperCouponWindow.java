package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.adapter.CouponPaperAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.model.coupon.CouponInfoEntity;

import java.util.ArrayList;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.pupopwindow
 * Author: SmileChen
 * Created on: 2016/11/28
 * Description: Nothing
 */
public class PaperCouponWindow extends AbsPupopWindow {


    private ArrayList<CouponInfoEntity> data = new ArrayList();
    private CouponPaperAdapter couponPaperAdapter;
    private Activity context;
    private int paper_id;

    public PaperCouponWindow(Activity context, ArrayList data, int paper_id) {
        super(context);
        this.paper_id = paper_id;
        this.data = data;
        setLayoutHeight();
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        couponPaperAdapter = new CouponPaperAdapter(context, lv_pupopl_papercoupon);
        lv_pupopl_papercoupon.setAdapter(couponPaperAdapter);
        couponPaperAdapter.addAll(data);
//        couponPaperAdapter.notifyDataSetChanged();

    }

    private RelativeLayout rel_pupop_papercoupon;
    private Button btn_pupop_papercoupon;
    private ImageView img_pupop_papercoupon_close;
    private ListView lv_pupopl_papercoupon;


    @Override
    public void bindView(View view) {
        rel_pupop_papercoupon = (RelativeLayout) view.findViewById(R.id.rel_pupop_papercoupon);
        btn_pupop_papercoupon = (Button) view.findViewById(R.id.btn_pupop_papercoupon);
        img_pupop_papercoupon_close = (ImageView) view.findViewById(R.id.img_pupop_papercoupon_close);
        lv_pupopl_papercoupon = (ListView) view.findViewById(R.id.lv_pupopl_papercoupon);
    }

    @Override
    public void setListener() {
        super.setListener();
        btn_pupop_papercoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < couponPaperAdapter.getCount(); i++) {
                    if (couponPaperAdapter.get(i).isSelected()) {
                        new OrderApiImpl().orderReward(((BaseActivity) activity).getUUId(), 3, TYPE_PAPER, paper_id, 0, 0, new NetReqCallback() {

                            @Override
                            public void apiRequestFail(String message,String method) {
                                AppUtil.showToastShort(message);
                            }

                            @Override
                            public void apiRequestSuccess(Object o,String method) {
                                AppUtil.showToastShort("打赏成功");
                                ((BaseActivity) activity).sendBrodcast(Constant.PaperCouponUsesSuccess);

                            }
                        }, couponPaperAdapter.get(i).getCode_id() + "");
                        dismiss();
                        return;
                    }
                }
                AppUtil.showToastShort("请先选择打赏优惠券");


            }
        });
        img_pupop_papercoupon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.popup_paper_coupon;
    }

    @Override
    public int getLayoutHeight() {
        return 0;
    }

    public void setLayoutHeight() {
        int height = 0;

        if (data.size() < 2) {
            height = DensityUtil.dip2px(290 + 140 * (data.size() - 1));
        } else {
            height = DensityUtil.dip2px(430);
        }
        this.setHeight(height);
    }
}
