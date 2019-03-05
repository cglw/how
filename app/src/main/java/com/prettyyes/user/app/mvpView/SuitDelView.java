package com.prettyyes.user.app.mvpView;

import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.api
 * Author: SmileChen
 * Created on: 2016/10/14
 * Description: Nothing
 */
public interface SuitDelView extends BaseView {
    void setSuitDelVpData(ArrayList<String> datas);

    void setGood(String tv);

    void setbad(String tv);

    void setCollect(boolean is);

    void setSuitGoosGv(ArrayList<SpuInfoEntity> datas);

    void setRecommdData(SpuInfoEntity spuInfoEntity);

//    void setRecommendVpData(ArrayList<RecommendGoodsEntity> datas);

    void setRecommendTv(String tv);

    void setTotalPrice(float price);

    void closeVpswipe();

    void setHeadView(String url, boolean famous_type);

    void setAct(String act);

    void setNickname(String nickname);

    void setDesc(String desc);

    void setSuitName(String suitname);

    void showProgressDialog(String msg);

    void dismisProgreDialog();

    int getActivityLayoutId();

}
