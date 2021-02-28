package com.abyiyi.tools.tmp.Generics;

/**
 * Created by dongqingsong on 2018/3/5.
 */
public class Container {

    private String key;
    private String value;

    public Container(String k, String v) {
        key = k;
        value = v;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
