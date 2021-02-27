package com.abyiyi.controller;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.logging.Logger;

@RestController
public class DruidController {
    @Resource
    private JdbcTemplate jdbcTemplate ;
    @RequestMapping("/druidData")
    public String druidData (){
        String sql = "SELECT COUNT(1) FROM test" ;
        Integer countOne = jdbcTemplate.queryForObject(sql,Integer.class) ;
        // countOne==2
        System.out.println("countOne===="+countOne);
        return "success" ;
    }
}