package com.prettyyes.user.core.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

/**
 * Created by chengang on 2017/6/26.
 */

public class ViewUtils {
    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动  false：不可以滚动
     */
    public static boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    public static boolean canVerticalScroll(ScrollView scrollView,MotionEvent event) {

        //滚动的距离
        int scrollY = scrollView.getScrollY();
        //控件内容的总高度
        int scrollRange = scrollView.getHeight();


        //控件实际显示的高度
        int scrollExtent = scrollView.getChildAt(0).getHeight();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    private void getEdittext(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof EditText) { // 若是Button记录下
                EditText newDtv = (EditText) view;

            } else if (view instanceof ViewGroup) {
                // 若是布局控件（LinearLayout或RelativeLayout）,继续查询子View
                this.getEdittext((ViewGroup) view);
            }
        }
    }

}
