package com.prettyyes.user.app.adapter.order;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.mvpPresenter.OrderCheckPresenter;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.lvandgrid.MyListView;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.order
 * Author: SmileChen
 * Created on: 2016/10/27
 * Description: Nothing
 */
public class CheckOrderOneAdapter extends SpecilAbsAdapter<OrderCheckPresenter.ListOne> {

    private boolean isfirst = true;


    private TextView tv_sellername;
    private TextView tv_totalprice;
    private EditTextDel etv_write;
    private MyListView lv;
    private ImageView img_head;
    private TextView tv_nickname;
    private TextView tv_act;
    private RelativeLayout rel_kolinfo;
    private TextView tv_totalnum;
    private myWatcher mWatcher;


    public CheckOrderOneAdapter(Context context) {
        super(R.layout.item_lv_checkorder_one, new ArrayList<OrderCheckPresenter.ListOne>(), context);

    }

    private int height = 0;

    @Override
    public void bindView(ViewHolder vHolder) {
        tv_sellername = (TextView) vHolder.getView(R.id.tv_checkorderAdpone_sellername);
        tv_totalprice = (TextView) vHolder.getView(R.id.tv_checkorderAdpone_totalprice);
        etv_write = (EditTextDel) vHolder.getView(R.id.etv_checkorderAdpone);
        lv = (MyListView) vHolder.getView(R.id.lv_checkorderAdpone);
        img_head = (ImageView) vHolder.getView(R.id.img_checkorderAdpone_headimg);
        tv_nickname = (TextView) vHolder.getView(R.id.tv_checkorderAdpone_nickname);
        tv_act = (TextView) vHolder.getView(R.id.tv_checkorderAdpone_act);
        rel_kolinfo = (RelativeLayout) vHolder.getView(R.id.rel_checkorderAdpone_kolinfo);
        tv_totalnum = (TextView) vHolder.getView(R.id.tv_checkorderAdpone_totalnum);

    }

    public int index = -1;
    public int mTouchItemPosition = 0;

    @Override
    public void showData(ViewHolder vHolder, final OrderCheckPresenter.ListOne data, final int position) {


        AppUtil.loadTypaeFace(context, tv_totalprice);
        etv_write.setTag(data);

        etv_write.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (canVerticalScroll((EditTextDel) v)) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }

                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    index = position;
                }
                return false;
            }
        });

        etv_write.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            //设置焦点监听，当获取到焦点的时候才给它设置内容变化监听解决卡的问题

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextDel et = (EditTextDel) v;
                if (mWatcher == null) {
                    mWatcher = new myWatcher();
                }
                if (hasFocus) {
                    et.addTextChangedListener(mWatcher);//设置edittext内容监听
                } else {
                    et.removeTextChangedListener(mWatcher);
                }

            }
        });


        etv_write.clearFocus();//防止点击以后弹出键盘，重新getview导致的焦点丢失
        if (index != -1 && index == position) {
            // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
            etv_write.requestFocus();
        }
        etv_write.setText(datas.get(position).getRemark());//这一定要放在clearFocus()之后，否则最后输入的内容在拉回来时会消失
        etv_write.setSelection(etv_write.getText().length());
        tv_sellername.setText(data.getSeller_name());
        tv_totalprice.setText(Constant.RMB + data.getTotalprice());
        ImageLoadUtils.loadHeadImg(context, data.getSeller_headimg(), img_head);
        BadgeView badgeView2 = new BadgeView(context);
        badgeView2.initNopadding(44);
        badgeView2.setTargetView(img_head);
        badgeView2.setImageTag(AppUtil.isFamous(data.getFamous_type()), 44);

        tv_nickname.setText(data.getSeller_name());
        tv_act.setText(data.getAct());
        tv_totalnum.setText(String.format("共%s件商品", data.getTotalnumber()));

        rel_kolinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolInfoActivity(context,data.getSeller_id());
//                KolInfoActivity.goKolInfo(context, Integer.parseInt(data.getSeller_id()));
            }
        });


        RelativeLayout.LayoutParams la = (RelativeLayout.LayoutParams) lv.getLayoutParams();
        for (int j = 0; j < data.getSuit().size(); j++) {
            if (data.getSuit().get(j).getType().equals("title")) {
                height += 30;
            } else {
                height += 94;
            }
        }
        la.height = height;

        lv.setAdapter(new CheckOrderTwoAdapter(data.getSuit(), context));


    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动  false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    class myWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub


        }

        @Override
        public void afterTextChanged(Editable s) {
            datas.get(index).setRemark(s.toString());//为输入的位置内容设置数组管理器，防止item重用机制导致的上下内容一样的问题
        }

    }


}
