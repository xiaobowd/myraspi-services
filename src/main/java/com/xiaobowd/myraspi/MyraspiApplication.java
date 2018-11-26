package com.xiaobowd.myraspi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.xiaobowd.myraspi.mapper")
public class MyraspiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MyraspiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyraspiApplication.class);
    }
}
