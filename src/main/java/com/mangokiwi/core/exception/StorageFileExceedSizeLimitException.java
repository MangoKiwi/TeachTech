package com.mangokiwi.core.exception;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;

/**
 * Created by maolei on 5/25/17.
 */
public class StorageFileExceedSizeLimitException extends FileUploadBase.SizeLimitExceededException {
    public StorageFileExceedSizeLimitException(String message, long actual, long permitted) {
        super(message, actual, permitted);
    }
}
