package com.prettyyes.user.app.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.view.pupopwindow.AbsPupopWindow;
import com.prettyyes.user.core.event.InputCallback;
import com.prettyyes.user.core.event.PriceEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;

/**
 * Created by chengang on 2017/5/16.
 */

public class PriceAndfreightDialog extends AbsPupopWindow {
    public PriceAndfreightDialog(Activity context) {
        super(context);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


    }


    public boolean isfromFright = false;

    public PriceAndfreightDialog setIsfromFright(boolean isfromFright) {
        this.isfromFright = isfromFright;
        return this;
    }

    private String hint;

    public void setHint(String hint) {
        this.hint = hint;
    }

    private EditText edit_price;
    private EditText edit_freight;

    public PriceAndfreightDialog setEdit_freight(String price) {
        edit_freight.setText(price);
        if (price != null)
            edit_freight.setSelection(price.length());
        return this;
    }

    public PriceAndfreightDialog setEdit_price(String price) {
        edit_price.setText(price);
        if (price != null)
            edit_price.setSelection(price.length());


        return this;

    }

    @Override
    public void show(View view) {
        super.show(view);

        if (isfromFright) {
            edit_freight.requestFocus();
        }
    }

    @Override
    public void bindView(View view) {
        edit_freight = (EditText) view.findViewById(R.id.edit_freight);
        edit_price = (EditText) view.findViewById(R.id.edit_price);

//        edit_price.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                return false;
//            }
//        });
        edit_freight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == KeyEvent.ACTION_DOWN) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_freight.getWindowToken(), 0);
                }

                return false;
            }
        });

        edit_freight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().startsWith("."))
                    edit_freight.setText("");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().startsWith("."))
                    edit_price.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private String content = "";
    private InputCallback inputCallback;

    public void setInputCallback(InputCallback inputCallback) {
        this.inputCallback = inputCallback;
    }

    private int lastHeight;

    @Override
    public void setListener() {
        super.setListener();

        SoftKeyboardUtil.observeSoftKeyboard(activity, new SoftKeyboardUtil.OnSoftKeyboardChangeListener() {
            @Override
            public void onSoftKeyBoardChange(int softKeybardHeight, int top, boolean visible) {

                if (softKeybardHeight < lastHeight) {
                    dismiss();
                }
                lastHeight = softKeybardHeight;


            }
        });

    }


    @Override
    public int getLayout() {
        return R.layout.popup_edit_price_freight;
    }

    @Override
    public int getLayoutHeight() {
        return 0;
    }

    @Override
    public void setHeightBy() {
        super.setHeightBy();
        setHeight((int) activity.getResources().getDimension(R.dimen.template_input_height));
    }

    @Override
    public void dismiss() {
        super.dismiss();
        PriceEvent o = new PriceEvent();

        String freight_price = edit_freight.getText().toString();
        if (freight_price.length() < 1)
            freight_price = "0";
        o.setFreight_price(freight_price);

        String goods_price = edit_price.getText().toString();
        if (goods_price.length() < 1)
            goods_price = "0";
        o.setGoods_price(goods_price);
        AppRxBus.getInstance().post(o);
    }
}
