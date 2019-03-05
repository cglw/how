package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by chengang on 2017/6/2.
 */

public class BottomBuyView extends AbsLinearlayoutView {
    public BottomBuyView(Context context) {
        super(context);
    }

    public BottomBuyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @ViewInject(R.id.btn_common_addcart)
    private Button button_addcart;
    @ViewInject(R.id.btn_common_buy)
    private Button button_buy;
    @ViewInject(R.id.tv_common_price)
    private TextView tv_price;


    @Override
    public int bindLayout() {
        return R.layout.layout_appbottom_addwishlist;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void setDataByModel(Object data) {

    }

    public void setPrice(String price) {
        tv_price.setText(String.format(String.format("¥:%s", price)));
    }
}
