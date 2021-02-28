package com.abyiyi.tools.tmp.demo.my;


import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dongqingsong on 2018/3/20.
 */
public class ProxyMyTest implements InvocationHandler {

    private Object target;


    public Object creatProxyObject(Object tar){
        this.target = tar;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        System.out.println("事物开始");
        Object result = method.invoke(target, args);
        System.out.println("事物结束");
        return result;
    }

    public static void main(String[] args) {
        ProxyMyTest px= new ProxyMyTest();
        IMyTest a = (IMyTest)px.creatProxyObject(new MyTestImpl());
        a.sayHello("s","a");
    }
}
