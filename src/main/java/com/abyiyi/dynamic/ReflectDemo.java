package com.abyiyi.dynamic;

import java.lang.reflect.Method;

/**
 * Created by dongqingsong on 2020/5/30.
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.dj.Person");
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i].getName());
        }
        Method m1 = clazz.getDeclaredMethod("setName",String.class);
        Person p = (Person) clazz.newInstance();
        m1.invoke(p,"sqs");
        System.out.println(p.toString());

    }


}
