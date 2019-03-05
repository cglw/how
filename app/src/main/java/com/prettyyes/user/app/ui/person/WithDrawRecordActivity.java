package com.prettyyes.user.app.ui.person;

import com.prettyyes.user.api.netXutils.requests.WithDrawRecordRequest;
import com.prettyyes.user.api.netXutils.response.WithdrawRecordRes;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.event.ApplyWithDrawSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;

import java.util.List;

/**
 * Created by chengang on 2017/10/17.
 */

public class WithDrawRecordActivity extends SingleListActivity<WithdrawRecordRes> {


    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("提现记录");
//        mSubscription= AppRxBus.getInstance().toObservable(ApplyWithDrawSuccessEvent.class).subscribe(new Action1<ApplyWithDrawSuccessEvent>() {
//            @Override
//            public void call(ApplyWithDrawSuccessEvent applyWithDrawSuccessEvent) {
//               swipeRv.loadingFistEnter();
//            }
//        });
        mSubscription=AppRxBus.getInstance().subscribeEvent(new RxCallback<ApplyWithDrawSuccessEvent>() {
            @Override
            protected void back(ApplyWithDrawSuccessEvent obj) {
                swipeRv.loadingFistEnter();

            }
        });

    }

    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
        return super.setDrividHeightPx(3);
    }

    @Override
    protected void requestData(int page) {
        new WithDrawRecordRequest().setPage(page).post(this);
    }

    @Override
    public List getListData(WithdrawRecordRes withdrawRecordRes) {
        return withdrawRecordRes.getData();
    }

}
