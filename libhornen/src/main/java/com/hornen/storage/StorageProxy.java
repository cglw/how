package com.hornen.storage;

import android.content.Context;

/**
 * Created by Hornen on 15/11/24.
 */
public class StorageProxy implements IStorageProvider {

    private final IStorageProvider mFileStorage;
    private final IStorageProvider mSPStoreage;

    public StorageProxy(Context cxt) {
        this(cxt, "generic");
    }

    public StorageProxy(Context cxt, String zoneName) {
        mFileStorage = new FileStorage(cxt, zoneName);
        mSPStoreage = new SharedPreferenceStorage(cxt, zoneName);
    }

    @Override
    public void save(String key, Object obj) {
        if(Toolkit.isBasicValueObject(obj)) {
            mSPStoreage.save(key, obj);
        } else {
            mSPStoreage.remove(key);
            mFileStorage.save(key, obj);
        }
    }

    @Override
    public <X> X resolve(String key, Class<X> clazz) {
        if(Toolkit.isBasicValueClass(clazz)) {
            return mSPStoreage.resolve(key, clazz);
        } else {
            return mFileStorage.resolve(key, clazz);
        }
    }

    @Override
    public boolean exist(String key) {
        String key1 = this.encryptKey(key);
        return this.mSPStoreage.exist(key1) ? true : this.mFileStorage.exist(key1);
    }

    @Override
    public void remove(String key) {
        mFileStorage.remove(this.encryptKey(key));
        mSPStoreage.remove(this.encryptKey(key));
    }

    @Override
    public void removeAll() {
        mFileStorage.removeAll();
        mSPStoreage.removeAll();
    }

    @Override
    public String getProviderIden() {
        return "StorageProxy";
    }

    private String encryptKey(String key) {
        return key;
    }
}
