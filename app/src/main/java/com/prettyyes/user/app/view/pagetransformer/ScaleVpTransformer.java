package com.prettyyes.user.app.view.pagetransformer;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.utils
 * Author: SmileChen
 * Created on: 2016/8/9
 * Description: 其实核心代码就是这个动画实现部分，这里设置了一个最大缩放和最小缩放比例，
 * 当处于最中间的view往左边滑动时，它的position值是小于0的，
 * 并且是越来越小,它右边的view的position是从1逐渐减小到0的
 */
public class ScaleVpTransformer implements ViewPager.PageTransformer {
    public ScaleVpTransformer(float f)
    {
        MIN_SCALE=f;
    }

    private void initTransForm(View view) {
        view.setScaleX(MIN_SCALE);
        view.setScaleY(MIN_SCALE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            view.getParent().requestLayout();
        }
    }
    public ScaleVpTransformer(float f,ViewPager viewPager)
    {
        MIN_SCALE=f;
        if (viewPager.getChildCount() > 0) {
            int index = viewPager.getCurrentItem();
            if (viewPager.getChildAt(index - 1) != null) {
                initTransForm(viewPager.getChildAt(index - 1));
            }
            if (viewPager.getChildAt(index + 1) != null) {
                initTransForm(viewPager.getChildAt(index + 1));
            }

        }
    }
    public ScaleVpTransformer()
    {

    }
    public static  float MAX_SCALE = 1.0f;
    public static  float MIN_SCALE = 0.8f;
    /**核心就是实现transformPage(View page, float position)这个方法**/
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;
        //一个公式
        float scaleValue = MIN_SCALE + tempScale * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);



        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
    }
}
