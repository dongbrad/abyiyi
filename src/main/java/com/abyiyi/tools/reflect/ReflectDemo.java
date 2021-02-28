package com.abyiyi.tools.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by dongqingsong on 2018/11/7.
 */
public class ReflectDemo {

    public static void main(String[] args)throws Exception {
        Class clazz = Class.forName("com.abyiyi.reflect.Person");
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor c1 = clazz.getConstructor(String.class);
        Constructor c2 = clazz.getConstructor(String.class,String.class);
        Constructor c3 = clazz.getConstructor(String.class,String.class,int.class);

        /**
         * 可变参数和数组对象一般是通用的
         */
        Class[] pType = new Class[]{String.class,String.class};
        Object[] obj1 = new Object[]{ "ddd" };
        Object[] obj2 = new Object[]{ "ddd", "anybody" };
        Object[] obj3 = new Object[]{ "ddd", "anybody" ,99};
        Object obj = c2.newInstance("ww","wwww");
        Object obj222 = c2.newInstance(obj2);
        Person p = (Person)obj;
        System.out.println(p.getName()+"--"+p.getSex());

        for (int i = 0; i < constructors.length; i++) {
            Constructor c = constructors[i];
            System.out.println("===");
            Class[] pas = c.getParameterTypes();
            for (int j = 0; j < pas.length; j++) {
                System.out.println(pas[j].getName());
            }
        }
        Method[] method = clazz.getDeclaredMethods();
    }
}
