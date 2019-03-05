package com.prettyyes.user.app.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.core.containter.JumpPageManager;

/**
 * Created by chengang on 2017/12/4.
 */

public class SelectMediaDialog extends Dialog {
    public SelectMediaDialog(@NonNull final Context context, DialogCallback callback) {
        super(context, R.style.simple_dialog);
        this.dialogCallback=callback;
        setContentView(R.layout.dialog_select_media);
        LinearLayout ll_root = (LinearLayout) findViewById(R.id.ll_root);
        ll_root.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                JumpPageManager.getManager().goAddExtraVideoUrlActivity(v.getContext(),null,null);

            }
        }); ll_root.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (dialogCallback != null)
                    dialogCallback.picture();

            }
        });
        ll_root.getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (dialogCallback != null)
                    dialogCallback.vedio();
            }
        });
    }

    public DialogCallback dialogCallback;

    public void setDialogCallback(DialogCallback dialogCallback) {
        this.dialogCallback = dialogCallback;
    }

    public interface DialogCallback {
        void picture();

        void vedio();
    }

}
