package com.xiaobowd.mysite.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页
 * @author xiaobowd
 *
 */
@Log4j2
@Api
@RestController
@RequestMapping("/hello")
public class IndexController {

    @GetMapping("/hello")
    @ApiOperation(value = "你好",notes = "你好")
    public String hello(){
        return "你好！";
    }
}
