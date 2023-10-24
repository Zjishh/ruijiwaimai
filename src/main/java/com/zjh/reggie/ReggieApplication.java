package com.zjh.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/****************************
 * @project reggie
 * @package com.zjh.reggie
 * @className ReggieApplication
 * @author Zjiah
 * @date 2023/10/17 21:25
 * @Description:   *
 ****************************/
@ServletComponentScan//过滤器开关
@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class, args);
        log.info(new String(new char[100]).replace("\0", "\r\n"));
        log.info("启动启动启动"+new String(new char[8]).replace("\0", "\r\n"));


    }
}
