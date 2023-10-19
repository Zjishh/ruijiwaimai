package com.zjh.reggie.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/****************************
 * @project reggie
 * @package com.zjh.reggie
 * @className MyMeta
 * @author Zjiah
 * @date 2023/10/19 16:31
 * @Description:   *
 ****************************/
@Component
@Slf4j
public class MyMeta implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充");
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser",BaseContext.getThreadLocal());
        metaObject.setValue("createUser",BaseContext.getThreadLocal());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充");
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser",BaseContext.getThreadLocal());
    }
}
