package com.xiaobowd.myraspi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Api
@RestController()
@RequestMapping("/img")
public class ImageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);


    @GetMapping("/view/{imageName}")
    @ApiOperation(value ="图片查看页面",notes = "查看指定名字的tomcat目录下的图片")
    public ModelAndView viewImage(@PathVariable("imageName")String imageName){
        ModelAndView modelAndView = new ModelAndView("image");
        imageName += ".jpg";
        modelAndView.addObject("imageName",imageName);
        return modelAndView;
    }

}
