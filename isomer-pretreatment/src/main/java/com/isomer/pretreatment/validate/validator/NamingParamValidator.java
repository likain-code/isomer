package com.isomer.pretreatment.validate.validator;

import com.isomer.pretreatment.validate.annotation.ParamValidate;

import java.lang.reflect.Field;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/2 12:58
 */
public class NamingParamValidator implements ParamValidator {

    @Override
    public void validate(Object arg, ParamValidate annotation) throws IllegalAccessException {
        String[] validateNames = annotation.names();

        Field[] fields = arg.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (matches(validateNames, field.getName())) {
                doValidate(field, field.get(arg));
            }
        }
    }
}
