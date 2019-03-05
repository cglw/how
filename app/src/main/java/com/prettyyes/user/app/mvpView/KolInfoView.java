package com.prettyyes.user.app.mvpView;

import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.api
 * Author: SmileChen
 * Created on: 2016/10/20
 * Description: Nothing
 */
public interface KolInfoView extends BaseView {

    void setHeadView(String url, int famous_type);

    void setName(String name);

    void setInfo(String info);

    void setGradeTitle(String gradeTitle);

    void setTag(ArrayList<String> tags);

    void setUser_security(String tv);

    void setMormalNum(int good, int bad, int collection, int shar);

    void setShareNum(int share);

    // void addMedal();
    void setSuitNum(int total, int normal, int taobao, int imgs);

    void setContentProportion(int good, int firstpageNum);

    void setCircleView(double progress, double progress_Inner, int total);

    void setImageList(ArrayList<String> data);

    void setNumListName(String name1, String name2, String name3);

    void setCollecttion(boolean isCollection);

    void changeCollectionNum();

    void setIngredient(List<String> datas);


    ScrollView getScrollview();

}
