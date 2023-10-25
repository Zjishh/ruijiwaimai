package com.zjh.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Employee;
import com.zjh.reggie.mapper.CategoryMapper;
import com.zjh.reggie.mapper.DishMapper;
import com.zjh.reggie.mapper.EmployeeMapper;
import com.zjh.reggie.mapper.SetMealMapper;
import com.zjh.reggie.service.CategoryService;
import com.zjh.reggie.service.EmployeeService;
import com.zjh.reggie.utils.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service.impl
 * @className CategoryServiceimpl
 * @author Zjiah
 * @date 2023/10/19 17:54
 * @Description:   *
 ****************************/
@Service
@Slf4j
public class CategoryServiceimpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setMealMapper;

    @Override
    public void csave(Category category) {

        categoryMapper.scave(category);

    }

    @Override
    public void cupdate(Category category) {
        categoryMapper.cupdate(category);
    }

    @Override
    @Transactional
    public void cdelete(Long id) {
        categoryMapper.cdelete(id);
        Integer dselectcount = dishMapper.dselectcount(id);
        if (dselectcount > 0) {
            throw new CustomException("已经关联菜品，当前分类下有菜品 不能删除");
        }
        Integer sselectcount = setMealMapper.sselectcount(id);
        if (sselectcount > 0) {
            throw new CustomException("已经关联套餐，当前分类下有套餐 不能删除");
        }

    }

    @Override
    public List<Category> typelist(Integer type) {
        if (type == null){
           return categoryMapper.typelistnull();
        }
        return categoryMapper.typelist(type);
    }
}

