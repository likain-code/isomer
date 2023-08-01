package com.isomer.pretreatment.generate.annotation;

import java.lang.annotation.*;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 19:39
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueGenerate {
}
