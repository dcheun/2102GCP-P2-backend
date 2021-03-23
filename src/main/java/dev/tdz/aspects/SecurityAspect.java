package dev.tdz.aspects;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.tdz.entities.AppUser;
import dev.tdz.repos.AppUserRepo;
import dev.tdz.services.AppUserService;
import dev.tdz.utils.JwtUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Aspect
public class SecurityAspect {

    @Autowired
    AppUserService appUserService;

    @Around("authorizeUser()")
    public Object authenticate(ProceedingJoinPoint pjp) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        String token = request.getHeader("Authorization").split(" ")[1];
        //token = ctx.header("Authorization").split(" ")[1];
        DecodedJWT jwt = null;
        try{
            jwt = JwtUtil.verifyToken(token);
        }
        catch(JWTDecodeException je){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, je.getMessage());
        }
        Object obj = pjp.proceed();
        return obj;
    }

    @Pointcut("@annotation(dev.tdz.aspects.Authorized)")
    private void authorizeUser(){};



}
