package com.prettyyes.user.core.containter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.CategoryModel;
import com.prettyyes.user.model.applocal.AttrName;
import com.prettyyes.user.model.common.ActInfo;
import com.prettyyes.user.model.v8.AliAccountEntity;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.BankEntity;
import com.prettyyes.user.model.v8.QuestionEntity;
import com.prettyyes.user.model.v8.SkuSearchEntity;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: testApkUpdate
 * Package Name: com.cg.app.main.containter
 * Author: SmileChen
 * Created on: 2016/9/14
 * Description: Nothing
 */
public interface JumpPageInterface {

    void goSkuActivity(Context context, String type, String sku_id);

    void goSkuActivity(Context context, SpuInfoEntity spuInfoEntity);

    void goShareActivity(Context context, ShareModel share_model);

    IntentParams getIntentParmas(Activity activity);

    void goCategoryActivity(Context context, ArrayList<CategoryModel> categoryModels);

    void goTemplateActivity(Context context);

    void goTemplateActivity(Context context, String module_id, String spu_type);

    void goTemplateNewActivity(Context context, String module_id, String spu_type);

    void goWengetUrlActivity(Context context);

    void goWengetUrlActivity(Context context, String type);

    void goBigImgActivity(Context context, String img);

    void goBigImgActivity(Context context, ArrayList<String> imgs, int index);

    void goAttrsActivity(Context context);

    void goAttrsActivity(Context context, List<AttrName> data);

    void goUnitActivity(Context context);

    void goUnitActivity(Context context, List<SpuInfoEntity> datas);

    void goSpuListActivity(Context context);

    void goRichEditActivity(Context context);

    void goRichEditActivity(Context context, String html);

    void goKolReplyActivity(Context context, String task_id);

    void goKolReplyActivity(Context context, String task_id, String desc);

    void goKolReplyActivity(Context context);

    void goBrandListActivty(Context context);

    void goBrandSpuListActivty(Context context, String brand_id);

    void goMoreSpuReply(Context context, String task_id);

    void goMoreSpuReply(Context context, String task_id, String first_answer_id);

    void goSearchActivity(Context context);

    void goCommentActivity(Context context, String answer_id);

    void goCommentActivity(Context context, String answer_id, String task_id);

    void goCommentActivity(Context context, String answer_id, AnswerInfoEntity answerInfoEntity, QuestionEntity questionEntity);

    void goCommentActivity(Context context, String answer_id, boolean scrolltoTop);

    void goTopicActivty(Context context, int topic_id);

    void goSearchListActivity(Context context, String search_type, String key_word);

    void goInvateKolActivity(Context context, String task_id);

    void goKolInfoActivity(Context context, String seller_id);

    void goUnLoginActivity(Context context);

    void goApplayKolActivity(Context context);

    void goApplayKolDetailActivity(Context context);

    void goWebActivity(Context context, String url);

    void goWebActivity(Context context, String type, String html);

    void goWebActivity(Context context, String url, ShareModel share_model);

    void goWebActivity(Context context, String url, ShareModel share_model, boolean isneedDownload);

    void goMyAccountActivity(Context context);

    void goBankAccount(Context context, BankEntity bankEntity);

    void goAliAccount(Context context, AliAccountEntity aliAccountEntity);

    void goCollectActivity(Context context);

    void goCartActivity(Context context);

    void goSettingActivity(Context context);

    void goMyAskListActivity(Context context);

    void goMyReplyQueActivity(Context context);

    void goInvateMeActivity(Context context);

    void goSellerOrderListActivity(Context context);

    void goMyOrderInfoActivity(Context context, String order_number);

    void goRefundActivityActivity(Context context, String order_number);

    void goRefundActivityActivity(Context context, String order_number, String type);

    void goSellerOrderInfoActivity(Context context, String order_num);

    void goAddShipActivity(Context context, String order_id);

    void goShipCompanyActivity(Context context);

    void goMyMedalActivity(Context context);

    void goMedalListActivity(Context context);

    void goCouponListActivity(Context context);

    void goAddressListActivity(Context context);

    void goAddressListActivity(Context context, int type, String addressId);

    void goOrderListActivity(Context context);

    void goPersonInfoActivity(Context context);

    void goOrderCheckActivity(Context context, int buy_now);

    void goCartListActivity(Context context);

    void goAskActivity(Context context);

    void goReplyDetailActivity(Context context, String task_id, String answer_id);

    void goReplyDetailActivity(Context context, List<AnswerInfoEntity> datas, QuestionEntity questionEntity, int index, int page);

    void goHowToAsk(Context context);

    void goHowToAsk(Context context, boolean have_ask);

    void goMyScoreListDetailActivity(Context context);

    void goMyScoreRuleActivity(Context context);

    void goLiveActivity(Context context, ActInfo actInfo);

    void goKolActActivity(Context context, int activityId);

    void goScoreDetailActivity(Context context);

    void goPayActivity(ZBundle zBundle);

    void goSendQuestionActivity(ZBundle zBundle);

    void goWithdrawRecordActivity(Context context);

    void goSkuSelectActivity(Context context);

    void goSkuSelectActivity(Context context, boolean need_select);

    void goAddPaperActivity(Context context, String module_id);

    void goUnitTempleteActivity(Context context);

    void goUnitTempleteActivity(Context context, String module_id);

    void goTaobaoTempleteActivity(Context context, SkuSearchEntity skuSearchEntity);

    void goTaobaoUrlSelectActivity(Context context);


    void goPaperPreActivity(Context context, String module_id, String title, String html);

    void goVideoActivity(Context context, String video_path, String video_covery, View view);


    void goTaskNewUserActivity(Context context);

    void goScoreGetActivity(Context context);

    void goBestNewActivity(Context context);

    void goMainActivity(Context context);

    void goCommentInfoActivity(Context context, String comment_id, String answer_id);

    void goInputTxtActivity(Context context, String getcode, String content);

    void goAddExtraVideoUrlActivity(Context context,String video_path,String video_covery);
    void goHowShopActivity(Context context);
}
