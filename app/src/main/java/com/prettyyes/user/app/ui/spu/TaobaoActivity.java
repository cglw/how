package com.prettyyes.user.app.ui.spu;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.fragments.RecommondFragment;
import com.prettyyes.user.app.fragments.TaobaoBottomFragment;
import com.prettyyes.user.app.fragments.TaobaoTopFragment;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.comment.MoreReplyActivity;
import com.prettyyes.user.app.ui.how.LiveActActivity;
import com.prettyyes.user.app.ui.how.TopicActivity;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.order.OrderManager;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.view.annotation.ViewInject;

public class TaobaoActivity extends GoosInfoActivity {

    private String taobao_id;
    @ViewInject(R.id.img_wishlist)
    public ImageView img_wishlist;
//    private BadgeView badgeView;


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

    public void onResume() {
        super.onResume();
        AppStatistics.onPageStart(getThis(),"SkuDetailPage_taobao_" + taobao_id); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    public void onPause() {
        super.onPause();
        AppStatistics.onPageEnd(getThis(),"SkuDetailPage_taobao_" + taobao_id); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
    }


    @Override
    protected void initVariables() {
        super.initVariables();

    }

    @Override
    protected void initViews() {
        super.initViews();
        hideHeader();
//        badgeView = new BadgeView(this);
//        badgeView.setTargetView(img_wishlist);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_taobao;
    }

    @Override
    public void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    protected void loadData() {
        super.loadData();
        OrderManager.getInstance().refreshWishListNum();
    }

    private void handlerData(SpuInfoEntity taoBaoEntity) {
        taobao_id = taoBaoEntity.getSku_id();
        final TaobaoBottomFragment taobaoBottomFragment = TaobaoBottomFragment.newInstance(taoBaoEntity);
        TaobaoTopFragment taobaoTopFragment = TaobaoTopFragment.newInstance(taoBaoEntity);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.first, taobaoTopFragment).add(R.id.second, taobaoBottomFragment).add(R.id.framelayout_recommend, RecommondFragment.newInstance(taoBaoEntity))
                .commit();
    }

    @Override
    protected void setListener() {
        super.setListener();
//        mSubscription = AppRxBus.getInstance().toObservable(Object.class).subscribe(new RxAction1<Object>() {
//            @Override
//            public void callback(Object object) {
//                if (object instanceof CartNumEvent) {
////                    badgeView.setBadgeCount(((CartNumEvent) object).getNum());
//                }
//
//
//            }
//        });
        img_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
    }

    @Override
    public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
        super.apiRequestSuccess(spuInfoEntity, method);
        handlerData(spuInfoEntity);
    }
}
