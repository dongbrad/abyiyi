package com.abyiyi.tools.tmp.demo.wangbaoqiang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 演员的经纪人，他不会唱歌和跳舞，但是经纪人能找到会唱歌跳舞的人
 * @
 */
public class ActorJingJiRen {
    // 设计一个类变量记住代理类要代理的目标对象
    private Actor actor = new WangBaoQiang();

    // 通过经纪人，返回王宝强的对象
    public Actor getProxy() {
        // 使用Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces,
        // InvocationHandler h)返回某个对象的代理对象
        return (Actor) Proxy.newProxyInstance(ActorJingJiRen.class.getClassLoader(), actor.getClass().getInterfaces(), new InvocationHandler() {
                    /**
                     * InvocationHandler接口只定义了一个invoke方法，因此对于这样的接口，
                     * 我们不用单独去定义一个类来实现该接口， 而是直接使用一个匿名内部类来实现该接口，new
                     * InvocationHandler() {}就是针对InvocationHandler接口的匿名实现类
                     *
                     * 在invoke方法编码指定返回的代理对象干的工作 proxy : 把代理对象自己传递进来
                     * method：把代理对象当前调用的方法传递进来 args:把方法参数传递进来
                     * 因此我们可以在invoke方法中使用method.getName()就可以知道当前调用的是代理对象的哪个方法
                     */
                    @Override
                    public Object invoke(Object proxy, Method method,
                                         Object[] args) throws Throwable {
                        if (method.getName().equals("sing")) {
                            System.out.println("我是他的经纪人，有事情先找我！");
                            //代理对象调用真实目标对象的sing方法去处理用户请求
                            return method.invoke(actor, args);
                        }
                        if (method.getName().equals("dance")) {
                            //代理对象调用真实目标对象的dance方法去处理用户请求
                            System.out.println("我是他的经纪人，有事情先找我！");
                            return method.invoke(actor, args);
                        }
                        return null;
                    }
                });
    }

}
