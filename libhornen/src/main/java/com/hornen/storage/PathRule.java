package com.hornen.storage;

import android.content.Context;
import android.os.Environment;

import com.hornen.common.MD5;

import java.io.File;
import java.util.Random;

/**
 * Created by Hornen on 15/11/11.
 */
public class PathRule {

    private Random randomSeed = new Random();

    private Context context;
    private String AppDirName = ".unset.path";
    public PathRule(Context cxt) {
        this.context = cxt;
    }

    public void setAppName(String appName) {
        if(null != appName) {
            this.AppDirName = appName;
        }
    }

    public String getRandomName() {
        return MD5.getMD5(String.valueOf(randomSeed.nextDouble()));
    }

    public String getInternalFilePath() {
        return context.getFilesDir().getAbsolutePath();
    }

    public String getInternalCachePath() {
        return context.getCacheDir().getAbsolutePath();
    }

    public String getExternalFilePath() {
        return context.getExternalFilesDir("").getAbsolutePath();
    }

    public String getExternalCachePath() {
        return context.getExternalCacheDir().getAbsolutePath();
    }

    public String getPublicFilePath() {
        return createDirectoryIfNotExist(getPublicPath() + "/file");
    }

    public String getPublicCachePath() {
        return createDirectoryIfNotExist(getPublicPath() + "/cache");
    }

    public String getInternalRandomFile() {
        return getInternalFilePath() + "/" + getRandomName();
    }

    public String getInternalRandomCache() {
        return getInternalCachePath() + "/" + getRandomName();
    }

    public String getExternalRandomFile() {
        return  getExternalFilePath() + "/" + getRandomName();
    }

    public String getExternalRandomCache() {
       return getExternalCachePath() + "/" + getRandomName();
    }

    public String getPublicRandomFile() {
        return getPublicFilePath() + "/" + getRandomName();
    }

    public String getPublicRandomCache() {
        return getPublicCachePath() + "/" + getRandomName();
    }

    public String createDirectoryIfNotExist(String dir) {
        File dirF = new File(dir);
        if(dirF.isDirectory()) {
            dirF.mkdirs();
        }

        return dir;
    }

    public String getPublicPath() {
        return createDirectoryIfNotExist(getSDCardPath() +"/" + AppDirName);
    }

    private String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
