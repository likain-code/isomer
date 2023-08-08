package com.isomer.pretreatment.encrypt.annotation;

import java.lang.annotation.*;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/7 14:06
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Decryption {
}
