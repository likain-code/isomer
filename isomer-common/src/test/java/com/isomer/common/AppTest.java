package com.isomer.common;

import com.isomer.common.logger.LoggerUtil;
import org.junit.Test;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 13:48
 */
public class AppTest {

    @Test
    public void testLogger() {
        LoggerUtil.INFO.print(this.getClass(), "info...", new Throwable("Exception"));
        LoggerUtil.WARN.print(this.getClass(), "warn...");
        LoggerUtil.ERROR.print(this.getClass(), "error", new Throwable("Exception"));
    }
}
