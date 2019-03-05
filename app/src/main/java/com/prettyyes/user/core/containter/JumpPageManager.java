package com.prettyyes.user.core.containter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.app.ui.GsyVideoActivity;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.app.ui.comment.CommentNewInfoListActivity;
import com.prettyyes.user.app.ui.comment.CommentNewListActivity;
import com.prettyyes.user.app.ui.comment.MoreReplyActivity;
import com.prettyyes.user.app.ui.comment.ReplyDetailActivity;
import com.prettyyes.user.app.ui.common.AddExtraVideoUrlActivity;
import com.prettyyes.user.app.ui.common.HowtoAskActivity;
import com.prettyyes.user.app.ui.common.ShareActivity;
import com.prettyyes.user.app.ui.common.ViewPagerActivity;
import com.prettyyes.user.app.ui.common.WebviewActivity;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.app.ui.how.CategoryActivity;
import com.prettyyes.user.app.ui.how.CollectionActivity;
import com.prettyyes.user.app.ui.how.EditAttrsActivity;
import com.prettyyes.user.app.ui.how.KolActivityActivity;
import com.prettyyes.user.app.ui.how.LiveActActivity;
import com.prettyyes.user.app.ui.how.ScoreGetActivity;
import com.prettyyes.user.app.ui.how.SearchActivity;
import com.prettyyes.user.app.ui.how.SearchListActivity;
import com.prettyyes.user.app.ui.how.TaskNewUserActivity;
import com.prettyyes.user.app.ui.how.TopicActivity;
import com.prettyyes.user.app.ui.kol.ApplyKolActivity;
import com.prettyyes.user.app.ui.kol.ApplyToKolDetialActivity;
import com.prettyyes.user.app.ui.kol.BestNewQueActivity;
import com.prettyyes.user.app.ui.kol.InvateKolActivity;
import com.prettyyes.user.app.ui.kol.KolInfoActivity;
import com.prettyyes.user.app.ui.kol.KolReplyActivity;
import com.prettyyes.user.app.ui.order.AddShipActivity;
import com.prettyyes.user.app.ui.order.CheckOrderActivity;
import com.prettyyes.user.app.ui.order.CouponListActivity;
import com.prettyyes.user.app.ui.order.HowShopActivity;
import com.prettyyes.user.app.ui.order.MyOrderInfoActivity;
import com.prettyyes.user.app.ui.order.MyOrderListActivity;
import com.prettyyes.user.app.ui.order.RefundActivity;
import com.prettyyes.user.app.ui.order.SellerOrderInfoActivity;
import com.prettyyes.user.app.ui.order.SellerOrderListActivity;
import com.prettyyes.user.app.ui.order.ShipCompanyActivity;
import com.prettyyes.user.app.ui.order.WishListActivity;
import com.prettyyes.user.app.ui.person.AddressListActivity;
import com.prettyyes.user.app.ui.person.AliAccountActivity;
import com.prettyyes.user.app.ui.person.BankAccountActivity;
import com.prettyyes.user.app.ui.person.InputTxtActivity;
import com.prettyyes.user.app.ui.person.InvateMeReplyActivity;
import com.prettyyes.user.app.ui.person.MedalAndScoreActivity;
import com.prettyyes.user.app.ui.person.MedalListActivity;
import com.prettyyes.user.app.ui.person.MyAccountActivity;
import com.prettyyes.user.app.ui.person.MyAskActivity;
import com.prettyyes.user.app.ui.person.MyReplyQueActivity;
import com.prettyyes.user.app.ui.person.MyScoreListDetailActivity;
import com.prettyyes.user.app.ui.person.PersonInfoActivity;
import com.prettyyes.user.app.ui.person.ScoreRuleActivity;
import com.prettyyes.user.app.ui.person.WithDrawRecordActivity;
import com.prettyyes.user.app.ui.setting.SettingActivity;
import com.prettyyes.user.app.ui.spu.BrandInfoActivity;
import com.prettyyes.user.app.ui.spu.BrandListActivity;
import com.prettyyes.user.app.ui.spu.BrandSpuListActivity;
import com.prettyyes.user.app.ui.spu.PaperInfoActivity;
import com.prettyyes.user.app.ui.spu.SkuSelectActivity;
import com.prettyyes.user.app.ui.spu.SpuListActivity;
import com.prettyyes.user.app.ui.spu.SuitDelActivity;
import com.prettyyes.user.app.ui.spu.TaobaoActivity;
import com.prettyyes.user.app.ui.spu.TaobaoWebActivity;
import com.prettyyes.user.app.ui.spu.UnitInfoActivity;
import com.prettyyes.user.app.ui.spu.UnitListActivity;
import com.prettyyes.user.app.ui.template.AddPaperActivity;
import com.prettyyes.user.app.ui.template.AddTemplateNewActivity;
import com.prettyyes.user.app.ui.template.GoodsTempleteActivity;
import com.prettyyes.user.app.ui.template.PaperPreActivity;
import com.prettyyes.user.app.ui.template.TaobaoNewTempleteActivity;
import com.prettyyes.user.app.ui.template.TaobaoUrlSelectActivity;
import com.prettyyes.user.app.ui.template.TemplateActivity;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.CategoryModel;
import com.prettyyes.user.model.applocal.AttrName;
import com.prettyyes.user.model.common.ActInfo;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.model.v8.AliAccountEntity;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.BankEntity;
import com.prettyyes.user.model.v8.QuestionEntity;
import com.prettyyes.user.model.v8.SkuSearchEntity;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.app.account.Constant.INTENT_PARAMS;
import static com.prettyyes.user.app.account.Constant.TYPE_BRAND;
import static com.prettyyes.user.app.account.Constant.TYPE_NONE;
import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;
import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;
import static com.prettyyes.user.app.account.Constant.TYPE_UNIT;
import static com.prettyyes.user.core.utils.AppUtil.blur;

