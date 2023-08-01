package com.isomer.user.aspect;

import com.isomer.common.utils.StringUtil;
import com.isomer.redis.core.RedisService;
import com.isomer.user.exception.IllegalOriginException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 14:10
 */
@Aspect
@Component
public class OriginCheckAspect {

    @Autowired
    private RedisService<String, String> redisService;

    @Pointcut("execution(* com.isomer..controller.*Controller.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeInvoke() {
        ServletRequestAttributes attributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String code = request.getHeader("Code");

        if (StringUtil.isNullOrEmpty(code) || !redisService.hasKey(code)) {
            throw new IllegalOriginException(
                    "Request for [" + request.getRequestURI() + "] has been blocked since it is not from global gateway");
        }

        redisService.deleteKey(code);
    }
}
