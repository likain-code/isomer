package com.isomer.pretreatment.encrypt.aspect;

import com.isomer.common.constant.EncryptionConstant;
import com.isomer.common.utils.AESUtil;
import com.isomer.pretreatment.encrypt.annotation.Decryption;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/7 16:43
 */
@Aspect
public class DecryptionAspect {

    @Before("execution(* com.isomer.*.controller.*.*(..))")
    public void beforeController(JoinPoint joinPoint) throws Exception {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(Decryption.class) && parameters[i].getType().equals(String.class)) {
                String arg = (String) joinPoint.getArgs()[i];
                String duplicate = arg.replace(" ", "+");
                String decrypted = AESUtil.decrypt(duplicate, EncryptionConstant.KEY);
                Field field = arg.getClass().getDeclaredField("value");
                field.setAccessible(true);
                field.set(arg, decrypted.toCharArray());
            }
        }
    }
}
