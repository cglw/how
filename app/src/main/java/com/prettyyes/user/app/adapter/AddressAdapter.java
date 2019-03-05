package com.prettyyes.user.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.core.event.AddressSelectEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.v8.AddressEntity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter
 * Author: SmileChen
 * Created on: 2016/8/12
 * Description: Nothing
 */
public class AddressAdapter extends SpecilAbsAdapter<AddressEntity> {

    private boolean ishaveselect = false;

    public AddressAdapter(Context context) {
        super(R.layout.item_listview_address, context);
    }


    public AddressAdapter(Context context, boolean ishave) {
        super(R.layout.item_listview_address, context);
        this.ishaveselect = ishave;

    }

    @Override
    public void showData(ViewHolder vHolder, final AddressEntity data, final int position) {
        bindView(vHolder);

        tv_phone.setText(data.getGet_uname() + "  " + data.getMobile());
        tv_address.setText(data.getProvince_name() + " " + data.getArea_name() + " " + data.getCity_name() + "\n" + data.getDetail());
        if (data.getIs_default().equals("1")) {
            tv_default.setVisibility(View.VISIBLE);
        } else {
            tv_default.setVisibility(View.INVISIBLE);
        }
        View parent = (View) image_Select.getParent();

        if (ishaveselect) {
            parent.setVisibility(View.VISIBLE);
            if (data.isselect()) {
                image_Select.setImageResource(R.mipmap.ic_check_red_checked);
                Intent intent = new Intent();
                intent.putExtra("Address", data);
                intent.setAction(Constant.AddressSelect);
                context.sendBroadcast(intent);

            } else {
                image_Select.setImageResource(R.mipmap.ic_check_red_uncheck);
            }
        } else {
            parent.setVisibility(View.GONE);
        }
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < AddressAdapter.this.getData().size(); i++) {
                    if (i != position)
                        AddressAdapter.this.getData().get(i).setIsselect(false);
                }
                data.setIsselect(!data.isselect());
                AddressAdapter.this.notifyDataSetChanged();

                if (data.isselect()) {
                    Intent intent = new Intent();
                    intent.putExtra("Address", data);
                    intent.setAction(Constant.AddressSelect);
                    AppRxBus.getInstance().post(new AddressSelectEvent(data));
                    context.sendBroadcast(intent);
                    ((Activity)context).finish();
                }
            }
        });

    }

    public void bindView(ViewHolder viewHolder) {
        tv_phone = (TextView) viewHolder.getView(R.id.tv_addresslist_phone);
        tv_address = (TextView) viewHolder.getView(R.id.tv_addresslist_address);
        tv_default = (TextView) viewHolder.getView(R.id.tv_addresslist_defaut);
        image_Select = (ImageView) viewHolder.getView(R.id.img_address_select);
    }

    private TextView tv_phone;
    private TextView tv_address;
    private TextView tv_default;
    private ImageView image_Select;

}
