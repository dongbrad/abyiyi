package com.abyiyi.dynamic.cglib2;

import net.sf.cglib.proxy.Enhancer;

public class CglibTest {


    public static void main(String[] args) {
        DaoProxy daoProxy = new DaoProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(daoProxy);

        Dao dao = (Dao)enhancer.create();
        dao.update();
        dao.select();
    }


    
}