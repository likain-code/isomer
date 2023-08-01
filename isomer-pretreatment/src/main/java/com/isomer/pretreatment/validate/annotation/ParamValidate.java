package com.isomer.pretreatment.validate.annotation;

import java.lang.annotation.*;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 16:37
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamValidate {

    Class<?>[] types();
}
