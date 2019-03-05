package com.prettyyes.user.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddressSelectEvent;
import com.prettyyes.user.core.event.DeleteSelectAddress;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.AddressEntity;


/**
 * Created by chengang on 2017/7/28.
 */

public class InputAddressHolder extends MutiTypeViewHolder {


    public int addressid;

    public InputAddressHolder(View itemView, BaseActivity activity) {

        super(itemView);
        bindView();
        tv_checkorederAct_default.setVisibility(View.GONE);
//        activity.mSubscription = AppRxBus.getInstance().toObservable(Object.class).subscribe(new RxAction1<Object>() {
//            @Override
//            public void callback(Object o) {
//                if (o instanceof AddressSelectEvent) {
//
//                    bindData(((AddressSelectEvent) o).addressEntity);
//
//
//                } else if (o instanceof DeleteSelectAddress) {
//
//                }
//            }
//        });

        activity.mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AddressSelectEvent>() {
            @Override
            protected void back(AddressSelectEvent obj) {
                bindData(obj.addressEntity);
            }
        }, new RxCallback<DeleteSelectAddress>() {
            @Override
            protected void back(DeleteSelectAddress obj) {
                addressid = 0;
                tv_checkorederAct_default.setVisibility(View.GONE);
                tv_checkorderAct_name.setText("");
                tv_checkorderAct_address.setText("");
                tv_checkorderAct_phone.setText("");

            }
        });
        lin_checkorederAct_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpPageManager.getManager().goAddressListActivity(context, JumpPageManager.TYPE_SELECT, addressid + "");
            }
        });
    }

    @Override
    public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

    }

    public void bindData(AddressEntity o) {
        addressid = o.getA_id();
        if (o.getIs_default().equals("1"))
            tv_checkorederAct_default.setVisibility(View.VISIBLE);
        else
            tv_checkorederAct_default.setVisibility(View.GONE);
        tv_checkorderAct_address.setText(o.getDetail());
        tv_checkorderAct_name.setText(o.getGet_uname());
        tv_checkorderAct_phone.setText(o.getMobile());
    }


    @Override
    public void bindView() {
        lin_checkorederAct_edit = getView(R.id.lin_checkorederAct_edit);
        tv_checkorderAct_name = getView(R.id.tv_checkorderAct_name);
        tv_checkorderAct_phone = getView(R.id.tv_checkorderAct_phone);
        tv_checkorederAct_default = getView(R.id.tv_checkorederAct_default);
        tv_checkorderAct_address = getView(R.id.tv_checkorderAct_address);
    }

    private LinearLayout lin_checkorederAct_edit;
    private TextView tv_checkorderAct_name;
    private TextView tv_checkorderAct_phone;
    private TextView tv_checkorederAct_default;
    private TextView tv_checkorderAct_address;

}
