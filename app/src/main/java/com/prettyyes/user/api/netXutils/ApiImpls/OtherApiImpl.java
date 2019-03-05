package com.prettyyes.user.api.netXutils.ApiImpls;


import com.google.gson.reflect.TypeToken;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.Apis.OtherApi;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.api.netXutils.urls.OtherUrl;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.ActivityListModel;
import com.prettyyes.user.model.Banner;
import com.prettyyes.user.model.other.AceGetList;
import com.prettyyes.user.model.other.AceGetSuitList;
import com.prettyyes.user.model.other.ClientGetAddress;
import com.prettyyes.user.model.other.ClientRegion;
import com.prettyyes.user.model.other.NotifyContentList;
import com.prettyyes.user.model.other.NotifyCount;
import com.prettyyes.user.model.other.RecommendIndex;
import com.prettyyes.user.model.other.SceneFind;
import com.prettyyes.user.model.other.SceneGetHot;
import com.prettyyes.user.model.other.ShareAdd;
import com.prettyyes.user.model.other.TagAdd;
import com.prettyyes.user.model.other.TagHot;
import com.prettyyes.user.model.other.TaoBaoEntity;
import com.prettyyes.user.model.user.UserInfo;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/7/1.
 */
public class OtherApiImpl implements OtherApi {

