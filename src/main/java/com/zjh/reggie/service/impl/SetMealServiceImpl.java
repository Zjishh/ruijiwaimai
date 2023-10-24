package com.zjh.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.reggie.dto.SetmealDto;
import com.zjh.reggie.entity.Setmeal;
import com.zjh.reggie.entity.SetmealDish;
import com.zjh.reggie.mapper.SetMealMapper;
import com.zjh.reggie.service.SetMealService;
import com.zjh.reggie.service.SetmealDishService;
import com.zjh.reggie.utils.CustomException;
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
    private SetMealService setMealService;


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

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        for (Long id : ids) {
            LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Setmeal::getId,id);
            wrapper.eq(Setmeal::getStatus,1);
            if (this.count(wrapper) > 0){
                throw new CustomException("状态为正在售卖，不能删除");
            }

            setMealService.removeById(id);
            LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SetmealDish::getSetmealId,id);
            setmealDishService.remove(queryWrapper);
        }
    }
}
