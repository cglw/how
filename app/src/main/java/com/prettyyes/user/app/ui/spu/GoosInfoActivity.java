package com.prettyyes.user.app.ui.spu;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.api.netXutils.requests.AddCartRequest;
import com.prettyyes.user.api.netXutils.requests.SpuDetailRequest;
import com.prettyyes.user.api.netXutils.response.AddCartRes;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.fragments.RecommondFragment;
import com.prettyyes.user.app.ui.appview.AddWishSuccessDialog;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.app.view.pupopwindow.AttrSlectPupopWindow;
import com.prettyyes.user.core.DataChange;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.CartNumEvent;
import com.prettyyes.user.core.event.ChangeUnitNumEvent;
import com.prettyyes.user.core.order.OrderManager;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.applocal.AttrName;
import com.prettyyes.user.model.v8.AttrParent;
import com.prettyyes.user.model.v8.SpuInfoEntity;
import com.prettyyes.user.model.v8.UnitUpload;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;

/**
 * Created by chengang on 2017/7/2.
 */

public abstract class GoosInfoActivity extends BaseActivity implements NetReqCallback<SpuInfoEntity> {

    @ViewInject(R.id.btn_common_addcart)
    public View btn_addcart;
    @ViewInject(R.id.btn_common_buy)
    public View btn_buy;
    @ViewInject(R.id.tv_common_price)
    public TextView tv_total_price;
    @ViewInject(R.id.tv_buy)
    public TextView tv_buy;

    @ViewInject(R.id.ll_content)
    private LinearLayout ll_content;
    @ViewInject(R.id.tv_chat)
    private TextView tv_chat;


    @ViewInject(R.id.KolSimpleInfoView)
    public KolSimpleInfoView kolSimpleInfoView;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_content)
    private TextView tv_content;


    public SpuInfoEntity spuInfoEntity;
    //    BadgeView badgeView;
    private boolean isEdit = false;


    @Override
    protected void initViews() {
        View inflate = LayoutInflater.from(this).inflate(setLayout(), ll_content, true);
        x.view().inject(this, inflate);
        showRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
//                WishListActivity.goWishListActivity(getThis());
            }
        });
//        badgeView = new BadgeView(this);
//        badgeView.setTargetView(getImg_right());
        btn_buy.setFocusable(true);
        btn_buy.setFocusableInTouchMode(true);
        btn_buy.requestFocus();

    }

    public abstract int setLayout();

    @Override
    protected int bindLayout() {
        return R.layout.activity_goods_info;
    }

    @Override
    protected void loadData() {
        getSpuInfo();
    }

    private void getSpuInfo() {
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            String spu_type = intentParmas.getSpu_type();
            String module_id = intentParmas.getModule_id();
            if (intentParmas.getModule_id() != null) {
                new SpuDetailRequest().setSpu_type(spu_type).setModule_id(module_id).post(this);
            }
            else {
                SpuInfoEntity spuInfoEntity = (SpuInfoEntity) intentParmas.getSpuInfoEntity();
                if (spuInfoEntity != null) {
                    this.spuInfoEntity = spuInfoEntity;
                    this.spuInfoEntity.setIs_edit(true);
                    isEdit = true;
                    apiRequestSuccess(spuInfoEntity, "");
                }
                else {
                    showSnack("没有获取到商品信息");
                }
            }
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        tv_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chat();
            }
        });
        btn_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (JumpPageManager.getManager().checkUnlogin(getThis())) return;

                if (spuInfoEntity == null) {
                    showToastShort(R.string.empty_spu_detail);
                    return;
                }
                cart_status = "1";
                if (checkNoAttrPost()) return;
                AttrSlectPupopWindow attrSlectPupopWindow = new AttrSlectPupopWindow(getThis(), unitDetailBeanArrayList);
                attrSlectPupopWindow.show(getRootView());
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (JumpPageManager.getManager().checkUnlogin(getThis())) return;

                if (spuInfoEntity == null) {
                    showToastShort(R.string.empty_spu_detail);
                    return;
                }
                cart_status = "2";
                if (checkNoAttrPost()) return;
                AttrSlectPupopWindow attrSlectPupopWindow = new AttrSlectPupopWindow(getThis(), unitDetailBeanArrayList);
                attrSlectPupopWindow.show(getRootView());
            }
        });
