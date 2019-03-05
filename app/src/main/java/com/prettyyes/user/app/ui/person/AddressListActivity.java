package com.prettyyes.user.app.ui.person;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.app.adapter.AddressAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.DeleteSelectAddress;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.other.ClientGetAddress;
import com.prettyyes.user.model.v8.AddressEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.prettyyes.user.core.containter.JumpPageManager.TYPE_SHOW;

public class AddressListActivity extends BaseActivity {

    @ViewInject(R.id.tv_addressshow_addmore)
    private TextView tv_more;
    @ViewInject(R.id.lv_addressshow_address)
    private ListView lv;

    private AddressAdapter adapter;


    private int current = TYPE_SHOW;
    private int Address_id = -1;

    @Override
    protected int bindLayout() {
        return R.layout.activity_address_show;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            current = intentParmas.getAddress_list_type();
            try {
                Address_id = Integer.parseInt(intentParmas.getAddress_id());
            } catch (Exception ee) {
            }
        }
    }

    @Override
    protected void initViews() {
        showBack();
        setActivtytitle("收货地址");
        if (current == 0)
            adapter = new AddressAdapter(this);
        else {
            adapter = new AddressAdapter(this, true);
        }
        lv.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    private void getData() {
        new OtherApiImpl().clientGetAddress(getUUId(), 2, new NetReqCallback<ClientGetAddress>() {
            @Override
            public void apiRequestFail(String message, String method) {
                loadFail();
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(ClientGetAddress clientGetAddress, String method) {
                loadSuccess();
                adapter.clear();
                for (int i = 0; i < clientGetAddress.getList().size(); i++) {
                    if (clientGetAddress.getList().get(i).getA_id() == Address_id) {
                        clientGetAddress.getList().get(i).setIsselect(true);
                        break;
                    }
                }
                adapter.addAll((ArrayList<AddressEntity>) clientGetAddress.getList());
            }


        });
    }

    @Override
    protected void setListener() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AddressListActivity.this, ChangeAddressActivity.class);
                AddressEntity a = adapter.get(position);
                intent.putExtra("info", a);
                intent.putExtra("type",ChangeAddressActivity.TYPE_CHANGE);

                startActivity(intent);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                domore(position);
                return true;
            }
        });
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressListActivity.this, ChangeAddressActivity.class);
                intent.putExtra("type",ChangeAddressActivity.TYPE_ADD);
                nextActivity(intent);
            }
        });

    }

    @Override
    protected void loadData() {
        getData();

    }

    private void domore(final int i) {
        alertView = new AlertView("更多操作", null, "取消", new String[]{"删除"},
                new String[]{"设为默认地址", "编辑"}, AddressListActivity.this, AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if (position == 0) {
                    editAddress(i, 1, 0);
                } else if (position == 1) {
                    editAddress(i, 0, 1);

                } else if (position == 2) {
                    Intent intent = new Intent(AddressListActivity.this, ChangeAddressActivity.class);
                    AddressEntity a = adapter.get(i);
                    intent.putExtra("info", a);
                    intent.putExtra("type",ChangeAddressActivity.TYPE_CHANGE);

                    startActivity(intent);
                }
            }
        });
        alertView.setCancelable(true).show();

    }

    /**
     * @param i
     * @param type      1删除 0正常
     * @param isdefault 1默认0正常
     */
    public void editAddress(int i, final int type, final int isdefault) {

        final AddressEntity a = adapter.get(i);
        new OtherApiImpl().clientEditeAddress(getUUId(), a.getA_id(), a.getProvince_id(), a.getCity_id(), a.getArea_id(), a.getProvince_name(), a.getCity_name(), a.getArea_name(), a.getDetail(), a.getMobile(), isdefault, a.getGet_uname(), type, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(Object apiResponse, String method) {
                if (type == 1) {
                    if (Address_id == a.getA_id()) {
                        //删除成功刷新
                        Intent intent = new Intent();
                        intent.setAction(Constant.AddressDelete);
                        sendBroadcast(intent);
                        AppRxBus.getInstance().post(new DeleteSelectAddress());
                    }
                }
                getData();
            }

        });
    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        super.setInentFliter(inentFliter);
        inentFliter.addAction(Constant.AddressAddSuccess);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
        super.handlerIntenter(context, intent);
        if (intent.getAction().equals(Constant.AddressAddSuccess))
            getData();
    }
}
