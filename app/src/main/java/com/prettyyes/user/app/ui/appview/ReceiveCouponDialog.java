package com.prettyyes.user.app.ui.appview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.CouponAdapter;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.coupon.CouponInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/6/2.
 */

public class ReceiveCouponDialog extends Dialog {


    private Button btn_go_coupon;
    private RecyclerView rv;

    public ReceiveCouponDialog(final Context context, final List<CouponInfoEntity> datas) {
        super(context, R.style.simple_dialog);
        setContentView(R.layout.dialog_receive_coupon);


        btn_go_coupon = (Button) findViewById(R.id.btn_go_coupon);
        rv = (RecyclerView) findViewById(R.id.rv);
        CouponAdapter couponAdapter = new CouponAdapter(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(couponAdapter);
        couponAdapter.addAll(datas);

        btn_go_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goCouponListActivity(getContext());
                dismiss();
            }
        });
    }

    @Override
    public void show() {
        if (btn_go_coupon != null)
            if (isShowBtn) {
                btn_go_coupon.setVisibility(View.VISIBLE);
            } else {
                btn_go_coupon.setVisibility(View.GONE);

            }
        super.show();
    }

    public boolean isShowBtn = true;

    public ReceiveCouponDialog setShowBtn(boolean showBtn) {
        isShowBtn = showBtn;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        Point size = new Point();
        d.getSize(size);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = size.x; //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);
    }
}
