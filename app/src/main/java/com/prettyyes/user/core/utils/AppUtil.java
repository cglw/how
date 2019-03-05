package com.prettyyes.user.core.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.meituan.android.walle.WalleChannelReader;
import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.view.app.ExToast;
import com.prettyyes.user.core.MyLifecycleHandler;
import com.prettyyes.user.core.containter.JumpPageManager;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import static android.content.Context.WINDOW_SERVICE;
import static com.prettyyes.user.app.base.BaseApplication.APP_CHANNEL;
import static com.umeng.socialize.Config.appName;


public class AppUtil {

    public static Bitmap blur(Activity activity) {
        Bitmap bitmap = AppUtil.myShot(activity, 15);
        if (bitmap == null)
            return null;

        Bitmap bitmap1 = FastBlurUtil.getBitmap(bitmap);
        if (bitmap1 == null)
            return null;
        return ImageHelper.comp(bitmap1);
    }

    private static final String TAG = "AppUtil";

    public void showLongToast(final Context context, String who, String question, final int task_id) {
//        showMyToast(context, who, question, task_id);
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            if (!Settings.canDrawOverlays(context)) {
//                AppUtil.showToastShort("你需要开启管理窗口权限");
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//                return;
//            } else {
//                showMyToast(context, who, question, task_id);
//            }
//
//        } else {
//
//
//            showMyToast(context, who, question, task_id);
//
//        }
    }

    private void showMyToast(final Context context, String who, String question, final int task_id) {
        final ExToast toast = ExToast.makeText(context, "", 2500);
        toast.setGravity(Gravity.TOP, 0, 80);
        TextView tv = new TextView(context);
        tv.setWidth(BaseApplication.ScreenWidth - DensityUtil.dip2px(50));
        tv.setHeight(DensityUtil.dip2px(50));
        tv.setGravity(Gravity.CENTER);
        tv.setText(who + "回答了你的问题：" + question);
        tv.setTextSize(15);
        tv.setMaxLines(2);
        tv.setEllipsize(TextUtils.TruncateAt.END);
        tv.setTextColor(Color.WHITE);
        tv.setClickable(true);

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        JumpPageManager.getManager().goMoreSpuReply(context, task_id + "");
                        break;
                }
                return true;
            }

        });
        tv.setBackgroundResource(R.drawable.bg_round_darkgreen_6);
        toast.setAnimations(R.style.anim_toast_top_message);
        toast.setView(tv);
        toast.show();
    }

    public static void showToastByView(final Context context, String text, View view) {

        if (StringUtils.strIsEmpty(text))
            return;

        Toast result = new Toast(context);
        //获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //由layout文件创建一个View对象
//        View view = inflater.inflate(R.layout.view_home_notify, null);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(BaseApplication.ScreenWidth,
//                DensityUtil.dip2px(36));
//        View toastTextView = view.findViewById(R.id.view_notify_home);
//        TextView tv = (TextView) view.findViewById(R.id.tv_homenotify_desc);
//        tv.setText(text);
        //设置TextView的宽度为 屏幕宽度
//        toastTextView.setLayoutParams(layoutParams);
        result.setGravity(Gravity.TOP, 0, DensityUtil.dip2px(40));
        result.setView(view);
        result.setDuration(Toast.LENGTH_SHORT);
        result.show();
//
//        TopShowViewPorxy topShowViewPorxy=new TopShowViewPorxy(view);
//        topShowViewPorxy.
//

    }


    public static boolean isFamous(int famous) {
        if (famous == 1) {
            return true;
        }
        return false;

    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        return bmp;

    }

    public static Bitmap myShot(Activity activity) {
        //获取当前屏幕的大小
        int width = BaseApplication.ScreenWidth;
        int height = BaseApplication.ScreenHeight;
        //生成相同大小的图片
        Bitmap temBitmap = null;
        temBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        //找到当前页面的跟布局
        View view = activity.getWindow().getDecorView().getRootView();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();
        return temBitmap;

    }

    public static Bitmap myShot(Activity activity, int scale) {
        //获取当前屏幕的大小
        int width = BaseApplication.ScreenWidth;
        int height = BaseApplication.ScreenHeight;
        //生成相同大小的图片
        Bitmap temBitmap = null;
        //temBitmap=Bitmap.createBitmap( width/scale, height/scale, Bitmap.Config.RGB_565 );
        //找到当前页面的跟布局
        View view = activity.getWindow().getDecorView().getRootView();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();
        return temBitmap;

    }

    public static void loadTypaeFace(Context context, TextView tv) {
        if (context != null && tv != null) {

            Typeface type = null;
            try {
                type = BaseApplication.TYPE_MONEY;
            } catch (Exception ee) {

            }
            if (null != type)
                tv.setTypeface(type);
        }
    }


    public static void showToastShort(String content) {
        if (StringUtils.strIsEmpty(content))
            return;
        Toast.makeText(BaseApplication.getAppContext(), content, Toast.LENGTH_SHORT).show();
    }


    public static void showToastShort(int res) {
        Toast.makeText(BaseApplication.getAppContext(), BaseApplication.getAppContext().getString(res), Toast.LENGTH_SHORT).show();
    }

    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 描述：打开并安装文件.
     *
     * @param context file
     * @param file    apk文件路径
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 描述：卸载程序.
     *
     * @param packageName 包名
     */
    public static void uninstallApk(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + packageName);
        intent.setData(packageURI);
        context.startActivity(intent);
    }


    /**
     * 用来判断服务是否运行.
     *
     * @param ctx
     * @param className 判断的服务名字 "com.xxx.xx..XXXService"
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context ctx, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
        Iterator<RunningServiceInfo> l = servicesList.iterator();
        while (l.hasNext()) {
            RunningServiceInfo si = (RunningServiceInfo) l.next();
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }
        return isRunning;
    }


    public static boolean isActivityRunning(Context mContext, String activityClassName) {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if (info != null && info.size() > 0) {
            ComponentName component = info.get(0).topActivity;
            if (activityClassName.equals(component.getClassName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 停止服务.
     *
     * @param ctx
     * @param className the class name
     * @return true, if successful
     */
    public static boolean stopRunningService(Context ctx, String className) {
        Intent intent_service = null;
        boolean ret = false;
        try {
            intent_service = new Intent(ctx, Class.forName(className));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (intent_service != null) {
            ret = ctx.stopService(intent_service);
        }
        return ret;
    }


    /**
     * @param
     * @return int
     * @description 获取cpu个数
     * @auther jiaBF
     */
    public static int getNumCores() {
        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    //Check if filename is "cpu", followed by a single digit number
                    if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                        return true;
                    }
                    return false;
                }

            });
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            //Default to return 1 core
            return 1;
        }
    }


    /**
     * 描述：判断网络是否有效.
     *
     * @param context the context
     * @return true, if is network available
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


    /**
     * 描述：判断网络是否有效.没有网络时进行提示
     *
     * @param context the context
     * @return true, if is network available
     */
    public static boolean isNetworkAvailableMsg(Context context, int msgId) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    } else {
                    }
                } else {
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Gps是否打开
     * 需要<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />权限
     *
     * @param context the context
     * @return true, if is gps enabled
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * wifi是否打开.
     *
     * @param context the context
     * @return true, if is wifi enabled
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断当前网络是否是wifi网络.
     *
     * @param context the context
     * @return boolean
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前网络是否是3G网络.
     *
     * @param context the context
     * @return boolean
     */
    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }


    /**
     * 导入数据库
     *
     * @param context
     * @param
     * @return
     */
    public static boolean importDatabase(Context context, String dbName, int rawRes) {
        int buffer_size = 1024;
        InputStream is = null;
        FileOutputStream fos = null;
        boolean flag = false;

        try {
            String dbPath = "/data/data/" + context.getPackageName() + "/databases/" + dbName;
            File dbfile = new File(dbPath);
            //判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
            if (!dbfile.exists()) {
                //欲导入的数据库
                if (!dbfile.getParentFile().exists()) {
                    dbfile.getParentFile().mkdirs();
                }
                dbfile.createNewFile();
                is = context.getResources().openRawResource(rawRes);
                fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[buffer_size];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.flush();
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
        return flag;
    }

    /**
     * 获取手机串号(IMEI)
     *
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        return telephonyManager.getDeviceId();
    }

    public static String getAppVersionCode(Context context) {
        return getVersionCode(context) + "";
    }

    public static String getPackageName(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        String name = "com.prettyyes.user";
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            name = packInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NullPointerException e) {
        }
        return name;
    }

    /**
     * 获取当前应用的版本号
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        int version = 0;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NullPointerException e) {
        }
        return version;
    }

    /**
     * 获取当前应用的版本名
     *
     * @return
     */
    public static String getVersionName(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null)
            return "";
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        String version = "";
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取当前应用的版本名
     *
     * @return
     */
    public static String getVersionName() {
        if (BaseApplication.context == null)
            return "";
        // 获取packagemanager的实例
        PackageManager packageManager = BaseApplication.context.getPackageManager();
        if (packageManager == null)
            return "";
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        String version = "";
        try {
            packInfo = packageManager.getPackageInfo(BaseApplication.context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static final long WAIT_TIME = 2L * 1000;//返回按钮连续按下的时间
    public static long exitTime;//上一次按下返回键的时间戳

    /**
     * 初始化手机屏幕的宽高
     *
     * @param activity
     */
    public static DisplayMetrics getScreenSize(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, double dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(double dpValue) {
        final float scale = BaseApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, double pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * sp转px
     *
     * @param context
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     * @return
     */
    public static boolean hideInputMethod(View view) {
        if (view == null || view.getContext() == null)
            return false;
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(view.getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean hideInputMethod(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() == null)
            return false;
        return imm.hideSoftInputFromWindow(activity.getCurrentFocus().getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     * @return
     */
    public static void showInputMethod(View view) {
        if (view == null || view.getContext() == null)
            return;
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showInputMethod(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null == activity || activity.getCurrentFocus() == null)
            return;
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * 判断一个应用程序是否在前台运行
     *
     * @param context
     * @return
     */
    public static boolean isRunningForeground(Context context) {
//        ActivityManager am = (ActivityManager) context
//                .getSystemService(Context.ACTIVITY_SERVICE);
//        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
//        String currentPackageName = cn.getPackageName();
//        if (!TextUtils.isEmpty(currentPackageName)
//                && currentPackageName.equals(context.getPackageName())) {
//            return true;
//        }
        boolean applicationInForeground = MyLifecycleHandler.isApplicationInForeground();
        if (applicationInForeground)

            return true;

        return false;
    }


    /**
     * @param @param  context
     * @param @return
     * @return List<ApplicationInfo>
     * @description 获取手机装的应用包名
     * @author jiaBF
     */
    public static List<ApplicationInfo> getInstallAppInfo(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApplications =
                packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        return installedApplications;
    }

    public static List<String> getPackageName(Activity activity) {
        PackageManager packageManager = activity.getPackageManager();
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);
        List<String> pagNames = new ArrayList<>();
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            String pagName = packageInfo.packageName;
            pagNames.add(pagName);
            System.out.println("name==" + appName + ",package==" + pagName);
        }
        return pagNames;
    }

    /**
     * @param @param  context
     * @param @return
     * @return boolean
     * @description
     * @author jiaBF
     */
    public static boolean hasInstallByPackageName(Context context, String packageName) {
        List<ApplicationInfo> installedApplications = getInstallAppInfo(context);
        for (ApplicationInfo appInfo : installedApplications) {
            if (appInfo.packageName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一个应用程序是否在前台运行
     * <uses-permission android:name = “android.permission.GET_TASKS”/>
     *
     * @return
     */
    public static String getActivityName(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        String activityName = am.getRunningTasks(1).get(0).topActivity.getClassName();
        return activityName;
    }

    /**
     * 判断除了当前activity栈中是否还有其他activity
     *
     * @param @param  context
     * @param @return
     * @return boolean
     * @description
     * @author jiaBF
     */
    public static boolean hasActivityByTask(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        return am.getRunningTasks(1).size() > 1;
    }

    /**
     * 直接拨打电话
     */
    public static void callPhone(final Context context, String mobile) {
        Intent phoneIntent = new Intent("android.intent.action.CALL",
                Uri.parse("tel:" + mobile));
        context.startActivity(phoneIntent);
    }

    /**
     * @param @param context
     * @param @param mobile
     * @return void
     * @description 打开拨打电话面板
     * @author jiaBF
     */
    public static void dialPhone(final Context context, String mobile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + mobile));
        context.startActivity(intent);
    }

    public static void sendMessage(String phone, String content, Context context) {
        Uri uri = Uri.parse("smsto:" + phone);
        Intent ii = new Intent(Intent.ACTION_SENDTO, uri);
        ii.putExtra("sms_body", content);
        context.startActivity(ii);
    }

    /**
     * 网络类型  是2g网络或者WIFI和3G的判断
     * return 1是wifi 、2是2g 、3是3g
     *
     * @param context
     * @return
     */
    public static int isWifiOr2GNetWork(Context context) {
        int flag = 0;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo != null && networkinfo.isAvailable()) {
            if (networkinfo.getType() == ConnectivityManager.TYPE_WIFI) {
                flag = 1;
            } else if (networkinfo != null && networkinfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                //电信2G是 NETWORK_TYPE_CDMA
                //移动2G卡 NETWORK_TYPE_EDGE
                //联通的2G NETWORK_TYPE_GPRS
                if (networkinfo.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS
                        || networkinfo.getSubtype() == TelephonyManager.NETWORK_TYPE_CDMA
                        || networkinfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE) {
                    flag = 2;
                } else {
                    flag = 3;
                }
            }
        }
        return flag;
    }

    public static int getWindowWidth(Context context) {
        // 获取屏幕分辨率
        WindowManager wm = (WindowManager) (context
                .getSystemService(WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;
        return mScreenWidth;
    }

    public static int getWindowHeigh(Context context) {
        // 获取屏幕分辨率
        WindowManager wm = (WindowManager) (context
                .getSystemService(WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenHeigh = dm.heightPixels;
        return mScreenHeigh;
    }

    /**
     * 切换视图的显示
     *
     * @param v 切换后可见为true else false
     */
    public static boolean toggle(View v) {
        if (v.getVisibility() == View.GONE) {
            v.setVisibility(View.VISIBLE);
            return true;
        } else {
            v.setVisibility(View.GONE);
            return false;
        }
    }

    /**
     * source 点击view切换切换视图的显示
     *
     * @param source 点击的目标view,自动添加点击事件
     * @param target
     */
    public static void setToggle(View source, final View target) {
        source.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                toggle(target);
            }
        });
    }

    /**
     * 获取系统SDK版本
     *
     * @param @return
     * @return int
     * @description
     * @author jiaBF
     */
    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {

        }
        return version;
    }


    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }


    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }

    public static String getAppChannel() {

        if (APP_CHANNEL == null)
            APP_CHANNEL = WalleChannelReader.getChannel(BaseApplication.getAppContext());
        return APP_CHANNEL;
    }

    public static String getIdfa() {
        return new DeviceUuidFactory(BaseApplication.getAppContext()).getDeviceUuid() + "";
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }


}
