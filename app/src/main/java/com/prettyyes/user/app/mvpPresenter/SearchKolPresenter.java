package com.prettyyes.user.app.mvpPresenter;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.mvpView.SearchKolView;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/11/7
 * Description: Nothing
 */
public class SearchKolPresenter {
    private static final String TAG = "SearchKolPresenter";
    public SearchKolView searchKolView;

    public SearchKolPresenter(SearchKolView searchKolView) {
        this.searchKolView = searchKolView;
    }

    public void getSearchList(final int page, final ArrayList<SellerInfoEntity> input_list) {
        new UserApiImpl().useSellerSearchList(searchKolView.getSearchString(), page, new NetReqCallback<ArrayList<SellerInfoEntity>>() {


            @Override
            public void apiRequestFail(String message, String method) {
                searchKolView.getLv().loadingfail();
                if (page == 1)
                    searchKolView.getThis().loadFail();
            }

            @Override
            public void apiRequestSuccess(ArrayList<SellerInfoEntity> sellerInfoEntities, String method) {
                searchKolView.getThis().loadSuccess();
                if (sellerInfoEntities.size() < MIN_PAGE_SIZE) {
                    searchKolView.getLv().loadingEnd();
                } else {
                    searchKolView.getLv().loadingSuccessHavedata();
                }


                for (int i = 0; i < input_list.size(); i++) {

                    for (int j = 0; j < sellerInfoEntities.size(); j++) {
                        if (input_list.get(i).getId() == sellerInfoEntities.get(j).getId()) {
                            sellerInfoEntities.get(j).setIsselect(true);
                            break;
                        }
                    }

                }
                searchKolView.getAdapter().addAll(sellerInfoEntities);
            }
        });
    }
}
