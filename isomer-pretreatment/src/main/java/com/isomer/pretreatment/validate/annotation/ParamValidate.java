package com.isomer.pretreatment.validate.annotation;

import com.isomer.pretreatment.validate.enums.Mode;

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

    Mode mode() default Mode.NAMING;

    Class<?>[] types() default {};

    String[] names() default {};
}
