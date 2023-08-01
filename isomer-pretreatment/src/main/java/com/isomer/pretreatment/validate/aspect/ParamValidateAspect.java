package com.isomer.pretreatment.validate.aspect;

import com.isomer.pretreatment.validate.annotation.ParamValidate;
import com.isomer.pretreatment.validate.annotation.SupportValidate;
import com.isomer.pretreatment.validate.exception.IllegalParamException;
import com.isomer.pretreatment.validate.exception.UnSupportValidateException;
import com.isomer.common.utils.StringUtil;
import com.sun.istack.internal.NotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 16:40
 */
@Aspect
@Order(-2)
public class ParamValidateAspect {

    @Before("execution(* com.isomer.*.controller.*.*(..))")
    public void beforeController(JoinPoint joinPoint) throws IllegalAccessException {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (!parameter.isAnnotationPresent(ParamValidate.class)) {
                continue;
            }

            Class<?> parameterType = parameter.getType();
            if (!parameterType.isAnnotationPresent(SupportValidate.class)) {
                throw new UnSupportValidateException(
                        "There is no annotation @SupportValidate present on Class [" + parameterType.getName() + "]");
            }

            ParamValidate annotation = parameter.getAnnotation(ParamValidate.class);
            Class<?>[] types = annotation.types();

            Field[] fields = parameterType.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Class<?> type = field.getType();

                if (match(types, type)) {
                    Object o = field.get(joinPoint.getArgs()[i]);
                    if (type.equals(String.class)) {
                        if (!field.getName().equals("createBy")) {
                            String s = (String) o;
                            if (StringUtil.isNullOrEmpty(s)) {
                                throw new IllegalParamException(
                                        "Field [" + field.getName() + "] cannot be null or empty");
                            }
                        }
                    } else {
                        if (o == null) {
                            throw new IllegalParamException(
                                    "Field [" + field.getName() + "] cannot be null");
                        }
                    }
                }
            }
        }
    }

    private boolean match(@NotNull Class<?>[] classes, @NotNull Class<?> clazz) {
        for (Class<?> c : classes) {
            if (c == clazz) {
                return true;
            }
        }
        return false;
    }
}
