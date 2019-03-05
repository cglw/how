package com.prettyyes.user.core.utils;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Army on 16/10/22
 */
public class PermissionUtils {

    private static final String TAG = PermissionUtils.class.getSimpleName();


    public static final int CODE_MULTI_PERMISSION = 10010;

    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;

    private static final String[] requestPermissions = {
            PERMISSION_RECORD_AUDIO,
            PERMISSION_GET_ACCOUNTS,
            PERMISSION_READ_PHONE_STATE,
            PERMISSION_CALL_PHONE,
            PERMISSION_CAMERA,
            PERMISSION_ACCESS_FINE_LOCATION,
            PERMISSION_ACCESS_COARSE_LOCATION,
            PERMISSION_READ_EXTERNAL_STORAGE,
            PERMISSION_WRITE_EXTERNAL_STORAGE,
            PERMISSION_WRITE_CALENDAR
    };
    public static final String[] myrequestPermissions = {

            PERMISSION_CAMERA,
            PERMISSION_WRITE_EXTERNAL_STORAGE
    };

    public static abstract class PermissionGrant {
        public abstract void onPermissionGranted(int requestCode);
        public void onPermissionGranted(int requestCode,String[] permissions){}
        public void onPermissionDenied(){}
    }


    private static String getPerssionByCode(String code) {
        String res = "";
        switch (code) {
            case PERMISSION_RECORD_AUDIO:
                res = "访问录音";
                break;
            case PERMISSION_GET_ACCOUNTS:
                res = "手机账户";
                break;
            case PERMISSION_READ_PHONE_STATE:
                res = "手机状态";
                break;
            case PERMISSION_CALL_PHONE:
                res = "打电话";
                break;
            case PERMISSION_CAMERA:
                res = "访问相机";
                break;
            case PERMISSION_ACCESS_FINE_LOCATION:
                res = "文件访问";
                break;
            case PERMISSION_ACCESS_COARSE_LOCATION:
                res = "手机定位";
                break;
            case PERMISSION_WRITE_EXTERNAL_STORAGE:
                res = "读写取SD卡";
                break;
            case PERMISSION_WRITE_CALENDAR:
                res = "日历访问";
                break;
            case PERMISSION_READ_EXTERNAL_STORAGE:
                res = "读取SD卡";
                break;
        }

        return res;
    }




    private static void requestMultiResult(Activity activity, String[] permissions, int[] grantResults, PermissionGrant permissionGrant) {

        if (activity == null) {
            return;
        }

        //TODO
        Map<String, Integer> perms = new HashMap<>();

        ArrayList<String> notGranted = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            perms.put(permissions[i], grantResults[i]);
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permissions[i]);
            }
        }
        if (notGranted.size() == 0) {
            permissionGrant.onPermissionGranted(CODE_MULTI_PERMISSION);
            permissionGrant.onPermissionGranted(CODE_MULTI_PERMISSION,permissions);
        } else {

            String res = "";
            for (int i = 0; i < notGranted.size(); i++) {
                res += getPerssionByCode(notGranted.get(i));
                if (i != notGranted.size() - 1)
                    res += ",";
            }
            res = "您需要开启" + res + "权限";
            openSettingActivity(activity, res,permissionGrant);
        }

    }


        /**
         * 一次申请多个权限
         */
    public static void requestMultiPermissions(final Activity activity, PermissionGrant grant, String[] myrequestPermissions) {

        final List<String> permissionsList = getNoGrantedPermission(activity, false, myrequestPermissions);
        final List<String> shouldRationalePermissionsList = getNoGrantedPermission(activity, true, myrequestPermissions);
        //TODO checkSelfPermission
        if (permissionsList == null || shouldRationalePermissionsList == null) {
            return;
        }
        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                    CODE_MULTI_PERMISSION);
        } else if (shouldRationalePermissionsList.size() > 0) {
            ActivityCompat.requestPermissions(activity, shouldRationalePermissionsList.toArray(new String[shouldRationalePermissionsList.size()]),
                    CODE_MULTI_PERMISSION);
        } else {
            grant.onPermissionGranted(CODE_MULTI_PERMISSION);
        }

    }

    public static void requestSinglePermission(final Activity activity, PermissionGrant grant, String myrequestPermissions) {

        requestMultiPermissions(activity, grant, new String[]{myrequestPermissions});
    }

    /**
     * @param activity
     * @param requestCode  Need consistent with requestPermission
     * @param permissions
     * @param grantResults
     */
    public static void requestPermissionsResult(final Activity activity, final int requestCode, @NonNull String[] permissions,
                                                @NonNull int[] grantResults, PermissionGrant permissionGrant) {
        if (activity == null) {
            return;
        }
        if (requestCode == CODE_MULTI_PERMISSION) {
            requestMultiResult(activity, permissions, grantResults, permissionGrant);
            return;
        }

    }


    /**
     * @param activity
     * @param isShouldRationale true: return no granted and shouldShowRequestPermissionRationale permissions, false:return no granted and !shouldShowRequestPermissionRationale
     * @return
     */
    private static ArrayList<String> getNoGrantedPermission(Activity activity, boolean isShouldRationale, String[] myrequestPermissions) {

        ArrayList<String> permissions = new ArrayList<>();

        for (int i = 0; i < myrequestPermissions.length; i++) {
            String requestPermission = myrequestPermissions[i];


            //TODO checkSelfPermission
            int checkSelfPermission = -1;
            try {
                checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
            } catch (RuntimeException e) {
                Toast.makeText(activity, "please open those permission", Toast.LENGTH_SHORT)
                        .show();
                return null;
            }

            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                    if (isShouldRationale) {
                        permissions.add(requestPermission);
                    }

                } else {

                    if (!isShouldRationale) {
                        permissions.add(requestPermission);
                    }
                }

            }
        }

        return permissions;
    }

    private static void openSettingActivity(final Activity activity, String message, final PermissionGrant permissonGrant) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle("权限提醒");
        dialog.setMessage(message);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                permissonGrant.onPermissionDenied();

            }
        });
        dialog.create();
        dialog.show();
    }
}