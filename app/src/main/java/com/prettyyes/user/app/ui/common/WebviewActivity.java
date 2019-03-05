package com.prettyyes.user.app.ui.common;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.DesUtils;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.app.view.app.AppWebView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.PermissionUtils;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebView;

import static com.prettyyes.user.core.containter.JumpPageManager.TYPE_HTML;


public class WebviewActivity extends BaseActivity {
    private static final String TAG = "WebviewActivity";
    private AppWebView webView;
    private ImageView img;
    Animation out;
    String loadurl = "http://api.prettyyes.com/h5";
    String type = "";
    String html = "";
    private boolean isneeedDownload = false;
    ProgressDialog progressDialog = null;
    private ShareModel share_model;

    @Override
    public void setSoftModel() {

    }



    @Override
    protected void initVariables() {
        super.initVariables();

        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if(intentParmas!=null) {
            loadurl = intentParmas.getUrl();
            isneeedDownload = intentParmas.isneeedDownload();
            share_model = intentParmas.getShareModel();
            type = intentParmas.getType();
            html = intentParmas.getHtml();
        }

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViews() {
        setActivtytitle("How");
        webView = (AppWebView) findViewById(R.id.webview);

        if (share_model != null) {
            setReightIconListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share();
                }
            }, R.mipmap.general_share_top_icon);
        }
    }

    private void share() {
        String url = share_model.getTargetUrl();
        if (url.equals("?"))
            url += "&uuid=" + DesUtils.encryptionByDes(getUUId());
        else
            url += "?uuid=" + DesUtils.encryptionByDes(getUUId());
        LogUtil.i(TAG, "share_url-->" + url);
        new ShareHandler(this).setRes(share_model,null)
                .shareAtWindow(R.id.activity_webviewactivity);
    }

    @Override
    protected void setListener() {
        webView.setWebCallback(new AppWebView.WebCallback() {
            @Override
            public void setWebTitle(String title) {

                setActivtytitle(title.replace("—— How", ""));

            }
        });
        webView.setOnPageLoadComplete(new AppWebView.OnPageLoadComplete() {
            @Override
            public void onComplete(WebView view, String url) {
                loadSuccess();

                CookieManager cookieManager = CookieManager.getInstance();

            }
        });
    }

    @Override
    protected void loadData() {
        initWeb();
    }


    private void initWeb() {
        if (type != null && type.equals(TYPE_HTML)) {
            webView.loadContent(html);
            return;
        }
        if (isneeedDownload) {
            webView.setIsdownload(isneeedDownload);
        }
        webView.setLoadurl(loadurl);
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
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        if (type != null && type.equals("adv")) {
            nextActivity(MainActivity.class);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void back() {
        if (type != null && type.equals("adv")) {
            nextActivity(MainActivity.class);
        }
        super.back();

    }

    @Override
    protected void onPause() {
        if (type != null && type.equals("html")) {

        } else {
            webView.reload();
        }
        if (TextUtils.isEmpty(loadurl))
            AppStatistics.onPageEnd(getThis(),"WebDetailPage_" + loadurl);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(loadurl))
            AppStatistics.onPageStart(getThis(),"WebDetailPage_" + loadurl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (webView != null)
            webView.releaseMemoryLeak();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, new PermissionUtils.PermissionGrant() {
            @Override
            public void onPermissionGranted(int requestCode) {
                showToastShort("权限申请成功，你可以继续操作");
            }
        });
    }
}
