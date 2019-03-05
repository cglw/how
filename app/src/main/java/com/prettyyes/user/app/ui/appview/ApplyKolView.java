package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.core.containter.JumpPageManager;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by chengang on 2017/9/5.
 */

public class ApplyKolView extends AbsLinearlayoutView {

    public ApplyKolView(Context context) {
        super(context);
    }

    public ApplyKolView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_apply_kol;
    }

    @ViewInject(R.id.tv_content)
    public TextView tv_content;
    @ViewInject(R.id.btn_apply)
    public Button btn_apply;


    @Override
    public void initViews() {

    }

    public void category() {
        tv_content.setText(getContext().getString(R.string.apply_kol_category));
        btn_apply.setText("查看新手任务");

    }

    public void reward() {
        tv_content.setText(getContext().getString(R.string.apply_kol_reward));
        btn_apply.setText("查看新手任务");

    }

    public void best_new() {
        tv_content.setText(getContext().getString(R.string.apply_kol_best_new));
        btn_apply.setText("查看How值详情");

    }

    @Override
    public void setListener() {
        super.setListener();
        btn_apply.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Button) v).getText().toString().equals("查看新手任务"))
                    JumpPageManager.getManager().goTaskNewUserActivity(getContext());
                else {
                    JumpPageManager.getManager().goMyMedalActivity(getContext());

                }
            }
        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void setDataByModel(Object data) {

    }
}
