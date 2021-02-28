package com.abyiyi.dynamic.demo;

class TestClass {
    public int compute(int param) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param + 1000;
    }
}