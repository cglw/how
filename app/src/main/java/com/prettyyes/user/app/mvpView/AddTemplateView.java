package com.prettyyes.user.app.mvpView;

import com.prettyyes.user.app.ui.appview.SelectMediaView;
import com.prettyyes.user.model.CategoryModel;

import java.util.List;

/**
 * Created by chengang on 2017/8/17.
 */

public interface AddTemplateView {

    void setStock(String status);

    String getStockState();

    String getDesc();

    void setDesc(String desc);

    void setGoodsName(String name);

    void setSize(String size);

    String getGoodsName();

    void setGoodsPrice(String price);

    String getGoodsPrice();

    void setGoodsFreight(String price);

    String getGoodsFreight();

    void setImages(List<String> imgs);

    String getImage() throws Exception;

    String getMainImage() throws Exception;

    void setCategory(List<CategoryModel> category);

    List<CategoryModel> getCategory();

    String getSpuType();

    SelectMediaView getPhotoSelectView();
}
