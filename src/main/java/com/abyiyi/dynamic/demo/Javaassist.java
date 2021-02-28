package com.abyiyi.dynamic.demo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * Created by dongqingsong on 2020/2/11.
 */
public class Javaassist {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        CtClass ctClass = pool.makeClass("com.dj.demo.Stu");

        CtClass cc = pool.get("com.dj.demo.Stu");
        cc.setSuperclass(pool.get("com.dj.demo.Person"));


        byte[] b = cc.toBytecode();
        System.out.println(new String(b));
        /**
         * 如果CtClass 对象有writeFile（），toClass（），toBytecode（）转化为类文件，Javaassist将冻结CtClass对象，进一步修复不允许
         * 解冻
         */

        cc.writeFile();
        cc.defrost();
        cc.setSuperclass(null);
    }
}
