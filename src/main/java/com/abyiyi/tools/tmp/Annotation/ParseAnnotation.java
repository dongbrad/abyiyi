package com.abyiyi.tools.tmp.Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by dongqingsong on 2018/3/6.
 */
public class ParseAnnotation {
    /**
     * 打印出UseAnnotation类的所有类注解
     * @throws ClassNotFoundException
     */
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class clz = Class.forName("com.example.annotation.UseAnnotation");
        Annotation[] annotations = clz.getAnnotations();
        for (Annotation annotation:annotations) {
            FirstAnnotation firstAnnotation = (FirstAnnotation)annotation;
            System.out.println("name=" + firstAnnotation.name()
                    + ", age:" + firstAnnotation.age());
        }
    }

    /**
     * 打印出UseAnnotation类的构造方法注解
     */
    public static void parseConstructAnnotation() throws ClassNotFoundException  {
        Class clz = Class.forName("com.example.annotation.UseAnnotation");
        Constructor[] constructors = clz.getConstructors();
        for (Constructor constructor:constructors) {
            boolean hasAnnotation = constructor.isAnnotationPresent(FirstAnnotation.class);
            if (hasAnnotation) {
                FirstAnnotation annotation = (FirstAnnotation)constructor.getAnnotation(FirstAnnotation.class);
                System.out.println("name=" + annotation.name()
                        + ", age:" + annotation.age());
            }
        }
    }

    /**
     * 打印出UseAnnotation类所有的方法注解
     */
    public static void parseMethodAnnotation() throws ClassNotFoundException {
        Class clz = Class.forName("com.example.annotation.UseAnnotation");
        Method[] methods = clz.getDeclaredMethods();
        for (Method method:methods) {
            boolean hasAnnotation = method.isAnnotationPresent(FirstAnnotation.class);
            if (hasAnnotation) {
                Annotation[] annotations = method.getAnnotations();
                for(Annotation annotation:annotations) {
                    FirstAnnotation firstAnnotation = (FirstAnnotation)annotation;
                    System.out.println("name=" + firstAnnotation.name()
                            + ", age:" + firstAnnotation.age());
                }
            }
        }
    }

    /**
     * 打印出所有成员变量的注解
     */
    public static void parseParamAnnotation() throws ClassNotFoundException {
        Class clz = Class.forName("com.example.annotation.UseAnnotation");
        Field[] fields = clz.getDeclaredFields();
        for (Field field:fields) {
            boolean hasAnnotation = field.isAnnotationPresent(FirstAnnotation.class);
            if (hasAnnotation) {
                Annotation[] annotations = field.getAnnotations();
                for(Annotation annotation:annotations) {
                    FirstAnnotation firstAnnotation = (FirstAnnotation)annotation;
                    System.out.println("name=" + firstAnnotation.name()
                            + ", age:" + firstAnnotation.age());
                }
            }
        }
    }
}
