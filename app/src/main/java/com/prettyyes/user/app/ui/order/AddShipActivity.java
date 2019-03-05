package com.prettyyes.user.app.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.GetShipCompanyByIdRequest;
import com.prettyyes.user.api.netXutils.requests.SetOrderShipRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddShipSuccessEvent;
import com.prettyyes.user.core.event.SelectShipCompanyEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.PermissionUtils;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.ShipEntity;
import com.ta.utdid2.android.utils.StringUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.List;

public class AddShipActivity extends BaseActivity {


    private ImageView img_go_zxing;
    private EditTextDel etv_ship_number;
    private RelativeLayout view_go_ship_commany;
    private TextView tv_shipcompay_name;

    private String ship_code = "";
    private String order_number;

    private void bindViews() {
        img_go_zxing = (ImageView) findViewById(R.id.img_go_zxing);
        etv_ship_number = (EditTextDel) findViewById(R.id.etv_ship_number);
        view_go_ship_commany = (RelativeLayout) findViewById(R.id.view_go_ship_commany);
        tv_shipcompay_name = (TextView) view_go_ship_commany.getChildAt(0);
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParams = JumpPageManager.getManager().getIntentParmas(getThis());
        if (intentParams != null)
            order_number = intentParams.order_number;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_add_ship;
    }

    @Override
    protected void initViews() {
        bindViews();
        setActivtytitle(R.string.title_activity_addship);

    }


    @Override
    protected void setListener() {

        img_go_zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.requestSinglePermission(getThis(), new PermissionUtils.PermissionGrant() {
                    @Override
                    public void onPermissionGranted(int requestCode) {
                        scan();

                    }
                }, PermissionUtils.PERMISSION_CAMERA);
            }
        });
        view_go_ship_commany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goShipCompanyActivity(getThis());

            }
        });

        setRightTvListener("发货", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etv_ship_number.getText().toString())) {
                    showToastShort("运单号不允许为空");
                    return;
                }
                String str = tv_shipcompay_name.getText().toString();
                if (StringUtils.isEmpty(str) || str.equals("获取失败") || str.equals("请输入物流单号")) {
                    showToastShort("请选择正确的物流公司");
                    return;
                }


                new SetOrderShipRequest().setOrder_id(order_number)
                        .setShip_company(ship_code)
                        .setShip_number(etv_ship_number.getText().toString())
                        .post(new NetReqCallback<Object>() {
                            @Override
                            public void apiRequestFail(String message, String method) {
                                showToastShort(message);
                            }

                            @Override
                            public void apiRequestSuccess(Object o, String method) {
                                showToastShort("添加物流信息成功");
                                AppRxBus.getInstance().post(new AddShipSuccessEvent(order_number));
                                finish();

                            }
                        });
            }
        });

        etv_ship_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkShipCompany();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       mSubscription= AppRxBus.getInstance().subscribeEvent(new RxCallback<SelectShipCompanyEvent>() {
            @Override
            protected void back(SelectShipCompanyEvent obj) {
                tv_shipcompay_name.setText(obj.getShipCompany_name());
                ship_code = obj.getShip_code();
            }
        });

    }

    @Override
    protected void loadData() {

    }

    //扫描二维码
    //https://cli.im/text?2dd0d2b267ea882d797f03abf5b97d88二维码生成网站
    public void scan() {
        startActivityForResult(new Intent(this, CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result = bundle.getString("result");
                etv_ship_number.setText(result);
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, new PermissionUtils.PermissionGrant() {
            @Override
            public void onPermissionGranted(int requestCode) {
                scan();
                checkShipCompany();
            }
        });
    }

    private void checkShipCompany() {

        new GetShipCompanyByIdRequest().setOrder_number(etv_ship_number.getText().toString().trim()).post(new NetReqCallback<List<ShipEntity>>() {
            @Override
            public void apiRequestFail(String message, String method) {
                tv_shipcompay_name.setText(message);
            }

            @Override
            public void apiRequestSuccess(List<ShipEntity> shipEntities, String method) {

                if (shipEntities == null || shipEntities.size() < 1)
                    return;
                ShipEntity shipEntity = shipEntities.get(0);
                if (!StringUtils.isEmpty(shipEntity.getShipperName()))
                    tv_shipcompay_name.setText(shipEntity.getShipperName());
                ship_code = shipEntity.getShipperCode();
            }
        });

//        GetShipCompanyByIdApi getShipCompanyByIdApi = new GetShipCompanyByIdApi();
//        getShipCompanyByIdApi.setOrder_number(etv_ship_number.getText().toString());
//        new HttpManager(new HttpOnNextListener<String>() {
//            @Override
//            public void onNext(String resulte, String mothead) {
//
//                try {
//
//                    org.json.JSONArray jsonarray = new org.json.JSONArray(resulte);
//
//                    for (int i = 0; i < jsonarray.length(); i++) {
//                        JSONObject o = (JSONObject) jsonarray.get(i);
//                        String shipperName = o.optString("ShipperName");
//                        if (!StringUtils.isEmpty(shipperName))
//                            tv_shipcompay_name.setText(shipperName);
//                        ship_code = o.optString("ShipperCode");
//                        return;
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onError(ApiException e) {
//                tv_shipcompay_name.setText(e.getMessage());
//
//            }
//        }, this).doHttpDeal(getShipCompanyByIdApi);
    }
}
