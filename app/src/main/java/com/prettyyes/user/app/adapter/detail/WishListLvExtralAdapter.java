package com.prettyyes.user.app.adapter.detail;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.mvpPresenter.WishListPresenter;
import com.prettyyes.user.app.view.PupopAmountView;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/26
 * Description: Nothing
 */
public class WishListLvExtralAdapter extends SpecilAbsAdapter<WishListPresenter.WishVpEntity.WishVpListEntity> {

    public interface CartCallback {
        public void cartedit(String cart_id, int num, int cart_staus);
    }

    private CartCallback cartCallback;
    ProgressDialog progressDialog = null;

    public void setCartCallback(CartCallback cartCallback) {
        this.cartCallback = cartCallback;
    }

    public WishListLvExtralAdapter(ArrayList<WishListPresenter.WishVpEntity.WishVpListEntity> datas, Context context) {
        super(R.layout.item_lv_wishlist, datas, context);
        progressDialog = new ProgressDialog(context);

    }

    @Override
    public void bindView(ViewHolder vHolder) {
        tv_suitname = (TextView) vHolder.getView(R.id.tv_wishlistLvAdp_name);
        rel_suit = (RelativeLayout) vHolder.getView(R.id.rel_wishlistLvAdp_suit);
        rel_translate = (RelativeLayout) vHolder.getView(R.id.rel_wishlistLvAdp_tranlate);
        checkbox = (CheckBox) vHolder.getView(R.id.checkbox_wishlistLvAdp_select);
        img_uintimg = (ImageView) vHolder.getView(R.id.img_wishlistLvAdp_uintimg);
        pAmountview = (com.prettyyes.user.app.view.PupopAmountView) vHolder.getView(R.id.pAmountview_wishlistLvAdp);
        tv_uintname = (TextView) vHolder.getView(R.id.tv_wishlistLvAdp_uintname);
        tv_price = (TextView) vHolder.getView(R.id.tv_wishlistLvAdp_price);
    }

    @Override
    public void showData(ViewHolder vHolder, final WishListPresenter.WishVpEntity.WishVpListEntity data, final int position) {
        if (data.getType().equals("title")) {
            tv_suitname.setVisibility(View.VISIBLE);
            tv_suitname.setText(data.getSuit_name());
            rel_suit.setVisibility(View.GONE);
            tv_suitname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ClickUtils.isFastDoubleClick()) {
                        if (progressDialog == null) {
                            progressDialog = new ProgressDialog(context);
                        }
                        progressDialog.setMessage("提交中...");
                        progressDialog.setCancelable(true);
                        progressDialog.show();

                        for (int i = position + 1; i < WishListLvExtralAdapter.this.getCount(); i++) {
                            if (WishListLvExtralAdapter.this.get(i).getType().equals("title")) {
                                return;
                            } else {
                                final int j = i;
                                new OrderApiImpl().cartEdit(BaseApplication.UUID, WishListLvExtralAdapter.this.get(i).getCart_id() + "", WishListLvExtralAdapter.this.get(i).getNum(), 1, new NetReqCallback() {
                                    @Override
                                    public void apiRequestFail(String message,String method) {
                                        if (progressDialog != null)
                                            progressDialog.dismiss();
                                    }

                                    @Override
                                    public void apiRequestSuccess(Object o,String method) {
                                        if (progressDialog != null)
                                            progressDialog.dismiss();
                                        WishListLvExtralAdapter.this.get(j).setCart_status(1);
                                        WishListLvExtralAdapter.this.notifyDataSetChanged();


                                    }
                                });


                            }
                            WishListLvExtralAdapter.this.notifyDataSetChanged();


                        }
                    }
                }
            });
        } else {
            tv_suitname.setVisibility(View.GONE);
            rel_suit.setVisibility(View.VISIBLE);
            tv_uintname.setText(data.getUnit_name());
            tv_price.setText(Constant.RMB + data.getPrice());
            pAmountview.setGoods_Num(data.getNum());
            ImageLoadUtils.loadListimg(context, data.getImg(), img_uintimg);
            if (data.getCart_status() == 1) {
                checkbox.setChecked(true);
                rel_translate.setVisibility(View.VISIBLE);
            } else {
                checkbox.setChecked(false);
                rel_suit.setAlpha(1.0f);
                rel_translate.setVisibility(View.GONE);

            }

            pAmountview.setOnAmountChangeListener(new PupopAmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    data.setNum(amount);
                }
            });

            rel_suit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    cartEditChangeState(data, v, true);
                }
            });


            rel_translate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartEditChangeState(data, v, false);
                }
            });
        }
    }

    private void cartEditChangeState(final WishListPresenter.WishVpEntity.WishVpListEntity data, final View v, final boolean rel_suit) {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setMessage("提交中...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        if (rel_suit) {
            data.setCart_status(1);
        } else {
            data.setCart_status(0);
        }

//        CheckBox childAt = (CheckBox) ((RelativeLayout) v).getChildAt(0);
//        if (childAt != null)
//            childAt.setChecked(childAt.isChecked());

        if (!ClickUtils.isFastDoubleClick())
            new OrderApiImpl().cartEdit(BaseApplication.UUID, data.getCart_id() + "", data.getNum(), data.getCart_status(), new NetReqCallback() {
                @Override
                public void apiRequestFail(String message,String method) {
                    AppUtil.showToastShort(message);
                    if (progressDialog != null)
                        progressDialog.dismiss();
                }

                @Override
                public void apiRequestSuccess(Object o,String method) {
                    if (progressDialog != null)
                        progressDialog.dismiss();
                    if (rel_suit) {
                        data.setCart_status(1);
                        ((RelativeLayout) v).getChildAt(((RelativeLayout) v).getChildCount() - 1).setVisibility(View.VISIBLE);
                        ((CheckBox) ((RelativeLayout) v).getChildAt(0)).setChecked(true);

                    } else {
                        data.setCart_status(0);
                        v.setVisibility(View.GONE);
                        ((CheckBox) ((RelativeLayout) v.getParent()).getChildAt(0)).setChecked(false);

                    }


                }
            });
    }

    private TextView tv_suitname;
    private RelativeLayout rel_suit;
    private RelativeLayout rel_translate;
    private CheckBox checkbox;
    private ImageView img_uintimg;
    private PupopAmountView pAmountview;
    private TextView tv_uintname;
    private TextView tv_price;
    private TextView tv_remove;


}
