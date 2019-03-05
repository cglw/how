package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.DensityUtil;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/7/29
 * Description: Nothing
 * h5活动页面
 */
public class LinearVerticalPupop extends PopupWindow {

    private LinearLayout lin_share;
    private LinearLayout lin_refuse;

    private View mMenuView;

    public LinearVerticalPupop(Activity context, View.OnClickListener clickListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_more_share, null);
        lin_share = (LinearLayout) mMenuView.findViewById(R.id.lin_morePupop_share);
        lin_refuse = (LinearLayout) mMenuView.findViewById(R.id.lin_morePupop_refuseSeller);

        lin_share.setOnClickListener(clickListener);
        lin_share.setTag(0);
        lin_refuse.setOnClickListener(clickListener);
        lin_refuse.setTag(1);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(DensityUtil.dip2px(context,150));
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dip2px(context,100));
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        // this.setAnimationStyle(R.style.AnimBottom);
       // this.setAnimationStyle(R.style.animon_fromtop);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);





        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                // int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int height_share = mMenuView.findViewById(R.id.lin_morePupop_share).getTop();
                int with_share = mMenuView.findViewById(R.id.lin_morePupop_share).getLeft();
                int height_refuse = mMenuView.findViewById(R.id.lin_morePupop_refuseSeller).getTop();
                int with_refuse=mMenuView.findViewById(R.id.lin_morePupop_refuseSeller).getLeft();
                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y>height_refuse||y<height_share||x<with_share||x>with_refuse) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        //  this.setAnimationStyle(R.style.PopupAnimation);

    }

}
