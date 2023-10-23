package com.zjh.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.reggie.dto.DishDto;
import com.zjh.reggie.entity.Dish;
import com.zjh.reggie.entity.Employee;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service
 * @className DishService
 * @author Zjiah
 * @date 2023/10/20 13:17
 * @Description:   *
 ****************************/
public interface DishService extends IService<Dish> {
    void ddelete(Long ids);

    Integer dselect(Long ids);


    void addDish(DishDto dishDto);

    DishDto getById(Long id);

    void updateDishWithFlavor(DishDto dishDto);
}
