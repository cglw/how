package com.prettyyes.user.app.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.prettyyes.user.R;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/7/20
 * Description: Nothing
 */
public class UIDialogView implements View.OnClickListener {
    private SimpleDialogShow simpleDialogShow;
    private TextView tvDialogTitle,tvContent;
    private FrameLayout llayContentLayout;
    private Button btnCancel,btnConfirm;
    private View view,centerLine;
    private boolean hasNewDialog = true,hasCancel = true;
    private Context context;
    private String title = "";
    private IBtnconfirm btnconfirm;

    public UIDialogView(Context context)
    {
        this.context = context;
        if(null == simpleDialogShow)
        {
            simpleDialogShow = new  SimpleDialogShow();
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dialog_simpledialog, null);
        tvDialogTitle = (TextView) view.findViewById(R.id.tvDialogTitle);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        btnCancel = (Button) view.findViewById(R.id.btnDialogCancel);
        btnConfirm = (Button) view.findViewById(R.id.btnDialogConfirm);
        llayContentLayout = (FrameLayout) view.findViewById(R.id.llayContentLayout);
        centerLine = view.findViewById(R.id.centerLine);
        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());

    }



    public void showDialog(int contentStrId)
    {
        //	AppStrUtil
        //show(AppStrUtil.getStrById(context, contentStrId));
        show(contentStrId + "");

    }

    public void show(String content)
    {
        if(!hasNewDialog) //每次都是一个新的
        {
            simpleDialogShow = new  SimpleDialogShow();
        }
        simpleDialogShow.setHasCancel(hasCancel);

        if(TextUtils.isEmpty(title))
        {
            title = "系统提示";
        }
        tvDialogTitle.setText(title);
        tvContent.setText(content);
        simpleDialogShow.showDialog((Activity)context, view);
    }
    public void show(String content,boolean cancelable)
    {
        if(!hasNewDialog) //每次都是一个新的
        {
            simpleDialogShow = new  SimpleDialogShow();
        }
        simpleDialogShow.setHasCancel(hasCancel);

        if(TextUtils.isEmpty(title))
        {
            title = "系统提示";
        }
        tvDialogTitle.setText(title);
        tvContent.setText(content);
        simpleDialogShow.setHasCancel(cancelable);
        simpleDialogShow.showDialog((Activity)context, view);
    }

    public void show()
    {
        show("");
    }


    public void setHasCancel(boolean hasCancel)
    {
        this.hasCancel =  hasCancel;
    }




    public void setTitle(String title) {
        this.title = title;
    }

    public void dimiss()
    {
        if(null != simpleDialogShow)
        {
            simpleDialogShow.dimissDialog();
        }
    }

    public void setContentView(View view)
    {
        llayContentLayout.removeAllViews();
        llayContentLayout.addView(view);
    }

    public void setHidenCancel()
    {
        btnCancel.setVisibility(View.GONE);
        centerLine.setVisibility(View.GONE);
    }


    public void setConfirmTxt(String confirmTxt) {
        btnConfirm.setText(confirmTxt);
    }

    public void setCancelTxt(String cancelTxt) {
        btnCancel.setText(cancelTxt);
    }

    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();
        if (id == R.id.btnDialogCancel)
        {
            dimiss();
            if(null != btnconfirm)
            {
                btnconfirm.cancel();
            }
        }
        else if (id == R.id.btnDialogConfirm)
        {
            if(null != btnconfirm)
            {
                btnconfirm.confirm();
            }
        }
    }

    public interface IBtnconfirm
    {
        public void confirm();
        public void cancel();
    }

    public void setBtnconfirm(IBtnconfirm btnconfirm) {
        this.btnconfirm = btnconfirm;
    }
}
