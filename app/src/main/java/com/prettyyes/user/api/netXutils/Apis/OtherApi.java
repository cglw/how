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
public interface OtherApi extends BaseApi {
    public abstract void SceneGetHot(String uuid,int gender, NetReqCallback netReqCallback);
    public abstract void AceGetList(int page,int gender,String nickname, NetReqCallback netReqCallback);
    public abstract void AceGetSuitList(int page,String uuid,int uid, NetWorkCallback paramNetWorkCallback);
    public abstract void AceLike(String uuid,int ace_id,int is_like, NetReqCallback netReqCallback);
    public abstract void SceneFind(String scene_id_str, NetWorkCallback paramNetWorkCallback);
    public abstract void clientGetRegion(String uuid,int pid,int type, NetReqCallback netReqCallback);
    public abstract void clientSaveAddress(String uuid,int province_id,int city_id,int area_id,String province_name,String city_name,String area_name,String detail,String mobile,int is_default,String get_uname, NetReqCallback netReqCallback);
    public abstract void clientEditeAddress(String uuid,int a_id,int province_id,int city_id,int area_id,String province_name,String city_name,String area_name,String detail,String mobile,int is_default,String get_uname,int del, NetReqCallback netReqCallback);
    public abstract void clientGetAddress(String uuid,int is_default, NetReqCallback netReqCallback);
    public abstract void RecommendIndex(int page, NetWorkCallback paramNetWorkCallback);
    public abstract void WechatLogin(String openid,String access_token,String deviceToken, NetReqCallback netReqCallback);
    public abstract void WechatBind(String openid,String access_token,String deviceToken,String telephone,String password,String verify, NetReqCallback netReqCallback);
    public abstract void WeiboLogin(String uid,String access_token,String deviceToken,NetReqCallback netReqCallback);
    public abstract void WeiboBind(String uid,String access_token,String deviceToken,String telephone,String password,String verify,NetReqCallback netReqCallback);
    public abstract void getHotTags(NetReqCallback netReqCallback);
    public abstract void addTags(String tag_name, NetReqCallback netReqCallback);
    public abstract void TaobaoDel(String uuid,int taobao_id, NetReqCallback netReqCallback);

    public abstract void sellerShareIncrement(String seller_id,String uuid,String share_type,String type_id,NetReqCallback netReqCallback);

    public abstract void getActivityList(NetReqCallback netReqCallback);
    public abstract void getBannerList(NetReqCallback netReqCallback);
    public abstract void getNotityContentList(String uuid,int page,NetReqCallback netReqCallback);
    public abstract void getNotityCount(String uuid,NetReqCallback netReqCallback);
    public abstract void clearNotifyCount(String uuid,NetReqCallback netReqCallback);

}
