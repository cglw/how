package com.prettyyes.user.app.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.detail.TaobaoDelVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.mvpPresenter.TaobaoDelPresenter;
import com.prettyyes.user.app.mvpView.TaobaoDelView;
import com.prettyyes.user.app.ui.spu.TaobaoActivity;
import com.prettyyes.user.app.view.ClipViewPager;
import com.prettyyes.user.app.view.tvbtnetv.DrawableCenterTextView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.ScalePageTransformer;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.AppConfig.PAGE_LIMIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;


public class TaobaoTopFragment extends BaseFragment implements TaobaoDelView {
    @ViewInject(R.id.lin_taobaodel_container)
    private LinearLayout lin_container;//viewpager根布局
    @ViewInject(R.id.vp_taobaodel_showimg)
    private ClipViewPager vp_img;
    @ViewInject(R.id.img_taobaodel_bigimg)
    private ImageView img_bigimg;
    @ViewInject(R.id.tv_taobaodelAct_price)
    private TextView tv_price;
    @ViewInject(R.id.tv_taobaodelAct_title)
    private TextView tv_title;


    @ViewInject(R.id.tv_taobaodelAct_share)
    private DrawableCenterTextView tv_share;
    private TaobaoDelVpAdapter taobaoDelVpAdapter;
    private SpuInfoEntity taobaoEntity;
    private TaobaoDelPresenter taobaoDelPresenter = new TaobaoDelPresenter(this);

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    public static TaobaoTopFragment newInstance(SpuInfoEntity taobaoEntity) {
        TaobaoTopFragment fragment = new TaobaoTopFragment();
        Bundle args = new Bundle();
        args.putSerializable(TYPE_TAOBAO, taobaoEntity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_taobao_top;
    }

    @Override
    public View bindView() {
        return null;
    }


    @Override
    public void initParms(Bundle parms) {
        taobaoEntity = (SpuInfoEntity) parms.getSerializable(TYPE_TAOBAO);


    }

    @Override
    public void initView(View view) {
        taobaoDelVpAdapter = new TaobaoDelVpAdapter(getActivity());
        vp_img.setSpeedScroller(300);
        vp_img.setOverScrollMode(vp_img.OVER_SCROLL_NEVER);//去掉边界
        vp_img.setPageMargin(DensityUtil.dip2px(getActivity(), 16));
        vp_img.setAdapter(taobaoDelVpAdapter);
        vp_img.setOffscreenPageLimit(PAGE_LIMIT);
    }

    @Override
    public void setListener() {
        // 将父节点Layout事件分发给viewpager，否则只能滑动中间的一个view对象
        lin_container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vp_img.dispatchTouchEvent(event);
            }
        });


//        ((TaobaoActivity) getThis()).img_wishlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WishListActivity.goWishListActivity(getThis());
//            }
//        });
        tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TaobaoActivity) getActivity()).share();
//                taobaoDelPresenter.share();
            }
        });

//               tv_lookdetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((TaobaoActivity) getActivity()).dragLayout.goBottom();
//            }
//        });

    }

    @Override
    public void doBusiness(Context mContext) {
        taobaoDelPresenter.getNetData(taobaoEntity);
    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    @Override
    public void setVpData(List<String> data) {
        vp_img.setOffscreenPageLimit(data.size());
        taobaoDelVpAdapter.addAll((ArrayList<String>) data);
        vp_img.setPageTransformer(true, new ScalePageTransformer(vp_img));

        if (data != null && data.size() > 1)
            taobaoDelPresenter.initIndicator((LinearLayout) vp_img.getParent(), getActivity(), vp_img);

    }

    @Override
    public void setCoverImg(final String url) {
        taobaoDelPresenter.setImageByUrl(img_bigimg, url);
        img_bigimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBigImgActivity(getThis(), url);
            }
        });
    }

    @Override
    public void setPrice(String price) {
        if (price != null)
            tv_price.setText(price);
    }

    @Override
    public void setTitle(String title) {
        tv_title.setText(title);
    }

    @Override
    public void setLink(String link) {

    }

    @Override
    public void share() {

    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }


    private ProgressDialog progressDialog;

    @Override
    public void showProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    public int getTaobaoId() {
        return Integer.parseInt(taobaoEntity.getModule_id());
    }

    @Override
    public void dismisProgreDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public int getActivityBaseView() {
        return R.id.rel_taobao_base;
    }

    @Override
    public BaseActivity getThis() {
        return (BaseActivity) getActivity();
    }
}
