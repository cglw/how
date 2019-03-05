package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.detail.PupopSelectAdapter;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.mvpPresenter.SuitDelPresenter;
import com.prettyyes.user.core.utils.DensityUtil;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/7/29
 * Description: Nothing
 */
public class SelectGoodNumPopup extends PopupWindow {


    private View mMenuView;
    private Activity activity;
    private ListView lv;
    private Button btn_confirm;
    private ImageView img_close;
    private RadioGroup rgp_selectnum;

    public interface ClickCallback {
        public void click(View view);
    }

    public boolean ishaveSlectSize() {
        for (int i = 0; i < rgp_selectnum.getChildCount(); i++) {
            if (((RadioButton) rgp_selectnum.getChildAt(i)).isChecked())
                return true;
        }
        return false;
    }

    public String getSelectSize() {
        if (datas.size() > 0)
            return datas.get(0).getSize();
        return "";
    }


    public ArrayList<SuitDelPresenter.SelectModel> getData() {
        return this.datas;
    }

    private PupopSelectAdapter pupopSelectAdapter;
    private ArrayList<SuitDelPresenter.SelectModel> datas = new ArrayList<>();

    public SelectGoodNumPopup(Activity context, final ArrayList<SuitDelPresenter.SelectModel> data, View.OnClickListener onClickListener) {
        super(context);
        this.activity = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.item_pupoplist_selectnum, null);
        lv = (ListView) mMenuView.findViewById(R.id.lv_pupoplSelectNum);
        btn_confirm = (Button) mMenuView.findViewById(R.id.btn_pupoplSelectNum);
        img_close = (ImageView) mMenuView.findViewById(R.id.img_pupoplSelectNum_close);
        rgp_selectnum = (RadioGroup) mMenuView.findViewById(R.id.rgp_selectnum);
        btn_confirm.setOnClickListener(onClickListener);

        this.datas.clear();
        this.datas.addAll(data);
        pupopSelectAdapter = new PupopSelectAdapter(datas, new PupopSelectAdapter.ClickCallback() {
            @Override
            public void backprice() {
                setBtnTv();
            }
        }, activity);

        initSize();
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }

        });
        rgp_selectnum.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (group.getChildAt(i).getId() == checkedId) {
                        String tv = ((RadioButton) group.getChildAt(i)).getText().toString();
                        for (int j = 0; j < SelectGoodNumPopup.this.datas.size(); j++) {
                            SelectGoodNumPopup.this.datas.get(j).setSize(tv);
                        }
                        return;
                    }
                }
            }
        });


        lv.setAdapter(pupopSelectAdapter);
        setBtnTv();

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        if (data.size() < 3) {
            this.setHeight(DensityUtil.dip2px(170 + 110 * data.size()));
        } else {
            this.setHeight(DensityUtil.dip2px(500));
        }
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        // this.setAnimationStyle(R.style.AnimBottom);
        this.setAnimationStyle(R.style.animon);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x60000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.7f;
        activity.getWindow().setAttributes(lp);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框

        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                // int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int height = mMenuView.findViewById(R.id.rel_puponlv_base).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });


    }

    private void initSize() {
        for (int i = 0; i < rgp_selectnum.getChildCount(); i++) {
            String tv = ((RadioButton) rgp_selectnum.getChildAt(i)).getText().toString();
            if (tv.equals(datas.get(0).getSize())) {
                ((RadioButton) rgp_selectnum.getChildAt(i)).setChecked(true);
                return;
            }

        }
    }

    private void setBtnTv() {
        float totalprice = 0;
        for (int i = 0; i < pupopSelectAdapter.getData().size(); i++) {
            totalprice += pupopSelectAdapter.getData().get(i).getNum() * pupopSelectAdapter.getData().get(i).getPrice();
        }
        if (totalprice > 0)
            btn_confirm.setText("确 定" + "(" + Constant.RMB + totalprice + ")");
        else
            btn_confirm.setText("确 定");
    }

    public void show(int id) {
        this.showAtLocation(activity.findViewById(id), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

}
