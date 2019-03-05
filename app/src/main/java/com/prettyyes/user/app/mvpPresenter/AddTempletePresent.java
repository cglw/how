package com.prettyyes.user.app.mvpPresenter;

import android.content.DialogInterface;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.api.netXutils.requests.CommonRequest;
import com.prettyyes.user.api.netXutils.requests.SpuDetailRequest;
import com.prettyyes.user.api.netXutils.response.AddTempleteRes;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpView.AddTemplateView;
import com.prettyyes.user.app.ui.appview.EditDialog;
import com.prettyyes.user.app.view.dialog.MyBottomDialog;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.CategoryModel;
import com.prettyyes.user.model.applocal.AttrName;
import com.prettyyes.user.model.applocal.AttrVaule;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.model.v8.AttrListBean;
import com.prettyyes.user.model.v8.SellerInfoEntity;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.prettyyes.user.app.account.Constant.TYPE_BRAND;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;
import static com.prettyyes.user.core.containter.JumpPageManager.TYPE_HTML;
import static com.prettyyes.user.core.utils.AppUtil.showToastShort;

/**
 * Created by chengang on 2017/12/5.
 */

public class AddTempletePresent<T extends CommonRequest> {
    private AddTemplateView addTemplateView;
    private BaseActivity activity;
    public ArrayList<CategoryModel> categoryModelList = new ArrayList<>();
    public List<AttrName> attrNames = new ArrayList<>();//属性名称列表，本地显示
    public String attr_json = "";
    public T commonRequest;
    public SpuDetailRequest spuDetailRequest;
    public SpuInfoEntity spuInfoEntity = new SpuInfoEntity();


    public void setCategoryModelList(ArrayList<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }


    public SpuInfoEntity getSpuInfoEntity() {

        if (spuInfoEntity == null)
            return new SpuInfoEntity();
        return spuInfoEntity;
    }


    public AddTempletePresent(AddTemplateView addTemplateView) {
        this.addTemplateView = addTemplateView;
        this.activity = (BaseActivity) addTemplateView;
        spuDetailRequest = new SpuDetailRequest();
    }


    public void loadData() {
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(activity);
        if (intentParmas != null) {
            getSpuDetail(intentParmas.getModule_id());
        } else {
            getSpuDetail("0");
        }

    }

