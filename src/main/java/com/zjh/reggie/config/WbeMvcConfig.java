package com.zjh.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

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

    
    //扩展MVC框架的消息转换器
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("********消息转换器启动**********");
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();//创建消息转换器对象
        messageConverter.setObjectMapper(new JacksonObjectMapper());//设置对象转换器 底层使用JACKSON转换
        converters.add(0,messageConverter);//序号0 最前面 追加到第一个
    }
}
