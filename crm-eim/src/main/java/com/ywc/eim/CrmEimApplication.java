package com.ywc.eim;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.ywc.eim.mapper")
@SpringBootApplication
@EnableDubbo
public class CrmEimApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmEimApplication.class, args);
    }

}
