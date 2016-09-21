package com.example.castle.csite.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by castle on 16-8-21.
 * 线程池代理
 */
public class ThreadPoolProxy {

    //线程池的相关类
    ThreadPoolExecutor mExecutor;
    private int mCorePoolSize;//核心线程大小
    private int mMaximumPoolSize;//最大线程数
    private long mKeepAliveTime;//  线程保存时间

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        mCorePoolSize = corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
        mKeepAliveTime = keepAliveTime;
        initThreadPoolExexutor();
    }

    /**
     * 提交任务
     *
     * @param task 要提交的任务
     */
    public void submit(Runnable task) {
        mExecutor.submit(task);
    }

    /**
     * 执行任务
     *
     * @param task 要执行的任务
     */
    public void execute(Runnable task) {
        mExecutor.execute(task);
    }

    /**
     * 移除任务
     *
     * @param task 要移除的任务
     */
    public void remove(Runnable task) {
        mExecutor.remove(task);
    }

    private void initThreadPoolExexutor() {
        //线程不存在或者已经关闭才能进行创建
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
            //保证线程安全
            synchronized (ThreadPoolProxy.class) {
                int corePoolSize = mCorePoolSize;
                int maximumPoolSize = mMaximumPoolSize;
                long keepAliveTime = mKeepAliveTime;

                TimeUnit unit = TimeUnit.MICROSECONDS;
                LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>();
                ThreadFactory threadFactory = Executors.defaultThreadFactory();
                RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

                mExecutor = new ThreadPoolExecutor(
                        corePoolSize,//核心线程数
                        maximumPoolSize,//最大线程数
                        keepAliveTime,//保持时间，只有线程数超出核心数的时候才会起作用
                        unit,//保持时间单位
                        workQueue,//任务队列
                        threadFactory,//线程工厂
                        handler//异常捕获器
                );
            }
        }
    }

}
