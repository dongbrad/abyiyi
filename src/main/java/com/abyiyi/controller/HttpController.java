package com.abyiyi.controller;

import com.abyiyi.tools.client.http.HttpClientTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dongqingsong on 2021/3/1.
 */
@RestController
public class HttpController {
    @Resource
    private HttpClientTemplate httpClientTemplate;
    @RequestMapping("/httpApi")
    public String druidData (String url)throws Exception{
        String url1 ="http://hq.sinajs.cn/list=sh601398";
        httpClientTemplate.doHttpGet(url,null);
        return "success" ;
    }

}

