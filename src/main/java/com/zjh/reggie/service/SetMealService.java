package com.zjh.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.reggie.dto.SetmealDto;
import com.zjh.reggie.entity.Setmeal;

import java.util.List;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service
 * @className SetMealDish
 * @author Zjiah
 * @date 2023/10/23 20:31
 * @Description:   *
 ****************************/
public interface SetMealService extends IService<Setmeal> {
    void addSetMeal(SetmealDto setmealDto);

    void delete(List<Long> ids);
}
