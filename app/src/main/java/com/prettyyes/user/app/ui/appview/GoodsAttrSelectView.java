package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.app.view.FlowLayout;
import com.prettyyes.user.model.applocal.AttrName;

/**
 * Created by chengang on 2017/7/3.
 */

public class GoodsAttrSelectView extends AbsLinearlayoutView {


    private static final String TAG = "GoodsAttrSelectView";

    public GoodsAttrSelectView(Context context) {
        super(context);
    }

    public GoodsAttrSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_goodsattr_select;
    }

    private TextView tv_title;
    private FlowLayout flowLayout;

    @Override
    public void initViews() {
        tv_title = (TextView) getView(R.id.tv_title);
        flowLayout = (FlowLayout) getView(R.id.flowlayout);
    }

    public void setAttrName(final AttrName attrName) {
        tv_title.setText(attrName.getName());
        flowLayout.removeAllViews();
        for (int i = 0; i < attrName.getValues().size(); i++) {
            TextView inflate = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.layout_retange_tv, null);
            inflate.setText(attrName.getValues().get(i).getAttr_value());
            inflate.setClickable(true);
            inflate.setTag(i);
            setSelctBg(inflate, attrName.getValues().get(i).isSelect());
            inflate.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) v.getTag();

                    boolean select = !attrName.getValues().get(tag).isSelect();


                    FlowLayout parent = (FlowLayout) v.getParent();

                    for (int j = 0; j < parent.getChildCount(); j++) {
                        TextView childAt = (TextView) parent.getChildAt(j);
                        attrName.getValues().get(j).setSelect(false);
                        setSelctBg(childAt, false);
                    }


                    attrName.getValues().get(tag).setSelect(select);
                    setSelctBg((TextView) v, select);


                }
            });
            flowLayout.addView(inflate);
            inflate.setLayoutParams(new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        }


    }

    int max = 0;

    private void setSelctBg(TextView tv, boolean select) {
        if (select)
            tv.setBackgroundResource(R.drawable.bg_retange_red_2);
        else
            tv.setBackgroundResource(R.drawable.bg_retange_grey_2);
    }


    @Override
    public void setDataByModel(Object data) {

    }
}
