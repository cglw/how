package com.prettyyes.user.core.containter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.prettyyes.user.app.ui.kol.KolReplyActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Project Name: testApkUpdate
 * Package Name: com.cg.app.main.containter
 * Author: SmileChen
 * Created on: 2016/9/14
 * Description: Nothing
 */
public class ZBundleCore {
    private static ZBundleCore instance;
    private HashMap<String, ArrayList<ZBundle>> bundlePool = new HashMap<String, ArrayList<ZBundle>>();
    //采用订阅式  一对多消息订阅映射池 key 界面名称(包名.类目)，value 订阅该界面的组件列表
    private List<Activity> activityList = new LinkedList<Activity>();// 界面容器

    private ZBundleCore() {
    }

    public synchronized static ZBundleCore getInstance() {
        if (instance == null) {
            instance = new ZBundleCore();
        }
        return instance;
    }


    public void go(ZBundle zBundle, Class<? extends Activity> c) {
        if (!ZBundleConfig.bundleMap.contains(c)) {
            Toast.makeText(zBundle.getUi(), "很抱歉，该功能正常升级维护中，请先去网站使用。",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(zBundle.getUi(), c);
        if (null != zBundle.getData()) {
            intent.putExtras(zBundle.getData());
        }
        zBundle.getUi().startActivity(intent);
        addBridge(zBundle, c);

    }

    public void goNostartActivity(ZBundle zBundle, Class<? extends Activity> c) {
        if (!ZBundleConfig.bundleMap.contains(c)) {
            Toast.makeText(zBundle.getUi(), "很抱歉，该功能正常升级维护中，请先去网站使用。",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(zBundle.getUi(), c);
        if (null != zBundle.getData()) {
            intent.putExtras(zBundle.getData());
        }
        addBridge(zBundle, c);

    }

    private void addBridge(ZBundle zBundle, Class<? extends Activity> c) {
        String key = c.getCanonicalName();
        if (bundlePool.containsKey(key)) {
            ArrayList<ZBundle> bundleList = bundlePool.get(key);
            bundleList.add(zBundle);
        } else {
            ArrayList<ZBundle> bundleList = new ArrayList<>(0);
            bundleList.add(zBundle);
            bundlePool.put(key, bundleList);
        }
    }

    // 新界面处理完业务后如果需要告知上一组件是否成功则需要主动调用该方法，容器会帮忙把消息回传给对该界面业务感兴趣的所有组件
    public void onComplete(int result, ZBundle zBundle) {
        String key = zBundle.getUi().getClass().getCanonicalName();
        if (bundlePool.containsKey(key)) {
            ArrayList<ZBundle> bundleList = bundlePool.get(key);
            int size = bundleList.size();
            for (int i = 0; i < size; i++) {
                ZBundle zbjBundle2 = bundleList.get(i);
                zbjBundle2.getCallback()
                        .onComplete(result, zBundle.getData());
            }
            bundlePool.remove(key);
        }
    }


    public void onCompleteNoremove(int result, ZBundle zBundle) {
        String key = zBundle.getUi().getClass().getCanonicalName();
        if (bundlePool.containsKey(key)) {
            ArrayList<ZBundle> bundleList = bundlePool.get(key);
            int size = bundleList.size();
            for (int i = 0; i < size; i++) {
                ZBundle zbjBundle2 = bundleList.get(i);
                zbjBundle2.getCallback()
                        .onComplete(result, zBundle.getData());
            }
            // bundlePool.remove(key);
        }
    }

    /**
     * -------------界面生命周期管理 --------------
     **/
    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        Log.d("-----addActivity---", activity.getClass().getSimpleName());
        activityList.add(activity);
    }

    // Activity移除
    public void removeActivity(Activity activity) {
        Log.d("-----removeActivity---", activity.getClass().getSimpleName());
        activityList.remove(activity);
        // 界面处理完业务后如果不需要告知对该界面业务感兴趣的所有组件，则该界面关闭时移除所有监听该界面组件的映射
        String key = activity.getClass().getCanonicalName();
        if (bundlePool.containsKey(key)) {
            bundlePool.remove(key);
        }
    }


    public boolean isLastSecond(Class<?> cls) {
        for (Activity activity : activityList) {
            if (activity.getClass().equals(cls)) {
                if (activityList.size() >= 2)
                    return activityList.get(activityList.size() - 2) == activity;
            }
        }

        return false;
    }

    public boolean isTopActivity(Activity activity) {
        return activityList.get(activityList.size() - 1) == activity;
    }

    public boolean isTopActivity(Class<?> cls) {
        for (int i = activityList.size() - 1; i >= 0; i--) {
            if (activityList.get(i).getClass().equals(cls)) {
                return activityList.get(activityList.size() - 1) == activityList.get(i);
            }
        }


        return false;
    }

    public boolean isExist(Activity activity) {
        for (int i = 0; i < activityList.size(); i++) {
            if (activityList.get(i) == activity)
                return true;

        }
        return false;
    }

    public boolean isExistKolReplay() {
        return isExist(KolReplyActivity.class);
    }


    public Activity getTopActivity() {
        if (activityList.size() >= 1) {
            return activityList.get(activityList.size() - 1);
        }
        return null;
    }

    /**
     * 指定的Activity是否存在
     */
    public boolean isExist(Class<?> cls) {
        for (Activity activity : activityList) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
            activity.finish();
            activity = null;
        }
    }


    // 根据名字关闭Activity
    public void finishActivity(String activityName) {
        int size = activityList.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activityList.get(i);
            if (activity.getClass().getSimpleName().equals(activityName)) {
                Log.d("-----removeActivity---", activityName);
                removeActivity(activity);
                activity.finish();
                return;
            }
        }
    }

}
