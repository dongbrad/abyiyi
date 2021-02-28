package com.abyiyi.tools.tmp.demo.demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dongqingsong on 2018/3/5.
 */
public class BookFacadeProxy implements InvocationHandler {
    private Object target;
    /**
     * 绑定委托对象并返回一个代理类
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), this);
    }

    @Override
    /**
     * 调用方法
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("事物开始");
        Object result=method.invoke(target, args);
        System.out.println("事物结束");
        return result;
    }

    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookProxy.addBook();
    }
}
