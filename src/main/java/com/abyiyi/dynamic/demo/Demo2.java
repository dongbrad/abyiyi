package com.abyiyi.dynamic.demo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Created by dongqingsong on 2020/2/12.
 */
public class Demo2 {


    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.dj.demo.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();
    }
}
