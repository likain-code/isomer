package com.isomer.pretreatment.validate.validator;

import com.isomer.common.utils.StringUtil;
import com.isomer.pretreatment.validate.annotation.ParamValidate;
import com.isomer.pretreatment.validate.exception.IllegalParamException;
import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/2 12:57
 */
public interface ParamValidator {

    void validate(Object arg, ParamValidate annotation) throws IllegalAccessException;

    default <T> boolean matches(T[] tArray, @NotNull T t) {
        for (T var : tArray) {
            if (var != null && var.equals(t)) {
                return true;
            }
        }
        return false;
    }

    default void doValidate(Field field, Object fieldValue) {
        if (field.getType().equals(String.class)) {
            String s = (String) fieldValue;
            if (StringUtil.isNullOrEmpty(s)) {
                throw new IllegalParamException("Field [" + field.getName() + "] cannot be null or empty");
            }
        } else {
            if (fieldValue == null) {
                throw new IllegalParamException("Field [" + field.getName() + "] cannot be null");
            }
        }
    }
}
