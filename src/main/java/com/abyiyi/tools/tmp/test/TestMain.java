package com.abyiyi.tools.tmp.test;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * Created by dongqingsong on 2018/3/8.
 */
public class TestMain {
    public static void main(String[] args){
        HashMap<String,String> map  = new HashMap<String,String>();
        map.put("a","12");
        map.put("b","3");
        map.put("v","cc");
        map.put("3","ddd");
        System.out.println(map.toString());
        System.out.println(map.get("k"));
        System.out.println(JSONObject.toJSONString(map));
        String a= JSONObject.toJSONString(map);
        System.out.println(a);

        String url = "https://venus.yhd.com/channelForCps/getUnplGenerate";
        System.out.println(url.contains("/channelForCps/getUnplGenerate  "));
        System.out.println("--");
        System.out.println("http://search.m.yhd.com//".contains("//search.m.yhd.com"));
        String turl = "https://pro.yhd.com/yhd/active/xhHpfavcc3tSCvpFB8T58Jmxk1q/index.html";
        String burl = "http://search.m.yhd.com/c0-0/k%25E7%2589%259B%25E5%25A5%25B6/";
        System.out.println("2================"+turl.contains("search.m.yhd.com"));
        System.out.println("1================"+burl.contains("search.m.yhd.com"));
        if(true && false && false){
            System.out.println("2");
        }else{
            System.out.println("211");
        }

    }
}
