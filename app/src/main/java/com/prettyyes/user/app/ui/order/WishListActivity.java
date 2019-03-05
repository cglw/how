package com.prettyyes.user.app.ui.order;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.adapter.CartListAdapter;
import com.prettyyes.user.app.adapter.detail.WishListVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.mvpPresenter.WishListPresenter;
import com.prettyyes.user.app.mvpView.WishListView;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.view.lvandgrid.EmptyListener;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ChangeCartStatusOrNumEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.order.CartItemModel;
import com.prettyyes.user.model.v8.CartInfoEntity;
import com.prettyyes.user.model.v8.CartListEntity;
import com.prettyyes.user.model.v8.CartListRes;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

public class WishListActivity extends BaseActivity implements WishListView, EmptyListener {

    //    @ViewInject(R.id.vp_wishlistAct_showmain)
//    private ClipViewPager vp_wishlist;
//    @ViewInject(R.id.rel_wishlishAct_container)
//    private RelativeLayout rel_wishlist;
    @ViewInject(R.id.tv_common_price)
    private TextView tv_price;
    @ViewInject(R.id.rel_common_buy)
    private RelativeLayout rel_buy;
    @ViewInject(R.id.swipeRecycle_cartlist)
    private SwipeRecycleView swipeRecycleView;
    private CartListAdapter cartListAdapter;

    private OrderApiImpl orderApi;


    public static void goWishListActivity(Context context) {
        if (TextUtils.isEmpty(BaseApplication.UUID)) {
            RegisterActivity.getRegister(context);
        } else {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).nextActivity(WishListActivity.class);
            } else {
                context.startActivity(new Intent(context, WishListActivity.class));
            }
        }

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_cart_list;
    }

    @Override
    protected void initViews() {
        setActivtytitle("购物车");
        orderApi = new OrderApiImpl();
        cartListAdapter = new CartListAdapter(this);
        cartListAdapter.setRecyclerView(swipeRecycleView.getRecycleView());
        swipeRecycleView.setAdapter(cartListAdapter);


    }

    @Override
    protected void loadData() {
        swipeRecycleView.loadPageData();
    }

    @Override
    public void setHeadimg(String url) {

    }

    @Override
    protected void setListener() {
        swipeRecycleView.setListener(new SwipeRecycleView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getData(page);
            }
        });
        swipeRecycleView.setEmpty_listener(this);
        rel_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cartListAdapter.isHaveSelect()) {
                    showFailedError("你需要选择商品");
                    return;
                }
                JumpPageManager.getManager().goOrderCheckActivity(getThis(),0);
//                orderApi.orderCheckInfo(getThis().getUUId(), 0, new NetReqCallback<OrderCheck>() {
//                    @Override
//                    public void apiRequestFail(String message, String method) {
//                        dismisProgreDialog();
//
//                        showFailedError(message);
//                    }
//
//                    @Override
//                    public void apiRequestSuccess(OrderCheck orderCheck, String method) {
//                        dismisProgreDialog();
//                        CheckOrderActivity.goCheckOrderActivity(getThis(), orderCheck, 0);
//                    }
//                });

            }
        });
