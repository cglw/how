package com.prettyyes.user.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.SpuRecommendRequest;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.adapter.detail.RecommendVpAdapter;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


public class RecommondFragment extends BaseFragment {


    //    @ViewInject(R.id.lin_suitdelAct_indexRecommend)
//    private LinearLayout lin_recommendindex;//推荐的指示器
    @ViewInject(R.id.lin_recommend)
    private LinearLayout lin_recommend;//跟布局
    @ViewInject(R.id.tv_recommend)
    private TextView tv_recommend;//推荐的指示器
    @ViewInject(R.id.vp_suitdelAct_recommend)
    private AutoViewPager vp_recommend;
    @ViewInject(R.id.tv_chat_kf)
    private TextView tv_chat_kf;

    public static RecommondFragment newInstance(SpuInfoEntity spuInfoEntity) {
        RecommondFragment recommondFragment = new RecommondFragment();
        Bundle args = new Bundle();
        args.putSerializable("SpuInfoEntity", spuInfoEntity);
        recommondFragment.setArguments(args);
        return recommondFragment;
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_brand_recommend;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

        this.spuInfoEntity = (SpuInfoEntity) parms.getSerializable("SpuInfoEntity");

    }

    private SpuInfoEntity spuInfoEntity;

    private RecommendVpAdapter recommendVpAdapter;

    private void initVpRecommend() {
        recommendVpAdapter = new RecommendVpAdapter(getActivity());
//        vp_recommend.setOverScrollMode(OVER_SCROLL_NEVER);//去掉边界

        new SpuRecommendRequest().setModule_id(spuInfoEntity.getModule_id()).setSpu_type(spuInfoEntity.getSpu_type()).post(new NetReqCallback<List<SpuInfoEntity>>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(List<SpuInfoEntity> spuInfoEntities, String method) {
                if (spuInfoEntities.size() > 0) {
                    tv_recommend.setText(spuInfoEntities.get(0).getSpu_name());
                    recommendVpAdapter.addAll((ArrayList<SpuInfoEntity>) spuInfoEntities);
                    vp_recommend.setIsneedIndict(true);
                    vp_recommend.setAbsVpAdapter(recommendVpAdapter);
                    vp_recommend.initIndicator();

                } else {
                    lin_recommend.setVisibility(View.GONE);

                }

            }
        });
    }

    @Override
    public void setListener() {
        super.setListener();
        tv_chat_kf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDataRepo.getAccountManager().chatWithkf();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (vp_recommend != null)
            vp_recommend.clearSelf();
    }

    @Override
    public void initView(View view) {
        initVpRecommend();

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }
}
