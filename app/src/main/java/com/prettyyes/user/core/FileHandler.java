package com.prettyyes.user.core;

import android.graphics.Bitmap;

import com.hornen.common.MD5;
import com.prettyyes.user.api.netXutils.ApiImpls.VersionApiImpl;
import com.prettyyes.user.api.netXutils.ProgressCallback;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.core.utils.ImageHelper;

import java.io.File;
import java.io.IOException;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/9/27
 * Description: Nothing
 */
public class FileHandler {
    public FileHandler() {

    }

    /**
     * 得到bitmap
     *
     * @param key
     * @return
     */
    public Bitmap getDownLoadBitmap(String key) {
        File updateFile = FileUtils.getDiskCacheDir(BaseApplication.getAppContext(), MD5.getMD5Str(key));
        if (updateFile.exists() && updateFile.length() > 0) {
            try {
                return ImageHelper.getimage(updateFile.getAbsolutePath(), 1000);
            } catch (Exception ee) {

            }
        }
        return null;
    }

    public File getDownLoadFile(String key) {
        File updateFile = FileUtils.getDiskCacheDir(BaseApplication.getAppContext(), MD5.getMD5Str(key));
        if (updateFile.exists() && updateFile.length() > 0) {
            try {
                return updateFile;
            } catch (Exception ee) {

            }
        }
        return null;
    }

    /**
     * 根据url命名
     *
     * @param url
     * @param callback
     */
    public void download(String url, final Callback callback) {

        File updateFile = FileUtils.getDiskCacheDir(BaseApplication.getAppContext(), MD5.getMD5Str(url));
        if (!updateFile.exists()) {
            try {
                updateFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new VersionApiImpl().downLoadImg(url, updateFile.getAbsolutePath(), new ProgressCallback() {
            @Override
            public void onFail(String paramString1, String paramString2) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                if (null != callback) {
                    callback.progress((int) (current / total * 100));
                }
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String result) {
                if (null != callback) {
                    callback.downloadsuccess();
                }
            }
        });
    }

    public interface Callback {
        public void downloadsuccess();

        public void progress(int x);
    }

}