    /**
     * 获取场景任务
     *
     * @param uuid
     * @param gender
     * @param netReqCallback
     */
    @Override
    public void SceneGetHot(String uuid, int gender, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("gender", gender);
        Type localType = new TypeToken<ApiResContent<SceneGetHot>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_sceneGethot, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "SceneGetHot");
        }
    }

    /**
     * 精选好手
     *
     * @param page
     * @param gender
     * @param netReqCallback
     */
    @Override
    public void AceGetList(int page, int gender, String nickname, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("page", page);
        localHashMap.put("gender", gender);
        localHashMap.put("nickname", nickname);
        Type localType = new TypeToken<ApiResContent<AceGetList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_aceGetlist, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "AceGetList");
        }
    }

    /**
     * 精选好手套系列表
     *
     * @param page
     * @param uuid
     * @param uid
     * @param paramNetWorkCallback
     */
    @Override
    public void AceGetSuitList(int page, String uuid, int uid, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("page", page);
        localHashMap.put("uid", uid);
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<AceGetSuitList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_aceGetSuitlist, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }


    /**
     * 添加删除喜欢的好手
     *
     * @param uuid
     * @param ace_id
     * @param is_like 0为取消 1为喜欢
     * @param
     */
    @Override
    public void AceLike(String uuid, int ace_id, int is_like, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("is_like", is_like);
        localHashMap.put("ace_id", ace_id);
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<AceGetSuitList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_aceLike, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "AceLike");
        }
    }


    /**
     * 查询场景
     *
     * @param scene_id_str
     * @param paramNetWorkCallback
     */
    @Override
    public void SceneFind(String scene_id_str, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("scene_id_str", scene_id_str);
        Type localType = new TypeToken<ApiResContent<SceneFind>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_scencFind, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 获取收获省市区
     *
     * @param uuid
     * @param pid            上一级id,默认为1
     * @param type           类型1省2市3区
     * @param netReqCallback
     */
    @Override
    public void clientGetRegion(String uuid, int pid, int type, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("pid", pid);
        localHashMap.put("type", type);
        Type localType = new TypeToken<ApiResContent<ClientRegion>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_clientGetRegion, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "clientGetRegion");
        }
    }

    /**
     * 保存收获地址 根据res判定
     * 同下
     *
     * @param uuid
     * @param province_id
     * @param city_id
     * @param area_id
     * @param province_name
     * @param city_name
     * @param area_name
     * @param detail
     * @param mobile
     * @param is_default
     * @param get_uname
     * @param netReqCallback
     */
    @Override
    public void clientSaveAddress(String uuid, int province_id, int city_id, int area_id, String province_name, String city_name, String area_name, String detail, String mobile, int is_default, String get_uname, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("province_id", province_id);
        localHashMap.put("city_id", city_id);
        localHashMap.put("area_id", area_id);
        localHashMap.put("province_name", province_name);
        localHashMap.put("city_name", city_name);
        localHashMap.put("area_name", area_name);
        localHashMap.put("detail", detail);
        localHashMap.put("mobile", mobile);
        localHashMap.put("is_default", is_default);
        localHashMap.put("get_uname", get_uname);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_clientSaveAddress, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "clientSaveAddress");
        }
    }

    /**
     * 编辑收货地址
     *
     * @param uuid
     * @param a_id           地址id
     * @param province_id    省id
     * @param city_id        市id
     * @param area_id        区id
     * @param province_name  省
     * @param city_name      市
     * @param area_name      区
     * @param detail         详细地址
     * @param mobile         手机号
     * @param is_default     1默认地址0不是
     * @param get_uname      收货人
     * @param del            0删除1整除
     * @param netReqCallback
     */
    @Override
    public void clientEditeAddress(String uuid, int a_id, int province_id, int city_id, int area_id, String province_name, String city_name, String area_name, String detail, String mobile, int is_default, String get_uname, int del, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("a_id", a_id);
        localHashMap.put("province_id", province_id);
        localHashMap.put("city_id", city_id);
        localHashMap.put("area_id", area_id);
        localHashMap.put("province_name", province_name);
        localHashMap.put("city_name", city_name);
        localHashMap.put("area_name", area_name);
        localHashMap.put("detail", detail);
        localHashMap.put("mobile", mobile);
        localHashMap.put("is_default", is_default);
        localHashMap.put("del", del);
        localHashMap.put("get_uname", get_uname);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_clientEditAddress, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "clientEditeAddress");
        }
    }

    /**
     * 获取收获地址
     *
     * @param uuid
     * @param is_default     是否获取默认地址 1默认 0否
     * @param netReqCallback
     */
    @Override
    public void clientGetAddress(String uuid, int is_default, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("is_default", is_default);
        Type localType = new TypeToken<ApiResContent<ClientGetAddress>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_clientGetAddress, localHashMap, localType, netReqCallback);
            return;
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "clientGetAddress");
        }
    }

    /**
     * 推荐
     *
     * @param page                 页数
     * @param paramNetWorkCallback
     */
    @Override
    public void RecommendIndex(int page, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<RecommendIndex>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_recommendIndex, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    private static final String TAG = "OtherApiImpl";

    /**
     * 微信登陆
     *
     * @param openid
     * @param access_token   token
     * @param netReqCallback
     */
    @Override
    public void WechatLogin(String openid, String access_token, String deviceToken, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("openid", openid);
        localHashMap.put("access_token", access_token);
        localHashMap.put("deviceToken", deviceToken);
        localHashMap.put("source_uid", DataCenter.getSource_uid());

        Type localType = new TypeToken<ApiResContent<UserInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_wechatAuth, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "WechatLogin");
        }
    }

    @Override
    public void WechatBind(String openid, String access_token, String deviceToken, String telephone, String password, String verify, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("openid", openid);
        localHashMap.put("access_token", access_token);
        localHashMap.put("deviceToken", deviceToken);
        localHashMap.put("telephone", telephone);
        localHashMap.put("password", password);
        localHashMap.put("verify", verify);
        localHashMap.put("source_uid", DataCenter.getSource_uid());
        Type localType = new TypeToken<ApiResContent<UserInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_wechatBind, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "WechatBind");
        }
    }

    /**
     * @param
     * @param access_token   微博用户accessToken
     * @param deviceToken    设备token
     * @param netReqCallback
     */
    @Override
    public void WeiboLogin(String uid, String access_token, String deviceToken, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", uid);
        localHashMap.put("access_token", access_token);
        localHashMap.put("deviceToken", deviceToken);
        localHashMap.put("source_uid", DataCenter.getSource_uid());
        Type localType = new TypeToken<ApiResContent<UserInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_weiboAuth, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "WeiboLogin");
        }
    }

    @Override
    public void WeiboBind(String uid, String access_token, String deviceToken, String telephone, String password, String verify, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", uid);
        localHashMap.put("access_token", access_token);
        localHashMap.put("deviceToken", deviceToken);
        localHashMap.put("telephone", telephone);
        localHashMap.put("password", password);
        localHashMap.put("verify", verify);
        localHashMap.put("source_uid", DataCenter.getSource_uid());

        Type localType = new TypeToken<ApiResContent<UserInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_weiboBind, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "WeiboBind");
        }
    }

    /**
     * @param netReqCallback
     */
    @Override
    public void getHotTags(NetReqCallback netReqCallback) {
        Type localType = new TypeToken<ApiResContent<TagHot>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_tagsHot, new HashMap<String, String>(), localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "getHotTags");
        }
    }

    /**
     * 添加tag
     *
     * @param tag_name
     * @param netReqCallback
     */

    @Override
    public void addTags(String tag_name, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("tag_name", tag_name);
        Type localType = new TypeToken<ApiResContent<TagAdd>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_tagsAdd, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "addTags");
        }
    }

    /**
     * @param uuid
     * @param taobao_id      淘宝id
     * @param netReqCallback
     */
    @Override
    public void TaobaoDel(String uuid, int taobao_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("taobao_id", taobao_id);
        Type localType = new TypeToken<ApiResContent<TaoBaoEntity>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_TaobaoDel, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "TaobaoDel");
        }
    }

    /**
     * 分享商家
     *
     * @param seller_id
     * @param netReqCallback
     */
    @Override
    public void sellerShareIncrement(String seller_id, String uuid, String share_type, String type_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("seller_id", seller_id);
        localHashMap.put("uuid", uuid);
        localHashMap.put("share_type", share_type);
        localHashMap.put("type_id", type_id);
        Type localType = new TypeToken<ApiResContent<ShareAdd>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_sellerShareIncrement, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "sellerShareIncrement");
        }
    }

    /**
     * 获取官方活动列表
     *
     * @param netReqCallback
     */
    @Override
    public void getActivityList(NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        Type localType = new TypeToken<ApiResContent<ActivityListModel>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_activityList, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "getActivityList");
        }
    }

    @Override
    public void getBannerList(NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        Type localType = new TypeToken<ApiResContent<Banner>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_banner, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "getBannerList");
        }
    }

    @Override
    public void getNotityContentList(String uuid, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<NotifyContentList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_noticeList, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "getNotityContentList");
        }
    }

    @Override
    public void getNotityCount(String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<NotifyCount>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_noticeCount, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "getNotityCount");
        }
    }

    @Override
    public void clearNotifyCount(String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(OtherUrl.Url_noticeRead, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "clearNotifyCount");
        }
    }
}
