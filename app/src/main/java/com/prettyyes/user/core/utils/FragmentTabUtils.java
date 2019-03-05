package com.prettyyes.user.core.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.HomeTabSelectAgainEvent;

import java.util.List;


/**
 * 主界面 底部切换tab工具类
 *
 * @Package
 * @作 用:
 * @创 建 人:
 * @日 期:
 * @修 改 人:
 * @日 期:
 */
public class FragmentTabUtils implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "FragmentTabUtils";
    private List<Fragment> fragments; // 一个tab页面对应一个Fragment
    private List<String> flags;
    private RadioGroup rgs; // 用于切换tab
    private FragmentManager fragmentManager; // Fragment所属的Activity
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private int currentTab; // 当前Tab页面索引
    private OnRgsExtraCheckedChangedListener listener; // 用于让调用者在切换tab时候增加新的功能
    private Activity context;
    private int lastckeckindex = -1;

    public static Bitmap bitmap = null;
    private int tag1;//标记第一个，0我的,1地图
    private int tag2 = 0;//标记第二个,0接单,1刷新
    private int tag3;//标记第三个
    private boolean isfirst = false;

    /**
     * @param fragmentManager
     * @param fragments
     * @param fragmentContentId
     * @param
     */
    public FragmentTabUtils(int pageIndex, FragmentManager fragmentManager, List<Fragment> fragments, int fragmentContentId, final RadioGroup rgs, OnRgsExtraCheckedChangedListener listener, List flags, Activity context) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragmentManager = fragmentManager;
        this.fragmentContentId = fragmentContentId;
        this.listener = listener;
        this.context = context;
        this.flags = flags;
        this.isfirst = true;
        rgs.setOnCheckedChangeListener(this);
        ((RadioButton) rgs.getChildAt(pageIndex)).setChecked(true);
        lastckeckindex = pageIndex;
        for (int i = 0; i < rgs.getChildCount(); i++) {

            View childAt = rgs.getChildAt(i);
            childAt.setTag(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) v.getTag();
                    if (tag == lastckeckindex)
                        AppRxBus.getInstance().post(new HomeTabSelectAgainEvent(tag));


                    if (tag == 3 || tag == 4) {
                        if (SpMananger.getUUID() == null) {
                            ((RadioButton) v).setChecked(false);
                            return;
                        }
                    }
                    if (tag != 2)
                        lastckeckindex = tag;

                    LogUtil.i(TAG, "lastckeckindex" + lastckeckindex + "--->" + v.getTag());
                }
            });
        }

    }

    public void checkIndex(int pageIndex) {
        ((RadioButton) rgs.getChildAt(pageIndex)).setChecked(true);
        lastckeckindex = pageIndex;

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        for (int i = 0; i < rgs.getChildCount(); i++) {

            if (i == 2 && rgs.getChildAt(i).getId() == checkedId) {
                if (ClickUtils.isFastDoubleClick()) {
                    return;
                }
                if (!JumpPageManager.getManager().checkUnlogin(context)) {
                    AskActivity.goAskActivity(context);
                }
                ((RadioButton) rgs.getChildAt(lastckeckindex)).setChecked(true);


            } else if (rgs.getChildAt(i).getId() == checkedId) {
                if (SpMananger.getUUID() == null) {
                    if (i == 3 || i == 4) {
                        RegisterActivity.getRegister(context);
                        ((RadioButton) rgs.getChildAt(lastckeckindex)).setChecked(true);
                        continue;
                    }
                }
                LogUtil.i(TAG, "onCheckedChanged" + "checkedId--->" + i);


                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);
//                getCurrentFragment().onPause(); // 暂停当前tab
                if (getCurrentFragment() != null)
                    getCurrentFragment().onStop(); // 暂停当前tab
                if (fragment.isAdded()) {
                    fragment.onStart(); // 启动目标tab的fragment onStart()
//                    fragment.onResume(); // 启动目标tab的onResume()
                } else if (!fragment.isAdded()) {
                    ft.add(fragmentContentId, fragment, flags.get(i));
                    ft.commitAllowingStateLoss();
                }
                showTab(i); // 显示目标tab

                // 如果设置了切换tab额外功能功能接口
                if (null != listener) {
                    listener.OnRgsExtraCheckedChanged(radioGroup, checkedId, i);
                }

            }


        }

    }

    /**
     * 切换tab
     *
     * @param idx
     */
    private void showTab(int idx) {
        if (idx == 0) {
            AppStatistics.onEvent(context, "wenda");
        } else if (idx == 1) {
            AppStatistics.onEvent(context, "kol");

        } else if (idx == 2) {
            AppStatistics.onEvent(context, "ask");
        } else if (idx == 3) {
            AppStatistics.onEvent(context, "activity");


        } else if (idx == 4) {
            AppStatistics.onEvent(context, "more");
        }


        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);
            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }

            ft.commitAllowingStateLoss();

        }
        currentTab = idx; // 更新目标tab为当前tab
    }

    /**
     * 获取一个带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (!isfirst) {

            // 设置切换动画
            if (index > currentTab) {
                ft.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
            } else {
                ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
            }
        }
        isfirst = false;
        return ft;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    public OnRgsExtraCheckedChangedListener getListener() {
        return listener;
    }

    public void setListener(OnRgsExtraCheckedChangedListener listener) {
        this.listener = listener;
    }

    /**
     * 切换tab额外功能功能接口
     */
    public static interface OnRgsExtraCheckedChangedListener {
        public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index);
    }

    public static Bitmap myShot(Activity activity) {
        //获取当前屏幕的大小
        int width = activity.getWindow().getDecorView().getRootView().getWidth();
        int height = activity.getWindow().getDecorView().getRootView().getHeight();
        //生成相同大小的图片
        Bitmap temBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        //找到当前页面的跟布局
        View view = activity.getWindow().getDecorView().getRootView();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();
        return ImageHelper.comp(temBitmap);

    }


}