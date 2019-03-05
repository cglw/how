package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.DensityUtil;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/7/29
 * Description: Nothing
 */
public class SharePopupWindow extends PopupWindow {

    private ImageView cancel;
    private ImageView sina;
    private ImageView weixin;
    private ImageView weixin_friend;
    private ImageView qq;
    private View mMenuView;
    public SharePopupWindow(final Activity context, View.OnClickListener itemsOnClick)
    {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_share, null);
        sina= (ImageView) mMenuView.findViewById(R.id.img_popup_sina);
        weixin= (ImageView) mMenuView.findViewById(R.id.img_popup_weixin);
        weixin_friend= (ImageView) mMenuView.findViewById(R.id.img_popup_weixin_friend);
        qq= (ImageView) mMenuView.findViewById(R.id.img_popup_qq);
        cancel= (ImageView) mMenuView.findViewById(R.id.btn_popupshare_cancel);


        sina.setOnClickListener(itemsOnClick);
        weixin.setOnClickListener(itemsOnClick);
        weixin_friend.setOnClickListener(itemsOnClick);
        qq.setOnClickListener(itemsOnClick);
        cancel.setOnClickListener(itemsOnClick);


        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dip2px(300));
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
       // this.setAnimationStyle(R.style.AnimBottom);
        this.setAnimationStyle(R.style.animon);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
       // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;
        context.getWindow().setAttributes(lp);

        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                // int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int height = mMenuView.findViewById(R.id.lin_share).getTop();
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
            }
        });
        //  this.setAnimationStyle(R.style.PopupAnimation);

    }

}
