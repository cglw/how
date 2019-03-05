package com.prettyyes.user.app.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bumptech.glide.Glide;
import com.microquation.linkedme.android.LinkedME;
import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StatusBarUtil;
import com.prettyyes.user.model.user.UserInfo;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;
import com.weavey.loading.lib.LoadingLayout;

import org.xutils.x;

import static android.support.design.widget.Snackbar.make;
import static com.prettyyes.user.AppConfig.STARTBAR_ALPHA;


/**
 * Created by Administrator on 2016/7/2.
 */
public abstract class BaseActivity extends AppCompatActivity {


    public String uuid_act;
    public String TAG = this.getClass().getName();
    public BaseApplication application;
    protected Context mContext;
    private LinearLayout parentLinearLayout;//把父类activity和子类activity的view都add到这里
    private ImageView img_base_back;
    private RelativeLayout rel_base_back;
    private ImageView img_base_right;
    private TextView tv_base_title;
    private RelativeLayout rel_base_header;
    private TextView tv_base_righttxt;
    private View view_base_right;
    public AlertView alertView;
    private TextView tv_base_left;
    public LoadingLayout loadingLayout;

    private MyReceiver myReceive;
    public Object mSubscription;


    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            handlerIntenter(context, intent);
        }
    }

    /**
     * 处理广播接收的intent
     *
     * @param context
     * @param intent
     */
    public void handlerIntenter(Context context, Intent intent) {
    }

    public boolean isLoginRefresh(Intent intent) {
        if (intent.getAction().equals(Constant.LOGIN_REFRESH)) {
            return true;
        }
        return false;
    }


    public void registerReceiver() {
        this.myReceive = new MyReceiver();
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction(Constant.LOGIN_REFRESH);
        localIntentFilter.addAction(Constant.CollectTion_Goods);
        localIntentFilter.addAction(Constant.CollectTion_KOL);
        setInentFliter(localIntentFilter);
        registerReceiver(this.myReceive, localIntentFilter);
    }

    public void setInentFliter(IntentFilter inentFliter) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LinkedME.getInstance().onLMCreated(this);
        super.onCreate(savedInstanceState);

        ZBundleCore.getInstance().addActivity(this);
