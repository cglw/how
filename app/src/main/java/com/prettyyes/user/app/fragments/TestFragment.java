package com.prettyyes.user.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.prettyyes.user.R;

import com.prettyyes.user.app.base.BaseFragment;

public class TestFragment extends BaseFragment {


    @Override
    public int bindLayout() {
        return R.layout.fragment_test;
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
        //initPullListSet(pullList);


    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }
}
