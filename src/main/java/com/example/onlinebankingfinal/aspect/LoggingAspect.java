package com.example.onlinebankingfinal.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * The LoggingAspect class is responsible for logging requests and method execution in the controller and service layers.
 * It uses the AspectJ annotation @Aspect to define the aspect and @Component to make it a Spring bean.
 * This class uses the Lombok annotation @Slf4j to enable logging using the Self Logging Facade for Java (SLF4J) framework.
 *
 * <p>The LoggingAspect class contains the following methods:</p>
 *
 * <ul>
 *   <li>controllerLog() - Defines the pointcut expression for logging controller methods.</li>
 *   <li>serviceLog() - Defines the pointcut expression for logging service methods.</li>
 *   <li>doBeforeController() - Logs information before executing a controller method.</li>
 *   <li>doBeforeService() - Logs information before executing a service method.</li>
 *   <li>doAfterReturning() - Logs the return value after executing a controller method.</li>
 *   <li>throwsException() - Logs an exception when a controller method throws an exception.</li>
 * </ul>
 *
 * <p>This class makes use of the following dependencies:</p>
 *
 * <ul>
 *   <li>ServletRequestAttributes - Used to get the servlet request attributes.</li>
 *   <li>HttpServletRequest - Represents an HTTP request.</li>
 *   <li>RequestContextHolder - Provides access to the current request.</li>
 *   <li>JoinPoint - Represents a point during the execution of a program.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 *
 * <pre><code>
 *     // Create an instance of the LoggingAspect class
 *     LoggingAspect loggingAspect = new LoggingAspect();
 *
 *     // Execute controllerLog() method
 *     loggingAspect.controllerLog();
 *
 *     // Execute serviceLog() method
 *     loggingAspect.serviceLog();
 *
 *     // Execute doBeforeController() method
 *     loggingAspect.doBeforeController(jp);
 *
 *     // Execute doBeforeService() method
 *     loggingAspect.doBeforeService(jp);
 *
 *     // Execute doAfterReturning() method
 *     loggingAspect.doAfterReturning(returnObject);
 *
 *     // Execute throwsException() method
 *     loggingAspect.throwsException(jp, ex);
 * </code></pre>
 */
@Aspect
@Component
@Slf4j //SELF LOGGING FACADE FOR JAVA
public class LoggingAspect {

    @Pointcut("execution(public * com.example.onlinebankingfinal.controller.*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution(public * com.example.onlinebankingfinal.service.*.*(..))")
    public void serviceLog() {
    }

    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("""
                        NEW REQUEST:
                        IP : {}
                        URL : {}
                        HTTP_METHOD : {}
                        CONTROLLER_METHOD : {}.{}""",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("""

                        Return value: {}
                        END OF REQUEST""",
                returnObject);
    }

    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(jp.getArgs()), ex.getMessage());
    }
}
