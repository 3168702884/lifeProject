package com.example.demo.config;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class LoginAop {

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void loginAop(){}
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Around("loginAop()")
    //登录验证
    public Object loginAOP(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String method = joinPoint.getSignature().getName();
        HttpServletResponse response = servletRequestAttributes.getResponse() ;
        Object [] args = joinPoint.getArgs();
        String token = (String) args[0];
        if (method=="wxLogin"){
            return joinPoint.proceed();
        }
        if (null == stringRedisTemplate.opsForValue().get(token)){
            response.setHeader("loginStatu","loginFail");
            return null;
        }

        return joinPoint.proceed();
    }
}
