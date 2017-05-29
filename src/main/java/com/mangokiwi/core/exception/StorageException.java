package com.mangokiwi.core.exception;

import org.aspectj.lang.annotation.control.CodeGenerationHint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by maolei on 5/23/17.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StorageException extends RuntimeException {
    public StorageException(String msg) {
        super(msg);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
