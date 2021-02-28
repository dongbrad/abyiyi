package com.abyiyi.cams;

import java.util.concurrent.Callable;

/**
 * Created by dongqingsong on 2020/3/8.
 */
public abstract class CallableTemplate<V> implements Callable<V> {


    public void beforeProecess(){
        System.out.println("-----");
    }


    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public V call() throws Exception {
        beforeProecess();
        V result = process();
        afterProcess();
        return result;
    }

    /**
     *
     */

    public abstract V process();



    public void afterProcess(){

    }



}
