package com.ecomm.backend.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {
    private static  final Logger LOGGER= LoggerFactory.getLogger(PerformanceAspect.class);

    @Around("execution (* com.ecomm.backend.Services.ProductServices.getAllProducts(..))")
    public Object getPerformance(ProceedingJoinPoint pj) throws Throwable {
        long start=System.currentTimeMillis();
        Object obj=pj.proceed();
        long end=System.currentTimeMillis();
        LOGGER.info("Time {}ms", end - start);
        return obj;
    }

}
