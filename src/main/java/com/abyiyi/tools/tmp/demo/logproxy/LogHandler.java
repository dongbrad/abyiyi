package com.abyiyi.tools.tmp.demo.logproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dongqingsong on 2018/3/20.
 */
public class LogHandler implements InvocationHandler {

    //将要代理的对象保存为成员变量
    private Object targetObject;

    //将被代理的对象传进来，通过这个方法生成代理对象
    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        /***
         * 1.targetObject.getClass().getClassLoader()目标对象通过getClass方法获取代理类的所有信息后，调用getClassLoader()方法来获取类加载器。
         * 获取类加载器后，可以通过这个类型的加载器，在程序运行时，将生成的代理类加载到JVM即Java虚拟机中，以便运行时需要！
         *2.targetObject.getClass().getInterfaces()获取被代理类的所有接口信息，以便于生成的代理类可以具有代理类接口中的所有方法。
         * 3、this：我们使用动态代理是为了更好的扩展，比如在方法之前做什么，之后做什么等操作。这个时候这些公共的操作可以统一交给代理类去做。
         * 这个时候需要调用实现了InvocationHandler 类的一个回调方法。由于自身变实现了这个方法，所以将this传递过去。
         *
         */
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }


    /**
     * 1、Object proxy生成的代理对象，在这里不是特别的理解这个对象，但是个人认为是已经在内存中生成的proxy对象。
     * 2、Method method：被代理的对象中被代理的方法的一个抽象。
     * 3、Object[] args：被代理方法中的参数。这里因为参数个数不定，所以用一个对象数组来表示。
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    //代理模式内部要毁掉的方法
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("start-->>" + method.getName());//方法执行前的操作
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        Object ret = null;
        try {
            //调用目标方法，如果目标方法有返回值，返回ret,如果没有抛出异常
            ret = method.invoke(targetObject, args);
            System.out.println("success-->>" + method.getName()); //方法执行后操作
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error-->>" + method.getName());//出现异常时的操作
            throw e;
        }
        return ret;
    }

    //客户端调用
    public static void main(String[] args) {


    }

}



