package com.mangokiwi.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tangmaolei on 5/26/17.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TeacherAlreadyExistException extends RuntimeException{
	public TeacherAlreadyExistException(String msg) { super(msg);}

	public TeacherAlreadyExistException(String msg, Throwable cause) { super(msg, cause);}
}