//        mSubscription = AppRxBus.getInstance().toObservable(ChangeCartStatusOrNumEvent.class).subscribe(new RxAction1<ChangeCartStatusOrNumEvent>() {
//            @Override
//            public void callback(ChangeCartStatusOrNumEvent changeCartStatusOrNumEvent) {
//                tv_price.setText(String.format("合计：￥%.2f", cartListAdapter.getTotalPrice()));
//                if (cartListAdapter.getDataCount() < MIN_PAGE_SIZE)
//                    swipeRecycleView.loadingEnd();
//            }
//        });

        mSubscription= AppRxBus.getInstance().subscribeEvent(new RxCallback<ChangeCartStatusOrNumEvent>() {
            @Override
            protected void back(ChangeCartStatusOrNumEvent obj) {
                tv_price.setText(String.format("合计：￥%.2f", cartListAdapter.getTotalPrice()));
                if (cartListAdapter.getDataCount() < MIN_PAGE_SIZE)
                    swipeRecycleView.loadingEnd();
            }
        });
    }

    private void getData(final int page) {
        orderApi.cartList(getUUId(), page, new NetReqCallback<CartListRes>() {
            @Override
            public void apiRequestFail(String message, String method) {
                swipeRecycleView.loadingfail();
                if (page == 1)
                    loadFail();

            }

            @Override
            public void apiRequestSuccess(CartListRes cartListRes, String method) {
                handler(cartListRes);
                loadSuccess();
            }
        });
    }

    private void handler(CartListRes cartListRes) {
        swipeRecycleView.loadingSuccessHavedata();
        ArrayList<CartItemModel> datas = new ArrayList<>();
        List<CartListEntity> list = cartListRes.getList();
        for (int i = 0; i < list.size(); i++) {
            CartItemModel cartItemModel = new CartItemModel();
            CartListEntity cartList = list.get(i);
            cartItemModel.setSeller_id(cartList.getSeller_id());
            cartItemModel.setNickname(cartList.getSeller_name());
            cartItemModel.setHeadimg(cartList.getSeller_headimg());
            cartItemModel.setGrade_title(cartList.getGrade_title());
            cartItemModel.setFamous_type(cartList.getFamous_type() + "");
            cartItemModel.setType("kol");
            datas.add(cartItemModel);

            for (int j = 0; j < cartList.getCart_list().size(); j++) {
                CartInfoEntity cartInfo = cartList.getCart_list().get(j);

                CartItemModel child = new CartItemModel();
                child.setModule_id(cartInfo.getModule_id());
                child.setSeller_id(cartList.getSeller_id());
                child.setAttr_value_text(cartInfo.getAttr_value_text());
                child.setSku_type(cartInfo.getSpu_type());
                child.setUnit_id(cartInfo.getModule_id());
                child.setCart_id(cartInfo.getCart_id());
                child.setUnit_img(cartInfo.getMain_img());
                child.setUnit_name(cartInfo.getSku_name());
                child.setPrice(cartInfo.getSku_price());
                child.setNum(cartInfo.getNum());
                child.setCart_status(cartInfo.getCart_status());
                cartItemModel.getUnit_childs().add(new CartItemModel.Unit(cartInfo.getCart_id(), cartInfo.getCart_status()));
                datas.add(child);


            }
//
//            for (int k = 0; k < listEntity.getTaobao().size(); k++) {
//                List<WishListEntity.ListEntity.TaobaoEntity> taobao = listEntity.getTaobao();
//                CartItemModel child = new CartItemModel();
//
//                child.setSeller_id(listEntity.getSeller_id());
//                child.setUnit_id(taobao.get(k).getTaobao_id() + "");
//                child.setSku_type(TYPE_TAOBAO);
//                child.setSku_id(taobao.get(k).getTaobao_id());
//                child.setCart_id(taobao.get(k).getCart_id());
//                child.setUnit_img(taobao.get(k).getMain_image());
//                child.setUnit_name(taobao.get(k).getTitle());
//                child.setPrice(taobao.get(k).getPrice());
//                child.setNum(taobao.get(k).getNum());
//                child.setCart_status(taobao.get(k).getCart_status());
//                cartItemModel.getUnit_childs().add(new CartItemModel.Unit(taobao.get(k).getCart_id(), taobao.get(k).getCart_status()));
//                datas.add(child);
//
//            }

        }

        cartListAdapter.addAll(datas);
        AppRxBus.getInstance().post(new ChangeCartStatusOrNumEvent());
        swipeRecycleView.setLoadingEnd(true);
    }

