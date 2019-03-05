package com.prettyyes.user.app.fragments.brand;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.view.app.AppWebView;

import org.xutils.view.annotation.ViewInject;


public class BrandDescFragment extends BaseFragment {


    @ViewInject(R.id.webview)
    private AppWebView appWebView;

    @Override
    public int bindLayout() {
        return R.layout.fragment_brand_desc;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {


    }
    public static BrandDescFragment newInstance(String desc) {
        BrandDescFragment recommondFragment = new BrandDescFragment();
        Bundle args = new Bundle();
        args.putString("desc", desc);
        recommondFragment.setArguments(args);
        return recommondFragment;
    }


    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    public void setListener() {
        super.setListener();
    }

    @Override
    public void initView(View view) {
        String desc = getArguments().getString("desc");
        appWebView.loadContent(desc);

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }
}
