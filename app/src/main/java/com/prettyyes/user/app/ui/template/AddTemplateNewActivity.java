package com.prettyyes.user.app.ui.template;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.CommonRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.AddTempletePresent;
import com.prettyyes.user.app.mvpView.AddTemplateView;
import com.prettyyes.user.app.ui.appview.SelectMediaView;
import com.prettyyes.user.app.view.PriceAndfreightDialog;
import com.prettyyes.user.app.view.app.AppWebView;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AttrSelectEvent;
import com.prettyyes.user.core.event.CategorySelectEvent;
import com.prettyyes.user.core.event.PriceEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;
import com.prettyyes.user.core.utils.ViewUtils;
import com.prettyyes.user.model.CategoryModel;
import com.prettyyes.user.model.applocal.AttrName;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


public abstract class AddTemplateNewActivity<T extends CommonRequest> extends BaseActivity implements AddTemplateView, View.OnClickListener, View.OnTouchListener {


    @ViewInject(R.id.tv_category)
    public TextView tv_category;
    @ViewInject(R.id.tv_size_content)
    public TextView tv_size_content;
    @ViewInject(R.id.tv_freight)
    public TextView tv_freight;
    @ViewInject(R.id.tv_price)
    public TextView tv_price;
    @ViewInject(R.id.edit_title)
    EditText edit_title;
    @ViewInject(R.id.tv_category_list)
    TextView tv_category_list;
    @ViewInject(R.id.tv_stock)
    TextView tv_stock;
    @ViewInject(R.id.photoSelectView)
    SelectMediaView photoSelectView;
    @ViewInject(R.id.rel_size)
    View view_size;
    @ViewInject(R.id.rel_price_freight)
    View view_price_freight;
    @ViewInject(R.id.rel_category)
    View view_category;

    @ViewInject(R.id.view_stock)
    View view_stock;
    @ViewInject(R.id.ll_photo)
    LinearLayout ll_photo;
    @ViewInject(R.id.ll_price)
    LinearLayout ll_price;
    @ViewInject(R.id.ll_freight)
    LinearLayout ll_freight;

    @ViewInject(R.id.btn_post_template)
    Button btn_post_template;
    @ViewInject(R.id.rel_root)
    View rel_root;
    @ViewInject(R.id.edit_desc)
    EditText edit_desc;

    @ViewInject(R.id.webview)
    public AppWebView webview;


    public AddTempletePresent<T> addTempletePresent;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(true);
    }

    @Override
    protected void initViews() {
        setActivtytitle("添加商品");
        addTempletePresent = new AddTempletePresent<>(this);
        addTempletePresent.commonRequest = setRequest();
        photoSelectView.setOnSelectImage(true);
        photoSelectView.setTopShowViewPorxyRoot(rel_root);

    }

    @Override
    public SelectMediaView getPhotoSelectView() {
        return photoSelectView;
    }

    public abstract T setRequest();

    @Override
    protected int bindLayout() {
        return R.layout.layout_template_com_new;
    }

