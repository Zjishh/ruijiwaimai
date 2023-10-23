package com.zjh.reggie.controller;

import com.zjh.reggie.mapper.SetMealDishMapper;
import com.zjh.reggie.service.SetMealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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
public class SetmealDishController {
    @Autowired
    private SetMealDishService setMealDishService;


}
