package dev.tdz.aspects;

import dev.tdz.exceptions.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Aspect
public class ErrorHandlerAspect {

    private static final Logger logger = Logger.getLogger(
            ErrorHandlerAspect.class.getName());
    private static final String ENVIRONMENT = Dotenv.load().get("ENVIRONMENT");

    // Handle various exceptions thrown by Controller Operations.
    // Advise on which HttpStatus to respond with.
    @AfterThrowing(
            pointcut = "controllerOperations()",
            throwing = "ex"
    )
    public void responseAdvice(Throwable ex) throws Throwable {
        Exception responseExc;

        if (ex instanceof NotFoundException) {
            responseExc = new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }
        else if (ex instanceof NotAuthenticatedException) {
            responseExc = new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
        else if (ex instanceof BadTokenException) {
            responseExc = new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
        else if (ex instanceof NotAuthorizedException) {
            responseExc = new ResponseStatusException(
                    HttpStatus.FORBIDDEN, ex.getMessage());
        }
        else if (ex instanceof BadRequestException) {
            responseExc = new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        else {
            // Treat all other errors as an Internal Server Error for now.
            responseExc = new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ENVIRONMENT.equals("production") ? "" : ex.getMessage()
            );
            logger.error("  responseAdvice: " + responseExc.getMessage());
            throw responseExc;
        }
        logger.warn("  responseAdvice: " + responseExc.getMessage());
        throw responseExc;
    }

    @Pointcut("@annotation(dev.tdz.aspects.ErrorHandler)")
    private void controllerOperations() {}

}
