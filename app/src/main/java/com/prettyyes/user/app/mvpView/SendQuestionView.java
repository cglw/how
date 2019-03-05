package com.prettyyes.user.app.mvpView;

import android.widget.ImageView;

import com.prettyyes.user.app.view.imageview.CircleProgressImageView;
import com.prettyyes.user.app.view.progress.HorProgressView;
import com.prettyyes.user.app.view.tvbtnetv.MentionEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.api
 * Author: SmileChen
 * Created on: 2016/10/17
 * Description: Nothing
 */
public interface SendQuestionView extends BaseView {


    void tagsIsShow(boolean isvisiable);

    String getQuestion();

    void setQuestion(String question);

    String getSence();

    void setSence(String sence);

    void setImageListVisiable(int count);

    ImageView createImageView();


    List<CircleProgressImageView> getNeedUploadImgList();

    void setImageView(String url);

    int getImageViewWith();

    void setLeftTv(int leftnumCount);

    float getMinPrice();

    float getMaxPrice();

    void setPrice(float max, float min);

    void showProgress(int progress);

    void closeProgress();

    MentionEditText getQuestionEdit();


    HorProgressView getToastProgress();

    void setIsOpen(boolean isOpen);

    boolean getIsOpen();

    MentionEditText getSendQueEdit();

    String getAtUserIds();

    String getActId();

    void addTagToFlowLayout(ArrayList<String> tags);

    void setBulletscreen(String[] res);

    String getActName();
    String postQuestion();

}
