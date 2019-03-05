package com.prettyyes.user.app.view.app;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.mvpPresenter.QuestionEntity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.app.ui.order.WishListActivity;
import com.prettyyes.user.core.CalendarHandler;
import com.prettyyes.user.core.LikeDisLikeHandler;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.PermissionUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.BIgImgEntity;
import com.prettyyes.user.model.v8.SellerInfoEntity;
import com.prettyyes.user.model.web.CalendarModel;
import com.prettyyes.user.model.web.WishData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;
import static com.prettyyes.user.app.ui.how.TopicActivity.goQuestionActivity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.app
 * Author: SmileChen
 * Created on: 2016/12/1
 * Description: 提供js 调用的android本地方法
 */
public class WebAppInterface {
    private static final String TAG = "WebAppInterface";
    Context mContext;

    /**
     * Instantiate the interface and set the context
     */
    public WebAppInterface(Context c) {
        mContext = c;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void saveDate(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final CalendarModel calendarModel = GsonHelper.getGson().fromJson(json, CalendarModel.class);
                PermissionUtils.requestSinglePermission((Activity) mContext, new PermissionUtils.PermissionGrant() {
                    @Override
                    public void onPermissionGranted(int requestCode) {
                        CalendarHandler.addCalendar(mContext, calendarModel);
                    }
                }, PermissionUtils.PERMISSION_WRITE_CALENDAR);

            }
        });
    }

    /**
     * window.how.toSuit('{"suit_id":"5466"}')
     *
     * @param json
     */
    @JavascriptInterface
    public void toSuit(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    JumpPageManager.getManager().goSkuActivity(mContext, TYPE_SUIT, getVirableByJson(json, "suit_id"));
//                    SuitDelActivity.goSuitDelActivity(mContext, Integer.parseInt());
                } catch (Exception ee) {

                }
            }
        });
    }

    /**
     * onclick="window.how.toTaobao('{"taobao_id":"86"}')"
     *
     * @param json
     */
    @JavascriptInterface
    public void toTaobao(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JumpPageManager.getManager().goSkuActivity((Activity) mContext, TYPE_TAOBAO, getVirableByJson(json, "taobao_id"));
                } catch (Exception ee) {

                }
            }
        });
    }

    /**
     * window.how.toKol('{"uid":"1085"}')
     *
     * @param json
     */
    @JavascriptInterface
    public void toKol(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JumpPageManager.getManager().goKolInfoActivity(mContext, getVirableByJson(json, "uid"));
                } catch (Exception ee) {

                }
            }
        });
    }

    /**
     * window.how.addWishlist('{"cart_type":TYPE_SUIT,"suit_id":"5466","suit_unit":"","taobao_id":"","num":"1","cart_status":"1"}')
     *
     * @param json
     */
    @JavascriptInterface
    public void addWishlist(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WishData wishData = GsonHelper.getGson().fromJson(json, WishData.class);

                try {
                    if (wishData.getCart_type().equals(TYPE_SUIT))
                        new OrderApiImpl().cartaddgoods(BaseApplication.UUID, wishData.getCart_type(), Integer.parseInt(wishData.getSuit_id()), "", 0, 1, 0, new NetReqCallback() {
                            @Override
                            public void apiRequestFail(String message, String method) {

                            }

                            @Override
                            public void apiRequestSuccess(Object o, String method) {
                                AppUtil.showToastShort(mContext.getString(R.string.addCartSuccess));
                            }
                        });
                    else if (wishData.getCart_type().equals(TYPE_TAOBAO))
                        new OrderApiImpl().cartaddgoods(BaseApplication.UUID, wishData.getCart_type(), 0, "", Integer.parseInt(wishData.getTaobao_id()), 1, 0, new NetReqCallback() {
                            @Override
                            public void apiRequestFail(String message, String method) {

                            }

                            @Override
                            public void apiRequestSuccess(Object o, String method) {
                                AppUtil.showToastShort(mContext.getString(R.string.addCartSuccess));

                            }
                        });
                } catch (Exception ee) {

                }

            }
        });
    }

    /**
     * window.how.upAnwser('{"answer_id":"82944"}')
     *
     * @param json
     */
    @JavascriptInterface
    public void upAnwser(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(mContext);
                    return;
                }
                try {
                    AppUtil.showToastShort("like");
                    new LikeDisLikeHandler().LikeDisLikeAnswerReply(BaseApplication.UUID, "like", 1, Integer.parseInt(getVirableByJson(json, "answer_id")));
                } catch (Exception ee) {

                }
            }
        });
    }

    @JavascriptInterface
    public void downAnswer(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(mContext);
                    return;
                }
                try {
                    AppUtil.showToastShort("dislike");
                    new LikeDisLikeHandler().LikeDisLikeAnswerReply(BaseApplication.UUID, "dislike", 1, Integer.parseInt(getVirableByJson(json, "answer_id")));
                } catch (Exception ee) {

                }
            }
        });
    }

    @JavascriptInterface
    public void ask(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LogUtil.i(TAG, "ask");
                LogUtil.i(TAG, json);
                if (TextUtils.isEmpty(json)) {
                    AskActivity.goAskActivity(mContext);

                } else {
                    String uid = getVirableByJson(json, "uid");
                    String nickname = getVirableByJson(json, "nickname");
                    String act_id = getVirableByJson(json, "act_id");
                    String act_name = getVirableByJson(json, "act_name");

                    SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
                    int id = 0;
                    if (StringUtils.strIsEmpty(uid))
                        id = 0;
                    else
                        id = Integer.parseInt(uid);
                    sellerInfoEntity.setId(id);
                    sellerInfoEntity.setNickname(nickname);


                    if (act_id != null) {
                        QuestionEntity questionEntity = new QuestionEntity();
                        questionEntity.setAct_id(act_id + "");
                        questionEntity.setHash_tag(act_name);
                        AskActivity.goAskActivity(mContext, questionEntity, sellerInfoEntity);

                    } else {
                        AskActivity.goAskActivity(mContext, sellerInfoEntity);

                    }


                }
            }
        });
    }

    @JavascriptInterface
    public void ask() {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AskActivity.goAskActivity(mContext);
            }
        });
    }


    @JavascriptInterface
    public void jump(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LogUtil.i(TAG, json);

                new PushHandler(mContext).handReceiveData(json);
            }
        });
    }


    @JavascriptInterface
    public void topicAsk(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    goQuestionActivity(mContext, Integer.parseInt(getVirableByJson(json, "topic_id")));
                } catch (Exception ee) {

                }
            }
        });
    }

    @JavascriptInterface
    public void shopCart() {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    WishListActivity.goWishListActivity(mContext);
                } catch (Exception ee) {

                }
            }
        });
    }

    @JavascriptInterface
    public void share(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String share_url = getVirableByJson(json, "share_url");
                String title = getVirableByJson(json, "share_title");
                String image = getVirableByJson(json, "share_image");
                String content = getVirableByJson(json, "share_content");
                String share_weibo_img = getVirableByJson(json, "share_weibo_img");
                String share_weibo_content = getVirableByJson(json, "share_weibo_content");
                View view = ((ViewGroup) ((Activity) mContext).findViewById(android.R.id.content)).getChildAt(0);
                ShareModel share_model = new ShareModel();
                share_model.setTargetUrl(share_url);
                share_model.setTitle(title);
                share_model.setImage(image);
                share_model.setShare_content(content);
                share_model.setShare_weibo_img(share_weibo_img);
                share_model.setShare_weibo_content(share_weibo_content);
                new ShareHandler((Activity) mContext).setRes(share_model, null).shareAtWindow(view);
            }
        });
    }


    @JavascriptInterface
    public void goBigImgActivity(final String json) {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LogUtil.i(TAG, json);
                BIgImgEntity bIgImgEntity = GsonHelper.getGson().fromJson(json, BIgImgEntity.class);
                if (bIgImgEntity.imgs.size() <= 1)
                    JumpPageManager.getManager().goBigImgActivity(mContext, bIgImgEntity.image_url);
                else
                    JumpPageManager.getManager().goBigImgActivity(mContext, (ArrayList<String>) bIgImgEntity.imgs, bIgImgEntity.index);
            }
        });
        //// TODO: 16/6/23
    }

    private String getVirableByJson(String json, String key) {
        String result = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.optString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


}
