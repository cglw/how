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

public class EditDialog extends Dialog {


    private EditText edit;
    private TextView tv_cancel;
    private TextView tv_confirm;


    public EditDialog(final Activity context, final DialogCallback dialogCallback, String text) {
        super(context, R.style.simple_dialog);
        setContentView(R.layout.layout_edit);
        edit = (EditText) findViewById(R.id.edit);
        edit.setText(text);
        if (edit.getText().toString().length() > 0)
            edit.setSelection(edit.getText().toString().length());
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
                if (edit.getText().toString().length() <= 0) {
                    AppUtil.showToastShort("内容不允许为空");
                }
                if (dialogCallback != null) {
                    dialogCallback.confirm(edit.getText().toString());
                    dismiss();

                }
            }
        });
    }


    public interface DialogCallback {
        void confirm(String text);
    }

}
