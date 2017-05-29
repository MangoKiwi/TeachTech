package com.mangokiwi.core.exception;

/**
 * Created by maolei on 5/25/17.
 */
public class StorageFileInvalidTypeException extends StorageException {
    public StorageFileInvalidTypeException(String message) {
        super(message);
    }

    public StorageFileInvalidTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
