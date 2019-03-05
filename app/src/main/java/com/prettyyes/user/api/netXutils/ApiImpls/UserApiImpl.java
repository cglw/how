package com.prettyyes.user.api.netXutils.ApiImpls;

import com.google.gson.reflect.TypeToken;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.Apis.UserApi;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.api.netXutils.ProgressCallback;
import com.prettyyes.user.api.netXutils.RSAUtils;
import com.prettyyes.user.api.netXutils.urls.UserUrl;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.FirstModel;
import com.prettyyes.user.model.user.UserDeviceToken;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.model.user.UserInfolist;
import com.prettyyes.user.model.user.UserLikeseller;
import com.prettyyes.user.model.user.UserSellerIntroducefromuser;
import com.prettyyes.user.model.user.UserShareHot;
import com.prettyyes.user.model.user.UserShareSellFromUser;
import com.prettyyes.user.model.user.UserStyle;
import com.prettyyes.user.model.user.UserStyleInfo;
import com.prettyyes.user.model.user.UserStyleQuestion;
import com.prettyyes.user.model.user.UserTaskList;
import com.prettyyes.user.model.user.UserUpdateUserGender;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.ApiImpls
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class UserApiImpl implements UserApi {

    private static final String TAG = "UserApiImpl";

    /**
     * 获取用户自身提问接口
     *
     * @param uuid
     * @param page                 页数
     * @param paramNetWorkCallback
     */
    @Override
    public void userGetTaskList(String uuid, int page, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<UserTaskList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userTaskList, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 喜欢的人列表
     *
     * @param uuid
     * @param page
     * @param netReqCallback
     */

    @Override
    public void userLikeSeller(String uuid, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<UserLikeseller>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userLikeseller, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

    /**
     * 用户风格管理
     *
     * @param uuid                 用户uuid
     * @param height               身高
     * @param weight               体重
     * @param age                  年龄
     * @param body_type            体型
     * @param tags                 标签
     * @param like_color           喜欢的颜色
     * @param question             穿衣的疑惑
     * @param paramNetWorkCallback
     */
    @Override
    public void UserStyle(String uuid, int height, int weight, int age, String body_type, String tags, String like_color, String question, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("height", height);
        localHashMap.put("weight", weight);
        localHashMap.put("age", age);
        localHashMap.put("body_type", body_type);
        localHashMap.put("tags", tags);
        localHashMap.put("like_color", like_color);
        localHashMap.put("question", question);
        Type localType = new TypeToken<ApiResContent<UserStyle>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userUserstyle, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 用户穿衣的疑惑修改
     *
     * @param uuid
     * @param question             穿衣的疑惑
     * @param paramNetWorkCallback
     */
    @Override
    public void UserStyleQuestion(String uuid, String question, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("question", question);
        Type localType = new TypeToken<ApiResContent<UserStyleQuestion>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userUserStyleQuestion, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 用户风格
     *
     * @param uuid
     * @param paramNetWorkCallback
     */
    @Override
    public void UserStyleInfo(String uuid, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<UserStyleInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userUserStyleinfo, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }


    /**
     * 买家获取好手说明书
     *
     * @param uuid
     * @param saller_id
     * @param netReqCallback
     */

    @Override
    public void userSellerIntroducefromuser(String uuid, int saller_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("seller_id", saller_id);
        Type localType = new TypeToken<ApiResContent<UserSellerIntroducefromuser>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userSellerintroducefromuser, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }


    /**
     * 用户的uuid获取
     *
     * @param uuid
     * @param msg_device_token 设备的token
     * @param netReqCallback
     */
    @Override
    public void userInfo(String uuid, String msg_device_token, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("msg_device_token", msg_device_token);
        Type localType = new TypeToken<ApiResContent<UserInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userInfo, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

    /**
     * 买家编辑
     *
     * @param uuid
     * @param nickname       用户名
     * @param information    卖家介绍
     * @param tag            标签；分割
     * @param gender         性别 1男2女
     * @param headimg        头像
     * @param netReqCallback
     */
    @Override
    public void userEdit(String uuid, String nickname, String information, String tag, int gender, String headimg, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("nickname", nickname);
        localHashMap.put("information", information);
        localHashMap.put("tag", tag);
        localHashMap.put("gender", gender);
        localHashMap.put("headimg", headimg);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userEdit, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

    /**
     * 获取用户头像
     *
     * @param uuid
     * @param user_id        用户id,多个分号隔开
     * @param netReqCallback
     */
    @Override
    public void UserInfoList(String uuid, String user_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("user_id", user_id);
        Type localType = new TypeToken<ApiResContent<UserInfolist>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userUserinfolist, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

    /**
     * 更新设备的token
     *
     * @param uuid
     * @param deviceToken          用户设备token
     * @param paramNetWorkCallback
     */
    @Override
    public void UserDevideToken(String uuid, String deviceToken, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("deviceToken", deviceToken);
        Type localType = new TypeToken<ApiResContent<UserDeviceToken>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userDeviceToken, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 用户分享商家
     *
     * @param seller_id            商家id
     * @param link                 客户端链接
     * @param paramNetWorkCallback
     */
    @Override
    public void userShareSellerFromuser(int seller_id, String link, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("seller_id", seller_id);
        localHashMap.put("link", link);
        Type localType = new TypeToken<ApiResContent<UserShareSellFromUser>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userSharesellerfromuser, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 用户分享场景
     *
     * @param hot_id               场景id
     * @param link                 客户端链接
     * @param paramNetWorkCallback
     */
    @Override
    public void userShareHot(int hot_id, String link, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("hot_id", hot_id);
        localHashMap.put("link", link);
        Type localType = new TypeToken<ApiResContent<UserShareHot>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userSharehot, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 修改用户性别
     *
     * @param uuid
     * @param gender               性别，1男，2女，默认女
     * @param paramNetWorkCallback
     */
    @Override
    public void userUpdateUserGender(String uuid, int gender, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("gender", gender);
        Type localType = new TypeToken<ApiResContent<UserUpdateUserGender>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userUpdateUserGender, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 商家搜索
     *
     * @param nickname             昵称关键字
     * @param page                 页码
     * @param paramNetWorkCallback
     */
    @Override
    public void userSellerSearchList(String nickname, int page, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("nickname", nickname);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userSellerSearchList, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 注册
     *
     * @param username       用户名
     * @param telephone      手机号
     * @param password       密码
     * @param type           用户类型：1买家，2卖家
     * @param verify         手机验证码
     * @param slogan         优惠券
     * @param gender         性别 1 男 2 女  默认 女
     * @param netReqCallback
     */
    @Override
    public void userRegister(String username, String telephone, String password, String type, String verify, String slogan, int gender, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("username", username);
        localHashMap.put("telephone", telephone);
        localHashMap.put("password", password);
        localHashMap.put("type", type);
        localHashMap.put("verify", verify);
        localHashMap.put("slogan", slogan);
        localHashMap.put("gender", gender);
        localHashMap.put("source_uid", DataCenter.getSource_uid());

        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userRegist, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

    /**
     * 手机验证码
     *
     * @param telephone            (必填)手机号
     * @param template_type        (必填)注册类型 'reg'注册验证码
     * @param paramNetWorkCallback
     */
    @Override
    public void userTelephoneVerify(String telephone, String template_type, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("template_type", template_type);
        localHashMap.put("telephone", telephone);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userTelephoneVerify, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    @Override
    public void userTelephoneVerifyToEveryBody(String telephone, String template_type, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("template_type", template_type);
        localHashMap.put("telephone", telephone);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_usertelephoneVerifyToEveryBody, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }


    /**
     * 用户修改密码
     *
     * @param uuid
     * @param passwd               用户现在密码
     * @param passwd_now           用户修改密码
     * @param passwd_now_repeat    用户重复密码
     * @param paramNetWorkCallback
     */
    @Override
    public void userResetPwd(String uuid, String passwd, String passwd_now, String passwd_now_repeat, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("passwd", passwd);
        localHashMap.put("passwd_now", passwd_now);
        localHashMap.put("passwd_now_repeat", passwd_now_repeat);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userResetPwd, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 用户忘记密码的验证码
     *
     * @param telephone            手机号
     * @param paramNetWorkCallback
     */
    @Override
    public void userForget_getcode(String telephone, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("telephone", telephone);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userForget, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 用户忘记密码提交
     *
     * @param paramNetWorkCallback
     * @String telephone 手机号
     * @String verify 验证码
     * @String password 密码
     */
    @Override
    public void userForgetUpd(String telephone, String verify, String password, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("telephone", telephone);
        localHashMap.put("verify", verify);
        localHashMap.put("password", password);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userForgetUpd, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 用户登录
     *
     * @param username             用户名（手机号或微博微信账号）
     * @param password             密码
     * @param deviceToken          设备Token
     * @param paramNetWorkCallback
     */
    @Override
    public void userLogin(String username, String password, String deviceToken, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("username", username);
        localHashMap.put("password", password);
        localHashMap.put("deviceToken", deviceToken);
        Type localType = new TypeToken<ApiResContent<UserInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userLogin, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    @Override
    public void userLogin(String username, String password, String deviceToken, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("username", username);
        String enpwd = RSAUtils.encrpyByRsa(password);
        localHashMap.put("password", enpwd);
        localHashMap.put("deviceToken", SpMananger.getClien_id());
        localHashMap.put("source_uid", DataCenter.getSource_uid());
        Type localType = new TypeToken<ApiResContent<UserInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userLogin, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

    /**
     * @param uuid
     * @param part             headimg用户头像，task用户任务图片，scene场景，suit_cover套系封面图片，suit_unit套系单品图片
     * @param binary_img       url
     * @param progressCallback
     */
    @Override
    public void userUploadImg(String uuid, String part, String binary_img, ProgressCallback progressCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("part", part);
        localHashMap.put("binary_img", new File(binary_img));
        try {
            this.httpAccess.upLoadFileAsyn(UserUrl.Url_userUploadimg, localHashMap, binary_img, progressCallback);
        } catch (Exception localException) {
            progressCallback.onFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    @Override
    public void cancelUploadImg(String uuid, String part, String binary_img, ProgressCallback progressCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("part", part);
//        this.httpAccess.cancelUpLoad(UserUrl.Url_userUploadimg + uuid + part);
    }

    @Override
    public void userUploadImgs(String uuid, String part, ArrayList binary_img, ProgressCallback progressCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("part", part);
        try {
            this.httpAccess.upLoadFilesAsyn(UserUrl.Url_userUploadimg, localHashMap, binary_img, progressCallback);
        } catch (Exception localException) {
            progressCallback.onFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    @Override
    public void updateKolShareImg(String kol_share_img, String seller_id, String binary_img, ProgressCallback progressCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("kol_share_img", kol_share_img);
        localHashMap.put("seller_id", seller_id);
        localHashMap.put("binary_img", new File(binary_img));
        try {
            this.httpAccess.upLoadFileAsyn(UserUrl.Url_updateKolShareImg, localHashMap, binary_img, progressCallback);
        } catch (Exception localException) {
            progressCallback.onFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * @param nickname
     * @param page
     * @param netReqCallback
     */
    @Override
    public void useSellerSearchList(String nickname, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("nickname", nickname);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<ArrayList<SellerInfoEntity>>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userSearchSeller, localHashMap, localType, netReqCallback, false);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

    /**
     * 验证旧手机号码
     *
     * @param uuid
     * @param old_telephone
     * @param verify
     * @param netReqCallback
     */
    @Override
    public void usechangeTelephoneVerifyOldTelephone(String uuid, String old_telephone, String verify, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("old_telephone", old_telephone);
        localHashMap.put("verify", verify);
        Type localType = new TypeToken<ApiResContent<ArrayList<SellerInfoEntity>>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userchangeTelephoneVerifyOldTelephone, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

    /**
     * 更换手机号码
     *
     * @param uuid
     * @param new_telephone
     * @param new_password
     * @param new_verify
     * @param netReqCallback
     */
    @Override
    public void usechangeTelephone(String uuid, String new_telephone, String new_password, String new_verify, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("new_telephone", new_telephone);
        localHashMap.put("new_password", new_password);
        localHashMap.put("new_verify", new_verify);
        Type localType = new TypeToken<ApiResContent<ArrayList<SellerInfoEntity>>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userchangeTelephone, localHashMap, localType, netReqCallback, false);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

    @Override
    public void userFirstActivity(String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<FirstModel>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(UserUrl.Url_userFirstActivity, localHashMap, localType, netReqCallback, false);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(), "");
        }
    }

}
