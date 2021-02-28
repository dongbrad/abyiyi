package com.abyiyi.tools.tmp.spring;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 * Created by dongqingsong on 2019/7/6.
 */;
public class TestPropertyDemo {

    public static void main(String[] args) throws Exception {
        Field[] fields = Node.class.getDeclaredFields();
        for (Field field :fields) {
            System.out.println("name --"+field.getName() + "----"+field.getType());
            PropertyDescriptor p = new PropertyDescriptor(field.getName(),Node.class);
            System.out.println(p.toString());
            System.out.println("*****--"+p.getPropertyEditorClass());

        }


    }
}
