package com.zjh.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Dish;
import com.zjh.reggie.mapper.CategoryMapper;
import com.zjh.reggie.mapper.DishMapper;
import com.zjh.reggie.service.CategoryService;
import com.zjh.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service.impl
 * @className DishServiceImpl
 * @author Zjiah
 * @date 2023/10/20 13:17
 * @Description:   *
 ****************************/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishMapper dishMapper;

    @Override
    public void ddelete(Long ids) {
        dishMapper.ddelete(ids);

    }

    @Override
    public Integer dselect(Long ids) {
        Integer dselect = dishMapper.dselectcount(ids);
        return dselect;
    }
}
