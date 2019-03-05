package com.prettyyes.user.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.DensityUtil;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/10/26
 * Description: Nothing
 */
public class PupopAmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private int amount = 1; //购买数量
    private int goods_storage = 100; //商品库存

    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private TextView btnDecrease;
    private TextView btnIncrease;

    public PupopAmountView(Context context) {
        this(context, null);
    }

    public PupopAmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount_puponselectnum, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (TextView) findViewById(R.id.btnDecrease);
        btnIncrease = (TextView) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, DensityUtil.dip2px(30));
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, DensityUtil.dip2px(30));
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, DensityUtil.dip2px(12));
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams( LayoutParams.MATCH_PARENT,btnWidth);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams( LayoutParams.MATCH_PARENT,tvWidth);
        etAmount.setLayoutParams(textParams);

    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }
    public void setGoods_Num(int goodsnum) {
        this.amount = goodsnum;
        etAmount.setText(goodsnum + "");
    }
    public int getGoodNum()
    {
        return amount;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setText(amount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
            }
        }

        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

}
