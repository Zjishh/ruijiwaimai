package com.zjh.reggie.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/****************************
 * @project reggie
 * @package com.zjh.reggie.config
 * @className JsonToStringConfig
 * @author Zjiah
 * @date 2023/10/19 10:14
 * @Description:   *
 ****************************/
@Configuration
public class JacksonConfiguration {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // Long 会自定转换成 String
            builder.serializerByType(Long.class, ToStringSerializer.instance);
        };
    }
}