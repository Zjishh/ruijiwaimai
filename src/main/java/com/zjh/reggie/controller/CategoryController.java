package com.zjh.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Employee;
import com.zjh.reggie.service.CategoryService;
import com.zjh.reggie.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/****************************
 * @project reggie
 * @package com.zjh.reggie.controller
 * @className CategoryController
 * @author Zjiah
 * @date 2023/10/19 17:57
 * @Description:   *
 ****************************/
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<Page> list(Integer page, Integer  pageSize ,String name){
        //构造分页构造器
        Page pageinfo = new Page(page, pageSize);
        //添加构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Category::getName, name);
        //添加排序条件
        queryWrapper.orderByDesc(Category::getUpdateTime);
        categoryService.page(pageinfo, queryWrapper);
        return Result.success(pageinfo);


    }


}
