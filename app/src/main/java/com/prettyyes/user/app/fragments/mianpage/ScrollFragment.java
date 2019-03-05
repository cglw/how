package com.prettyyes.user.app.fragments.mianpage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseFragment;

/**
 * Created by chengang on 2017/8/3.
 */

public class ScrollFragment extends BaseFragment {


    @Override
    public int bindLayout() {
        return R.layout.fragment_scrollview;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
//        OperateFragment operateFragment = new OperateFragment();
//        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.framelayout, operateFragment).commit();

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }
}