//    private void handler(WishListEntity wishListEntity) {
//
//        swipeRv.loadingSuccessHavedata();
//        ArrayList<CartItemModel> datas = new ArrayList<>();
//        List<WishListEntity.ListEntity> list = wishListEntity.getList();
//        for (int i = 0; i < list.size(); i++) {
//            CartItemModel cartItemModel = new CartItemModel();
//            WishListEntity.ListEntity listEntity = list.get(i);
//            cartItemModel.setSeller_id(listEntity.getSeller_id());
//            cartItemModel.setNickname(listEntity.getSeller_name());
//            cartItemModel.setHeadimg(listEntity.getSeller_headimg());
//            cartItemModel.setType("kol");
//            datas.add(cartItemModel);
//
//            for (int j = 0; j < listEntity.getSuit().size(); j++) {
//                WishListEntity.ListEntity.SuitEntity suitEntity = listEntity.getSuit().get(j);
//
//                List<WishListEntity.ListEntity.SuitEntity.SuitUnitEntity> suit_unit = suitEntity.getSuit_unit();
//                for (int k = 0; k < suit_unit.size(); k++) {
//                    CartItemModel child = new CartItemModel();
//                    child.setSku_id(suitEntity.getSuit_id());
//                    child.setSeller_id(listEntity.getSeller_id());
//                    child.setSku_type(TYPE_SUIT);
//                    child.setUnit_id(suit_unit.get(k).getUnit_id() + "");
//                    child.setCart_id(suit_unit.get(k).getCart_id());
//                    child.setUnit_img(suit_unit.get(k).getImg());
//                    child.setUnit_name(suit_unit.get(k).getUnit_name());
//                    child.setPrice(suit_unit.get(k).getPrice());
//                    child.setNum(suit_unit.get(k).getNum());
//                    child.setCart_status(suit_unit.get(k).getCart_status());
//                    cartItemModel.getUnit_childs().add(new CartItemModel.Unit(suit_unit.get(k).getCart_id(), suit_unit.get(k).getCart_status()));
//                    datas.add(child);
//                }
//
//
//            }
//
//            for (int k = 0; k < listEntity.getTaobao().size(); k++) {
//                List<WishListEntity.ListEntity.TaobaoEntity> taobao = listEntity.getTaobao();
//                CartItemModel child = new CartItemModel();
//
//                child.setSeller_id(listEntity.getSeller_id());
//                child.setUnit_id(taobao.get(k).getTaobao_id() + "");
//                child.setSku_id(taobao.get(k).getTaobao_id());
//                child.setCart_id(taobao.get(k).getCart_id());
//                child.setUnit_img(taobao.get(k).getMain_image());
//                child.setUnit_name(taobao.get(k).getTitle());
//                child.setPrice(taobao.get(k).getPrice());
//                child.setNum(taobao.get(k).getNum());
//                child.setCart_status(taobao.get(k).getCart_status());
//                cartItemModel.getUnit_childs().add(new CartItemModel.Unit(taobao.get(k).getCart_id(), taobao.get(k).getCart_status()));
//                datas.add(child);
//
//            }
//
//        }
//
//        cartListAdapter.addAll(datas);
//        RxBus.getInstance().post(new ChangeCartStatusOrNumEvent());
//        swipeRv.setLoadingEnd(true);
//
//
//    }

    @Override
    public void setVpdata(ArrayList<WishListPresenter.WishVpEntity> vpdata) {
//        int index = vp_wishlist.getCurrentItem();
//        wishListVpAdapter.clear();
//        wishListVpAdapter.addAll(vpdata);
//        vp_wishlist.setOffscreenPageLimit(vpdata.size());
//        vp_wishlist.setAdapter(wishListVpAdapter);
//
//        if (index >= vpdata.size()) {
//            vp_wishlist.setCurrentItem(vpdata.size() - 1);
//        } else {
//            vp_wishlist.setCurrentItem(index);
//        }
    }

    @Override
    public WishListVpAdapter getAdapter() {
        return null;
    }

    private ProgressDialog progressDialog;

    @Override
    public void showProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    public void dismisProgreDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
        progressDialog = null;
    }


    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        inentFliter.addAction(Constant.OrderCreate);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
    }

    @Override
    public void setEmpty(LinearLayout ll, Button button) {

        button.setVisibility(View.VISIBLE);
        button.setText("去看看");
        ((TextView) ll.getChildAt(0)).setText("达洛维夫人说她自己去买花。");
        ((TextView) ll.getChildAt(1)).setText("——伍尔夫");
        ((TextView) ll.getChildAt(2)).setText("您的购物车还没有物品，当下中意的好物千万别犹豫到下一秒。");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.goMainActivity(getThis(), 0);
            }
        });

    }


}
