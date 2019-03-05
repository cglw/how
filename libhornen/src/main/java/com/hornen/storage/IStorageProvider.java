package com.hornen.storage;

/**
 * Created by Hornen on 15/9/29.
 */
public interface IStorageProvider {
    void save(String var1, Object var2);

    <X> X resolve(String var1, Class<X> var2);

    boolean exist(String var1);

    void remove(String var1);

    void removeAll();

    String getProviderIden();
}
