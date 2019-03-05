package com.prettyyes.user.app.mvpPresenter;

import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.api.netXutils.requests.SystemBulletScreenReq;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpView.AskActView;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.core.SPUtils;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.PostAskSuccessEvent;
import com.prettyyes.user.core.event.TopicAskEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.AlertMessageResponse;
import com.prettyyes.user.model.system.SystemBulletScreen;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/3/31.
 */

public class AskActPresenter {
    private static final String TAG = "AskActPresenter";
    private AskActView askActView;
    private QuestionEntity questionEntity;
    private String uuid;
    private ArrayList<SellerInfoEntity> seller_array;

    public QuestionEntity getQuestionEntity() {

        if (questionEntity == null)
            questionEntity = new QuestionEntity();
        return questionEntity;
    }


    public AskActPresenter(AskActView askActView) {
        this.askActView = askActView;
        this.uuid = askActView.getThis().getUUId();
        seller_array = new ArrayList<>();
    }

    public void postQuestion() {
        getPageData();

        String question = questionEntity.getQuestion();
        if (hash_tag != null)
            question = questionEntity.getQuestion().replaceFirst(hash_tag, "");
        if (question == null || question.trim().length() <= 0) {
            askActView.showFailedError("你需要输入你的问题");
            return;
        }
        if (askActView.getThis().getUUId() == null) {
            AppUtil.showToastShort("你需要先登录");
            RegisterActivity.getRegister(askActView.getThis());
            return;
        }
        try {
            imgs = askActView.getSelectPhoto().getImages();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.showToastShort(e.getMessage());
            return;
        }
        askActView.getThis().backActivityTopout();

        uploadtask();


    }

    public void setHashTag(QuestionEntity data) {
        if (data == null)
            return;
        hash_tag = "";

        if (!StringUtils.strIsEmpty(data.getTopic_hash_tag()))
            hash_tag = data.getTopic_hash_tag();
        else
            hash_tag = "";
        askActView.getQuestionView().setText(hash_tag);
        if (hash_tag != null && hash_tag.length() >= 1)
            askActView.getQuestionView().setSelection(hash_tag.length() - 1);
    }

    private String hash_tag = "";

    public void refreshFlowlayout(ArrayList<SellerInfoEntity> sellerInfo_Entity_list) {
        if (sellerInfo_Entity_list == null) {
            return;
        }
        askActView.getFlowly_Kol().removeAllViews();
        seller_array.clear();
        for (int i = 0; i < sellerInfo_Entity_list.size(); i++) {
            addKol(sellerInfo_Entity_list.get(i).getNickname());
            seller_array.add(sellerInfo_Entity_list.get(i));
        }
    }

    public void getPageData() {
        if (questionEntity == null)
            questionEntity = new QuestionEntity();
        questionEntity.setQuestion(askActView.getQuestionView().getText() + "");
        questionEntity.setIsopen((askActView.getIsOpenView().isChecked() ? 1 : 0));


    }

    private void addKol(String name) {
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(askActView.getThis());
        layoutParams.setMargins(0, 0, DensityUtil.dip2px(5), 0);
        tv.setLayoutParams(layoutParams);
        tv.setText("@" + name);
        tv.setTextSize(15);
        tv.setTextColor(ContextCompat.getColor(askActView.getThis(), R.color.yellow_dark));
        askActView.getFlowly_Kol().addView(tv);
    }

    private String imgs;

    private void uploadtask() {
        String at_kols = "";
        for (int i = 0; i < seller_array.size(); i++) {
            at_kols += seller_array.get(i).getId() + ";";
        }
        String question = questionEntity.getQuestion();
        if (hash_tag != null && hash_tag.length() > 0)
            question = questionEntity.getQuestion().replaceFirst(hash_tag, "");

        new TaskImpl().TaskAdd(uuid, questionEntity.getSence(),
                questionEntity.getMinprice(),
                questionEntity.getMaxprice(),
                question,
                imgs,
                "", questionEntity.getIsopen(),
                at_kols,
                questionEntity.getAct_id(), questionEntity.getTopic_id(),
                new NetReqCallback() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        askActView.showFailedError(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {

                        if (!StringUtils.strIsEmpty(questionEntity.getTopic_id()))
                            AppRxBus.getInstance().post(new TopicAskEvent());
                        else
                            ((BaseActivity) askActView).sendBrodcast(Constant.SendQuesTionSuccess);


                        askActView.showFailedError("发问上传成功");
                        if (ZBundleCore.getInstance().isTopActivity(MainActivity.class))
                            AppRxBus.getInstance().postDely(new PostAskSuccessEvent());

                        new SPUtils<QuestionEntity>().delete(QuestionEntity.class);

                        AlertMessageResponse.isNeedShow(o, DataCenter.CouponGetType.ASK);
                    }
                });
    }

    public ArrayList<SellerInfoEntity> getSeller_array() {
        return seller_array;
    }

    public void getBulletscreen() {
        SystemBulletScreenReq systemBulletScreenReq = new SystemBulletScreenReq();

        if (getQuestionEntity().getAct_id() != null && getQuestionEntity().getAct_id().length() > 0) {
            systemBulletScreenReq.setTypeAct().setAct_id(getQuestionEntity().getAct_id());
        }
        systemBulletScreenReq.post(new NetReqCallback<SystemBulletScreen>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(SystemBulletScreen systemBulletScreen, String method) {
                askActView.setBulletscreen(systemBulletScreen.getList().toArray(new String[systemBulletScreen.getList().size()]));

            }
        });

    }
}