//        mSubscription = AppRxBus.getInstance().toObservable(Object.class).subscribe(new RxAction1<Object>() {
//            @Override
//            public void callback(Object object) {
//                if (object instanceof ChangeUnitNumEvent)
//                    setTotal_price(((ChangeUnitNumEvent) object).getPrice() + "");
//                else if (object instanceof CartNumEvent) {
////                    badgeView.setBadgeCount(((CartNumEvent) object).getNum());
//                }
//            }
//        });
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ChangeUnitNumEvent>() {
            @Override
            protected void back(ChangeUnitNumEvent obj) {
                setTotal_price(((ChangeUnitNumEvent) obj).getPrice() + "");
            }
        }, new RxCallback<CartNumEvent>() {
            @Override
            protected void back(CartNumEvent obj) {

            }
        });


    }

    public void setTotal_price(String price) {
        if (price == null||price.equals(""))
            price = "0";
        tv_total_price.setText(String.format("合计: ¥%.2f", Float.parseFloat(price)));

    }

    public boolean checkNoAttrPost() {
        if (isEdit || spuInfoEntity.getModule_id().equals("0")) {
            showToastShort(R.string.error_do_nothing);
            return true;
        }
        if (checkNoAttr()) {
            post(sku_id, unit_detail, 1);
            return true;
        }

        return false;
    }

    public void post(String sku_id, String unit_detail, int num) {
        final String status = cart_status;
        showProgressDialog(R.string.post_api_ing);
        new AddCartRequest().setModule_id(spuInfoEntity.getModule_id())
                .setSku_id(sku_id)
                .setCart_status(status)
                .setSuit_unit(unit_detail)
                .setCart_type(spuInfoEntity.getSpu_type())
                .setNum(num)
                .post(new NetReqCallback<AddCartRes>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        showToastShort(message);
                        dismissProgressDialog();

                    }

                    @Override
                    public void apiRequestSuccess(AddCartRes addCartRes, String method) {
                        dismissProgressDialog();
                        if (status.equals("1")) {
                            OrderManager.getInstance().refreshWishListNum();
                            new AddWishSuccessDialog(getThis()).show();
                        } else {

                            JumpPageManager.getManager().goOrderCheckActivity(getThis(), 1);
                        }

                    }
                });
    }

    private boolean checkNoAttr() {


        boolean ishaveAttrname = false;
        //遍历item的数据源的属性名称,如果存在名称则为true
        for (int i = 0; i < unitDetailBeanArrayList.size(); i++) {
            List<AttrName> attrNames = unitDetailBeanArrayList.get(i).getAttrNames();
            if (attrNames != null && attrNames.size() > 0) {
                ishaveAttrname = true;
                break;
            }


        }
        //没有任何属性
        if (!ishaveAttrname) {

            if (unitDetailBeanArrayList.get(0) == null)
                return false;
            //取第一个的sku-id
            List<AttrParent> attr_json = unitDetailBeanArrayList.get(0).getAttr_json();
            if (attr_json != null && attr_json.size() > 0)
                sku_id = attr_json.get(0).getSku_id();

            if (spuInfoEntity.getSpu_type().equals(TYPE_SUIT)) {
                ArrayList<UnitUpload> datas = new ArrayList<>();
                for (int i = 0; i < spuInfoEntity.getUnit_detail().size(); i++) {
                    UnitUpload unitUpload = new UnitUpload();
                    unitUpload.setUnit_id(spuInfoEntity.getUnit_detail().get(i).getModule_id());
                    if (spuInfoEntity.getUnit_detail().get(i).getAttr_json() != null && spuInfoEntity.getUnit_detail().get(i).getAttr_json().size() > 0)
                        unitUpload.setSku_id(spuInfoEntity.getUnit_detail().get(i).getAttr_json().get(0).getSku_id());
                    unitUpload.setNum(1);
                    datas.add(unitUpload);
                }

                unit_detail = GsonHelper.getGson().toJson(datas);

            }
            return true;
        }


        return false;
    }

    private String sku_id;


    private ArrayList<SpuInfoEntity> unitDetailBeanArrayList = new ArrayList<>();

    @Override
    public void apiRequestFail(String message, String method) {
        showToastShort(message);
        loadFail();
    }

    @Override
    public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
        loadSuccess();
        this.spuInfoEntity = spuInfoEntity;
        setPageData(spuInfoEntity);
        setAttrs(spuInfoEntity);


    }

    private void setAttrs(SpuInfoEntity spuInfoEntity) {
        if(spuInfoEntity==null)
            return;
        if (isEdit)
            return;

        if ("0".equals(spuInfoEntity.getModule_id())) {
            return;
        }

        if (spuInfoEntity.getUnit_detail() == null || spuInfoEntity.getUnit_detail().size() <= 0) {
            spuInfoEntity.setAttrNames(DataChange.getAttrNames(spuInfoEntity));
            unitDetailBeanArrayList.add(spuInfoEntity);
            selectOnlyOneAttr(unitDetailBeanArrayList);

            return;
        }
        List<SpuInfoEntity> unit_detail = spuInfoEntity.getUnit_detail();
        for (int i = 0; i < unit_detail.size(); i++) {
            if (unit_detail.get(i).getAttrNames() == null) {
                unit_detail.get(i).setAttrNames(DataChange.getAttrNames(unit_detail.get(i)));

            }
        }
        unitDetailBeanArrayList.addAll(unit_detail);
        selectOnlyOneAttr(unitDetailBeanArrayList);

    }

    private void setPageData(SpuInfoEntity spuInfoEntity) {
        if (spuInfoEntity == null)
            return;

        if (spuInfoEntity.getSeller_info() != null)
            kolSimpleInfoView.setSellerInfo(spuInfoEntity.getSeller_info());
        tv_title.setText(spuInfoEntity.getSpu_name());
        tv_content.setText(spuInfoEntity.getSpu_desc());

        setTotal_price(spuInfoEntity.getSpu_price());
        OrderManager.getInstance().refreshWishListNum();
        if (spuInfoEntity.getUnit_detail() != null) {
            float total = 0;

            for (int i = 0; i < spuInfoEntity.getUnit_detail().size(); i++) {
                String spu_price = spuInfoEntity.getUnit_detail().get(i).getSpu_price();
                total += Float.parseFloat(spu_price);
            }
            setTotal_price(total + "");
        }


    }

    private void selectOnlyOneAttr(ArrayList<SpuInfoEntity> unitDetailBeanArrayList) {

        for (int i = 0; i < unitDetailBeanArrayList.size(); i++) {
            if (unitDetailBeanArrayList.get(i).getAttrNames() == null)
                continue;
            for (int j = 0; j < unitDetailBeanArrayList.get(i).getAttrNames().size(); j++) {
                unitDetailBeanArrayList.get(i).getAttrNames().get(j).setSlectOnlyOne();
            }
        }

    }

