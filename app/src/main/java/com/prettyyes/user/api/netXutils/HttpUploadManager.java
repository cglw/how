package com.prettyyes.user.api.netXutils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.hornen.common.MD5;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.api.netXutils.requests.UploadVedioReq;
import com.prettyyes.user.api.netXutils.response.UploadVedioRes;
import com.prettyyes.user.core.AppUploadListener;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.core.utils.ImageHelper;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.ThreadManager;
import com.prettyyes.user.model.UploadImgModel;
import com.prettyyes.user.model.v8.VedioEntity;

import java.io.File;

/**
 * Created by chengang on 2017/6/23.
 */

public class HttpUploadManager {

    private static final String TAG = "HttpUploadManager";
    public Object tag;


    public HttpUploadManager setTag(Object tag) {
        this.tag = tag;
        return this;
    }

    public static HttpUploadManager create() {
        return new HttpUploadManager();
    }

    private volatile static HttpUploadManager INSTANCE;

    /*
     * 获取单例
     * @return
     */
    public static HttpUploadManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpUploadManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpUploadManager();

                }
            }
        }
        return INSTANCE;
    }


    private String pic_list = "";
    private String target_path = "";

    public String getPic_list() {
        return pic_list;
    }

    public void startUpload(final String path, final String type, final AppUploadListener appUploadListener) {
        if (path == null)
            return;

        final Handler mainHandler = new Handler(Looper.getMainLooper());

        Runnable http = new Runnable() {
            @Override
            public void run() {

                if (path.startsWith("http")) {


                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            appUploadListener.onNext(path, path);
                        }
                    });

                    return;
                }
                Bitmap comp = null;
                target_path = path;

                if (path.endsWith("gif")) {
                    //暂时不去压缩gif
                } else {

                    try {

                        File file = new File(path);

                        if (file.exists()) {
                            if (FileUtils.getFileSize(file) / 1024 < 1024) {
                                target_path = path;
                            } else {
                                comp = ImageHelper.comp(path);
                                target_path = ImageHelper.saveBitmapToCacheFile(comp, MD5.getMD5(path) + "." + path.substring(path.lastIndexOf(".") + 1));
                            }

                        }

                    } catch (final Exception ee) {
                        LogUtil.i(TAG, ee + "");
                        if (target_path == null)
                            target_path = "https://image.prettyyes.com/ic_defaultimg.png";
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                appUploadListener.onError(ee + "");
                            }
                        });
                        return;
                    }
                }


                if (target_path == null)
                    target_path = "https://image.prettyyes.com/ic_defaultimg.png";


                userApi = new UserApiImpl();
                userApi.userUploadImg(SpMananger.getUUID(), type, target_path, new ProgressCallback() {
                    @Override
                    public void onFail(final String paramString1, String paramString2) {
                        ZBundleCore.getInstance().getTopActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (appUploadListener != null)
                                            appUploadListener.onError(paramString1);
                                    }
                                });
                            }
                        });


                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onLoading(final long total, final long current, boolean isDownloading) {


                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                if (appUploadListener != null)
                                    appUploadListener.onProgress(current, total);
                            }
                        });


                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(final String result) {

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {


                                UploadImgModel uploadImgModel = GsonHelper.getGson().fromJson(result, UploadImgModel.class);
                                String path = "";
                                if (uploadImgModel != null)
                                    path = uploadImgModel.getExtra().getPic_url();
                                if (appUploadListener != null)
                                    appUploadListener.onNext(result, path);
                            }
                        });


                    }
                });
            }
        };

        ThreadManager.getDownloadPool().execute(http);


    }

    public void cancel(String path) {

    }


    public void startUploadVedio(final String path, final AppUploadListener appUploadListener) {


        Runnable http = new Runnable() {
            @Override
            public void run() {
                final Handler mainHandler = new Handler(Looper.getMainLooper());

                if (path.startsWith("http")) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            appUploadListener.onNext(path, path);
                        }
                    });
                    return;
                }

                new UploadVedioReq().setBinary_video(new File(path)).postFile(new ProgressCallback() {
                    @Override
                    public void onFail(String paramString1, String paramString2) {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String result) {

                        UploadVedioRes uploadVedioRes = GsonHelper.getGson().fromJson(result, UploadVedioRes.class);
                        if (uploadVedioRes.isSuccess()) {
                            VedioEntity vedioEntity = uploadVedioRes.getExtra();
                        }

                    }
                });
            }
        };

        ThreadManager.getDownloadPool().execute(http);


    }

    UserApiImpl userApi;

}
