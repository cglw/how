package com.prettyyes.user.app.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.appview.KolSmallView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ChangeCartStatusOrNumEvent;
import com.prettyyes.user.core.order.OrderManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.order.CartItemModel;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/6/6.
 */

public class CartListAdapter extends AbsRecycleAdapter<CartItemModel> {
    private static final String TAG = "CartListAdapter";
    private OrderApiImpl orderApi;

    public CartListAdapter(Context context) {
        super(context, R.layout.item_lv_orderinfo_new);
        orderApi = new OrderApiImpl();
    }

    private final static int tag_1 = 3 << 24;
    private final static int tag_2 = 4 << 24;
    private final int payload_child = 1;
    private final int payload_parent = 2;

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final CartItemModel cartItemModel, int position) {

        if (cartItemModel.getType().equals("kol")) {
            view_kol_info.setVisibility(View.VISIBLE);
            view_suit.setVisibility(View.GONE);
//            tv_nickname.setText(cartItemModel.getNickname());

//            ImageLoadUtils.loadHeadImg(context, cartItemModel.getHeadimg(), img_kol_head);

            SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
            sellerInfoEntity.setSeller_id(cartItemModel.getSeller_id());
            sellerInfoEntity.setGrade_title(cartItemModel.getGrade_title());
            sellerInfoEntity.setHeadimg(cartItemModel.getHeadimg());
            sellerInfoEntity.setNickname(cartItemModel.getNickname());
            sellerInfoEntity.setFamous_type(cartItemModel.getFamous_type());
            kol_samllview.setSellerInfo(sellerInfoEntity);
            check_kol.setChecked(cartItemModel.isKol_select());
            check_kol.setTag(position);
            check_kol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (int) v.getTag();
                    CheckBox view = (CheckBox) v;
                    int cart_status = view.isChecked() ? 1 : 0;
                    //先不改变状态
                    view.setChecked(!view.isChecked());
                    for (int i = 0; i < CartListAdapter.this.getItemData(index).getUnit_childs().size(); i++) {
                        editCartChild(index + 1 + i, CartListAdapter.this.getItemData(index + 1 + i).getNum(), cart_status);
                    }

                }
            });
