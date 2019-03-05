package com.prettyyes.user.api.netXutils.ApiImpls;

import com.google.gson.reflect.TypeToken;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.Apis.SuitApi;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.api.netXutils.urls.SuitUrl;
import com.prettyyes.user.model.Suit.AnswerListBySku;
import com.prettyyes.user.model.Suit.SuitAnswerList;
import com.prettyyes.user.model.Suit.SuitDetailEntity;
import com.prettyyes.user.model.Suit.SuitFavourite;
import com.prettyyes.user.model.Suit.SkuCollectEntity;
import com.prettyyes.user.model.Suit.SuitList;
import com.prettyyes.user.model.Suit.SuitOrderComment;
import com.prettyyes.user.model.Suit.SuitTaskAndComment;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.ApiImpls
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class SuitApiImpl implements SuitApi {
    /**
     * 收藏套系1 取消套系0
     *
     * @param uuid
     * @param  favourite_id
     * @param is_like 1 收藏 0 取消收藏
     * @param netReqCallback
     */
    @Override
    public void suitFavourite(String uuid, int favourite_id, int is_like,String favourite_type, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("favourite_id", favourite_id);
        localHashMap.put("is_like", is_like);
        localHashMap.put("favourite_type", favourite_type);
        Type localType = new TypeToken<ApiResContent<SuitFavourite>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SuitUrl.Url_suitFavourite, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"suitFavourite");
        }
    }
    /**
     * 套系详情
     *
     * @param uuid
     * @param suit_id 套系id
     * @param netReqCallback
     */
    @Override
    public void suitSuitGet(String uuid, int suit_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("suit_id", suit_id);
        Type localType = new TypeToken<ApiResContent<SuitDetailEntity>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SuitUrl.Url_suitGet, localHashMap, localType, netReqCallback,true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"suitSuitGet");
        }
    }

    /**
     * 套系列表
     *
     * @param uuid
     * @param page 页数
     * @param netReqCallback
     */
    @Override
    public void suitFavouriteSuitList(String uuid, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<SkuCollectEntity>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SuitUrl.Url_suitFavouriteList, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getLocalizedMessage(),"suitFavouriteSuitList");
        }
    }

    /**
     * 用户获取商家已经搭配好的套系列表
     *
     * @param uuid
     * @param seller_id 卖家id
     * @param page 页数
     * @param netReqCallback
     */
    @Override
    public void suitUserGetSuitListBySellerId(String uuid, int seller_id, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        localHashMap.put("seller_id", seller_id);
        Type localType = new TypeToken<ApiResContent<SuitList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SuitUrl.Url_suitUserGetSuitListBySellerId, localHashMap, localType, netReqCallback,true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"suitUserGetSuitListBySellerId");
        }
    }

    /**
     * 获取套系的订单评论
     *
     * @param uuid
     * @param page 页数
     * @param paramNetWorkCallback
     */
    @Override
    public void suitSuittorderComment(String uuid, int page, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<SuitOrderComment>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SuitUrl.Url_suitSuitordercomment, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 获取套系的订单评论与问答
     *
     * @param suit_id 套系id
     * @param page 页数
     * @param paramNetWorkCallback
     */
    @Override
    public void suitSuitTaskAndComment(String suit_id, int page, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("suit_id", suit_id);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<SuitTaskAndComment>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SuitUrl.Url_suitSuitTaskAndComment, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    @Override
    public void answerListBySku(String uuid, String suit_id, int page, String type, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("module_id", suit_id);
        localHashMap.put("page", page);
        localHashMap.put("type", type);
        Type localType = new TypeToken<ApiResContent<AnswerListBySku>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SuitUrl.Url_answerListBySku, localHashMap, localType, paramNetWorkCallback,true);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getMessage(),"answerListBySku");
        }
    }

    @Override
    public void userGetSuitListBySellerId(String uuid, String seller_id, int page, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("seller_id", seller_id);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<SuitAnswerList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SuitUrl.Url_userGetSuitListBySellerId, localHashMap, localType, paramNetWorkCallback,true);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getMessage(),"userGetSuitListBySellerId");
        }
    }
}
