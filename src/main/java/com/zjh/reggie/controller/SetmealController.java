package com.zjh.reggie.controller;

import com.zjh.reggie.dto.SetmealDto;
import com.zjh.reggie.service.SetMealService;
import com.zjh.reggie.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping
    public Result<String> addSetMeal(@RequestBody SetmealDto setmealDto){
        setMealService.addSetMeal(setmealDto);
        return Result.success("新增套餐成功");
    }
}
