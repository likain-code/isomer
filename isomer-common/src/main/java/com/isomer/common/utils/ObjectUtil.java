package com.isomer.common.utils;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/3 18:30
 */
public class ObjectUtil {

    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }
}
