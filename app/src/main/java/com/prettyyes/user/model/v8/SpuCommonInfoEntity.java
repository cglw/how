package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.CategoryModel;
import com.prettyyes.user.model.applocal.AttrName;

import java.util.List;

/**
 * Created by chengang on 2017/7/4.
 */

public class SpuCommonInfoEntity extends BaseModel {

    /**
     * spu_id : 0
     * module_id : 0
     * spu_type : string
     * spu_name : string
     * small_img : string
     * spu_price : string
     * spu_desc : string
     * share_status : 0
     * status : 0
     * spu_status : 0
     ***/
    private String spu_id;
    private String module_id;
    private String spu_type;
    private String spu_name;
    private String small_img;
    public String main_img;
    private String spu_price;
    private String spu_desc;
    private String share_status;
    private String status;
    private String spu_status;
    private String rubbish;
    private String bright_point;
    private String express_price;
    private String brand_id;
    private String brand_name;
    private String created_at;
    private String category_name;
    private SkuListEntity sku_list;
    private SellerInfoEntity seller_info;
    private boolean isSelect = false;
    private List<CategoryModel> category;
    private String is_like;
    private String wish_list;
    private List<RecommendGoodsEntity> recommend_goods;
    private String share_url;
    private ShareModel share_model;
    private List<AttrName> attrNames;
    private String attr_json_local;
    private int num = 1;
    private List<AttrParent> attr_json;
    private List<AttrListBean> attr_list;
    private List<AttrStr> attr_str_list;
    public String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public boolean isCommonShare() {
        return share_status == null ? false : share_status.equals("1");
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    private String sku_id;
    private boolean is_edit = false;

    public boolean is_edit() {
        return is_edit;
    }

    public void setIs_edit(boolean is_edit) {
        this.is_edit = is_edit;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getAttr_json_local() {
        return attr_json_local;
    }

    public void setAttr_json_local(String attr_json_local) {
        this.attr_json_local = attr_json_local;
    }

    public List<AttrParent> getAttr_json() {
        if (getSku_list() != null)
            return getSku_list().getAttr_json();
        return attr_json;
    }

    public void setAttr_json(List<AttrParent> attr_json) {
        this.attr_json = attr_json;
    }

    public List<AttrListBean> getAttr_list() {
        if (getSku_list() != null)
            return getSku_list().getAttr_list();
        return attr_list;
    }

    public void setAttr_list(List<AttrListBean> attr_list) {
        this.attr_list = attr_list;
    }

    public List<AttrStr> getAttr_str_list() {
        if (getSku_list() != null)
            return getSku_list().getAttr_str_list();
        return attr_str_list;
    }

    public void setAttr_str_list(List<AttrStr> attr_str_list) {
        this.attr_str_list = attr_str_list;
    }

    public String getMain_img() {
        return main_img;
    }


    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getSpu_type() {
        return spu_type;
    }

    public void setSpu_type(String spu_type) {
        this.spu_type = spu_type;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getSmall_img() {
        return small_img;
    }

    public void setSmall_img(String small_img) {
        this.small_img = small_img;
    }

    public String getSpu_price() {
        return spu_price;
    }

    public void setSpu_price(String spu_price) {
        this.spu_price = spu_price;
    }

    public String getSpu_desc() {
        return spu_desc;
    }

    public void setSpu_desc(String spu_desc) {
        this.spu_desc = spu_desc;
    }

    public String getShare_status() {
        return share_status;
    }

    public void setShare_status(String share_status) {
        this.share_status = share_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpu_status() {
        return spu_status;
    }

    public void setSpu_status(String spu_status) {
        this.spu_status = spu_status;
    }

    public String getRubbish() {
        return rubbish;
    }

    public void setRubbish(String rubbish) {
        this.rubbish = rubbish;
    }

    public String getBright_point() {
        return bright_point;
    }

    public void setBright_point(String bright_point) {
        this.bright_point = bright_point;
    }

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public SkuListEntity getSku_list() {
        return sku_list;
    }

    public void setSku_list(SkuListEntity sku_list) {
        this.sku_list = sku_list;
    }

    public SellerInfoEntity getSeller_info() {
        return seller_info;
    }

    public void setSeller_info(SellerInfoEntity seller_info) {
        this.seller_info = seller_info;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<CategoryModel> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryModel> category) {
        this.category = category;
    }

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }

    public String getWish_list() {
        return wish_list;
    }

    public void setWish_list(String wish_list) {
        this.wish_list = wish_list;
    }

    public List<RecommendGoodsEntity> getRecommend_goods() {
        return recommend_goods;
    }

    public void setRecommend_goods(List<RecommendGoodsEntity> recommend_goods) {
        this.recommend_goods = recommend_goods;
    }

    public String getShare_url() {
        return share_url;
    }

    public ShareModel getShare_model() {
        return share_model;
    }

    public void setShare_model(ShareModel share_model) {
        this.share_model = share_model;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public List<AttrName> getAttrNames() {
        return attrNames;
    }

    public void setAttrNames(List<AttrName> attrNames) {
        this.attrNames = attrNames;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
