package com.prettyyes.user.app.ui.spu;

import android.view.KeyEvent;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.SpeedSearchRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.app.AppWebView;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.TaobaoSelectUrlEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.v8.SkuSearchEntity;
import com.tencent.smtt.sdk.WebBackForwardList;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.R.id.webview;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;

public class TaobaoWebActivity extends BaseActivity{


    @ViewInject(webview)
    AppWebView appWebView;

    private String url = "http://www.taobao.com/";
    private String load_url = url;
    private String wechat_url = "https://h5.weidian.com/m/search/index/index.html";

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_taobao_web;
    }

    @Override
    protected void initViews() {

        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            String go_url_type = intentParmas.getGo_url_type();
            if (go_url_type.equals(TYPE_TAOBAO)) {
                load_url = url;

            } else {
                load_url = wechat_url;

            }
        }
        setActivtytitle("进入商品详情页后点击->");
        setRightTvListener(getString(R.string.confirm), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                new SpeedSearchRequest(appWebView.getUrl()).post(new NetReqCallback<SkuSearchEntity>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        showToastShort(message);
                        dismissProgressDialog();
                    }

                    @Override
                    public void apiRequestSuccess(SkuSearchEntity skuSearchEntity, String method) {
                        AppRxBus.getInstance().post(new TaobaoSelectUrlEvent(skuSearchEntity));
                        dismissProgressDialog();

                        finish();
                    }
                });

            }
        });
        appWebView.setLoadurl(load_url);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (appWebView.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            WebBackForwardList mWebBackForwardList = appWebView.copyBackForwardList();
            if (mWebBackForwardList.getCurrentIndex() > 0) {
                String historyUrl = mWebBackForwardList.getItemAtIndex(
                        mWebBackForwardList.getCurrentIndex() - 1).getUrl();
                if (!historyUrl.contains("www")) {
                    appWebView.goBack();
                    return true;
                } else {

                }
            }
        }
        return super.onKeyDown(keyCode, event);

    }
}
