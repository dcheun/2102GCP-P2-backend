package dev.tdz.aspects;

import dev.tdz.controllers.AppUserController;
import dev.tdz.services.AppUserService;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
@Aspect
public class LoggingAspect {



    Logger logger = Logger.getLogger(LoggingAspect.class.getName());


    @Around("logging()")
    public Object  loggingAdvice(ProceedingJoinPoint pjp) throws Throwable{
        StringBuilder sb = new StringBuilder();
        Object obj = null;

        for(Object o1 : pjp.getArgs()){
            sb.append(o1);
            sb.append(", ");
        }
        logger.info(pjp.getSignature().getName() + "("+sb.toString() + ")" + " was executed ");
       try{
            obj = pjp.proceed();
       }catch(Exception e){
           logger.error(pjp.getSignature().getName() + " " + e.getMessage());
           throw e;
       }
        return  obj;
    }


    @Pointcut("@annotation(dev.tdz.aspects.Logging)")
    private void logging(){}





}
