package com.prettyyes.user.app.fragments.brand;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.xutils.view.annotation.ViewInject;


public class BrandInfoFragment extends BaseFragment {


    @ViewInject(R.id.kolSimpleInfoView)
    private KolSimpleInfoView kolSimpleInfoView;


    public static BrandInfoFragment newInstance(SellerInfoEntity sellerInfoEntity) {
        BrandInfoFragment recommondFragment = new BrandInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable("sellerInfoEntity", sellerInfoEntity);
        recommondFragment.setArguments(args);
        return recommondFragment;
    }


    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_brand_info;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }


    @Override
    public void setListener() {
        super.setListener();

    }

    @Override
    public void initView(View view) {

        SellerInfoEntity seller_info = (SellerInfoEntity) getArguments().getSerializable("sellerInfoEntity");
        if (seller_info != null) {
            kolSimpleInfoView.setSellerInfo(seller_info);

        }

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }
}
