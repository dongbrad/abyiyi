package com.abyiyi.tools.tmp.demo.wangbaoqiang;

/**
 * Created by dongqingsong on 2018/3/5.
 */
public class ProxyTest {
    public static void main(String[] args) {
        // 首先找到经纪人
        ActorJingJiRen proxy = new ActorJingJiRen();
        // 通过经纪人获得相关演员（代理对象）
        Actor p = proxy.getProxy();
        // 让演员唱歌
        String retValue = p.sing("天下无贼");
        System.out.println(retValue);
        // 让演员跳舞
        String value = p.dance("凤凰传奇");
        System.out.println(value);
    }
}
