package com.hornen.storage;

/**
 * Created by Hornen on 15/9/29.
 */
public class StorageException extends RuntimeException {

    public StorageException() {
        super();
    }

    public StorageException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(Throwable throwable) {
        super(throwable);
    }
}
