package com.abyiyi.tools.tmp.demo.mydemo;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * Created by dongqingsong on 2018/3/5.
 */
public class MyProxyImpl implements MyProxy {
    @Override
    public void sayHello(String str) {
        System.out.println("my proxy is success;" + str);
    }
}
