package com.abyiyi.tools.tmp.demo.mydemo;




import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dongqingsong on 2018/3/5.
 */
public class DynamicDemo implements InvocationHandler{
    private Object target;

    public Object  createProxyClass(Object obj){
        this.target = obj;
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----------Start");
        Object result=method.invoke(target, args);
        System.out.println("------------END");
        return result;
    }

    public static void main(String[] args) {
        DynamicDemo proxy = new DynamicDemo();
        MyProxy bookProxy = (MyProxy) proxy.createProxyClass(new MyProxyImpl());
        bookProxy.sayHello("Test Dynamic Success");
    }






}