/**
 * Project Name: testApkUpdate
 * Package Name: com.cg.app.main.containter
 * Author: SmileChen
 * Created on: 2016/9/14
 * Description: Nothing
 */
public class JumpPageManager implements JumpPageInterface {


    public JumpPageManager() {
    }

    public static JumpPageManager getManager() {
        return SingleJumpPageManager.instance;
    }

    private static class SingleJumpPageManager {
        static JumpPageManager instance = new JumpPageManager();
    }


    private static final String TAG = "JumpPageManager";
    public static final int TYPE_SHOW = 0;
    public static final int TYPE_SELECT = 1;
    private static JumpPageManager jumpPageManager;


    @Override
    public void goPayActivity(ZBundle zBundle) {
    }

    @Override
    public void goSendQuestionActivity(ZBundle zBundle) {

    }

    @Override
    public void goWithdrawRecordActivity(Context context) {
        goActivity(context, WithDrawRecordActivity.class);
    }

    @Override
    public void goSkuSelectActivity(Context context) {
        if (SpMananger.getUUID() == null) {
            goUnLoginActivity(context);
            return;
        }
        goActivity(context, createIntentParams().setNeed_select(false), SkuSelectActivity.class);

//        if (SpMananger.getUserInfo() != null && SpMananger.getUserInfo().isSeller())
//        else {
//            goApplayKolActivity(context);
//        }

    }

    @Override
    public void goSkuSelectActivity(Context context, boolean need_select) {
        if (SpMananger.getUUID() == null) {
            goUnLoginActivity(context);
            return;
        }
        goActivity(context, createIntentParams().setNeed_select(need_select), SkuSelectActivity.class);

//        if (SpMananger.getUserInfo() != null && SpMananger.getUserInfo().isSeller())
//        else {
//            goApplayKolActivity(context);
//        }
    }

    @Override
    public void goAddPaperActivity(Context context, String module_id) {
        goActivity(context, createIntentParams().setModule_id(module_id), AddPaperActivity.class);
    }

    @Override
    public void goUnitTempleteActivity(Context context) {
        goActivity(context, createIntentParams().setSpu_type(TYPE_UNIT).setModule_id("0"), GoodsTempleteActivity.class);
    }

    @Override
    public void goUnitTempleteActivity(Context context, String module_id) {
        goActivity(context, createIntentParams().setSpu_type(TYPE_UNIT).setModule_id(module_id), GoodsTempleteActivity.class);

    }

    @Override
    public void goTaobaoTempleteActivity(Context context, SkuSearchEntity skuSearchEntity) {
        goActivity(context, createIntentParams().setSkuSearchEntity(skuSearchEntity), TaobaoNewTempleteActivity.class);
    }

