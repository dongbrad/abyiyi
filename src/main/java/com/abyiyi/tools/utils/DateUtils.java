package com.abyiyi.tools.utils;

import java.text.SimpleDateFormat;

/**
 * Created by dongqingsong on 2020/2/29.
 *
 * 线程安全
 *
 */
public class DateUtils {

    private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();
    public static SimpleDateFormat getSimpleDateFormat(){
        SimpleDateFormat sdf = t1.get();
        if(sdf == null){
            return new SimpleDateFormat();
        }else{
            return sdf;
        }

    }

}
