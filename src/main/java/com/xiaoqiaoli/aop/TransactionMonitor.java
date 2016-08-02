package com.xiaoqiaoli.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Aspect
@Component
public class TransactionMonitor {
    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionMonitor.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("execution(* com.xiaoqiaoli.service.impl.*ServiceImpl.*(..))")
    public Object beforTransaction(ProceedingJoinPoint proceedingJoinPoint) {
        String uuid = UUID.randomUUID().toString();
        try {
            LOGGER.info("编号:{},业务[{}]开始时间:{},参数为:{}", uuid, proceedingJoinPoint.getSignature(), new Date(), objectMapper.writeValueAsString(proceedingJoinPoint.getArgs()));
            Object retVal = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            LOGGER.info("编号:{},业务[{}]结束时间:{},参数为:{}", uuid, proceedingJoinPoint.getSignature(), new Date(), objectMapper.writeValueAsString(proceedingJoinPoint.getArgs()));
            return retVal;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

    }
}
