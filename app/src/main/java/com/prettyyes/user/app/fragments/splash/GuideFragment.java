package com.prettyyes.user.app.fragments.splash;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.ViewPagerLocalAdapter;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.core.AdvImgHandler;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class GuideFragment extends BaseFragment {

    @ViewInject(R.id.vp_guidefragment_showimg)
    private ViewPager vp_showimg;

    @Override
    public int bindLayout() {
        return R.layout.fragment_guide;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

        ArrayList<Integer> data = new ArrayList<>();
        data.add(R.mipmap.ic_guide_one);
        data.add(R.mipmap.ic_guide_two);
        data.add(R.mipmap.ic_guide_three);
        ViewPagerLocalAdapter adapter = new ViewPagerLocalAdapter(getActivity(), vp_showimg);
        vp_showimg.setAdapter(adapter);
        adapter.addAll(data);
        new AdvImgHandler().loadAdv(getActivity());//下载图片
    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
