package com.zjh.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.reggie.dto.SetmealDto;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Dish;
import com.zjh.reggie.entity.Setmeal;
import com.zjh.reggie.entity.SetmealDish;
import com.zjh.reggie.mapper.CategoryMapper;
import com.zjh.reggie.service.SetMealService;
import com.zjh.reggie.utils.Result;
import io.micrometer.core.instrument.binder.BaseUnits;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/****************************
 * @project reggie
 * @package com.zjh.reggie.controller
 * @className SetmealDishController
 * @author Zjiah
 * @date 2023/10/23 20:29
 * @Description:   *
 ****************************/
@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {

    

    @Autowired
    private SetMealService setMealService;

    @Autowired
    private CategoryMapper categoryMapper;


    @PostMapping
    public Result<String> addSetMeal(@RequestBody SetmealDto setmealDto){
        setMealService.addSetMeal(setmealDto);
        return Result.success("新增套餐成功");
    }

    @GetMapping("/page")
    public Result<Page> list( Integer page,Integer pageSize,String name){
        //构造分页构造器
        Page<Setmeal> pageinfo = new Page<>(page, pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>(page, pageSize);
        //添加构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Setmeal::getName, name);
        //添加排序条件
        queryWrapper.orderByAsc(Setmeal::getUpdateTime);
        setMealService.page(pageinfo, queryWrapper);

        BeanUtils.copyProperties(pageinfo,setmealDtoPage,"records");

        List<Setmeal> records = pageinfo.getRecords();

        List<SetmealDto> setmealDtos = null;

        setmealDtos = records.stream().map((item)->{
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);
            Long categoryId = item.getCategoryId();
            String getname = categoryMapper.getname(categoryId);
            if (getname != null){
                setmealDto.setCategoryName(getname);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        setmealDtoPage.setRecords(setmealDtos);

        return Result.success(setmealDtoPage);
    }

    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids){
        setMealService.delete(ids);
        return Result.success("删除成功");
    }
}