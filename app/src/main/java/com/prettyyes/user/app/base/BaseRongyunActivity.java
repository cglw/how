package com.prettyyes.user.app.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
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
import com.microquation.linkedme.android.LinkedME;
import com.microquation.linkedme.android.callback.LMReferralCloseListener;
import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.StatusBarUtil;
import com.prettyyes.user.model.user.UserInfo;
import com.umeng.analytics.MobclickAgent;

import org.xutils.x;

import static com.prettyyes.user.AppConfig.STARTBAR_ALPHA;


/**
 * Created by Administrator on 2016/7/2.
 */
public abstract class BaseRongyunActivity extends FragmentActivity {


    public String uuid_act;
    public BaseApplication application;
    protected Context mContext;
    private LinearLayout parentLinearLayout;//把父类activity和子类activity的view都add到这里
    private ImageView img_base_back;
    private RelativeLayout rel_base_back;
    private ImageView img_base_right;
    private TextView tv_base_title;
    private RelativeLayout rel_base_header;
    private TextView tv_base_righttxt;
    protected View mContextView = null;
    public AlertView alertView;
    private UserInfo userInfo;

    private MyReceiver myReceive;


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
        super.onCreate(savedInstanceState);
        ZBundleCore.getInstance().addActivity(this);
        AppRxBus.getInstance().register(this);

//        AppManager.getAppManager().addActivity(this);
        registerReceiver();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSoftModel();
        initContentView(R.layout.activity_header_prettyyes);
        initFindViewById();
        this.application = BaseApplication.getInstance();
        initVariables();
        setContentView(bindLayout());
        setStatusBar();

        x.view().inject(this);
        initViews();
        setListener();
        loadData();

    }


    public void setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white), STARTBAR_ALPHA);

    }




    public void setSoftModel() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
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
    private void HideSoftInput(IBinder token) {
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

        rel_base_back.setOnClickListener(new View.OnClickListener() {
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
        StatusBarUtil.setColor(this, color, 0);

    }

    public ImageView getImg_base_back() {
        return img_base_back;
    }

    private void initContentView(int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);


    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
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

    private void NextAnim() {
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    private void backAnim() {
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

    }

    public void back() {
        finishActivity();
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
        finish();
        overridePendingTransition(0, R.anim.push_top_out);
    }

    public void nextActivity(Intent intent) {
        startActivity(intent);
        NextAnim();
    }

    private void finishActivity() {
        finish();
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

    public void hideTitle() {
        rel_base_header.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceive);
        ZBundleCore.getInstance().removeActivity(this);
        AppRxBus.getInstance().unregister(mSubscription);

    }
    public Object mSubscription;


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void setRightTvListener(String tv, View.OnClickListener listener) {
        tv_base_righttxt.setText(tv);
        tv_base_righttxt.setVisibility(View.VISIBLE);
        tv_base_righttxt.setClickable(true);
        tv_base_righttxt.setOnClickListener(listener);
        img_base_right.setVisibility(View.GONE);
    }

    public TextView getRight_tv() {
        return tv_base_righttxt;
    }

    public void showBack() {
        rel_base_back.setVisibility(View.VISIBLE);
    }

    public void showRight(View.OnClickListener onClickListener) {
        img_base_right.setVisibility(View.VISIBLE);
        img_base_right.setOnClickListener(onClickListener);
    }

    public void setReightIconListener(View.OnClickListener onClickListener, int res) {
        img_base_right.setVisibility(View.VISIBLE);
        img_base_right.setImageResource(res);
        img_base_right.setOnClickListener(onClickListener);
    }

    public void setLeftImgSelectBg(int res) {
        rel_base_back.setBackgroundResource(res);
    }

    public UserInfo getUserInfo() {
        return SpMananger.getUserInfo();
    }

    private void closeAlertView() {
        if (alertView != null && alertView.isShowing()) {
            alertView.dismiss();
        }
    }

    public String getUUId() {
        String uuid = BaseApplication.UUID;
        if (uuid == null || uuid.isEmpty()) {
        }
        return uuid;
    }

    public void getUUid(int type) {
        uuid_act = getUUId();

        if (uuid_act == null || uuid_act.isEmpty()) {
            RegisterActivity.getRegister(this);
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

    private LinkedME linkedME;

    @Override
    protected void onStart() {
        super.onStart();
        try {
            try {
                //如果消息未处理则会初始化initSession，因此不会每次都去处理数据，不会影响应用原有性能问题
                if (!LinkedME.getInstance().isHandleStatus()) {
                    //初始化LinkedME实例
                    linkedME = LinkedME.getInstance();
                    linkedME.setHandleStatus(true);
                    //初始化Session，获取Intent内容及跳转参数
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (linkedME != null) {
            linkedME.closeSession(new LMReferralCloseListener() {
                @Override
                public void onCloseFinish() {
                }
            });
        }
    }

    public void showSnake(String tv) {
        Snackbar.make(parentLinearLayout, tv, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnake(View view, String tv) {
        Snackbar.make(view, tv, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

}
