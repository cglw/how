package com.prettyyes.user.api.netXutils.Apis;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ProgressCallback;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.Apis
 * Author: SmileChen
 * Created on: 2016/9/1
 * Description: Nothing
 */
public interface VersionApi extends BaseApi{
    public abstract void getVersion(NetReqCallback netWorkCallback);
    public abstract void downLoadFile(String filename, ProgressCallback progressCallback);
    public abstract void splashImg(NetReqCallback netReqCallback);
    public abstract void downLoadImg(String url,String filename,ProgressCallback progressCallback);

}
