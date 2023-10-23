package com.zjh.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.reggie.dto.DishDto;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Dish;
import com.zjh.reggie.entity.DishFlavor;
import com.zjh.reggie.mapper.CategoryMapper;
import com.zjh.reggie.mapper.DishFlavorMapper;
import com.zjh.reggie.mapper.DishMapper;
import com.zjh.reggie.service.CategoryService;
import com.zjh.reggie.service.DishFlavorService;
import com.zjh.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service.impl
 * @className DishServiceImpl
 * @author Zjiah
 * @date 2023/10/20 13:17
 * @Description:   *
 ****************************/
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private DishFlavorService dishFlavorService;

    @Override
    public void ddelete(Long ids) {
        dishMapper.ddelete(ids);

    }

    @Override
    public Integer dselect(Long ids) {
        Integer dselect = dishMapper.dselectcount(ids);
        return dselect;
    }

    @Override
    @Transactional
    public void addDish(DishDto dishDto) {
        dishMapper.addDish(dishDto);
        Long dishid = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishid);
            return item;
        }).collect(Collectors.toList());
        log.info(flavors.toString());

        dishFlavorService.saveBatch(flavors);

    }

    @Override
    public DishDto getById(Long id) {
        //查询基本信息
        Dish dish = super.getById(id);
        log.info(dish.toString());

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> list = dishFlavorService.list(dishFlavorLambdaQueryWrapper);

        dishDto.setFlavors(list);


        return dishDto;
    }
}
