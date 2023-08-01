package com.isomer.pretreatment.generate.annotation;

import java.lang.annotation.*;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 19:44
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportGenerate {
}
