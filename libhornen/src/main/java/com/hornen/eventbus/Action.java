package com.hornen.eventbus;

/**
 * Created by Hornen on 15/9/29.
 */
public interface Action<T> {
    void exectue(T var);
}
