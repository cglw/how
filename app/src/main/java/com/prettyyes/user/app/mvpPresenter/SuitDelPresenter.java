package com.prettyyes.user.app.mvpPresenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.CollectRequest;
import com.prettyyes.user.api.netXutils.response.CollectRes;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.mvpView.SuitDelView;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.kol.KolInfoActivity;
import com.prettyyes.user.app.view.ClipViewPager;
import com.prettyyes.user.app.view.indictator.CircleIndicator;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;

import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.core.utils.AppUtil.showToastShort;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.presenter
 * Author: SmileChen
 * Created on: 2016/10/14
 * Description: Nothing
 */
public class SuitDelPresenter {
    private SuitDelView suitDelView;
    private SpuInfoEntity spuInfoEntity;
    private String seller_id;

    public SuitDelPresenter(SuitDelView suitDelView) {
        this.suitDelView = suitDelView;
    }

    public void setData(SpuInfoEntity spuInfoEntity) {
        this.spuInfoEntity = spuInfoEntity;

        if (spuInfoEntity.getIs_like() != null)
            suitDelView.setCollect(spuInfoEntity.getIs_like().equals("1"));

        ArrayList a = (ArrayList) StringUtils.getSplitArray(spuInfoEntity.getSmall_img());
        suitDelView.setSuitDelVpData(a);
        suitDelView.setGood(spuInfoEntity.getBright_point());
        suitDelView.setbad(spuInfoEntity.getRubbish());
        suitDelView.setSuitGoosGv((ArrayList<SpuInfoEntity>) spuInfoEntity.getUnit_detail());
        suitDelView.setAct(spuInfoEntity.getSeller_info().getAce_txt());
        suitDelView.setNickname(spuInfoEntity.getSeller_info().getNickname());
        suitDelView.setDesc(spuInfoEntity.getSpu_desc());
        suitDelView.setHeadView(spuInfoEntity.getSeller_info().getHeadimg(), spuInfoEntity.getSeller_info().isFamous());
        suitDelView.setSuitName(spuInfoEntity.getSpu_name());
        suitDelView.setRecommdData(spuInfoEntity);

        seller_id = spuInfoEntity.getSeller_info().getSeller_id();


    }


    public void goKolInfoActivity() {
        if (seller_id == null) {
            return;
        }
        Intent i = new Intent(suitDelView.getThis(), KolInfoActivity.class);
        i.putExtra("seller_id", seller_id);
        suitDelView.getThis().nextActivity(i);
    }

    public void chatWithSeller() {
        if (seller_id == null || spuInfoEntity == null) {
            return;
        }
        DataCenter.CURRENT_ChatFromType = DataCenter.ChatFromType.SKU;
//        AccountDataRepo.appacountinstance.chatWithSeller(suitDelView.getThis(), seller_id + "", spuInfoEntity.getMain_img(),
//                spuInfoEntity.getSpu_name(),
//                spuInfoEntity.getShare_url(),
//                spuInfoEntity.getSpu_price()
//                , spuInfoEntity.getModule_id());
    }

    public String getTv_recomment(int position) {
        return spuInfoEntity.getRecommend_goods().get(position).getGoods_name();
    }


    public void initIndicator(LinearLayout lin, ClipViewPager vp, CircleIndicator.Gravity gravity, Context context, int number) {
        for (int i = 0; i < lin.getChildCount(); i++) {
            if (lin.getChildAt(i) instanceof CircleIndicator) {
                lin.removeView(lin.getChildAt(i));
            }
        }
        CircleIndicator circleIndicator = new CircleIndicator(context);
        lin.addView(circleIndicator);
        LinearLayout.LayoutParams layparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(context, 6));
        circleIndicator.setIndicatorMargin(DensityUtil.dip2px(context, 7));
        circleIndicator.setIndicatorLayoutGravity(gravity);
        circleIndicator.setIndicatorRadius(DensityUtil.dip2px(context, 3));
        circleIndicator.setLayoutParams(layparam);
        circleIndicator.setIndicatorSelectedBackground(context.getResources().getColor(R.color.black));
        circleIndicator.setIndicatorBackground(Color.rgb(216, 216, 216));
        circleIndicator.setIndicatorMode(CircleIndicator.Mode.OUTSIDE);
        circleIndicator.setViewPager(vp, number);
    }

    public void loadHead(String url, ImageView imageView) {
        ImageLoadUtils.loadHeadImg(suitDelView.getThis(), url, imageView);
    }

    public void share() {
        if (BaseApplication.UUID == null) {
            RegisterActivity.getRegister(suitDelView.getThis());
            return;
        }
        if (spuInfoEntity == null) {
            suitDelView.showFailedError("还未获取到分享信息");
            return;
        }
        if (spuInfoEntity != null)
            new ShareHandler(suitDelView.getThis()).setRes(spuInfoEntity.getShare_model()
                    , new ShareHandler.ShareCallback() {
                        @Override
                        public void share(boolean issuccess) {
                            ShareHandler.postShare(TYPE_SUIT, spuInfoEntity.getModule_id(), spuInfoEntity.getSeller_info().getSeller_id() + "");
                        }
                    }).shareAtWindow(suitDelView.getThis().getRootView());
        postShareData();
    }

    private void postShareData() {
    }


    public void collect(final boolean is, String suit_id) {
        if (suitDelView.getThis().getUUId() == null) {
            RegisterActivity.getRegister(suitDelView.getThis());
            return;
        }
        new CollectRequest().setModule_id(suit_id).setSpu_type(TYPE_SUIT).setIs_like(is ? "1" : "0").post(new NetReqCallback<CollectRes>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(CollectRes collectRes, String method) {
                if (collectRes.getInfo().equals("1")) {
                    showToastShort("收藏成功");
                } else {
                    showToastShort("取消收藏成功");
                }

            }
        });


    }


    public static class SelectModel {
        private int unit_id;
        private String title;
        private float price;
        private int num = 1;
        private String uint_img;
        private String size;

        public SelectModel(int uint_id, String title, float price, String uint_img, String size) {
            this.unit_id = uint_id;
            this.title = title;
            this.price = price;
            this.uint_img = uint_img;
            this.size = size;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getUint_img() {
            return uint_img;
        }

        public void setUint_img(String uint_img) {
            this.uint_img = uint_img;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(int unit_id) {
            this.unit_id = unit_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }


}
