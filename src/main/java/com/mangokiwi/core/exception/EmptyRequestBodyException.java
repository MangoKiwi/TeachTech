package com.mangokiwi.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhenfeng on 5/17/17.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmptyRequestBodyException extends RuntimeException {
	public EmptyRequestBodyException(String msg){super(msg);}
}
