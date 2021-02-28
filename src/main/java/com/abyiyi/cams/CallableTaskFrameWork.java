package com.abyiyi.cams;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class CallableTaskFrameWork implements ICallableTaskFrameWork {

    private IConcurrentThreadPool concurrentThreadPool = new ConcurrentThreadPool();

    @Override
    public <V> List<V> submitsAll(List<? extends CallableTemplate<V>> tasks)
            throws InterruptedException, ExecutionException {

        concurrentThreadPool.initConcurrentThreadPool();

        return concurrentThreadPool.invokeAll(tasks);
    }

}
