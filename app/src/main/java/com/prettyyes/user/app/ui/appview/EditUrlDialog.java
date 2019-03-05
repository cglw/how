package com.prettyyes.user.app.ui.appview;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.AppUtil;

/**
 * Created by chengang on 2017/6/2.
 */

public class EditUrlDialog extends Dialog {


    private EditText edit_name;
    private EditText edit_url;
    private TextView tv_cancel;
    private TextView tv_confirm;


    public EditUrlDialog(final Activity context, final DialogCallback dialogCallback) {
        super(context, R.style.simple_dialog);
        setContentView(R.layout.dialog_edit_url);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_url = (EditText) findViewById(R.id.edit_url);
        tv_cancel = (TextView) findViewById(R.id.tv_cancle);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        setCancelable(false);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_name.getText().toString().length() <= 0) {
                    AppUtil.showToastShort("链接名称不允许为空");
                }
                if (edit_url.getText().toString().length() <= 0) {
                    AppUtil.showToastShort("链接地址不允许为空");
                }
                if (dialogCallback != null) {
                    dialogCallback.confirm(edit_name.getText().toString(), edit_url.getText().toString());
                    dismiss();

                }
            }
        });
    }


    public interface DialogCallback {
        void confirm(String name, String url);
    }

}
