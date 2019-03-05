package com.prettyyes.user.app.ui.order;

import android.widget.ListView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.app.adapter.ShipAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.model.order.OrderShipInfo;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;

public class ShipStateActivity extends BaseActivity {

    @ViewInject(R.id.tv_shipActvity_info)
    private TextView tv_shipinfo;
    @ViewInject(R.id.lv_shipActivty_shiplist)
    private ListView lv_ship;

    private String ordernum;
    private ShipAdapter adapter;

    @Override
    protected void initVariables() {
        ordernum = getIntent().getStringExtra("ordernum");
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_ship_state;
    }

    @Override
    protected void initViews() {
        setActivtytitle("物流信息");
        showBack();
        adapter = new ShipAdapter(0, new ArrayList<OrderShipInfo.InfoEntity>(), this);
        lv_ship.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        getUUid(0);

    }

    @Override
    public void doSomethingByUUid(int type) {
        new OrderApiImpl().OrderGetShipInfo(uuid_act, ordernum, new NetWorkCallback() {
            @Override
            public void apiRequestFail(String code, String message) {
                loadFail();
            }

            @Override
            public void apiRequestSuccess(ApiResContent apiResponse) {
                loadSuccess();
                if (apiResponse.isSuccess()) {
                    OrderShipInfo orderShipInfo = (OrderShipInfo) apiResponse.getExtra();
                    setData(orderShipInfo);
                    Collections.reverse(orderShipInfo.getInfo());
                    adapter.addAll((ArrayList<OrderShipInfo.InfoEntity>) orderShipInfo.getInfo());
                }
            }
        });
    }

    public void setData(OrderShipInfo data) {
        tv_shipinfo.setText(data.getShip_company() + " 物流编号：" + data.getShip_number());
    }
}
