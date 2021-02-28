package com.abyiyi.tools.tmp.consumeProduce;

import java.util.LinkedList;

/**
 * Created by dongqingsong on 2018/3/26.
 */
public class Storage {

    // 仓库最大存储量
    private final int MAX_SIZE = 100;
    private Object lock = new Object();

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

    // 生产num个产品
    public void produce(int num) {
        // 同步代码段
        synchronized (lock) {
            // 如果仓库剩余容量不足
            while (list.size() + num > MAX_SIZE) {
                System.out.println("【要生产的产品数量】:" + num + "【库存量】:" + list.size() + "暂时不能执行生产任务!");
                try {
                    // 由于条件不满足，生产阻塞
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 生产条件满足情况下，生产num个产品
            for (int i = 1; i <= num; ++i) {
                list.add(new Object());
            }

            System.out.println("【已经生产产品数】:" + num + "【现仓储量为】:" + list.size());

            lock.notifyAll();
        }
    }

    // 消费num个产品
    public void consume(int num) {
        // 同步代码段
        synchronized (lock) {
            // 如果仓库存储量不足
            while (list.size() < num) {
                System.out.println("【要消费的产品数量】:" + num + "【库存量】:"
                        + list.size() + "暂时不能执行生产任务!");
                try {
                    // 由于条件不满足，消费阻塞
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 消费条件满足情况下，消费num个产品
            for (int i = 1; i <= num; ++i) {
                list.remove();
            }

            System.out.println("【已经消费产品数】:" + num + "【现仓储量为】:" + list.size());

            lock.notifyAll();
        }
    }

    // get/set方法
    public LinkedList<Object> getList() {
        return list;
    }

    public void setList(LinkedList<Object> list) {
        this.list = list;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }
}
