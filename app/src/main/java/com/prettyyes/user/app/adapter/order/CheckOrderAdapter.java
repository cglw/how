package com.prettyyes.user.app.adapter.order;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.OrderCheckItemModel;

import static com.prettyyes.user.core.utils.ViewUtils.canVerticalScroll;

/**
 * Created by chengang on 2017/7/4.
 */

public class CheckOrderAdapter extends AbsRecycleAdapter<OrderCheckItemModel> {
    public CheckOrderAdapter(Context context) {
        super(context, R.layout.item_lv_checkorder_new);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, OrderCheckItemModel data, int position) {
        view_top.setVisibility(View.GONE);
        view_bottom.setVisibility(View.GONE);
        view_center.setVisibility(View.GONE);
        switch (data.getItem_type()) {
            case OrderCheckItemModel.TYPE_TOP:
                view_top.setVisibility(View.VISIBLE);
                setTopdata(data);
                break;
            case OrderCheckItemModel.TYPE_CENTER:
                view_center.setVisibility(View.VISIBLE);
                setCenterdata(data, position);
                break;
            case OrderCheckItemModel.TYPE_BOTTOM:
                view_bottom.setVisibility(View.VISIBLE);
                setBottomdata(data);
                break;
        }


    }

    private void setTopdata(OrderCheckItemModel data) {
        kolSimpleInfoView.setSellerInfo(data.getSellerInfoEntity());
    }

    private void setCenterdata(final OrderCheckItemModel data, final int position) {
        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_covey);
        tv_name.setText(data.getSpu_name());
        tv_price.setText(StringUtils.getPrice(data.getSpu_price()));
        tv_num.setText("X" + data.getNum() + "");
        tv_attr_str.setText(StringUtils.getAttrs(data.getAttr_value_text()));

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
//                    etv_write.clearFocus();
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
        etv_write.setText(data.getRemark());//这一定要放在clearFocus()之后，否则最后输入的内容在拉回来时会消失
        etv_write.setSelection(etv_write.getText().length());


    }

    public int index = -1;

    private void setBottomdata(OrderCheckItemModel data) {
        tv_total_express.setText(StringUtils.getPrice(data.getExpress_price()));
        StringUtils.addStyleSpan(tv_total_num, String.format("共%s件商品", data.getTotalnumber()));
        tv_total_price.setText(StringUtils.getPrice(data.getTotalprice()));

    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        view_top = holder.getView(R.id.view_top);
        view_center = holder.getView(R.id.view_center);
        kolSimpleInfoView = holder.getView(R.id.KolSimpleInfoView);
        img_covey = holder.getView(R.id.img_covey);
        tv_name = holder.getView(R.id.tv_name);
        tv_price = holder.getView(R.id.tv_price);
        tv_num = holder.getView(R.id.tv_num);
        tv_total_num = holder.getView(R.id.tv_total_num);
        view_bottom = holder.getView(R.id.view_bottom);
        etv_write = holder.getView(R.id.edit_remark);
        tv_total_price = holder.getView(R.id.tv_total_price);
        tv_total_express = holder.getView(R.id.tv_total_express);
        tv_attr_str = holder.getView(R.id.tv_attr_str);

    }

    private RelativeLayout view_top;
    private View view_center;
    private KolSimpleInfoView kolSimpleInfoView;
    private ImageView img_covey;
    private TextView tv_name;
    private TextView tv_price;
    private TextView tv_num;
    private TextView tv_total_num;
    private LinearLayout view_bottom;
    private EditTextDel etv_write;
    private TextView tv_attr_str;
    private TypefaceTextView tv_total_price;
    private TypefaceTextView tv_total_express;
    private myWatcher mWatcher;

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
            getItems().get(index).setRemark(s.toString());//为输入的位置内容设置数组管理器，防止item重用机制导致的上下内容一样的问题
        }

    }
}
