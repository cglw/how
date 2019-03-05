package com.prettyyes.user.app.view.tvbtnetv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/8/11
 * Description: radiobutton 文字图片居中
 */
public class DrawableCenterRadioButton extends RadioButton {
    public DrawableCenterRadioButton(Context context) {
        super(context);
    }

    public DrawableCenterRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableCenterRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableTop = drawables[1];
            if (drawableTop != null) {
                float textHeight=measureHeight(getText().toString());
                textHeight=0;
                int drawablePadding = getCompoundDrawablePadding();
                int drawableHeight = drawableTop.getIntrinsicHeight();
                float bodyHeight = textHeight + drawableHeight + drawablePadding;
                setPadding(0, (int) (getHeight() - bodyHeight), 0, 0);
                float tranheight=(getHeight() - bodyHeight) / 2;
                canvas.translate(0, 0-tranheight);
            }
        }
        super.onDraw(canvas);
    }

    //获取文本高度
    public int measureHeight(String text) {

        Paint.FontMetrics fontMetrics= getPaint().getFontMetrics();

        return (int)(fontMetrics.descent-fontMetrics.ascent+ fontMetrics.leading);
    }


}