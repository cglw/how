package com.prettyyes.user.app.ui.spu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.KeyEvent;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.CouponApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.view.app.AppWebView;
import com.prettyyes.user.app.view.pupopwindow.PaperCouponWindow;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.coupon.CouponList;
import com.prettyyes.user.model.type.PaperEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class PaperWebviewActivity extends GoosInfoActivity {

    @ViewInject(R.id.webview_paper)
    private AppWebView webView;
    String html;
    int paper_id;
    String price;

    public void onResume() {
        super.onResume();
        if (paper_id != 0)
            AppStatistics.onPageStart(getThis(),"SkuDetailPage_paper_" + paper_id); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

//    @Override
//    protected int bindLayout() {
//        return ;
//    }

    @Override
    protected void initVariables() {
        super.initVariables();
        paper_id = getIntent().getIntExtra("paper_id", 0);


    }

    public static void goPaperActivity(BaseActivity activity, String html, int isReward, int paper_id, String price) {
        Intent intent = new Intent(activity, PaperWebviewActivity.class);
        intent.putExtra("html", html);
        intent.putExtra("isReward", isReward + "");
        intent.putExtra("paper_id", paper_id);
        intent.putExtra("price", price);
        activity.nextActivity(intent);

    }

    public static void goPaperActivity(BaseActivity activity, int paper_id) {
        Intent intent = new Intent(activity, PaperWebviewActivity.class);
        intent.putExtra("paper_id", paper_id);
        activity.nextActivity(intent);

    }

    public static void goPaperActivityByReceiver(BaseActivity activity, int paper_id) {
        Intent intent = new Intent(activity, PaperWebviewActivity.class);
        intent.putExtra("paper_id", paper_id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.nextActivity(intent);

    }

    private String value = "";

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void initViews() {
        setActivtytitle("How");


    }

    @Override
    public int setLayout() {
        return R.layout.activity_paper_webview;
    }
//
//    private void share() {
//        if (paperEntity != null)
//            new ShareHandler(this).setRes(paperEntity.getShare_url(), paperEntity.getSimple_desc(), paperEntity.getPaper_image(), paperEntity.getPaper_name(), new ShareHandler.ShareCallback() {
//                @Override
//                public void share(boolean issuccess) {
//                    ShareHandler.postShare(TYPE_PAPER, paperEntity.getPaper_id() + "", paperEntity.getSeller_id() + "");
//                }
//            })
//                    .shareAtWindow(getRootView());
//
//    }

    @Override
    protected void loadData() {
        new OrderApiImpl().getPaper(BaseApplication.UUID, paper_id + "", new NetReqCallback<PaperEntity>() {
            @Override
            public void apiRequestFail(String message,String method) {
                AppUtil.showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(PaperEntity paperEntity,String method) {
                PaperWebviewActivity.this.paperEntity = paperEntity;
                html = paperEntity.getDesc();
                price = paperEntity.getPrice();
                content = webView.formatHtml(paperEntity.getPaper_name(), paperEntity.getDesc());
                initWeb();

            }
        });
    }

    String content = "";
    private PaperEntity paperEntity;

    @Override
    protected void setListener() {
        super.setListener();
        webView.setWebCallback(new AppWebView.WebCallback() {
            @Override
            public void setWebTitle(String title) {
                setActivtytitle(title);
            }
        });

    }

    private void initWeb() {
        webView.loadContent(content);
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (paper_id != 0)
            AppStatistics.onPageEnd(getThis(),"SkuDetailPage_paper_" + paper_id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        webView.releaseMemoryLeak();

    }


    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        super.setInentFliter(inentFliter);
        inentFliter.addAction(Constant.OrderPaySuccess);
        inentFliter.addAction(Constant.PaperCouponUsesSuccess);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
        super.handlerIntenter(context, intent);
    }


    private void payByCoupon() {
        if (getUUId() == null) {
            RegisterActivity.getRegister(this);
            return;
        }
        new CouponApiImpl().CouponList(getUUId(), "2", new NetReqCallback<CouponList>() {
            @Override
            public void apiRequestFail(String message,String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(CouponList couponList,String method) {
                if (couponList.getList().size() > 0) {
                    new PaperCouponWindow(PaperWebviewActivity.this, (ArrayList) couponList.getList(), paper_id).show(getRootView());
                } else {
                    showToastShort("暂无优惠券");
                }
            }
        });
    }


}
