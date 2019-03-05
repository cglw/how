package com.prettyyes.user.api.netXutils;

import com.prettyyes.user.AppConfig;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;

/**
 * Created by Hornen on 15/11/20.
 */
@HttpRequest(
        host = AppConfig.BaseUrl,
        path = "index.php",
        builder = DefaultParamsBuilder.class
)
public class HowParams extends RequestParams {

}
