package com.prettyyes.user.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.prettyyes.user.api.netXutils.DesUtils;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.HowNewTask;
import com.prettyyes.user.core.event.RewardSelectEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.data.DataCenter;

import org.json.JSONException;
import org.json.JSONObject;

import static com.prettyyes.user.app.account.Constant.TYPE_BRAND;
import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;
import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;
import static com.prettyyes.user.app.account.Constant.TYPE_UNIT;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/12/12
 * Description: 推送的逻辑处理
 */
public class PushHandler {
    private Context context;
    private static final String TAG = "PushHandler";

    public PushHandler(Context context) {
        this.context = context;
    }

    public void handReceiveData(String json) {
        if(null==json)
            return;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String type = jsonObject.optString("type");
            if (TextUtils.isEmpty(type)) {
                type = jsonObject.optString("page_type");
            }

            switch (type) {
                case "howSku":
                    String sku_id = jsonObject.optString("sku_id");
                    if (TextUtils.isEmpty(sku_id))
                        return;
                    JumpPageManager.getManager().goSkuActivity(context, TYPE_BRAND, sku_id);
                    break;
                case "howUnit":
                    String how_unit_id = jsonObject.optString("sku_id");
                    if (TextUtils.isEmpty(how_unit_id))
                        return;
                    JumpPageManager.getManager().goSkuActivity(context, TYPE_UNIT, how_unit_id);
                    break;
                case "howSuit":
                    String suit_id = jsonObject.optString("sku_id");
                    if (TextUtils.isEmpty(suit_id)) {
                        suit_id = jsonObject.optString("suit_id");
                    }
                    if (TextUtils.isEmpty(suit_id))
                        return;
                    startMianActivity();
                    JumpPageManager.getManager().goSkuActivity(context, TYPE_SUIT, suit_id);
//                    SuitDelActivity.goSuitDelActivityByReceiver(context, Integer.parseInt(suit_id));
                    break;
                case "howTaobao":
                    String taobao_id = jsonObject.optString("sku_id");
                    if (TextUtils.isEmpty(taobao_id)) {
                        taobao_id = jsonObject.optString("taobao_id");
                    }
                    if (TextUtils.isEmpty(taobao_id))
                        return;
                    startMianActivity();
                    JumpPageManager.getManager().goSkuActivity(context, TYPE_TAOBAO, taobao_id);

//                    TaobaoActivity.goTaobaoActivityByReceiver(context, Integer.parseInt(taobao_id));
                    break;
                case "howPaper":
                    String paper_id = jsonObject.optString("sku_id");
                    if (TextUtils.isEmpty(paper_id))
                        return;
                    startMianActivity();
                    JumpPageManager.getManager().goSkuActivity(context, TYPE_PAPER, paper_id);
                    break;
                case "howSpu":
                    String spu_id = jsonObject.optString("spu_id");
                    String spu_type = jsonObject.optString("spu_type");
                    startMianActivity();
                    JumpPageManager.getManager().goSkuActivity(context, spu_type, spu_id);
                    break;
                case "howKol":
                    String seller_id = jsonObject.optString("seller_id");
                    if (TextUtils.isEmpty(seller_id))
                        return;
                    startMianActivity();
                    JumpPageManager.getManager().goKolInfoActivity(context, seller_id);
                    break;
                case "howReply":
                    final String task_id = jsonObject.optString("task_id");
                    String task = jsonObject.optString("task");
                    String reply_kol = jsonObject.optString("reply_kol");
                    String notify_type = jsonObject.optString("notify_type");
                    if (StringUtils.strIsEmpty(task_id))
                        return;
                    if (notify_type != null && notify_type.equals("transmission")) {
                        LogUtil.i(TAG, "ZBundleCore.getInstance().getTopActivity()" + ZBundleCore.getInstance().getTopActivity());


                        String showtxt = String.format("%s 回答了你的问题：%s", reply_kol, task);

                        if (ZBundleCore.getInstance().getTopActivity() != null)

                            if (MyLifecycleHandler.isApplicationInForeground())
                                ((BaseActivity) ZBundleCore.getInstance().getTopActivity()).showSnackLong(showtxt, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        JumpPageManager.getManager().goMoreSpuReply(context, task_id);
                                    }
                                });
                        return;
                    }
                    startMianActivity();
                    JumpPageManager.getManager().goMoreSpuReply(context, task_id);

//                    MoreReplyActivity.goMoreCommentActivityByReceiver(context, Integer.parseInt(task_id));
//                    Intent intent = new Intent();
//                    intent.setAction(Constant.MyProblem_LIST_REFRESH_READ_TASKID);
//                    intent.putExtra("task_id", task_id);
//                    context.sendBroadcast(intent);

