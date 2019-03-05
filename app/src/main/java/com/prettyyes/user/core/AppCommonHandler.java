package com.prettyyes.user.core;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.HowScoreRequest;
import com.prettyyes.user.api.netXutils.requests.TaskNewUserListReq;
import com.prettyyes.user.model.common.ListCommon;
import com.prettyyes.user.model.v8.HowScoreEntity;
import com.prettyyes.user.model.v8.TaskNewUserItemEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by chengang on 2018/2/2.
 */

public class AppCommonHandler {

    public static AppCommonHandler create() {
        return new AppCommonHandler();
    }

    public Observable checkHowScore() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter e) throws Exception {
                new HowScoreRequest().post(new NetReqCallback<HowScoreEntity>() {
                    @Override
                    public void apiRequestFail(String message, String method) {

                    }

                    @Override
                    public void apiRequestSuccess(HowScoreEntity howScoreEntity, String method) {

                        try {
                            float how_score = Float.parseFloat(howScoreEntity.getHow_score());
                            if(how_score>=300)
                                e.onNext(true);
                            else
                                e.onNext(false);

                        } catch (Exception ee) {

                        }
                    }
                });
            }
        });
    }

    public Observable checkCompleteNewUserTask() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
                new TaskNewUserListReq().post(new NetReqCallback<ListCommon<List<TaskNewUserItemEntity>>>() {
                    @Override
                    public void apiRequestFail(String message, String method) {

                    }

                    @Override
                    public void apiRequestSuccess(ListCommon<List<TaskNewUserItemEntity>> listListCommon, String method) {
                        int count = 0;
                        for (int i = 0; i < listListCommon.getList().size(); i++) {
                            boolean complete = listListCommon.getList().get(i).isComplete();
                            if (complete) {
                                count++;
                            } else {
                                e.onNext(false);
                                break;

                            }

                        }
                        if (count > 0 && (count == listListCommon.getList().size())) {
                            e.onNext(true);
                        }
                    }


                });
            }
        });
    }

}
