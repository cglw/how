/*
 * BadgeView.java
 * BadgeView
 * 
 * Copyright (c) 2012 Stefan Jauker.
 * https://github.com/kodex83/BadgeView
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 数字标签，右上角
 */

package com.prettyyes.user.app.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TabWidget;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.ImageHelper;


public class BadgeView extends TextView {
    private static final String TAG = "BadgeView";

    private boolean mHideOnNull = true;

    public BadgeView(Context context) {
        this(context, null);
        setTextSize(6);

    }


    public void initNopadding(int size) {
        LayoutParams layoutParams =
                new LayoutParams(dip2Px(size / 3), dip2Px(size / 3));
        setLayoutParams(layoutParams);
        setHideOnNull(true);
        setBadgeCount(0);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);

    }

    public BadgeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        if (!(getLayoutParams() instanceof LayoutParams)) {
            LayoutParams layoutParams =
                    new LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.RIGHT | Gravity.TOP);
            setLayoutParams(layoutParams);
        }

        // set default font
        setTextColor(Color.WHITE);
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        setPadding(dip2Px(5), dip2Px(1), dip2Px(5), dip2Px(1));

        // set default background
        setBackground(9, Color.parseColor("#d3321b"));

        setGravity(Gravity.CENTER);

        // default values
        setHideOnNull(true);
        setBadgeCount(0);
    }


    public void setImageTag(boolean isshow) {

        setHeight(dip2Px(10));
        setWidth(dip2Px(10));

        if (isshow) {
            setBackgroundColor(getResources().getColor(R.color.theme_red));
        }
        setBadgeGravity(Gravity.BOTTOM | Gravity.RIGHT);
        setText("");
        setHideOnNull(!isshow);


    }
  public void setImageTagRound(boolean isshow) {

        setHeight(dip2Px(4));
        setWidth(dip2Px(4));

        if (isshow) {
            setBackgroundResource(R.drawable.bg_round_red_2);
        }
        setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        setText("");
        setHideOnNull(!isshow);


    }

    public void setImageTag(boolean isshow, int imagesize) {

        if (isshow) {

            Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.kol_vip);
            Bitmap bitmaptarget = ImageHelper.zoomImage(bitmap, dip2Px(imagesize / 3), dip2Px(imagesize / 3));
            Drawable bgDrawable = new BitmapDrawable(bitmaptarget);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                setBackground(bgDrawable);
            else
                setBackgroundDrawable(bgDrawable);
        }
        setBadgeGravity(Gravity.BOTTOM | Gravity.RIGHT);
        setText("");
        setHideOnNull(!isshow);


    }

    public void setBackground(int dipRadius, int badgeColor) {
        int radius = dip2Px(dipRadius);
        float[] radiusArray = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};

        RoundRectShape roundRect = new RoundRectShape(radiusArray, null, null);
        ShapeDrawable bgDrawable = new ShapeDrawable(roundRect);
        bgDrawable.getPaint().setColor(badgeColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            setBackground(bgDrawable);
        else
            setBackgroundDrawable(bgDrawable);
    }

    /**
     * @return Returns true if view is hidden on badge value 0 or null;
     */
    public boolean isHideOnNull() {
        return mHideOnNull;
    }

    /**
     * @param hideOnNull the hideOnNull to set
     */
    public void setHideOnNull(boolean hideOnNull) {
        mHideOnNull = hideOnNull;
        if (isHideOnNull())
            setText(null);
        else
            setText(getText());
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.TextView#setText(java.lang.CharSequence, android.widget.TextView.BufferType)
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        if (isHideOnNull() && (text == null || text.toString().equalsIgnoreCase("0"))) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
        super.setText(text, type);
    }

    public void setBadgeCount(int count) {

        if (count <= 99 && count >= 0)
            setText(String.valueOf(count));
        else if (count > 99)
            setText("99+");

        int j = 0;
        int i = 0;
        if (count < 10) {
            i = DensityUtil.dip2px(1);
            j = DensityUtil.dip2px(4);
        } else {
            i = DensityUtil.dip2px(1);
            j = DensityUtil.dip2px(2);
        }
        setPadding(j, i, j, i);

    }

    public int getBadeCount() {
        if (TextUtils.isEmpty(getText().toString()))
            return 0;
        return Integer.parseInt(getText().toString());
    }

    public Integer getBadgeCount() {
        if (getText() == null) {
            return null;
        }

        String text = getText().toString();
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setBadgeGravity(int gravity) {
        LayoutParams params = (LayoutParams) getLayoutParams();
        params.gravity = gravity;
        setLayoutParams(params);
    }

    public int getBadgeGravity() {
        LayoutParams params = (LayoutParams) getLayoutParams();
        return params.gravity;
    }

    public void setBadgeMargin(int dipMargin) {
        setBadgeMargin(dipMargin, dipMargin, dipMargin, dipMargin);
    }

    public void setBadgeMargin(int leftDipMargin, int topDipMargin, int rightDipMargin, int bottomDipMargin) {
        LayoutParams params = (LayoutParams) getLayoutParams();
        params.leftMargin = dip2Px(leftDipMargin);
        params.topMargin = dip2Px(topDipMargin);
        params.rightMargin = dip2Px(rightDipMargin);
        params.bottomMargin = dip2Px(bottomDipMargin);
        setLayoutParams(params);
    }

    public int[] getBadgeMargin() {
        LayoutParams params = (LayoutParams) getLayoutParams();
        return new int[]{params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin};
    }

    public void incrementBadgeCount(int increment) {
        Integer count = getBadgeCount();
        if (count == null) {
            setBadgeCount(increment);
        } else {
            setBadgeCount(increment + count);
        }
    }

    public void decrementBadgeCount(int decrement) {
        incrementBadgeCount(-decrement);
    }

    /*
     * Attach the BadgeView to the TabWidget
     * 
     * @param target the TabWidget to attach the BadgeView
     * 
     * @param tabIndex index of the tab
     */
    public void setTargetView(TabWidget target, int tabIndex) {
        View tabView = target.getChildTabViewAt(tabIndex);
        setTargetView(tabView);
    }

    /*
     * Attach the BadgeView to the target view
     * 
     * @param target the view to attach the BadgeView
     */
    public void setTargetView(View target) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }

        if (target == null) {
            return;
        }

        if (target.getParent() instanceof FrameLayout) {
            for (int i = 0; i < ((FrameLayout) target.getParent()).getChildCount(); i++) {
                View view = ((FrameLayout) target.getParent()).getChildAt(i);
                if (view instanceof BadgeView)
                    ((FrameLayout) target.getParent()).removeView(view);

            }
            ((FrameLayout) target.getParent()).addView(this);

        } else if (target.getParent() instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) target.getParent()).getChildCount(); i++) {
                View view = ((ViewGroup) target.getParent()).getChildAt(i);
                if (view instanceof BadgeView)
                    ((ViewGroup) target.getParent()).removeView(view);
            }
            // use a new Framelayout container for adding badge
            ViewGroup parentContainer = (ViewGroup) target.getParent();
            int groupIndex = parentContainer.indexOfChild(target);
            parentContainer.removeView(target);

            FrameLayout badgeContainer = new FrameLayout(getContext());
            ViewGroup.LayoutParams parentLayoutParams = target.getLayoutParams();

            badgeContainer.setLayoutParams(parentLayoutParams);
            target.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            parentContainer.addView(badgeContainer, groupIndex, parentLayoutParams);
            badgeContainer.addView(target);

            badgeContainer.addView(this);
        } else if (target.getParent() == null) {
            Log.e(getClass().getSimpleName(), "ParentView is needed");
        }

    }

    /*
     * converts dip to px
     */
    private int dip2Px(float dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }
}
