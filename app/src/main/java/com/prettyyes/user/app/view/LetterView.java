package com.prettyyes.user.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.prettyyes.user.core.utils.AppRxBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by chengang on 2017/6/13.
 */

public class LetterView extends View {

    private String TAG = LetterView.class.getSimpleName();
    //A,B,C....Z,#
    public List<String> letters;
    private Paint mPaint;
    private int selectPosition = -1;

    private TextView mLetter;

    public void setmLetter(TextView mLetter) {
        this.mLetter = mLetter;
    }

    public LetterView(Context context) {
        this(context, null);
    }

    public LetterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //生产字母
        letters = new ArrayList<>();
        for (int i = 65; i < 91; i++) {
            letters.add(String.format(Locale.CHINA, "%c", i));
        }
        letters.add("#");//追加一个#
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 获取View的宽度
         * 获取View的高度
         */
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //测量字的宽度
        int size = letters.size();
        for (int i = 0; i < size; i++) {
            float textWidth = mPaint.measureText(letters.get(i));
            int singleHeight = height / size;
            if (selectPosition == i) {//被选中的
                mPaint.setColor(Color.RED);
            } else {
                mPaint.setColor(Color.BLUE);
            }
            canvas.drawText(letters.get(i), (width - textWidth) / 2, singleHeight * (i + 1), mPaint);
            /**
             * drawText() x y \_ 为基准线
             */
            invalidate();
        }
    }

    /**
     * Android将触摸事件封装,包装了动作,位置信息;onClick也是一种motionEvent
     * onClick 事件 实际上是 onTouchEvent事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //包装了动作,位置信息
//        event.getY();//相对于View本身的坐标值
//        event.getRawY();//返回的是相对于屏幕的坐标值
        float y = event.getY();
//        Log.e(TAG, "onTouchEvent: RawY:"+event.getRawY() );
        int measuredHeight = getMeasuredHeight();
        int singleHeight = measuredHeight / letters.size();
        int position = (int) (y / singleHeight);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                selectPosition = position;
                if (mLetter != null) {
                    mLetter.setVisibility(View.VISIBLE);
                    //极限情况有可能下标越界,需要判断一下
                    if (position < letters.size() && position >= 0) {
                        mLetter.setText(letters.get(position));
                        AppRxBus.getInstance().post(letters.get(position));
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                selectPosition = -1;
                if (mLetter != null) {
                    mLetter.setVisibility(View.GONE);
                }
                break;
        }
        //返回true代表事件被处理了
        return true;
    }


}