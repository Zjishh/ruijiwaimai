package com.zjh.reggie.utils;

import org.springframework.stereotype.Component;

/****************************
 * @project reggie
 * @package com.zjh.reggie.utils
 * @className BaseContext
 * @author Zjiah
 * @date 2023/10/19 17:03
 * @Description:  基于ThreadLocal封装的工具类，用户保存和获取当前登录用户的ID *
 ****************************/
@Component
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setThreadLocal(Long id){
        threadLocal.set(id);
    }

    public static Long getThreadLocal(){
        return threadLocal.get();
    }
}
