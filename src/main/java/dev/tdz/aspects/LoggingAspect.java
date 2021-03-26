package dev.tdz.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(
            LoggingAspect.class.getName());

    @Around("logging()")
    public Object loggingAdvice(ProceedingJoinPoint pjp) throws Throwable {
        StringJoiner sj = new StringJoiner(", ");

        for (Object o : pjp.getArgs()) {
            sj.add(String.valueOf(o));
        }
        logger.info("====== " + pjp.getSignature().toLongString() + " ======");
        logger.info("  " + pjp.getSignature().toShortString().split("[(]")[0] +
                "(" + sj.toString() + ")" + " was called");

        return pjp.proceed();
    }

    @Around("serviceLogging()")
    public Object serviceLoggingAdvice(ProceedingJoinPoint pjp) throws Throwable {
        StringJoiner sj = new StringJoiner(", ");

        for (Object o : pjp.getArgs()) {
            sj.add(String.valueOf(o));
        }
        logger.info("  " + pjp.getSignature().toShortString().split("[(]")[0] +
                "(" + sj.toString() + ")" + " was called");

        return pjp.proceed();
    }

    @Pointcut("@annotation(dev.tdz.aspects.Logging)")
    private void logging() {
    }

    @Pointcut("@annotation(dev.tdz.aspects.ServiceLogging)")
    private void serviceLogging() {
    }

}
