package com.prettyyes.user.app.view.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.utils.LogUtil;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.app
 * Author: SmileChen
 * Created on: 2016/11/30
 * Description: Nothing
 */
public class AppWebView extends WebView {
    private static final String TAG = "AppWebView";
    private boolean isdownload = false;

    //由于webview 下载问题，所以设置这个直接调用跳转系统浏览器
    public void setIsdownload(Boolean is) {
        isdownload = is;
    }

    public AppWebView(Context context) {
        super(context);
        initSetting();
    }

    public AppWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting();

    }

    public AppWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();

    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initSetting() {
        final WebSettings webSettings = getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        setVerticalScrollBarEnabled(true);
        setHorizontalScrollBarEnabled(false);
        addJavascriptInterface(new WebAppInterface(this.getContext()), "how");
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
//        //设置支持缩放
//        webSettings.setBuiltInZoomControls(true);
        //设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
        //    setDownloadListener(new MyWebViewDownLoadListener(this.getContext()));

        //设置Web视图
        getSettings().setBlockNetworkImage(true);
        setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //设置当前页面 是否直接跳转浏览器
                if (isdownload) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    getContext().startActivity(intent);
                } else {
                    LogUtil.i(TAG, "loadurl-->" + url);
//                    if (url.startsWith("taobao://") || url.startsWith("tmall://"))
//                        return false;
                    if (url.startsWith("http") || url.startsWith("https"))
                        view.loadUrl(url);
                    else
                        return false;

                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (onPageLoadComplete != null) {
                    onPageLoadComplete.onComplete(view, url);
                }
//                addImgOnclick();
                getSettings().setBlockNetworkImage(false);
                if (!getSettings().getLoadsImagesAutomatically()) {
                    getSettings().setLoadsImagesAutomatically(true);
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });
        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                if (webCallback != null) {
                    if (title.startsWith("http") || title.startsWith("test") || title.startsWith("api"))
                        return;

                    webCallback.setWebTitle(title);
                }
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float dy = event.getY();
                float distance = dy - lastY;
                lastY = dy;
                int result = (int) (getContentHeight() * getScale() - (getHeight() + getWebScrollY()));
                if (distance >= 0) {
                    if (result == (
                            getContentHeight() * getScale() - getHeight()) || getContentHeight() == 0) {
                        requestDisallowInterceptTouchEvent(false);

                    } else {
                        requestDisallowInterceptTouchEvent(true);

                    }
                } else if (distance < 0) {
                    if (result == 0 || getContentHeight() == 0) {
                        requestDisallowInterceptTouchEvent(false);
                    } else {
                        requestDisallowInterceptTouchEvent(true);
                    }
                }


                return false;
            }
        });

    }

    private String url = "";

    //这个方法加载url
    public void setLoadurl(final String url) {
        this.url = url;
        try {
            AppWebView.this.loadUrl(url);
            syncCookie(url, this.getContext());
        } catch (Exception ee) {
            LogUtil.i(TAG, ee + "");
        }


    }


    //同步Cookie
    public boolean syncCookie(String url, Context context) {

        CookieManager cookieManager = CookieManager.getInstance();
        String uuid = "";
        if (SpMananger.getUUID() != null)
            uuid = SpMananger.getUUID();
        cookieManager.setAcceptCookie(true);
        if (url.startsWith(AppConfig.getUrl())) {
            cookieManager.setCookie(url, String.format("uuid=%s", uuid));
            cookieManager.setCookie(url, "fromHowApp=1");
        }
        cookieManager.setCookie(url, "isLogin=true");


        String newCookie = CookieManager.getInstance().getCookie(url);
        LogUtil.i(TAG, "newCookie" + newCookie);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(context);
            cookieSyncManager.sync();
        } else {
            cookieManager.setAcceptThirdPartyCookies(this, true);
            cookieManager.flush();
        }


        return !TextUtils.isEmpty(newCookie);
    }


    private OnScrollChangedCallback mOnScrollChangedCallback;

    @Override
    protected void onScrollChanged(final int l, final int t, final int oldl,
                                   final int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(l - oldl, t - oldt);
        }
    }

    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public interface OnScrollChangedCallback {
        public void onScroll(int dx, int dy);
    }

    public interface OnPageLoadComplete {
        public void onComplete(WebView view, String url);
    }

    private OnPageLoadComplete onPageLoadComplete;

    public OnPageLoadComplete getOnPageLoadComplete() {
        return onPageLoadComplete;
    }

    public void setOnPageLoadComplete(OnPageLoadComplete onPageLoadComplete) {
        this.onPageLoadComplete = onPageLoadComplete;
    }

    private WebCallback webCallback;

    public WebCallback getWebCallback() {
        return webCallback;
    }

    public void setWebCallback(WebCallback webCallback) {
        this.webCallback = webCallback;
    }

    public interface WebCallback {
        void setWebTitle(String title);
    }

    public void releaseMemoryLeak() {
        int sdk = Build.VERSION.SDK_INT;
        if (sdk >= Build.VERSION_CODES.LOLLIPOP) {
            destroyWebview();
            return;
        }
        try {
            Field field1 = PasswordTransformationMethod.class.getDeclaredField("sInstance");
            if (field1 != null) {
                field1.setAccessible(true);
                field1.set(PasswordTransformationMethod.class, null);
            }
            Field field2 = HideReturnsTransformationMethod.class.getDeclaredField("sInstance");
            if (field2 != null) {
                field2.setAccessible(true);
                field2.set(HideReturnsTransformationMethod.class, null);
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }

    private void destroyWebview() {
        if (this != null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = this.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this);
            }

            this.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            this.getSettings().setJavaScriptEnabled(false);
            this.clearHistory();
            this.clearView();
            this.removeAllViews();
            try {
                this.destroy();
            } catch (Throwable ex) {

            }
        }
    }

    @Override
    public void loadDataWithBaseURL(String s, String s1, String s2, String s3, String s4) {
        super.loadDataWithBaseURL(s, s1, s2, s3, s4);
    }

    public void loadContent(String html) {
        loadDataWithBaseURL(null, formatHtml("", html), "text/html", "utf-8", null);
    }

    public void loadContent(String title, String html) {
        loadDataWithBaseURL(null, formatHtml(title, html), "text/html", "utf-8", null);
    }

    public String formatHtmlWithImg(String title, String[] pics) {
        final String articleFormat = "<html><head><meta charset=\"utf-8\"><title>%1s</title><style>img {width: 100%;background: url(loading.gif) 50% no-repeat;}</style><link type=\"text/css\" rel=\"stylesheet\" href=\"http://www.mayfou.com/Public/Css/article.css\" /></head><body>%2s</body></html>";
        final String centerImageFormat = "<img class=\"center\" src=\"%1s\" id=\"%2d\"/>";


        String imgs = "";

        for (int i = 0; i < pics.length; i++) {
            LogUtil.i(TAG, "html-->" + pics[i]);

            Map<String, String> map = new HashMap<>();
            map.put("index", i + "");


            imgs += String.format(centerImageFormat, pics[i], i);
        }

        imgs += "";

        String html = String.format(articleFormat, title, imgs);
        LogUtil.i(TAG, "html-->" + html);


        return html;


    }

    private void addImgOnclick() {
        loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<objs.length;i++){" +
                "    objs[i].onclick=function(){" +
                "        window.how.openImage(this.src);" +
                "    }" +
                "}" +
                "})()");
    }


    public String formatHtml(String title, String body) {
        final String articleFormat = "<html><head><meta charset=\"utf-8\">" +
                "<title>%1s</title><link type=\"text/css\" rel=\"stylesheet\" href=\"https://image.prettyyes.com/spu_desc.css\" />" +
                "</head><body>%2s</body><script>\n" +
                "    var imgs=document.getElementsByTagName('img');\n" +
                "    console.log(imgs);\n" +
                "    var imgScr = '';\n" +
                "\n" +
                "    var img_arr =new Array();\n" +
                "    for(var i=0;i<imgs.length;i++){\n" +
                "        img_arr[i] = imgs[i].src;\n" +
                "        imgs[i].setAttribute('index',i);\n" +
                "    }\n" +
                "\n" +
                "    var obj = new Object();\n" +
                "    obj.imgs = img_arr;\n" +
                "//    console.log(obj);\n" +
                "    var json_str  = '';\n" +
                "    for(i=0;i<imgs.length;i++){\n" +
                "        imgs[i].onclick = function(){\n" +
                "\n" +
                "//            alert(this.src);\n" +
                "//            alert(this.getAttribute(\"index\"));\n" +
                "\n" +
                "            obj.index = this.getAttribute(\"index\");\n" +
                "            obj.image_url = this.src;\n" +
                "            json_str = JSON.stringify(obj)\n" +
                "           \n" +
                "//            window.location.href='image-preview:'+this.src\n" +
                "            window.how.goBigImgActivity(json_str);\n" +
                "            \n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "</script></html>";
        final String centerImageFormat = "<img class=\"center\" src=\"%1s\" width=\"%2d\" height=\"%3d\" id=\"%4d\"/>";
        final String imageFormat = "<img src=\"%1s\" width=\"%2d\" height=\"%3d\" id=\"%4d\"/>";

        String html = String.format(articleFormat, title, body);

//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics metrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(metrics);
//
//        int maxWith = (int) (getWidth() / metrics.density) - 20;
//
//        //替换img标签
//        if (pics != null) {
//            int id = 1;
//            for (ArticleXQ.PictureEntry pic : pics) {
//                String[] pixel = pic.pixel.split("\\*");
//                int width = Integer.parseInt(pixel[0]);
//                int height = Integer.parseInt(pixel[1]);
//
//                if (width > maxWith) {
//                    height = height * maxWith / width;
//                    width = maxWith;
//                }
//
//                String img;
//                if (width < 80) {
//                    img = String.format(imageFormat, pic.src, width, height, id++);
//                } else {
//                    img = String.format(centerImageFormat, pic.src, width, height, id++);
//                }
//
//                html = html.replace(pic.ref, img);
//            }
//        }

        return html;
    }

    private boolean checkUrlValid(String aUrl) {
        boolean result = true;
        if (aUrl == null || aUrl.equals("") || !aUrl.contains("http")) {
            return false;
        }
        if (aUrl.contains("s.click")) {
            result = false;
        }
        return result;
    }


    private float lastY = 0;
    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

        @Override
        public void onViewInitFinished(boolean arg0) {
            // TODO Auto-generated method stub
            //初始化完成回调
            syncCookie(url, getContext());

        }

        @Override
        public void onCoreInitFinished() {
            // TODO Auto-generated method stub
        }
    };
}
