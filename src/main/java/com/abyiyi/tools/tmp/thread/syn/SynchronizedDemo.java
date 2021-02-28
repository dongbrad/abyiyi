package com.abyiyi.tools.tmp.thread.syn;

/**
 * Created by dongqingsong on 2018/8/27.
 */


public class SynchronizedDemo implements Runnable {
    private static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SynchronizedDemo());
            thread.start();
        }
        System.out.println("result: " + count);
    }

    @Override
    public void run() {
        synchronized (SynchronizedDemo.class) {
            for (int i = 0; i < 1000000; i++)
                count++;
        }
    }

}
