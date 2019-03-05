package com.prettyyes.user.app.ui.order;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.ShipCompangRequest;
import com.prettyyes.user.app.adapter.order.ShipCompanyAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.LetterView;
import com.prettyyes.user.app.view.lvandgrid.StickyDecoration;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.ShipEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

public class ShipCompanyActivity extends SingleListActivity<List<ShipEntity>> {

    @ViewInject(R.id.letterview)
    LetterView letterView;
    @ViewInject(R.id.tv_letter)
    TextView tv_letter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_ship_company;
    }

    StickyDecoration decor;

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_shipCompany);
        letterView.setmLetter(tv_letter);
        decor = new  StickyDecoration(getApplicationContext());
        decor.setShipCompantAdapter((ShipCompanyAdapter) adapter);
        swipeRv.getRecycleView().addItemDecoration(decor);
        swipeRv.getSwpie().setEnabled(false);
    }

    @Override
    protected void requestData(int page) {
        new ShipCompangRequest().post(this);
    }

    @Override
    public void apiRequestSuccess(List<ShipEntity> shipEntities, String method) {
        super.apiRequestSuccess(shipEntities, method);
        swipeRv.loadingEnd();
    }

    @Override
    public List getListData(List<ShipEntity> shipEntities) {
        return shipEntities;
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new ShipCompanyAdapter(this);
    }

    @Override
    protected void setListener() {
        super.setListener();
//        mSubscription = AppRxBus.getInstance().toObservable(String.class).subscribe(new RxAction1<String>() {
//            @Override
//            public void callback(String s) {
//                if(!s.equals("#")) {
//                    int selectPosition = 0;
//                    for (int i = 0; i < adapter.getDataCount(); i++) {
//                        if (((ShipCompanyAdapter) adapter).getItemData(i).getLetter().equals(s.toLowerCase())) {
//                            selectPosition = i;
//                            break;
//                        }
//                    }
//                    scrollPosition(selectPosition);
//                }
//
//            }
//        });
//
        mSubscription=AppRxBus.getInstance().subscribeEvent(new RxCallback<String>() {
            @Override
            protected void back(String s) {
                if(!s.equals("#")) {
                    int selectPosition = 0;
                    for (int i = 0; i < adapter.getDataCount(); i++) {
                        if (((ShipCompanyAdapter) adapter).getItemData(i).getLetter().equals(s.toLowerCase())) {
                            selectPosition = i;
                            break;
                        }
                    }
                    scrollPosition(selectPosition);
                }

            }
        });

    }

    private void scrollPosition(int index) {
        LinearLayoutManager layout = (LinearLayoutManager) swipeRv.getRecycleView().getLayoutManager();
        int firstPosition = layout.findFirstVisibleItemPosition();
        int lastPosition = layout.findLastVisibleItemPosition();
        if (index <= firstPosition) {
            swipeRv.getRecycleView().scrollToPosition(index);
        } else if (index <= lastPosition) {
            int top = swipeRv.getRecycleView().getChildAt(index - firstPosition).getTop();
            swipeRv.getRecycleView().scrollBy(0, top);
        } else {
            swipeRv.getRecycleView().scrollToPosition(index);
        }
    }


}
