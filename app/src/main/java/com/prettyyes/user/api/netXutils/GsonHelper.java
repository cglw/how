package com.prettyyes.user.api.netXutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Cg on 2016/7/1.
 */
public class GsonHelper {
    public static Gson getGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

}
