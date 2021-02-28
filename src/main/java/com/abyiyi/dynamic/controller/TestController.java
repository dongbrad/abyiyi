package com.abyiyi.dynamic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dongqingsong on 2020/2/20.
 */
@RestController
public class TestController {

    @PostMapping("/a")
    public void test(String json){
        System.out.println("-----");
    }
}
