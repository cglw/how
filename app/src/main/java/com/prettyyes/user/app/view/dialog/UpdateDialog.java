package com.prettyyes.user.app.view.dialog;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.StringUtils;

/**
 * Created by chengang on 2017/6/7.
 */

public class UpdateDialog extends AppCompatDialog {
    public UpdateDialog(Context context) {
        super(context, R.style.simple_dialog);
        setContentView(R.layout.dialog_update_app);
        button_update = (Button) findViewById(R.id.btn_update);
        rel_close = (RelativeLayout) findViewById(R.id.rel_close);
        tv_update_content = (TextView) findViewById(R.id.tv_update_content);
        rel_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setCancelable(false);
    }

    private Button button_update;
    private RelativeLayout rel_close;
    private TextView tv_update_content;

    public UpdateDialog setUpdateContent(String content) {
        if (!StringUtils.strIsEmpty(content)) {
            tv_update_content.setText(content);
        }
        return this;
    }


    public UpdateDialog setListener(View.OnClickListener onClickListener) {
        button_update.setTag(this);
        button_update.setOnClickListener(onClickListener);
        return this;
    }
}
