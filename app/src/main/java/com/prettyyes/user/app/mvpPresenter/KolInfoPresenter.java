package com.prettyyes.user.app.mvpPresenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.api.netXutils.ProgressCallback;
import com.prettyyes.user.app.mvpView.KolInfoView;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.app.ui.spu.SuitListActivity;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.other.ShareAdd;
import com.prettyyes.user.model.user.UserSellerIntroducefromuser;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.presenter
 * Author: SmileChen
 * Created on: 2016/10/20
 * Description: Nothing
 */
public class KolInfoPresenter {
    private KolInfoView kolInfoView;
    private UserApiImpl userApi;
    private int update_kol_img = 0;

    public KolInfoPresenter(KolInfoView kolInfoView) {
        this.kolInfoView = kolInfoView;
        userApi = new UserApiImpl();
    }

    public void getNetData(String uuid, int seller_id) {
        userApi.userSellerIntroducefromuser(uuid, seller_id, new NetReqCallback<UserSellerIntroducefromuser>() {
            @Override
            public void apiRequestFail(String message, String method) {
                kolInfoView.showFailedError(message);
                kolInfoView.getThis().loadFail();
            }

            @Override
            public void apiRequestSuccess(UserSellerIntroducefromuser data, String method) {
                kolInfoView.getThis().loadSuccess();
                bindData(data);
                KolInfoPresenter.this.seller_id = data.getSeller_id();
                KolInfoPresenter.this.nickname = data.getNickname();

            }


        });
    }

    void postShare(int seller_id) {
        new OtherApiImpl().sellerShareIncrement(seller_id + "", kolInfoView.getThis().getUUId(), "kol", seller_id + "", new NetReqCallback<ShareAdd>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(ShareAdd shareAdd, String method) {
                kolInfoView.setShareNum(shareAdd.getIncrement());
            }

        });
    }

    public void setListImage(String url, ImageView image) {
        ImageLoadUtils.loadListimg(kolInfoView.getThis(), url, image);
    }

    private void bindData(UserSellerIntroducefromuser data) {
        //    update_kol_img = data.getUpdate_kol_img();
        kolInfoView.setIngredient(data.getIngredient());
        kolInfoView.setHeadView(data.getHeadimg(), data.getFamous_type());
        kolInfoView.setUser_security(data.getUser_security());
        kolInfoView.setName(data.getNickname());
        kolInfoView.setInfo(data.getAce_txt());
        kolInfoView.setGradeTitle(data.getGrade_title());
        ArrayList<String> a = new ArrayList();
        for (int i = 0; i < data.getTags().size(); i++) {
            a.add(data.getTags().get(i).getTag_name());
        }
        kolInfoView.setTag(a);
        kolInfoView.setMormalNum(data.getZeze_num(), data.getPei_num(), data.getFavourite_num(), data.getShare_num());

        kolInfoView.setSuitNum(data.getNum_list().get(0).getNum(), data.getNum_list().get(1).getNum(), data.getNum_list().get(2).getNum(), data.getNum_list().get(0).getNum());
        kolInfoView.setContentProportion(data.getQuality_num(), data.getHome_task_num());
        if (data.getTask_num() == 0) {
            kolInfoView.setCircleView(0, 0, 0);
        } else {
            kolInfoView.setCircleView(100 * data.getQuality_num() * 1.0 / data.getTask_num(),
                    100 * data.getHome_task_num() * 1.0 / data.getTask_num(), data.getTask_num()
            );
        }
        ArrayList<String> b = new ArrayList<String>();
        for (int i = 0; i < data.getSuit_img().size(); i++) {
            b.add(data.getSuit_img().get(i).getCover_img());
        }
        kolInfoView.setImageList(b);

        kolInfoView.setNumListName(new StringBuilder().append(data.getNum_list().get(0).getNum()).toString(),

                new StringBuilder().append(data.getQuality_num()).toString()
                , new StringBuilder().append(data.getHome_task_num()).toString());
        kolInfoView.setCollecttion(data.getIs_like().equals("yes"));
        share_model = data.getShare_model();
    }

    private ShareModel share_model;

    private int seller_id;
    private String nickname;

    public void ask(int seller_id) {
        if (seller_id == 0) {
            kolInfoView.showFailedError("未获取到kol的账号");
            return;
        }
        SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
        sellerInfoEntity.setId(seller_id);
        sellerInfoEntity.setNickname(nickname);
        AskActivity.goAskActivity(kolInfoView.getThis(), sellerInfoEntity);
    }
//这是scrollview的

    public Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取scrollview实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(ContextCompat.getColor(kolInfoView.getThis(), R.color.theme_darkgreen));
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }


    public void share(final int seller_id, int layoutid) {

        if (kolInfoView.getThis().getUUId() == null) {
            RegisterActivity.getRegister(kolInfoView.getThis());
            return;
        }

        if (share_model != null)
            new ShareHandler(kolInfoView.getThis()).setRes(share_model, new ShareHandler.ShareCallback() {
                @Override
                public void share(boolean issuccess) {
                    postShare(seller_id);

                }
            }).shareAtWindow(layoutid);

    }

    public void uploadimg(String filepath) {
        userApi.userUploadImg(kolInfoView.getThis().getUUId(), "image", filepath, new ProgressCallback() {
            @Override
            public void onFail(String paramString1, String paramString2) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String result) {

            }
        });
    }

    public void setImage(String url, ImageView imageView) {
        ImageLoadUtils.loadHeadImg(kolInfoView.getThis(), url, imageView);
    }

    public void favorite(final TextView tv, String uuid, int seller_id, final boolean ischeck) {
        tv.setEnabled(false);
        int islike = 0;
        if (ischeck)
            islike = 1;
        new OtherApiImpl().AceLike(uuid, seller_id, islike, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {

                kolInfoView.showFailedError(ischeck ? "收藏失败" : "取消收藏失败");
                tv.setEnabled(true);

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {
                kolInfoView.showFailedError(ischeck ? "收藏成功" : "取消收藏成功");
                kolInfoView.setCollecttion(ischeck);
                if (tv.getText().equals(""))
                    return;
                if (ischeck)
                    tv.setText(new StringBuilder().append((Integer.parseInt(tv.getText().toString()) + 1)));
                else
                    tv.setText(new StringBuilder().append((Integer.parseInt(tv.getText().toString()) - 1)));
                tv.setEnabled(true);

            }
        });
    }


    public void gokolSuitList(int seller_id) {
        Intent intent = new Intent(kolInfoView.getThis(), SuitListActivity.class);
        intent.putExtra("seller_id", seller_id);
        kolInfoView.getThis().nextActivity(intent);
    }
}