    public void getSpuDetail(String module_id) {
        spuDetailRequest.setSpu_type(addTemplateView.getSpuType()).setModule_id(module_id).post(new NetReqCallback<SpuInfoEntity>() {
            @Override
            public void apiRequestFail(String message, String method) {
                activity.loadFail();
            }

            @Override
            public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
                activity.loadSuccess();
                AddTempletePresent.this.spuInfoEntity = spuInfoEntity;

                if (spuInfoEntity != null)
                    setPagedata(spuInfoEntity);
            }
        });
    }

    public void setPagedata(SpuInfoEntity data) {

        addTemplateView.setGoodsName(data.getSpu_name());
        addTemplateView.setGoodsFreight(data.getExpress_price());
        addTemplateView.setGoodsPrice(data.getSpu_price());
        addTemplateView.setCategory(data.getCategory());
        addTemplateView.setImages(StringUtils.getSplitArray(data.getSmall_img()));

        addTemplateView.setDesc(data.getSpu_desc());

        addTemplateView.setStock(data.getStatus());
        if (data.getSku_list() != null && data.getAttr_json() != null && data.getAttr_list() != null && data.getAttr_list().size() > 0) {
            String attr_json = GsonHelper.getGson().toJson(spuInfoEntity.getSku_list().getAttr_json());

            ArrayList<AttrName> attrnames = new ArrayList<>();
            List<AttrListBean> attr_list = spuInfoEntity.getSku_list().getAttr_list();
            for (int i = 0; i < attr_list.size(); i++) {
                AttrName attrname = new AttrName();
                attrname.setName(attr_list.get(i).getAttr_name());
                attrname.setAttr_id(attr_list.get(i).getAttr_id());
                LinkedHashMap<String, AttrVaule> stringAttrVauleHashMap = attr_list.get(i).getValues();
                List<AttrVaule> attrVaules = new ArrayList<>();
                for (String key : stringAttrVauleHashMap.keySet()) {
                    AttrVaule attrVaule = stringAttrVauleHashMap.get(key);
                    attrVaules.add(attrVaule);
                }
                attrname.setValues(attrVaules);
                attrnames.add(attrname);
            }
            setAttr(attr_json, attrnames);
        } else {
            if (spuInfoEntity.getAttrNames() != null && spuInfoEntity.getAttr_json_local() != null) {
                setAttr(spuInfoEntity.getAttr_json_local(), spuInfoEntity.getAttrNames());
            }
        }
        try {
            refresgSpu();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String getAttrNameString(List<AttrName> attrNames) {
        String res = "";
        for (int i = 0; i < attrNames.size(); i++) {
            res += attrNames.get(i).getName();
            for (int j = 0; j < attrNames.get(i).getValues().size(); j++) {
                if (attrNames.get(i).isSelected())
                    res += attrNames.get(i).getValues().get(j).getAttr_value();
            }
        }
        return res;
    }

    public void setAttr(String attr_json, List<AttrName> attrNames) {
        if (getAttrNameString(attrNames).equals(getAttrNameString(this.attrNames))) {
            //没有修改就不去重新设置attrNames
            return;
        }
        this.attr_json = attr_json;
        this.attrNames = attrNames;
        String res = "";
        for (int i = 0; i < attrNames.size(); i++) {
            if (attrNames.get(i).isSelected()) {
                if (i < attrNames.size() - 1)
                    res += attrNames.get(i).getName() + "/";
                else
                    res += attrNames.get(i).getName();
            }

        }
        if (res.endsWith("/"))
            res = res.substring(0, res.length() - 1);
        addTemplateView.setSize(res);
    }

    private void postData() {
        try {
            refresgSpu();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.showToastShort(e.getMessage());
            return;
        }
        if (main_img == null || smalll_img.length() <= 0) {
            AppUtil.showToastShort("封面图不可以为空");
            return;
        }
        activity.showProgressDialog("正在上传...");

        if (commonRequest != null) {
            commonRequest.setAttr_json(spuInfoEntity.getAttr_json_local());
            commonRequest.setSpu_name(spuInfoEntity.getSpu_name());
            commonRequest.setSpu_desc(spuInfoEntity.getSpu_desc());
            commonRequest.setSpu_price(spuInfoEntity.getSpu_price());
            commonRequest.setExpress_price(spuInfoEntity.getExpress_price());
            commonRequest.setModule_id(spuInfoEntity.getModule_id());

            if (addTemplateView.getSpuType().equals(TYPE_BRAND))
                commonRequest.setShare_status("1");
            else
                commonRequest.setShare_status("0");

            commonRequest.setSpu_status(spuInfoEntity.getStatus());
            commonRequest.setSmall_img(spuInfoEntity.getSmall_img());
            commonRequest.setMain_img(spuInfoEntity.getMain_img());
            String ids = "";
            for (int i = 0; i < categoryModelList.size(); i++) {
                ids += categoryModelList.get(i).getCat_id() + ";";
            }
            commonRequest.setCategory_ids(ids);
        }


        commonRequest.post(new NetReqCallback<AddTempleteRes>() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);
                activity.dismissProgressDialog();

            }

            @Override
            public void apiRequestSuccess(AddTempleteRes t, String method) {

                showToastShort(R.string.upload_success);
                AppRxBus.getInstance().post(new AddTemplateOrSelectSuccessEvent(t.getModule_id(), t.getModule_type()));

                if (t.isCompletetNewBie())
                    AppRxBus.getInstance().post(new TaskCompleteEvent());
                if (spuInfoEntity != null && ZBundleCore.getInstance().isExistKolReplay()) {
                    spuInfoEntity.setModule_id(t.getModule_id());
                    spuInfoEntity.setSpu_type(t.getModule_type());
                    JumpPageManager.getManager().goKolReplyActivity(activity);

                }


                activity.dismissProgressDialog();
                activity.finish();
            }
        });
    }