//            img_kol_head.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    JumpPageManager.getManager().goKolInfoActivity(context,cartItemModel.getSeller_id());
////                    KolInfoActivity.goKolInfo(context, Integer.parseInt(cartItemModel.getSeller_id()));
//                }
//            });
        } else {
            view_kol_info.setVisibility(View.GONE);
            view_suit.setVisibility(View.VISIBLE);
            check_unit.setChecked(cartItemModel.getCart_status() == 1);
            tv_uintname.setText(cartItemModel.getUnit_name());
            tv_num.setText(String.format("%s", cartItemModel.getNum()));
            tv_price.setText(String.format("¥ %s", cartItemModel.getPrice()));
            ImageLoadUtils.loadListimg(context, cartItemModel.getUnit_img(), img_unit);
            img_minus.setTag(tag_1, tv_num);
            img_minus.setTag(tag_2, cartItemModel);
            img_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tv_num = (TextView) v.getTag(tag_1);
                    CartItemModel data = (CartItemModel) v.getTag(tag_2);
                    int target_num = data.getNum() - 1;
                    if (target_num >= 1) {
                        editNum(tv_num, data, target_num);
                    }


                }
            });
            tv_attr_str.setText(StringUtils.getAttrs(cartItemModel.getAttr_value_text()));
            img_plus.setTag(tag_1, tv_num);
            img_plus.setTag(tag_2, cartItemModel);
            img_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TextView tv_num = (TextView) v.getTag(tag_1);
                    final CartItemModel data = (CartItemModel) v.getTag(tag_2);
                    final int target_num = data.getNum() + 1;
                    if (data.getNum() <= 100) {
                        editNum(tv_num, data, target_num);
                    }

                }
            });
            check_unit.setTag(position);
            check_unit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox view = (CheckBox) v;
                    int need_cart_status = view.isChecked() ? 1 : 0;
                    //先不改变状态
                    view.setChecked(!view.isChecked());
                    int index = (int) v.getTag();
                    CartItemModel child = CartListAdapter.this.getItemData(index);
                    editCartChild(index, child.getNum(), need_cart_status);


                }
            });
            rel_delete.setTag(position);
            rel_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int index = (int) v.getTag();
                    deleteData(index);
                }
            });


            img_unit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.i(TAG, "-->" + cartItemModel.getModule_id() + "-->" + cartItemModel.getSku_type());
                    JumpPageManager.getManager().goSkuActivity(context, cartItemModel.getSku_type(), cartItemModel.getModule_id());
                }
            });
            ((View) tv_uintname.getParent()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpPageManager.getManager().goSkuActivity(context, cartItemModel.getSku_type(), cartItemModel.getModule_id());

                }
            });

            ((View) img_plus.getParent()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
    }

    private void editNum(final TextView tv_num, final CartItemModel data, final int target_num) {
        showProgressDialog();
        orderApi.cartEdit(BaseApplication.UUID, data.getCart_id() + "", target_num, data.getCart_status(), new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {
                dismissProdissDialog();

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                dismissProdissDialog();
                data.setNum(target_num);
                tv_num.setText(String.format("%s", data.getNum()));
                AppRxBus.getInstance().post(new ChangeCartStatusOrNumEvent());
                OrderManager.getInstance().refreshWishListNum();


            }
        });
    }


    private void editCartChild(final int index, int num, final int cart_status) {
        final CartItemModel child = CartListAdapter.this.getItemData(index);
        showProgressDialog();
        new OrderApiImpl().cartEdit(BaseApplication.UUID, CartListAdapter.this.getItemData(index).getCart_id() + "", num, cart_status, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {
                dismissProdissDialog();
            }

            @Override
            public void apiRequestSuccess(Object o, String method) {

                dismissProdissDialog();
                child.setCart_status(cart_status);

                for (int i = index; i >= 0; i--) {
                    CartItemModel parent = CartListAdapter.this.getItemData(i);
                    if (child.getSeller_id().equals(parent.getSeller_id())
                            && parent.getType().equals("kol")) {
                        //找到父亲,，修改父亲数据源，修改父亲视图,找到父亲在第 i 个位置
                        for (int j = 0; j < parent.getUnit_childs().size(); j++) {
                            if (parent.getUnit_childs().get(j).getCart_id().equals(child.getCart_id())) {
                                parent.getUnit_childs().get(j).setCart_status(child.getCart_status());
                                CartListAdapter.this.refreshItem(i, payload_parent);
                                break;
                            }

                        }
                        break;
                    }

                }
                CartListAdapter.this.refreshItem(index, payload_child);
                AppRxBus.getInstance().post(new ChangeCartStatusOrNumEvent());

            }
        });


    }

    private Bundle getPayloads(int payload) {
        Bundle payloadNew = new Bundle();
        payloadNew.putInt("KEY_BOOLEAN", payload);
        return payloadNew;
    }

    private void deleteData(final int index) {

        new AlertDialog.Builder(context).setTitle("提醒").setMessage("确定删除这商品？").setPositiveButton("取消", null).setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                //获取当前的cart——id
                final String cart_id = CartListAdapter.this.getItemData(index).getCart_id();
                final String seller_id = CartListAdapter.this.getItemData(index).getSeller_id();

                showProgressDialog();
                orderApi.cartdelgoods(BaseApplication.UUID, cart_id + "", new NetReqCallback() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        dismissProdissDialog();
                        AppUtil.showToastShort(message);

                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {

                        dismissProdissDialog();
                        //删除当前的
                        CartListAdapter.this.remove(index);

                        for (int i = index - 1; i >= 0; i--) {
                            CartItemModel parent = CartListAdapter.this.getItemData(i);
                            if (seller_id.equals(parent.getSeller_id())
                                    && parent.getType().equals("kol")) {
                                //找到父亲,，修改父亲数据源，修改父亲视图,找到父亲在第 i 个位置
                                for (int j = 0; j < parent.getUnit_childs().size(); j++) {
                                    if (parent.getUnit_childs().get(j).getCart_id().equals(cart_id)) {
                                        parent.getUnit_childs().remove(j);
                                        if (parent.getUnit_childs().size() <= 0) {
                                            CartListAdapter.this.remove(i);

                                            //删除父亲
                                        } else {
                                            CartListAdapter.this.refreshItem(i, payload_parent);
                                        }
                                        break;
                                    }

                                }


                                break;
                            }

                        }
                        OrderManager.getInstance().refreshWishListNum();

                        AppRxBus.getInstance().post(new ChangeCartStatusOrNumEvent());

                    }
                });


            }
        }).show();


    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        view_kol_info = holder.getView(R.id.view_kol_info);
        check_kol = holder.getView(R.id.check_kol);
