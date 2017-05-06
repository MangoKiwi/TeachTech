package com.mangokiwi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


import javax.persistence.EntityNotFoundException;

/**
 * Created by zhenfeng on 5/5/17.
 */

@Aspect
@Component
public class ExceptionHandleAspect {

    @AfterReturning(pointcut = "@annotation(com.mangokiwi.core.annotation.HandleEntityNotFound)", returning = "returningObject" )
    public void entityNotFoundAfterAdvice(JoinPoint joinPoint, Object returningObject){
        if(returningObject == null){
            Signature signature = joinPoint.getSignature();
            throw new EntityNotFoundException(signature.getDeclaringTypeName()+ " is not found");
        }
    }
}
