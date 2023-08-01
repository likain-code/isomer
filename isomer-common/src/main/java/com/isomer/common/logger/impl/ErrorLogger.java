package com.isomer.common.logger.impl;

import com.isomer.common.logger.ILogger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 15:36
 */
public class ErrorLogger extends ILogger {

    private static final ILogger LOGGER = new ErrorLogger();

    private ErrorLogger() {
    }

    public static ILogger getInstance() {
        return LOGGER;
    }

    @Override
    public void print(Class<?> clazz, String message) {
        globalLoggers.computeIfAbsent(clazz, LoggerFactory::getLogger).error(message);
    }

    public void print(Class<?> clazz, String message, Throwable throwable) {
        globalLoggers.computeIfAbsent(clazz, LoggerFactory::getLogger).error(message, throwable);
    }
}
