package com.isomer.common.logger.impl;

import com.isomer.common.logger.ILogger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 15:00
 */
public class WarnLogger extends ILogger {

    private static final ILogger LOGGER = new WarnLogger();

    private WarnLogger() {
    }

    public static ILogger getInstance() {
        return LOGGER;
    }

    @Override
    public void print(Class<?> clazz, String message) {
        globalLoggers.computeIfAbsent(clazz, LoggerFactory::getLogger).warn(message);
    }
}
