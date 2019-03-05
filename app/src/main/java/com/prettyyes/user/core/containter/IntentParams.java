package com.prettyyes.user.core.containter;

import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.CategoryModel;
import com.prettyyes.user.model.applocal.AttrName;
import com.prettyyes.user.model.common.ActInfo;
import com.prettyyes.user.model.v8.AliAccountEntity;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.BankEntity;
import com.prettyyes.user.model.v8.HowScoreEntity;
import com.prettyyes.user.model.v8.QuestionEntity;
import com.prettyyes.user.model.v8.SkuSearchEntity;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/6/22.
 */

public class IntentParams implements Serializable {
    private ShareModel share_model;
    private ArrayList<CategoryModel> categoryModels;
    private List<SpuInfoEntity> spuInfoEntities;
    private List<AnswerInfoEntity> answerInfoEntities;
    private List<AttrName> attrNames;
    private String module_id;
    private String spu_type;
    private String go_url_type;
    private SpuInfoEntity spuInfoEntity;
    private String html;
    private String brand_id;
    private String task_id;
    private String answer_id;
    private String search_type;
    private String key_word;
    private String seller_id;
    private String url;
    private BankEntity bankEntity;
    private AliAccountEntity alipayEntity;
    private boolean isneeedDownload;
    private boolean scrollToTop = true;//默认滚动到顶部
    public String order_number;
    public String ship_code;
    public String ship_number;
    private String address_id;
    private int address_list_type;
    private int buy_now;
    public int position;
    public int page;
    public String question;
    public boolean show_ask;
    private ActInfo actInfo;
    private int activity_id;
    private HowScoreEntity howScoreEntity;
    private String type;
    private String title;
    public SkuSearchEntity skuSearchEntity;
    public String video_path;
    public String video_covery;
    public String comment_id;
    public boolean need_select;
    public String getcode;
    public String content;

    public IntentParams setGetcode(String getcode) {
        this.getcode = getcode;
        return this;
    }

    public IntentParams setContent(String content) {
        this.content = content;
        return this;

    }

    public String getContent() {
        return content;
    }

    public String getGetcode() {
        return getcode;
    }

    public IntentParams setComment_id(String comment_id) {
        this.comment_id = comment_id;
        return this;
    }

    public String getComment_id() {
        return comment_id;
    }

    public IntentParams setNeed_select(boolean need_select) {
        this.need_select = need_select;
        return this;
    }

    public IntentParams setVideo_covery(String video_covery) {
        this.video_covery = video_covery;
        return this;
    }

    public String getVideo_covery() {
        return video_covery;
    }

    public IntentParams setVideo_path(String video_path) {
        this.video_path = video_path;
        return this;
    }

    public String getVideo_path() {
        return video_path;
    }

    public String getTitle() {
        return title;
    }

    public IntentParams setTitle(String title) {
        this.title = title;
        return this;
    }

    public IntentParams setSkuSearchEntity(SkuSearchEntity skuSearchEntity) {
        this.skuSearchEntity = skuSearchEntity;
        return this;
    }

    public IntentParams setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public IntentParams setHowScoreEntity(HowScoreEntity howScoreEntity) {
        this.howScoreEntity = howScoreEntity;
        return this;
    }

    public HowScoreEntity getHowScoreEntity() {
        return howScoreEntity;
    }

    public IntentParams setActInfo(ActInfo actInfo) {
        this.actInfo = actInfo;
        return this;
    }

    public ActInfo getActInfo() {
        return actInfo;
    }

