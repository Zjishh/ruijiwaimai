package com.zjh.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/****************************
 * @project reggie
 * @package com.zjh.reggie
 * @className ReggieApplication
 * @author Zjiah
 * @date 2023/10/17 21:25
 * @Description:   *
 ****************************/
@SpringBootApplication
@Slf4j
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("启动启动启动");

    }
}
