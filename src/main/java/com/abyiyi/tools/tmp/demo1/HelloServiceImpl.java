package com.abyiyi.tools.tmp.demo1;

/**
 * Created by dongqingsong on 2018/3/5.
 */
public class HelloServiceImpl implements IHello  {

    public String sayHello(String info) {
        String result = "hello : " + info;
        System.out.println(result);
        return result;
    }
}
