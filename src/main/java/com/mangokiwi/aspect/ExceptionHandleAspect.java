package com.mangokiwi.aspect;

import com.mangokiwi.core.annotation.HandleEmptyRequestBody;
import com.mangokiwi.core.exception.EmptyRequestBodyException;
import com.mangokiwi.core.exception.EntityNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;


/**
 * Created by zhenfeng on 5/5/17.
 */

@Aspect
@Component
public class ExceptionHandleAspect {

	@AfterReturning(pointcut = "@annotation(com.mangokiwi.core.annotation.HandleEntityNotFound)", returning = "returningObject")
	public void entityNotFoundAfterAdvice(JoinPoint joinPoint, Object returningObject) {
		if (returningObject == null) {
			MethodSignature signature = ((MethodSignature) joinPoint.getSignature());
			Method method = signature.getMethod();
			throw new EntityNotFoundException(method.getReturnType().getSimpleName() + " is not found");
		}
	}

	@Before("@annotation(com.mangokiwi.core.annotation.HandleEmptyRequestBody)")
	public void emptyRequestBeforeAdvice(JoinPoint joinPoint) {
		MethodSignature methodSignature = ((MethodSignature) joinPoint.getSignature());
		Method method = methodSignature.getMethod();
		HandleEmptyRequestBody emptyRequestBodyAnnotation = method.getAnnotation(HandleEmptyRequestBody.class);
		Annotation[][] annotations = method.getParameterAnnotations();
		Object[] args = joinPoint.getArgs();
		boolean find = false;
		int i = 0;
		OuterLoop:
		for (; i < args.length; i++) {
			for (Annotation annotation : annotations[i]) {
				if (RequestBody.class.isInstance(annotation)) {
					find = true;
					break OuterLoop;
				}
			}
		}
		if (find) {
			boolean all = emptyRequestBodyAnnotation.allFields();
			boolean include = false;
			HashSet<String> checkingFields = null;
			if (!all) {
				include = emptyRequestBodyAnnotation.includingFields().length != 0;
				if (include)
					checkingFields = new HashSet<>(
							Arrays.asList(emptyRequestBodyAnnotation.includingFields()));
				else
					checkingFields = new HashSet<>(
							Arrays.asList(emptyRequestBodyAnnotation.excludingFields()));
			}
			Object currentParam = args[i];
			for (Field field : currentParam.getClass().getDeclaredFields()) {
				if (all || (include && checkingFields.contains(field.getName()))
						|| (!include && !checkingFields.contains(field.getName()))) {
					try {
						field.setAccessible(true);
						if (field.get(currentParam) == null) {
							throw new EmptyRequestBodyException(field.getName() + " is empty");
						}
					} catch (IllegalAccessException e) {
						// this won't happen since accessibility has been set to true
					}
				}
			}
		}
	}
}
