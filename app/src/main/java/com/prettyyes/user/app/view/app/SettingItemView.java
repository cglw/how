package com.prettyyes.user.app.view.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;


/**
 * Created by Hornen on 15/11/18.
 * 我的页面item项
 */
public class SettingItemView extends LinearLayout {
    private RelativeLayout settingItem;
    private RoundView roundview_unread;
    private ImageView ivArrow;//?
    private TextView tvLeft, tvRight;//????
    private View split;//????
    private AttributeSet attrs;


    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs = attrs;
        initView(context);
    }

    public SettingItemView(Context context) {

        super(context);
        initView(context);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        initView(context);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_person_list, this);
        settingItem = (RelativeLayout) view.findViewById(R.id.settingItem);
        ivArrow = (ImageView) view.findViewById(R.id.ivArrow);
        tvLeft = (TextView) view.findViewById(R.id.tvSettingText);
        tvRight = (TextView) view.findViewById(R.id.tvTip);
        split = view.findViewById(R.id.view_split);
        roundview_unread = (RoundView) view.findViewById(R.id.roundview_unread);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);


        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.SettingItemView_leftTitle:
                    tvLeft.setText(a.getString(index));
                    break;
                case R.styleable.SettingItemView_rightContent:
                    tvRight.setText(a.getString(index));
                    break;

            }
        }
        a.recycle();


    }

    public void setUnread(int count) {
        if (count > 0) {
            roundview_unread.setVisibility(VISIBLE);
        } else {
            roundview_unread.setVisibility(GONE);

        }

    }


    public void setOnItemClickListener(OnClickListener listener) {
        settingItem.setOnClickListener(listener);
    }

    public void ishaveselect(boolean ishave) {
        if (!ishave) {
            // settingItem.setBackgroundResource(R.drawable.ui_noround_white);
        }
    }

    public void setItemTip(int resId) {
//        if(resId == R.string.command_value_hidden) {
//            tvTip.setVisibility(View.INVISIBLE);
//            return;
//        }

        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(resId);
    }

    public void setRightText(String tip) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(tip);
    }

    public String getRightText() {
        return tvRight.getText().toString();
    }

    public TextView getRightView() {
        return tvRight;
    }

    public void hideItemTip() {
        tvRight.setVisibility(View.INVISIBLE);
    }

    public void setItemText(int resId) {
//        if(resId == R.string.command_value_hidden) {
//            tvText.setVisibility(View.INVISIBLE);
//            return;
//        }

        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText(resId);
    }

    public void setLeftText(String text) {
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText(text);
    }

    public String getLeftTv() {
        return tvLeft.getText().toString();
    }

    public void hideItemText() {
        tvLeft.setVisibility(View.GONE);
    }

    public void hideArrow() {
        ivArrow.setVisibility(View.GONE);
    }

    public void showSplit() {
        split.setVisibility(VISIBLE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
