package com.abyiyi.tools.tmp.thread.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dongqingsong on 2018/9/4.
 */
public class ThreadPoolDemo {

    public static void main(String[] arg){

        ExecutorService threadPool = null;

//        threadPool = Executors.newFixedThreadPool(3);
//        threadPool = Executors.newCachedThreadPool();
        threadPool = Executors.newSingleThreadExecutor();
        for (int m= 0;m<10;m++){
            final int task = m;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0;i<=10;i++){
                        System.out.println(Thread.currentThread().getName()+"========="+task);
                    }

                }
            });
        }

        System.out.println("10 个任务被提交");


    }
}
