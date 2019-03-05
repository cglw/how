package com.prettyyes.user.api.netXutils.Apis;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.api.netXutils.ProgressCallback;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.Apis
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public interface UserApi extends BaseApi{
    public abstract void userGetTaskList(String uuid,int page, NetWorkCallback paramNetWorkCallback);
    public abstract void userLikeSeller(String uuid,int page, NetReqCallback netReqCallback);
    public abstract void UserStyle(String uuid,int height,int weight,int age,String body_type,String tags,String like_color,String question, NetWorkCallback paramNetWorkCallback);
    public abstract void UserStyleQuestion(String uuid,String question, NetWorkCallback paramNetWorkCallback);
    public abstract void UserStyleInfo(String uuid, NetWorkCallback paramNetWorkCallback);
    public abstract void userSellerIntroducefromuser(String uuid,int saller_id, NetReqCallback netReqCallback);
    public abstract void userInfo(String uuid,String msg_device_token, NetReqCallback netReqCallback);
    public abstract void userEdit(String uuid,String nickname,String information,String tag,int gender,String headimg,  NetReqCallback netReqCallback);
    public abstract void UserInfoList(String uuid,String user_id, NetReqCallback netReqCallback);
    public abstract void UserDevideToken(String uuid, String deviceToken, NetWorkCallback paramNetWorkCallback);
    public abstract void userShareSellerFromuser(int seller_id, String link, NetWorkCallback paramNetWorkCallback);
    public abstract void userShareHot(int hot_id, String link, NetWorkCallback paramNetWorkCallback);
  //  public abstract void userResidencetime(int hot_id, String link, NetWorkCallback paramNetWorkCallback);
    public abstract void userUpdateUserGender(String uuid,int gender, NetWorkCallback paramNetWorkCallback);
    public abstract void userSellerSearchList(String nickname,int page, NetWorkCallback paramNetWorkCallback);
    public abstract void userRegister(String username,String telephone,String password,String type,String verify,String slogan,int gender, NetReqCallback netReqCallback);
    public abstract void userTelephoneVerify(String telephone,String template_type, NetWorkCallback paramNetWorkCallback);
    public abstract void userTelephoneVerifyToEveryBody(String telephone,String template_type, NetReqCallback netReqCallback);
    public abstract void userResetPwd(String uuid,String passwd,String passwd_now,String passwd_now_repeat, NetWorkCallback paramNetWorkCallback);
    public abstract void userForget_getcode(String telephone, NetWorkCallback paramNetWorkCallback);
    public abstract void userForgetUpd(String telephone,String verify,String password, NetWorkCallback paramNetWorkCallback);
    public abstract void userLogin(String username,String password,String deviceToken, NetWorkCallback paramNetWorkCallback);
    public abstract void userLogin(String username,String password,String deviceToken, NetReqCallback netReqCallback);
    public abstract void userUploadImg(String uuid,String part,String binary_img, ProgressCallback progressCallback);
    public abstract void cancelUploadImg(String uuid,String part,String binary_img, ProgressCallback progressCallback);
    public abstract void userUploadImgs(String uuid, String part, ArrayList binary_img, ProgressCallback progressCallback);
    public abstract void updateKolShareImg(String kol_share_img,String seller_id,String binary_img, ProgressCallback progressCallback);
    public abstract void useSellerSearchList(String nickname,int page, NetReqCallback netReqCallback);
    public abstract void usechangeTelephoneVerifyOldTelephone(String uuid,String old_telephone,String verify, NetReqCallback netReqCallback);
    public abstract void usechangeTelephone(String uuid,String new_telephone,String new_password,String new_verify, NetReqCallback netReqCallback);
    public abstract void userFirstActivity(String uuid, NetReqCallback netReqCallback);

}
