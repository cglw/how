package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.core.utils.ImageHelper;

import java.io.File;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/7/29
 * Description: Nothing
 * h5活动页面
 */
public class AlertMessageWindow extends PopupWindow {

    private ImageView img_close;
    private ImageView img_alertimg;
    private String filename;
    private View mMenuView;

    public AlertMessageWindow(Activity context, String filename) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.activity_alertmessage, null);
        img_close = (ImageView) mMenuView.findViewById(R.id.img_AlertmessageAct_close);
        img_alertimg = (ImageView) mMenuView.findViewById(R.id.img_AlertmessageAct_alertimg);

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        // this.setAnimationStyle(R.style.AnimBottom);
        this.setAnimationStyle(R.style.animon_fromtop);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);


        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        File updateFile = FileUtils.getDiskCacheDir(BaseApplication.getAppContext(), filename);
        if (updateFile.exists() && updateFile.length() > 0) {
            try {
                img_alertimg.setImageBitmap(ImageHelper.getimage(updateFile.getAbsolutePath(), 1000));
            } catch (Exception ee) {

            }
        }



        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//        mMenuView.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//
//                // int height = mMenuView.findViewById(R.id.pop_layout).getTop();
//                int height = mMenuView.findViewById(R.id.rel_popup_getpic).getTop();
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        dismiss();
//                    }
//                }
//                return true;
//            }
//        });
        //  this.setAnimationStyle(R.style.PopupAnimation);

    }

}
