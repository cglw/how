package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.core.containter.JumpPageManager;

/**
 * Created by chengang on 2017/7/10.
 */

public class ReplySelectPupop extends AbsPupopWindow {
    public ReplySelectPupop(Activity context) {
        super(context);
    }

    private LinearLayout ll_brand;
    private LinearLayout ll_new_sku;
    private LinearLayout ll_sku;


    @Override
    public void bindView(View view) {
        ll_brand = (LinearLayout) view.findViewById(R.id.ll_brand);
        ll_new_sku = (LinearLayout) view.findViewById(R.id.ll_new_sku);
        ll_sku = (LinearLayout) view.findViewById(R.id.ll_sku);
        ll_new_sku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goTemplateActivity(activity);
                dismiss();

            }
        });
        ll_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBrandListActivty(activity);
                dismiss();


            }
        });
        ll_sku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSpuListActivity(activity);
                dismiss();

            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.popup_reply_select;
    }

    @Override
    public int getLayoutHeight() {
        return 0;
    }

    @Override
    public void setHeightBy() {
        super.setHeightBy();
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
