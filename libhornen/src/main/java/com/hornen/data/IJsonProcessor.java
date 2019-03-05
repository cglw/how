package com.hornen.data;

/**
 * Created by Hornen on 15/11/24.
 */
public interface IJsonProcessor {
    byte[] serialize(Object var1);

    <T> T deserialize(byte[] var1, Class<T> var2);
}
