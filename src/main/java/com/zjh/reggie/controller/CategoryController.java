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
import org.springframework.web.bind.annotation.*;

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
    public Result<Page> list(Integer page, Integer pageSize, String name) {
        //构造分页构造器
        Page pageinfo = new Page(page, pageSize);
        //添加构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Category::getName, name);
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageinfo, queryWrapper);
        return Result.success(pageinfo);


    }

    @PostMapping
    public Result<String> csave(@RequestBody Category category) {
        categoryService.csave(category);
        return Result.success("OK");
    }

    @PutMapping
    public Result<String> cupdate(@RequestBody Category category) {
        categoryService.cupdate(category);
        return Result.success("OK");
    }



    @DeleteMapping
    public Result<String> cdelete( Long ids){
        categoryService.cdelete(ids);
        return Result.success("删除成功");
    }


}
