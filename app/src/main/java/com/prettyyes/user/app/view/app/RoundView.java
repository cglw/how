package com.prettyyes.user.app.view.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.R;


/**
 * Created by chengang on 2017/2/27.
 */

public class RoundView extends View {
    private Paint paint;
    private int COLOR_BG;

    public RoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }
    public RoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }
    public RoundView(Context context) {
        super(context);

    }


    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
        COLOR_BG=a.getColor(R.styleable.RoundView_bg_color, 0xFF7ED321);


    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(COLOR_BG);
        paint.setStyle(Paint.Style.FILL);

        int l = Math.min(getMeasuredHeight(), getMeasuredWidth());

        canvas.drawCircle(l / 2, l / 2, l / 2, paint);

    }
}
