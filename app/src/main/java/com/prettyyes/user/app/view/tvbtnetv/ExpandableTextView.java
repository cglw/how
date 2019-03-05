package com.prettyyes.user.app.view.tvbtnetv;

/**
 * Created by Carbs.Wang on 2016/7/16.
 * website: https://github.com/Carbs0126/
 * <p/>
 * Thanks to :
 * 1.ReadMoreTextView
 * https://github.com/borjabravo10/ReadMoreTextView
 * 2.TouchableSpan
 * http://stackoverflow.com/questions
 * /20856105/change-the-text-color-of-a-single-clickablespan-when-pressed-without-affecting-o
 * 3.FlatUI
 * http://www.bootcss.com/p/flat-ui/
 * <p/>
 * <p/>
 * 带全文收起的
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.ui.appview.QuestionTextView;
import com.prettyyes.user.core.event.ExpandTvEvent;
import com.prettyyes.user.core.utils.AppRxBus;

import java.lang.reflect.Field;

public class ExpandableTextView extends QuestionTextView {

    public static final int STATE_SHRINK = 0;
    public static final int STATE_EXPAND = 1;

    private static final String CLASS_NAME_VIEW = "android.view.View";
    private static final String CLASS_NAME_LISTENER_INFO = "android.view.View$ListenerInfo";
    private static final String ELLIPSIS_HINT = "..";
    private static final String GAP_TO_EXPAND_HINT = " ";
    private static final String GAP_TO_SHRINK_HINT = " ";
    private static final int MAX_LINES_ON_SHRINK = 2;
    private static final int TO_EXPAND_HINT_COLOR = 0xFF3498DB;
    private static final int TO_SHRINK_HINT_COLOR = 0xFFE74C3C;
    private static final int TO_EXPAND_HINT_COLOR_BG_PRESSED = 0x55999999;
    private static final int TO_SHRINK_HINT_COLOR_BG_PRESSED = 0x55999999;
    private static final boolean TOGGLE_ENABLE = true;
    private static final boolean SHOW_TO_EXPAND_HINT = true;
    private static final boolean SHOW_TO_SHRINK_HINT = true;

    private String mEllipsisHint;
    private String mToExpandHint;
    private String mToShrinkHint;
    private String mGapToExpandHint = GAP_TO_EXPAND_HINT;
    private String mGapToShrinkHint = GAP_TO_SHRINK_HINT;
    private boolean mToggleEnable = TOGGLE_ENABLE;
    private boolean mShowToExpandHint = SHOW_TO_EXPAND_HINT;
    private boolean mShowToShrinkHint = SHOW_TO_SHRINK_HINT;
    private int mMaxLinesOnShrink = MAX_LINES_ON_SHRINK;
    private int mToExpandHintColor = TO_EXPAND_HINT_COLOR;
    private int mToShrinkHintColor = TO_SHRINK_HINT_COLOR;
    private int mToExpandHintColorBgPressed = TO_EXPAND_HINT_COLOR_BG_PRESSED;
    private int mToShrinkHintColorBgPressed = TO_SHRINK_HINT_COLOR_BG_PRESSED;
    private int mCurrState = STATE_SHRINK;

    //  used to add to the tail of modified text, the "shrink" and "expand" text
    private TouchableSpan mTouchableSpan;
    private BufferType mBufferType = BufferType.NORMAL;
    private TextPaint mTextPaint;
    private Layout mLayout;
    private int mTextLineCount = -1;
    private int mLayoutWidth = 0;
    private int mFutureTextViewWidth = 0;
    private boolean isShowLeftTv = false;
    private boolean isShowNotAll = false;

    public boolean isShowNotAll() {
        return isShowNotAll;
    }

    //  the original text of this view
    private CharSequence mOrigText;

    //  used to judge if the listener of corresponding to the onclick event of ExpandableTextView
    //  is specifically for inner toggle
    private ExpandableClickListener mExpandableClickListener;
    private OnExpandListener mOnExpandListener;

    private boolean isTextCanTouch = true;
    private ViewTreeObserver viewTreeObserver;

    public boolean isTextCanTouch() {
        return isTextCanTouch;
    }

    public void setTextCanTouch(boolean textCanTouch) {
        isTextCanTouch = textCanTouch;
    }

    public ExpandableTextView(Context context) {
        super(context);
        init();
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        if (a == null) {
            return;
        }
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.ExpandableTextView_etv_MaxLinesOnShrink) {
                mMaxLinesOnShrink = a.getInteger(attr, MAX_LINES_ON_SHRINK);
            } else if (attr == R.styleable.ExpandableTextView_etv_EllipsisHint) {
                mEllipsisHint = a.getString(attr);
            } else if (attr == R.styleable.ExpandableTextView_etv_ToExpandHint) {
                mToExpandHint = a.getString(attr);
            } else if (attr == R.styleable.ExpandableTextView_etv_ToShrinkHint) {
                mToShrinkHint = a.getString(attr);
            } else if (attr == R.styleable.ExpandableTextView_etv_EnableToggle) {
                mToggleEnable = a.getBoolean(attr, TOGGLE_ENABLE);
            } else if (attr == R.styleable.ExpandableTextView_etv_ToExpandHintShow) {
                mShowToExpandHint = a.getBoolean(attr, SHOW_TO_EXPAND_HINT);
            } else if (attr == R.styleable.ExpandableTextView_etv_ToShrinkHintShow) {
                mShowToShrinkHint = a.getBoolean(attr, SHOW_TO_SHRINK_HINT);
            } else if (attr == R.styleable.ExpandableTextView_etv_ToExpandHintColor) {
                mToExpandHintColor = a.getInteger(attr, TO_EXPAND_HINT_COLOR);
            } else if (attr == R.styleable.ExpandableTextView_etv_ToShrinkHintColor) {
                mToShrinkHintColor = a.getInteger(attr, TO_SHRINK_HINT_COLOR);
            } else if (attr == R.styleable.ExpandableTextView_etv_ToExpandHintColorBgPressed) {
                mToExpandHintColorBgPressed = a.getInteger(attr, TO_EXPAND_HINT_COLOR_BG_PRESSED);
            } else if (attr == R.styleable.ExpandableTextView_etv_ToShrinkHintColorBgPressed) {
                mToShrinkHintColorBgPressed = a.getInteger(attr, TO_SHRINK_HINT_COLOR_BG_PRESSED);
            } else if (attr == R.styleable.ExpandableTextView_etv_InitState) {
                mCurrState = a.getInteger(attr, STATE_SHRINK);
            } else if (attr == R.styleable.ExpandableTextView_etv_GapToExpandHint) {
                mGapToExpandHint = a.getString(attr);
            } else if (attr == R.styleable.ExpandableTextView_etv_GapToShrinkHint) {
                mGapToShrinkHint = a.getString(attr);
            } else if (attr == R.styleable.ExpandableTextView_etv_IsCanTouchTxt) {
                isTextCanTouch = a.getBoolean(attr, true);
            } else if (attr == R.styleable.ExpandableTextView_etv_show_left_tv) {
                isShowLeftTv = a.getBoolean(attr, false);
            }
        }
        a.recycle();
    }


    private void init() {


        mTouchableSpan = new TouchableSpan();
        setMovementMethod(new LinkTouchMovementMethod());
        if (TextUtils.isEmpty(mEllipsisHint)) {
            mEllipsisHint = ELLIPSIS_HINT;
        }
        if (TextUtils.isEmpty(mToExpandHint)) {
            mToExpandHint = getResources().getString(R.string.to_expand_hint);
        }
        if (TextUtils.isEmpty(mToShrinkHint)) {
            mToShrinkHint = getResources().getString(R.string.to_shrink_hint);
        }
        if (mToggleEnable) {
            if (isTextCanTouch) {
                mExpandableClickListener = new ExpandableClickListener();
                setOnClickListener(mExpandableClickListener);
            } else {

                setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tvCallBack != null) {
                            if (!isDoExtral)
                                tvCallBack.onclick(v);
                            isDoExtral = false;
                        }
                    }
                });


            }
        }
        viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = getViewTreeObserver();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    obs.removeOnGlobalLayoutListener(this);
                } else {
                    obs.removeGlobalOnLayoutListener(this);
                }
                setTextInternal(getNewTextByConfig(), mBufferType);
                AppRxBus.getInstance().post(new ExpandTvEvent());

            }
        });
    }

    public interface TvCallBack {
        public void onclick(View view);
    }

    private TvCallBack tvCallBack;

    public void setTvCallBac(TvCallBack tvCallBack) {
        this.tvCallBack = tvCallBack;
    }

    /**
     * used in ListView or RecyclerView to update ExpandableTextView
     *
     * @param text                original text
     * @param futureTextViewWidth the width of ExpandableTextView in px unit,
     *                            used to get max line number of original text by given the width
     * @param expandState         expand or shrink
     */
    public void updateForRecyclerView(CharSequence text, int futureTextViewWidth, int expandState) {
        mFutureTextViewWidth = futureTextViewWidth;
        mCurrState = expandState;
        setText(text);
    }

    public void updateForRecyclerView(CharSequence text, BufferType type, int futureTextViewWidth) {
        mFutureTextViewWidth = futureTextViewWidth;
        setText(text, type);
    }

    public void updateForRecyclerView(CharSequence text, int futureTextViewWidth) {
        mFutureTextViewWidth = futureTextViewWidth;
        setText(text);
    }

    /**
     * get the current state of ExpandableTextView
     *
     * @return STATE_SHRINK if in shrink state
     * STATE_EXPAND if in expand state
     */
    public int getExpandState() {
        return mCurrState;
    }


