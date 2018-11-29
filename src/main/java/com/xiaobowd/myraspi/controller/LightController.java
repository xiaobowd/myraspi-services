package com.xiaobowd.myraspi.controller;

import com.xiaobowd.myraspi.service.ILightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "小灯控制",description = "控制小灯的服务")
@RestController
@RequestMapping("/light")
public class LightController {
    private static final Logger logger = LoggerFactory.getLogger(LightController.class);
    @Autowired
    private ILightService lightService;

    @ApiOperation(value = "开灯",notes = "开灯")
    @GetMapping("/on")
    public void turnLightOn(){
        try {
//             lightService.turnLightOn();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @ApiOperation(value = "关灯",notes = "关灯")
    @GetMapping("/off")
    public void turnLightOff(){
        try {
//             lightService.turnLightOff();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
