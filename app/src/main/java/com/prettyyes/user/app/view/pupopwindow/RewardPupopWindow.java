package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.core.order.OrderManager;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.pupopwindow
 * Author: SmileChen
 * Created on: 2016/11/28
 * Description: Nothing
 */
public class RewardPupopWindow extends AbsPupopWindow {
    public RewardPupopWindow(Activity context) {
        super(context);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    private LinearLayout lin_pupoppaper_pay;
    private TextView tv_rewardpupop_price;
    private LinearLayout lin_rewardpupop_one;
    private LinearLayout lin_rewardpupop_two;
    private EditText edit_rewardpupop;


    private String price = "0";
    private int paper_id;

    public RewardPupopWindow setPaper_id(int paper_id) {
        this.paper_id = paper_id;
        return this;
    }


    @Override
    public void bindView(View view) {
        lin_pupoppaper_pay = (LinearLayout) view.findViewById(R.id.lin_pupoppaper_pay);
        tv_rewardpupop_price = (TextView) view.findViewById(R.id.tv_rewardpupop_price);
        lin_rewardpupop_one = (LinearLayout) view.findViewById(R.id.lin_rewardpupop_one);
        lin_rewardpupop_two = (LinearLayout) view.findViewById(R.id.lin_rewardpupop_two);
        edit_rewardpupop = (EditText) view.findViewById(R.id.edit_rewardpupop);
    }

    @Override
    public void setListener() {
        super.setListener();
        lin_pupoppaper_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPrice().equals("0")) {
                    ((BaseActivity) activity).showToastShort("请先选择或输入金额");
                    return;
                }
                dismiss();
                new OrderManager(activity).OrderReward(((BaseActivity) activity).getUUId(), TYPE_PAPER, paper_id, 0, Float.parseFloat(getPrice()));
            }
        });

        edit_rewardpupop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setLinOneUncheck();
                setLinTwoUncheck();
            }

            @Override
            public void afterTextChanged(Editable s) {
                price=s.toString();
                tv_rewardpupop_price.setText("(" + Constant.RMB + s.toString() + ")");

            }
        });

        for (int i = 0; i < lin_rewardpupop_one.getChildCount(); i++) {
            RadioButton ra = (RadioButton) lin_rewardpupop_one.getChildAt(i);
            ra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit_rewardpupop.setText("");
                    setLinOneUncheck();
                    setLinTwoUncheck();
                    ((RadioButton) v).setChecked(true);
                    price = ((RadioButton) v).getText().toString();
                    tv_rewardpupop_price.setText("(" + price + ")");

                }
            });
        }

        for (int i = 0; i < lin_rewardpupop_two.getChildCount(); i++) {
            if (i < 2) {
                RadioButton ra = (RadioButton) lin_rewardpupop_two.getChildAt(i);
                ra.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edit_rewardpupop.setText("");
                        setLinTwoUncheck();
                        setLinOneUncheck();
                        ((RadioButton) v).setChecked(true);
                        price = ((RadioButton) v).getText().toString();
                        tv_rewardpupop_price.setText("(" + price + ")");
                    }
                });
            }
        }
    }


    public void setLinOneUncheck() {
        for (int i = 0; i < lin_rewardpupop_one.getChildCount(); i++) {
            RadioButton ra = (RadioButton) lin_rewardpupop_one.getChildAt(i);
            ra.setChecked(false);
        }
    }

    public void setLinTwoUncheck() {
        for (int i = 0; i < lin_rewardpupop_two.getChildCount(); i++) {
            if (i < 2) {
                RadioButton ra = (RadioButton) lin_rewardpupop_two.getChildAt(i);
                ra.setChecked(false);
            }
        }
    }

    public String getPrice() {
        if (price.contains(Constant.RMB))
            return price.replace(Constant.RMB, "");
        else
            return price;

    }

    @Override
    public int getLayout() {
        return R.layout.popup_reward;
    }

    @Override
    public int getLayoutHeight() {
        return 180;
    }
}
