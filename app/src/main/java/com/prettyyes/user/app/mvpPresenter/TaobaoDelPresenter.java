package com.prettyyes.user.app.mvpPresenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.app.mvpView.TaobaoDelView;
import com.prettyyes.user.app.view.indictator.CircleIndicator;
import com.prettyyes.user.app.view.pupopwindow.SelectGoodNumPopup;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.presenter
 * Author: SmileChen
 * Created on: 2016/10/14
 * Description: Nothing
 */
public class TaobaoDelPresenter {

    private OtherApiImpl otherApi;
    private TaobaoDelView taobaoDelView;
    private SpuInfoEntity taoBaoEntity;

    public TaobaoDelPresenter(TaobaoDelView taobaoDelView) {
        this.taobaoDelView = taobaoDelView;
        otherApi = new OtherApiImpl();
    }


    public void getNetData(SpuInfoEntity taoBaoEntity) {

        TaobaoDelPresenter.this.taoBaoEntity = taoBaoEntity;
        taobaoDelView.setVpData(getSmallimg(taoBaoEntity.getSmall_img()));
        taobaoDelView.setCoverImg(taoBaoEntity.getMain_img());
        if (taoBaoEntity.getSpu_price() != null)
            taobaoDelView.setPrice("RMB  " + taoBaoEntity.getSpu_price());
        taobaoDelView.setTitle(taoBaoEntity.getSpu_name());

    }

    public void setImageByUrl(ImageView view, String url) {
        ImageLoadUtils.loadListimg(view.getContext(), url, view);
    }

    private List<String> getSmallimg(String urls) {
        String[] dataArray;
        if (urls.contains(";")) {
            dataArray = StringUtils.convertStrToArray(urls + ";");
        } else {
            String url = urls;
            dataArray = new String[]{url};
        }
        ArrayList a = new ArrayList();
        for (int i = 0; i < dataArray.length; i++) {
            a.add(dataArray[i]);
        }
        return a;

    }


    public void initIndicator(LinearLayout linearLayout, Context context, ViewPager viewPager) {
        CircleIndicator circleIndicator = new CircleIndicator(context);
        linearLayout.addView(circleIndicator);
        LinearLayout.LayoutParams layparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(context, 6));
        circleIndicator.setIndicatorMargin(DensityUtil.dip2px(context, 5));
        circleIndicator.setIndicatorLayoutGravity(CircleIndicator.Gravity.CENTER);
        circleIndicator.setIndicatorRadius(DensityUtil.dip2px(context, 3f));
        circleIndicator.setLayoutParams(layparam);
        circleIndicator.setIndicatorSelectedBackground(context.getResources().getColor(R.color.black_pale));
        circleIndicator.setIndicatorBackground(context.getResources().getColor(R.color.grey_bg));
        circleIndicator.setIndicatorMode(CircleIndicator.Mode.OUTSIDE);
        circleIndicator.setViewPager(viewPager);
    }

    SelectGoodNumPopup selectGoodNumPopup = null;

    public ArrayList<SuitDelPresenter.SelectModel> getSelectDatas() {
        return datas;
    }

    private ArrayList<SuitDelPresenter.SelectModel> datas = new ArrayList<>();

    public void addSelect(int unit_id, String unit_name, float price, String unit_img) {
        SuitDelPresenter.SelectModel selectModel = new SuitDelPresenter.SelectModel(unit_id, unit_name, price, unit_img, "");
        datas.add(selectModel);
    }




    public void share() {
        if (taoBaoEntity != null)
            new ShareHandler(taobaoDelView.getThis()).setRes(taoBaoEntity.getShare_model(), new ShareHandler.ShareCallback() {
                @Override
                public void share(boolean issuccess) {
                    ShareHandler.postShare(TYPE_TAOBAO, taoBaoEntity.getModule_id() + "", taoBaoEntity.getSeller_info().getSeller_id() + "");

                }
            }).shareAtWindow(R.id.rel_taobao_base);
    }
}
