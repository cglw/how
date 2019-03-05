package com.prettyyes.user.core;

import android.util.Log;

import com.hornen.storage.StorageProxy;
import com.hornen.storage.Toolkit;
import com.prettyyes.user.app.base.BaseApplication;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/10/21
 * Description: SharedPreference
 */
public class SPUtils<T> {
    private static final String TAG = "SPUtils";
    StorageProxy storageProxy;

    public SPUtils() {
        storageProxy = new StorageProxy(BaseApplication.getAppContext());
    }

    public void save(T t) {
        storageProxy.save(t.getClass().getCanonicalName(), t);
    }

    public void saveData(String key, Object obj) {
        if(Toolkit.isBasicValueObject(obj)) {
            storageProxy.save(key, obj);
        } else {
            storageProxy.remove(key);
        }
    }
    public void save(T t, String type) {
        Log.e(TAG, "save: ");
        storageProxy.save(t.getClass().getCanonicalName() + type, t);
    }



    public T get(String key, Class<T> clazz) {
        if (Toolkit.isBasicValueClass(clazz)) {
            return storageProxy.resolve(key, clazz);
        } else {
            return storageProxy.resolve(key, clazz);
        }
    }

    public void delete(Class cl) {
        storageProxy.remove(cl.getCanonicalName());
    }

    public T get(Class cl) {
        if (storageProxy.exist(cl.getCanonicalName())) {
            return (T) storageProxy.resolve(cl.getCanonicalName(), cl);
        }
        return null;
    }

    public T get(Class cl, String type) {
        if (isEixst(cl, type))
            return (T) storageProxy.resolve(cl.getCanonicalName() + type, cl);
        return null;
    }

    public boolean isEixst(Class cl, String type) {
        return storageProxy.exist(cl.getCanonicalName() + type);
    }


}
