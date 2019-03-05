package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.core.utils.DensityUtil;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.pupopwindow
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public abstract class AbsPupopWindow extends PopupWindow {


    private float defaultAlpha =0.7f;

    public void setDefaultAlpha(float defaultAlpha) {
        this.defaultAlpha = defaultAlpha;
    }

    public View mMenuView;

    public abstract void bindView(View view);

    public abstract int getLayout();

    public void setListener() {
    }

    public abstract int getLayoutHeight();

    public Activity activity;

    public AbsPupopWindow(final Activity context) {
        super(context);
        activity = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (getLayout() != 0&&inflater!=null) {
            mMenuView = inflater.inflate(getLayout(), null);
        }
        View view = createView();
        if (view != null) {
            mMenuView = view;
        }
        bindView(mMenuView);
        setListener();

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dip2px(getLayoutHeight()));

        setHeightBy();
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        // this.setAnimationStyle(R.style.AnimBottom);
        this.setAnimationStyle(setAnimation());
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);


        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                // int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int height = mMenuView.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();

                    }
                }
                return true;
            }
        });
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 1f;
                context.getWindow().setAttributes(lp);
                sendDismissBrocast();
            }
        });

    }

    public int setAnimation() {
        return R.style.animon;
    }

    public View createView() {
        return null;
    }


    public void setHeightBy() {

    }

    public void show(View view) {
        sendDismissBrocast();
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = defaultAlpha;
        activity.getWindow().setAttributes(lp);
        this.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

    }
    public void showAsdropdown(View view)
    {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = defaultAlpha;
        activity.getWindow().setAttributes(lp);
        //设置layout在PopupWindow中显示的位置
        this.showAsDropDown(view);

    }

    public void show(int id) {
        sendDismissBrocast();
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = defaultAlpha;
        activity.getWindow().setAttributes(lp);
        //设置layout在PopupWindow中显示的位置
        this.showAtLocation(activity.findViewById(id), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    public PopupWindow show() {
        sendShowBrocast();
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = defaultAlpha;
        activity.getWindow().setAttributes(lp);
        //设置layout在PopupWindow中显示的位置
        this.showAtLocation(activity.findViewById(id), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        return this;
    }

    private void sendShowBrocast() {
        activity.sendBroadcast(new Intent(Constant.PUPOPSHOW));
    }


    private void sendDismissBrocast() {
        activity.sendBroadcast(new Intent(Constant.PUPOPDISMISS));
    }



    private int id;

    public void setLayoutId(int id) {
        this.id = id;
    }


}
