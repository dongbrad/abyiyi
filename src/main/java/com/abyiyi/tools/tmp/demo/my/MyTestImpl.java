package com.abyiyi.tools.tmp.demo.my;

/**
 * Created by dongqingsong on 2018/3/20.
 */
public class MyTestImpl implements  IMyTest{
    @Override
    public String sayHello(String str,String s) {
        System.out.println("Hello world" + str +"======="+s);
        return "Hello world" + str;
    }
}
