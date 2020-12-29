package pers.qianyucc.qblog.aspect;

import cn.hutool.json.*;
import lombok.extern.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.context.request.*;
import pers.qianyucc.qblog.service.retrofit.*;
import pers.qianyucc.qblog.service.retrofit.model.*;
import retrofit2.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 * @author mizzle_rain
 * @date 2020年10月31日22:19:36
 */
@Slf4j
//@Aspect
//@Component
public class ControllerLogAspect {
    @Autowired
    private LogService logService;
    @Value("${spring.application.name}")
    private String applicationName;

    @Pointcut("execution(public * pers.qianyucc.qblog.controller.*.*(..))")
    public void controllerLog() {
    }

    @Around("controllerLog()")
    public Object doAfterReturning(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        Object result = proceedingJoinPoint.proceed();
        RequestLogDTO requestLogDTO = new RequestLogDTO();
        requestLogDTO.setIp(Objects.nonNull(req.getHeader("X-Real-IP")) ? req.getHeader("X-Real-IP") : req.getRemoteAddr());
        requestLogDTO.setUrl(req.getRequestURL().toString());
        requestLogDTO.setMethod(req.getMethod());
        requestLogDTO.setClassMethod(proceedingJoinPoint.getSignature().toString());
        requestLogDTO.setParameters(JSONUtil.toJsonStr(proceedingJoinPoint.getArgs()));
        requestLogDTO.setResult(JSONUtil.toJsonStr(result));
        requestLogDTO.setCreatedBy(applicationName);
        requestLogDTO.setUserAgent(req.getHeader("user-agent"));
        requestLogDTO.setTimeCost(System.currentTimeMillis() - start);
        logService.writeRequestLog(requestLogDTO).enqueue(new Callback<HttpResult>() {
            @Override
            public void onResponse(Call<HttpResult> call, Response<HttpResult> res) {
                if (res.isSuccessful()) {
                    log.info("日志写入成功：{}", JSONUtil.toJsonStr(res.body()));
                } else {
                    try {
                        log.info("日志写入失败：error info：{} ：{}", res.errorBody().string(), JSONUtil.toJsonStr(requestLogDTO));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpResult> call, Throwable throwable) {
                log.info("日志写入失败：error{} ：{}", throwable.toString(), JSONUtil.toJsonStr(requestLogDTO));
            }
        });
        return result;
    }

    /**
     * 异常处理
     */
    @AfterThrowing(pointcut = "controllerLog()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, RuntimeException e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        RequestLogDTO requestLogDTO = new RequestLogDTO();
        requestLogDTO.setIp(Objects.nonNull(req.getHeader("X-Real-IP")) ? req.getHeader("X-Real-IP") : req.getRemoteAddr());
        requestLogDTO.setUrl(req.getRequestURL().toString());
        requestLogDTO.setMethod(req.getMethod());
        requestLogDTO.setClassMethod(joinPoint.getSignature().toString());
        requestLogDTO.setParameters(JSONUtil.toJsonStr(joinPoint.getArgs()));
        requestLogDTO.setException(e.toString());
        requestLogDTO.setCreatedBy(applicationName);
        requestLogDTO.setUserAgent(req.getHeader("user-agent"));
        log.info("error Request Info      : {}", JSONUtil.toJsonStr(requestLogDTO));
    }
}
