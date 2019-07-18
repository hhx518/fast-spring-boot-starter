/**
 * Copyright (c) 2016-2019 沙漏开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.hlx.fast.common.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.hlx.fast.common.annotation.SysLog;
import com.hlx.fast.common.entity.SysLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {
    @Pointcut("@annotation(com.hlx.fast.common.annotation.SysLog)")
    public void logPointCut() {
    }
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("----SysLogAspect---{}", "around");
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(point, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLog = new SysLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.des());
        }
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        Map<Object, Object> params = CollUtil.newHashMap();
        for (int i = 0; i < args.length; i++) {
            String key = signature.getParameterNames()[i];
            params.put(key, args[i]);
        }

        String param = ObjectUtil.toString(signature.getParameterNames()).replace("[", "").replace("]", "");
        sysLog.setMethod(className + "." + methodName + "(" + param + ")");
        //请求的参数
        try {
            sysLog.setResult(JSONUtil.toJsonStr(joinPoint.proceed()));
            sysLog.setParams(ObjectUtil.toString(params));
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //获取request
        //设置IP地址
        sysLog.setIp("");
        //用户名
        String username = "";
        sysLog.setUsername(username);
        sysLog.setTime(time);
        sysLog.setCreateDate(DateUtil.date());
        log.info("----log---{}", JSONUtil.toJsonPrettyStr(sysLog));
        //保存系统日志
        //sysLogService.save(sysLog);
    }
}
