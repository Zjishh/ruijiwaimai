package com.zjh.reggie.utils;

/****************************
 * @project reggie
 * @package com.zjh.reggie.utils
 * @className CustomException
 * @author Zjiah
 * @date 2023/10/21 8:44
 * @Description:  自定义业务异常类 *
 ****************************/
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
