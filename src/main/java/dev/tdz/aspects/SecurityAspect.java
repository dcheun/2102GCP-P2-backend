package dev.tdz.aspects;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.tdz.entities.AppUser;
import dev.tdz.exceptions.BadTokenException;
import dev.tdz.exceptions.InternalServerException;
import dev.tdz.exceptions.NotAuthenticatedException;
import dev.tdz.exceptions.NotFoundException;
import dev.tdz.services.AppUserService;
import dev.tdz.utils.JwtUtil;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class SecurityAspect {

    private static final Logger logger = Logger.getLogger(
            SecurityAspect.class.getName());
    private static final String ENVIRONMENT = Dotenv.load().get("ENVIRONMENT");

    @Autowired
    private AppUserService appUserService;

    @Around("authorizeUser()")
    public Object authorizationAdvice(ProceedingJoinPoint pjp) throws Throwable {

        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();

        try {
            String token = request.getHeader("Authorization").split(" ")[1];
            DecodedJWT jwt = JwtUtil.verifyToken(token);
            Integer authUserId = jwt.getClaim("id").asInt();
            Integer authRoleId = jwt.getClaim("userRoleId").asInt();
            // Set jwt attributes on request for downstream retrieval.
            request.setAttribute("authUserId", authUserId);
            request.setAttribute("authRoleId", authRoleId);
        } catch (Exception e) {
            Exception exc = new BadTokenException(
                    ENVIRONMENT.equals("production") ? "Bad token" : "Bad token " + e.getMessage());
            logger.warn("  authorizationAdvice: " + exc.getMessage());
            throw exc;
        }
        // This should be outside of the try/catch for further downstream Error Handling.
        return pjp.proceed();
    }

    @Around("authenticateUser()")
    public Object authenticationAdvice(ProceedingJoinPoint pjp)
            throws Throwable {

        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        // Get the argument passed into the advised method.
        AppUser appUserArg = (AppUser) pjp.getArgs()[0];

        try {
            AppUser appUser = appUserService.getAppUserByEmail(appUserArg.getEmail());
            String jwt = JwtUtil.generateJwtToken(appUser.getId(), appUser.getUserRoleId());
            // Set jwt in request attribute for downstream retrieval.
            request.setAttribute("encodedJWT", jwt);
        } catch (NotFoundException | NotAuthenticatedException e) {
            Exception exc = new NotAuthenticatedException(
                    ENVIRONMENT.equals("production") ? "Failed to login" : e.getMessage());
            logger.warn("  authenticationAdvice: " + exc.getMessage());
            throw exc;
        } catch (JWTCreationException e) {
            // Invalid Signing configuration / Couldn't convert Claims.
            Exception exc = new InternalServerException(
                    ENVIRONMENT.equals("production") ? "Token error" : e.getMessage());
            logger.error("  authenticationAdvice: " + exc.getMessage());
            throw exc;
        } catch (Exception e) {
            Exception exc = new InternalServerException(
                    ENVIRONMENT.equals("production") ? "" : e.getMessage());
            logger.error("  authenticationAdvice: " + exc.getMessage());
            throw exc;
        }
        // This should be outside of the try/catch for further downstream Error Handling.
        return pjp.proceed();
    }

    @Pointcut("@annotation(dev.tdz.aspects.Authorization)")
    private void authorizeUser() {
    }

    @Pointcut("@annotation(dev.tdz.aspects.Authentication)")
    private void authenticateUser() {}

}
