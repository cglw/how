package com.prettyyes.user.app.ui.spu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.UnitListInSuitAdapter;
import com.prettyyes.user.app.adapter.detail.SuitVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.mvpPresenter.SuitDelPresenter;
import com.prettyyes.user.app.mvpView.SuitDelView;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.comment.MoreReplyActivity;
import com.prettyyes.user.app.ui.how.LiveActActivity;
import com.prettyyes.user.app.ui.how.TopicActivity;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.ClipViewPager;
import com.prettyyes.user.app.view.indictator.CircleIndicator;
import com.prettyyes.user.app.view.pagetransformer.ScaleVpTransformer;
import com.prettyyes.user.app.view.tvbtnetv.ExpandableTextView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.rv.FullyLinearLayoutManager;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.prettyyes.user.AppConfig.PAGE_LIMIT;

public class SuitDelActivity extends GoosInfoActivity implements SuitDelView {
    private String suit_id;
    private SuitVpAdapter suitVpadapter;
    @ViewInject(R.id.tv_suitdelAct_suitname)
    private TextView tv_suitnmae;
    @ViewInject(R.id.tv_suitdelAct_good)
    private ExpandableTextView tv_good;
    @ViewInject(R.id.tv_suitdelAct_bad)
    private ExpandableTextView tv_bad;
    @ViewInject(R.id.tv_suitdelAct_totalprice)
    private TextView tv_totalprice;
    @ViewInject(R.id.grid_suitdelAct_suitdel)
    private RecyclerView custgridview_suitdel;
    @ViewInject(R.id.lin_suitdelAct_topindex)
    private LinearLayout lin_topindex;//上面的指示器
    @ViewInject(R.id.vp_suitdelAct_suit)
    private ClipViewPager vp_suit;

    @ViewInject(R.id.checkbox_suitdelAct_collect)
    private CheckBox checkBox_suit_like;
    @ViewInject(R.id.img_suitdelAct_share)
    private ImageView img_share;
    @ViewInject(R.id.rel_suitdelAct_headimg)
    private RelativeLayout rel_go_chat;


    @ViewInject(R.id.tv_suitdelAct_desc)
    TextView tv_desc;

    private SuitDelPresenter suitDelPresenter = new SuitDelPresenter(this);
    private UnitListInSuitAdapter unitListInSuitAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        if (!(ZBundleCore.getInstance().isLastSecond(MainActivity.class) ||
                ZBundleCore.getInstance().isLastSecond(MoreReplyActivity.class) ||
                ZBundleCore.getInstance().isLastSecond(LiveActActivity.class) || ZBundleCore.getInstance().isLastSecond(TopicActivity.class)
        )) {
            LogUtil.i("TAG", "没有回复");
            DataCenter.ANSWER_ID_CURRENT = 0;
        }
    }

    public void onResume() {
        super.onResume();
        AppStatistics.onPageStart(getThis(), "SkuDetailPage_suit_" + suit_id); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    public void onPause() {
        super.onPause();
        AppStatistics.onPageEnd(getThis(), "SkuDetailPage_suit_" + suit_id); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义


    }

    public static void goSuitDelActivity(Context context, int suit_id) {
        Intent intent = new Intent(context, SuitDelActivity.class);
        intent.putExtra("suit_id", suit_id);
        if (context instanceof BaseActivity)
            ((BaseActivity) context).nextActivity(intent);
        else
            context.startActivity(intent);
    }

    public static void goSuitDelActivityByReceiver(Context context, int suit_id) {
        Intent intent = new Intent(context, SuitDelActivity.class);
        intent.putExtra("suit_id", suit_id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (context instanceof BaseActivity)
            ((BaseActivity) context).nextActivity(intent);
        else
            context.startActivity(intent);
    }


    @Override
    protected void initVariables() {
        super.initVariables();
    }

    BadgeView badgeView;

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("商品详情");
        showBack();
        custgridview_suitdel.setFocusable(false);
        initVpsuit();


    }

    @Override
    public int setLayout() {
        return R.layout.activity_suit_info;
    }


    private void initVpsuit() {
        vp_suit.setIscanClickOther(false);
        suitVpadapter = new SuitVpAdapter(this);
        vp_suit.setOverScrollMode(vp_suit.OVER_SCROLL_NEVER);//去掉边界
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) vp_suit.getLayoutParams();
        layoutParams.height = BaseApplication.ScreenWidth - DensityUtil.dip2px(50) * 2;
        vp_suit.setAdapter(suitVpadapter);
    }


    @Override
    protected void loadData() {
        super.loadData();
    }

    @Override
    protected void setListener() {
        super.setListener();

        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();

            }
        });

        checkBox_suit_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getEitd_state())
                    return;
                suitDelPresenter.collect(((CheckBox) v).isChecked(), suit_id);
            }
        });
        ((LinearLayout) vp_suit.getParent()).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vp_suit.dispatchTouchEvent(event);
            }
        });

        vp_suit.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setSuitDelVpData(final ArrayList<String> datas) {
        vp_suit.setOffscreenPageLimit(PAGE_LIMIT);
        suitVpadapter.addAll(datas);
        vp_suit.setPageTransformer(true, new ScaleVpTransformer(0.9f, vp_suit));
        if (datas != null && datas.size() > 0) {
            vp_suit.setCurrentItem(0);
        }

        if (datas != null && datas.size() > 1) {
            suitDelPresenter.initIndicator(lin_topindex, vp_suit, CircleIndicator.Gravity.CENTER, this, datas.size());
            lin_topindex.setVisibility(View.VISIBLE);
        } else {
            lin_topindex.setVisibility(View.GONE);
        }
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public void setGood(String tv) {
        if (tv != null)
            tv_good.setText(tv);
    }

    @Override
    public void setbad(String tv) {
        if (tv != null)
            tv_bad.setText(tv);
    }

    @Override
    public void setCollect(boolean is) {

        checkBox_suit_like.setChecked(is);


    }

    @Override
    public void setSuitGoosGv(ArrayList<SpuInfoEntity> datas) {
        if (datas == null)
            return;

        unitListInSuitAdapter = new UnitListInSuitAdapter(this);
        custgridview_suitdel.setAdapter(unitListInSuitAdapter);
        custgridview_suitdel.setLayoutManager(new FullyLinearLayoutManager(this));
        unitListInSuitAdapter.addAll(datas);
    }

    @Override
    public void setRecommdData(SpuInfoEntity spuInfoEntity) {
        setRecommend(spuInfoEntity);
    }

    @Override
    public void setRecommendTv(String tv) {

    }


    @Override
    public void setTotalPrice(float price) {
        tv_totalprice.setText("RMB " + price + "");
        tv_total_price.setText(String.format("合计:¥%.2f", price));
    }

    @Override
    public void closeVpswipe() {

    }

    @Override
    public void setHeadView(String url, boolean famous) {


    }

    @Override
    public void setAct(String act) {


    }

    @Override
    public void setNickname(String nickname) {

    }

    @Override
    public void setDesc(String desc) {
        tv_desc.setText(desc);

    }

    @Override
    public void setSuitName(String suitname) {
        if (suitname != null)
            tv_suitnmae.setText(suitname);
    }

    private ProgressDialog progressDialog;

    @Override
    public void showProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    public void dismisProgreDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public int getActivityLayoutId() {
        return R.id.rel_suitdelAct_base;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
        super.apiRequestSuccess(spuInfoEntity, method);
        suitDelPresenter.setData(spuInfoEntity);
        if (spuInfoEntity != null)
            suit_id = spuInfoEntity.getModule_id();

    }


}
