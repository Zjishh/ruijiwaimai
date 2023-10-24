package com.zjh.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.reggie.dto.SetmealDto;
import com.zjh.reggie.entity.Setmeal;
import com.zjh.reggie.entity.SetmealDish;
import com.zjh.reggie.mapper.SetMealMapper;
import com.zjh.reggie.service.SetMealService;
import com.zjh.reggie.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service.impl
 * @className SetMealDisServiceImpl
 * @author Zjiah
 * @date 2023/10/23 20:31
 * @Description:   *
 ****************************/
@Service
@Slf4j
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetMealService {




    @Autowired
    private SetmealDishService setmealDishService;


    @Transactional
    public void addSetMeal(SetmealDto setmealDto) {
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);

    }
}
