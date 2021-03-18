package com.player.playermusic.aspect;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.UserEntity;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.JwtToken;
import com.player.playermusic.Entity.LogEntity;
import com.player.playermusic.dao.LogDao;
import com.player.playermusic.utils.CookieUtils;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogDao logDao;

    @Value("${app.appId}")
    private String appId;

    @Value("${app.appName}")
    private String appName;

    @Autowired
    private JwtToken jwtToken;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.player.common.myInterface.OperLog)")
    public void logPoinCut() {
    }

    //@Around：环绕通知
    @Around("logPoinCut()")
    public Object saveSysLog(ProceedingJoinPoint proceedingJoinPoint) {
        //开始调用时间
        Long start = System.currentTimeMillis();
        //保存日志
        LogEntity sysLog = new LogEntity();
        sysLog.setAppId(appId);
        sysLog.setAppName(appName);

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        OperLog myLog = method.getAnnotation(OperLog.class);

        if (myLog != null) {
            sysLog.setDescription(myLog.message());//保存获取的操作
            sysLog.setOparation(myLog.operation());//
        }
        //获取请求的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = proceedingJoinPoint.getArgs();
        //将参数所在的数组转换成json
        Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.stream(args);
        List<Object> logArgs = stream
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        String params = JSON.toJSONString(logArgs);
        if (params == "[]" || params == "{}") params = null;
        sysLog.setParams(params);
        sysLog.setStartTime(new Date());
        //获取用户名
        //获取用户ip地址
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        sysLog.setQueryString(request.getQueryString());

        String token = request.getHeader("Authorization");
        if(token!= null && !"".equals(token)){
            UserEntity userEntity =jwtToken.parserToken(token, UserEntity.class);
            if(userEntity != null && !"".equals(userEntity)){
                sysLog.setUserId(userEntity.getUserId());
                CookieUtils.setTokenCookie(attributes.getResponse(),token);
            }
        }
        // 记录下请求内容
        sysLog.setIp(request.getRemoteAddr());
        sysLog.setUrl(request.getRequestURL().toString());
        sysLog.setType(request.getMethod());

        // 计时并调用目标函数
        Long time = System.currentTimeMillis() - start;
        sysLog.setRunTime(time);

        try {
            Object result = proceedingJoinPoint.proceed();
            sysLog.setEndTime(new Date());
            sysLog.setResult(JSON.toJSONString(result));
            logDao.save(sysLog);
            return result;
        } catch (Throwable throwable) {
            sysLog.setResult(throwable.getMessage());
            logDao.save(sysLog);
            throwable.printStackTrace();
        }
        return null;
    }

    public static <T> Stream<T> streamOf(T[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : Arrays.asList(array).stream();
    }
}
