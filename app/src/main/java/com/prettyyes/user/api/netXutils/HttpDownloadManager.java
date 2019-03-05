package com.prettyyes.user.api.netXutils;

import com.prettyyes.user.api.netXutils.ApiImpls.VersionApiImpl;

/**
 * Created by chengang on 2017/6/23.
 */

public class HttpDownloadManager {

    public HttpDownloadManager()
    {

    }

    public void download(String file)
    {
        new VersionApiImpl().downLoadFile(file, new ProgressCallback() {
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

            }
        });
    }

}
