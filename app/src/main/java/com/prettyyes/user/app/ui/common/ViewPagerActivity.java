package com.prettyyes.user.app.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.SuitApiImpl;
import com.prettyyes.user.app.adapter.detail.BigImgVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.comment.MoreReplyActivity;
import com.prettyyes.user.app.ui.how.LiveActActivity;
import com.prettyyes.user.app.ui.how.TopicActivity;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.utils.PermissionUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.Suit.SuitDetailEntity;
import com.prettyyes.user.model.other.TaoBaoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.prettyyes.user.AppConfig.PAGE_LIMIT;
import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;


public class ViewPagerActivity extends BaseActivity {
    private ViewPager mPager;
    private ArrayList<String> urls;
    private int index;
    private String type;
    private int id;
    private int totalcount = 0;
    @ViewInject(R.id.tv_vp_index)
    private TextView tv_index;
    @ViewInject(R.id.tv_bigimgAdp_desc)
    private TextView tv_desc;
    @ViewInject(R.id.img_vp_addcart)
    private ImageView img_addcart;
    @ViewInject(R.id.rel_vp_desc)
    private RelativeLayout rel_desc;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!(ZBundleCore.getInstance().isLastSecond(MainActivity.class) ||
                ZBundleCore.getInstance().isLastSecond(MoreReplyActivity.class) ||
                ZBundleCore.getInstance().isLastSecond(LiveActActivity.class) || ZBundleCore.getInstance().isLastSecond(TopicActivity.class)
        )) {
            DataCenter.ANSWER_ID_CURRENT = 0;
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void initVariables() {
        index = getIntent().getIntExtra("index", 0);
        urls = getIntent().getStringArrayListExtra("urls");
        id = getIntent().getIntExtra("id", 0);
        type = getIntent().getStringExtra("type");
        totalcount = urls.size();
    }

    public static void goVpActivity(Context activity, int index, String url, String type) {
        Intent intent = new Intent(activity, ViewPagerActivity.class);
        intent.putExtra("index", index);
        ArrayList a = new ArrayList();
        a.add(url);
        intent.putExtra("urls", a);
        intent.putExtra("type", type);
        activity.startActivity(intent);
        if (activity instanceof Activity)
            ((Activity) activity).overridePendingTransition(R.anim.a5, 0);
    }

    public static void goVpActivity(Context context, int index, ArrayList urls, String type) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("urls", urls);
        intent.putExtra("type", type);
        context.startActivity(intent);

        if (context instanceof Activity)
            ((Activity) context).overridePendingTransition(R.anim.a5, 0);
    }

    public static void goVpActivity(Context context, int index, ArrayList urls, String type, int id) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("urls", urls);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        context.startActivity(intent);
        if (context instanceof Activity)
            ((Activity) context).overridePendingTransition(R.anim.a5, 0);
    }


    public static void goVpActivity(Context context, int index, String urls, String type, int id) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        intent.putExtra("index", index);
        ArrayList b = new ArrayList();
        b.add(urls);
        intent.putExtra("urls", b);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        context.startActivity(intent);
        if (context instanceof Activity)
            ((Activity) context).overridePendingTransition(R.anim.a5, 0);

    }

    @Override
    protected void initViews() {
        hideHeader();
        mPager = (ViewPager) findViewById(R.id.vp_vpActivity_showimg);
        mPager.setOffscreenPageLimit(PAGE_LIMIT);
        mPager.setAdapter(new BigImgVpAdapter(this, urls));
        mPager.setCurrentItem(index);
        if (totalcount > 1)
            tv_index.setText((index + 1) + "/" + totalcount);
        else {
            tv_index.setText("");
        }

    }

    @Override
    public void setStatusBar() {
        setTranslucenBar();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (totalcount > 1)
                    tv_index.setText((position + 1) + "/" + totalcount);
                else
                    tv_index.setText("");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        img_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCart(type, id);
            }
        });
    }

    private void addCart(String type, int id) {
        if (getUUId() == null) {
            RegisterActivity.getRegister(this);
            return;
        }
        img_addcart.setColorFilter(ContextCompat.getColor(this, R.color.theme_red));


        if (type.equals(TYPE_SUIT))
            new OrderApiImpl().cartaddgoods(getUUId(), type, id, "", 0, 1, 0, new NetReqCallback() {
                @Override
                public void apiRequestFail(String message, String method) {

                }

                @Override
                public void apiRequestSuccess(Object o, String method) {
                    showToastShort(getString(R.string.addCartSuccess));
                }
            });
        else if (type.equals(TYPE_TAOBAO))
            new OrderApiImpl().cartaddgoods(getUUId(), type, 0, "", id, 1, 0, new NetReqCallback() {
                @Override
                public void apiRequestFail(String message, String method) {

                }

                @Override
                public void apiRequestSuccess(Object o, String method) {
                    showToastShort(getString(R.string.addCartSuccess));

                }
            });
    }

    @Override
    protected void loadData() {
        getData();
    }

    private void getData() {
        if (type.equals(TYPE_TAOBAO)) {
            getTaobaoData();
        } else if (type.equals(TYPE_SUIT)) {
            getSuitData();
        } else {
            rel_desc.setVisibility(View.GONE);
            img_addcart.setVisibility(View.GONE);
        }
    }

    private void getSuitData() {
        new SuitApiImpl().suitSuitGet(getUUId(), id, new NetReqCallback<SuitDetailEntity>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(SuitDetailEntity suitDetailEntity, String method) {
                setLoadResult(suitDetailEntity.getSuit().getDesc(), suitDetailEntity.getSuit().isAddwish_lish());
            }
        });
    }

    @Override
    public void back() {
        finish();
        this.overridePendingTransition(0, R.anim.a3);
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        this.overridePendingTransition(0, R.anim.a3);
    }

    public void getTaobaoData() {
        new OtherApiImpl().TaobaoDel(getUUId(), id, new NetReqCallback<TaoBaoEntity>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(TaoBaoEntity taoBaoEntity, String method) {
                setLoadResult(taoBaoEntity.getTitle(), taoBaoEntity.isAddwish_lish());
            }
        });

    }


    private void setLoadResult(String desc, boolean wishlist) {
        tv_desc.setText(desc);
        if (wishlist) {
            img_addcart.setColorFilter(ContextCompat.getColor(this, R.color.theme_red));
        } else {
            img_addcart.setColorFilter(Color.WHITE);
        }
        if (desc.length() > 0)
            rel_desc.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, new PermissionUtils.PermissionGrant() {
            @Override
            public void onPermissionGranted(int requestCode) {

            }
        });
    }

}
