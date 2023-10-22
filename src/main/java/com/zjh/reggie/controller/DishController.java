package com.zjh.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.reggie.entity.Dish;
import com.zjh.reggie.service.DishFlavorService;
import com.zjh.reggie.service.DishService;
import com.zjh.reggie.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/****************************
 * @project reggie
 * @package com.zjh.reggie.controller
 * @className DishController
 * @author Zjiah
 * @date 2023/10/21 9:37
 * @Description:   *
 ****************************/

@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;



    @GetMapping("/page")
    public Result<Page> list(Integer page, Integer pageSize, String name) {
        //构造分页构造器
        Page pageinfo = new Page(page, pageSize);
        //添加构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        //添加排序条件
        queryWrapper.orderByAsc(Dish::getUpdateTime);
        dishService.page(pageinfo, queryWrapper);
        return Result.success(pageinfo);

    }

    @GetMapping("/{id}")
    public Result<Dish> getById(@PathVariable Long id){
        Dish byId = dishService.getById(id);

        return Result.success(byId);
    }
}
