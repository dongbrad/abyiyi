package com.abyiyi.cams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ConcurrentThreadPool implements IConcurrentThreadPool {
    private ThreadPoolExecutor threadPoolExecutor;
    // 核心线程数
    private int corePoolSize = 10;
    // 最大线程数
    private int maximumPoolSize = 20;
    // 超时时间30秒
    private long keepAliveTime = 30;

    @Override
    public void initConcurrentThreadPool() {
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
    }

    /**
     * 提交单个任务
     *
     * @param task
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public <V> V submit(CallableTemplate<V> task) throws InterruptedException, ExecutionException {
        Future<V> result = threadPoolExecutor.submit(task);

        return result.get();
    }


    @Override
    public <V> List<V> invokeAll(List<? extends CallableTemplate<V>> tasks)
            throws InterruptedException, ExecutionException {

        List<Future<V>> tasksResult = threadPoolExecutor.invokeAll(tasks);
        List<V> resultList = new ArrayList<V>();

        for (Future<V> future : tasksResult) {
            resultList.add(future.get());
        }

        return resultList;
    }

}
