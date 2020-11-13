package com.study.studythirdparty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

/**
 * @Author x.zaft
 * @Date 2020/11/13
 * @Version v1.0
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {


    final static Logger logger = LoggerFactory.getLogger(DemoController.class);
    @ApiOperation(value = "查找", notes = "test")
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String one(Integer v){
        logger.info("test log");
        return "say hello"+"--"+v;
    }
}
