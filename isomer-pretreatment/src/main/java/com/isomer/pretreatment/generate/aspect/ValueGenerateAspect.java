package com.isomer.pretreatment.generate.aspect;

import com.isomer.pretreatment.exception.UnSupportGenerateException;
import com.isomer.pretreatment.generate.annotation.SupportGenerate;
import com.isomer.pretreatment.generate.annotation.ValueGenerate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 19:40
 */
@Aspect
@Order(-1)
public class ValueGenerateAspect {

    @Before("execution(* com.isomer.*.controller.*.*(..))")
    public void beforeController(JoinPoint joinPoint) throws IllegalAccessException {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (!parameter.isAnnotationPresent(ValueGenerate.class)) {
                continue;
            }

            Class<?> parameterType = parameter.getType();
            if (!parameterType.isAnnotationPresent(SupportGenerate.class)) {
                throw new UnSupportGenerateException(
                        "There is no annotation @SupportGenerate present on Class [" + parameterType.getName() + "]");
            }

            Field[] fields = parameterType.getDeclaredFields();
            Field createTimeField = getCreateTimeField(fields);
            Field createByField = getCreateByField(fields);

            if (createTimeField != null) {
                createTimeField.set(joinPoint.getArgs()[i], new Timestamp(System.currentTimeMillis()));
            }

            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            if (createByField != null && ra != null) {
                ServletRequestAttributes sra = (ServletRequestAttributes) ra;
                createByField.set(joinPoint.getArgs()[i], sra.getRequest().getHeader("token"));
            }
        }
    }

    private Field getCreateTimeField(Field[] fields) {
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("createTime")) {
                return field;
            }
        }
        return null;
    }

    private Field getCreateByField(Field[] fields) {
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("createBy")) {
                return field;
            }
        }
        return null;
    }
}
