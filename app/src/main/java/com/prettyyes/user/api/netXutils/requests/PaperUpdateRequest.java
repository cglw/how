package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.response.AddTempleteRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/6/29.
 */

public class PaperUpdateRequest extends CommonRequest<AddTempleteRes> {

    public static final String PAPER_DEFAULT_ATTRNAME = "内容不易，谢谢打赏";

    @Override
    public String setExtraUrl() {
        return "/app/paper/paperUpdate";
    }

//    @Override
//    public void post(final NetReqCallback<AddTempleteRes> callback) {
//        if (spu_price == null)
//            spu_price = "0";
//        double res = Float.parseFloat(spu_price);
//
//        if (spu_price == null || spu_price.length() <= 0 || res <= 0) {
//            spu_price = "5";super.post(callback);
//
//
////            new AttributeEditRequest().setAttr_name(PAPER_DEFAULT_ATTRNAME).post(new NetReqCallback<AttributeEditRes>() {
////                @Override
////                public void apiRequestFail(String message, String method) {
////
////
////                }
////
////                @Override
////                public void apiRequestSuccess(AttributeEditRes attributeEditRes, String method) {
////                    String attr_fmt = "[{\\\"attr\\\":[{\\\"attr_id\\\":\\\"%s\\\",\\\"attr_name\\\":\\\"内容不易，谢谢打赏\\\",\\\"attr_value\\\":\\\"5元\\\",\\\"parent_attr_id\\\":\\\"0\\\"}],\\\"sku_id\\\":\\\"\\\",\\\"sku_no\\\":\\\"\\\",\\\"sku_price\\\":\\\"\\\",\\\"sku_store\\\":\\\"\\\"}]";
////
////                    AttrParent attrParent = new AttrParent();
////                    Attr attr = new Attr();
////                    attr.setAttr_id(attributeEditRes.getAttr_id());
////                    attr.setAttr_value("5元");
////                    attr.setAttr_name(PAPER_DEFAULT_ATTRNAME);
////                    ArrayList<Attr> attr1 = new ArrayList<>();
////                    attr1.add(attr);
////                    attrParent.setAttr(attr1);
////                    ArrayList<AttrParent> data = new ArrayList<>();
////                    data.add(attrParent);
////                    if (attr_json == null || attr_json.length() <= 0)
////                        attr_json = GsonHelper.getGson().toJson(data);
//////                    setParams().put("spu_price", spu_price);
//////                    setParams().put("attr_json", GsonHelper.getGson().toJson(data));
////                    PaperUpdateRequest.super.post(callback);
////
////
////                }
////            });
////        } else
//
//
//
//    }
//

    @Override
    public HashMap<String, Object> setParams() {


        HashMap<String, Object> stringObjectHashMap = super.setParams();


        return stringObjectHashMap;

    }
}
