package com.abyiyi.tools.tmp.demo.wangbaoqiang;

/**
 * 王宝强是一个演员，他会唱歌和跳舞
 *
 */
public class WangBaoQiang implements Actor {
    private String name ;
    public WangBaoQiang(){
        this.name = "王宝强";
    }
    @Override
    public String sing(String name){
        System.out.println(this.getName() + "开始唱"+name+"歌！！");
        return "歌唱完了，谢谢大家！";
    }

    @Override
    public String dance(String name){
        System.out.println(this.getName() +  "开始跳"+name+"舞！！");
        return "舞跳完了，谢谢大家！";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
