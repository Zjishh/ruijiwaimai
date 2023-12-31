package com.zjh.reggie.exception;


import com.zjh.reggie.utils.CustomException;
import com.zjh.reggie.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/****************************
 * @project empservice
 * @package com.zjh.emp.exception
 * @className GlobeExceptionHandler
 * @author Zjiah
 * @date 2023/10/13 17:10
 * @Description: 全局异常处理器*
 ****************************/

@Slf4j
@RestControllerAdvice
public class GlobeExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<Object> SQLIntegrityConstraintViolationException(Exception e) {
        log.info("*********************************异常抛出" + e.toString());
        if (e.toString().contains("Duplicate entry")) {
            String repeat = null;
            String[] split = e.toString().split(" ");
            for (int i = 0; i < split.length - 3; i++) {
                if (split[i].equals("Duplicate") && split[i + 1].equals("entry")) {
                    repeat = split[i + 2];
                }
            }
            return Result.error("操作失败，"+repeat+"已存在");
        }
        log.info(e.toString());
        return Result.error("操作失败，联系管理员");
    }

    @ExceptionHandler(CustomException.class)
    public Result<Object> CustomException(Exception e) {
        log.info("*********************************异常抛出" + e.toString());

            return Result.error("操作失败，"+e.getMessage());

    }

}
