package tn.esprit.pr1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceAspect {

    private static final Logger log = LoggerFactory.getLogger(ServiceAspect.class);

    // Pointcut targeting all methods in any class inside the Services package
    @Pointcut("execution(* tn.esprit.pr1.Services.*.*(..))")
    public void serviceMethodsPointcut() {}

    // ─── @Before ─────────────────────────────────────────────────────────────
    // Logs the method name and its arguments before execution
    @Before("serviceMethodsPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("[BEFORE] Appel de la méthode : {}.{}() avec arguments : {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    // ─── @After ──────────────────────────────────────────────────────────────
    // Logs after execution finishes (whether successful or with exception)
    @After("serviceMethodsPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("[AFTER]  Fin d'exécution de : {}.{}()",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    // ─── @AfterReturning ─────────────────────────────────────────────────────
    // Logs the return value when the method completes normally
    @AfterReturning(pointcut = "serviceMethodsPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AFTER_RETURNING] {}.{}() a retourné : {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result);
    }

    // ─── @AfterThrowing ──────────────────────────────────────────────────────
    // Logs if the method throws an exception
    @AfterThrowing(pointcut = "serviceMethodsPointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("[AFTER_THROWING] {}.{}() a lancé une exception : {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                ex.getMessage());
    }

    // ─── @Around ─────────────────────────────────────────────────────────────
    // Measures and logs the execution time of every service method
    @Around("serviceMethodsPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("[AROUND - BEFORE] Début de : {}.{}()",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        long elapsed = System.currentTimeMillis() - start;
        log.info("[AROUND - AFTER]  Fin de : {}.{}() — durée : {} ms",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                elapsed);
        return result;
    }
}