//        AppManager.getAppManager().addActivity(this);
        registerReceiver();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSoftModel();
        initContentView(R.layout.activity_header_prettyyes);
        initFindViewById();
        this.application = BaseApplication.getInstance();
        initVariables();
        if (bindLayout() != 0)
            setContentView(bindLayout());
        else
            setContentView(bindView());

        setStatusBar();
        x.view().inject(this);
        initViews();
        setListener();
        loadData();


    }


    private View bindView() {
        return null;
    }


    public void setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white), STARTBAR_ALPHA);
    }

    public void setTranslucenBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, STARTBAR_ALPHA, null);

    }


    public void setSoftModel() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        handSoft(ev);
        return super.dispatchTouchEvent(ev);
    }

    public void handSoft(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
    }


    // 判定是否需要隐藏
    public boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom);
        }
        return false;
    }

    // 隐藏软键盘
    public void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected abstract int bindLayout();


    /**
     * 布局默认的StatBar高度为24dp
     */
    private void initFindViewById() {
        img_base_back = (ImageView) findViewById(R.id.img_base_back);
        rel_base_back = (RelativeLayout) findViewById(R.id.rel_base_back);
        img_base_right = (ImageView) findViewById(R.id.img_base_right);
        tv_base_title = (TextView) findViewById(R.id.tv_base_title);
        rel_base_header = (RelativeLayout) findViewById(R.id.rel_base_header);
        tv_base_righttxt = (TextView) findViewById(R.id.tv_base_righttxt);
        tv_base_left = (TextView) findViewById(R.id.tv_base_left);
        view_base_right = findViewById(R.id.view_base_right);
//        loadingLayout= (LoadingLayout) findViewById(R.id.loadingLayout);
        rel_base_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

    }

    public void showLeft() {
        tv_base_left.setVisibility(View.VISIBLE);
        img_base_back.setVisibility(View.GONE);
        tv_base_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }


    public void setApptitleTvColor(int color) {
        tv_base_title.setTextColor(color);
    }

    public void setApptitleColor(int color) {
        rel_base_header.setBackgroundColor(color);
    }

    public void setStateBarColor(int color) {
        StatusBarUtil.setColor(this, color, STARTBAR_ALPHA);

    }

    public ImageView getImg_base_back() {
        return img_base_back;
    }

    public ImageView getImg_right() {
        return img_base_right;
    }

    private void initContentView(int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
    }

    View contentView = null;

    @Override
    public void setContentView(int layoutResID) {
        if (isRemove_loadingLayout()) {
            contentView = LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
            return;
        }
        contentView = LayoutInflater.from(this).inflate(layoutResID, null);
        loadingLayout = new LoadingLayout(this);
        loadingLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.backgroundWhit));
        parentLinearLayout.addView(loadingLayout);
        loadingLayout.addView(contentView);
        loadingLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        loadingLayout.isFirstVisible = true;
        if (contentView == null)
            contentView = new View(getThis());
        loadingLayout.setContentView(contentView);
        loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadData();
            }
        });
        needLoading(true);
    }


    public boolean isRemove_loadingLayout() {
        return remove_loadingLayout;
    }

    private boolean remove_loadingLayout = false;

    public void needLoading(boolean need) {
        if (need && loadingLayout != null) {
            loadingLayout.setStatus(LoadingLayout.Loading);
        }

    }


    @Override
    public void setContentView(View view) {
        parentLinearLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {

        parentLinearLayout.addView(view, params);

    }

    private LinearLayout getParentLinearLayout() {
        return parentLinearLayout;
    }

    private void setParentLinearLayout(LinearLayout parentLinearLayout) {
        this.parentLinearLayout = parentLinearLayout;
    }

    public void setActivtytitle(String title) {
        tv_base_title.setText(title);
    }


    public void setActivtytitle(int title) {
        tv_base_title.setText(getString(title));
    }

    public void setActivtytitleTextColor(int color) {
        tv_base_title.setTextColor(color);
    }


    //得到intent传进来的值
    protected void initVariables() {
    }

    //初始化view
    protected abstract void initViews();

    //设置事件监听
    protected void setListener() {
    }

    //载入数据
    protected abstract void loadData();

    public void showToastShort(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void showToastShort(int id) {
        Toast.makeText(this, getString(id), Toast.LENGTH_SHORT).show();
    }

    private void NextAnim() {
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    private void backAnim() {
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

    }

    public void back() {
        ZBundleCore.getInstance().finishActivity(this);
    }

    public void nextActivity(Class actclass) {
        startActivity(new Intent(this, actclass));
        NextAnim();
    }

    public void nextActivityTopIn(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.push_top_in, R.anim.fake_anim);
    }

    public void backActivityTopout() {
        ZBundleCore.getInstance().finishActivity(this);
        overridePendingTransition(0, R.anim.push_top_out);
    }

    public void nextActivity(Intent intent) {
        startActivity(intent);
        NextAnim();
    }

    private void finishActivity() {
        backAnim();
    }

    public void finishActivityNoAnim() {
        finish();
    }

    public String getViewTxt(View view) {
        if (view instanceof TextView) {
            return ((TextView) view).getText().toString();
        } else if (view instanceof Button) {
            return ((Button) view).getText().toString();
        } else if (view instanceof EditTextDel) {
            return ((EditTextDel) view).getText().toString();
        } else {
            return "";
        }
    }

    public void hideHeader() {
        rel_base_header.setVisibility(View.GONE);
    }

    public void showHeader() {
        rel_base_header.setVisibility(View.VISIBLE);
    }

    public void hideTitle() {
        rel_base_header.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LinkedME.getInstance().onLMDestoryed(this);
        unregisterReceiver(myReceive);
//        AppManager.getAppManager().finishActivity(this);
        ZBundleCore.getInstance().removeActivity(this);
        Glide.get(this).clearMemory();
        AppRxBus.getInstance().unregister(mSubscription);

    }

    @Override
    protected void onResume() {
        LinkedME.getInstance().onLMResumed(this);
        super.onResume();
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        LinkedME.getInstance().onLMPaused(this);
        super.onPause();
        MobclickAgent.onPause(this);

    }

    public void setRightTvListener(String tv, View.OnClickListener listener) {
        tv_base_righttxt.setText(tv);
        tv_base_righttxt.setVisibility(View.VISIBLE);
        view_base_right.setOnClickListener(listener);
        img_base_right.setVisibility(View.GONE);
    }

    public void setRightTvListener(int id, View.OnClickListener listener) {
        tv_base_righttxt.setText(getString(id));
        tv_base_righttxt.setVisibility(View.VISIBLE);
        view_base_right.setOnClickListener(listener);
        img_base_right.setVisibility(View.GONE);
    }


    public TextView getRight_tv() {
        return tv_base_righttxt;
    }

    public TextView getLeft_tv() {
        return tv_base_left;
    }

    public TextView getTv_base_title() {
        return tv_base_title;
    }

    public void showBack() {
        rel_base_back.setVisibility(View.VISIBLE);
    }

    public void showRight(View.OnClickListener onClickListener) {
        img_base_right.setVisibility(View.VISIBLE);
        view_base_right.setOnClickListener(onClickListener);
    }

    public void setReightIconListener(View.OnClickListener onClickListener, int res) {
        img_base_right.setVisibility(View.VISIBLE);
        img_base_right.setImageResource(res);
        view_base_right.setOnClickListener(onClickListener);
    }

    public UserInfo getAccount() {
        return SpMananger.getUserInfo();
    }


    public String getUUId() {
        return SpMananger.getUUID();
    }

    public void getUUid(int type) {
        uuid_act = getUUId();

        if (uuid_act == null || uuid_act.isEmpty()) {
            nextActivity(RegisterActivity.class);
            return;
        }
        doSomethingByUUid(type);

    }

    public void doSomethingByUUid(int type) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (alertView != null && alertView.isShowing()) {
            alertView.dismiss();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void sendBrodcast(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        sendBroadcast(intent);
    }


    @Override
    protected void onStart() {
        LinkedME.getInstance().onLMStarted(this);
        super.onStart();

    }

    @Override
    protected void onStop() {
        LinkedME.getInstance().onLMStoped(this);
        super.onStop();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private ProgressDialog progressDialog;

    public void showProgressDialog(String... msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            if (msg.length <= 0)
                progressDialog.setMessage(getString(R.string.loading_more));
            else
                progressDialog.setMessage(msg[0]);


        }
        progressDialog.show();
    }

    public void showProgressDialog(int id) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);

            progressDialog.setMessage(getString(id));


        }
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public View getRootView() {
        return parentLinearLayout;
    }

    public BaseActivity getThis() {
        return this;
    }

    public void requestRootFouse() {
        getRootView().setFocusable(true);//获取焦点，因为Scrollview的原因
        getRootView().setFocusableInTouchMode(true);
        getRootView().requestFocus();
    }

    public void showSnack(int res) {
        if (getRootView() != null)
            make(getRootView(), getString(res), Snackbar.LENGTH_SHORT).show();

    }

    public void showSnack(String msg) {
        LogUtil.i(TAG, "showSnack+" + getRootView());
        if (getRootView() != null)
            make(getRootView(), msg, Snackbar.LENGTH_SHORT).show();
    }

    public Snackbar showSnackLong(String msg) {
        if (getRootView() != null) {
            Snackbar make = Snackbar.make(getRootView(), msg, Snackbar.LENGTH_LONG);
            make.show();
            return make;
        }
        return null;
    }

    public Snackbar showSnackLong(String msg, View.OnClickListener onClickListener) {
        if (getRootView() != null) {
            Snackbar make = Snackbar.make(getRootView(), msg, Snackbar.LENGTH_LONG);
            make.setAction(msg, onClickListener).show();
            return make;
        }
        return null;
    }

    public void showSnackINDEFINITE(String msg) {
        make(getRootView(), msg, Snackbar.LENGTH_INDEFINITE).show();
    }


    public void loadSuccess() {
        if (loadingLayout != null)
            loadingLayout.setStatus(LoadingLayout.Success);

    }

    public void loadFail() {
        if (loadingLayout != null)
            loadingLayout.setStatus(LoadingLayout.Error);
    }

    /**
     * 分享授权回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);

    }
}
