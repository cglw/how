package com.prettyyes.user.app.ui.person;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.view.dialog.ChangeAddressDialog;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.model.v8.AddressEntity;

import org.xutils.view.annotation.ViewInject;

public class ChangeAddressActivity extends BaseActivity {


    @ViewInject(R.id.edit_changeaddress_name)
    private EditTextDel edit_name;
    @ViewInject(R.id.edit_changeaddress_phone)
    private EditTextDel edit_phone;
    @ViewInject(R.id.edit_changeaddress_detailAddress)
    private EditTextDel edit_detailAddress;
    @ViewInject(R.id.tv_changeaddress_address)
    private TextView tv_address;
    @ViewInject(R.id.btn_changeaddress_confirm)
    private Button btn_confirm;

    public static int TYPE_CHANGE = 0;//修改地址
    public static int TYPE_ADD = 1;//添加新地址
    private String title;
    private AddressEntity data = new AddressEntity();
    private String name;
    private String mobile;
    private String address;
    private String detailAddress;
    private int is_default = 0;//是否是默认
    private int current_type;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        current_type = getIntent().getIntExtra("type", 0);
        if (TYPE_ADD== current_type) {
            title = "添加地址";
        } else if (current_type == TYPE_CHANGE) {
            title = "修改地址";
            data = (AddressEntity) getIntent().getSerializableExtra("info");
            name = data.getGet_uname();
            mobile = data.getMobile();
            address = data.getProvince_name() + " " + data.getArea_name() + " " + data.getCity_name();
            detailAddress = data.getDetail();
        }

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_change_address;
    }

    @Override
    protected void initViews() {
        setActivtytitle(title);
        showBack();
        edit_name.setText(name);
        edit_detailAddress.setText(detailAddress);
        tv_address.setText(address);
        edit_phone.setText(mobile);
    }

    @Override
    protected void setListener() {
        super.setListener();
        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_city();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddress();
            }
        });
    }


    @Override
    protected void loadData() {

    }

    public void select_city() {
        ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
                ChangeAddressActivity.this);
        mChangeAddressDialog.show();
        mChangeAddressDialog
                .setAddresskListener(new ChangeAddressDialog.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area, int provinceId, int cityId, int areaId) {
                        tv_address.setText(province + " " + city + " " + area);
                        data.setProvince_id(provinceId);
                        data.setArea_id(areaId);
                        data.setCity_id(cityId);
                        data.setProvince_name(province);
                        data.setArea_name(area);
                        data.setCity_name(city);
                    }
                });

    }

    /**
     * 保存地址
     */
    public void saveAddress() {
        data.setDetail(edit_detailAddress.getText().toString());
        data.setMobile(edit_phone.getText().toString());
        data.setGet_uname(edit_name.getText().toString());
        if (current_type== TYPE_CHANGE) {
            editAddress();
        } else {
            addAddress();
        }
    }

    /**
     * 添加新地址
     */
    private void addAddress() {
        new OtherApiImpl().clientSaveAddress(getUUId(), data.getProvince_id(), data.getCity_id(), data.getArea_id(), data.getProvince_name(), data.getCity_name(), data.getArea_name(), data.getDetail(), data.getMobile(), is_default, data.getGet_uname(), new NetReqCallback() {
            @Override
            public void apiRequestFail(String message,String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(Object apiResponse,String method) {
                showToastShort("添加地址成功");
                sendBrodcast(Constant.AddressAddSuccess);
                finish();

            }
        });
    }

    /**
     * 编辑地址
     */
    public void editAddress() {
        AddressEntity a = data;
        new OtherApiImpl().clientEditeAddress(getUUId(), a.getA_id(), a.getProvince_id(), a.getCity_id(), a.getArea_id(), a.getProvince_name(), a.getCity_name(), a.getArea_name(), a.getDetail(), a.getMobile(), Integer.parseInt(a.getIs_default()), a.getGet_uname(), 0, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message,String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(Object apiResponse,String method) {
                finish();
                sendBrodcast(Constant.AddressAddSuccess);
            }
        });
    }

}
