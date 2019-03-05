package com.prettyyes.user.core.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.prettyyes.user.R;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.utils
 * Author: SmileChen
 * Created on: 2016/10/18
 * Description: Nothing
 */
public class SoftKeyboardUtil {

    public static int getRvItemY(int position, RecyclerView rv) {
        int item_y = 0;

        try {
            LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();

            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            int index = position - firstVisibleItemPosition;
            if (index >= 0 && index <= rv.getChildCount()) {
                View childAt = rv.getChildAt(index);
                int[] loa = new int[2];
                childAt.getLocationOnScreen(loa);
                item_y = loa[1] + childAt.getHeight();
            }
        } catch (Exception ee) {
            return 0;
        }

        return item_y;

    }

    public static int getItemY(View v) {
        if (v == null)
            return 0;
        int item_y = 0;
        int[] loa = new int[2];
        v.getLocationOnScreen(loa);
        item_y = loa[1] + v.getHeight();
        return item_y;

    }

    public static void scorllRvToLocation(RecyclerView rv, View intputView, int lastY) {
        if (rv == null || intputView == null)
            return;
        //判断软键盘弹出还是可见的item 被遮挡
        int[] position2 = new int[2];
        intputView.getLocationOnScreen(position2);
        if (lastY <= position2[1]) {

        } else {
            rv.scrollBy(0, lastY - position2[1]);

        }


    }


    public static void observeSoftKeyboard(Activity activity, final OnSoftKeyboardChangeListener listener) {
        final View decorView = activity.getWindow().getDecorView();

        ViewTreeObserver.OnGlobalLayoutListener listener1 = new ViewTreeObserver.OnGlobalLayoutListener() {
            int previousKeyboardHeight = -1;

            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                int displayHeight = rect.bottom - rect.top;
                int height = decorView.getHeight();
                int keyboardHeight = height - displayHeight;
                if (previousKeyboardHeight != keyboardHeight) {
                    boolean hide = (double) displayHeight / height > 0.8;
                    listener.onSoftKeyBoardChange(keyboardHeight, displayHeight, !hide);
                }
                previousKeyboardHeight = height;

            }
        };
        decorView.setTag(R.id.tag_act_soft, listener1);
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(listener1);
    }

    public static void observeSoftKeyboardDestopry(Activity activity) {
        if (activity == null)
            return;
        final View decorView = activity.getWindow().getDecorView();
        ViewTreeObserver.OnGlobalLayoutListener tag = (ViewTreeObserver.OnGlobalLayoutListener) decorView.getTag(R.id.tag_act_soft);
        if (tag != null)
            decorView.getViewTreeObserver().removeOnGlobalLayoutListener(tag);
    }

    public interface OnSoftKeyboardChangeListener {
        void onSoftKeyBoardChange(int softKeybardHeight, int top, boolean visible);
    }


    /**
     * @throws
     * @MethodName:closeInputMethod
     * @Description:关闭系统软键盘
     */

    static public void closeInputMethod(Context context, View view) {

        try {
            //获取输入法的服务
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            //判断是否在激活状态
            if (imm.isActive()) {
                //隐藏输入法!!,
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }

        } catch (Exception e) {
        } finally {
        }

    }

    /**
     * @throws
     * @MethodName:openInputMethod
     * @Description:打开系统软键盘
     */

    static public void openInputMethod(final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();


        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) editText

                        .getContext().getSystemService(

                                Context.INPUT_METHOD_SERVICE);

                inputManager.showSoftInput(editText, 0);

            }
        },200);

    }
}
