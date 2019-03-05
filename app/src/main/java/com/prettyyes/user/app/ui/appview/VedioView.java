package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;


/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/12/16
 * Description: Nothing
 */
public class VedioView extends AbsLinearlayoutView {
    public VedioView(Context context) {
        super(context);
    }

    public VedioView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public int bindLayout() {
        return R.layout.item_mianlayout_typevedio;
    }

    @Override
    public void initViews() {
//        vedio_otherqueAdp = (AppBaseVedioPlayer) getView(R.id.vedio_otherqueAdp);
    }

    @Override
    public void setDataByModel(Object data) {

    }

    @Override
    public void setData(String... data) {

        for (int i = 0; i < data.length; i++) {
            String value = data[i];
            switch (i) {
                case 0:
                    break;
                case 1:
                    break;

            }
        }
    }
}
