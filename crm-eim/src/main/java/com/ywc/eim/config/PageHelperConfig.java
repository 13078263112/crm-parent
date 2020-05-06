package com.ywc.eim.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 嘟嘟~
 * @date 2020/3/24 14:39
 */
@Configuration
public class PageHelperConfig {
    @Bean
    public PageHelper createPaeHelper(){
        return new PageHelper();
    }
}
