package com.hxy.code.net;

import com.hxy.code.R;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class ThreadPoolUtils {

    public ThreadPoolUtils() {
    }
    private static int CORE_POOL_SIZE=3;
    private static int MAX_POOL_SIZE=200;
    private static int KEEP_ALIVE_TIME=5000;
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(
            10);
    private static ThreadFactory threadFactory=new ThreadFactory() {
        private final AtomicInteger integer=new AtomicInteger();
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable,"MyThreadPool thread:"+integer.getAndIncrement());
        }
    };
    private static RejectedExecutionHandler handler=new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

        }
    };
    private static ThreadPoolExecutor threadpool;
    static {
        threadpool=new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,workQueue,threadFactory,handler);
    }
    public static void execute(Runnable runnable){
        threadpool.execute(runnable);
    }
}
