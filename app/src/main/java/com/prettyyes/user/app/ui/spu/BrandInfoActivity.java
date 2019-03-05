package com.prettyyes.user.app.ui.spu;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.MyFragmentPagerAdapter;
import com.prettyyes.user.app.adapter.detail.SuitVpAdapter;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.fragments.RecommondFragment;
import com.prettyyes.user.app.fragments.brand.BrandDescFragment;
import com.prettyyes.user.app.fragments.brand.BrandInfoFragment;
import com.prettyyes.user.app.view.ClipViewPager;
import com.prettyyes.user.app.view.app.AppWebView;
import com.prettyyes.user.app.view.indictator.CircleIndicator;
import com.prettyyes.user.app.view.pagetransformer.ScaleVpTransformer;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.prettyyes.user.AppConfig.PAGE_LIMIT;

public class BrandInfoActivity extends GoosInfoActivity {

    @ViewInject(R.id.vp_suitdelAct_suit)
    private ClipViewPager vp_suit;
    @ViewInject(R.id.lin_suitdelAct_topindex)
    private LinearLayout lin_topindex;//上面的指示器

    @ViewInject(R.id.webview)
    private AppWebView appWebView;
    @ViewInject(R.id.viewpager)
    private ClipViewPager vp;

    @ViewInject(R.id.tabLayout_brand)
    private TabLayout tabLayout;
    @ViewInject(R.id.tv_price)
    private TextView tv_price;
    @ViewInject(R.id.tv_express_price)
    private TextView tv_express_price;

    @ViewInject(R.id.appbar_layout)
    private AppBarLayout appBarLayout;
    @ViewInject(R.id.coordinatorLayout)
    private CoordinatorLayout coordinatorLayout;

    private ArrayList<Fragment> fragments;

    @Override
    public int setLayout() {
        return R.layout.activity_brand2;
    }


    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_brand_info);

    }

    @Override
    public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
        super.apiRequestSuccess(spuInfoEntity, method);
        appWebView.loadContent(spuInfoEntity.getSpu_desc());
        if (spuInfoEntity != null) {
            String[] titles = new String[]{getString(R.string.brand_info_desc), getString(R.string.brand_infomation), getString(R.string.brand_recommon)};
            tv_price.setText(StringUtils.getPrice(spuInfoEntity.getSpu_price()));
            tv_express_price.setText(StringUtils.getExpress_price(spuInfoEntity.getExpress_price()));
            fragments = new ArrayList<>();
            fragments.add(BrandDescFragment.newInstance(spuInfoEntity.getSpu_desc()));
            fragments.add(BrandInfoFragment.newInstance(spuInfoEntity.getSeller_info()));
            fragments.add(RecommondFragment.newInstance(spuInfoEntity));
            vp.setOffscreenPageLimit(fragments.size());
            vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles) {
            });
            tabLayout.setTabMode(TabLayout.MODE_FIXED | TabLayout.GRAVITY_FILL);
            tabLayout.setupWithViewPager(vp);
//            RxBus.getInstance().post(spuInfoEntity);
        }
        ArrayList datas = (ArrayList) StringUtils.getSplitArray(spuInfoEntity.getSmall_img());
        if (datas != null && datas.size() > 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) vp_suit.getLayoutParams();
            layoutParams.height = BaseApplication.ScreenWidth - AppUtil.dip2px(50) * 2;
            vp_suit.setOffscreenPageLimit(PAGE_LIMIT);
            SuitVpAdapter suitVpadapter = new SuitVpAdapter(this);
            suitVpadapter.addAll(datas);
            vp_suit.setAdapter(suitVpadapter);
            vp_suit.setPageTransformer(true, new ScaleVpTransformer(0.9f, vp_suit));
            if (datas != null && datas.size() > 0) {
                vp_suit.setCurrentItem(0);
            }

            if (datas != null && datas.size() > 1) {
                CircleIndicator.initIndicator(lin_topindex, vp_suit, CircleIndicator.Gravity.CENTER, this, datas.size());
                lin_topindex.setVisibility(View.VISIBLE);
            } else {
                lin_topindex.setVisibility(View.GONE);
            }
        }

    }
}