    @Override
    public void goTaobaoUrlSelectActivity(Context context) {
        goActivity(context, TaobaoUrlSelectActivity.class);
    }

    @Override
    public void goPaperPreActivity(Context context, String module_id, String title, String html) {
        goActivity(context, createIntentParams().setModule_id(module_id).setTitle(title).setHtml(html), PaperPreActivity.class);
    }

    @Override
    public void goVideoActivity(Context activity, String video_path, String video_covery, View view) {
//        goActivity(activity, createIntentParams().setVideo_path(video_path).setVideo_covery(video_covery), GsyVideoActivity.class);
        if (activity instanceof Activity) {
            Intent intent = new Intent(activity, GsyVideoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(INTENT_PARAMS, createIntentParams().setVideo_covery(video_covery).setVideo_path(video_path));
            bundle.putBoolean(GsyVideoActivity.TRANSITION, true);
            intent.putExtras(bundle);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Pair pair = new Pair<>(view, GsyVideoActivity.IMG_TRANSITION);
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) activity, pair);
                ActivityCompat.startActivity(activity, intent, activityOptions.toBundle());
            } else {
                activity.startActivity(intent);
                ((Activity) activity).overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        }
//        if(context instanceof Activity)
//            ((Activity) context).overridePendingTransition(R.anim.a5, 0);
    }

    @Override
    public void goTaskNewUserActivity(Context context) {
        goActivity(context, TaskNewUserActivity.class);
    }

    @Override
    public void goScoreGetActivity(Context context) {
        goActivity(context, ScoreGetActivity.class);

    }

    @Override
    public void goBestNewActivity(Context context) {
        goActivity(context, BestNewQueActivity.class);
    }

    @Override
    public void goMainActivity(Context context) {
        goActivity(context, MainActivity.class);
    }

    @Override
    public void goCommentInfoActivity(Context context, String comment_id, String answer_id) {
        goActivity(context, createIntentParams().setComment_id(comment_id).setAnswer_id(answer_id), CommentNewInfoListActivity.class);

    }

    @Override
    public void goInputTxtActivity(Context context, String getcode, String content) {
        goActivity(context, createIntentParams().setGetcode(getcode).setContent(content), InputTxtActivity.class);

    }

    @Override
    public void goAddExtraVideoUrlActivity(Context context, String video_path, String video_covery) {
        goActivity(context, createIntentParams().setVideo_path(video_path).setVideo_covery(video_covery), AddExtraVideoUrlActivity.class);
    }

    @Override
    public void goHowShopActivity(Context context) {
        goActivity(context, HowShopActivity.class);
    }

    @Override
    public void goSkuActivity(Context context, String spu_type, String module_id) {
        if (spu_type == null || module_id == null)
            return;
        Class activity_class = null;
        switch (spu_type) {
            case TYPE_TAOBAO:
                activity_class = TaobaoActivity.class;
                break;
            case TYPE_PAPER:
                activity_class = PaperInfoActivity.class;
                break;
            case TYPE_SUIT:
                activity_class = SuitDelActivity.class;
                break;
            case TYPE_UNIT:
                activity_class = UnitInfoActivity.class;
                break;
            case TYPE_BRAND:
                activity_class = BrandInfoActivity.class;
                break;


        }
        if (activity_class == null)
            return;
        goActivity(context, createIntentParams().setSpu_type(spu_type).setModule_id(module_id), activity_class);
    }

    @Override
    public void goSkuActivity(Context context, SpuInfoEntity spuInfoEntity) {
        String spu_type = spuInfoEntity.getSpu_type();
        if (spu_type == null)
            return;
        Class activity_class = null;
        switch (spu_type) {
            case TYPE_TAOBAO:
                activity_class = TaobaoActivity.class;
                break;
            case TYPE_PAPER:
                activity_class = PaperInfoActivity.class;
                break;
            case TYPE_SUIT:
                activity_class = SuitDelActivity.class;
                break;
            case TYPE_UNIT:
                activity_class = UnitInfoActivity.class;
                break;
            case TYPE_BRAND:
                activity_class = BrandInfoActivity.class;
                break;
            case TYPE_NONE:
                return;
            default:
                JumpPageManager.getManager().goWebActivity(context, String.format(AppConfig.getUrl() + "/sku?sku_type=%s&sku_id=%s", spu_type, spuInfoEntity.getModule_id()));
                return;
        }
        if (activity_class == null)
            return;
        goActivity(context, createIntentParams().setSpuInfoEntity(spuInfoEntity), activity_class);

    }

    @Override
    public void goShareActivity(Context context, ShareModel share_model) {
        IntentParams intentParams = new IntentParams();
        intentParams.setShareModel(share_model);
        goActivity(context, intentParams, ShareActivity.class);
    }

    @Override
    public IntentParams getIntentParmas(Activity activity) {
        return (IntentParams) activity.getIntent().getSerializableExtra(INTENT_PARAMS);
    }

    @Override
    public void goCategoryActivity(Context context, ArrayList<CategoryModel> categoryModels) {

        goActivity(context, createIntentParams().setCategoryModels(categoryModels), CategoryActivity.class);
    }


    @Override
    public void goTemplateActivity(Context context) {
//        if (checkSeller(context)) return;
        goActivity(context, TemplateActivity.class);
    }

    @Override
    public void goTemplateActivity(Context context, String module_id, String spu_type) {

        if (spu_type == null || module_id == null)
            return;

        if (spu_type.equals(TYPE_BRAND) || spu_type.equals(TYPE_SUIT)) {
            AppUtil.showToastShort("新版本商品已不支持该类型编辑");
            return;
        }

        UserInfo userInfo = SpMananger.getUserInfo();
        if (userInfo == null) {

            return;
        }

        Class activity_class = null;
        switch (spu_type) {
            case TYPE_PAPER:
                activity_class = AddPaperActivity.class;
                break;
            case TYPE_UNIT:
                activity_class = GoodsTempleteActivity.class;
                break;
            case TYPE_SUIT:
                activity_class = GoodsTempleteActivity.class;
                break;
            case TYPE_BRAND:
                activity_class = GoodsTempleteActivity.class;
                break;
            case TYPE_TAOBAO:
                activity_class = TaobaoNewTempleteActivity.class;
                break;
            default:
                JumpPageManager.getManager().goWebActivity(context, String.format(AppConfig.getUrl() + "/sku?sku_type=%s&sku_id=%s", spu_type, module_id));
                return;
        }

//        boolean iscanClick = false;
//
//        for (int i = 0; i < userInfo.getSeller_template().size(); i++) {
//            UserInfo.SellerTemplateBean sellerTemplateBean = userInfo.getSeller_template().get(i);
//            String equal_sputype = spu_type;
//            if (equal_sputype.equals(TYPE_BRAND))
//                equal_sputype = BRAND_STR;
//            if (sellerTemplateBean.getStyle().equals(equal_sputype) && sellerTemplateBean.getStatus() == 1) {
//                iscanClick = true;
//                break;
//            }
//
//        }
//        if (activity_class == null || !iscanClick) {
//            return;
//        }
        goActivity(context, createIntentParams().setModule_id(module_id).setSpu_type(spu_type), activity_class);
    }

    @Override
    public void goTemplateNewActivity(Context context, String module_id, String spu_type) {
        goActivity(context, createIntentParams().setModule_id(module_id).setSpu_type(spu_type), AddTemplateNewActivity.class);
    }


    @Override
    public void goWengetUrlActivity(Context context) {
        goActivity(context, TaobaoWebActivity.class);
    }

    @Override
    public void goWengetUrlActivity(Context context, String type) {
        goActivity(context, createIntentParams().setGo_url_type(type), TaobaoWebActivity.class);
    }

    @Override
    public void goBigImgActivity(Context context, String img) {
        if (StringUtils.strIsEmpty(img))
            return;
        ViewPagerActivity.goVpActivity(context, 1, img, "");

    }

    @Override
    public void goBigImgActivity(Context context, ArrayList<String> imgs, int index) {
        ViewPagerActivity.goVpActivity(context, index, imgs, "img");


    }

    @Override
    public void goAttrsActivity(Context context) {
        goActivity(context, EditAttrsActivity.class);
    }

    @Override
    public void goAttrsActivity(Context context, List<AttrName> data) {
        goActivity(context, createIntentParams().setAttrNames(data), EditAttrsActivity.class);
    }

    @Override
    public void goUnitActivity(Context context) {
        goActivity(context, UnitListActivity.class);
    }

    @Override
    public void goUnitActivity(Context context, List<SpuInfoEntity> datas) {
        goActivity(context, createIntentParams().setSpuInfoEntities(datas), UnitListActivity.class);

    }

    @Override
    public void goSpuListActivity(Context context) {
        if (checkUnlogin(context)) return;
        goActivity(context, SpuListActivity.class);
    }

    private boolean checkSeller(Context context) {
//        if (SpMananger.getUserInfo() == null) {
//            goUnLoginActivity(context);
//            return true;
//        }
//        if (!SpMananger.getUserInfo().isSeller()) {
//            AppUtil.showToastShort("非卖家不可使用该功能");
//            return true;
//        }
        return false;
    }

    @Override
    public void goRichEditActivity(Context context) {
//        goActivity(context, RichEditActivity.class);
    }

    @Override
    public void goRichEditActivity(Context context, String html) {
//        goActivity(context, createIntentParams().setHtml(html), RichEditActivity.class);

    }

    @Override
    public void goKolReplyActivity(Context context, String task_id) {
        if (checkUnlogin(context)) return;
        goActivity(context, createIntentParams().setTask_id(task_id), KolReplyActivity.class);
    }

    @Override
    public void goKolReplyActivity(Context context, String task_id, String desc) {
        if (checkUnlogin(context)) return;
        goActivity(context, createIntentParams().setTask_id(task_id).setQuestion(desc), KolReplyActivity.class);
    }

    @Override
    public void goKolReplyActivity(Context context) {
        if (checkUnlogin(context)) return;
        goActivity(context, KolReplyActivity.class);
    }

    @Override
    public void goBrandListActivty(Context context) {

        goActivity(context, BrandListActivity.class);
    }

    @Override
    public void goBrandSpuListActivty(Context context, String brand_id) {
        if (checkUnlogin(context)) return;
        goActivity(context, createIntentParams().setBrand_id(brand_id), BrandSpuListActivity.class);
    }

    public boolean checkUnlogin(Context context) {
        if (context == null) {
            context = ZBundleCore.getInstance().getTopActivity();
        }
        if (SpMananger.getUUID() == null) {
            goUnLoginActivity(context);
            return true;
        }
        return false;
    }

    @Override
    public void goMoreSpuReply(Context context, String task_id) {
        if (StringUtils.strIsEmpty(task_id))
            return;
        goActivity(context, createIntentParams().setTask_id(task_id), MoreReplyActivity.class);
    }

    @Override
    public void goMoreSpuReply(Context context, String task_id, String first_answer_id) {
        if (StringUtils.strIsEmpty(task_id))
            return;
        goActivity(context, createIntentParams().setTask_id(task_id).setAnswer_id(first_answer_id), MoreReplyActivity.class);
    }

    @Override
    public void goSearchActivity(Context context) {
        AppStatistics.onEvent(context, "search_bar");
        goActivity(context, SearchActivity.class);

    }

    @Override
    public void goCommentActivity(Context context, String answer_id) {
        if (checkUnlogin(context))
            return;
        goActivity(context, createIntentParams().setAnswer_id(answer_id), CommentNewListActivity.class);
    }

    @Override
    public void goCommentActivity(Context context, String answer_id, String task_id) {
        if (checkUnlogin(context))
            return;
        goActivity(context, createIntentParams().setAnswer_id(answer_id).setTask_id(task_id), CommentNewListActivity.class);

    }

    @Override
    public void goCommentActivity(Context context, String answer_id, AnswerInfoEntity answerInfoEntity, QuestionEntity questionEntity) {
//        goActivity(context, createIntentParams().setAnswer_id(answer_id).setScrollToTop(false).setAnswerInfoEntity(answerInfoEntity).setQuestionEntity(questionEntity), CommentListActivity.class);

    }

    @Override
    public void goCommentActivity(Context context, String answer_id, boolean scrolltoTop) {
//        goActivity(context, createIntentParams().setAnswer_id(answer_id).setScrollToTop(scrolltoTop), CommentListActivity.class);

    }

    @Override
    public void goTopicActivty(Context context, int topic_id) {
        TopicActivity.goQuestionActivity(context, topic_id);
    }

    @Override
    public void goSearchListActivity(Context context, String search_type, String key_word) {

        goActivity(context, createIntentParams().setSearch_type(search_type).setKey_word(key_word), SearchListActivity.class);
    }

    @Override
    public void goInvateKolActivity(Context context, String task_id) {
        goActivity(context, createIntentParams().setTask_id(task_id), InvateKolActivity.class);
    }

    @Override
    public void goKolInfoActivity(Context context, String seller_id) {
        goActivity(context, createIntentParams().setSeller_id(seller_id), KolInfoActivity.class);
    }

    @Override
    public void goUnLoginActivity(Context context) {

        goActivity(context, RegisterActivity.class);
    }

    @Override
    public void goApplayKolActivity(Context context) {
        goActivity(context, ApplyKolActivity.class);
    }

    @Override
    public void goApplayKolDetailActivity(Context context) {
        goActivity(context, ApplyToKolDetialActivity.class);

    }

    @Override
    public void goWebActivity(Context context, String url) {
        goActivity(context, createIntentParams().setUrl(url), WebviewActivity.class);
    }

    public static final String TYPE_HTML = "html";

    @Override
    public void goWebActivity(Context context, String type, String html) {
        goActivity(context, createIntentParams().setType(type).setHtml(html), WebviewActivity.class);
    }

    @Override
    public void goWebActivity(Context context, String url, ShareModel share_model) {
        goActivity(context, createIntentParams().setUrl(url).setShareModel(share_model), WebviewActivity.class);

    }

    @Override
    public void goWebActivity(Context context, String url, ShareModel share_model, boolean isneedDownload) {
        goActivity(context, createIntentParams().setUrl(url).setShareModel(share_model).setIsneeedDownload(isneedDownload), WebviewActivity.class);

    }

    @Override
    public void goMyAccountActivity(Context context) {
        goActivity(context, MyAccountActivity.class);
    }

    @Override
    public void goBankAccount(Context context, BankEntity bankEntity) {
        if (bankEntity != null)
            goActivity(context, createIntentParams().setBankEntity(bankEntity), BankAccountActivity.class);
    }

    @Override
    public void goAliAccount(Context context, AliAccountEntity aliAccountEntity) {
        goActivity(context, createIntentParams().setAlipayEntity(aliAccountEntity), AliAccountActivity.class);

    }

    @Override
    public void goCollectActivity(Context context) {
        goActivity(context, CollectionActivity.class);
    }

    @Override
    public void goCartActivity(Context context) {
        goActivity(context, WishListActivity.class);
    }

    @Override
    public void goSettingActivity(Context context) {
        goActivity(context, SettingActivity.class);
    }

    @Override
    public void goMyAskListActivity(Context context) {
        goActivity(context, MyAskActivity.class);
    }

    @Override
    public void goMyReplyQueActivity(Context context) {
        if (checkUnlogin(context)) return;
        goActivity(context, MyReplyQueActivity.class);
    }

    @Override
    public void goInvateMeActivity(Context context) {
        goActivity(context, InvateMeReplyActivity.class);

    }

    @Override
    public void goSellerOrderListActivity(Context context) {
        goActivity(context, SellerOrderListActivity.class);
    }

    @Override
    public void goMyOrderInfoActivity(Context context, String order_number) {
        goActivity(context, createIntentParams().setOrder_number(order_number), MyOrderInfoActivity.class);

    }

    @Override
    public void goRefundActivityActivity(Context context, String order_number) {
        goActivity(context, createIntentParams().setOrder_number(order_number).setType(USER), RefundActivity.class);

    }

    public static final String SELLER = "seller";
    public static final String USER = "user";

    @Override
    public void goRefundActivityActivity(Context context, String order_number, String type) {
        goActivity(context, createIntentParams().setOrder_number(order_number).setType(type), RefundActivity.class);

    }

    @Override
    public void goSellerOrderInfoActivity(Context context, String order_num) {
        goActivity(context, createIntentParams().setOrder_number(order_num), SellerOrderInfoActivity.class);
    }

    @Override
    public void goAddShipActivity(Context context, String order_id) {
        goActivity(context, createIntentParams().setOrder_number(order_id), AddShipActivity.class);
    }

    @Override
    public void goShipCompanyActivity(Context context) {
        goActivity(context, ShipCompanyActivity.class);
    }

    @Override
    public void goMyMedalActivity(Context context) {
        if (checkUnlogin(context))
            return;
        goActivity(context, MedalAndScoreActivity.class);
    }

    @Override
    public void goMedalListActivity(Context context) {
        goActivity(context, MedalListActivity.class);
    }

    @Override
    public void goCouponListActivity(Context context) {
        goActivity(context, CouponListActivity.class);
    }

    @Override
    public void goAddressListActivity(Context context) {
        goActivity(context, AddressListActivity.class);
    }

    @Override
    public void goAddressListActivity(Context context, int type, String addressId) {
        goActivity(context, createIntentParams().setAddress_id(addressId).setAddress_list_type(type), AddressListActivity.class);
    }

    @Override
    public void goOrderListActivity(Context context) {
        goActivity(context, MyOrderListActivity.class);
    }

    @Override
    public void goPersonInfoActivity(Context context) {
        if (checkUnlogin(context))
            return;
        goActivity(context, PersonInfoActivity.class);
    }

    @Override
    public void goOrderCheckActivity(Context context, int buy_now) {
        goActivity(context, createIntentParams().setBuy_now(buy_now), CheckOrderActivity.class);
    }

    @Override
    public void goCartListActivity(Context context) {
        goActivity(context, WishListActivity.class);
    }

    @Override
    public void goAskActivity(Context context) {
        try {
            if (context instanceof Activity) {
                DataCenter.bgBitmap = blur((Activity) context);
            }
        } catch (Exception ee) {

        }
        goActivity(context, AskActivity.class);
    }

    @Override
    public void goReplyDetailActivity(Context context, String task_id, String answer_id) {
        goActivity(context, createIntentParams().setTask_id(task_id).setAnswer_id(answer_id), ReplyDetailActivity.class);
        if (context instanceof Activity)
            ((Activity) context).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void goReplyDetailActivity(Context context, List<AnswerInfoEntity> datas, QuestionEntity questionEntity, int index, int page) {
        goActivity(context, createIntentParams().setQuestionEntity(questionEntity).setAnswerInfoEntities(datas).setPosition(index).setPage(page), ReplyDetailActivity.class);
    }

    @Override
    public void goHowToAsk(Context context) {
        goActivity(context, HowtoAskActivity.class);
    }

    @Override
    public void goHowToAsk(Context context, boolean have_ask) {
        goActivity(context, createIntentParams().setShow_ask(have_ask), HowtoAskActivity.class);

    }

    @Override
    public void goMyScoreListDetailActivity(Context context) {
        goActivity(context, MyScoreListDetailActivity.class);
    }

    @Override
    public void goMyScoreRuleActivity(Context context) {
        goActivity(context, ScoreRuleActivity.class);
    }

    @Override
    public void goLiveActivity(Context context, ActInfo actInfo) {
        if (ZBundleCore.getInstance().isExist(LiveActActivity.class)) {
            close(context);
            return;
        }
        goActivity(context, createIntentParams().setActInfo(actInfo), LiveActActivity.class);

    }

    @Override
    public void goKolActActivity(Context context, int activityId) {
        goActivity(context, createIntentParams().setActivity_id(activityId), KolActivityActivity.class);

    }

    @Override
    public void goScoreDetailActivity(Context context) {
        goActivity(context, MedalAndScoreActivity.class);

    }

//    @Override
//    public void goReplyDetailActivity(Context context, String task_id, String answer_id) {
//        goActivity(context, createIntentParams().setTask_id(task_id).setAddress_id(answer_id), ReplyDetailActivity.class);
//
//    }

    private void goActivity(Context context, IntentParams intentParams, Class activity_class) {
        Intent intent = new Intent(context, activity_class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(INTENT_PARAMS, intentParams);
        intent.putExtras(bundle);
        if (!(context instanceof Activity))
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void close(Context context) {
        if (context instanceof Activity)
            ((Activity) context).finish();

    }

    private IntentParams createIntentParams() {
        return new IntentParams();
    }

    private void goActivity(Context context, Class activity_class) {
        Intent intent = new Intent(context, activity_class);
        if (!(context instanceof Activity))
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
