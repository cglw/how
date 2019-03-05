package com.prettyyes.user.api.netXutils.Apis;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.NetWorkCallback;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.Apis
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public interface SuitApi extends BaseApi {
    public abstract void suitFavourite(String uuid,int suit_id,int is_like,String favourite_type, NetReqCallback netReqCallback);
    public abstract void suitSuitGet(String uuid,int suit_id, NetReqCallback netReqCallback);
    public abstract void suitFavouriteSuitList(String uuid,int page, NetReqCallback paramNetWorkCallback);
    public abstract void suitUserGetSuitListBySellerId(String uuid,int seller_id,int page, NetReqCallback netReqCallback);
    public abstract void suitSuittorderComment(String uuid,int page, NetWorkCallback paramNetWorkCallback);
    public abstract void suitSuitTaskAndComment(String suit_id,int page, NetWorkCallback paramNetWorkCallback);
    public abstract void answerListBySku(String uuid,String suit_id,int page,String type, NetReqCallback paramNetWorkCallback);
    public abstract void userGetSuitListBySellerId(String uuid,String seller_id,int page, NetReqCallback paramNetWorkCallback);

}
