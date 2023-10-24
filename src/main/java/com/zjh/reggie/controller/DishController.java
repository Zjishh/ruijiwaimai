package com.zjh.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.reggie.dto.DishDto;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Dish;
import com.zjh.reggie.service.CategoryService;
import com.zjh.reggie.service.DishFlavorService;
import com.zjh.reggie.service.DishService;
import com.zjh.reggie.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/page")
    public Result<Page> list(Integer page, Integer pageSize, String name) {
        //构造分页构造器
        Page<Dish> pageinfo = new Page<>(page, pageSize);

        //添加构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        //添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageinfo, queryWrapper);

        Page<DishDto> pageinfos = new Page<>(page, pageSize);

        BeanUtils.copyProperties(pageinfo, pageinfos, "records");

        List<Dish> records = pageinfo.getRecords();

        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);

            Long categoryId = item.getCategoryId();

            Category category = categoryService.getById(categoryId);

            if (categoryService != null) {
                String categoryname = category.getName();

                dishDto.setCategoryName(categoryname);
            }

            return dishDto;

        }).collect(Collectors.toList());

        pageinfos.setRecords(list);


        return Result.success(pageinfos);

    }

    @GetMapping("/{id}")
    public Result<DishDto> getById(@PathVariable Long id) {
        DishDto byId = dishService.getById(id);
        return Result.success(byId);
    }

    @PostMapping
    public Result<String> addDish(@RequestBody DishDto dishDto) {
        dishService.addDish(dishDto);
        return Result.success("添加菜品成功");
    }

    @PutMapping
    public Result<String> updateDish(@RequestBody DishDto dishDto) {
        dishService.updateDishWithFlavor(dishDto);
        return Result.success("修改成功");
    }


    @GetMapping("/list")
    public Result<List<Dish>> list(Dish dish){
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null,Dish::getCategoryId,dish.getCategoryId());
        queryWrapper.eq(Dish::getStatus,1);
        List<Dish> list = dishService.list(queryWrapper);
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        dishService.list(queryWrapper);

        return Result.success(list);



    }
}
