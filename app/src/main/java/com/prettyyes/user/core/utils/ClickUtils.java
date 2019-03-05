package com.prettyyes.user.core.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.view.tvbtnetv.ExpandableTextView;

/**
 * Created by Administrator on 2016/1/8.
 */
public class ClickUtils {
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    public static void setClick(TextView tv, String total, String target, View.OnClickListener clickListener) {
        if (!TextUtils.isEmpty(target) && total.contains(target)) {
            SpannableString spanableInfo = new SpannableString(total);
            Clickable what = new Clickable(clickListener, tv.getContext());
            spanableInfo.setSpan(what, target.indexOf(target), target.indexOf(target) + target.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(spanableInfo);
            tv.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            tv.setText(total);
        }
    }

    public static void setClickHashTag(TextView tv, String total, String target, View.OnClickListener hashtag_clickListener, int color, View.OnClickListener onClickListener) {
        if (!TextUtils.isEmpty(target) && total.contains(target)) {
            SpannableString spanableInfo = new SpannableString(total);
            Clickable what = new Clickable(hashtag_clickListener, tv.getContext());
            what.setColor(color);
            spanableInfo.setSpan(what, 0, target.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            if (onClickListener != null)
//                spanableInfo.setSpan(new ClickableNormal(onClickListener), target.length(), total.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(spanableInfo);
            tv.setOnTouchListener(new LinkMovementMethodOverride());
        } else {
            tv.setText(total);
        }
    }

    public static void setText(TextView tv, String total, String start, int size, View.OnClickListener clickListener) {
        if (!TextUtils.isEmpty(start)) {
            SpannableString spanableInfo = new SpannableString(total);
            if (clickListener != null)
                spanableInfo.setSpan(new Clickable(clickListener, tv.getContext()), 0, start.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanableInfo.setSpan(new AbsoluteSizeSpan(size), 0, start.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanableInfo.setSpan(new ForegroundColorSpan(Color.BLACK), 0, start.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(spanableInfo);
            tv.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            tv.setText(total);
        }
    }


    public static void setText(TextView tv, String total, String start, int size, int color) {
        if (!TextUtils.isEmpty(start)) {
            SpannableString spanableInfo = new SpannableString(total);
//            spanableInfo.setSpan(new Clickable(clickListener, tv.getContext()), 0, start.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanableInfo.setSpan(new AbsoluteSizeSpan(size), 0, start.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanableInfo.setSpan(new ForegroundColorSpan(color), 0, start.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(spanableInfo);
            tv.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            tv.setText(total);
        }
    }

    public static void setClick(ExpandableTextView tv, String total, String target, View.OnClickListener clickListener) {
        SpannableString spanableInfo = new SpannableString(total);
        spanableInfo.setSpan(new Clickable(clickListener, tv.getContext()), target.indexOf(target), target.indexOf(target) + target.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spanableInfo);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    static class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;
        private Context context;

        public Clickable(View.OnClickListener l, Context context) {
            mListener = l;
            this.context = context;
        }

        private int color;

        public int getColor() {
            return color;
        }

        public Clickable setColor(int color) {
            this.color = color;
            return this;
        }

        /**
         * 重写父类点击事件
         */
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

        /**
         * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
         */
        @Override
        public void updateDrawState(TextPaint ds) {
            if (color == 0)
                ds.setColor(ContextCompat.getColor(context, R.color.hash_tag_color));
            else
                ds.setColor(color);

        }
    }

    static class ClickableNormal extends ClickableSpan {
        private final View.OnClickListener mListener;

        public ClickableNormal(View.OnClickListener l) {
            mListener = l;
        }


        /**
         * 重写父类点击事件
         */
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

    }


    public static class LinkMovementMethodOverride implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView widget = (TextView) v;
            Object text = widget.getText();
            if (text instanceof Spanned) {
                Spanned buffer = (Spanned) text;

                int action = event.getAction();

                if (action == MotionEvent.ACTION_UP
                        || action == MotionEvent.ACTION_DOWN) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    x -= widget.getTotalPaddingLeft();
                    y -= widget.getTotalPaddingTop();

                    x += widget.getScrollX();
                    y += widget.getScrollY();

                    Layout layout = widget.getLayout();
                    int line = layout.getLineForVertical(y);
                    int off = layout.getOffsetForHorizontal(line, x);

                    ClickableSpan[] link = buffer.getSpans(off, off,
                            ClickableSpan.class);

                    if (link.length != 0) {
                        if (action == MotionEvent.ACTION_UP) {
                            link[0].onClick(widget);
                        } else if (action == MotionEvent.ACTION_DOWN) {
                            // Selection only works on Spannable text. In our case setSelection doesn't work on spanned text
                            //Selection.setSelection(buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                        }
                        return true;
                    }
                }

            }

            return false;
        }

    }

    public static boolean isContain(View view, float x, float y) {
        int[] point = new int[2];
        view.getLocationOnScreen(point);

        LogUtil.i("isContain", point[0] + "," + point[1] + "," + (point[0] + view.getWidth()) + "," + (point[1] + view.getHeight()));
        LogUtil.i("isContain", x + "," + y);

        if (x >= point[0] && x <= (point[0] + view.getWidth()) && y >= point[1] && y <= (point[1] + view.getHeight())) {
            return true;
        }
        return false;
    }
}
