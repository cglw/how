package com.prettyyes.user.core;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.app.view.pupopwindow.SharePopupWindow;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.PermissionUtils;
import com.prettyyes.user.model.other.ShareAdd;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMWeb;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/8/24
 * Description: 分享的逻辑处理
 */
public class ShareHandler {
    private SharePopupWindow sharePopupWindow;
    private Activity activity;
    private String targetUrl = "";
    private String text = "";
    private String img = "";
    private String title = "";
    private String path = "";
    private String user_name = "";
    private ShareModel shareModel = null;
    private ShareCallback shareCallback;
    private static final String TAG = "ShareHandler";
    private static final String Default_Image = "https://image.prettyyes.com/home-share-image.png?imageView2/1/w/150/h/150/format/jpg";


    public static void postShare(String share_type, String type_id, String seller_id) {
        new OtherApiImpl().sellerShareIncrement(seller_id + "", BaseApplication.UUID, share_type, type_id + "", new NetReqCallback<ShareAdd>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(ShareAdd shareAdd, String method) {
                ShareAdd.isNeedShow(shareAdd);
            }

        });
    }

    public ShareHandler(Activity activity) {
        this.activity = activity;
    }


    public ShareHandler setRes(ShareModel share_model, ShareCallback shareCallback) {
        if (share_model == null)
            return this;
        this.shareModel = share_model;
        this.targetUrl = share_model.getTargetUrl();
        this.text = share_model.getShare_content();
        this.img = share_model.getImage();
        this.title = share_model.getTitle();
        this.shareCallback = shareCallback;
        if (share_model.getPath() != null && share_model.getPath().length() > 0)
            this.path = share_model.getPath();
        if (share_model.getUsername() != null && share_model.getUsername().length() > 0)
            this.user_name = share_model.getUsername();

        return this;

    }

    public void share_weibo() {
        if (TextUtils.isEmpty(img)) {
            img = Default_Image;
        }
        shareWeb(title, text, targetUrl, img, activity, SHARE_MEDIA.SINA);
    }

    public void share_minapp() {
        if (TextUtils.isEmpty(img)) {
            img = Default_Image;
        }
        shareMin(title, text, targetUrl, img, path, user_name, activity, SHARE_MEDIA.WEIXIN);
    }

    private void shareWeb(final String title, final String content, final String url, final String thumb_img, final Activity activity, final SHARE_MEDIA share_media) {

        PermissionUtils.requestSinglePermission(activity, new PermissionUtils.PermissionGrant() {
            @Override
            public void onPermissionGranted(int requestCode) {
                UMImage thumb = new UMImage(activity, thumb_img);
                UMWeb web = new UMWeb(url);
                web.setThumb(thumb);
                web.setDescription(content);
                web.setTitle(title);
                new ShareAction(activity).withMedia(web).setPlatform(share_media).setCallback(umShareListener).share();

            }

            @Override
            public void onPermissionDenied() {
                super.onPermissionDenied();
                AppUtil.showToastShort("请开启手机存储权限");
            }
        }, PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE);

    }

    public void shareImageWeixin_Circle() {
        shareImage(img, SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    private void shareImage(final String thumb_img, final SHARE_MEDIA share_media) {

        PermissionUtils.requestSinglePermission(activity, new PermissionUtils.PermissionGrant() {
            @Override
            public void onPermissionGranted(int requestCode) {
                UMImage thumb = new UMImage(activity, thumb_img);
                new ShareAction(activity).withMedia(thumb).setPlatform(share_media).setCallback(umShareListener).share();

            }

            @Override
            public void onPermissionDenied() {
                super.onPermissionDenied();
                AppUtil.showToastShort("请开启手机存储权限");
            }
        }, PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE);
    }

    private void shareMin(final String title, final String content, final String url, final String thumb_img, final String path, final String user_name, final Activity activity, final SHARE_MEDIA share_media) {

        PermissionUtils.requestSinglePermission(activity, new PermissionUtils.PermissionGrant() {
            @Override
            public void onPermissionGranted(int requestCode) {
                UMImage thumb = new UMImage(activity, thumb_img);
                UMMin min = new UMMin(url);
                min.setThumb(thumb);
                min.setPath(path);
                min.setUserName(user_name);
                min.setDescription(content);
                min.setTitle(title);
                LogUtil.e("share",min.getPath()+""+min.getUserName());
                new ShareAction(activity).withMedia(min).setPlatform(share_media).setCallback(umShareListener).share();

            }

            @Override
            public void onPermissionDenied() {
                super.onPermissionDenied();
                AppUtil.showToastShort("请开启手机存储权限");
            }
        }, PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE);

    }


    public void share_wechat() {
        if (TextUtils.isEmpty(img)) {
            img = Default_Image;
        }
        if (path != null && path.length() > 0 && user_name != null && user_name.length() > 0)
            shareMin(title, text, targetUrl, img, path, user_name, activity, SHARE_MEDIA.WEIXIN);
        else
            shareWeb(title, text, targetUrl, img, activity, SHARE_MEDIA.WEIXIN);
    }

    public void share_friends() {
        if (TextUtils.isEmpty(img)) {
            img = Default_Image;
        }
        if (shareModel != null && shareModel.TypeIsImg()) {
            shareImage(img, SHARE_MEDIA.WEIXIN_CIRCLE);
        } else
            shareWeb(title, text, targetUrl, img, activity, SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    public void share_qq() {
        if (TextUtils.isEmpty(img)) {
            img = Default_Image;
        }

        shareWeb(title, text, targetUrl, img, activity, SHARE_MEDIA.QQ);
    }

    public ShareHandler shareAtWindow(int id) {
        share();
        sharePopupWindow.showAtLocation(activity.findViewById(id), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        return this;
    }

    public ShareHandler shareAtWindow(View view) {
        share();
        sharePopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        return this;
    }


    private void share() {
        sharePopupWindow = new SharePopupWindow(activity, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePopupWindow.dismiss();

                switch (v.getId()) {
                    case R.id.img_popup_sina:
                        share_weibo();
                        break;
                    case R.id.img_popup_weixin:
                        share_wechat();
                        break;
                    case R.id.img_popup_weixin_friend:
                        share_friends();
                        break;
                    case R.id.img_popup_qq:
                        share_qq();
                        break;
                }
            }
        });
    }

    private String weibo_extral = "\n%s（想看更多？关注@How官方微博，下载 http://www.prettyyes.com)";

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            showToastShort(activity, " 分享成功啦");
            if (shareCallback != null) {
                shareCallback.share(true);
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            LogUtil.i(TAG,"error:"+t.getMessage());
            showToastShort(activity, " 分享失败啦");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            showToastShort(activity, " 分享取消了");
        }
    };

    private void showToastShort(Activity activity, String content) {
        Toast.makeText(activity, content, Toast.LENGTH_SHORT).show();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public interface ShareCallback {
        public void share(boolean issuccess);
    }


    public void sendMiniApps() {

        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
        //低版本微信打开 URL
        miniProgram.webpageUrl = "";
        //跳转的小程序的原始 ID
        miniProgram.userName = "wxa894112813044d58";
        //小程序的 Path
        miniProgram.path = "pages/index/index";

        WXMediaMessage msg = new WXMediaMessage(miniProgram);
        final String shareTitle = "test";
        if (!TextUtils.isEmpty(shareTitle)) {
            msg.title = title;
        }

        final String shareDescription = "desc";
        if (!TextUtils.isEmpty(shareDescription)) {
            msg.description = shareDescription;
        }

        Bitmap temp = BitmapFactory.decodeResource(activity.getResources(),
                R.drawable.ic_camera);
        msg.setThumbImage(temp);


        //使用此方法会出现无法分享的问题
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(icon, 150, 150, true);
//        icon.recycle();
//        msg.thumbData = BitmapUtils.bitmapToByteArray(thumbBmp, true);

        IWXAPI api = WXAPIFactory.createWXAPI(activity, AppConfig.APPID_WeiXin);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = msg;
        req.scene = WXSceneSession;
        api.sendReq(req);

    }
}
