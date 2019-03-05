package com.prettyyes.user.app.mvpPresenter;

import android.content.Intent;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.mvpView.CollectKolView;
import com.prettyyes.user.app.ui.kol.KolInfoActivity;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.model.user.UserLikeseller;

import java.util.ArrayList;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/11/4
 * Description: Nothing
 */
public class CollectKolPresenter {

    private CollectKolView collectKolView;

    public CollectKolPresenter(CollectKolView collectKolView) {
        this.collectKolView = collectKolView;
    }

    public void getCollectKollist(int page) {
        new UserApiImpl().userLikeSeller(collectKolView.getThis().getUUId(), page, new NetReqCallback<UserLikeseller>() {
            @Override
            public void apiRequestFail(String message,String method) {
                collectKolView.getSwipe().loadingfail();
            }

            @Override
            public void apiRequestSuccess(UserLikeseller userLikeseller,String method) {
                if (userLikeseller == null) {
                    collectKolView.getSwipe().loadingEnd();
                    return;
                } else if (userLikeseller.getList().size() <MIN_PAGE_SIZE) {
                    collectKolView.getSwipe().loadingEnd();
                } else {
                    collectKolView.getSwipe().loadingSuccessHavedata();
                }
                collectKolView.getAdapter().addAll((ArrayList<UserLikeseller.ListEntity>) userLikeseller.getList());

            }
        });
    }

    public void goKolInfoActivity(int position) {
        if (!ClickUtils.isFastDoubleClick()) {
            Intent intent = new Intent(collectKolView.getThis(), KolInfoActivity.class);
            intent.putExtra("seller_id", collectKolView.getAdapter().get(position).getSeller_id());
            collectKolView.getThis().nextActivity(intent);
        }
    }
}
