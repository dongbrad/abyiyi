package com.abyiyi.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dongqingsong on 2021/3/1.
 */
@RestController
public class ActivityController {

    @Resource
    ProcessEngine engineEngine ;//注入流程引擎

    @RequestMapping("/testActivi")
    public void test(){

        // 部署流程文件
        DeploymentBuilder builder = engineEngine.getRepositoryService().createDeployment();

        Deployment deploy = builder.addClasspathResource("processes/HelloWorld.bpmn").deploy();

        System.out.println("部署完成\n"+deploy.getId());

        System.out.println("----------------");
        // 启动流程
    }
}
