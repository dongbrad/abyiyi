package com.abyiyi.tools.tmp.demo.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dongqingsong on 2018/3/5.
 */
public class DynamicProxy implements InvocationHandler {

    //代理的是这个类的接口
    Object originObj;

    public DynamicProxy(Object originObj) {
        this.originObj = originObj;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(originObj.getClass().getClassLoader(), originObj.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("这句是代理打印的!");
        return method.invoke(originObj, args);
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //先生成一个原始实现了SayHello接口的实例,因为需要这个实例去生成代理实例
        HelloImpl originHello = new HelloImpl();
        //得到可以动态生成代理实例的对象
        DynamicProxy proxy = new DynamicProxy(originHello);
        //得到代理SayHello接口的实例对象
        SayHello proxyHello = (SayHello) proxy.getProxy();
        proxyHello.sayHello();
    }

}