//    }


    public void closePage() {

        if (spuInfoEntity != null && spuInfoEntity.getModule_id() != null && !spuInfoEntity.getModule_id().equals("0")) {

            if (checkNeedSave()) {
                DialogHelper.getInstance().getDialogNoCancel(activity.getString(R.string.template_edit_giveup), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                }).setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(true).show();
            } else {
                activity.finish();
            }

        } else {
            if (checkNeedSave()) {
                DialogHelper.getInstance().getDialogNoCancel(activity.getString(R.string.template_need_save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        save();
                    }
                }).setNegativeButton(activity.getString(R.string.template_back), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                }).setCancelable(true).show();
            } else {
                activity.finish();
            }
        }
    }

    public void save() {

        try {
            refresgSpu();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.showToastShort(e.getMessage());
            return;
        }
        spuDetailRequest.saveData(spuInfoEntity);
        activity.finish();
    }


    //检查是否保存
    private boolean checkNeedSave() {

        if (!StringUtils.strIsEmpty(addTemplateView.getGoodsName())) {
            return true;
        }
        if (!StringUtils.strIsEmpty(addTemplateView.getDesc())) {
            return true;
        }
        if (!StringUtils.strIsEmpty(addTemplateView.getGoodsPrice())) {
            return true;
        }
        if (!StringUtils.strIsEmpty(addTemplateView.getGoodsFreight())) {
            return true;
        }
        if (addTemplateView.getCategory() != null && addTemplateView.getCategory().size() > 0) {
            return true;
        }
        if ((attrNames != null && attrNames.size() > 0) && !StringUtils.strIsEmpty(attr_json)) {
            return true;
        }

        try {
            refreshImg();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.showToastShort(e.getMessage());
            return false;
        }
        if (main_img != null && main_img.length() > 0) {
            return true;
        }
        return false;
    }

    public void selectStock() {
        final MyBottomDialog myBottomDialog = new MyBottomDialog(activity);
        myBottomDialog.show();
        List<String> objects = new ArrayList<>();
        objects.add("在售");
        objects.add("售罄");
        objects.add("预定");
        myBottomDialog.getBottomDialogAdapter().addAll(objects);

        myBottomDialog.getBottomDialogAdapter().setMyOnItemClickListener(new AbsRecycleAdapter.OnMyItemClickListener<String>() {
            @Override
            public void clickItem(AbsRecycleViewHolder holder, View view, String o, int position) {
                myBottomDialog.dismiss();
                addTemplateView.setStock((position + 1) + "");
            }
        });
    }

    public void selectCategory() {
        JumpPageManager.getManager().goCategoryActivity(activity, categoryModelList);
    }

    public void lookdesc() {
        if (addTemplateView.getSpuType().equals(TYPE_TAOBAO)) {
            JumpPageManager.getManager().goWebActivity(activity, TYPE_HTML, addTemplateView.getDesc());
        } else {
            new EditDialog(activity, new EditDialog.DialogCallback() {
                @Override
                public void confirm(String text) {
                    addTemplateView.setDesc(text);
                }
            }, addTemplateView.getDesc()).show();
        }

    }

    public void selectSize() {
        JumpPageManager.getManager().goAttrsActivity(activity, attrNames);
    }

    public void postTemplete() {

        postData();


    }

    private void refreshImg() throws Exception {
        main_img = addTemplateView.getMainImage();
        smalll_img = addTemplateView.getImage();
    }

    private String main_img;
    private String smalll_img;


    private static final String TAG = "AddTempletePresent";


    public SpuInfoEntity refresgSpu() throws Exception {

        refreshImg();
        spuInfoEntity = getSpuInfoEntity();
        spuInfoEntity.setSpu_name(addTemplateView.getGoodsName());
        spuInfoEntity.setSpu_desc(addTemplateView.getDesc());
        spuInfoEntity.setSpu_type(addTemplateView.getSpuType());
        spuInfoEntity.setSpu_price(addTemplateView.getGoodsPrice());
        spuInfoEntity.setExpress_price(addTemplateView.getGoodsFreight());
        spuInfoEntity.setSmall_img(smalll_img);
        spuInfoEntity.setMain_img(main_img);
        spuInfoEntity.setStatus(addTemplateView.getStockState());
        spuInfoEntity.setCategory(addTemplateView.getCategory());
        if (spuInfoEntity.getSeller_info() == null) {
            SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
            UserInfo userInfo = activity.getAccount();
            if (userInfo != null) {
                sellerInfoEntity.setNickname(userInfo.getNickname());
                if (userInfo.getUid() != null)
                    sellerInfoEntity.setSeller_id(String.valueOf(Integer.parseInt(userInfo.getUid())));
                sellerInfoEntity.setFamous_type(userInfo.getFamous_type() + "");
                sellerInfoEntity.setHeadimg(userInfo.getHeadimg());
                sellerInfoEntity.setAce_txt(userInfo.getAce_txt());
                spuInfoEntity.setSeller_info(sellerInfoEntity);
            }
        }
        if (attr_json != null)
            spuInfoEntity.setAttr_json_local(attr_json);
        if (attrNames != null)
            spuInfoEntity.setAttrNames(attrNames);
        return spuInfoEntity;
    }

    public void preview() {
        try {
            refresgSpu();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.showToastShort(e.getMessage());
            return;
        }
        JumpPageManager.getManager().goSkuActivity(activity, getSpuInfoEntity());
    }
}

