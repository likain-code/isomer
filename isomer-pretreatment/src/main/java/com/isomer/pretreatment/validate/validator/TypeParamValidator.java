package com.isomer.pretreatment.validate.validator;

import com.isomer.pretreatment.validate.annotation.ParamValidate;

import java.lang.reflect.Field;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/2 12:59
 */
public class TypeParamValidator implements ParamValidator {

    @Override
    public void validate(Object arg, ParamValidate annotation) throws IllegalAccessException {
        Class<?>[] validateTypes = annotation.types();

        Field[] fields = arg.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (matches(validateTypes, field.getType())) {
                doValidate(field, field.get(arg));
            }
        }
    }
}