//    private Paint paint_new = null;
//
//    private TextPaint getNewPaint() {
//        if (paint_new == null) {
//            paint_new = getPaint();
//            paint_new.setTextSize(AppUtil.dip2px(12));
//        }
//        return (TextPaint) paint_new;
//    }
//

    /**
     * refresh and get a will-be-displayed text by current configuration
     *
     * @return get a will-be-displayed text
     */
    private CharSequence getNewTextByConfig() {
        if (TextUtils.isEmpty(mOrigText)) {
            return mOrigText;
        }

        mLayout = getLayout();
        if (mLayout != null) {
            mLayoutWidth = mLayout.getWidth();
        }

        if (mLayoutWidth <= 0) {
            if (getWidth() == 0) {
                if (mFutureTextViewWidth == 0) {
                    return mOrigText;
                } else {
                    mLayoutWidth = mFutureTextViewWidth - getPaddingLeft() - getPaddingRight();
                }
            } else {
                mLayoutWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            }
        }

        mTextPaint = getPaint();

        mTextLineCount = -1;
        switch (mCurrState) {
            case STATE_SHRINK: {
                mLayout = new DynamicLayout(mOrigText, mTextPaint, mLayoutWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                mTextLineCount = mLayout.getLineCount();

                if (mTextLineCount <= mMaxLinesOnShrink) {
                    isShowNotAll = false;
                    return mOrigText;
                }
                if (isShowLeftTv)
                    mToExpandHint = "剩余字";
                int indexEnd = getValidLayout().getLineEnd(mMaxLinesOnShrink - 1);
                int indexStart = getValidLayout().getLineStart(mMaxLinesOnShrink - 1);
                int indexEndTrimmed = indexEnd
                        - getLengthOfString(mEllipsisHint)
                        - (mShowToExpandHint ? getLengthOfString(mToExpandHint) + getLengthOfString(mGapToExpandHint) : 0);
                if (indexEndTrimmed <= 0) {
                    isShowNotAll = true;
                    return mOrigText.subSequence(0, indexEnd);
                }


                if (indexEndTrimmed < indexStart) {
                    indexEndTrimmed = indexStart;
                }
                int remainWidth = getValidLayout().getWidth() -
                        (int) (getPaint().measureText(mOrigText.subSequence(indexStart, indexEndTrimmed).toString()) + 0.5);
                float widthTailReplaced = getPaint().measureText(getContentOfString(mEllipsisHint)
                        + (mShowToExpandHint ? (getContentOfString(mToExpandHint) + getContentOfString(mGapToExpandHint)) : ""));

                int indexEndTrimmedRevised = indexEndTrimmed;
                if (remainWidth > widthTailReplaced) {
                    int extraOffset = 0;
                    int extraWidth = 0;
                    while (remainWidth > widthTailReplaced + extraWidth) {
                        extraOffset++;
                        if (indexEndTrimmed + extraOffset <= mOrigText.length()) {
                            extraWidth = (int) (getPaint().measureText(
                                    mOrigText.subSequence(indexEndTrimmed, indexEndTrimmed + extraOffset).toString()) + 0.5);
                        } else {
                            break;
                        }
                    }
                    indexEndTrimmedRevised += extraOffset - 1;
                } else {
                    int extraOffset = 0;
                    int extraWidth = 0;
                    while (remainWidth + extraWidth < widthTailReplaced) {
                        extraOffset--;
                        if (indexEndTrimmed + extraOffset > indexStart) {
                            extraWidth = (int) (getPaint().measureText(mOrigText.subSequence(indexEndTrimmed + extraOffset, indexEndTrimmed).toString()) + 0.5);
                        } else {
                            break;
                        }
                    }
                    indexEndTrimmedRevised += extraOffset;
                }

                if (isShowLeftTv) {
                    int num_length = ((mOrigText.length() - indexEndTrimmedRevised) + "").length();
                    if (num_length == 1)
                        num_length = 2;
                    indexEndTrimmedRevised = (indexEndTrimmedRevised - (int) (Math.ceil(num_length / 2)));
                }
//                indexEndTrimmedRevised= (int) (indexEndTrimmedRevised*1.5);


                SpannableStringBuilder ssbShrink = new SpannableStringBuilder(mOrigText, 0, indexEndTrimmedRevised)
                        .append(mEllipsisHint);
                if (isShowLeftTv && (mOrigText.length() - indexEndTrimmedRevised) > 0)
                    mToExpandHint = String.format("剩余%d字", mOrigText.length() - indexEndTrimmedRevised);


                if ((mOrigText.length() - indexEndTrimmedRevised) > 0)
                    isShowNotAll = true;
                else
                    isShowNotAll = false;

                if (mShowToExpandHint) {
                    ssbShrink.append(getContentOfString(mGapToExpandHint) + getContentOfString(mToExpandHint));
                    ssbShrink.setSpan(mTouchableSpan, ssbShrink.length() - getLengthOfString(mToExpandHint), ssbShrink.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(12, true);

                    ssbShrink.setSpan(absoluteSizeSpan, ssbShrink.length() - getLengthOfString(mToExpandHint), ssbShrink.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                return ssbShrink;
            }
            case STATE_EXPAND: {
                if (!mShowToShrinkHint) {
                    return mOrigText;
                }
                mLayout = new DynamicLayout(mOrigText, mTextPaint, mLayoutWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                mTextLineCount = mLayout.getLineCount();

                if (mTextLineCount <= mMaxLinesOnShrink) {
                    return mOrigText;
                }

                SpannableStringBuilder ssbExpand = new SpannableStringBuilder(mOrigText)
                        .append(mGapToShrinkHint).append(mToShrinkHint);


                ssbExpand.setSpan(mTouchableSpan, ssbExpand.length() - getLengthOfString(mToShrinkHint), ssbExpand.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return ssbExpand;
            }
        }
        return mOrigText;
    }
//
//    TextPaint paint_small = null;
//
//    private TextPaint getSmallPaint() {
//        if (paint_small == null) {
//            paint_small = new TextPaint();
//            float textSize = getResources().getDisplayMetrics().scaledDensity * 15;
//            paint_small.setTextSize(textSize);
//
//        }
//        return paint_small;
//    }

//    private float getMidValue(String txt) {
//        float big = getPaint().measureText(txt);
//
//        float small = paint_small.measureText(txt);
//
//        return big / small;
//
//
//    }


    public void setExpandListener(OnExpandListener listener) {
        mOnExpandListener = listener;
    }

    private Layout getValidLayout() {
        return mLayout != null ? mLayout : getLayout();
    }

    private void toggle() {
        switch (mCurrState) {
            case STATE_SHRINK:
                mCurrState = STATE_EXPAND;
                if (mOnExpandListener != null) {
                    mOnExpandListener.onExpand(this);
                }
                break;
            case STATE_EXPAND:
                mCurrState = STATE_SHRINK;
                if (mOnExpandListener != null) {
                    mOnExpandListener.onShrink(this);
                }
                break;
        }
        setTextInternal(getNewTextByConfig(), mBufferType);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        mOrigText = text;
        mBufferType = type;
        setTextInternal(getNewTextByConfig(), type);
    }

    private void setTextInternal(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    private int getLengthOfString(String string) {
        if (string == null)
            return 0;
        return string.length();
    }

    private String getContentOfString(String string) {
        if (string == null)
            return "";
        return string;
    }


    public interface OnExpandListener {
        void onExpand(ExpandableTextView view);

        void onShrink(ExpandableTextView view);
    }

    private class ExpandableClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            toggle();
        }
    }

    public OnClickListener getOnClickListener(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return getOnClickListenerV14(view);
        } else {
            return getOnClickListenerV(view);
        }
    }

    private OnClickListener getOnClickListenerV(View view) {
        OnClickListener retrievedListener = null;
        try {
            Field field = Class.forName(CLASS_NAME_VIEW).getDeclaredField("mOnClickListener");
            field.setAccessible(true);
            retrievedListener = (OnClickListener) field.get(view);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retrievedListener;
    }

    private OnClickListener getOnClickListenerV14(View view) {
        OnClickListener retrievedListener = null;
        try {
            Field listenerField = Class.forName(CLASS_NAME_VIEW).getDeclaredField("mListenerInfo");
            Object listenerInfo = null;

            if (listenerField != null) {
                listenerField.setAccessible(true);
                listenerInfo = listenerField.get(view);
            }

            Field clickListenerField = Class.forName(CLASS_NAME_LISTENER_INFO).getDeclaredField("mOnClickListener");

            if (clickListenerField != null && listenerInfo != null) {
                clickListenerField.setAccessible(true);
                retrievedListener = (OnClickListener) clickListenerField.get(listenerInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retrievedListener;
    }

    private boolean isDoExtral = false;

    /**
     * Copy from:
     * http://stackoverflow.com/questions
     * /20856105/change-the-text-color-of-a-single-clickablespan-when-pressed-without-affecting-o
     * By:
     * Steven Meliopoulos
     */
    private class TouchableSpan extends ClickableSpan {
        private boolean mIsPressed;

        public void setPressed(boolean isSelected) {
            mIsPressed = isSelected;
        }

        @Override
        public void onClick(View widget) {
            if (!isTextCanTouch)
                return;
            if (hasOnClickListeners()
                    && (getOnClickListener(ExpandableTextView.this) instanceof ExpandableClickListener)) {
            } else {
                isDoExtral = true;
                toggle();
            }
        }


        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            switch (mCurrState) {
                case STATE_SHRINK:
                    ds.setColor(mToExpandHintColor);
                    ds.bgColor = mIsPressed ? mToExpandHintColorBgPressed : 0;
                    break;
                case STATE_EXPAND:
                    ds.setColor(mToShrinkHintColor);
                    ds.bgColor = mIsPressed ? mToShrinkHintColorBgPressed : 0;
                    break;
            }
            ds.setUnderlineText(false);
        }
    }


    /**
     * Copy from:
     * http://stackoverflow.com/questions
     * /20856105/change-the-text-color-of-a-single-clickablespan-when-pressed-without-affecting-o
     * By:
     * Steven Meliopoulos
     */
    public class LinkTouchMovementMethod extends LinkMovementMethod {
        private TouchableSpan mPressedSpan;

        @Override
        public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mPressedSpan = getPressedSpan(textView, spannable, event);
                if (mPressedSpan != null) {
                    mPressedSpan.setPressed(true);
                    Selection.setSelection(spannable, spannable.getSpanStart(mPressedSpan),
                            spannable.getSpanEnd(mPressedSpan));
                }
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                TouchableSpan touchedSpan = getPressedSpan(textView, spannable, event);
                if (mPressedSpan != null && touchedSpan != mPressedSpan) {
                    mPressedSpan.setPressed(false);
                    mPressedSpan = null;
                    Selection.removeSelection(spannable);
                }
            } else {
                if (mPressedSpan != null) {
                    mPressedSpan.setPressed(false);
                    super.onTouchEvent(textView, spannable, event);
                }
                mPressedSpan = null;
                Selection.removeSelection(spannable);
            }
            return true;
        }

        private TouchableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= textView.getTotalPaddingLeft();
            y -= textView.getTotalPaddingTop();

            x += textView.getScrollX();
            y += textView.getScrollY();

            Layout layout = textView.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            TouchableSpan[] link = spannable.getSpans(off, off, TouchableSpan.class);
            TouchableSpan touchedSpan = null;
            if (link.length > 0) {
                touchedSpan = link[0];
            }
            return touchedSpan;
        }


    }

    public void clearSelf() {
        if (this != null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            mTouchableSpan = null;
            ViewParent parent = ExpandableTextView.this.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(ExpandableTextView.this);
            }

            try {
            } catch (Throwable ex) {

            }


            Class etv = this.getClass();//得到当前的类
            Class tv = etv.getSuperclass();//得到当前类的父类
            Field mChangeWatcher = null;//Field  用来存放变量的
            try {
                //获取mSpinnerFinalOffset这个名称变量
                mChangeWatcher = etv.getDeclaredField("mChangeWatcher");
                //因为这个变量是私有的所以开启权限设置true
                mChangeWatcher.setAccessible(true);
                mChangeWatcher.set(etv, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}