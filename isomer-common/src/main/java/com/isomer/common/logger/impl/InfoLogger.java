package com.isomer.common.logger.impl;

import com.isomer.common.logger.ILogger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 14:09
 */
public class InfoLogger extends ILogger {

    private static final ILogger LOGGER = new InfoLogger();

    private InfoLogger() {
    }

    public static ILogger getInstance() {
        return LOGGER;
    }

    @Override
    public void print(Class<?> clazz, String message) {
        globalLoggers.computeIfAbsent(clazz, LoggerFactory::getLogger).info(message);
    }
}
