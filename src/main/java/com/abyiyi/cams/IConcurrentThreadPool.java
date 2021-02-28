package com.abyiyi.cams;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by dongqingsong on 2020/3/8.
 */
public interface IConcurrentThreadPool {

    /**
     * 初始化线程池
     */
    void initConcurrentThreadPool();


    /**
     * 提交单个任务
     * @param task
     * @param <V>
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    <V> V submit(CallableTemplate<V> task) throws InterruptedException,ExecutionException;

    /**
     *
     */


    <V> List<V> invokeAll(List<? extends CallableTemplate<V>> tasks)throws InterruptedException, ExecutionException;

 }
