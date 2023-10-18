package com.zjh.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/****************************
 * @project reggie
 * @package com.zjh.reggie.config
 * @className WbeMvcConfig
 * @author Zjiah
 * @date 2023/10/18 10:30
 * @Description:   配置webMVC*
 ****************************/
@Slf4j
@Configuration
public class WbeMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始静态资源映射");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");


    }
}
