package com.isomer.common.async;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/3 13:53
 */
public class AsyncCenter {

    private static final Executor executor = new ThreadPoolExecutor(
            10, 20, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void commit(Runnable r) {
        executor.execute(r);
    }

    public static void commit(Runnable r, long delay, TimeUnit unit) {
        if (delay != 0) {
            try {
                unit.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executor.execute(r);
    }
}