//    private void goCheckOrderActivity() {
//
//
//        showProgressDialog("正在拉取信息，请稍等");
//
//
//        new OrderApiImpl().orderCheckInfo(getThis().getUUId(), 1, new NetReqCallback<OrderCheck>() {
//            @Override
//            public void apiRequestFail(String message, String method) {
//                dismissProgressDialog();
//                showToastShort(message);
//
//            }
//
//            @Override
//            public void apiRequestSuccess(OrderCheck orderCheck, String method) {
//                dismissProgressDialog();
//                CheckOrderActivity.goCheckOrderActivity(getThis(), orderCheck, 2);
//            }
//        });
//    }

    public boolean getEitd_state() {
        if (spuInfoEntity == null)
            return false;

        if (spuInfoEntity.is_edit()) {
            showToastShort(R.string.error_do_nothing);
        }
        return spuInfoEntity.is_edit();
    }

    public void share() {
        if (spuInfoEntity == null)
            return;
        if (getEitd_state()) {
            return;
        }
        if (spuInfoEntity != null)
            new ShareHandler(getThis()).setRes(spuInfoEntity.getShare_model(), new ShareHandler.ShareCallback() {
                @Override
                public void share(boolean issuccess) {
                    ShareHandler.postShare(spuInfoEntity.getSpu_type(), spuInfoEntity.getModule_id() + "", spuInfoEntity.getSeller_info().getSeller_id() + "");

                }
            }).shareAtWindow(getRootView());
    }

    public void chat() {
        if (spuInfoEntity == null)
            return;
        if (isEdit || spuInfoEntity.getModule_id().equals("0")) {
            showToastShort(R.string.error_do_nothing);
            return;
        }

        if (spuInfoEntity != null) {
            DataCenter.CURRENT_ChatFromType = DataCenter.ChatFromType.SKU;
            if (spuInfoEntity.getSeller_info() != null)
                AccountDataRepo.getAccountManager().chatWithSeller(spuInfoEntity.getSeller_info().getSeller_id(), GsonHelper.getGson().toJson(spuInfoEntity));
        }
    }

    public void setRecommend(SpuInfoEntity spuInfoEntity) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.framelayout_recommend, RecommondFragment.newInstance(spuInfoEntity))
                .commit();
    }

    private String cart_status;
    private String unit_detail;

}
