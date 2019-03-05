package jp.wasabeef.richeditor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by chengang on 2018/1/8.
 */

public class TestWebView extends WebView {

    private boolean isReady = false;

    public TestWebView(Context context) {
        this(context, null);
    }

    public TestWebView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.webViewStyle);
    }

    private static final String CALLBACK_SCHEME = "re-callback://";

    @SuppressLint("SetJavaScriptEnabled")
    public TestWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setVerticalScrollBarEnabled(true);
        setHorizontalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(createWebviewClient());
    }

    protected TestWebView.EditorWebViewClient createWebviewClient() {
        return new TestWebView.EditorWebViewClient();
    }

    private String mContents;

    private void callback(String text) {
        mContents = text.replaceFirst(CALLBACK_SCHEME, "");
        if (jsCallback != null) {
            jsCallback.call(mContents);
        }
    }

    private JsCallback jsCallback;

    public void setJsCallback(JsCallback jsCallback) {
        this.jsCallback = jsCallback;
    }

    public interface JsCallback {
        public void call(String text);
    }

    protected class EditorWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String decode;
            try {
                decode = URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // No handling
                return false;
            }

            if (TextUtils.indexOf(url, CALLBACK_SCHEME) == 0) {
                callback(decode);
                return true;
            }
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            //网络异常，WebView加载我们自定义的页面
            super.onReceivedError(view, errorCode, description, failingUrl);
            view.loadUrl("file:///android_asset/error.html");          }
    }
}