//        img_kol_head = holder.getView(R.id.img_kol_head);
        view_suit = holder.getView(R.id.view_suit);
        check_unit = holder.getView(R.id.check_unit);
        img_unit = holder.getView(R.id.img_unit);
        tv_uintname = holder.getView(R.id.tv_uintname);
//        tv_nickname = holder.getView(R.id.tv_nickname);
        img_minus = holder.getView(R.id.img_minus);
        tv_num = holder.getView(R.id.tv_num);
        img_plus = holder.getView(R.id.img_plus);
        tv_price = holder.getView(R.id.tv_price);
        rel_delete = holder.getView(R.id.rel_delete);
        tv_attr_str = holder.getView(R.id.tv_attr_str);
        kol_samllview = holder.getView(R.id.kol_samllview);


    }


    @Override
    public void onBindViewHolder(AbsRecycleViewHolder holder, int position, List<Object> payloads) {

        if (payloads == null || payloads.isEmpty()) {
            bindView(holder);
            bindData(holder, CartListAdapter.this.getItemData(position), position);
        } else {
            if ((Integer) payloads.get(0) == payload_parent) {
                check_kol.setChecked(CartListAdapter.this.getItemData(position).isKol_select());
            } else if ((Integer) payloads.get(0) == payload_child) {
                check_unit.setChecked(CartListAdapter.this.getItemData(position).getCart_status() == 1);
            }
        }
        super.onBindViewHolder(holder, position, payloads);
    }

    private KolSmallView kol_samllview;
    private RelativeLayout view_kol_info;
    private CheckBox check_kol;
    //    private ImageView img_kol_head;
    private RelativeLayout view_suit;
    private CheckBox check_unit;
    private ImageView img_unit;
    private TextView tv_uintname;
    //    private TextView tv_nickname;
    private ImageView img_minus;
    private TextView tv_num;
    private ImageView img_plus;
    private TextView tv_price;
    private TextView tv_attr_str;
    private View rel_delete;


    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("正在提交...");
        }
        progressDialog.show();
    }

    private void dismissProdissDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();

    }

    private void goSkuActivity(int sku_id, String sku_type) {
        JumpPageManager.getManager().goSkuActivity((Activity) context, sku_type, sku_id + "");
    }

    private ProgressDialog progressDialog;

    public double getTotalPrice() {
        double total = 0;
        for (int i = 0; i < CartListAdapter.this.getDataCount(); i++) {
            if (!CartListAdapter.this.getItemData(i).getType().equals("kol") && CartListAdapter.this.getItemData(i).getCart_status() == 1) {
                try {
                    total += CartListAdapter.this.getItemData(i).getNum() * Double.parseDouble(CartListAdapter.this.getItemData(i).getPrice());

                } catch (Exception ee) {
                    return 0;
                }
            }
        }
        return total;
    }

    public boolean isHaveSelect() {
        boolean isHave = false;
        for (int i = 0; i < CartListAdapter.this.getDataCount(); i++) {
            if (!CartListAdapter.this.getItemData(i).getType().equals("kol") && CartListAdapter.this.getItemData(i).getCart_status() == 1) {
                isHave = true;
                break;
            }
        }
        return isHave;
    }

    private RecyclerView recyclerView;

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    private void refreshItem(int position, int type) {
        if (recyclerView == null)
            return;
        int start = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int end = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

        if (position >= start && position <= end) {
            //获取指定位置view对象
            View view = recyclerView.getChildAt(position - start);
            if (type == payload_parent) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_kol);
                checkBox.setChecked(CartListAdapter.this.getItemData(position).isKol_select());
            } else {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_unit);
                checkBox.setChecked(CartListAdapter.this.getItemData(position).getCart_status() == 1);

            }

        } else
            CartListAdapter.this.notifyItemChanged(position);

    }

}
