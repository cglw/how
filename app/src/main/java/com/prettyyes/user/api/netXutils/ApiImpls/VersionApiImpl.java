package com.prettyyes.user.api.netXutils.ApiImpls;

import com.google.gson.reflect.TypeToken;
import com.prettyyes.user.AppConfig;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.Apis.VersionApi;
import com.prettyyes.user.api.netXutils.ProgressCallback;
import com.prettyyes.user.api.netXutils.urls.VersionUrl;
import com.prettyyes.user.model.AdvModel;
import com.prettyyes.user.model.VersionModel;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.ApiImpls
 * Author: SmileChen
 * Created on: 2016/9/1
 * Description: Nothing
 */
public class VersionApiImpl implements VersionApi {

    @Override
    public void getVersion( NetReqCallback netWorkCallback) {
        Type localType = new TypeToken<ApiResContent<VersionModel>>() {
        }.getType();


        this.httpAccess.getAsyn(VersionUrl.Url_version, localType, netWorkCallback);
    }

    @Override
    public void downLoadFile(String filename, ProgressCallback progressCallback) {
        this.httpAccess.downLoadFileAsyn(AppConfig.APK_DOWNLOAD_URL, filename, progressCallback);

    }

    @Override
    public void splashImg(NetReqCallback netReqCallback) {
        Type localType = new TypeToken<ApiResContent<AdvModel>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(VersionUrl.Url_splashimg,new HashMap<String, String>(), localType, netReqCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downLoadImg(String url,String filename, ProgressCallback progressCallback) {
        this.httpAccess.downLoadFileAsyn(url, filename, progressCallback);
    }


}
