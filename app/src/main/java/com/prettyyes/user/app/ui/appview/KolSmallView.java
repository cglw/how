package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;

import com.prettyyes.user.R;
import com.prettyyes.user.model.v8.SellerInfoEntity;

/**
 * Created by chengang on 2017/7/31.
 */

public class KolSmallView extends KolSimpleInfoView {
    public KolSmallView(Context context) {
        super(context);
    }

    public KolSmallView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_kolsmall;
    }


    @Override
    public void setSellerInfo(SellerInfoEntity data) {
        setSellerInfo(data, 20);
    }
}