                    break;
                case "howWeb":
                    String url = jsonObject.optString("url");
                    String isOnlyDownLoad = jsonObject.optString("is_down");
                    String share_title = jsonObject.optString("share_title");
                    String share_content = jsonObject.optString("share_content");
                    String share_img = jsonObject.optString("share_img");
                    String share_url = jsonObject.optString("share_url");
                    ShareModel share_model = new ShareModel();
                    share_model.setShare_content(share_content);
                    share_model.setImage(share_img);
                    share_model.setTargetUrl(share_url);
                    share_model.setTitle(share_title);
                    if (TextUtils.isEmpty(url))
                        return;
                    startMianActivity();

                    if (BaseApplication.UUID != null) {
                        if (url.contains("?")) {
                            url += "&uuid=";
                        } else {
                            url += "?uuid=";
                        }
                        try {

                            url += DesUtils.encryptionByDes(BaseApplication.UUID);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    boolean needownload = isOnlyDownLoad.equals("1");
                    if (StringUtils.strIsEmpty(share_model.getTargetUrl()))
                        JumpPageManager.getManager().goWebActivity(context, url, null, needownload);
                    else
                        JumpPageManager.getManager().goWebActivity(context, url, share_model, needownload);

                    break;
                case "howPage":
                    String page = jsonObject.optString("page");
                    if (TextUtils.isEmpty(page))
                        return;
                    switch (page) {
                        case "home":
                            startMianActivity(0);
                            break;
                        case "kol":
                            startMianActivity(1);
                            break;
                        case "reply":
                            startMianActivity(3);
                            break;
                        case "myinfo":
                            startMianActivity(4);
                            break;
                        default:
                            break;
                    }
                    break;
                case "howAsk":
                    startMianActivity();
                    JumpPageManager.getManager().goAskActivity(context);
//                    AskActivity.goAskActivityByReceiver(context);
                    break;
                case "howOrder":
                    String ordernumber = jsonObject.optString("order_id");
                    if (TextUtils.isEmpty(ordernumber))
                        return;
                    startMianActivity();
                    JumpPageManager.getManager().goMyOrderInfoActivity(context, ordernumber);
//                    MyOrderInfoActivity.goOrderInfoActivityByout(context, ordernumber);
                case "howKolSession":
                    String session_id = jsonObject.optString("session_id");
                    if (TextUtils.isEmpty(session_id))
                        return;
                    startMianActivity();
                    JumpPageManager.getManager().goKolActActivity(context, Integer.parseInt(session_id));
//                    KolActivityActivity.goKolActActivityByout(context, Integer.parseInt(session_id));
                    //启动页面
                    break;
                case "howTopicTask":
                    String topic_id = jsonObject.optString("topic_id");
                    if (TextUtils.isEmpty(topic_id))
                        return;
                    startMianActivity();
                    JumpPageManager.getManager().goTopicActivty(context, Integer.parseInt(topic_id));
                    //启动页面
                    break;

                case "howNewTask":
//                    if (MyLifecycleHandler.isApplicationInForeground())
                    AppRxBus.getInstance().post(new HowNewTask(true));
                    break;
                case "howAt":
                    String from = jsonObject.optString("from");
                    String task_from = jsonObject.optString("task");
                    JumpPageManager.getManager().goInvateMeActivity(context);
//                    if (ZBundleCore.getInstance().getTopActivity() != null)
//                        if (AppUtil.isRunningForeground(context))
//                            ((BaseActivity) ZBundleCore.getInstance().getTopActivity()).showSnackLong(String.format("\"%s\"提了这个问题\"%s\"并@了你", from, task_from), new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    JumpPageManager.getManager().goInvateMeActivity(context);
//                                }
//                            });
                    break;
                case "howSourceUid":
                    String uid = jsonObject.optString("uid");
                    DataCenter.setSource_uid(uid);
                    break;
                case "howReplyTask":
                    String reply_task_id = jsonObject.optString("task_id");
                    String task_desc = jsonObject.optString("task_desc");
                    JumpPageManager.getManager().goKolReplyActivity(context, reply_task_id, task_desc);
                    break;
                case "howInvite":
                    String howInvite_share_model = jsonObject.optString("share_model");
                    ShareModel share_model1 = GsonHelper.getGson().fromJson(howInvite_share_model, ShareModel.class);
                    JumpPageManager.getManager().goShareActivity(context, share_model1);
                    break;
                case "howAnswer":
                    String answer_id = jsonObject.optString("answer_id");
                    String howAnswer_task_id = jsonObject.optString("task_id");
                    JumpPageManager.getManager().goCommentActivity(context, answer_id,howAnswer_task_id);
                    break;
                case "login":
                    JumpPageManager.getManager().checkUnlogin(context);
                    break;
                case "howPersonalEdit":
                    JumpPageManager.getManager().goPersonInfoActivity(context);
                    break;
                case "howMember":
                    JumpPageManager.getManager().goMyMedalActivity(context);
                    break;
                case "howPurchaseOrder":
                    JumpPageManager.getManager().goOrderListActivity(context);
                    break;
                case "howShoppingCart":
                    JumpPageManager.getManager().goCartListActivity(context);
                    break;
                case "howSaleOrder":
                    JumpPageManager.getManager().goSellerOrderListActivity(context);
                    break;
                case "howAccountSetting":
                    JumpPageManager.getManager().goMyAccountActivity(context);
                    break;
                case "howSkuManage":
                    JumpPageManager.getManager().goSpuListActivity(context);
                    break;
                case "howMyAsk":
                    JumpPageManager.getManager().goAskActivity(context);
                    break;
                case "howMyReply":
                    JumpPageManager.getManager().goMyReplyQueActivity(context);
                    break;
                case "howMyCollect":
                    JumpPageManager.getManager().goCollectActivity(context);
                    break;
                case "howMyInvite":
                    JumpPageManager.getManager().goInvateMeActivity(context);
                    break;
                case "howMyCoupon":
                    JumpPageManager.getManager().goCouponListActivity(context);
                    break;
                case "howMySetting":
                    JumpPageManager.getManager().goSettingActivity(context);
                    break;
                case "howCustomerService":
                    AccountDataRepo.getAccountManager().chatWithkf();
                    break;
                case "howCommentList":
                    String commentAnswerId = jsonObject.optString("answer_id");
                    String commentTaskId = jsonObject.optString("task_id");
                    JumpPageManager.getManager().goCommentActivity(context, commentAnswerId,commentTaskId);
                    break;
                case "howNewUserTask":
                    JumpPageManager.getManager().goTaskNewUserActivity(context);
                    break;
                case "howScoreGet":
                    JumpPageManager.getManager().goScoreGetActivity(context);
                    break;
                case "howReward":
                    startMianActivity(0);
                    AppRxBus.getInstance().post(new RewardSelectEvent());
                    break;
                case "howReplyBestNew":
                    JumpPageManager.getManager().goBestNewActivity(context);
                    break;
                default:
                    break;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void startMianActivity() {
        if (!ZBundleCore.getInstance().isExist(MainActivity.class)) {
            startMainAlive(context, 0);
        }
    }

    private void startMianActivity(int index) {
        startMainAlive(context, index);
    }


    /**
     * @param context
     * @param index
     */
    private void startMainAlive(Context context, int index) {
        Intent i = new Intent();
        i.setClass(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("pageIndex", index);
        i.putExtras(bundle);
        if (!(context instanceof Activity))
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


}
