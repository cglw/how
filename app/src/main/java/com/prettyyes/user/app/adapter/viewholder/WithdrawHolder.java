package com.prettyyes.user.app.adapter.viewholder;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.ApplyRichesRequest;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.event.ApplyWithDrawSuccessEvent;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.WithdrawEntity;

/**
 * Created by chengang on 2017/10/17.
 */

public class WithdrawHolder extends MutiTypeViewHolder<WithdrawEntity> {

    public WithdrawHolder(ViewGroup parent) {
        super(parent.getContext(), parent, R.layout.item_withdraw_history);
    }

    @Override
    public void bindData(WithdrawEntity modle, int position, RecyclerView.Adapter adpter) {
        if (modle.getIs_get_apply() == 2) {
            tv_title.setVisibility(View.VISIBLE);
            tv_time.setVisibility(View.GONE);
            btn_withdraw.setVisibility(View.VISIBLE);
            tv_state.setVisibility(View.GONE);

        } else {
            tv_title.setVisibility(View.GONE);
            tv_time.setVisibility(View.VISIBLE);
            btn_withdraw.setVisibility(View.GONE);
            tv_state.setVisibility(View.VISIBLE);
        }

        if (modle.getIs_get_apply() == 1) {
            tv_state.setText("成功提现");
            tv_state.setTextColor(ContextCompat.getColor(context,R.color.theme_red));
        } else if (modle.getIs_get_apply() == 0) {
            tv_state.setText("正在提现");
            tv_state.setTextColor(ContextCompat.getColor(context,R.color.yellow_add_cart));


        }


        tv_price.setText(StringUtils.getPrice(modle.getApply_money()));
        tv_time.setText(modle.getApply_time());
        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApplyRichesRequest().post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        AppUtil.showToastShort(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        AppRxBus.getInstance().post(new ApplyWithDrawSuccessEvent());
                        AppUtil.showToastShort("申请提现成功");
                    }
                });
            }
        });

    }

    @Override
    public void bindView() {
        tv_title = findViewById(R.id.tv_title);
        tv_price = findViewById(R.id.tv_price);
        tv_time = findViewById(R.id.tv_time);
        btn_withdraw = findViewById(R.id.btn_withdraw);
        tv_state = findViewById(R.id.tv_state);
    }

    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_time;
    private TextView tv_state;
    private Button btn_withdraw;

}
