package com.abyiyi.tools.tmp.Generics;

import java.util.HashMap;

/**
 * Created by dongqingsong on 2018/3/5.
 */
public class ContainerGenerics<K, V> {
    private K key;
    private V value;

    public ContainerGenerics(K k, V v) {
        key = k;
        value = v;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
    public static void main(String[] args) {
        ContainerGenerics<String, String> c1 = new ContainerGenerics<String, String>("name", "findingsea");
        ContainerGenerics<String, Integer> c2 = new ContainerGenerics<String, Integer>("age", 24);
        ContainerGenerics<Double, Double> c3 = new ContainerGenerics<Double, Double>(1.1, 2.2);
        System.out.println(c1.getKey() + " : " + c1.getValue());
        System.out.println(c2.getKey() + " : " + c2.getValue());
        System.out.println(c3.getKey() + " : " + c3.getValue());

    }
}
