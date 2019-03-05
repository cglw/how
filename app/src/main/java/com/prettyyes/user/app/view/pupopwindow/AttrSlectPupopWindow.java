package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.UnitAttrVpAdapter;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/7/2.
 */

public class AttrSlectPupopWindow extends AbsPupopWindow {
    ViewPager viewPager;

    public AttrSlectPupopWindow(Activity context, ArrayList<SpuInfoEntity> unitDetailBeens) {
        super(context);
        if (viewPager != null) {
            unitAttrVpAdapter = new UnitAttrVpAdapter(activity,unitDetailBeens);
            viewPager.setOffscreenPageLimit(unitAttrVpAdapter.getCount());
            viewPager.setAdapter(unitAttrVpAdapter);
            unitAttrVpAdapter.setAbsPupopWindow(this);
        }

    }




    @Override
    public void bindView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.vp);


    }

    public UnitAttrVpAdapter getUnitAttrVpAdapter() {
        return unitAttrVpAdapter;
    }

    private UnitAttrVpAdapter unitAttrVpAdapter;


    @Override
    public int getLayout() {
        return R.layout.pupop_attr_select;
    }

    @Override
    public int getLayoutHeight() {
        return 0;
    }

    @Override
    public void setHeightBy() {
        super.setHeightBy();
        setHeight(AppUtil.dip2px(300));
    }
}
