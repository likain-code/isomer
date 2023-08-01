package com.isomer.common.logger;

import com.isomer.common.logger.impl.ErrorLogger;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 14:51
 */
public abstract class ILogger {

    protected final Map<Class<?>, Logger> globalLoggers = new HashMap<>();

    public abstract void print(Class<?> clazz, String message);

    public void print(Class<?> clazz, String message, Throwable throwable) {
        if (this.getClass().equals(ErrorLogger.class)) {
            this.print(clazz, message, throwable);
        } else {
            this.print(clazz, message);
        }
    }
}
