package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;

/**
 * Created by chengang on 2017/3/30.
 */

public class HomeAskView extends AbsRelativelayoutView {
    public HomeAskView(Context context) {
        super(context);
    }

    public HomeAskView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private View view_ask;
    private View view_btnbg;
    private TextView tv_askView_asktext;

    @Override
    public int bindLayout() {
        return R.layout.layout_homepage_send_question;
    }

    @Override
    public void initViews() {


        view_btnbg = getView(R.id.view_aksview_btnbg);
        tv_askView_asktext = (TextView) getView(R.id.tv_askView_asktext);
        view_ask = getView(R.id.view_aksview_ask);
    }

    @Override
    public void setListener() {
        super.setListener();
//        ((View) tv_askView_asktext.getParent()).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AskActivity.goAskActivity(getContext());
//
//            }
//        });
    }

    @Override
    public void setDataByModel(Object data) {

    }

    public void setAskAndColor(String asktv, String color) {
        tv_askView_asktext.setText(asktv);

        if (color.equals("home_ask_button_type2"))
            view_btnbg.setBackgroundResource(R.mipmap.home_yellow_ask_icon);
        else
            view_btnbg.setBackgroundResource(R.mipmap.home_red_ask_icon);

    }
}