    public IntentParams setActivity_id(int activity_id) {
        this.activity_id = activity_id;
        return this;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public IntentParams setShow_ask(boolean show_ask) {
        this.show_ask = show_ask;
        return this;
    }

    public IntentParams setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public int getPage() {
        return page;
    }

    public IntentParams setPage(int page) {
        this.page = page;
        return this;
    }

    public IntentParams setPosition(int position) {
        this.position = position;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public List<AnswerInfoEntity> getAnswerInfoEntities() {
        return answerInfoEntities;
    }

    public IntentParams setAnswerInfoEntities(List<AnswerInfoEntity> answerInfoEntities) {
        this.answerInfoEntities = answerInfoEntities;
        return this;
    }

    public IntentParams setBuy_now(int buy_now) {
        this.buy_now = buy_now;
        return this;
    }

    public Integer getBuy_now() {
        return buy_now;
    }

    public IntentParams setAddress_id(String address_id) {
        this.address_id = address_id;
        return this;
    }

    public IntentParams setAddress_list_type(int address_list_type) {
        this.address_list_type = address_list_type;
        return this;
    }

    public int getAddress_list_type() {
        return address_list_type;
    }

    public String getAddress_id() {
        return address_id;
    }

    public IntentParams setShip_number(String ship_number) {
        this.ship_number = ship_number;
        return this;
    }

    public IntentParams setShip_code(String ship_code) {
        this.ship_code = ship_code;
        return this;
    }

    public IntentParams setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }

    private AnswerInfoEntity answerInfoEntity;
    private QuestionEntity questionEntity;

    public AliAccountEntity getAlipayEntity() {
        return alipayEntity;
    }

    public IntentParams setAlipayEntity(AliAccountEntity alipayEntity) {
        this.alipayEntity = alipayEntity;
        return this;
    }

    public BankEntity getBankEntity() {
        return bankEntity;
    }

    public IntentParams setBankEntity(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
        return this;
    }

    public AnswerInfoEntity getAnswerInfoEntity() {
        return answerInfoEntity;
    }

    public IntentParams setAnswerInfoEntity(AnswerInfoEntity answerInfoEntity) {
        this.answerInfoEntity = answerInfoEntity;
        return this;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public IntentParams setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
        return this;
    }

    public boolean isScrollToTop() {
        return scrollToTop;
    }

    public IntentParams setScrollToTop(boolean scrollToTop) {
        this.scrollToTop = scrollToTop;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public IntentParams setUrl(String url) {
        this.url = url;
        return this;
    }

    public boolean isneeedDownload() {
        return isneeedDownload;
    }

    public IntentParams setIsneeedDownload(boolean isneeedDownload) {
        this.isneeedDownload = isneeedDownload;
        return this;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public IntentParams setSeller_id(String seller_id) {
        this.seller_id = seller_id;
        return this;
    }

    public String getSearch_type() {
        return search_type;
    }

    public IntentParams setSearch_type(String search_type) {
        this.search_type = search_type;
        return this;
    }

    public String getKey_word() {
        return key_word;
    }

    public IntentParams setKey_word(String key_word) {
        this.key_word = key_word;
        return this;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public IntentParams setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
        return this;
    }

    public String getTask_id() {
        return task_id;
    }

    public IntentParams setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public IntentParams setBrand_id(String brand_id) {
        this.brand_id = brand_id;
        return this;
    }

    public String getHtml() {
        return html;
    }

    public IntentParams setHtml(String html) {
        this.html = html;
        return this;
    }

    public List<SpuInfoEntity> getSpuInfoEntities() {
        return spuInfoEntities;
    }

    public IntentParams setSpuInfoEntities(List<SpuInfoEntity> spuInfoEntities) {
        this.spuInfoEntities = spuInfoEntities;
        return this;
    }

    public SpuInfoEntity getSpuInfoEntity() {
        return spuInfoEntity;
    }

    public String getGo_url_type() {
        return go_url_type;
    }

    public IntentParams setGo_url_type(String go_url_type) {
        this.go_url_type = go_url_type;
        return this;
    }

    public IntentParams setSpuInfoEntity(SpuInfoEntity spuInfoEntity) {
        this.spuInfoEntity = spuInfoEntity;
        return this;
    }

    public List<AttrName> getAttrNames() {
        return attrNames;
    }

    public IntentParams setAttrNames(List<AttrName> attrNames) {
        this.attrNames = attrNames;
        return this;
    }

    public String getModule_id() {
        return module_id;
    }

    public IntentParams setModule_id(String module_id) {
        this.module_id = module_id;
        return this;
    }

    public String getSpu_type() {
        return spu_type;
    }

    public IntentParams setSpu_type(String spu_type) {
        this.spu_type = spu_type;
        return this;
    }

    public ArrayList<CategoryModel> getCategoryModels() {
        return categoryModels;
    }

    public IntentParams setCategoryModels(ArrayList<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
        return this;
    }

    public ShareModel getShareModel() {
        return share_model;
    }

    public IntentParams setShareModel(ShareModel share_model) {
        this.share_model = share_model;
        return this;
    }

    public String getOrder_number() {
        return order_number;
    }
}
