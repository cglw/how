package com.prettyyes.user.app.view.tvbtnetv;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.prettyyes.user.R;


/**
 * 带删除的edittext
 * Created by Cg on 2016/3/29.
 */
public class EditTextDel extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearDrawable;
    private boolean hasFoucs;

    public EditTextDel(Context context) {
        this(context, null);
    }

    public EditTextDel(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public EditTextDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    @Override
    public boolean onTextContextMenuItem(int id) {
        final int ID_PASTE = android.R.id.paste;
        String TAG = "TAG2";

        if (id == ID_PASTE) {
            if (onTextContextChange != null)
                onTextContextChange.paste();
        }
        return super.onTextContextMenuItem(id);
    }

    public onTextContextChange onTextContextChange;

    public void setOnTextContextChange(EditTextDel.onTextContextChange onTextContextChange) {
        this.onTextContextChange = onTextContextChange;
    }

    public interface onTextContextChange {
        void paste();
    }

    private void init() {
        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片,获取图片的顺序是左上右下（0,1,2,3,）
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(
                    R.mipmap.icon_del);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
                mClearDrawable.getIntrinsicHeight());
        // 默认设置隐藏图标
        setClearIconVisible(false);

        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        super.addTextChangedListener(watcher);

    }


    public void addMyTextChangedListener(final ChangeCallback changeCallback) {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (changeCallback != null)
                    changeCallback.change(EditTextDel.this, s.toString());
            }
        });
    }

    public interface ChangeCallback {
        public void change(EditTextDel editTextDel, String tv);
    }

    /* @说明：isInnerWidth, isInnerHeight为ture，触摸点在删除图标之内，则视为点击了删除图标
         * event.getX() 获取相对应自身左上角的X坐标
         * event.getY() 获取相对应自身左上角的Y坐标
         * getWidth() 获取控件的宽度
         * getHeight() 获取控件的高度
         * getTotalPaddingRight() 获取删除图标左边缘到控件右边缘的距离
         * getPaddingRight() 获取删除图标右边缘到控件右边缘的距离
         * isInnerWidth:
         *  getWidth() - getTotalPaddingRight() 计算删除图标左边缘到控件左边缘的距离
         *  getWidth() - getPaddingRight() 计算删除图标右边缘到控件左边缘的距离
         * isInnerHeight:
         *  distance 删除图标顶部边缘到控件顶部边缘的距离
         *  distance + height 删除图标底部边缘到控件顶部边缘的距离
         */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height) / 2;
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y < (distance + height);
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                    if (clearCallback != null) {
                        clearCallback.call();
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，
     * 输入长度为零，隐藏删除图标，否则，显示删除图标
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
            if (showHideback != null) {
                showHideback.show(true);
            }
        } else {
            setClearIconVisible(false);
            if (showHideback != null) {
                showHideback.show(false);
            }
        }
    }

    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void setClearback(ClearCallback clearback) {
        this.clearCallback = clearback;
    }

    public void setShowback(ShowHideback showback) {
        this.showHideback = showback;
    }

    private ClearCallback clearCallback;
    private ShowHideback showHideback;

    public interface ClearCallback {
        public void call();
    }

    public interface ShowHideback {
        public void show(boolean show);
    }

    public void clearSelf() {
        if (this != null) {
            ViewParent parent = this.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this);
            }
        }
    }


}