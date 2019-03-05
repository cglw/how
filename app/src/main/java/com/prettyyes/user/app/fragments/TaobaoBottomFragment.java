package com.prettyyes.user.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.view.app.AppWebView;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;

public class TaobaoBottomFragment extends BaseFragment {

    @ViewInject(R.id.webview_taobao)
    private AppWebView webView;


    String html = "";

    private SpuInfoEntity taoBaoEntity;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    public static TaobaoBottomFragment newInstance(SpuInfoEntity taoBaoEntity) {
        TaobaoBottomFragment fragment = new TaobaoBottomFragment();
        Bundle args = new Bundle();
        args.putSerializable(TYPE_TAOBAO, taoBaoEntity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_taobao_bottom;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {
        taoBaoEntity = (SpuInfoEntity) parms.getSerializable(TYPE_TAOBAO);

    }

    @Override
    public void initView(View view) {

    }


    @Override
    public void doBusiness(Context mContext) {
        if (taoBaoEntity != null) {
            html = taoBaoEntity.getSpu_desc();
            webView.loadContent(html);
        }
    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

}
