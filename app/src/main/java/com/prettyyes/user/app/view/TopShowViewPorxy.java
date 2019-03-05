package com.prettyyes.user.app.view;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.appview.UploadUxView;
import com.prettyyes.user.core.utils.AppUtil;

/**
 * Created by chengang on 2017/12/13.
 */

public class TopShowViewPorxy {


    private TSnackbar tSnackbar;
    private UploadUxView uploadUxView;
    private Runnable action;

    public TopShowViewPorxy(View root) {
        this.root = root;
        makeDefault(root);
    }

    public TopShowViewPorxy(View root, String text) {
        this.root = root;
    }

    private View root;

    @NonNull
    public TopShowViewPorxy makeDefault(@NonNull View root) {
        tSnackbar = TSnackbar.make(root, "", TSnackbar.LENGTH_INDEFINITE);

        tSnackbar.getView().setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.ux_green));
        TSnackbar.SnackbarLayout view = (TSnackbar.SnackbarLayout) tSnackbar.getView();
        TextView viewById = (TextView) view.findViewById(R.id.snackbar_text);
        viewById.setText("");
        uploadUxView = new UploadUxView(root.getContext());
        view.addView(uploadUxView, 0);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppUtil.dip2px(30)));
        uploadUxView.getImg_close().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return this;
    }

    @NonNull
    public TopShowViewPorxy makeHome(String text) {
        tSnackbar = TSnackbar.make(root, "", TSnackbar.LENGTH_SHORT);
        TSnackbar.SnackbarLayout view = (TSnackbar.SnackbarLayout) tSnackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.home_notify_bg));
        view.setPadding(0, 0, 0, 0);
        TextView viewById = (TextView) view.findViewById(R.id.snackbar_text);
        viewById.setText("");
        View view_home_notify = LayoutInflater.from(root.getContext()).inflate(R.layout.view_home_notify, null);
        TextView tv = (TextView) view_home_notify.findViewById(R.id.tv_homenotify_desc);
        tv.setText(text);
        view.addView(view_home_notify, 0);
        view.setLayoutParams(new LinearLayout.LayoutParams(BaseApplication.ScreenWidth, AppUtil.dip2px(36)));


        return this;
    }


    public UploadUxView getUploadUxView() {
        return uploadUxView;
    }

    public void show() {
        if (tSnackbar != null && !tSnackbar.isShown()) {
            makeDefault(root);
            tSnackbar.show();
        }
    }

    public void showHome(String text) {
        makeHome(text);
        if (tSnackbar != null && !tSnackbar.isShown()) {
            tSnackbar.show();
        }
    }


    public void dismiss() {
        if (tSnackbar != null)
            tSnackbar.dismiss();
    }

    private void setProgressText(String text, int index, int total) {
        show();
        uploadUxView.setProgressText(text);
        if (index == total) {
            action = new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            };
            uploadUxView.postDelayed(action, 1500);
        }

    }

    private void setText(String text) {
        show();
        uploadUxView.setProgressText(text);

        action = new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        };
        uploadUxView.postDelayed(action, 1500);


    }

    public void release() {
        dismiss();
        if (action != null && uploadUxView != null)
            uploadUxView.removeCallbacks(action);

    }

    public void setProgressVideoText(int index, int total) {
        if (total == 0 || index == 0)
            return;
        setProgressText(String.format("视频上传成功%d/%d", index, total), index, total);
    }

    public void showText(String text) {
        setText(text);
    }

    public void showTextNoHide(String text) {
        show();
        uploadUxView.setProgressText(text);
    }

    public void setProgressImageText(int index, int total) {
        if (total == 0 || index == 0)
            return;
        setProgressText(String.format("图片上传成功%d/%d", index, total), index, total);
    }

}
