package com.isomer.common.logger;

import com.isomer.common.logger.impl.ErrorLogger;
import com.isomer.common.logger.impl.InfoLogger;
import com.isomer.common.logger.impl.WarnLogger;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 13:40
 */
public class LoggerUtil {

    public static ILogger INFO = InfoLogger.getInstance();
    public static ILogger WARN = WarnLogger.getInstance();
    public static ILogger ERROR = ErrorLogger.getInstance();
}