//    @Override
//    public void setSoftModel() {
//
//    }

    @Override
    protected void setListener() {
        super.setListener();
        ll_price.setOnClickListener(this);
        ll_freight.setOnClickListener(this);
        view_size.setOnClickListener(this);
        view_category.setOnClickListener(this);
        view_stock.setOnClickListener(this);
        btn_post_template.setOnClickListener(this);
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<CategorySelectEvent>() {
            @Override
            protected void back(CategorySelectEvent o) {
                List<CategoryModel> categoryModelList = ((CategorySelectEvent) o).getCategoryModelList();
                addTempletePresent.setCategoryModelList((ArrayList<CategoryModel>) categoryModelList);
                setCategory(categoryModelList);
            }
        }, new RxCallback<PriceEvent>() {
            @Override
            protected void back(PriceEvent o) {
                setGoodsFreight(o.getFreight_price());
                setGoodsPrice(o.getGoods_price());
            }
        }, new RxCallback<AttrSelectEvent>() {
            @Override
            protected void back(AttrSelectEvent o) {
                List<AttrName> attrNames = ((AttrSelectEvent) o).getAttrNames();
                addTempletePresent.setAttr(((AttrSelectEvent) o).getAttr_json(), attrNames);
            }
        });

        setRightTvListener("预览", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    photoSelectView.check();
                    addTempletePresent.preview();
                } catch (Exception e) {
                    e.printStackTrace();
                    AppUtil.showToastShort(e.getMessage());
                }
            }
        });
        edit_desc.setOnTouchListener(this);
    }

    @Override
    protected void loadData() {
        addTempletePresent.loadData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_price:
                requestRootFouse();
                new PriceAndfreightDialog(getThis()).setEdit_freight(getGoodsFreight()).setEdit_price(getGoodsPrice()).show(getRootView());
                break;
            case R.id.ll_freight:
                requestRootFouse();
                new PriceAndfreightDialog(getThis()).setEdit_freight(getGoodsFreight()).setEdit_price(getGoodsPrice()).setIsfromFright(true).show(getRootView());
                break;
            case R.id.view_stock:
                addTempletePresent.selectStock();
                break;
            case R.id.btn_post_template:
                addTempletePresent.postTemplete();
                break;
            case R.id.rel_size:
                addTempletePresent.selectSize();
                break;
            case R.id.rel_category:
                addTempletePresent.selectCategory();
                break;

        }
    }

    public void requestRootFouse() {
        getRootView().setFocusable(true);//获取焦点，因为Scrollview的原因
        getRootView().setFocusableInTouchMode(true);
        getRootView().requestFocus();
    }

    @Override
    public void setStock(String status) {
        if (status == null)
            status = "1";
        if (status.equals("1")) {
            tv_stock.setText("在售");
        } else if (status.equals("3")) {
            tv_stock.setText("预定");
        } else {
            tv_stock.setText("售罄");
        }
    }

    @Override
    public String getStockState() {
        String state = "1";
        String s = tv_stock.getText().toString();
        if (s.equals("在售"))
            state = "1";
        else if (s.equals("预定"))
            state = "3";
        else
            state = "2";
        return state;

    }

    @Override
    public String getDesc() {
        return edit_desc.getText().toString();
    }

    @Override
    public void setDesc(String desc) {
        edit_desc.setText(desc);
        if (webview.getVisibility() == View.VISIBLE)
            webview.loadContent(desc);
    }

    @Override
    public void setGoodsName(String name) {
        edit_title.setText(name);
        if (edit_title.getText().toString() != null)
            edit_title.setSelection(edit_title.getText().toString().length());

    }

    @Override
    public String getGoodsName() {
        return edit_title.getText().toString();
    }

    @Override
    public void setGoodsPrice(String price) {
        tv_price.setText(price);
    }

    @Override
    public String getGoodsPrice() {
        return tv_price.getText().toString();
    }

    @Override
    public void setGoodsFreight(String price) {
        tv_freight.setText(price);
    }

    @Override
    public String getGoodsFreight() {
        return tv_freight.getText().toString();
    }


    @Override
    public void setCategory(List<CategoryModel> categoryModelList) {
        String category = "";
        for (int i = 0; i < categoryModelList.size(); i++) {
            category += categoryModelList.get(i).getCat_name();
            if (i < categoryModelList.size() - 1)
                category += ";";
        }
        tv_category_list.setText(category);
    }

    @Override
    public List<CategoryModel> getCategory() {
        return addTempletePresent.categoryModelList;
    }

    @Override
    public String getSpuType() {
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null)
            return intentParmas.getSpu_type();
        return "";
    }

    @Override
    public void setSize(String size) {
        tv_size_content.setText(size);
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    public String getMainImage() throws Exception {

        return photoSelectView.getMain();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoSelectView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String getImage() throws Exception {
        return photoSelectView.getImages();
    }

    @Override
    public void setImages(List<String> imgs) {
        LogUtil.i(TAG, "setImages" + imgs);
        photoSelectView.setImages(imgs);
    }

    @Override
    public void back() {
        try {
            photoSelectView.check();
            addTempletePresent.closePage();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.showToastShort(e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoSelectView.releseResource();
        SoftKeyboardUtil.observeSoftKeyboardDestopry(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
        if (((v.getId() == R.id.edit_desc) && ViewUtils.canVerticalScroll(edit_desc))) {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if (event.getAction() == MotionEvent.ACTION_UP) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

}


